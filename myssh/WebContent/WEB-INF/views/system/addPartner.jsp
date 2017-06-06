<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link  rel="icon" href="static/images/titleLogo.png"  />
        <title>平台运管管理中心-合伙人管理</title>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/commend.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/index.css">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/houseManage.css" />
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/reset.css" />
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/manageClause.css" />  
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/lib/laydate/need/laydate.css" />
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/validation.css" />
        <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-3.1.1.min.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery.validate.js"></script>
        <script type="text/javascript">
        	$(function(){
        		//表单验证
        		$(".form-box").validate({
        			rules: {
        				userCaption: "required",
        				phone: "required",
        				idCard: "required"
        			},
        			messages: {
        				userCaption: "请填写名字",
        				phone: "请填写电话号码",
        				idCard: "请填写身份证号"
        			}
        		});
        		
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
                <p>首页&nbsp;-&nbsp;合伙人管理&nbsp;-&nbsp;新建合伙人</p>
            </div>
            <div class="add-list">
                <form action="<%=request.getContextPath()%>/system_addPartner" method="post" class="form-box">
                     <div class="form-div">
                        <label  class="form-label" >合伙人姓名:<b>*</b></label>
                        <input type="text" id="userCaption" name="userCaption" > 
                    </div>
                    <div class="form-div">
                        <label  class="form-label" >手机号码:<b>*</b></label>
                     	<input type="text"  id="phone" name="phone"><span style="font-size:10px; color:#ff0000;" id="phoneMsg"></span>
                    </div>
               		<div class="form-div">
                        <label  class="form-label" >身份证号:<b>*</b></label>
                     	<input type="text"  id="idCard" name="idCard">
                    </div>
                    <div class="btn">
                        <input name="isSave" type="submit" class="btn1" value="提交后继续新增" />
                        <input name="isSave" type="submit" class="btn2" value="提交后返回列表" />
                    </div>
                </form>
            </div>
        </div>

     </body>
     </html>