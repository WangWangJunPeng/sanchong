/* 
* @Author: Marte
* @Date:   2017-02-13 09:59:56
* @Last Modified by:   Marte
* @Last Modified time: 2017-02-13 10:42:36
*/
$(document).ready(function(){
        // 切换
        $(".switch1").click(function(){
            $(this).addClass('default-border').siblings().removeClass('default-border');
            var num = $(this).index();
            //点击时，调用相应的后台数据
            getTodayShopList(num);
            $(".tab-pane").eq(num).show().siblings().hide();
        })
        
        //初始化页面时，调用后台方法 -今日门店counts
        getTodayShopCounts();
        //初始化页面时，调用后台方法 -今日门店list
        getTodayShopList(0);
});

function getTodayShopCounts(){
	$.ajax({
		type:"post",
		url:"shoper_today_shop_counts",
		success:function(data){
			data=eval("("+data+")");
			setTodayShopCounts(data.data);
		}
	});
}

function setTodayShopCounts(data){
	$("#enterBuy").text(data.todayEnterBuyCount);
	$("#record").text(data.todayRecordCount);
	$("#guide").text(data.todayGuidCount);
	$("#willExpired").text(data.willExpiredCount);
}

function  getTodayShopList(num){
	$.ajax({
		type:"post",
		url:"shoper_today_shop_list",
		data:{num:num},
		success:function(data){
			data=eval("("+data+")");
			setTodayShopList(data.root,num);
		}
	});
}

function setTodayShopList(data,num){
	if(num==0){
		var s = "<thead><tr><th>姓名</th><th>项目名称</th><th>当前状态</th><th>认购时间</th></tr></thead>";
		$.each(data,function(v,o){
				//console.log(o);
				s+='<tbody><tr><td>'+o.cusName+'</td>';
				s+='<td>'+o.proName+'</td>';
				s+='<td>'+o.currStatus+'</td>';
				s+='<td>'+o.appTime+'</td></tr></tbody>';
		});
	
		if(data.length>0){
			$("#t_enterBuy").html(s);
		}else{
			$("#t_enterBuy").html("<br/><span style='width:7%;height:30px;display:block;margin:0 auto;    border: 0px;'>暂无数据0</span>");
		}
	}else if(num==1){
		var s = "<thead><tr><th>姓名</th><th>项目名称</th><th>备案经纪人</th><th>备案时间</th></tr></thead>";
		$.each(data,function(v,o){
			s+='<tbody><tr><td>'+o.cusName+'</td>';
			s+='<td>'+o.proName+'</td>';
			s+='<td>'+o.mediName+'</td>';
			s+='<td>'+o.appTime+'</td></tr></tbody>';
		});
	
		if(data.length>0){
			$("#t_record").html(s);
		}else{
			$("#t_record").html("<br/><span style='width:7%;height:30px;display:block;margin:0 auto;    border: 0px;'>暂无数据1</span>");
		}
	}else if(num==2){
		var s = "<thead><tr><th>姓名</th><th>项目名称</th><th>备案经纪人</th><th>到访时间</th></tr></thead>";
		$.each(data,function(v,o){
			s+='<tbody><tr><td>'+o.cusName+'</td>';
			s+='<td>'+o.proName+'</td>';
			s+='<td>'+o.mediName+'</td>';
			s+='<td>'+o.appTime+'</td></tr></tbody>';
		});
	
		if(data.length>0){
			$("#t_guid").html(s);
		}else{
			$("#t_guid").html("<br/><span style='width:10%;height:30px;display:block;margin:0 auto;    border: 0px;'>暂无数据2</span>");
		}
	}else if(num==3){
		var s = "<thead><tr><th>姓名</th><th>项目名称</th><th>备案经纪人</th><th>到期时间</th></tr></thead>";
		$.each(data,function(v,o){
			s+='<tbody><tr><td>'+o.cusName+'</td>';
			s+='<td>'+o.proName+'</td>';
			s+='<td>'+o.mediName+'</td>';
			s+='<td>'+o.appTime+'</td></tr></tbody>';
		});
	
		if(data.length>0){
			$("#t_willexpire").html(s);
		}else{
			$("#t_willexpire").html("<br/><span style='width:10%;height:30px;display:block;margin:0 auto;    border: 0px;'>暂无数据3</span>");
		}
	}
}



