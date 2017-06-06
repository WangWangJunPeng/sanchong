$(document).ready(function(){
        	 //省市区ajax请求
            $("#province").change(function(){
            			var pId = $("#province").val();
            			$("#market").empty();
            			$.post("menu_list_city_area",
        						{
        						    "shengOrShi":pId
        						  },
        						  function(data){  
        							 var data = eval('('+ data +')');
        							 for(var i=0;i<data.root.length;i++){
        								 $("<option value='"+data.root[i].cityId+"'>"+data.root[i].cityName+"</option>").appendTo($("#market"))
        							 }
        								 changeMarket();
        							 
        						  });
            		})
            		function changeMarket(){
            			var pId = $("#market").val();
            			//alert(pId);
            			$("#area").empty();
            			$.post("menu_list_city_area",
        						{
        						    "shengOrShi":pId
        						  },
        						  function(data){  
        							 var data = eval('('+ data +')');
        							 for(var i=0;i<data.root.length;i++){
        								 $("<option value='"+data.root[i].cityId+"'>"+data.root[i].cityName+"</option> ").appendTo($("#area"));
        							 }
        						  });
            		}
            		
            		$("#market").change(function(){
            			changeMarket();
            		})
            	//自定义的插件方法,用于对图片的校验
				jQuery.validator.addMethod("photoPic", function(value, element) {
					var geshi = /\.(gif|jpg|jpeg|png|GIF|JPG|PNG|JPEG)$/;
					return this.optional(element) || (geshi.test(value));
				}, "图片格式只支持jpg,png,gif和jpeg");
            		jQuery.validator.addMethod("licensePic", function(value, element) {
    					var geshi = /\.(gif|jpg|jpeg|png|GIF|JPG|PNG|JPEG)$/;
    					return this.optional(element) || (geshi.test(value));
    				}, "图片格式只支持jpg,png,gif和jpeg");
            	//对input框进行验证	
				$("#form-box").validate({
					rules : {
						companyName : "required",
						shopName : "required",
						area : "required",
						address : "required",
						photoPic : {
							required : true,
							photoPic : true
						},
						licensePic : {
							required : true,
							licensePic : true
						},
						contactPerson : {
							required : true,
							rangelength : [ 2, 15 ]
						},
						phone : {
							required : true,
						},
						email : {
							required : true,
							email : true
						},
						bank_cards : {
							required : true,
							digits:true,
							rangelength : [ 15, 19 ]
						},
						bankName : {
							required : true,
							minlength : 5,
						},
						representative : {
							required : true,
							rangelength : [ 2, 10 ]
						}
					},
					//验证失败之后的提示语
					messages : {
						companyName : "不能为空",
						shopName : "不能为空",
						area : "不能为空",
						address : "不能为空",
						photoPic : {
							required : "不能为空"
						},
						licensePic : {
							required : "不能为空"
						},
						photoPic : {
							required : "不能为空"
						},
						licenseNo : {
							required : "不能为空"
						},
						contactPerson : {
							required : "不能为空",
							rangelength : "非法字符"
						},
						phone : {
							required : "不能为空",
						},
						email : {
							required : "不能为空",
							email : "邮箱地址错误"
						},
						bank_cards : {
							required : "不能为空",
							digits:"非法字符",
							rangelength : "请输入正确的银行卡号"
						},
						bankName : {
							required : "不能为空",
							minlength : "请输入正确的开户行名称",
						},
						representative : {
							required : "不能为空",
							rangelength : "非法字符"
						}
					},
					//图片格式错误的提示放在框外面
					errorPlacement: function(error, element){ 
			            
			        	 if (element.parent().parent().children().hasClass('up-pic') ) {
			                 
			                 error.appendTo(element.parent().parent());

			             }else if(element.parent().children().hasClass('otherError')){
			            	 error.insertAfter($(".eer"));
			             }
			             else{
			                 error.appendTo(element.parent()); 
			             }
			        }
			      

				});
				
            		
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
				//图片回显调用方法 ，下同
				$("#photoPic").change(function() {
					var file = document.getElementById('photoPic').value;
					if(file == ""){		//如果input框中没有文件就把img中的文件删除
						$('#newImg').attr("src","");
					}else{		//如果不为空那就IMG中显示图片
						newFile();
					}
				});
				$("#licensePic").change(function() {
					var file = document.getElementById('licensePic').value;
					if(file == ""){
						$('#oldImg').attr("src","");
					}else{
						oldFile();
					}
				}); 
				
				//当from提交的时候进行判断插件的验证是否全部通过，如果通过的话就弹出“正在注册...”; $('#form-box').valid()就是验证所有的验证是否全部通过
				$('#sub').click(function(){   //当点击提交的时候如果所有的验证都通过的话就弹出这个"正在注册的..."的提示框
					if($('input').val() != "" && $('#form-box').valid()){
					$('#photo_error').dialogBox({	//弹窗插件，注意一定要创建一个空的div而且id="photo_error",不然就会失效
						width: 250,
						height: 160,
						autoHide: true,
						time: 10000000,
						hasMask: true,
						hasBtn: false,
						effect: 'flip-horizontal',
						title: '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;register',
						content: '&nbsp;&nbsp;&nbsp;&nbsp;正在注册中，即将跳转登录页...'
					});
					}
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
			$("#sub").removeAttr("disabled");
		}
		if (data.status == 202) {
			$("#phone_exist").html(data.message);
			$("#phone_ok").html("");
			$("#sub").attr("disabled", true);
		}
	}