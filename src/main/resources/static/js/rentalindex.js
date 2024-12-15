$("#search_input").focus(function () {
    $(this).attr("value", "");
    $(this).css("opacity", "0.9");

})

$("#search_input").blur(function () {
    // $(this).css("opacity","0.3");
    $(this).attr("value", "搜索商品点击这里哟");
})


$("#personal_center_div").mouseover(function () {
    $("#dropdown_nav").css({"height": "270px"});
})
$("#personal_center_div").mouseleave(function () {
    $("#dropdown_nav").css({"height": "0"});
})



$("#kind_select").change(function (){
    $("#kind_select_submit").submit();
})


var backendValue = document.getElementById("selectvalue").value; // 后端传递的值
var select = document.getElementById("kind_select"); // 获取select元素
for (var i = 0; i < select.options.length; i++) {
    if (select.options[i].value === backendValue) {
        select.options[i].selected = true; // 选中匹配的option
        break;
    }
}


$("#dropdown_nav li:nth-child(3)").click(function (){
    window.location.href="/myorders";
})


$("#dropdown_nav li:nth-child(6)").click(function (){
    window.location.href='/myTobuy';
})




$("#center_commodity_div .commodity").click(function (){
    // alert("cnm");

    var this_commodity_id=$(this).find("#commodity_id").val();
    var hrefValue = `/commodityDaetail/${this_commodity_id}` ;
    window.location.href=hrefValue;
})











