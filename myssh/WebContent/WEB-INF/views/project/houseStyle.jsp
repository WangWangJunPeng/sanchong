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
				getAllHouseStyleData();
			});
	       	function getAllHouseStyleData(){
	       		$.ajax({
	       			type:"post",
	       			async:false,
	       			url:"list_all_house_type",
	       			success:function(data){
	       				data = eval("("+data+")");
	       				fillHouseStyleInfo(data.root);
	       				showDeleWindow();
	       				updateHouseType();
	       			}
	       		});
	       	}
	       	
	       	function fillHouseStyleInfo(data){
	       		var s = "<tr><th>户型图</th><th>户型名称</th><th>面积</th><th>房型</th><th>户型说明</th><th>操作</th></tr>";
	       		$.each(data,function(v,o){
	       			s+='<tr><td><img  src="'+o.photoURL+'" alt="" /></td>';
	       			s+='<td><p>'+o.caption+'</p></td>';
	       			s+= '<td><p>'+o.area+'&nbsp;㎡</p></td>';
                    s+='<td><p>'+o.housType+'</p></td>';
                    s+='<td><p>'+o.housTypeDesc+' </p></td>';
                    s+='<td><input type="button" class="btn-change" value="修改" data-upvalue="'+o.houseTypeId+'" />';
                    if(o.isUse!="yes"){
                    	s+='<input type="button" class="btn-cancel" value="删除" data-value="'+o.houseTypeId+'"/></td></tr>'; 
                    }
	       		});
	       		
	       		if(data.length>0){
					$("#houseStyleInfo").html(s);
				}else{
					$("#houseStyleInfo").html("<br/><span style='width:10%;height:30px;display:block;margin:0 auto;'>暂无数据</span>");
				}
	       	}
	       
	      	//删除选择弹框
      	 	function showDeleWindow(v){
    			$(".btn-cancel").click(function(){
    			   var num = $(this).data("value");
				   $(".cancel-box").show();
				   enterDelte(num);
				});
      		}
	      	
       		//删除后台ajax调用程序
       		function enterDelte(n){
	       		$(".yes-btn").click(function(){
	       			$.ajax({
	       				type:"post",
	       				//async:false,
	       				url:"delete_house_type_byId",
	       				data:{hId:n},
	       				success:function(data){
	       					getAllHouseStyleData();
	       				}
	       			});
	       			//弹框关闭方法
	       			agree();
	       		});
       		}
       		
       		//修改户型信息
       		function updateHouseType(){
	       		$(".btn-change").click(function(){
	       			popBox();
	       			$(".popChangeh").show()
	       			$.ajax({
	       				type:"post",
	       				//async:false,
	       				url:"get_current_house_type",
	       				data:{hId:$(this).data("upvalue")},
	       				success:function(data){
	       					data = eval("("+data+")");
							fillInForm(data.data);
	       				}
	       			});
	       		});
       		}
       		
       		//显示修改信息
       		function fillInForm(data){
       			if(data.hasOwnProperty("houseTypeId")){
       				$("#newImg").attr('src',data.photoURL);
       				$("#houseTypeId").val(data.houseTypeId);
       				$("#imgUplode").val(data.photoURL);
					$("#caption").val(data.caption);
					$("#area").val(data.area);
					$("#housTypeDesc").val(data.housTypeDesc);
					var str = data.housType.split(" ");
					$(str).each(function(index){
						if(this!=null && this!=""){
							//alert(this.substring(1));
							if(this.substring(1)=="房"){
								$("#home").find("option[value="+this+"]").prop("selected",true);
							}
							if(this.substring(1)=="厅"){
								$("#hall").find("option[value="+this+"]").prop("selected",true);
							}
							if(this.substring(1)=="卫"){
								$("#toilet").find("option[value="+this+"]").prop("selected",true);
							}
						}
					});
       			}
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
           <!-- <div class="side-bar">
                <ul>
                    <li><a href="">基本信息</a></li>
                    <li><a href="">效果图</a></li>
                    <li><a href="" style="color:#199ed8">户型</a></li>
                    <li><a href="">银行账号</a></li>
                    <li><a href="">带看业务定义</a></li>
                    <li><a href="">预售证管理</a></li>
                    <li><a href="">认购规则</a></li>
                </ul>
            </div>  -->
            <div class="main-content">
                   <div class="house-type">
                         <div class="add-house">
                             <button class="addBtn" id="addHouse">新增户型</button>
                         </div>
                         <div class="house-list">
                             <table id="houseStyleInfo" style="table-layout:fixed;">
                               <!--   <tr>
                                     <th>户型图</th>
                                     <th>户型名称</th>
                                     <th>面积</th>
                                     <th>房型</th>
                                     <th>户型说明</th>
                                     <th>操作</th>                                     
                                 </tr>
                                 <tr>
                                     <td>
                                         <img  src="" alt="" />
                                     </td>
                                     <td>
                                         <p>A3（两室一厅一卫）</p>
                                     </td>
                                     <td>
                                         <p>三房一厅两卫</p>
                                     </td>
                                     <td>
                                          <p>89m²</p>
                                     </td>
                                     <td>
                                         <p>说明说明说明说明 说明说明说明说明 说明说明说明说明 说明说明 </p>
                                     </td>
                                     <td>
                                         <input type="button" class="btn-change" value="修改" />
                                         <input type="button" class="btn-cancel" value="删除" />
                                     </td>
                                 </tr> -->
                            </table>
                         </div>
                    </div>       
                </div>     
            </div>
            <div class="popConh">
                <div class='close'></div>
                <form class='form-box' action='add_house_type' method="post" enctype="multipart/form-data" id="houseOneVerify">
                    <div class='form-div'>
                        <label class='form-label'>户型图<b>*</b></label>
                        <div class="up-pic">
                                <img src="" id="oldImg" > 
                                <span>+</span>
                                <input type="file" id="oFile" onchange="oldFile()" name="pic" multiple="true" class="form-input file-pic"/>
                        </div>
                    </div>
                    <div class='form-div'>
                        <label class='form-label'>户型名称<b>*</b></label>
                        <input class='form-input' type='text' name='caption' />
                    </div>
                    <div class='form-div'>
                        <label class='form-label'>面积<b>*</b></label>
                        <input class='form-input' type='text' name='area' />
                        <span>平方米</span>
                    </div>
                    <div class='form-div'>
                        <label class='form-label'>房型</label>
                        <select name='home'>
                            <option value=''>房间数量</option>
                            <option value='一房'>一房</option>
                            <option value='两房'>两房</option>
                            <option value='三房'>三房</option>
                            <option value='四房'>四房</option>
                            <option value='五房'>五房</option>
                            <option value='六房'>六房</option>
                        </select>
                        <select name='hall'>
                            <option value=''>厅数量</option>
                            <option value='一厅'>一厅</option>
                            <option value='两厅'>两厅</option>
                            <option value='三厅'>三厅</option>
                            <option value='四厅'>四厅</option>
                            <option value='五厅'>五厅</option>
                            <option value='六厅'>六厅</option>
                        </select>
                        <select name='toilet'>
                            <option value=''>卫生间数量</option>
                            <option value='一卫'>一卫</option>
                            <option value='两卫'>两卫</option>
                            <option value='三卫'>三卫</option>
                            <option value='四卫'>四卫</option>
                            <option value='五卫'>五卫</option>
                            <option value='六卫'>六卫</option>
                        </select>
                    </div>
                    <div class='form-div'>
                        <label class='form-label'>户型说明</label>
                        <textarea name='housTypeDesc'  cols='50' rows='5'></textarea>
                    </div>
                    <div class='btn' style='margin:50px 200px 10px 150px;'>
                        <input name="isSave" type='submit' class='btn1' value='保存' />
                        <input name="isSave" type='submit' class='btn2' value='保存并进入银行账号' />
                    </div>
                </form>
            </div>
            <!-- 修改户型 -->
            <div class="popChangeh">
                <div class='close'></div>
                <form class='form-box' action='add_house_type' method="post" enctype="multipart/form-data" id="houseTwoVerify">
                    <div class='form-div'>
                        <label class='form-label'>户型图<b>*</b></label>
                        <div class="up-pic">
                                <img src="" id="newImg" > 
                                <span>+</span>
                                <input type="file" id="nFile" onchange="newFile()" name="pic" multiple="true" class="form-input file-pic"/>
                                <input type="hidden" id="houseTypeId" name="houseTypeId">
                                <input type="hidden" id="imgUplode" name="photoURL">
                        </div>
                    </div>
                    <div class='form-div'>
                        <label class='form-label'>户型名称<b>*</b></label>
                        <input class='form-input' type='text' name='caption' id="caption"/>
                    </div>
                    <div class='form-div'>
                        <label class='form-label'>面积<b>*</b></label>
                        <input class='form-input' type='text' name='area' id="area"/>
                        <span>平方米</span>
                    </div>
                    <div class='form-div'>
                        <label class='form-label'>房型</label>
                        <select name='home' id="home">
                            <option value=''>房间数量</option>
                            <option value='一房'>一房</option>
                            <option value='两房'>两房</option>
                            <option value='三房'>三房</option>
                            <option value='四房'>四房</option>
                            <option value='五房'>五房</option>
                            <option value='六房'>六房</option>
                        </select>
                        <select name='hall' id="hall">
                            <option value=''>厅数量</option>
                            <option value='一厅'>一厅</option>
                            <option value='两厅'>两厅</option>
                            <option value='三厅'>三厅</option>
                            <option value='四厅'>四厅</option>
                            <option value='五厅'>五厅</option>
                            <option value='六厅'>六厅</option>
                        </select>
                        <select name='toilet' id="toilet">
                            <option value=''>卫生间数量</option>
                            <option value='一卫'>一卫</option>
                            <option value='两卫'>两卫</option>
                            <option value='三卫'>三卫</option>
                            <option value='四卫'>四卫</option>
                            <option value='五卫'>五卫</option>
                            <option value='六卫'>六卫</option>
                        </select>
                    </div>
                    <div class='form-div'>
                        <label class='form-label'>户型说明</label>
                        <textarea name='housTypeDesc'  cols='50' rows='5' id="housTypeDesc"></textarea>
                    </div>
                    <div class='btn' style='margin:50px 120px 10px 230px;'>
                        <input name="isSave" type='submit' class='btn1' value='保存' />
                    </div>
                </form>
            </div>
            <div class="cancel-box">
                <div class="cancel-msg">
                    <p>你确定杨删除此条信息吗？</p>
                </div>
                 <div class="cancel-sure">
                    <input type="button"  value="是" class="yes-btn" />
                    <input type="button"  value="否" class="no-btn" />
                 </div>        
            </div>
           
        </body>
        <script type="text/javascript">
            function changeFile() {
                var windowURL = window.URL || window.webkitURL;
                var loadImg = windowURL.createObjectURL(document.getElementById('file').files[0]);
                document.getElementById('img').setAttribute('src',loadImg);
            }    
        </script>
    </html>