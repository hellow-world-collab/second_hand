
$("#personal_center_div").mouseover(function () {
    $("#dropdown_nav").css({"height": "270px"});
}).mouseleave(function (){
    $("#dropdown_nav").css({"height": "0"});
})



$("#dropdown_nav li:nth-child(3)").click(function (){
    window.location.href="/myorders";
})


$("#dropdown_nav li:nth-child(6)").click(function (){
    window.location.href='/myTobuy';
})

$("#main_mark").click(function (){
    window.location.href='/index';
})

$("#nav_ul li:nth-child(1)").click(function (){
    window.location.href='/index';
})
// $("#nav_ul li:nth-child(2)").click(function (){
//     window.location.href='/index';
// })
// $("#nav_ul li:nth-child(3)").click(function (){
//     window.location.href='/index';
// })
// $("#nav_ul li:nth-child(4)").click(function (){
//     window.location.href='/index';
// })
$("#nav_ul li:nth-child(5)").click(function (){

})









for(let i=1;i<=$("#discussnum").val();i++){
// alert($(".nmd").attr("id"));
    var dis_id=$('#dis_id'+i).val();
//     alert($('#name'+i).val());
    var user_id=$('#disnameid'+i).val();
    var name=$('#disname'+i).val();
    // alert(name);
    var text=$('#text'+i).val();
    // alert(text);
    $("#dis_div").prepend(' <div class="reviewer_and_reply"></div>');
    // alert($('#username'+'1001').val());
    // $(".reviewer_and_reply:nth-child(1)").attr("id",'dis_id'+i);
    // alert($(".reviewer_and_reply:nth-child(1)").attr("id"));
    $(".reviewer_and_reply:nth-child(1)").prepend(` <div class="rev">
                            <input type="hidden" value="${dis_id}" class="this_dis_id">
                            <input type="hidden" value="${user_id}" class="reply_user_id">
                            <div class="rev_head_img"><img th:src="@{/images/素材-39.png}" alt=""></div>
                            <div class="rev_username">${name}</div>
                            <div class="rev_content">${text}</div>
<!--                            <div class=rev_time>时间</div>-->
                            <div class="disbutton">评论</div>
                        </div>`);


    for(let j=1;j<=$("#replynum").val();j++){
        if(dis_id==$('#dis_id_rep'+j).val()){
            // var name_id=$('#nameid'+j).val();
            var name=$('#name'+j).val();
            var toname=$('#toname'+j).val();
            var tonameid=$('#tonameid'+j).val();
            var name_id=$('#nameid'+j).val()

            var reptext=$('#reply_text'+j).val();
            var user_id=$("#uuuu"+i).val();
            if(tonameid==user_id){
                $(".reviewer_and_reply:nth-child(1) .rev").after(`<div class="rep">
                        <input type="hidden" value="${dis_id}" class="this_dis_id2">
                        <input type="hidden" value="${name_id}" class="reply_user_id2">
                            <div class="rep_head_img">头像</div>
                            <div class="rep_username">${name}</div>
                            <div class="rep_content">${reptext}</div>
<!--                            <div class="rep_time">回复时间</div>-->
                            <div class="repbutton">回复</div>
                        </div>`)
            }
            else{
                var nameTname=$('#nameTname'+j).val();
                // alert(nameTname);
                $(".reviewer_and_reply:nth-child(1) .rev").after(`<div class="rep_to_rep">
                            <input type="hidden" value="${dis_id}" class="this_dis_id3">
                        <input type="hidden" value="${name_id}" class="reply_user_id3">
                            <div class="rep2_head_img">头像</div>
                            <div class="rep2_to_rep_username">${nameTname}</div>
                            <div class="rep2_to_rep_content">${reptext}</div>
<!--                            <div class="rep2_to_rep_time">时间</div>-->
                            <div class="rep2_to_repbutton">回复</div>
                        </div>`);

            }
        }
    }

}

