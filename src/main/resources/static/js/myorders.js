// -----------------------------------------------------------------myorders  js 部分---------------------------------------------------
$("#personal_center_div").mouseover(function () {
    $("#dropdown_nav").css({"height": "210px"});
})
$("#personal_center_div").mouseleave(function () {
    $("#dropdown_nav").css({"height": "0"});
})


$("#nav_ul li:nth-child(1)").click(function (){
    window.location.href='/index';
})

$("#nav_ul li:nth-child(5)").click(function (){
  $("#notice").css({"display": "block"});
})
$("#tui").click(function (){
    $("#notice").css({"display": "none"});
})




window.onload=(function (){

    for(let i=1;i<=$("#page_total").val();i++){
        $("#append_li").append(`<li>${i}</li>`);
    }

    if($("title").text()=="我的订单"){
        $("#buy_span").css({"background-color":"#8acab9"});
        var n=$('#page_num').val();
        $(`#append_li li:nth-child(${n})`).css({"background-color":"#b9e8d5"});

        $("#append_li li").click(function (){
            var num=$(this).text();
            var hrefValue = `/myorders?start=${num}` ;
            window.location.href=hrefValue;
        })
    }
    if($("title").text()=="我的求购"){
        var n=$('#page_num').val();
        $(`#append_li li:nth-child(${n})`).css({"background-color":"#b9e8d5"});

        $("#append_li li").click(function (){
            var num=$(this).text();
            var hrefValue = `/myTobuy?start=${num}` ;
            window.location.href=hrefValue;
        })
    }
    if($("title").text()=="我的售出"){
        $("#sale_span").css({"background-color":"#8acab9"});
        var n=$('#page_num').val();
        $(`#append_li li:nth-child(${n})`).css({"background-color":"#b9e8d5"});

        $("#append_li li").click(function (){
            var num=$(this).text();
            var hrefValue = `/saleorders?start=${num}`;
            window.location.href=hrefValue;
        })
    }
    if($("title").text()=="我的租购订单"){
        $("#buy_span_rental").css({"background-color":"#8acab9"});
        var n=$('#page_num').val();
        $(`#append_li li:nth-child(${n})`).css({"background-color":"#b9e8d5"});

        $("#append_li li").click(function (){
            var num=$(this).text();
            var hrefValue = `/myodersrental?start=${num}` ;
            window.location.href=hrefValue;
        })
    }
    if($("title").text()=="我的租售订单"){
        $("#sale_span_rental").css({"background-color":"#8acab9"});
        var n=$('#page_num').val();
        $(`#append_li li:nth-child(${n})`).css({"background-color":"#b9e8d5"});

        $("#append_li li").click(function (){
            var num=$(this).text();
            var hrefValue = `/saleordersrental?start=${num}` ;
            window.location.href=hrefValue;
        })
    }


});

(function (){
    if($("#page_num").val()==$("#page_total").val()){
        $("#right_a").removeAttr("href");
    }
    if($("#page_num").val()==1){
        $("#left_a").removeAttr("href");
    }
})();


$("#buy_span").click(function (){
    $("#buy_span").css({"background-color":"#eaeaea"});
    $("#sale_span").css({"background-color":"#eaeaea"});
    $("#buy_span_rental").css({"background-color":"#eaeaea"});
    $("#sale_span_rental").css({"background-color":"#eaeaea"});

    window.location.href='/myorders';
})
$("#sale_span").click(function (){
    $("#buy_span").css({"background-color":"#eaeaea"});
    $("#sale_span").css({"background-color":"#eaeaea"});
    $("#buy_span_rental").css({"background-color":"#eaeaea"});
    $("#sale_span_rental").css({"background-color":"#eaeaea"});

    window.location.href='/saleorders';

})
$("#buy_span_rental").click(function (){
    $("#buy_span").css({"background-color":"#eaeaea"});
    $("#sale_span").css({"background-color": "#eaeaea"});
    $("#buy_span_rental").css({"background-color":"#eaeaea"});
    $("#sale_span_rental").css({"background-color":"#eaeaea"});

    window.location.href='/myordersrental';

})

$("#sale_span_rental").click(function (){
    $("#buy_span").css({"background-color":"#eaeaea"});
    $("#sale_span").css({"background-color": "#eaeaea"});
    $("#buy_span_rental").css({"background-color":"#eaeaea"});
    $("#sale_span_rental").css({"background-color":"#eaeaea"});
    window.location.href='/saleordersrental';



})




$("#dropdown_nav li:nth-child(6)").click(function (){
    window.location.href='/myTobuy';
})

$("#main_mark").click(function (){
    window.location.href='/index';
})











// ---------------------------------------------------------------saleorders js 部分-------------------------------------------------------------------









