/* 
* @Author: Marte
* @Date:   2017-03-01 10:37:09
* @Last Modified by:   Marte
* @Last Modified time: 2017-03-01 13:56:28
*/



$(document).ready(function(){
    $(".sure-payment").click(function(){
    		$("#sureHidRecordNo").val($(this).data("value"));
            popBox();
            $(".sure-pop").show();
        })
    $(".cancel-payment").click(function(){
    		$("#cancelHidRecordNo").val($(this).data("value"));
            popBox();
            $(".cancel-pop").show();
        })
    $(".btn-single").click(function(){
        var num = $(this).index();
        $("#hidNum").val(num);
        if(num==0){
        	getShoperSureBillsList();
        }else if(num==1){
        	getMediDateRanking();
        }else if(num==2){
        	getProDateRanking();
        }
        $(".bill-single").eq(num).show().siblings().hide();
    })
    getShoperSureBillsList();
    getProjectMenu();
    toPage1();
});
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
        $(".sub-popTwo").click(function(){
            if($("#reasonOne").val() != '') {
                //提交并关闭弹框
                $(".cancel-pop").hide();
                $(".mask").remove();
            }else{
                alert("请您填写确认原因");
                return false;
            }
        })
        $(".close-popTwo").click(function(){
                $("#reasonOne").val('');
                $(".cancel-pop").hide();           
                $(".mask").remove();
        })
// 确定
       $(".sub-popOne").click(function(){
            if($("#reasonOne").val() != '') {
                //提交并关闭弹框
                $(".sure-pop").hide();
                $(".mask").remove();
            }else{
                alert("请您填写取消原因");
                return false;
            }
        })
        $(".close-popOne").click(function(){
                $("#reasonOne").val('');
                $(".sure-pop").hide();           
                $(".mask").remove();
        })  
      
}


//分页参数设置
var startAllAppoint = 0;
var limitAllAppoint = 10;
var currentPageAllAppoint = 1;
var totalPageAllAppoint = 0;

// 获取中介对账单数据
function getShoperSureBillsList() {
	$.ajax({
		type : "post",
		url : "shoper_check_bill_list",
		async : false,
		data : {
			startTime : $("#start").val(),
			endTime : $("#end").val(),
			start : startAllAppoint,
			limit: limitAllAppoint,
			projectId : $("#projectSelect").val()
		},
		success : function(data) {
			data = eval("(" + data + ")");
			setShoperSureBillsInfo(data.root);
			startAllAppoint = data.currentResult;
			totalPageAllAppoint = data.totalPage;
		}
	});
}

//获取案场菜单
function getProjectMenu(){
	$.ajax({
		type : "post",
		url : "get_project_menu",
		async : false,
		dataType:"json",
		success : function(data) {
			//console.log(data);
			setProjectMenu(data);
		}
	});
}

function setShoperSureBillsInfo(data){
	//console.log(data);
	var s = "<tr><th>项目名称</th><th>佣金类型</th><th>佣金金额</th><th>经纪人</th><th>客户姓名</th><th>客户电话</th>" +
			"<th>佣金来源</th><th>带看时间</th><th>签约时间</th><th>结款时间</th><th>确认到款时间</th><th>结款状态</th>" +
			"<th>结款截止日期</th><th>操作</th></tr>";
	$.each(data,function(v, o) {
		s += '<tr><td>'+o.proName+'</td>';
		s += '<td>'+o.MoneyType+'</td>';
		s += '<td>'+o.mediMoney+'</td>';
		s += '<td>'+o.mediName+'</td>';
		s += '<td>'+o.customeName+'</td>';
		s += '<td>'+o.customePhone+'</td>';
		s += '<td>'+o.moneyComeFrom+'</td>';
		s += '<td>'+o.daiKanTime+'</td>';
		s += '<td>'+o.signTime+'</td>';
		s += '<td>'+o.payMoneyTime+'</td>';
		s += '<td>'+o.receiveMoneyTime+'</td>';
		s += '<td>'+o.mediPayMoneyStatus+'</td>';
		s += '<td>'+o.payMoneyEndTime+'</td>';
		// 门店到款操作按钮
		if (o.mediPayMoneytype== 1) {
			s += '<td><button class="sure-payment" data-value='+o.recordNo+'>确认到款</button></td></tr>'
		} else if (o.mediPayMoneytype == 2) {
			s += '<td><button class="cancel-payment" data-value='+o.recordNo+'>取消到款</button></td></tr>';
		}
	});

	if (data.length > 0) {
		$("#shoperBillListInfo").html(s);
	} else {
		$("#shoperBillListInfo").html("<br/><span style='width:10%;height:30px;display:block;margin:0 auto;'>暂无数据0</span>");
	}
}

