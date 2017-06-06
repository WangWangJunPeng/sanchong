$(document).ready(function(){
			
			//当手机号码框失去焦点时执行去后台查找是否重复 的方法
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
			//对input框进行验证
			$("#createForm").validate({
				rules : {
					userCaption : {
						required : true,
						rangelength : [2,15]
					},
					phone : {
						required : true,
					},
					idCard : {
						required : true,
						rangelength : [18,18]
					}
					
				},
				messages : {
					userCaption : {
						required : "不能为空",
						rangelength : "输入有误"
					},
					phone : {
						required : "不能为空",
					},
					idCard : {
						required : "不能为空",
						rangelength : "身份证号码长度为18位"
					}
				},
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