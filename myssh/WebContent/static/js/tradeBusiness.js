/* 
* @Author: Marte
* @Date:   2017-02-07 13:01:02
* @Last Modified by:   Marte
* @Last Modified time: 2017-02-08 11:05:07
*/

$(document).ready(function(){
    $(".btn-all button").click(function(){
        var amount = $(this).index();
        if(amount==0){
        	waitCheckApply();
        }else if(amount==1){
        	notEnterGetBargain();
        }else if(amount==2){
        	notEnterSign();
        }
        $(".btn-single").eq(amount).show().siblings(".btn-single").hide();
    })
    $(".span-one").click(function(){
        var numOne = $(this).index();
        if(numOne==0){
        	waitCheckApply();
        }else if(numOne==1){
        	alreadyCheckApply();
        }else if(numOne==2){
        	alreadyRefuseApply();
        }else if(numOne==3){
        	outTimeNotPlayMoneyApply();
        }else if(numOne==4){
        	backOutApply();
        }
        $(".table-one").eq(numOne).show().siblings().hide();
    })
    $(".span-two").click(function(){
        var numTwo = $(this).index();
        if(numTwo==0){
        	notEnterGetBargain();
        }else if(numTwo==1){
        	alreadyEnterGetBargain();
        }
        $(".table-two").eq(numTwo).show().siblings().hide();
    })
    $(".span-three").click(function(){
        var numThree = $(this).index();
        if(numThree==0){
        	notEnterSign();
        }else if(numThree==1){
        	alreadyEnterSign();
        }
        $(".table-three").eq(numThree).show().siblings().hide();
    })
    $(".look-over").click(function(){
        popBoxgg()
        $(".look-reject").show();
    })
    $(".btn-sure").click(function(){
        popBoxgg();
        $(".payment").show();
    })
    $(".det").click(function(){
    	location.reload();
    })
    //进入页面初始化页面显示数据
    waitCheckApply();
});
function popBoxgg(){
    var sWidth=document.body.scrollWidth;
    var sHeight=document.body.scrollHeight; 
    //获取页面的可视区域高度和宽度
    var wHeight=document.documentElement.clientHeight;
    var oMask=document.createElement("div");
        oMask.className="mask";
        oMask.style.height=sHeight+"px";
        oMask.style.width=sWidth+"px";
        document.body.appendChild(oMask);
        $(".mask").click(function(){
          $(".look-reject").hide();
           $(".payment").hide();
           
           $(".mask").remove();
        	
    })
   
        
}

//购买申请 - 待审核的申请
function waitCheckApply(){
	$.ajax({
		type : "post",
		url : "buy_apply_waitCheck_list",
		data : {doSign:"waitCheck"},
		success : function(data) {
			data=eval("("+data+")");
			setWaitCheckList(data.root);
		}
	});
}

function setWaitCheckList(data){
	//console.log("待审核的申请");
	var s = "<tr><th>房号</th><th>户型</th><th>建筑面积</th><th>使用面积</th><th>列表价</th><th>底价</th><th>中介供货价</th><th>申请数量</th><th>操作</th></tr>";
	$.each(data,function(v,o){
			s+='<tr><td>'+o.houseNo+'</td>';
			s+='<td>'+o.homeTypeName+'</td>';
			s+='<td>'+o.buildArea+'</td>';
			s+='<td>'+o.usefulArea+'</td>';
			s+='<td>'+o.listPrice+'</td>';
			s+='<td>'+o.minimumPrice+'</td>';
			s+='<td>'+o.shopPrice+'</td>';
			s+='<td>'+o.applyThisHouseCounts+'</td>';
			s+='<td><a href="to_see_buy_apply?houseNum='+o.houseNum+'" style="color:#0c95db;">查看申请列表</a></td></tr>';
	});

	if(data.length>0){
		$("#t_waitCheck").html(s);
	}else{
		$("#t_waitCheck").html("<br/><span style='width:10%;height:30px;display:block;margin:0 auto;'>暂无数据0</span>");
	}
}

//购买申请 - 已审核的申请
function alreadyCheckApply(){
	$.ajax({
		type : "post",
		url : "buy_apply_alreadyCheck_list",
		data : {doSign:"alreadyCheck"},
		success : function(data) {
			data=eval("("+data+")");
			setAlreadyCheckList(data.root);
		}
	});
}

