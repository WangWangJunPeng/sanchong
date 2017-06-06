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
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/jquery.dialogbox.css">
        <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-3.1.1.min.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery.dialogBox.js"></script>
        <script type="text/javascript">
        	$(function(){
        		//document.getElementById("Pvalidity").style.display="none";//隐藏
        		$("#Pvalidity").hide();
        		//document.getElementById("Svalidity").style.display="none";//隐藏
        		$("#Svalidity").hide();
        		$("#projectBox").click(function(){
        			if(this.checked){
        			userId = $("#userId").val();
        				if(userId){
        					$("#projectDIV").empty();
	        				$.post("query_all",
	        						{
	        						    "status":1,
	        						  },
	        						  function(data){  
	        							 var data = eval('('+ data +')');
	        							 for(var i=0;i<data.total;i++){
	        								 $('<label  class="form-label">'+data.root[i].projectName+'</label>').appendTo($("#projectDIV"));
	        								 $("<input type='checkbox' name='projectIds'  data-value='"+data.root[i].projectName+"' value='"+data.root[i].projectId+"' class='chooseSingle' />").appendTo($("#projectDIV"));
	        								 //document.getElementById("Pvalidity").style.display="";//显示
	        								 $("#Pvalidity").show();
	        							 }
	        							 
	        						  });
        					
        				}else{
        					$(this).attr("checked",false);
        					alert("请选择合伙人");
        				}
        			}else{
        				$("#projectDIV").empty();
        				$("#Pvalidity").hide();
        			}
    			})
    			
    			
    			$("#shopsBox").click(function(){
        			if(this.checked){
        			userId = $("#userId").val();
        				if(userId){
        					$("#shopDIV").empty();
	        				$.post("query_all",
	        						{
	        						    "status":2,
	        						  },
	        						  function(data){  
	        							 var data = eval('('+ data +')');
	        							 for(var i=0;i<data.total;i++){
	        								 $('<label  class="form-label">'+data.root[i].shopName+'</label>').appendTo($("#shopDIV"));
	        								 $("<input type='checkbox' name='shopIds' data-value='"+data.root[i].shopName+"' value='"+data.root[i].shopId+"' class='chooseSingle' />").appendTo($("#shopDIV"));
	        								 //document.getElementById("Pvalidity").style.display="";//显示
	        								 $("#Svalidity").show();
	        							 }
	        							 
	        						  });
        					
        				}else{
        					$(this).attr("checked",false);
        					alert("请选择合伙人");
        				}
        			}else{
        				$("#shopDIV").empty();
        				$("#Svalidity").hide();
        			}
    			})
    			
    			
    			$("#id1").click(function(){
    				$.post("login_login",
    						{
    						  },
    						  function(data){  
    							  alert(data.root.username);
    							  alert(data.username);
    							 
    						  });
    			})
        	})
        
        </script>
    </head>
    <body>

        <%@include file="public/systempublicpage.jsp" %>
        <div class="container">
            <div class="home-houseManageAdd">
                <p>首页&nbsp;-&nbsp;合伙人管理&nbsp;-&nbsp;新建关联</p>
            </div>
            <div class="add-list">
                <form action="<%=request.getContextPath()%>/addPartner_relation" method="post" class="form-box" id="form_one">
                    <div class="form-div">
                        <label  class="form-label" >合伙人<b>*</b></label>
                        <select id="userId" name="userId"  class="sale-id">
                            <option value="">请选择</option>
                            <c:forEach items="${users}" var="u">
                            	<option value="${u.userId}">${u.userCaption}</option>
                            </c:forEach> 
                        </select>
                    </div>

                    <div class="form-div">
                        <label  class="form-label" >项目<b>*</b></label>
                        <input type="checkbox" id="projectBox" >
                    </div>
                     <div class="form-div"  id="projectDIV">
                     	
                    </div>
                    <div class="form-div" id="Pvalidity">
                        <label  class="form-label" >有效期<b>*</b></label>
                        <select name="Pvalidity">
	                        <c:forEach begin="0" end="99" var="num">
		                        <c:choose>
		                        	<c:when test="${num==99}">
		                        		<option value=${num} selected = "selected">${num}年</option>
		                        	</c:when>
		                        	<c:otherwise>
			                        	<option value=${num}>${num}年</option>
		                        	</c:otherwise>
		                        </c:choose>
	                        </c:forEach>
                        </select>
                    </div>
                    <div class="form-div">
                        <label  class="form-label" >门店<b>*</b></label>
                        <input type="checkbox" id="shopsBox" class="chooseSingle">
                    </div>
                     <div class="form-div" id="shopDIV">
                    </div>
                    <div class="form-div" id="Svalidity">
                        <label  class="form-label" >有效期<b>*</b></label>
                        <select name="Svalidity">
	                        <c:forEach begin="0" end="99" var="num">
		                        <c:choose>
		                        	<c:when test="${num==99}">
		                        		<option value=${num} selected = "selected">${num}年</option>
		                        	</c:when>
		                        	<c:otherwise>
			                        	<option value=${num}>${num}年</option>
		                        	</c:otherwise>
		                        </c:choose>
	                        </c:forEach>
                        </select>
                    </div>
               		<input type="hidden" name="isSave" id="isSave">
                    <div class="btn">
                        <input  type="button" class="btn1" value="提交并返回房源列表" onclick="btClick(this)"/>
                        <input  type="button" class="btn2" value="提交并新增下一条" onclick="btClick(this)"/>
                    </div>
                </form>
            </div>
        </div>
		<div id="simple-dialogBox"></div>
		<script type="text/javascript">
		////////////////////////////
		//弹出框弹出框弹出框弹出框////////
		////////////////////////////
			function btClick(a){
				var val = $(a).val();
				$("#isSave").val(val);
				var userName = $("#userId").find("option:selected").text();
				var projects = "";
				var shops = "";
				$("input:checkbox[name='projectIds']:checked").each(function() {
					projects += $(this).data('value') + "    ";
				});
				$("input:checkbox[name='shopIds']:checked").each(function() {
					shops += $(this).data('value') + "    ";
				});
				$('#simple-dialogBox').dialogBox({
					width: 300,
					height: 200,
					hasMask: true,
					hasClose: true,
					hasBtn: true,
					confirmValue: '确定',
					confirm: function(){
						$("#form_one").submit();
					},
					cancelValue: '取消',
					title: '您修改了以下内容:',
					content: userName+'：<br/>'+'关联案场:'+projects+'<br/>'+'关联门店:'+shops
				});
			}
		</script>

     </body>
     </html>