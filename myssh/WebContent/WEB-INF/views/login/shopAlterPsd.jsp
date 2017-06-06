<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link  rel="icon" href="static/images/titleLogo.png"  />
        <title>门店管理后台-修改密码</title>
        <link rel="stylesheet" type="text/css" href="static/css/validation.css" />
        <link rel="stylesheet" type="text/css" href="static/css/reset.css" />
        <link rel="stylesheet" type="text/css" href="static/css/alterPsd.css" />
        <script type="text/javascript" src="static/js/jquery-3.1.1.min.js"></script>
        <script type="text/javascript" src="static/js/jquery.validate.js"></script>
        <script type="text/javascript" src="static/js/validation.js"></script>
        <script type="text/javascript">
        	$(document).ready(function (){
        		$("#btnSbm").click(function (){
        			updatePsd();
        		});
        	});
        	
        	function updatePsd(){
        		$.ajax({
        			type : "post",
        			url : "update_passworld",
        			data : $("#psd").serialize(),
        			success : function(data) {
        				console.log(data);
        				if(data.returnCode=="200"){
        					window.location = data.url;
        				}else if(data.returnCode=="201"){
        					$("#oldPswMsg").text(data.msg);
        				}
        			}
        		});
        	}
        
        </script>
    </head>
    <body>
        <%@include file="../publicpage/shoppublicpage.jsp" %>
        <div class="box">
            <div class="alter-psd">
                <form  id="psd">
                    <div class="form-div">
                        <label  class="form-label" >旧密码<b>*</b></label>
                        <input type="password" name="oldPsw" class="form-input"   />
                        <span id="oldPswMsg"></span>
                    </div>
                    <div class="form-div">
                        <label  class="form-label" >新密码<b>*</b></label>
                        <input type="password" name="newPsw" class="form-input"  id="newPsw" />
                    </div>
                    <div class="form-div">
                        <label  class="form-label" >确认密码<b>*</b></label>
                        <input type="password" name="surePsd" class="form-input" />
                    </div>
                    <div class="btn">
                        <input id="btnSbm" type="button" class="btn1" value="确定"/>       
                    </div>  
                </form>
            </div>
        </div>
    </body>
</html>