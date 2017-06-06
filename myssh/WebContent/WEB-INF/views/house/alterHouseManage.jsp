<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link  rel="icon" href="static/images/titleLogo.png"  />
        <title>案场助理个人中心-案场信息维护</title>
        <link rel="stylesheet" type="text/css" href="static/css/houseManage.css" />
         <link rel="stylesheet" type="text/css" href="static/css/validation.css" />  
        <link rel="stylesheet" type="text/css" href="static/css/reset.css" />
        <link rel="stylesheet" type="text/css" href="static/lib/laydate/need/laydate.css" />
        <script type="text/javascript" src="static/js/jquery-3.1.1.min.js"></script>
         <script type="text/javascript" src="static/js/validation.js"></script>
        <script type="text/javascript" src="static/lib/laydate/laydate.js"></script>
        <script src="static/js/citsy2.js"></script>
        <script type="text/javascript" src="static/js/layer.js"></script>
        <script type="text/javascript" src="static/js/citySelect2.js"></script>
        <script type="text/javascript" src="static/js/houseManage.js"></script>
        <script type="text/javascript">
	        $(document).ready(function(){
	   			getHouseTypeMenus();
	   			getPresalePermissionMenus();
	   			getIsOpen();
	   			//getYSZMenu();
	        	getHouseTypeMenu();
	        	getZXBZMenu();
	   		});
	        //户型菜单
	        function getHouseTypeMenus(){
	        	$.ajax({
	   				type:"post",
	   				url:"menu_list_house_type",
	   				success:function(data){
	   					data=eval("("+data+")");
	   					initHouseType(data.root);
	   				}
	   			});
	        }
	        function initHouseType(data){
				$.each(data,function(rowNum,o){
					$("#htMenu").append('<option value="'+o.htId+'">'+o.caption+'</option>');
				});
				getHXMenu();
			}
	        //预售证菜单
	        function getPresalePermissionMenus(){
	        	$.ajax({
	   				type:"post",
	   				url:"list_id_manage_infos",
	   				success:function(data){
	   					data=eval("("+data+")");
	   					initPresalePermission(data.root);
	   				}
	   			});
	        }
	        function initPresalePermission(data){
				$.each(data,function(rowNum,o){
					$("#ppMenu").append('<option value="'+o.picMap.imNum+'">'+o.picMap.imNum+'</option>');
				});
				getYSZMenu();
			}
	        function getIsOpen(){
	        	var open = $("#hidIsOpen").val();
	        	if(open==1){
	        		$("#checkIsOpen").prop("checked",true);
	        	}
	        }
	        
	        
	        //预售证
	        function getYSZMenu(){
	        	var v = $("#hiddenYSId").val();
	        	$("#ppMenu").find("option[value="+v+"]").prop("selected",true);
	        }
	        //房源类型
	        function getHouseTypeMenu(){
	        	var v = $("#hkId").val();
	        	$("#selectHKId").find("option[value="+v+"]").prop("selected",true);
	        }
	        //户型
	        function getHXMenu(){
	        	var v = $("#hiddenHTId").val();
	        	$("#htMenu").find("option[value="+v+"]").prop("selected",true);
	        }
	     	//装修标准
	        function getZXBZMenu(){
	        	var v = $("#hiddDecorationStandardId").val();
	        	$("#decorationStandardId").find("option[value="+v+"]").prop("selected",true);
	        }
        </script>
    </head>
    <body>
        <!-- <div class="header">
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
        <%@include file="../publicpage/publicHeader.jsp" %>
        <div class="container">
            <div class="home-houseManageAdd">
                <p style="font-size:14px;color:#0c95db;"><a href="to_pro_index" style="font-size:14px;color:#0c95db;">首页</a>&nbsp;-&nbsp;<a href="to_house_manage_page" style="font-size:14px;color:#0c95db;">房源管理</a>&nbsp;-&nbsp;修改房源</p>
            </div>
            <div class="add-list">
                <form action="addHouse" method="post" class="form-box" id="alertHouseVerify">
                	<input  id="houseInfo" type="hidden" value="${house}"/>
                	<input type="hidden" name="houseNum" class="form-input alike" value="${house.houseNum}"/>
                    <div class="form-div">
                        <label  class="form-label" >房号<b>*</b></label>
                        <input type="text" name="houseNo" class="form-input alike" value="${house.houseNo}"/>
                    </div>
                    <div class="form-div">
                    	<input id="hiddenYSId" type="hidden" value="${house.presalePermissionInfo}">
                        <label  class="form-label" >预售证号<b>*</b></label>
                        <select id="ppMenu" name="presalePermissionInfo"  class="sale-id">
                        </select>
                    </div>
                    <div class="form-div">
                        <label  class="form-label" >区域号<b>*</b></label>
                        <input type="text" name="district" class="form-input alike" value="${house.district}"/>
                    </div>
                    <div class="form-div">
                        <label  class="form-label" >楼栋号<b>*</b></label>
                        <input type="text" name="buildingNo" class="form-input alike " value="${house.buildingNo}"/>
                        <span>栋</span>
                    </div>
                    <div class="form-div">
                    	<input id="hkId" type="hidden" value="${house.houseKind}">
                        <label class="form-label" >房源类型<b>*</b></label>
                        <select id="selectHKId" name="houseKind"  class="house-style">
                            <option value="0">公寓</option>
                            <option value="1">排屋</option>
                            <option value="2">独栋</option>
                            <option value="3">商住两用</option>
                            <option value="4">办公室</option>
                            <option value="5">酒店式公寓</option>
                            <option value="6">商铺</option>
                            <option value="7">车位</option>
                            <option value="8">车库</option>
                            <option value="9">储藏室</option>
                        </select>
                    </div>
                    <div class="form-div">
                        <label  class="form-label" >单元<b>*</b></label>
                        <input type="text" name="unit" class="form-input same" value="${house.unit}"/>
                    </div>
                    <div class="form-div">
                        <label  class="form-label" >楼层<b>*</b></label>
                        <input type="text" name="floor" class="form-input same" value="${house.floor}"/>
                    </div>
                    <div class="form-div">
                        <label  class="form-label" >朝向<b>*</b></label>
                        <input type="text" name="direct" class="form-input same" value="${house.direct}"/>
                    </div>
                    <div class="form-div">
                        <label  class="form-label" >建筑面积<b>*</b></label>
                        <input type="text" name="buildArea" class="form-input same" value="${house.buildAreaStr}"/>
                        <span>平方米</span>
                    </div>
                    <div class="form-div">
                        <label  class="form-label" >使用面积<b>*</b></label>
                        <input type="text" name="usefulArea" class="form-input same" value="${house.usefulAreaStr}"/>
                        <span>平方米</span>
                    </div>
                    <div class="form-div">
                        <label  class="form-label" >列表价<b>*</b></label>
                        <input type="text" name="listPrice" class="form-input same" value="${house.listPriceStr}" id="listPrice"/>
                        <span>元</span>
                    </div>
                    <div class="form-div">
                        <label  class="form-label" >中介供货价<b>*</b></label>
                        <input type="text" name="shopPrice" class="form-input same" value="${house.shopPriceStr}" id="shopPrice"/>
                        <span>元</span>
                    </div>
                    <div class="form-div">
                        <label  class="form-label" >底价<b>*</b></label>
                        <input type="text" name="minimumPrice" class="form-input same" value="${house.minimumPriceStr}" id="minimumPrice"/>
                        <span>元</span>
                    </div>
                    
                    <div class="form-div">
                    	<input id="hiddenHTId" type="hidden" value="${house.houseTypeId}">
                        <label  class="form-label" >户型<b>*</b></label>
                        <select id="htMenu" name="houseTypeId"  class="sale-id">
                        </select>
                    </div>
                    <div class="form-div">
                    	<input id="hiddDecorationStandardId" type="hidden" value="${house.decorationStandard}">
                        <label  class="form-label" >装修标准<b>*</b></label>
                        <select id="decorationStandardId" name="decorationStandard"  class="sale-id">
                            <option value="0">毛坯</option>
                            <option value="1">普通装修</option>
                            <option value="2">精装修</option>
                            <option value="3">家具全配</option>
                            <option value="4">家电全配</option>
                        </select>
                    </div>
                    <div class="form-div"> 
                        <label  class="form-label">描述</label>
                        <textarea name="description" cols="50" rows="5">${house.description}</textarea>
                    </div>
                  <%--   <div class="form-div">
                        <label  class="form-label" >标签</label>
                        <input type="text" name="tags" class="form-input sign" value="${house.tags}"/>
                        <span>多个标签之间用逗号隔开</span>
                    </div> --%>
                    <div class="form-div">
                        <label  class="form-label" >是否对经纪人公开</label>
                        <input type="checkbox" id="checkIsOpen" name="checkIsOpen" class="form-input"/>
                        <input type="hidden" id="hidIsOpen" value="${house.isOpen}"/>
                        <span>公开</span>
                    </div>
                    <div class="btn">
                        <input name="isSave" type="submit" class="btn1" value="提交并返回房源列表" />
                        <!-- <input type="submit" class="btn2" value="提交并后留在此页" /> -->
                    </div>
                </form>
            </div>
        </div>

     </body>
     <script>
     $("#shopPrice").change(function(){
  		if(parseInt($("#shopPrice").val())>parseInt($("#listPrice").val())){
  			layer.alert("列表价大于中介供货价大于底价。");
  			$("#shopPrice").val('')
  		}
  	})
  	$("#minimumPrice").change(function(){
  		if(parseInt($("#minimumPrice").val())>parseInt($("#shopPrice").val()) || parseInt($("#minimumPrice").val())>parseInt($("#listPrice").val())){
  			layer.alert("列表价大于中介供货价大于底价。");
  			$("#minimumPrice").val('')
  		}
  	})
  	$("#listPrice").change(function(){
  		if(parseInt($("#shopPrice").val())>parseInt($("#listPrice").val()) || parseInt($("#minimumPrice").val())>parseInt($("#listPrice").val())){
  			layer.alert("列表价大于中介供货价大于底价。");
  			$("#listPrice").val('')
  		}
  	})
     </script>
     </html>