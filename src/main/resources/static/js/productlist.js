//重置按钮
function resetSearchForm() {
    document.getElementById("searchStr").value = ""; // 清空搜索框内容
}

//批量删除按钮
function dels() {
    if (confirm("确定删除所选商品吗？")) {
        var ids = "";
        $(".ids").each(
            function (i, e) {
                if ($(e).is(":checked")) {
                    var id = $(e).val();
                    ids += id + ",";
                }
            }
        );
        if (ids.length == 0) {
            alert("未选择任何商品,请选择要删除的商品！");
        } else {
            ids = ids.substr(0, ids.length - 1);
            location.href = "deleteProductsa?id=" + ids;
            //构造出类似deleteProducts?id=1,2,3这样的URL,后台可用数组接收
        }
    }
}


//能够不跳转到首页(by.li)
    window.addEventListener('beforeunload', function() {
    sessionStorage.setItem('scrollPosition', window.scrollY.toString());
});
    document.addEventListener('DOMContentLoaded', function() {
    var scrollPosition = sessionStorage.getItem('scrollPosition');
    if (scrollPosition !== null) {
    window.scrollTo(0, parseInt(scrollPosition));
}
});