function setAlreadyCheckList(data){
	console.log("已审核的申请");
	var s = "<tr><th>房号</th><th>房型</th><th>建筑面积</th><th>使用面积</th><th>列表价</th>" +
			"<th>底价</th><th>中介供货价</th><th>来源</th><th>申请人</th><th>申请时间</th>" +
			"<th>审批人</th><th>剩余打款时间(小时)</th></tr>";
	$.each(data,function(v,o){
			s+='<tr><td>'+o.houseNo+'</td>';
			s+='<td>'+o.homeTypeName+'</td>';
			s+='<td>'+o.buildArea+'</td>';
			s+='<td>'+o.usefulArea+'</td>';
			s+='<td>'+o.listPrice+'</td>';
			s+='<td>'+o.minimumPrice+'</td>';
			s+='<td>'+o.shopPrice+'</td>';
			s+='<td>'+o.source+'</td>';
			s+='<td>'+o.applyMan+'</td>';
			s+='<td>'+o.applyTime+'</td>';
			s+='<td>'+o.checkMan+'</td>';
			s+='<td>'+o.surplusPalyMoneyTime+'</td></tr>';
	});

	if(data.length>0){
		$("#t_alreadyCheck").html(s);
	}else{
		$("#t_alreadyCheck").html("<br/><span style='width:10%;height:30px;display:block;margin:0 auto;'>暂无数据1</span>");
	}
}

//购买申请 - 已拒绝的申请
function alreadyRefuseApply(){
	$.ajax({
		type : "post",
		url : "buy_apply_alreadyRefuse_list",
		data : {doSign:"alreadyRefuse"},
		success : function(data) {
			data=eval("("+data+")");
			setAlreadyRefuseList(data.root);
		}
	});
}

function setAlreadyRefuseList(data) {
	console.log("已拒绝的申请");
	var s = "<tr><th>房号</th><th>房型</th><th>建筑面积</th><th>使用面积</th>" +
			"<th>列表价</th><th>底价</th><th>中介供货价</th><th>来源</th>" +
			"<th>申请人</th><th>申请时间</th><th>审批人</th><th>拒绝原因</th></tr>";
	$.each(data, function(v, o) {
		s += '<tr><td>'+o.houseNo+'</td>';
		s += '<td>'+o.homeTypeName+'</td>';
		s += '<td>'+o.buildArea+'</td>';
		s += '<td>'+o.usefulArea+'</td>';
		s += '<td>'+o.listPrice+'</td>';
		s += '<td>'+o.minimumPrice+'</td>';
		s += '<td>'+o.shopPrice+'</td>';
		s += '<td>'+o.source+'</td>';
		s += '<td>'+o.applyMan+'</td>';
		s += '<td>'+o.applyTime+'</td>';
		s += '<td>'+o.checkMan+'</td>';
		s += '<td>'+o.refuseReason+'</td>';
		//s += '<td><span class="look-over">查看</span></td></tr>';
	});
	if (data.length > 0) {
		$("#t_alreadyRefuse").html(s);
	} else {
		$("#t_alreadyRefuse").html("<br/><span style='width:10%;height:30px;display:block;margin:0 auto;'>暂无数据2</span>");
	}
}

//购买申请 - 超期未打款的申请
function outTimeNotPlayMoneyApply(){
	$.ajax({
		type : "post",
		url : "buy_apply_outTimeNotPlayMoney_list",
		data : {doSign:"outTimeNotPlayMoney"},
		success : function(data) {
			data=eval("("+data+")");
			setOutTimeNotPlayMoneyList(data.root);
		}
	});
}


function setOutTimeNotPlayMoneyList(data) {
	console.log("超期未打款的申请");
	var s = "<tr><th>房号</th><th>房型</th><th>建筑面积</th><th>使用面积</th>"
			+ "<th>列表价</th><th>底价</th><th>中介供货价</th><th>来源</th>"
			+ "<th>申请人</th><th>申请时间</th><th>审批人</th><th>超期时间(小时)</th></tr>";
	$.each(data, function(v, o) {
		s += '<tr><td>'+o.houseNo+'</td>';
		s += '<td>'+o.homeTypeName+'</td>';
		s += '<td>'+o.buildArea+'</td>';
		s += '<td>'+o.usefulArea+'</td>';
		s += '<td>'+o.listPrice+'</td>';
		s += '<td>'+o.minimumPrice+'</td>';
		s += '<td>'+o.shopPrice+'</td>';
		s += '<td>'+o.source+'</td>';
		s += '<td>'+o.applyMan+'</td>';
		s += '<td>'+o.applyTime+'</td>';
		s += '<td>'+o.checkMan+'</td>';
		s += '<td>'+o.palyMoneyOutTime+'</td></tr>';
	});

	if (data.length > 0) {
		$("#t_outTimeNotPlayMoney").html(s);
	} else {
		$("#t_outTimeNotPlayMoney").html("<br/><span style='width:10%;height:30px;display:block;margin:0 auto;'>暂无数据3</span>");
	}
}

