/* 
* @Author: Marte
* @Date:   2017-02-06 14:26:03
* @Last Modified by:   Marte
* @Last Modified time: 2017-02-06 15:22:37
*/

$(document).ready(function(){
    $(".confirm").click(function(){
        $(this).addClass('same-border').siblings().removeClass('same-border');
        var num = $(this).index();
        //getDatas();
        //点击时，获取今日待办list
        getTodayNeedToDoList(num);
        //getTodayProCount();
        // console.log(num)
        $(".table-gather").eq(num).show().siblings().hide();
    });
    //进入页面时获取counts
    getDatas();
    //进入页面是获取list
    getTodayNeedToDoList(0);
    //进入页面时获取今日案场counts
    getTodayProCount();
});

function getDatas(){
	$.ajax({
		type:"post",
		url:"agent_page_today_need_doing",
		success:function(data){
			data=eval("("+data+")");
			getCounts(data.data);
		}
	});
}

function getCounts(data){
	$("#enterBuy").text(data.enterBuyCount);
	$("#getMoney").text(data.getMoneyCount);
	$("#waitSign").text(data.waitSignCount);
	$("#waitCash").text(data.waitCashCount);
}


function getTodayNeedToDoList(num){
	$.ajax({
		type:"post",
		url:"agent_page_list",
		data:{num:num},
		success:function(data){
			data=eval("("+data+")");
			setTodayNeedToDoList(data.root,num);
		}
	});
}

function setTodayNeedToDoList(data,num){
	if(num==0){
		var s = "<tr><th>地区</th><th>项目</th><th>申请人</th><th>申请时间</th><th>申请价格</th><th>操作</th></tr>";
		$.each(data,function(v,o){
				//console.log(o);
				s+='<tr><td>'+o.proCity+'</td>';
				s+='<td>'+o.proName+'</td>';
				s+='<td>'+o.cusName+'</td>';
				s+='<td>'+o.appTime+'</td>';
				s+='<td>'+o.appPrice+'</td>';
				s+=' <td><a href="to_see_buy_apply?houseNum='+o.houseNum+'" style="color:#0c95db;">查看</a></td></tr>';
		});
	
		if(data.length>0){
			$("#t_enterBuy").html(s);
		}else{
			$("#t_enterBuy").html("<br/><span style='width:10%;height:30px;display:block;margin:0 auto;'>暂无数据0</span>");
		}
	}else if(num==3){
		var s = "<tr><th>地区</th><th>项目</th><th>中介人</th><th>签约时间</th><th>成交价格</th><th>分销/带看比</th><th>提成金额</th><th>操作</th></tr>";
		$.each(data,function(v,o){
				//console.log(o);
				s+='<tr><td>'+o.proCity+'</td>';
				s+='<td>'+o.proName+'</td>';
				s+='<td>'+o.mediName+'</td>';
				s+='<td>'+o.contractConfirmTime+'</td>';
				s+=' <td>'+o.dealPrice+'</td>';
				s+=' <td>'+o.commPerc+'%</td>';
				s+=' <td>'+o.amount+'</td>';
				s+=' <td><a href="to_trade_business_page">查看</a></td></tr>';
				//s+='<td><input type="button" class="btn-cancel" value="删除" data-value="'+o.bankId+'"/></td></tr>';
		});
	
		if(data.length>0){
			$("#t_waitCash").html(s);
		}else{
			$("#t_waitCash").html("<br/><span style='width:10%;height:30px;display:block;margin:0 auto;'>暂无数据3</span>");
		}
	}else if(num==1){
		var s = "<tr><th>地区</th><th>项目</th><th>申请人</th><th>打款时间时间</th><th>购买价格</th><th>定金</th><th>操作</th></tr>";
		$.each(data,function(v,o){
				//console.log(o);
				s+='<tr><td>'+o.proCity+'</td>';
				s+='<td>'+o.proName+'</td>';
				s+='<td>'+o.cusName+'</td>';
				s+='<td>'+o.palyTime+'</td>';
				s+=' <td>'+o.palyPrice+'</td>';
				s+=' <td>'+o.earnest+'</td>';
				s+=' <td><a href="to_trade_business_page">查看</a></td></tr>';
				//s+='<td><input type="button" class="btn-cancel" value="删除" data-value="'+o.bankId+'"/></td></tr>';
		});
	
		if(data.length>0){
			$("#t_getMoney").html(s);
		}else{
			$("#t_getMoney").html("<br/><span style='width:10%;height:30px;display:block;margin:0 auto;'>暂无数据1</span>");
		}
	}else if(num==2){
		var s = "<tr><th>地区</th><th>项目</th><th>申请人</th><th>打款时间时间</th><th>购买价格</th><th>定金</th><th>操作</th></tr>";
		$.each(data,function(v,o){
				//console.log(o);
				s+='<tr><td>'+o.proCity+'</td>';
				s+='<td>'+o.proName+'</td>';
				s+='<td>'+o.cusName+'</td>';
				s+='<td>'+o.palyTime+'</td>';
				s+=' <td>'+o.palyPrice+'</td>';
				s+=' <td>'+o.earnest+'</td>';
				s+=' <td><a href="to_trade_business_page">查看</a></td></tr>';
				//s+='<td><input type="button" class="btn-cancel" value="删除" data-value="'+o.bankId+'"/></td></tr>';
		});
	
		if(data.length>0){
			$("#t_waitSign").html(s);
		}else{
			$("#t_waitSign").html("<br/><span style='width:10%;height:30px;display:block;margin:0 auto;'>暂无数据2</span>");
		}
	}
}

function getTodayProCount(){
	$.ajax({
		type:"post",
		url:"agent_today_pro_counts",
		success:function(data){
			data=eval("("+data+")");
			setTodayProCounts(data.data);
		}
	});
}

function setTodayProCounts(data){
	
	$("#todayVisit").text(data.todayVisitCount);
	$("#todayGuide").text(data.todayGuidCount);
	$("#todayRecord").text(data.todayRecordCount);
	$("#todayEnterBuy").text(data.todayEnterBuyCount);
	$("#todaySign").text(data.todaySignCount);
}










