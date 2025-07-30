package com.cupk.Controller;

import com.cupk.Service.ProductService;
import com.cupk.dto.FrontendRecRequest;
import com.cupk.dto.RecommendationRequest;
import com.cupk.pojo.Product;
import com.cupk.pojo.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController // 使用@RestController因为它返回JSON数据，而不是视图
@RequestMapping("/api") // 给所有接口加上/api前缀，方便管理
public class RecommendationController {

    @Autowired
    private ProductService productService;

    // 使用 RestTemplate 来调用 Python API
    private final RestTemplate restTemplate = new RestTemplate();

    // 从 application.properties 或 application.yml 文件中读取Python服务的地址
    // 示例: recommendation.service.url=http://127.0.0.1:8000
    @Value("${recommendation.service.url}")
    private String recommendationServiceUrl;

    @PostMapping("/recommend")
    public List<Product> getRecommendations(@RequestBody(required = false) FrontendRecRequest request, HttpSession session) {
        // 1. 准备向Python服务发送的请求
        RecommendationRequest pythonRequest = new RecommendationRequest();
//        System.out.println(request);
        // 检查前端是否传入了 product_id (用于商品详情页的推荐)
        if (request != null && request.getProduct_id() != null) {
            pythonRequest.setProduct_id(request.getProduct_id());
        }

        // 检查前端是否指定了数量n
        if (request != null && request.getN() != null) {
            pythonRequest.setN(request.getN());
        }

        // 2. 检查用户是否登录，如果登录了就设置user_id，以获取个性化推荐
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser != null) {
            pythonRequest.setUser_id(loginUser.getId());
        }

        // 如果用户未登录，且不是在商品详情页，pythonRequest将为空，服务会返回热门商品

        try {
            // 3. 调用Python的API
            String apiUrl = recommendationServiceUrl + "/recommend/hybrid";
            System.out.println(apiUrl);
            HttpEntity<RecommendationRequest> entity = new HttpEntity<>(pythonRequest);

            // 定义期望的返回类型是 List<Integer>
            ParameterizedTypeReference<List<Integer>> responseType = new ParameterizedTypeReference<>() {};
            ResponseEntity<List<Integer>> response = restTemplate.exchange(apiUrl, HttpMethod.POST, entity, responseType);

            List<Integer> recommendedProductIds = response.getBody();
            System.out.println("================推荐的ids");
            System.out.println(recommendedProductIds);
            if (recommendedProductIds == null || recommendedProductIds.isEmpty()) {
                return Collections.emptyList(); // 返回空列表
            }

            // 4. 根据ID列表，从数据库查询完整的商品信息
            // 注意: 这里假设你的服务能处理根据ID列表查询，如果不能，需要循环查询
            List<Product> recommendedProducts = productService.findProductByids(recommendedProductIds);

            // 按推荐顺序排序返回的结果
            List<Product> sortedProducts = recommendedProductIds.stream()
                    .flatMap(id -> recommendedProducts.stream().filter(p -> p.getId().equals(id)))
                    .collect(Collectors.toList());

            return sortedProducts;

        } catch (Exception e) {
            System.err.println("调用推荐服务失败: " + e.getMessage());
            // 在生产环境中，这里应该使用日志框架
            return Collections.emptyList(); // 失败时返回空列表，避免前端报错
        }
    }
}