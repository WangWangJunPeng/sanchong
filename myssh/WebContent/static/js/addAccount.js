$(document).ready(function(){
	$("#phone").blur(function() {
		if($('#phone').val() != ""){
			getPhoneNum()
		}
	});
	//当手机号码文本框得到焦点时把提示语置空
	$("#phone").focus(function() {
		$("#phone_exist").html("");
		$("#phone_ok").html("");
	});
});
//异步对手机号码进行校验，去数据库查找该手机是否已经注册过了
function getPhoneNum() {
	$.ajax({
		type : "post",
		async : true,
		url : "find_only_phone",
		data : {
			phoneNum : $("#phone").val()
		},
		success : function(data) {
			phoneIsExist(data);

		}
	});
}
//根据后台传来的data进行相应的操作
function phoneIsExist(data) {
	if (data.status == 200) {
		$("#phone_ok").html(data.message);
		$("#forNext").removeAttr("disabled");
		$("#forReturn").removeAttr("disabled");
	}
	if (data.status == 202) {
		$("#phone_exist").html(data.message);
		$("#phone_ok").html("");
		$("#forNext").attr("disabled", true);
		$("#forReturn").attr("disabled", true);
	}
}

function addAccountForm(obj){
	if($("input[name='userCaption']").val() != '' && $("input[name='phone']").val() != '' && $("input[name='idCard']").val() != ''){
		var toUrl = $(obj).data("value");
		$.ajax({
			type : "post",
			url : "account_manager_to_add_or_update",
			dataType:"json",
			data : $("#addCountVerify").serialize(),
			success:function(data) {
				if(data.code==200){
					 
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