
$(function () {
	

	
		$('#modal_info').on('hide.bs.modal',function() {//提示模糊框隐藏时候触发
       		 location.reload();  	//刷新当前页面
    	});
	
	

});



function getBorrowInfoById(id){
	ajax(
		  {
		  	method:'POST',
    		url:'admin/borrowManageAction_getBorrowInfoById.action',
			params: "borrowId=" + id,
			type:"json",
    		callback:function(data) {
				$("#borrowId").val(data.id);
				$("#ISBN").val(data.ISBN);
				$("#bookName").val(data.bookName);
				$("#bookType").val(data.bookType);
				$("#paperNO").val(data.paperNO);
				$("#readerName").val(data.readerName);
				$("#readerType").val(data.readertype);
				$("#overday").val(data.overday);
				if (data.state == 0) {
					$("#state").val("未归还");
				}else if(data.state == 1){
					$("#state").val("逾期未归还");
				}else if(data.state == 2){
					$("#state").val("归还");
				}else if(data.state == 3){
					$("#state").val("续借未归还");
				}else if(data.state == 4){
					$("#state").val("续借逾期未归还");
				}else if(data.state == 5){
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