function setProjectMenu(data){
	$.each(data,function(v, o) {
		$("#projectSelect").append('<option value="'+o.proId+'">'+o.proName+'</option>');
	});
}

//获取中介对账单数据  经纪人数据排行
function getMediDateRanking() {
	$.ajax({
		type : "post",
		url : "shoper_medi_data_order_list",
		async : false,
		data : {
			startTime : $("#start").val(),
			endTime : $("#end").val(),
			start : startAllAppoint,
			limit:limitAllAppoint,
			projectId : $("#projectSelect").val()
		},
		success : function(data) {
			data = eval("(" + data + ")");
			setMediDateRankingInfo(data.root);
			startAllAppoint = data.currentResult;
			totalPageAllAppoint = data.totalPage;
		}
	});
}

function setMediDateRankingInfo(data){
	console.log(data);
	var s = "<tr><th>经纪人</th><th>备案数</th><th>确客数</th><th>备案未到访数</th><th>备案已逾期</th>" +
			"<th>认购申请数</th><th>认购完成数</th><th>签约完成数</th>" + "<th>结佣数</th><th>到款</th></tr>";
	$.each(data,function(v, o) {
		s += '<tr><td>'+o.mediName+'</td>';
		s += '<td>'+o.guideCount+'</td>';
		s += '<td>'+o.enterCount+'</td>';
		s += '<td>'+o.ontVisitCount+'</td>';
		s += '<td>'+o.outValidTimeCount+'</td>';
		s += '<td>'+o.enterBuyApplyCount+'</td>';
		s += '<td>'+o.enterBuyEnterApplyCount+'</td>';
		s += '<td>'+o.signFinishCount+'</td>';
		s += '<td>'+o.payMoneyCount+'</td>';
		s += '<td>'+o.receiveMoneyCount+'</td>';
	});
	
	if (data.length > 0) {
	$("#mediDateRankingInfo").html(s);
	} else {
	$("#mediDateRankingInfo").html("<br/><span style='width:10%;height:30px;display:block;margin:0 auto;'>暂无数据1</span>");
	}
	
}

//获取中介对账单数据  项目数据排行
function getProDateRanking() {
	$.ajax({
		type : "post",
		url : "shoper_pro_data_order_list",
		async : false,
		data : {
			startTime : $("#start").val(),
			endTime : $("#end").val(),
			start : startAllAppoint,
			limit:limitAllAppoint,
			projectId : $("#projectSelect").val()
		},
		success : function(data) {
			data = eval("(" + data + ")");
			setProDateRankingInfo(data.root);
			startAllAppoint = data.currentResult;
			totalPageAllAppoint = data.totalPage;
		}
	});
}

function setProDateRankingInfo(data){
	console.log(data);
	var s = "<tr><th>项目</th><th>备案数</th><th>确客数</th><th>备案未到访数</th><th>备案已逾期</th>" +
			"<th>认购申请数</th><th>认购完成数</th><th>签约完成数</th><th>结佣数</th><th>到款</th>" +
			"<th>结佣平均时间</th><th>认购审核通过率</th></tr>";
	$.each(data,function(v, o) {
		s += '<tr><td>'+o.proName+'</td>';
		s += '<td>'+o.guideCount+'</td>';
		s += '<td>'+o.enterCount+'</td>';
		s += '<td>'+o.ontVisitCount+'</td>';
		s += '<td>'+o.outValidTimeCount+'</td>';
		s += '<td>'+o.enterBuyApplyCount+'</td>';
		s += '<td>'+o.enterBuyEnterApplyCount+'</td>';
		s += '<td>'+o.signFinishCount+'</td>';
		s += '<td>'+o.payMoneyCount+'</td>';
		s += '<td>'+o.receiveMoneyCount+'</td>';
		s += '<td>'+o.averageDates+'</td>';
		s += '<td>'+o.checkProgressRate+'</td>';
	});
	
	if (data.length > 0) {
	$("#proDateRankingInfo").html(s);
	} else {
	$("#proDateRankingInfo").html("<br/><span style='width:10%;height:30px;display:block;margin:0 auto;'>暂无数据2</span>");
	}
}

