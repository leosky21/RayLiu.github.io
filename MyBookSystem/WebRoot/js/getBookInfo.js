$(function () {
		$('#modal_info').on('hide.bs.modal',function() {//提示模糊框隐藏时候触发
       		 location.reload();  	//刷新当前页面
    	});
});
function getBookInfo(id){
	ajax(
		  {
		  	method:'POST',
    		url:'bookManageAction_getBookInfo.action',
			params: "bookId=" + id,
			type:"json",
    		callback:function(data) {
				$("#findISBN").val(data.ISBN);
				$("#findBookName").val(data.name);
				$("#findBookType").val(data.bookType);
				$("#findAutho").val(data.autho);
				$("#findPress").val(data.press);
				$("#findPrice").val(data.price);
				$("#findDescription").val(data.description);
				$("#findNum").val(data.num);
				$("#findAdmin").val(data.admin);
				$("#findCurrentNum").val(data.currentNum);
			}
		}
	);
	console.log(id);
}
function showInfo(msg) {
    $("#div_info").text(msg);
    $("#modal_info").modal('show');
}


