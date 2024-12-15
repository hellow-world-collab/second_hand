$("#search_input").focus(function () {
    $(this).attr("value", "");
    $(this).css("opacity", "0.9");

})

$("#search_input").blur(function () {
    // $(this).css("opacity","0.3");
    $(this).attr("value", "搜索商品点击这里哟");
})


$("#personal_center_div").mouseover(function () {
    $("#dropdown_nav").css({"height": "220px"});
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





$("#nav_ul li:nth-child(1)").click(function (){
    window.location.href='/index';
})
// $("#nav_ul li:nth-child(2)").click(function (){
//     window.location.href='/index';
// })
$("#nav_ul li:nth-child(3)").click(function (){

             window.location.href='/wantobuy';
})
// $("#nav_ul li:nth-child(4)").click(function (){
//     window.location.href='/index';
// })
$("#nav_ul li:nth-child(6)").click(function (){
  $("#notice").css({"display": "block"});

})
$("#tui").click(function (){
    $("#notice").css({"display": "none"});
})


$("#center_commodity_div .commodity").click(function (){
    // alert("cnm");

    var this_commodity_id=$(this).find("#commodity_id").val();
    var hrefValue = `commodityDetail/${this_commodity_id}` ;
    window.location.href=hrefValue;
})




window.onscroll = function() {

    var scrollTop=0;
    if(document.documentElement&&document.documentElement.scrollTop){
        scrollTop = document.documentElement.scrollTop;
    }else if(document.body){
        scrollTop = document.body.scrollTop;
    }
    console.log(scrollTop);
    if(scrollTop>=1400){
        $("#to_top").css({"visibility":"visible"});
        $("#to_top").attr("class","to_top_an1");

    }

        if(scrollTop<=800){
                $("#to_top").css({"visibility":"hidden"});
                $("#to_top").removeAttr("class","to_top_an1");
                $("#to_top").attr("class","to_top_an2");


        }

}
var myTimer = -1;
$("#to_top").click(function (){
    var scrollTo = document.documentElement.scrollTop || document.body.scrollTop
    if(myTimer == -1){
        myTimer = setInterval(() => {
            scrollTo -= 60
            if(scrollTo<=0){
                scrollTo = 0
                window.clearInterval(myTimer);
                myTimer = -1
            }

            window.scrollTo(0,scrollTo) //这是值  是指离开网页顶部的距离
        }, 1.5);

    }

})


$("#to_top").mouseover(function (){
    $(this).find("img").attr("src","/icon/回到顶部after.png");
    $(this).css({"background-color":"#7b837b"});

})
$("#to_top").mouseout(function (){
    $(this).find("img").attr("src","/icon/回到顶部.png");
    $(this).css({"background-color":" #dfdede"});

})


$("#logo_search").click(function (){
    $("#searchForm").submit();
})








