/* 
* @Author: grl
* @Date:   2017-02-04 14:33:41
* @Last Modified by:   grl
* @Last Modified time: 2017-02-04 17:32:24
*/

$(document).ready(function(){
      //ajax请求后台数据
      getProCustomerManagePageInfo();
      //初始化翻页信息
      initpage1();
});

function checkBoxSelect() {
	$("#allChoose").click(function() {
		$(".chooseSingle").prop("checked", this.checked);
	})
	$(".chooseSingle").click(function() {
		var flag = true;
		$(".chooseSingle").each(function() {
			if (!this.checked) {
				flag = false;
			}
			;
		});
		$("#allChoose").prop("checked", flag);
	})
}

function setPopBox(){
	
	getSelectedCustomerInfo();
	
	/*
	 * $("#agNext").next().remove(); getAgentsMenu(); popBox();
	 * $(".set-belong").show();
	 */
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
        $(".mask").click(function(){
           
        	$(".set-belong").hide();
           $(".mask").remove();
    })
        
}
//分页参数设置
var startAllAppoint = 0;
var limitAllAppoint = 5;
var currentPageAllAppoint = 1;
var totalPageAllAppoint = 0;
//ajax请求后台数据
function getProCustomerManagePageInfo(){
	$.ajax({
		type:"post",
		async:false,
		url:"list_pro_customers_info",
		data:{start:startAllAppoint,limit:limitAllAppoint,selectValue:$("#selectValue").val()},
		success:function(data,status){
			data=eval("("+data+")");
			getProCustomesInfo(data.root);
			startAllAppoint = data.currentResult;
			totalPageAllAppoint = data.totalPage;
			checkBoxSelect();
		}
	});
	
}

function initpage1(){
	$("#page1").jqPagination({
		  link_string : '',
		  current_page: currentPageAllAppoint, //设置当前页 默认为1
		  max_page : totalPageAllAppoint, //设置最大页 默认为1
		  page_string : '当前第{current_page}页,共{max_page}页',
		  paged : function(page) {
		  	  startAllAppoint = (page-1)*limitAllAppoint;
		  	  getProCustomerManagePageInfo();
		  }
	});
}

function getProCustomesInfo(data){
	var s = "<tr><th><input type='checkbox' id='allChoose'/></th><th>姓名</th><th>客户归属</th><th>性别</th><th>电话</th><th>已成交</th><th>添加时间</th><th>备注</th></tr>";
	$.each(data,function(v,o){
			//console.log(o);
			s+='<tr><td><input type="checkbox" class="chooseSingle" name="chooseOne" value="'+o.proCusId+'"/></td>';
			s+='<td>'+o.cusName+'</td>';
			s+='<td>'+o.cusOwnerAgent+'</td>';
			s+='<td>'+o.cusSex+'</td>';
			s+='<td>'+o.phone+'</td>';
			s+='<td>'+o.isSign+'</td>';
			s+='<td>'+o.addTime+'</td>';
			s+='<td>'+o.description+'</br></td></tr>';
	});

	if(data.length>0){
		$("#page1").show();
		$("#t_customerInfo").html(s);
	}else{
		$("#page1").hide();
		$("#t_customerInfo").html("<br/><span style='width:10%;height:30px;display:block;margin:0 auto;'>暂无数据</span>");
	}
	
	
}

//获取置业顾问菜单
function getAgentsMenu(){
	$.ajax({
		type:"post",
		url:"grl_get_agents_menu",
		success:function(data){
			data=eval("("+data+")");
			innerInfoAgent(data.root);
		}
	});
}

function innerInfoAgent(data){
	$.each(data,function(rowNum,o){
		$("#agNext").after('<option value="'+o.agId+'">'+o.agName+'</option>');
	});
}

//获取选中客户
function getSelectedCustomerInfo(){
	var sv = [];
	$("input[name='chooseOne']:checked").each(function(){ 
		var tv =$(this).val(); 
		sv.push(tv);
	})
	//console.log(sv);
	if(sv.length>0){
		$("#selectedCustomorId").val(sv);
		$("#agNext").next().remove();
		getAgentsMenu();
		popBox();
	    $(".set-belong").show();
	}else{
		alert("请先选择客户！");
	}
}

//搜索客户
function selectCustomers(){
	getProCustomerManagePageInfo();
	initpage1();
}









