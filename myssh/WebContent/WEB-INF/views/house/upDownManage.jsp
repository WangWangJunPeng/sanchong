<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <link  rel="icon" href="static/images/titleLogo.png"  />
        <title>案场助理个人中心-上下架管理</title>
        <link rel="stylesheet" type="text/css" href="static/css/upDownManage.css" />
        <link rel="stylesheet" type="text/css" href="static/css/reset.css" />
        <link rel="stylesheet" type="text/css" href="static/css/paging.css" />
        <script type="text/javascript" src="static/js/jquery-3.1.1.min.js"></script>
        <script type="text/javascript" src="static/js/layer.js"></script>
        <script type="text/javascript" src="static/js/query.js"></script>
        <!-- <script type="text/javascript" src="static/js/paging.js"></script> -->
        <script type="text/javascript" src="static/js/upDownManage.js"></script>
	       
        <script type="text/javascript">
	       $(document).ready(function(){
	   			getHouseTypeMenus();
	   			getFindHouseInfo();
	   			//初始化翻页信息
	   	      	initpage1();
	   		});
	    	// 全选全不选
	       function allChecked(){
	    	   
	       	$("#allChoose").click(function(){ 
	       	    
	       	    if(this.checked == true){
	       		$(".chooseSingle").each(function(){
	       			if(this.disabled == true){
	       				
	       				$(this).prop("checked",false);
	       			}else{
	       				$(this).prop("checked",true);
	       			}
	       		})
	       	    	
	       	    }else{
	       	    	$(".chooseSingle").prop("checked",false);
	       	    }
	       	
	       	})
	       	$(".chooseSingle").click(function(){
	       	    var flag=true;
	       
	       	    $(".chooseSingle").each(function() {
	       	    	
	       	        if (!this.checked) {
	       	        	
	       	            flag=false;
	       	        };
	       	    });
	       	     $("#allChoose").prop("checked",flag);
	       	  	
	       	  })
	    	   
	       }
	   		//分页参数设置
	        var startAllAppoint = 0;
	        var limitAllAppoint = 15;
	        var currentPageAllAppoint = 1;
	        var totalPageAllAppoint = 0;
	       function getFindHouseInfo(){
	    	   $.ajax({
	    		  type:"post",
	    		  async : false,
	    		  url:"selectHouseUpDown",
	    		  data:{decorationStandard:$('#decorationStandard').val(),
	    			  housType:$('#housType').val(),
	    			  houseStatus:$('#houseStatus').val(),
	    			  houseKind:$('#houseKind').val(),
	    			  isOpen:$('#isOpen').val(),
	    			  start:startAllAppoint,
	    			  limit:limitAllAppoint},
	    	   	 success:function(data){
	    	   		 data = eval("("+data+")");
	    	   		 getToFindHouseInfo(data.root);
	    	   		 allChecked();
	    	   		startAllAppoint = data.currentResult;
   					totalPageAllAppoint = data.totalPage;
   					//initpage1();
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
	        		  	  getFindHouseInfo();
	        		  }
	        	});
	        }
	       function getToFindHouseInfo(data){
	    	   var s='<tr><th><input type="checkbox" name="allChoose" id="allChoose" /></th>';
                   s += '<th>房号</th><th>类型</th><th>楼栋号</th><th>房型</th><th>建筑面积</th><th>使用面积</th>';
                   s += '<th><span class="price">列表价(元)</span><span class="sort"></span></th>';
                   s += '<th><span class="price">供货价(元)</span><span class="sort"></span></th>';
                   s += '<th><span class="price">底价(元)</span><span class="sort"></span></th>';
                   s += '<th>发布时间</th><th>房源状态</th><th>经纪人可见</th></tr>';
               $.each(data,function(v,o){
            	s += '<tr><td>';
            	if (o.houseStatus == 4){
            		s += '<input type="checkbox" class="chooseSingle" name="checkBox" value="'+o.houseNum+'" disabled="disabled" /></td><td>'+o.houseNo+'</td>';
            	
            	}else {
            		s += '<input type="checkbox" class="chooseSingle" name="checkBox" value="'+o.houseNum+'"/></td><td>'+o.houseNo+'</td>';
            	}
            	
	            if (o.houseKind == 0){
	            	s += '<td >公寓</td>';
	            }else if(o.houseKind == 1){
	            	s += '<td >排屋</td>';
	            }else if(o.houseKind == 2){
	            	s += '<td >独栋</td>';
	            }else if(o.houseKind == 3){
	            	s += '<td >商用两住</td>';
	            }else if(o.houseKind == 4){
	            	s += '<td >办公室</td>';
	            }else if(o.houseKind == 5){
	            	s += '<td >酒店式公寓</td>';
	            }else if(o.houseKind == 6){
	            	s += '<td >商铺</td>';
	            }else if(o.houseKind == 7){
	            	s += '<td >车位</td>';
	            }else if(o.houseKind == 8){
	            	s += '<td >车库</td>';
	            }else if(o.houseKind == 9){
	            	s += '<td >储藏室</td>';
	            }
            	s += '<td>'+o.buildingNo+'</td><td>'+o.houseStyle+'</td>';
				s += '<td>'+o.buildArea+'㎡</td><td>'+o.usefulArea+'㎡</td><td>'+o.listPrice+'</td>';		
				s += '<td>'+o.shopPrice+'</td><td>'+o.minimumPrice+'</td><td>'+o.shelvTime+'</td>';
				if (o.houseStatus == 0) {
					s += '<td >初始</td>';
				} else if(o.houseStatus == 1){
            	   s += '<td >上架</td>';
				} else if(o.houseStatus == 2){
            	   s += '<td >删除</td>';
				} else if(o.houseStatus == 3){
            	   s += '<td >撤销</td>';
				} else if(o.houseStatus == 4){
            	   s += '<td >签约</td>';
				} 
				if (o.isOpen == 1){
					s += '<td>可见</td>';
				} else{
					s += '<td>不可见</td>';
				}
                s += '</tr>';   
               }) ;
               if (data.length>0){
   				$('#housesInfo').html(s);
   			}else{
   				$("#housesInfo").html("<br/><span style='width:10%;height:30px;display:block;margin:0 auto;'>暂无数据</span>");
   			}
	       }
	       function getSearch(){
	    	   getFindHouseInfo();
	       }
	       
	        function getHouseTypeMenus(){
	        	$.ajax({
	   				type:"post",
	   				async : false,
	   				url:"upAndDown_menu_list_house_type",
	   				success:function(data){
	   					data=eval("("+data+")");
	   					initHouseType(data.root);
	   				}
	   			});
	        }
	        function initHouseType(data){
				$.each(data,function(rowNum,o){
					$("#htNext").after('<option value="'+o+'">'+o+'</option>');
				});
			}

        
	        function getUp(){        	
		        var aa="";
		        $("input[name='checkBox']:checked").each(function()
		        	{
		        	aa += $(this).val() + ",";
		       	 })
		       	 if(aa != ""){
		       		 layer.confirm('确认要上架选中的房源吗？', {
		                 title:['操作确认'],
		                 btn: ['确定','取消'],
		                 btnAlign:'c'
		             }, function(){
		                  layer.msg('上架成功!',{icon:1,time:2000},function(){
		                 	
		                	  $.ajax({
						       	   type: "POST",
						       	   url: "updateUp",
						       		//async: false,
						       	   data:{houseNumber:aa},
						       	   success: function(msg){
						       		getFindHouseInfo();
						       	   },
						       	   error:function(){
						       		 //alert(11111)
						       	   }
						       	});
		                  }); 
		                 
		             }); 	
	        	}else{
	        		layer.alert("请选择需要上架的房源")
	        	}
	        }
        </script>
        
        <script type="text/javascript">
	        function getDown(){
		        var aa="";
		        $("input[name='checkBox']:checked").each(function()
		        	{
		        	aa += $(this).val() + ",";
		       	 })
		       	if(aa != ""){
		       		 layer.confirm('确认要下架选中的房源吗？', {
		                 title:['操作确认'],
		                 btn: ['确定','取消'],
		                 btnAlign:'c'
		             }, function(){
		                  layer.msg('下架成功!',{icon:1,time:2000},function(){
		                 	
		                	  $.ajax({
		       		       	   type: "POST",
		       		       	   url: "updateDown",
		       		       		//async: false,
		       		       	   data:{houseNumber:aa},
		       		       	   success: function(msg){
		       		       		getFindHouseInfo();
		       		       	   },
		       		       	   error:function(){
		       		       		 //alert(11111)
		       		       	   }
		       		       	});
		                  }); 
		                 
		             }); 	
	        	}else{
	        		layer.alert("请选择需要下架的房源")
	        	}
	        }
        </script>
        	
        <script type="text/javascript">
	        function getOpen(){
		        var aa="";
		        $("input[name='checkBox']:checked").each(function()
		        	{
		        	aa += $(this).val() + ",";
		       	 })
		     	if(aa != ""){
		       		 layer.confirm('确认要对经纪人可见？', {
		                 title:['操作确认'],
		                 btn: ['确定','取消'],
		                 btnAlign:'c'
		             }, function(){
		                  layer.msg('可见成功!',{icon:1,time:2000},function(){
		                 	
		                	  $.ajax({
		       		       	   type: "POST",
		       		       	   url: "updateIsOpen",
		       		       		//async: false,
		       		       	   data:{houseNumber:aa},
		       		       	   success: function(msg){
		       		       		getFindHouseInfo();
		       		       	   },
		       		       	   error:function(){
		       		       		 //alert(11111)
		       		       	   }
		       		       	});
		                  }); 
		                 
		             }); 	
	        	}else{
	        		layer.alert("请选择需要对经纪人可见的的房源")
	        	}
	        }
        </script>
        
        <script type="text/javascript">
	        function getClose(){
		        var aa="";
		        $("input[name='checkBox']:checked").each(function()
		        	{
		        	aa += $(this).val() + ",";
		       	 })
		       	if(aa != ""){
		       		 layer.confirm('确认取消对经纪人可见？', {
		                 title:['操作确认'],
		                 btn: ['确定','取消'],
		                 btnAlign:'c'
		             }, function(){
		                  layer.msg('取消成功!',{icon:1,time:2000},function(){
		                 	
		                	  $.ajax({
		       		       	   type: "POST",
		       		       	   url: "updateIsClose",
		       		       		//async: false,
		       		       	   data:{houseNumber:aa},
		       		       	   success: function(msg){
		       		       		getFindHouseInfo();
		       		       	   },
		       		       	   error:function(){
		       		       		 //alert(11111)
		       		       	   }
		       		       	});
		                  }); 
		                 
		             }); 	
	        	}else{
	        		layer.alert("请选择需要取消对经纪人可见的的房源")
	        	}
	        }
        </script>
    </head>
    
    
    <body>
     <!-- <div class="header">
            <a href="###" class="self-center">案场助理个人中心</a>
            <ul>
              <li><a href="###">首页</a></li>
               <li><a href="###">案场信息维护</a></li>
               <li><a href="###">价格优惠条款</a></li>
               <li><a href="###">房源管理</a></li>
               <li><a href="###">上下架管理</a></li>
               <li><a href="###">客户管理</a></li>
               <li><a href="###">成交业务</a></li>
               <li><a href="###">账号管理</a></li>
            </ul>
            <a href="###" class="welcome">欢迎您，某某某</a>
        </div> -->
		<%@include file="../publicpage/publicHeader.jsp" %>
        <div class="container">
            <div class="upDown-manage">
                <p style="font-size:14px;color:#0c95db;"><a href="to_pro_index" style="font-size:14px;color:#0c95db;">首页</a>&nbsp;-&nbsp;<a href="###" style="font-size:14px;color:#0c95db;">上下架管理</a></p>
            </div>
            <div class="batch-resource">
                <div class="resource-btn">
                    <button class="btn1" onclick="getUp()">批量上架</button>
                    <button class="btn2" onclick="getDown()">批量下架</button>
                    <button class="btn3" onclick="getOpen()">对经纪人可见</button>
                    <button class="btn4" onclick="getClose()">取消对经纪人可见</button>
                </div>
                <div class="house-search">
                    <form action="" method="post">
                        <!-- <select name="decorationStandard" id="decorationStandard">
                          <option value="">装修标准</option>
                          <option value="0">毛坯</option>
                          <option value="1">普通装修</option>
                          <option value="2">精装修</option>
                          <option value="3">家具全配</option>
                          <option value="4">家电全配</option>
                        </select> -->
                        <select name="housType" id="housType">
                          <option value="" id="htNext">选择房型</option>
                         <!--  <option value="1">一室一厅</option>
                          <option value="2">二室一厅</option>
                          <option value="3">三室两厅</option>                    
                          <option value="4">四室三厅</option>   -->                  
                        </select>
                        <select name="houseStatus" id="houseStatus">
                          <option value="">房源状态</option>
                          <option value="0">下架</option>
                          <option value="1">上架</option>
                          <option value="3">撤销</option>
                          <option value="4">签约</option>
                        </select>
                        <select name="houseKind" id="houseKind">
                          <option value="">房源类型</option>
                          <option value="0">公寓</option>
                          <option value="1">排层</option>
                          <option value="2">独栋</option>
                          <option value="3">商住两用</option>
                          <option value="4">办公室</option>
                          <option value="5">酒店式公寓</option>
                          <option value="6">商位</option>
                          <option value="7">车位</option>
                          <option value="8">车库</option>
                          <option value="9">储藏室</option>
                        </select>
                        <select name="isOpen" id="isOpen">
                          <option value="">经纪人是否可见</option>
                          <option value="0">不可见</option>
                          <option value="1">可见</option>
                        </select>
                        <input type="button" value="搜索" class="btn-search" onclick="getSearch()"/>
                        <input type="reset" value="重置" class="btn-search" />
                    </form>                        
                   
                </div>
                <div class="house-list">
                   <table cellpadding="8" cellspacing="0" id="housesInfo">
                   </table>
                </div>
            </div>
            
         <div class="pagination" id="page1">
				<a href="#" class="first" data-action="first">&laquo;</a>
				<a href="#" class="previous" data-action="previous">&lsaquo;</a>
				<input type="text" readonly="readonly" data-max-page="40" />
				<a href="#" class="next" data-action="next">&rsaquo;</a>
				<a href="#" class="last" data-action="last">&raquo;</a>
		</div> 
		<div style="height:50px;"></div>
        </div>
    </body>       
   
</html>