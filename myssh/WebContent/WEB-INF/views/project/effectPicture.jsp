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
        <!-- <script src="static/js/city2.js"></script> -->
        <script type="text/javascript" src="static/js/citySelect2.js"></script>
        <script type="text/javascript" src="static/js/messageProtect.js"></script>
        <script type="text/javascript">
	        $(document).ready(function(){
	   			getAllPicInfos();
	   		});
	   		//异步获取信息
	   		function getAllPicInfos(){
	   			$.ajax({
	   				type:"post",
	   				async:false,
	   				url:"list_pro_pics",
	   				success:function(data){
	   					data=eval("("+data+")");
	   					fillPicInfos(data.root);
	   					showDeleWindow();
	   					changePics();
	   				}
	   			});
	   		}
	   		
	   		function fillPicInfos(data){
	   			var s = "<tr><th>图片</th><th>图片名称</th><th>图片说明</th><th>编辑</th></tr>";
				$.each(data,function(v,o){
						var urlStr = o.url;
						//console.log(urlStr);
						var indexNum = urlStr.indexOf(",");
						//console.log(indexNum);
						if(indexNum!=-1){
							urlStr = urlStr.substring(1,indexNum);
							//console.log(urlStr);
						}else{
							urlStr = urlStr.substring(1,urlStr.length-1);
							//console.log(urlStr);
						}
						s+=' <tr><td><img  src="'+urlStr+'"/></td>';
						s+='<td><p>'+o.caption+'</p></td>';
						s+='<td><p>'+o.description+'</p></td>';
						s+='<td><input type="button" class="btn-change" value="修改" data-value="'+o.photoId+'"/>';
						s+='<input type="button" class="btn-cancel"  value="删除" data-value="'+o.photoId+'"/></td></tr>';
				});
			
				if(data.length>0){
					$("#picInfos").html(s);
				}else{
					$("#picInfos").html("<br/><span style='width:10%;height:30px;display:block;margin:0 auto;'>暂无数据</span>");
				}
	   		}
	   		
	   	//效果修改
	   	function changePics(){
	   		$(".btn-change").click(function(){
	   			//后台请求
	   			var id = $(this).data("value");
	   			getThisGroupPics(id);
	   			getThisGroupPicsUrl(id);
	   			popBox();
                $(".popChange").show();
        	})
	   	}
	   	//获取效果图信息
	   	function getThisGroupPics(photoId){
	   		$.ajax({
   				type:"post",
   				async:false,
   				url:"get_current_proPic_info",
   				data:{proPicsId:photoId},
   				success:function(data){
   					data=eval("("+data+")");
   					setThisGroupPicsInfo(data.data);
   				}
   			})
	   	}
	   	//获取效果图url的list
	   	function getThisGroupPicsUrl(id){
	   		$.ajax({
   				type:"post",
   				async:false,
   				url:"get_current_proPic_url_list",
   				data:{proPicsId:id},
   				success:function(data){
   					data=eval("("+data+")");
   					setThisGroupPicsList(data.root);
   				}
   			});
	   	}
	   	
	   	//给效果图修改框的文本域赋值
	   	function setThisGroupPicsInfo(data){
	   		//console.log(data.url);
	   		$("#proPicId").val(data.photoId);
	   		$("#caption").val(data.caption);
	   		$("#description").val(data.description);
	   	}
	   	
	   	function setThisGroupPicsList(data){
	   		$.each(data,function(v,o){
	   			$(".addPic").before("<div class='bigerget' style='background:url(" + o + ") no-repeat center;background-size:cover;'onmouseover='showd(this)' onmouseleave='hided()'>" +
	   	        		"<div class='dustbinUrl' data-value="+v+"></div></div>");
				//console.log(data);
			});
	   		$("#proPicUrl").val(data);
	   		$(".dustbinUrl").click(function(){
	   			var v = $(this).data("value");
	   			data.splice(v,1);
	   			$("#proPicUrl").val(data);
	   			$(this).parent().remove();
	   			
	   		});
	   		
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
	       		$(".yes-btn").click(function(){
	       			$.ajax({
	       				type:"post",
	       				//async:false,
	       				url:"delete_curren_pro_pic_info",
	       				data:{proPicsId:v},
	       				success:function(data){
	       					getAllPicInfos();
	       				}
	       			});
	       			//弹框关闭方法
	       			agree();
	       		});
       		}
