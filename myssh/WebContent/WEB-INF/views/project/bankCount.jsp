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
        <script type="text/javascript" src="stacit/js/citySelect2.js"></script>
        <script type="text/javascript" src="static/js/messageProtect.js"></script>
       	<script type="text/javascript">
       		$(document).ready(function(){
       			getAllDateBankCounts();
       			//enterDelte();
       		});
       		//异步获取信息
       		function getAllDateBankCounts(){
       			$.ajax({
       				type:"post",
       				//async:false,
       				url:"get_bank_count_infos",
       				success:function(data){
       					data=eval("("+data+")");
       					fillBankInfo(data.root);
       					showDeleWindow();
       					//console.log(data.root);
       				},
       				/* error:function(){
       					alert("信息获取失败");
       				} */
       			});
       		}
       		//遍历数据，在页面显示
       		function fillBankInfo(data){
				var s = "<tr><th>银行名称</th><th>账号</th><th>账号名称</th><th>账户说明</th><th>操作</th></tr>";
				$.each(data,function(v,o){
						//console.log(o);
						s+='<tr><td><p>'+o.bankName+'</p></td>';
						s+='<td><p>'+o.accountNo+'</p></td>';
						s+='<td><p>'+o.accountName+'</p></td>';
						s+='<td><p>'+o.description+'</p></td>';
						s+='<td><input type="button" class="btn-cancel" value="删除" data-value="'+o.bankId+'"/></td></tr>';
				});
			
				if(data.length>0){
					$("#bankInfo").html(s);
				}else{
					$("#bankInfo").html("<br/><span style='width:10%;height:30px;display:block;margin:0 auto;'>暂无数据</span>");
				}
			}
       		//删除选择弹框
       		function showDeleWindow(){
     			$(".btn-cancel").click(function(){
 				   $(".cancel-box").show();
 				   enterDelte($(this).data("value"));
 				});
       		}
       		//删除后台ajax调用程序
       		function enterDelte(v){
	       		$("#enter-del").click(function(){
	       			$.ajax({
	       				type:"post",
	       				//async:false,
	       				url:"to_delete_bank_count",
	       				data:{bankId:v},
	       				success:function(data){
	       					getAllDateBankCounts();
	       				}
	       			});
	       			//弹框关闭方法
	       			agree();
	       		});
       		}
       	</script>
    </head>
    <body>
       <!--  <div class="header">
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
                    <li><a href="" style="color:#199ed8">银行账号</a></li>
                    <li><a href="">带看业务定义</a></li>
                    <li><a href="">预售证管理</a></li>
                    <li><a href="">认购规则</a></li>
                </ul>
            </div>  -->
            <div class="main-content">
                 <div class="bank-count">
                         <div class="add-count">
                             <button class="add-btn" id="addBtn">新增银行账号</button>
                         </div>
                         <div  class="count-list">
                             <table id="bankInfo">
                                 <!-- <tr>
                                     <th>银行名称</th>
                                     <th>账号</th>
                                     <th>账号名称</th>
                                     <th>账户说明</th>
                                     <th>操作</th>                                     
                                 </tr>
                                 <div></div>
                                 <tr>
                                     <td>
                                         <p>中国建设银行</p>
                                     </td>
                                     <td>
                                       <p>555555555</p>
                                     </td>
                                     <td>
                                          <p>难受香菇</p>
                                     </td>
                                     <td>
                                        <p>空间上的数据古斯啊</p>
                                     </td>
                                     <td>                                        
                                         <input type="button" class="btn-cancel" value="删除" />
                                     </td>
                                 </tr> 
                                 </div> -->
                             </table>
                         </div>
                    </div>       
            </div> 
        </div>
        
        <div class="popCon">
            <div class="close"></div>
            <form class="form-box" action="to_addBank_count" method="post" id="bankVerify">
                <div class="form-div">
                    <label class="form-label">银行名称</label>
                    <input class="form-input" type="text" name="bankName" />
                </div>
                <div class="form-div">
                    <label class="form-label">账号</label>
                    <input class="form-input" type="text" name="accountNo" />
                </div>
                <div class="form-div">
                    <label class="form-label">账户名称</label>
                    <input class="form-input" type="text" name="accountName" />
                </div>
                <div class="form-div">
                    <label class="form-label">账户说明</label>
                    <textarea name="description"  cols="50" rows="5"></textarea>
                </div>
                <div class="btn">
                    <input type="submit" class="btn1" name="isSave" value="保存" style="border:0;background-color:#169bd5;cursor:pointer;height:40px;color:#ffffff;"/>
                    <input type="submit" class="btn2" name="isSave" value="保存并进入带看业务定义" style="border:0;background-color:#169bd5;cursor:pointer;height:40px;color:#ffffff;"/>
                </div>
            </form>
        </div>
        
        <div class="cancel-box">
            <div class="cancel-msg">
                <p>你确定杨删除此条信息吗？</p>
            </div>
             <div class="cancel-sure">
                <input id="enter-del" type="button"  value="是" class="yes-btn" />
                <input type="button"  value="否" class="no-btn"/>
             </div>   
        </div>
        
        </body>
    </html>