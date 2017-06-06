	/*当页面加载完成时调用*/
		$(document).ready(function() {
			getAllUserData();
			
			$("#search").click(function() {
				getAllUserData();
			});
				$(".reset-pop").click(function(){
			        popBoxA($(this).data("value"));
			        $(".is-reset").show();
			    })
			     $(".stop-pop").click(function(){
			    	 popBoxA($(this).data("value"));
			        $(".is-stop").show();
			    })
			    $(".open-pop").click(function(){
			    	popBoxA($(this).data("value"));
			        $(".is-open").show();
			    })
			      $(".cancel-pop").click(function(){
			    	  popBoxA($(this).data("value"));
			        $(".is-cancel").show();
			    })
			
			
		});
		/*ajax同步发请求（当页面加载的时候）*/
		function getAllUserData() {
		var stas = $("#status").val();
			$.ajax({
				type : "post", /*为post方式*/
				async : false, /*开启同步请求，true为异步请求*/
				url : "system_all_user", /*url为发请求的url，利用Controller@RequestMapping进行拦截*/
				data : {"status" : stas},
				success : function(data) { /*当请求成功之后回调*/
					data = eval("(" + data + ")");
					fillUserInfo(data.root); /*获取json串,并传给这个方法*/
				},
			});
		}
		
		function popBoxA(data) {
			var sWidth = document.body.scrollWidth;
			var sHeight = document.body.scrollHeight;
			// 获取页面的可视区域高度和宽度
			var wHeight = document.documentElement.clientHeight;
			var oMask = document.createElement("div");
			oMask.className = "mask";
			oMask.style.height = sHeight + "px";
			oMask.style.width = sWidth + "px";
			document.body.appendChild(oMask);
			//重置密码
			$(".is-reset .sure-btn").click(function() {
				$.ajax({
					type : "post",
					url : "account_manager_to_update_userInfo",
					data : {userId : data,doSign : "reset"},
					success : function(data) {
						alert("密码重置成功...");
						window.location.href = "all_user_info_page";
					}
				});
				//清空当前对象中的内容
				$(".sure-btn").off();
				$(".is-reset").hide();
				$(".mask").remove();
			})
			$(".is-reset .close-btn").click(function() {
				$(".is-reset").hide();
				$(".mask").remove();
			})
			//是否禁用
			$(".is-stop .sure-btn").click(function() {
				//console.log(data);
				$.ajax({
					type : "post",
					url : "account_manager_to_update_userInfo",
					data : {userId : data,userStatus: 3,doSign : "enableOrdisable"},
					success : function(data) {
						alert("已成功禁用该用户...");
						window.location.href = "all_user_info_page";
					}
				});
				$(".sure-btn").off();
				$(".is-stop").hide();
				$(".mask").remove();
			})
			$(".is-stop .close-btn").click(function() {
				$(".is-stop").hide();
				$(".mask").remove();
			})
			//是否启用
			$(".is-open .sure-btn").click(function() {
				//console.log(data);
				$.ajax({
					type : "post",
					url : "account_manager_to_update_userInfo",
					data : {userId : data,userStatus: 1,doSign : "enableOrdisable"},
					success : function(data) {
						alert("启用成功...");
						window.location.href = "all_user_info_page";
					}
				});
				$(".sure-btn").off();
				$(".is-open").hide();
				$(".mask").remove();
			})
			$(".is-open .close-btn").click(function() {
				$(".is-open").hide();
				$(".mask").remove();
			})
			//是否删除
			$(".is-cancel .sure-btn").click(function() {
				//console.log(data);
				$.ajax({
					type : "post",
					url : "account_manager_to_update_userInfo",
					data : {userId : data,userStatus: 2,doSign : "delete"},
					success : function(data) {
						alert("成功删除此用户...");
						window.location.href = "all_user_info_page";
					}
				});
				$(".sure-btn").off();
				$(".is-cancel").hide();
				$(".mask").remove();
			})
			$(".is-cancel .close-btn").click(function() {
				$(".is-cancel").hide();
				$(".mask").remove();
			})
		}

		function fillUserInfo(data) {
			var s = "<tr><td>账号</td><td>名称</td><td>角色</td><td>创建时间</td><td>状态</td><td>所属上级</td><td>操作</td></tr>";
			$.each(data, function(v, o) { /*o为json的数据（后台传过来的）*/
				s += '<tr><td>' + o.phone + '</td>';
				s += '<td>' + o.userCaption + '</td>';
				if(o.roleId == 1){
				s += '<td>中介经纪人</td>';
				}
				if(o.roleId == 2){
				s += '<td>店长</td>';
				}
				if(o.roleId == 3){
				s += '<td>置业顾问</td>';
				}
				if(o.roleId == 4){
				s += '<td>案场助理</td>';
				}
				if(o.roleId == 5){
				s += '<td>超级管理员</td>';
				}
				if(o.roleId == 6){
				s += '<td>合伙人</td>';
				}
				s += '<td>' + o.createTime + '</td>';
				if(o.userStatus == 1){
				s += '<td>启用</td>';
				}
				if(o.userStatus == 3){
				s += '<td>禁用</td>';
				}
				s += '<td>' + o.contactPerson + '</td>';
				if(o.roleId != 5){
					s += '<td><a href="to_edit_user_page?userId='+o.userId+'&roleId='+o.roleId+'" class="edit-pen"><img src="static/images/edit.png" alt="" title="编辑" /></a>';
				}else{
					s +='<td>';
				}
				s += '<span class="reset-pop" data-value="'+o.userId+'"><img src="static/images/rest.png" alt="" title="密码重置"/></span>';
				if(o.roleId != 5){
					if(o.userStatus == 1){
						s += '<span class="stop-pop" data-value="'+o.userId+'"><img src="static/images/disable.png" alt="" title="禁用" /></span>';
					}
					if(o.userStatus == 3){
						s += '<span class="open-pop" data-value="'+o.userId+'"><img src="static/images/eable.png" alt="" title="启用" /></span>';
					}
					s += '<span class="cancel-pop" data-value="'+o.userId+'"><img src="static/images/delete.png" alt="" title="删除" /></span></td></tr>';
				}else{
					s += '</td></tr>';
				}
			});

			if (data.length > 0) {
				$("#system_user_info").html(s); /*当服务器有数据传送过来,将所有的元素都添加到id为systemCountInfo中*/
			} else {
				$("#system_user_info").html("<br/><span style='width:10%;height:30px;display:block;margin:0 auto;'>暂无数据</span>");
			}
		}
		
		