/*-----------------------------------------------------------------------------------*/ 
			//图片上传表单提交
			function uploadForm(obj) {
				//formdata表单提交数据
				var form = new FormData();
				form.append("caption", $("#picName").val());
				form.append("description", $("#picDesc").val());
				form.append("isSave", $(obj).val());
				console.log(filesList);
				console.log(array);
				for(var i=0;i<filesList.length;i++){
					if(array.length>0){
						for(var q=0;q<array.length;q++){
							if(i==array[q]){
								break;
							}else{
								form.append("pic", filesList[i]);			
							}
						}
					}else{
						form.append("pic", filesList[i]);
					}
				}
				//后台调用
				$.ajax({
					url : "add_updata_pro_pics",
					dataType : "json",
					type : "post",
					data :form,
					contentType : false,
					processData : false,
					success : function(data) {
						if (data.code == 200) {
							window.location.href=data.url;
						} else if (data.code == 201) {
							window.location.href=data.url;
						}
					},
					error : function() {
						alert("填写有误，请重新填写...");
					}
				});
			}
			
			//图片修改表单提交
			function updatePicForm(obj) {
				//formdata表单提交数据
				var form = new FormData();
				form.append("photoId", $("#proPicId").val());
				form.append("urlArray", $("#proPicUrl").val());
				form.append("caption", $("#picName").val());
				form.append("description", $("#picDesc").val());
				form.append("isSave", $(obj).val());
				console.log(filesList);
				console.log(array);
				for(var i=0;i<filesList.length;i++){
					if(array.length>0){
						for(var q=0;q<array.length;q++){
							if(i==array[q]){
								break;
							}else{
								form.append("pic", filesList[i]);			
							}
						}
					}else{
						form.append("pic", filesList[i]);
					}
				}
				//后台调用
				$.ajax({
					url : "add_updata_pro_pics",
					dataType : "json",
					type : "post",
					data :form,
					contentType : false,
					processData : false,
					success : function(data) {
						if (data.code == 200) {
							window.location.href=data.url;
						} else if (data.code == 201) {
							window.location.href=data.url;
						}
					},
					error : function() {
						alert("填写有误，请重新填写...");
					}
				});
			}
       		
       		
        </script>
    </head>
    <body>
        <%@include file="publicHeader.jsp" %>
        <div class="container">
            <div class="home-messageProtect">
                <p style="font-size:14px;color:#0c95db;"><a href="to_pro_index" style="font-size:12px;color:#0c95db;">首页</a>&nbsp;-&nbsp;案场信息维护</p>
            </div>
            <%@include file="basicSide.jsp" %>
            <div class="main-content">
                 <div class="effect-picture" >
                         <div class="add-picture">
                             <button class="addBtn" id="addPic">添加图片</button>
                         </div>
                         <div class="picture-list">
                             <table id="picInfos">
                            </table>
                         </div>
                    </div>                 
                </div>
            </div>
            <div class="popCon">
                <div class="close"></div>
                <form class="form-box" id="effectionVerifyOne" >
                    <div class="form-div">
                        <label class="form-label">上传图片<b>*</b></label>
                       <div class="single">
                       		
                            <div class="addPic">
                                 <span id="add">+</span>
                                 <input type="file" multiple="multiple" class='picture' onchange="fileupload(this)" name="pic" id="file0">
                            </div>
                        </div>
                    </div>
                    <div class="form-div">
                        <label class="form-label">图片名称<b>*</b></label>
                        <input id="picName" class="form-input" type="text" name="caption" />
                    </div>
                    <div class="form-div">
                        <label class="form-label">图片说明</label>
                        <textarea id="picDesc" name="description"  cols="50" rows="5"></textarea>
                    </div>
                    <div class='btn'>
                        <input name="isSave" type="button" class="btn1" value="保存" onclick="uploadForm(this)" />
                        <input name="isSave" type="button" class="btn2" value="保存并进入户型" onclick="uploadForm(this)"/>
                    </div>
                </form>
            </div>
            <div class="popChange">
                <div class="close"></div>
                <form class="form-box" id="effectionVerifyTwo"  >
                    <div class="form-div">
                    	<input id="proPicId" name="photoId" type="hidden">
                    	<input id="proPicUrl" name="urlArray" type="hidden"/>
                        <label class="form-label">上传图片<b>*</b></label>
                        <div class="single">
                            <div class="addPic">
                                 <span id="add2">+</span>
                                 <input type="file" multiple="multiple" class='picture' onchange="filechange(this)"  name="pic" id="afile0">
                            </div>
                        </div>
                    </div>
                    <div class="form-div">
                        <label class="form-label">图片名称<b>*</b></label>
                        <input id="caption" class="form-input" type="text" name="caption" />
                    </div>
                    <div class="form-div">
                        <label class="form-label">图片说明</label>
                        <textarea id="description" name="description"  cols="50" rows="5"></textarea>
                    </div>
                        <div class="btn" style="margin:50px 100px 10px 250px;">
                        <input name="isSave" type="button" class="btn1" value="保存" onclick="updatePicForm(this)"/>
                    </div>
                </form>
            </div>
            <div class="cancel-box">
                <div class="cancel-msg">
                    <p>你确定要删此条信息吗？</p>
                </div>
                <div class="cancel-sure">
                    <input type="button"  value="是" class="yes-btn" />
                    <input type="button"  value="否" class="no-btn" />
                </div>              
            </div>
              
        </body>
        
    </html>