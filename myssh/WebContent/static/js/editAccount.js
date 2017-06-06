function addAccountForm(obj){
	if($("input[name='userCaption']").val() != '' && $("input[name='phone']").val() != '' && $("input[name='idCard']").val() != ''){
	var toUrl = $(obj).data("value");
	$.ajax({
		type : "post",
		url : "account_manager_to_add_or_update",
		dataType:"json",
		data : $("#alertAccountVerify").serialize(),
		success : function(data) {
			if(data.code==200){
				//alert(data.msg);
				if(toUrl==1){
					window.location.href = "goto_accountsnum_manage_page";
				}else if(toUrl==0){
					window.location.href = "to_account_manager_page";
				}
			}else if(data.code==201){
				alert(data.msg);
			}
		}
	});
	}else{
		layer.alert("信息不能为空",{icon: 5});
	}
}