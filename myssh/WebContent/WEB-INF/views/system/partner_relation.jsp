<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
    
<!DOCTYPE html >
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
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/jquery.dialogbox.css">
        <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-3.1.1.min.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery.dialogBox.js"></script>
        <script type="text/javascript">
        	$(function(){
        		
        		$(".btn2").click(function(){
        			window.location.href="toPartnerList";
        		})
        		var phone1 = $("#phone").val();
        		$("#phone").change(function(){
        			var phoneNum = $("#phone").val();
        			if(phoneNum){
	        			$.post("find_only_phone",
	    						{
	    						    "phoneNum":phoneNum
	    						  },
	    						  function(data){  
	    							if(data.status==200){//表示号码没有重复
	    								$('#simple-dialogBox').dialogBox({
	    									title: '警告',
	    									hasBtn: true,
	    									confirmValue: '确认',
	    									confirm: function(){
	    										//alert('this is callback function');
	    									},
	    									cancelValue: '取消',
	    									cancel:function(){
	    										$("#phone").val(phone1);
	    									},
	    									hasClose: true,
	    									hasMask: true,
	    									content: "您确认将号码改成"+phoneNum+"?"
	    								});
	    							}
	    							if(data.status==202){
	    								$('#simple-dialogBox').dialogBox({
	    									title: '提示',
	    									autoHide: true,
	    									time: 1500,
	    									hasClose: true,
	    									hasMask: true,
	    									content: data.message
	    								});
	    								$("#phone").val(phone1);
	    							}
	    						  });
        			}else{
        				
        			}
        			
        		})
        		
        		$(".btn1").click(function(){
        			var partnerName = $("#userCaption").val();
    				var projects = "";
    				var shops = "";
    				$("input:checkbox[name='projectIds']").not("input:checked").each(function() {
    					projects += $(this).data('value') + "    ";
    				});
    				$("input:checkbox[name='shopIds']").not("input:checked").each(function() {
    					shops += $(this).data('value') + "    ";
    				});
    				var phoneNum = $("#phone").val();
    				var	context = "";
    				if(phoneNum != phone1){
    					context = "修改手机号码为:"+phoneNum+"<br/>";
    				}
    				if(projects!=""){
    					context += '取消案场关联:'+projects+'<br/>';
    				}
    				if(shops!=""){
    					context+='取消门店关联:'+shops;
    				}
    				
    				$('#simple-dialogBox').dialogBox({
    					
    					hasMask: true,
    					hasClose: true,
    					hasBtn: true,
    					confirmValue: '确定',
    					confirm: function(){
    						$("#form_one").submit();
    					},
    					cancelValue: '取消',
    					title: '您对 <u>'+partnerName+'</u> 做了以下修改:',
    					content: context
    				});
        		})
        		
        	})
        	
        
        </script>
    </head>
    <body>
		
        <%@include file="public/systempublicpage.jsp" %>
        <div id="simple-dialogBox"></div>
        <div class="container">
            <div class="home-houseManageAdd">
                <p><a href="system_index">首页</a>&nbsp;-&nbsp;<a href="toPartnerList">合伙人管理</a>&nbsp;-&nbsp;查看关联</p>
            </div>
            <div class="add-list">
                <form action="<%=request.getContextPath()%>/changePartner_relation" method="post" class="form-box" id="form_one">
                    <div class="form-div">
                        <label  class="form-label" >合伙人姓名:</label>
                        ${partner.partnerName}
                        <input type="hidden" name="userCaption" id="userCaption" value="${partner.partnerName}">
                        <input type="hidden" name="userId" value="${partner.partnerId}">
                    </div>
                    <div class="form-div">
                        <label  class="form-label" >电话号码:</label>
                        <input type="text" id="phone" name="phone" value="${partner.phone}">
                    </div>

                    <div class="form-div">
                        <label  class="form-label" >已关联项目:</label>
                        <c:forEach var="p" items="${partner.projects}">
                        	<input type="checkbox" id="projectIds" name="projectIds" value="${p.projectId }" checked="checked"  data-value="${p.projectName}"/>${p.projectName}
                        </c:forEach>
                    </div>
                    <div class="form-div">
                        <label  class="form-label" >已关联门店:</label>
                             <c:forEach var="s" items="${partner.shops}">
                        		<input type="checkbox" id="shopIds" name="shopIds" value="${s.shopId }" checked="checked"  data-value="${s.shopName }">${s.shopName }
                       		 </c:forEach>
                    </div>
                    <div class="btn">
                        <input  type="button" class="btn1" value="保存修改"/>
                        <input type="button" class="btn2" value="返回">
                    </div>
                </form>
            </div>
        </div>

     </body>
     </html>