/* 
* @Author: Marte
* @Date:   2017-01-21 11:44:44
* @Last Modified by:   Marte
* @Last Modified time: 2017-02-08 13:20:03
*/

$(document).ready(function(){
    getSeeBuyApplyDate();
});
// 审核框
function auditPop(recordNo){
	$("#checkRecordNo").val(recordNo);
    popBox();
    $(".audit-pop").show();
}
// 拒绝框
function rejectPop(recordNo){
	$("#reRecordNo").val(recordNo);
    popBox();
    $(".reject-pop").show();
}
function yin(){
	$(".audit-pop").hide();
    $(".reject-pop").hide();
   $(".mask").remove();
}
function popBox(){
    var sWidth=document.body.scrollWidth;
    var sHeight=document.body.scrollHeight; 
    //获取页面的可视区域高度和宽度
    var wHeight=document.documentElement.clientHeight;
    var oMask=document.createElement("div");
        oMask.className="mask";
        oMask.style.height=sHeight+"px";
        oMask.style.width=sWidth+"px";
        document.body.appendChild(oMask);
       
}

//认购审核方法
function checkBuyApply() {
	// ajax 后台调用
	$.ajax({
		type : "post",
		url : "agree_check_buy_apply_date",
		ansy: false,
		data : {
			recordNo : $("#checkRecordNo").val(),checkReson:$("#checkReson").val()
		},
		success : function(data) {
			alert("审核已通过，审核信息已通过短信发送给申请人...");
			window.location.href="to_trade_business_page";
		}
	});
	$(".audit-pop").hide();
	$(".mask").remove();
}

function getSeeBuyApplyDate(){
	$.ajax({
		type:"post",
		url:"get_see_buy_apply_date",
		data:{houseNum:$("#houseNum").val()},
		success:function(data){
			data=eval("("+data+")");
			setSeeBuyApplyDate(data.data);
		}
	});
}

//给房源信息表格和购买申请列表赋值
function setSeeBuyApplyDate(data){
	if(data.hasOwnProperty("houseInfo")){
		setHouseInfo(data.houseInfo);
	}else{
		
	}
	if(data.hasOwnProperty("buyApplyList")){
		setBuyApplyList(data.buyApplyList);
	}else{
		$("#applyBuyList").html("<br/><span style='width:10%;height:30px;display:block;margin:0 auto;'>暂无数据</span>");
	}
}

function setHouseInfo(data){
	$("#houseNo").text(data.houseNo);
	$("#houseTypeName").text(data.homeTypeName);
	$("#buileArea").text(data.buildArea);
	$("#usefulArea").text(data.usefulArea);
	$("#listPrice").text(data.listPrice);
	$("#miniPrice").text(data.minimumPrice);
	$("#mediPrice").text(data.shopPrice);
}

function setBuyApplyList(data){
	var s = "";
	$.each(data,function(v,o){
		s+= "<table cellpadding='5' id='applyBuyList'>";
			s+= "<tr><th>来源</th><th>申请人</th><th>购买价格</th><th>定金</th><th>支付方式</th><th>结算方式</th><th>订单性质</th><th>操作</th></tr>"
			s+='<tr><td>'+o.source+'</td>';
			s+='<td>'+o.applyMan+'</td>';
			s+=' <td>'+o.buyPrice+'</td>';
			s+=' <td>'+o.dposit+'</td>';
			if(o.payStyle == ""){		//要补充 0线上 1线下
				s+='<td>未填写</td>';
			}else if(o.payStyle == 0){
				s+=' <td>线上</td>';
			}else if(o.payStyle == 1){
				s+=' <td>线下</td>';
			}
			if(o.accountStyle == 0){
				s+=' <td>一次性</td>';
			}else if(o.accountStyle == 1){
				s+=' <td>公积金</td>';
			}else if(o.accountStyle == 2){
				s+=' <td>商贷按揭</td>';
			}else if(o.accountStyle == 3){
				s+=' <td>其他</td>';
			}else{	//要补充 结算方式 0一次性1公积金2商贷按揭3其他
				s+='<td>未知</td>';
			}
			if(o.orderProperty == 0 && o.orderProperty != ""){
				s+='<td>自购</td>';
			}else if(o.orderProperty == 1 && o.orderProperty != ""){
				s+='<td>代购</td>';
			}else{
				s+='<td>未填写</td>';
			}
			if(o.hadConRe=="yes"){
				s+='<td rowspan="3"><button style="background:#999" class="audit"  onclick="auditPop('+o.recordNo+')" disabled="disabled">审核</button>';
			}else{
				s+='<td rowspan="3"><button class="audit"  onclick="auditPop('+o.recordNo+')">审核</button>';
			}
			s+='<button class="reject" onclick="rejectPop('+o.recordNo+')">拒绝</button></td></tr>';
			
			s += "<tr><th colspan='2'>优惠</th>";
			if(o.orderProperty == 1)
				s += "<th>真实购买人</th><th>认购人身份证</th>";
			else
				s += "<th colspan='2'>认购人身份证</th>";
			
			s += "<th>订购人</th><th>联系方式</th><th>申请时间</th></tr>";
			
			s+='<tr><td colspan="2">'+o.benefitInfo+'</td>';
			
			if(o.orderProperty == 1)
				s+=' <td>'+o.rName+'</td><td>'+o.customerIDCard+'</td>';
			else
				s+=' <td colspan="2">'+o.customerIDCard+'</td>';
			s+='<td>'+o.customerName+'</td>';
			s+='<td>'+o.customerPhone+'</td>';
			s+='<td>'+o.applyTime+'</td>';
			//s+='<td></td>'
			s+= "</table>";
			
	});
	if(data.length>0){
		$("#applyList").after(s);
	}else{
		$("#applyBuyList").html("<br/><span style='width:10%;height:30px;display:block;margin:0 auto;'>暂无数据</span>");
	}
	
	
}