function selectDate(){
	if($("#hidNum").val()==0){
		getShoperSureBillsList();
		toPage1();
	}else if($("#hidNum").val()==1){
		getMediDateRanking();
		toPage2();
	}else if($("#hidNum").val()==2){
		getProDateRanking();
		toPage3();
	}
}

layui.use(['form', 'layedit', 'laydate','layer', 'laypage', 'element'], function(){
	  var layer = layui.layer
	  ,laypage = layui.laypage
	  ,element = layui.element()
	  ,form = layui.form()
	  ,layedit = layui.layedit
    ,laydate = layui.laydate;
	  
	  
	  var start = {
			    min: laydate.now()
			    ,max: '2099-06-16 23:59:59'
			    ,istoday: false
			    ,choose: function(datas){
			      end.min = datas; //开始日选好后，重置结束日的最小日期
			      end.start = datas //将结束日的初始值设定为开始日
			    }
			  };
			  
			  var end = {
			    min: laydate.now()
			    ,max: '2099-06-16 23:59:59'
			    ,istoday: false
			    ,choose: function(datas){
			      start.max = datas; //结束日选好后，重置开始日的最大日期
			    }
			  };
			  
	  
	 
	  //监听Tab切换
	  element.on('tab(demo)', function(data){
		  
		  currentPageAllAppoint = 1;
		  if(this.innerHTML == "中介对账单"){
			  getShoperSureBillsList();
			  toPage1();
			  $('#hidNum').val(0);
		  }
		  if(this.innerHTML == "经纪人数据排行"){
			  getMediDateRanking();
			  toPage2();
			  $('#hidNum').val(1);
		  }
		  if(this.innerHTML == "项目信息排行"){
			  getProDateRanking();
			  toPage3();
			  $('#hidNum').val(2);
	    	
		  }
		  
	  });
	  
	  //分页
	 /*  laypage({
	    cont: 'pageDemo' //分页容器的id
	    ,pages: 100 //总页数
	    ,skin: '#5FB878' //自定义选中色值
	    //skip: true //开启跳页
	    ,jump: function(obj, first){
	      if(!first){
	        layer.msg('第'+ obj.curr +'页');
	      }
	    }
	  }); */
	});
