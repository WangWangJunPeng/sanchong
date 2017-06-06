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
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/commend.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/index.css">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/houseManage.css" />
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/reset.css" />
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/lib/laydate/need/laydate.css" />
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/validation.css" />
        <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-3.1.1.min.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery.validate.js"></script>
        <script type="text/javascript">
        	$(function(){
        		//$("#commentForm").validate();
        		$("#commentForm").validate({
        			rules: {
        				projectName: "required",
        				officeAddress: "required",
        				
        				userCaption: {
        					required: true,
        					minlength: 2
        				},
        				phone:{
        					required:true,
        				},
        			
        				//email: {
        				//	required: true,
        				//	email: true
        				//},
        				
        			},
        			messages: {
        				projectName: "请填写案场名称",
        				officeAddress: "请填写售楼地址",
        				userCaption: {
        					required: "请输入助理姓名",
        					minlength: "至少 2个字节"
        				},
        				phone: {
        					required:"请输入电话",
        				}
        				
        			}
        		});
        		$("#province").validate();
        		
        		$("#province").change(function(){
        			var pId = $("#province").val();
        			$("#market").empty();
        			$.post("menu_list_city_area",
    						{
    						    "shengOrShi":pId
    						  },
    						  function(data){  
    							 var data = eval('('+ data +')');
    							 for(var i=0;i<data.root.length;i++){
    								 $("<option value='"+data.root[i].cityId+"'>"+data.root[i].cityName+"</option> ").appendTo($("#market"))
    							 }
    								 changeMarket();
    							 
    						  });
        		})
        		function changeMarket(){
        			var pId = $("#market").val();
        			//alert(pId);
        			$("#area").empty();
        			$.post("menu_list_city_area",
    						{
    						    "shengOrShi":pId
    						  },
    						  function(data){  
    							 var data = eval('('+ data +')');
    							 for(var i=0;i<data.root.length;i++){
    								 $("<option value='"+data.root[i].cityId+"'>"+data.root[i].cityName+"</option> ").appendTo($("#area"));
    							 }
    						  });
        		}
        		
        		$("#market").change(function(){
        			changeMarket();
        		})
        		
        		$("#phone").blur(function(){ 
        			var phoneNum = $("#phone").val();
        			if(phoneNum){
	        			$.post("find_only_phone",
	    						{
	    						    "phoneNum":phoneNum
	    						  },
	    						  function(data){  
	    							if(data.status==200){//表示号码没有重复
	    								$("#phoneMsg").html(data.message);
	    								$(":submit").removeAttr("disabled");
	    							}
	    							if(data.status==202){
	    								$("#phoneMsg").html(data.message);
	    								$(":submit").attr("disabled","true");
	    							}
	    						  });
        			}else{
        				
        			}
        		})
        		
        		$("#phone").focus(function(){
        			$("#phoneMsg").html("");
        		});
        	})
        
        </script>
    </head>
    <body>

        <%@include file="public/systempublicpage.jsp" %>
        <div class="container">
            <div class="home-houseManageAdd">
                <p>首页&nbsp;-&nbsp;案场管理&nbsp;-&nbsp;新增案场</p>
            </div>
            <div class="add-list">
                <form id="commentForm" action="addProject" method="post" class="form-box">
               		 <div class="form-div">
                        <label  class="form-label" >案场名称<b>*</b></label>
                        <input type="text" name="projectName" class="form-input alike " />
                    </div>
                    <div class="form-div">
                        <label  class="form-label" >所在地<b>*</b></label>
                        <select id="province" name="province"  class="sale-id">
                         	<option value="">请选择</option> 
                        	<c:forEach var="p" items="${provinces}">
	                            <option value="${p.cityId }">${p.cityName }</option> 
                        	</c:forEach>
                        </select>
                        <select id="market" name="market"  class="sale-id">
                            <option value="">请选择</option> 
                        </select>
                        <select id="area" name="area"  class="sale-id">
                            <option value="">请选择</option> 
                        </select>
                    </div>
<!--  
                    <div class="form-div">
                        <label  class="form-label" >售楼处地址<b>*</b></label>
                        <input type="text" name="officeAddress" class="form-input same" />
                    </div>
                    -->
                    <!--  
                     <div class="form-div">
                        <label  class="form-label" >案场联系电话<b>*</b></label>
                        <input type="text" name="" class="form-input same" />
                    </div>
                    -->
                    <div class="form-div">
                        <label  class="form-label" >案场助理姓名<b>*</b></label>
                        <input type="text" name="userCaption" class="form-input same" />
                    </div>
                    <div class="form-div">
                        <label  class="form-label" >案场助理电话<b>*</b></label>
                        <input type="text" id="phone" name="phone" class="form-input same" /><span style="font-size:10px; color:#ff0000;" id="phoneMsg"></span>
                    </div>
                    <!-- 
                    <div class="form-div">
                        <label  class="form-label" >所属合伙人<b>*</b></label>
                        <input type="text" name="" class="form-input same" />
                    </div>
                		-->
                    <div class="btn">
                        <input name="isSave" type="submit" class="btn1" value="提交并返回项目列表" />
                        <input name="isSave" type="submit" class="btn2" value="提交并新增下一条" />
                    </div>
                </form>
            </div>
        </div>

     </body>
     </html>