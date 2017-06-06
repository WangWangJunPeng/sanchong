<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link  rel="icon" href="static/images/titleLogo.png"  />
        <title>案场助理个人中心-案场信息维护</title>
        <link rel="stylesheet" type="text/css" href="static/css/messageProtect.css" />
         <link rel="stylesheet" type="text/css" href="static/css/validation.css" />
        <link rel="stylesheet" type="text/css" href="static/css/reset.css" />

        <link rel="stylesheet" type="text/css" href="static/lib/laydate/need/laydate.css" />
        <script type="text/javascript" src="static/js/jquery-3.1.1.min.js"></script>
         <script type="text/javascript" src="static/js/jquery.validate.js"></script>
         <script type="text/javascript" src="static/js/validation.js"></script>
        <script type="text/javascript" src="static/lib/laydate/laydate.js"></script>
        <script src="static/js/city2.js"></script>
        <script type="text/javascript" src="static/js/citySelect2.js"></script>
        <script type="text/javascript" src="static/js/messageProtect.js"></script>
        <script type="text/javascript">
	        $(document).ready(function() {
				getGudeInfo();
			});
			function getGudeInfo(){
				$.ajax({
					type : "post", 
					async : false,
					url:"get_current_pro_guide_info",
					success:function(data){
						data = eval("("+data+")");
						fillInGuide(data.data);
					},
					/* error:function(){
						alert("信息获取失败");
					} */
				});
			}
			function fillInGuide(data){
				if(data.hasOwnProperty("projectId")){
					$("#projectId").val(data.projectId);
					if(data.isDaiKan!=1){
						$("#checkIsDaiKan").prop("checked",false);
					}
					
					$("#daiKanMoney").val(data.daiKanMoneyStr);
					$("#fenXiaoMoney").val(data.fenXiaoMoneyStr);
					$("#validDays").val(data.validDays);
					$("#custormerProtectDays").val(data.custormerProtectDays);
					if(data.isYiDi!=1){
						$("#checkIsYiDi").prop("checked",false);
					}
					$("#yiDiSalesCommission").val(data.yiDiSalesCommissionStr);
					$("#yiDiValidity").val(data.yiDiValidity);
					if(data.isFast!=1){
						$("#checkIsFast").prop("checked",false);
					}
					$("#description").val(data.description);
				}
			}
        </script>
    </head>
    <body>
      <!--   <div class="header">
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
        <%@include file="publicHeader.jsp" %>
        <div class="container">
            <div class="home-messageProtect">
                <p style="font-size:14px;color:#0c95db;"><a href="to_pro_index" style="font-size:12px;color:#0c95db;">首页</a>&nbsp;-&nbsp;案场信息维护</p>
            </div>
            <%@include file="basicSide.jsp" %>
            <!--  <div class="side-bar">
                <ul>
                    <li><a href="">基本信息</a></li>
                    <li><a href="">效果图</a></li>
                    <li><a href="">户型</a></li>
                    <li><a href="">银行账号</a></li>
                    <li><a href="" style="color:#199ed8">带看业务定义</a></li>
                    <li><a href="">预售证管理</a></li>
                    <li><a href="">认购规则</a></li>
                </ul>
            </div> --> 
            <div class="main-content">
                <div class="service-definition" >
                        <form class="form-box" action="add_updata_pro_guide" method="post" id="serviceVerify">
                            <div class="form-div">
                                <label class="form-label">是否支持带看</label>
                                <input id="projectId" name="projectId" type="hidden"/>
                                <input id="checkIsDaiKan" type="checkbox" class="form-input " checked="checked" name="checkIsDaiKan"  />
                                <span>支持带看</span>
                            </div>
                            <div class="form-div">
                                <label class="form-label">带看佣金<b>*</b></label>
                                <input type="text" class="form-input money" name="daiKanMoney" id="daiKanMoney"/>
                                <span>%</span>
                            </div>
                            <div class="form-div">
                                <label class="form-label">分销佣金<b>*</b></label>
                                <input type="text" class="form-input money" name="fenXiaoMoney" id="fenXiaoMoney"/>
                                <span>%</span>
                            </div>
                            <div class="form-div">
                                <label class="form-label">有效天数<b>*</b></label>
                                <input type="text" class="form-input effect-day" name="validDays" id="validDays"/>
                                <span>天</span>
                            </div>
                            <div class="form-div">
                                <label class="form-label">客户保护期<b>*</b></label>
                                <select name="custormerProtectDays"  class="protect-date" id="custormerProtectDays">
                                     <option value="1">1个月</option>
                                     <option value="2">2个月</option>
                                     <option value="3">3个月</option>
                                     <option value="4">4个月</option>
                                     <option value="5">5个月</option>
                                     <option value="6">6个月</option>
                                     <option value="7">7个月</option>
                                     <option value="8">8个月</option>
                                     <option value="9">9个月</option>
                                     <option value="10">10个月</option>
                                     <option value="11">11个月</option>
                                     <option value="12">12个月</option>
                                </select>
                            </div>
                            <div class="form-div">
                                <label class="form-label">支持异地销售</label>
                                <input type="checkbox" class="form-input" checked="checked" name="checkIsYiDi" id="checkIsYiDi"/>
                                <span>支持异地销售</span>
                            </div>
                            <div class="form-div">
                                <label class="form-label">异地销售佣金<b>*</b></label>
                                <input type="text" class="form-input money" name="yiDiSalesCommission" id="yiDiSalesCommission"/>
                                <span>%</span>
                            </div>
                            <div class="form-div">
                                <label class="form-label">异地报备有效期<b>*</b></label>
                                <input type="text" class="form-input effect-day" name="yiDiValidity" id="yiDiValidity"/>
                                <span>天</span>
                            </div>
                            <div class="form-div quick-pay">
                                <input type="checkbox"  checked="checked" name="checkIsFast" id="checkIsFast"/>
                                <span>快速结佣</span>
                     
                            </div>
                            <div class="form-div"> 
                                <label  class="form-label">说明</label>
                                <textarea name="description"  cols="50" rows="5" id="description"></textarea>
                            </div>
                             <div class="btn">
                                <input name="isSave" type="submit" class="btn1" value="保存" />
                                <input name="isSave" type="submit"  class="btn2" value="保存并进入预售证管理" />
                            </div> 
                        </form>
                    </div>       
            </div>
            </div>  
           
               
        </body>
    </html>