function toPage1() {

	layui.use(['form', 'laypage', 'layedit','layer', 'laydate'], function() {
		var form = layui.form(),
			layer = layui.layer,
			layedit = layui.layedit,
			laydate = layui.laydate,
			laypage = layui.laypage;

						// 调用分页
						laypage({
							cont : 'paged1',
							pages : totalPageAllAppoint // 得到总页数
							,
							curr : currentPageAllAppoint,
							skip : true,
							jump : function(obj, first) {

								currentPageAllAppoint = obj.curr;
								startAllAppoint = (obj.curr - 1)
										* limitAllAppoint;
								getShoperSureBillsList();
								// document.getElementById('biuuu_city_list').innerHTML
								// = render(obj, obj.curr);
								if (!first) { // 一定要加此判断，否则初始时会无限刷新
									// location.href = '?page='+obj.curr;
								}
							}
						});

					});
};
function toPage2() {
	
	layui.use(['form', 'laypage', 'layedit','layer', 'laydate'], function() {
		var form = layui.form(),
			layer = layui.layer,
			layedit = layui.layedit,
			laydate = layui.laydate,
			laypage = layui.laypage;
				
				// 调用分页
				laypage({
					cont : 'paged2',
					pages : totalPageAllAppoint // 得到总页数
					,
					curr : currentPageAllAppoint,
					skip : true,
					jump : function(obj, first) {
						
						currentPageAllAppoint = obj.curr;
						startAllAppoint = (obj.curr - 1)
						* limitAllAppoint;
						getMediDateRanking();
						// document.getElementById('biuuu_city_list').innerHTML
						// = render(obj, obj.curr);
						if (!first) { // 一定要加此判断，否则初始时会无限刷新
							// location.href = '?page='+obj.curr;
						}
					}
				});
				
			});
};
function toPage3() {
	
	layui.use(['form', 'laypage', 'layedit','layer', 'laydate'], function() {
		var form = layui.form(),
			layer = layui.layer,
			layedit = layui.layedit,
			laydate = layui.laydate,
			laypage = layui.laypage;
				
				// 调用分页
				laypage({
					cont : 'paged3',
					pages : totalPageAllAppoint // 得到总页数
					,curr : currentPageAllAppoint,
					skip : true,
					jump : function(obj, first) {
						
						currentPageAllAppoint = obj.curr;
						startAllAppoint = (obj.curr - 1)
						* limitAllAppoint;
						getProDateRanking();
						// document.getElementById('biuuu_city_list').innerHTML
						// = render(obj, obj.curr);
						if (!first) { // 一定要加此判断，否则初始时会无限刷新
							// location.href = '?page='+obj.curr;
						}
					}
				});
				
			});
};


  /* var start = {
        elem: '#start',
        format: 'YYYY-MM-DD hh:mm:ss',
        // min: laydate.now(), //设定最小日期为当前日期
        max: '2099-06-16', //最大日期
        istime: true,
        istoday: false,
        choose: function(datas){
           end.min = datas; //开始日选好后，重置结束日的最小日期
           end.start = datas //将结束日的初始值设定为开始日
           
          $(".start-time").html($("#start").val());
        }
      };
  var end = {
    elem: '#end',
    format: 'YYYY-MM-DD hh:mm:ss',
    // min: laydate.now(),
    max: '2099-06-16',
    istime: true,
    istoday: false,
    choose: function(datas){
      start.max = datas; //结束日选好后，重置开始日的最大日期
      $(".end-time").html($("#end").val());
    }
  };
  laydate(start);
  laydate(end); */

  
	var tableToExcel1 = (function() {
		var uri = 'data:application/vnd.ms-excel;base64,', 
		template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><?xml version="1.0" encoding="UTF-8" standalone="yes"?><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head><body><table>{table}</table></body></html>', 
		base64 = function(s) {
			return window.btoa(unescape(encodeURIComponent(s)))
		}, 
		format = function(s, c) {
			return s.replace(/{(\w+)}/g, function(m, p) {
				return c[p];
			})
		}
		return function(table) {
			if (!table.nodeType)
				table = document.getElementById("shoperBillListInfo");
			var ctx = {
				worksheet : name,
				table : table.innerHTML
			}
			window.location.href = uri + base64(format(template, ctx))
		}
	})()
  
var tableToExcel2 = (function() {
	var uri = 'data:application/vnd.ms-excel;base64,', 
	template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><?xml version="1.0" encoding="UTF-8" standalone="yes"?><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head><body><table>{table}</table></body></html>', 
	base64 = function(s) {
		return window.btoa(unescape(encodeURIComponent(s)))
	}, 
	format = function(s, c) {
		return s.replace(/{(\w+)}/g, function(m, p) {
			return c[p];
		})
	}
	return function(table) {
		if (!table.nodeType)
			table = document.getElementById("mediDateRankingInfo");
		var ctx = {
			worksheet : name,
			table : table.innerHTML
		}
		window.location.href = uri + base64(format(template, ctx))
	}
})()

var tableToExcel3 = (function() {
	var uri = 'data:application/vnd.ms-excel;base64,', 
	template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><?xml version="1.0" encoding="UTF-8" standalone="yes"?><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head><body><table>{table}</table></body></html>', 
	base64 = function(s) {
		return window.btoa(unescape(encodeURIComponent(s)))
	}, 
	format = function(s, c) {
		return s.replace(/{(\w+)}/g, function(m, p) {
			return c[p];
		})
	}
	return function(table) {
		if (!table.nodeType)
			table = document.getElementById("proDateRankingInfo");
		var ctx = {
			worksheet : name,
			table : table.innerHTML
		}
		window.location.href = uri + base64(format(template, ctx))
	}
})()