//撤销的申请
function backOutApply(){
	$.ajax({
		type : "post",
		url : "buy_apply_backOutApply_list",
		data : {doSign:"backOutApply"},
		success : function(data) {
			data=eval("("+data+")");
			setBackOutApplyList(data.root);
		}
	});
}
function setBackOutApplyList(data){
	console.log("已撤销的申请");
	var s = "<tr><th>房号</th><th>房型</th><th>建筑面积</th><th>使用面积</th>" +
			"<th>列表价</th><th>底价</th><th>中介供货价</th><th>来源</th>" +
			"<th>申请人</th><th>撤单时间</th><th>审批人</th><th>撤单原因</th></tr>";
	$.each(data, function(v, o) {
		s += '<tr><td>'+o.houseNo+'</td>';
		s += '<td>'+o.homeTypeName+'</td>';
		s += '<td>'+o.buildArea+'</td>';
		s += '<td>'+o.usefulArea+'</td>';
		s += '<td>'+o.listPrice+'</td>';
		s += '<td>'+o.minimumPrice+'</td>';
		s += '<td>'+o.shopPrice+'</td>';
		s += '<td>'+o.source+'</td>';
		s += '<td>'+o.applyMan+'</td>';
		s += '<td>'+o.revokeTime+'</td>';
		s += '<td>'+o.checkMan+'</td>';
		s += '<td>'+o.killTheOrderReason+'</td>';
		//s += '<td><span class="look-over">查看</span></td></tr>';
	});
	if (data.length > 0) {
		$("#t_backout").html(s);
	} else {
		$("#t_backout").html("<br/><span style='width:10%;height:30px;display:block;margin:0 auto;'>暂无数据2</span>");
	}
}

//定金到款 - 未确认到款
function notEnterGetBargain(){
	$.ajax({
		type : "post",
		url : "trade_business_for_get_bargain_list",
		data : {doSign:"notEnter"},
		success : function(data) {
			data=eval("("+data+")");
			setNotEnterGetBargainList(data.root);
		}
	});
}

function setNotEnterGetBargainList(data) {
	console.log(data);
	var s = "<tr><th>房号</th><th>户型</th><th>建筑面积</th><th>使用面积</th>"
			+ "<th>列表价</th><th>底价</th><th>中介供货价</th><th>来源</th>"
			+ "<th>申请人</th><th>申请时间</th><th>操作</th></tr>";
	$.each(data, function(v, o) {
		s += '<tr><td>'+o.houseNo+'</td>';
		s += '<td>'+o.homeTypeName+'</td>';
		s += '<td>'+o.buildArea+'</td>';
		s += '<td>'+o.usefulArea+'</td>';
		s += '<td>'+o.listPrice+'</td>';
		s += '<td>'+o.minimumPrice+'</td>';
		s += '<td>'+o.shopPrice+'</td>';
		s += '<td>'+o.source+'</td>';
		s += '<td>'+o.applyMan+'</td>';
		s += '<td>'+o.applyTime+'</td>';
		s += '<td><a href="javascript:;" class="cert-pic" onclick=showPayMoneyVoucher("'+o.credentialsPhoto+'")>查看汇款凭证</a>';
		s += '<button class="btn-sure" onclick="enterPayMoney('+o.recordNo+')">确认到款</button></td></tr>';
	});

	if (data.length > 0) {
		$("#t_notEnterGetBargain").html(s);
	} else {
		$("#t_notEnterGetBargain").html("<br/><span style='width:10%;height:30px;display:block;margin:0 auto;'>暂无数据0</span>");
	}
}

function showPayMoneyVoucher(data){
	if(data!=null && data!=""){
		
		popBoxgg();
		picPop(data);
	}else{
		alert("暂无信息");
	}
}
//图片弹出
function picPop(data){
	$("body").append("<div style='margin:auto;position:fixed;top:0;bottom:0;left:0;right:0;width:800px;height:600px;z-index:100;' class='picDiv'><img src="+data+" style='width:600px;height:400px;margin:auto;position:fixed;top:0;bottom:0;left:0;right:0;'></img><img src='static/images/delete.png' style='position:absolute;right:0;top:0;z-index:1000;' onclick='det()'></img></div>")
}
function det(){
	$(".mask").remove();
	$(".picDiv").remove();
}
function enterPayMoney(data){
	$.ajax({
		type : "post",
		url : "trade_business_for_enter_bargain",
		data : {recordNo:data},
		success : function(data) {
			notEnterGetBargain();
			alert("确认到款成功...");
		}
	});
}

//定金到款 - 已确认的到款
function alreadyEnterGetBargain(){
	$.ajax({
		type : "post",
		url : "trade_business_for_get_bargain_list",
		data : {doSign:"alreadyEnter"},
		success : function(data) {
			data=eval("("+data+")");
			setalreadyEnterGetBargainList(data.root);
		}
	});
}