$(".disbutton").click(function (){
    $(".place1").remove();
    $(".place2").remove();
    $(".place3").remove();
    var this_dis_id=$(this).closest('.rev').find(".this_dis_id").val();
    var reply_user_id=$(this).closest('.rev').find(".reply_user_id").val();

    $(this).closest('.rev').after(`<div class="place1">
 <textarea placeholder="请输入评论"  class="rep_textarea"></textarea>
  <div id="place1_btn">评论</div></div>`);
    $("#place1_btn").click(function (){

        var comid=$("#commodity_id").val();
        var text=$(".rep_textarea").val();
        var this_user_id=$("#this_user_id").val();

        $.ajax({
            // type:"post",
            // url为插入评论的mapping请求
            url:`/addReply`,
            data:{"user_id":this_user_id,"reply_text":text,"parent_id":this_dis_id,"reply_user_id":reply_user_id}
        })
        setTimeout(function (){
            window.location.href=`/commodityDetail/${comid}`;
        },10);

        $(".place1").remove();
    })

})
$(".repbutton").click(function (){
    $(".place1").remove();
    $(".place2").remove();
    $(".place3").remove();

    var this_dis_id=$(this).closest('.rep').find(".this_dis_id2").val();
    var reply_user_id=$(this).closest('.rep').find(".reply_user_id2").val();


    $(this).closest('.rep').after(`<div class="place2">
 <textarea placeholder="请输入评论" class="rep_textarea"></textarea>
  <div id="place2_btn">评论</div></div>`);


    $("#place2_btn").click(function (){
        var comid=$("#commodity_id").val();
        var text=$(".rep_textarea").val();
        var this_user_id=$("#this_user_id").val();

        $.ajax({
            // type:"post",
            // url为插入评论的mapping请求
            url:`/addReply`,
            data:{"user_id":this_user_id,"reply_text":text,"parent_id":this_dis_id,"reply_user_id":reply_user_id}
        })
        setTimeout(function (){
            window.location.href=`/commodityDetail/${comid}`;
        },10);


        $(".place2").remove();
    })


})
$(".rep2_to_repbutton").click(function (){
    $(".place1").remove();
    $(".place2").remove();
    $(".place3").remove();

    var this_dis_id=$(this).closest('.rep_to_rep').find(".this_dis_id3").val();
    var reply_user_id=$(this).closest('.rep_to_rep').find(".reply_user_id3").val();


    $(this).closest('.rep_to_rep').after(`<div class="place3">
 <textarea placeholder="请输入评论" class="rep_textarea"></textarea>
  <div id="place3_btn">评论</div></div>`);


    $("#place3_btn").click(function (){
        var comid=$("#commodity_id").val();
        var text=$(".rep_textarea").val();
        var this_user_id=$("#this_user_id").val();

        $.ajax({
            // type:"post",
            // url为插入评论的mapping请求
            url:`/addReply`,
            data:{"user_id":this_user_id,"reply_text":text,"parent_id":this_dis_id,"reply_user_id":reply_user_id}
        })
        setTimeout(function (){

            window.location.href=`/commodityDetail/${comid}`;
        },10);


        $(".place3").remove();
    })


})

$("#pub_button").click(function (){
    var id=$("#commodity_id").val();
    var text=$("#textarea").val();
    var this_user_id=$("#this_user_id").val();
    $.ajax({
        // type:"post",
        // url为插入评论的mapping请求
        url:`/adddiscussHost`,
        data:{"user_id":this_user_id,"discuss_text":text,"product_id":id},
    })
    setTimeout(function (){
        window.location.href=`/commodityDetail/${id}`;
    },10);

})



$("#buy").click(function (){
    var com_id=$("#commodity_id").val();
    window.location.href=`/pay/${com_id}`;
})





window.onscroll = function() {

    var scrollTop=0;
    if(document.documentElement&&document.documentElement.scrollTop){
        scrollTop = document.documentElement.scrollTop;
    }else if(document.body){
        scrollTop = document.body.scrollTop;
    }

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















