/* 
* @Author: Marte
* @Date:   2017-02-23 14:42:00
* @Last Modified by:   Marte
* @Last Modified time: 2017-02-28 15:32:27
*/

$(document).ready(function(){
	getSureBillInfo();
});
//平台结款
function systemPay(obj){
	$("#sureHidrecordNo").val($(obj).data("value"));
	$("#enterSign").val("systemPay");
    popBox();
    $(".sure-pop").show();
}

//平台取消结款
function systemCancelPay(obj){
	$("#cancelHidrecordNo").val($(obj).data("value"));
	$("#cancelSign").val("systemCancel");
    popBox();
    $(".cancel-pop").show();
}

//中介结款
function shopPay(obj){
	$("#sureHidrecordNo").val($(obj).data("value"));
	$("#enterSign").val("mediPay");
    popBox();
    $(".sure-pop").show();
}

//中介取消结款
function shopCancelPay(obj){
	$("#cancelHidrecordNo").val($(obj).data("value"));
	$("#cancelSign").val("mediCancel");
    popBox();
    $(".cancel-pop").show();
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
        $(".sub-pop").click(function(){
            if($("#reasonOne").val() != '') {
                //提交并关闭弹框
                $(".cancel-pop").hide();
                $(".mask").remove();
            }else{
                alert("请您填写取消原因");
                return false;
            }
        })
        
// 确定
/*        $(".sure-openPop").click(function(){
            $(".sure-pop").hide();
             $(".mask").remove();
        })

         $(".sure-openClose").click(function(){
                 $(".sure-pop").hide();
                 $(".mask").remove();
        })*/
                
      
}

//确认结款关闭弹框
function payClosePop(){
	$("#reason").val('');
    $(".sure-pop").hide();           
    $(".mask").remove();
}


//取消结款关闭弹框
function cancelPayClosePop(){
    $("#reasonOne").val('');
    $(".cancel-pop").hide();           
    $(".mask").remove();
}

//获取对账单list
function getSureBillInfo() {
	$.ajax({
		type : "post",
		url : "agent_check_bill_list",
		data : {
			startTime : $("#start").val(),
			endTime : $("#end").val(),
		},
		success : function(data) {
			data = eval("(" + data + ")");
			setSureBillsInfo(data.root);
		}
	});
}

function setSureBillsInfo(data){
	
	var s = "<tr><th>中介佣金金额</th><th>平台佣金金额</th><th>佣金来源</th><th>带看时间</th>" +
			"<th>签约时间</th><th>中介结款状态</th><th>平台结款状态</th><th>结款截止日期</th><th>操作</th></tr>";
	$.each(data,function(v,o){
			s+='<tr><td>'+o.mediMoney+'</td>';
			s+='<td>'+o.systemMoney+'</td>';
			s+='<td>'+o.moneyComeFrom+'</td>';
			s+='<td>'+o.daiKanTime+'</td>';
			s+='<td>'+o.signTime+'</td>';
			s+='<td>'+o.mediPayMoneyStatus+'</td>';
			s+='<td>'+o.systemPayMoneyStatus+'</td>';
			s+=' <td>'+o.payMoneyEndTime+'</td>';
			//平台结款操作按钮
			if(o.systemPayMoneytype==0){
				s+='<td><p><button class="pay-supply flatform-pay" data-value='+o.recordNo+' onclick=systemPay(this)>平台结款</button></p>'
			}else if(o.systemPayMoneytype==1){
				s+='<td><p><button class="cancel-pay flatform-cancelPay" data-value='+o.recordNo+' onclick=systemCancelPay(this)>平台取消结款</button></p>';
			}
			//中介结款操作按钮
			if(o.mediPayMoneytype==0){
				s+='<p><button class="pay-supply angency-pay" data-value='+o.recordNo+' onclick=shopPay(this)>中介结款</button></p></td></tr>';
			}else if(o.mediPayMoneytype==1){
				s+= '<p><button class="cancel-pay angency-cancelPay" data-value='+o.recordNo+' onclick=shopCancelPay(this)>中介取消结款</button></p></td></tr>';
            
			}
			
	});

	if(data.length>0){
		$("#billListInfo").html(s);
	}else{
		$("#billListInfo").html("<br/><span style='width:10%;height:30px;display:block;margin:0 auto;'>暂无数据</span>");
	}
}

function searchBills(){
	getSureBillInfo();
}










