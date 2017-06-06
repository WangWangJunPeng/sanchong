<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <link  rel="icon" href="static/images/titleLogo.png"  />
        <title>门店后台管理</title>
        <link rel="stylesheet" type="text/css" href="static/css/menLogin.css" />
    </head>
    <body>
        <div class="header-top">
            <img class="top-pic" src="static/images/medilogin.png" alt="" />
            <a href="shop_regs" class="apply">注册门店申请</a>
        </div>
        <div class="login-container">
            <div class="login-box">
                <div class="login-word">
                    <p>门店账号登录</p>
                </div>
                <form action="login" class="form-box" method="post">
                    <div class="form-div">
                        <label for="" class="form-label">账号</label>
                        <input type="text" class="form-input" name="phone"/>
                    </div>
                    <div class="form-div">
                        <label for="" class="form-label">密码</label>
                        <input type="password" class="form-input" name="password"/>
                    </div>
                    <div class="btn">
                        <input type="submit" value="登&nbsp;&nbsp;录" />
                    </div>
                    <input type="hidden" name="sign" value="web"/>
					<input type="hidden" name="r_sign" value="shopowner"/>
                </form>
                <div class="forget">
                    <span>${data}</span>
                    <a href="###">忘记密码？</a>
                </div>
            </div>
        </div>
        <div class="an-footer">
            <div class="footer-msg">
                <img class="left" src="static/images/logofter.png" alt="" />
                <div class="line">
                    
                </div>
                <div class="right">
                    <span class="tel">TEL：0571-8515 2408</span>
                    <span class="email">E-MAIL：service@cntrio.com</span>
                    <div class="copy-right">
                        <span class="cy">Copyright © 2004-2016 杭州三重奏科技有限公司 版权所有</span>
                        <span class="bnum">备案号：浙ICP备16001756号-1</span>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>