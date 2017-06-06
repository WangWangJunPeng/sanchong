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
				getAllIdManageData();
			});
	        function getAllIdManageData(){
	        	$.ajax({
	       			type:"post",
	       			async:false,
	       			url:"list_id_manage_infos",
	       			success:function(data){
	       				data = eval("("+data+")");
	       				fillIdManageInfo(data.root);
	       				showDeleWindow();
	       				updateIdManage();
	       			}
	       		});
	        }
	        function fillIdManageInfo(data){
        		var s = "<tr><th>预售证许可证图片</th><th>预售证号</th><th>操作</th></tr>";
	        	$.each(data,function(v,o){
	        		s+='<tr><td><img  src="'+o.picMap.path+'" alt="" /></td>';
	        		s+='<td><p>'+o.picMap.imNum+'</p></td>';
	        		s+='<td><input type="button" class="btn-change" value="修改" data-upvalue="'+v+'" />';
	        		if(o.isUsed!="yes"){
	        			s+='<input type="button" class="btn-cancel"  value="删除" data-value="'+v+'"/></td></tr>';	        			
	        		}
	        	});
	        	
	        	if(data.length>0){
					$("#idManageInfo").html(s);
				}else{
					$("#idManageInfo").html("<br/><span style='width:10%;height:30px;display:block;margin:0 auto;'>暂无数据</span>");
				}
	        }
	        
	       //删除选择弹框
      	 	function showDeleWindow(){
    			$(".btn-cancel").click(function(){
    			   var num = $(this).data("value");
				   $(".cancel-box").show();
				   idManageDelte(num);
				});
      		}
	       //删除后台调用
	       function idManageDelte(num){
	    	   $(".yes-btn").click(function(){
	    		   $.ajax({
		    		   type:"post",
		    		   async:false,
		    		   url:"delete_id_manage_pro_pic_index",
		    		   data:{index:num},
		    		   success:function(data){
		    			   getAllIdManageData();
		    		   }
		    	   });
	    		 //弹框关闭方法
	       		 agree();
	    	   });
	       }
	      
      	 	//修改户型信息
       		function updateIdManage(){
	       		$(".btn-change").click(function(){
	       			popBox();
	       			$(".popChangeh").show();
	       			var index = $(this).data("upvalue");
	       			$("#index").val(index);
	       			$.ajax({
	       				type:"post",
	       				//async:false,
	       				url:"curren_id_manage_info",
	       				data:{index:index},
	       				success:function(data){
	       					data = eval("("+data+")");
							fillInIdManageForm(data.data);
							//console.log(data.data);
	       				}
	       			});
	       		});
       		}
      	 	//修改信息展示
      	 	function fillInIdManageForm(data){
      	 		$("#newImg").attr('src',data.path);
      	 		$("#idManageNum").val(data.imNum);
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
             <!-- <div class="side-bar">
                <ul>
                    <li><a href="">基本信息</a></li>
                    <li><a href="">效果图</a></li>
                    <li><a href="">户型</a></li>
                    <li><a href="" style="color:#199ed8">银行账号</a></li>
                    <li><a href="">带看业务定义</a></li>
                    <li><a href="">预售证管理</a></li>
                    <li><a href="">认购规则</a></li>
                </ul>
            </div> --> 
            <div class="main-content">
                 <div class="manage" >
                        <div class="add-id">
                             <button class="addBtn" id="addId">添加预售证</button>
                         </div>
                         <div class="id-list">
                             <table id="idManageInfo">
                                <!--  <tr>
                                     <th>预售证许可证图片</th>
                                     <th>预售证号</th>
                                     
                                     <th>操作</th>                                 
                                 </tr>
                                 <tr>
                                     <td>
                                         <img  src="" alt="" />
                                     </td>
                                     <td>
                                         <p>难受香菇</p>
                                     </td>
                                   
                                     <td>
                                         <input type="button" class="btn-change" value="修改" />
                                         <input type="button" class="btn-cancel"  value="删除" />
                                     </td>
                                 </tr> -->
                            </table>
                         </div>
                    </div>            
                </div>
            </div>
            <div class="popConh">
                <div class="close"></div>                
                <form action="add_or_update_id_mange_for_pro" method="post" class="form-box" enctype="multipart/form-data" id="idVerify" >
                        <div class="form-div">
                            <label  class="form-label">预售许可证图片<b>*</b></label>
                            
                            <div class="up-pic">
                                <img src="" id="oldImg" > 
                                <span>+</span>
                                <input type="file" id="oFile" onchange="oldFile()" name="pic" multiple="true" class="form-input file-pic"/>
                            </div>
                                                                              
                        </div>
                        
                        <div class="form-div" style="margin-top:30px;">
                            <label class="form-label">预售证号：<b>*</b></label>
                            <input type="text" class="form-input " name="idManageNum"  />
                        </div>      
                        <div class="btn">
                            <input name="isSave" type="submit" class="btn1" value="保存" />
                            <input name="isSave" type="submit" class="btn2" value="保存并进入认购规则" />
                        </div>                     
                </form>
            </div>
             <div class="popChangeh">
                <div class="close"></div>                
                <form action="updata_curren_idmanage_info" method="post" class="form-box" enctype="multipart/form-data" id="idVerifyTwo" >
                        <div class="form-div">
                            <label  class="form-label">预售许可证图片<b>*</b></label>
                            <input id="index" name="index" type="hidden"/>
                            <div class="up-pic">
                                <img src="" id="newImg" > 
                                <span>+</span>
                                <input type="file" id="nFile" onchange="newFile()" name="pic" multiple="true" class="form-input file-pic"/>
                            </div>
                                                                              
                        </div>
                        
                        <div class="form-div" style="margin-top:30px;">
                            <label class="form-label">预售证号：<b>*</b></label>
                            <input type="text" class="form-input " name="idManageNum" id="idManageNum"  />
                        </div>      
                        <div class="btn">
                            <input type="submit" class="btn1" value="保存" />
                        </div>                     
                </form>
            </div>
            <div class="cancel-box">
                <div class="cancel-msg">
                    <p>你确定要删除此条信息吗？</p>
                </div>
                 <div class="cancel-sure">
                    <input type="button"  value="是" class="yes-btn" />
                    <input type="button"  value="否" class="no-btn" />
                 </div>        
            </div>  
           
        </body>
       
    </html>