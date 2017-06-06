<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <link rel="stylesheet" type="text/css" href="static/css/allLogin.css" />
        <script type="text/javascript" src="static/js/jquery-3.1.1.min.js"></script>
    </head>
    <body>
        <div class="box">
            <div class="topLogo">
               <img src="static/images/logotwo.png" alt="" /> 
            </div>
            <div class="loginBox">
                <div class="loginBtn">
                   <div class="log" style="background: #888990;border-top-left-radius:4px;">
                       <p>门店登录</p>
                   </div>
                   <div class="log" style="border-top-right-radius:4px;">
                       <p>案场登录</p>
                   </div>
                </div>
                
                <div class="loginInput" >
                    <form action="login" method="post">
                        <div class="form-div">
                            <img src="static/images/account.png" alt="" />
                            <input type="text" class="form-input" placeholder="请输入门店账号" name="phone"/>
                        </div>
                        <div class="form-div">
                            <img src="static/images/psd.png" alt="" />
                            <input type="password" class="form-input" placeholder="密码" name="password"/>
                        </div>
                        <div class="loginError">
                            <span>${data}</span>
                        </div>
                        <div class="btn">
                            <input type="submit" value="登&nbsp;&nbsp;录" />
                        </div>
                        <input type="hidden" name="sign" value="web"/>
						<input type="hidden" name="r_sign" value="shopowner"/>
                    </form>
                    <div class="loginDown">
                        <span class="reg"><img src="static/images/reg.png" alt="" /><a href="shop_regs">注册门店</a></span>
                        <span class="forg"><img src="static/images/forget.png" alt="" /><a href="###">忘记密码</a></span>
                    </div>
                </div>



                <div class="loginInput" style="display:none;">
                    <form action="login" method="post">
                        <div class="form-div">
                            <img src="static/images/account.png" alt="" />
                            <input type="text" class="form-input" placeholder="请输入案场助理账号" name="phone"/>
                        </div>
                        <div class="form-div">
                            <img src="static/images/psd.png" alt="" />
                            <input type="password" class="form-input" placeholder="密码" name="password"/>
                        </div>
                        <div class="loginError">
                            <span>${data}</span>
                        </div>
                        <div class="btn">
                            <input type="submit" value="登&nbsp;&nbsp;录" />
                        </div>
                        <input type="hidden" name="sign" value="web"/>
			  			<input type="hidden" name="r_sign" value="engineer"/>
                    </form>
                    <div class="loginDown">
                        <span class="forg"><img src="static/images/forget.png" alt="" /><a href="###">忘记密码</a></span>
                    </div>
                </div>
            </div>
        </div>
        <script type="text/javascript">
            $(document).ready(function(){
                $(".log").click(function(){
                    var num = $(this).index();
              
                    $(this).css("background","#888990").siblings().css('backgroundColor', '#52545c');
                   

                    $(".loginInput").eq(num).show().siblings(".loginInput").hide();
                })
            })

        </script>
    </body>
</html>