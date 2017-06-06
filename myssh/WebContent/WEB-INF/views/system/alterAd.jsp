<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link  rel="icon" href="static/images/titleLogo.png"  />
        <title>平台运管管理中心-案场信息维护</title>
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/lib/laydate/need/laydate.css" />
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/commend.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/index.css">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/houseManage.css" />
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/reset.css" />
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/lib/laydate/need/laydate.css" />
         <link rel="stylesheet" type="text/css" href="static/css/validation.css" />
        <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-3.1.1.min.js"></script>
         <script type="text/javascript" src="static/js/jquery.validate.js"></script>
         <script type="text/javascript" src="static/js/validation.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/static/lib/laydate/laydate.js"></script>
		<script type="text/javascript">
			$(function(){
				getHtmlInfo();
				
				$("#adPosition").change(function(){
					var province = $("#province").val();
					var market = $("#market").val();
					var adPosition = $("#adPosition").val();
					$.post("query_sorting",
							{
							    "province":province,
							    "market":market,
							    "adPosition":adPosition
							  },
							  function(data){
								  var maxSt = data;
							    $("#maxSorting").html("当前最大值："+maxSt);
							  });
				})
				
				$("#province").change(function(){
					getMarktInfo();
	        	})
	        	
	        	var start = {
			          elem: '#start',
			          format: 'YYYY-MM-DD hh:mm:ss',
			          //min: laydate.now(), //设定最小日期为当前日期
			          max: '2099-06-16 23:59:59', //最大日期
			          istime: true,
			          istoday: false,
			          choose: function(datas){
			             end.min = datas; //开始日选好后，重置结束日的最小日期
			             end.start = datas  //将结束日的初始值设定为开始日
			          }
			        };
			    var end = {
			      elem: '#end',
			      format: 'YYYY-MM-DD hh:mm:ss',
			      //min: laydate.now(),
			      max: '2099-06-16 23:59:59',
			      istime: true,
			      istoday: false,
			      choose: function(datas){
			        start.max = datas; //结束日选好后，重置开始日的最大日期
			      }
			    };
			    laydate(start);
			    laydate(end);
			})
			
			function getMarktInfo(){
				var pId = $("#province").val();
    			$("#market").empty();
    			 $.ajaxSetup({
    			        async: false
    			 });
    			$.post("menu_list_city_area",
    				{"shengOrShi":pId},
					function(data){  
						var data = eval('('+ data +')');
						 for(var i=0;i<data.root.length;i++){
							 $("<option value='"+data.root[i].cityId+"'>"+data.root[i].cityName+"</option> ").appendTo($("#market"))
						 }
							 
				});
			}
			
			function getHtmlInfo(){
				var id = $("#adPosit").val();
				console.log(id);
				if($("#adPosit").val()=="为你推荐"){
					$("#adPosition").find("option[value=foryou]").prop("selected",true);
				}else if($("#adPosit").val()=="本地精选"){
					$("#adPosition").find("option[value=location]").prop("selected",true);
				}else if($("#adPosit").val()=="其他推荐"){
					$("#adPosition").find("option[value=others]").prop("selected",true);
				}
				
				if($("#isEnable").val()==1){
					$("#sIsEnable").prop("checked",true);
				}
				
				//设置选中项目
				var v = $("#proId").val();
				$("#projectId").find("option[value="+v+"]").prop("selected",true);
				//地区显示选中
				var city = $("#hidCity").val();
				var array = city.split("-");
				var prov = array[0];
				$("#province").find("option[value="+prov+"]").prop("selected",true);
				//获取市列表
				getMarktInfo();
				var markt = array[1];
				console.log(markt);
				$("#market").find("option[value="+markt+"]").prop("selected",true);
			}
			
			function oldFile() {
				
			    var windowURL = window.URL || window.webkitURL;
			    var loadImg = windowURL.createObjectURL(document.getElementById('pic').files[0]);
			    document.getElementById('oldImg').setAttribute('src',loadImg);
			    
			}   
		</script>
		
		<style type="text/css">
		
			.form-box .form-div .up-pic{display:inline-block;width: 100px;height:100px;border-radius: 5px;border: 1px solid skyblue;position: relative;text-align: center;overflow: hidden;top:20px;margin-left: 10px;}
			 .form-box .form-div .up-pic span{line-height: 90px;color: #169bd5;font-size: 100px;}
			 .form-box .form-div .up-pic .file-pic{ width: 100%;height: 100%;position: absolute;top:0;right: 0;z-index: 100;opacity: 0;cursor: pointer;}
			 .form-box .form-div img{height:100px;width:100px;border:0;position:absolute;left:0;}
			
	
		</style>
    </head>
    <body>
		<%@include file="public/systempublicpage.jsp" %>
        <div class="container">
            <div class="home-houseManageAdd">
                <p><a href="system_index">首页</a>&nbsp;-&nbsp;<a href="system_to_adList">广告管理</a>&nbsp;-&nbsp;修改广告</p>
            </div>
            <div class="add-list" >
                <form action="ad_update_by_grl" method="post" enctype="multipart/form-data" class="form-box" id="add_adver">
                	<input id="adId" type="hidden" name="adId" value="${ad.adId}">
                    <div class="form-div">
                       <label  class="form-label" >显示地区<b>*</b></label>
                       <%--  <input id="hidCity" name="adCity" type="hidden" value="${ad.provinceAndCity}"> --%>
                    	<input id="hidCity" type="hidden" value="${ad.provinceAndCity}">
                        <select id="province" name="province"  class="sale-id">
                         	<option value="">请选择</option> 
                        	<c:forEach var="p" items="${provinces}">
	                            <option value="${p.cityId }">${p.cityName }</option> 
                        	</c:forEach>
                        </select>
                        <select id="market" name="market"  class="sale-id">
                            <!-- <option value="">请选择</option> -->
                        </select>
                    </div>
                    <div class="form-div">
                        <label  class="form-label" >广告位<b>*</b></label>
                        <input id="adPosit" type="hidden" value="${ad.adPosition}">
                        <select id="adPosition" name="adPosition"  class="sale-id">
                            <option value="foryou" selected="selected">为你推荐</option>
                            <option value="location">本地精选</option> 
                          	<option value="others">其他推荐</option> 
                        </select>
                    </div>
                    <div class="form-div" style="margin-top:0px;margin-bottom:30px;">
                        <label  class="form-label" >导读图片<b>*</b></label>
                        
                         <div class="up-pic">
                            <img src="${ad.adUrl}" id="oldImg" > 
                            <span>+</span>
                        <input id="pic" name="picFile"  onchange="oldFile()" multiple="true" class="form-input file-pic"  type="file">
                        <input name="adUrl"  type="hidden" value="${ad.adUrl}">
                        </div>	
                        
                    </div>
                    <div class="form-div">
                        <label  class="form-label" >广告标题<b>*</b></label>
                        <input id="adtitle" type="text" name="adTitle" class="form-input alike " value="${ad.adTitle}"/>
                    </div>
                    <div class="form-div">
                        <label  class="form-label" >项目<b>*</b></label>
                        <input id="proId" type="hidden" value="${ad.projectId}">
                        <select id="projectId" name="projectId"  class="sale-id">
                            <option value="0">请选择</option> 
                            <c:forEach var="p" items="${projects}">
                            	<option value="${p.projectId }">${p.projectName }</option> 
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-div">
                        <label  class="form-label" >开始展示时间<b>*</b></label>
                        <input class="laydate-icon icon-li form-input start-time" name="startTime" id="start" value="${ad.startTime}"/>
                    </div>
                     <div class="form-div">
                        <label  class="form-label" >结束展示时间<b>*</b></label>
                        <input class="laydate-icon icon-li form-input end-time" name="endTime" id="end" value="${ad.endTime}"/>
                    </div>
                     <div class="form-div">
                        <a id="maxSorting" class="form-label"></a><br/>
                        <label  class="form-label" >排序<b>*</b></label>
                        <input type="text" name="sorting" class="form-input alike " value="${ad.sorting}"/>
                    </div>
                    <div class="form-div">
                        <label  class="form-label" >是否启用</label>
                        <input id="isEnable" type="hidden" value="${ad.state}">
                        <input id="sIsEnable" type="checkbox" name="State" class="form-input" value="1" />
                        <span>启用</span>
                    </div>
                    <div class="btn">
                        <input type="submit" class="btn1" value="确定" />
                    </div>
                    ${data}
                </form>
            </div>
        </div>
     </body>
     </html>