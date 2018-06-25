$(function () {
    $('#modal_info').on('hide.bs.modal', function () {//提示模糊框隐藏时候触发
        location.reload();  	//刷新当前页面
    });
});
function getBackInfoById(id) {
    ajax(
        {
            method: 'POST',
            url: '/admin/backManageAction_getBackInfoById.action',
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
                if (data.backState == 0) {
                    $("#state").val("未归还");
                } else if (data.backState == 1) {
                    $("#state").val("逾期未归还");
                } else if (data.backState == 2) {
                    $("#state").val("归还");
                } else if (data.backState == 3) {
                    $("#state").val("续借未归还");
                } else if (data.backState == 4) {
                    $("#state").val("续借逾期未归还");
                } else if (data.backState == 5) {
                    $("#state").val("续借归还");
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