$(function () {
    $('#modal_info').on('hide.bs.modal', function () {//提示模糊框隐藏时候触发
        location.reload();  	//刷新当前页面
    });
});
function getForfeitInfoById(id) {
    ajax(
        {
            method: 'POST',
            url: '/admin/forfeitManageAction_getForfeitInfoById.action',
            params: "borrowId=" + id,
            type: "json",
            callback: function (data) {
                $("#borrowId").val(data.borrowId);
                $("#ISBN").val(data.ISBN);
                $("#bookName").val(data.bookName);
                $("#bookType").val(data.bookType);
                $("#paperNO").val(data.paperNO);
                $("#readerName").val(data.readerName);
                $("#readerType").val(data.readerType);
                $("#overday").val(data.overDay);
                if (data.isPay) {
                    $("#state").val("未缴纳罚款");
                } else {
                    $("#state").val("已缴纳罚款");
                }
                $("#admin").val(data.adminName);
            }
        }
    );
}
function showInfo(msg) {
    $("#div_info").text(msg);
    $("#modal_info").modal('show');
}