function setalreadyEnterGetBargainList(data) {
	console.log("已确认的到款");
	var s = "<tr><th>房号</th><th>户型</th><th>建筑面积</th><th>使用面积</th>"
			+ "<th>列表价</th><th>底价</th><th>中介供货价</th><th>来源</th>"
			+ "<th>申请人</th><th>申请时间</th><th>审核时间</th><th>审核人</th></tr>";
	$.each(data, function(v, o) {
		s += '<tr><td>'+o.houseNo+'</td>';
		s += '<td>'+o.homeTypeName+'</td>';
		s += '<td>'+o.buildArea+'</td>';
		s += '<td>'+o.usefulArea+'</td>';
		s += '<td>'+o.listPrice+'</td>';
		s += '<td>'+o.minimumPrice+'</td>';
		s += '<td>'+o.shopPrice+'</td>';
		s += '<td>'+o.source+'</td>';
		s += '<td>'+o.applyMan+'</td>';
		s += '<td>'+o.applyTime+'</td>';
		s += '<td>'+o.remitConfirmTime+'</td>';
		s += '<td>'+o.checkMan+'</td></tr>';
	});

	if (data.length > 0) {
		$("#t_alreadyEnterGetBargain").html(s);
	} else {
		$("#t_alreadyEnterGetBargain").html("<br/><span style='width:10%;height:30px;display:block;margin:0 auto;'>暂无数据1</span>");
	}
}

//签约确认 - 未确认的签约
function notEnterSign(){
	$.ajax({
		type : "post",
		url : "trade_business_for_sign_list",
		data : {doSign:"notEnter"},
		success : function(data) {
			data=eval("("+data+")");
			setNotEnterSignList(data.root);
		}
	});
}

function setNotEnterSignList(data) {
	//console.log("未确认的签约");
	var s = "<tr><th>房号</th><th>户型</th><th>建筑面积</th><th>使用面积</th><th>列表价</th>" +
			"<th>底价</th><th>中介供货价</th><th>来源</th><th>申请人</th><th>申请时间</th><th>操作</th></tr>";
	$.each(data, function(v, o) {
		s += '<tr><td>'+o.houseNo+'</td>';
		s += '<td>'+o.homeTypeName+'</td>';
		s += '<td>'+o.buildArea+'</td>';
		s += '<td>'+o.usefulArea+'</td>';
		s += '<td>'+o.listPrice+'</td>';
		s += '<td>'+o.minimumPrice+'</td>';
		s += '<td>'+o.shopPrice+'</td>';
		s += '<td>'+o.source+'</td>';
		s += '<td>'+o.applyMan+'</td>';
		s += '<td>'+o.applyTime+'</td>';
		s += '<td><a href="javascript:;" class="cert-pic" onclick=enterSign('+o.recordNo+')>确认签约</a></td></tr>';
	});

	if (data.length > 0) {
		$("#t_notEnterSign").html(s);
	} else {
		$("#t_notEnterSign").html("<br/><span style='width:10%;height:30px;display:block;margin:0 auto;'>暂无数据0</span>");
	}
}

function enterSign(data){
	$.ajax({
		type : "post",
		url : "trade_business_for_enter_sign",
		data : {recordNo:data},
		success : function(data) {
			notEnterSign();
			alert("确认签约成功...");
		}
	});
}

//签约确认 - 已确认的签约
function alreadyEnterSign(){
	$.ajax({
		type : "post",
		url : "trade_business_for_sign_list",
		data : {doSign:"alreadyEnter"},
		success : function(data) {
			data=eval("("+data+")");
			setalreadyEnterSignList(data.root);
		}
	});
}

function setalreadyEnterSignList(data) {
	//console.log("已确认的签约");
	var s = "<tr><th>房号</th><th>户型</th><th>建筑面积</th><th>使用面积</th><th>列表价</th>"
			+ "<th>底价</th><th>中介供货价</th><th>来源</th><th>申请人</th><th>申请时间</th><th>确认时间</th><td>确认人</td></tr>";
	$.each(data, function(v, o) {
		s += '<tr><td>'+o.houseNo+'</td>';
		s += '<td>'+o.homeTypeName+'</td>';
		s += '<td>'+o.buildArea+'</td>';
		s += '<td>'+o.usefulArea+'</td>';
		s += '<td>'+o.listPrice+'</td>';
		s += '<td>'+o.minimumPrice+'</td>';
		s += '<td>'+o.shopPrice+'</td>';
		s += '<td>'+o.source+'</td>';
		s += '<td>'+o.applyMan+'</td>';
		s += '<td>'+o.applyTime+'</td>';
		s += '<td>'+o.signEnterTime+'</td>';
		s += '<td>'+o.signCheckMan+'</td></tr>';
	});

	if (data.length > 0) {
		$("#t_alreadyEnterSign").html(s);
	} else {
		$("#t_alreadyEnterSign").html("<br/><span style='width:10%;height:30px;display:block;margin:0 auto;'>暂无数据1</span>");
	}
}


