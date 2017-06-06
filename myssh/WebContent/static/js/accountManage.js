$(document).ready(function(){
	getAccountsManagerList();
});

function formWin(){
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
}

/*function restPass(obj){
	popBox(obj);
    $(".is-reset").show();
}*/

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
				window.location.href = "to_account_manager_page";
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
				window.location.href = "to_account_manager_page";
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
				window.location.href = "to_account_manager_page";
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
				window.location.href = "to_account_manager_page";
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


function getAccountsManagerList() {
	$.ajax({
		type : "post",
		url : "accountsNum_agent_info_list",
		data : {selectStatus : $("#selectStyleId").val()},
		success : function(data) {
			data = eval("(" + data + ")");
			setAccountsManagerInfo(data.root);
			formWin();
		}
	});
}

function setAccountsManagerInfo(data) {
	//console.log(data);
	var s = "<tr><th>账户名称</th><th>姓名</th><th>电话</th><th>身份证号码</th><th>权限</th><th>添加时间</th><th>状态</th><th>操作</th></tr>";
	$.each(data,function(v, o) {
		s += '<tr><td>' + o.phone+ '</td>';
		s += '<td>' + o.userCaption + '</td>';
		s += '<td>' + o.phone + '</td>';
		s += '<td>' + o.idCard + '</td>';
		s += '<td>' + o.roleName + '</td>';
		s += '<td>' + o.createTime + '</td>';
		s += '<td>' + o.userStatus + '</td>';
		if($("#hidUserId").val()!=o.userId){
			s += '<td><a href="to_edit_account_page?userId='+o.userId+'" class="edit-pen"><img src="static/images/edit.png" alt="" title="编辑" /></a>';
		}
		if($("#hidUserId").val()==o.userId){
			s += '<td>';
		}
		s += '<span class="reset-pop" data-value="'+o.userId+'"><img src="static/images/rest.png" alt="" title="密码重置"/></span>';
		if(o.userStatusNum==1 && $("#hidUserId").val()!=o.userId){
			s += '<span class="stop-pop" data-value="'+o.userId+'"><img src="static/images/disable.png" alt="" title="禁用" /></span>';
		}
		if(o.userStatusNum==3){
			s += '<span class="open-pop" data-value="'+o.userId+'"><img src="static/images/eable.png" alt="" title="启用" /></span>';
		}
		if($("#hidUserId").val()!=o.userId){
			s += '<span class="cancel-pop" data-value="'+o.userId+'"><img src="static/images/delete.png" alt="" title="删除" /></span></td></tr>';
		}
	});

	if (data.length > 0) {
		$("#accountManagerList").html(s);
	} else {
		$("#accountManagerList").html("<br/><span style='width:10%;height:30px;display:block;margin:0 auto;'>暂无数据</span>");
	}
}

function getSelectList(){
	//console.log($("#selectStyleId").val());
	getAccountsManagerList();
}