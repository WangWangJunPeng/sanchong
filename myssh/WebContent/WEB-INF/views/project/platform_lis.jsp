<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link  rel="icon" href="static/images/titleLogo.png"  />
<title>平台佣金管理</title>
        <link rel="stylesheet" type="text/css" href="static/css/messageProtect.css" />
        <link rel="stylesheet" type="text/css" href="static/css/reset.css" />

        <link rel="stylesheet" type="text/css" href="static/lib/laydate/need/laydate.css" />
        <script type="text/javascript" src="static/js/jquery-3.1.1.min.js"></script>
        <script type="text/javascript" src="static/lib/laydate/laydate.js"></script>
        <script src="static/js/city2.js"></script>
        <script type="text/javascript" src="static/js/citySelect2.js"></script>
        <script type="text/javascript" src="static/js/messageProtect.js"></script>
        <script type="text/javascript" src="static/js/plugIn.js"></script>
		<script type="text/javascript">
		$(document).ready(function(){
			
			$("#province").change(function(){
				var pId = $("#province").val();
				$("#market").empty();
				$.post("menu_list_city_area",
					{
						"shengOrShi" : pId
					},
					function(data){
						var data = eval('('+data+')');
						for(var i=0; i<data.root.length; i++){
							$("<option value='"+data.root[i].cityId+"'>"+data.root[i].cityName+"</option>").appendTo($("#market"))
						}
						changeMarket();
					});
			})
			
			function changeMarket(){
				var pId = $("#market").val();
				$("#area").empty();
				$.post("menu_list_city_area",
						{
							"shengOrShi" : pId
						},
				function(data){
					var data = eval('('+ data +')');
					for(var i=0; i<data.root.length; i++){
						$("<option value='"+data.root[i].cityId+"'>"+data.root[i].cityName+"</option>").appendTo($("#area"));
					}
				});
			}
			
			$("#market").change(function(){
    			changeMarket();
    		})
    		
    		$("#area").change(function(){
    			findProject();
    		})
			
			
			$("#guding").click(function(){  /* 一定要把函数写在这个主函数里面 */ 
	            $(".check").html("结算额");
	            $(".change-unit").html("元");
	        });
	        $("#baifenbi").click(function(){
	            $(".check").html("按比率");
	            $(".change-unit").html("%");
	        });
	        
			findProject();
		});
		
		function findProject(){
			$.ajax({
		        type : "post",
		        url : "find_projectId",
		        data : {
		            city : $('#province').val()+"-"+$('#market').val()+"-"+$('#area').val(),
		        },
		        success : function(data) {
		            data = eval("(" + data + ")");
		            getProjectName(data.root);
		        }
		    });
			
		}
		
		function getProjectName(data){
			$.each(data,function(rowNum,o){
				$("#project").after('<option value="'+o.projectId+'">'+o.projectName+'</option>');
			});
			
		}
		
		
		
		</script>

</head>
<body>
    <form class="form-box" action="platform_definition" method="post">
        <label class="form-label">城市<b>*</b></label>
        <div id="city"  class="house-search">
            <select name="province" id="province">
                <option value="">请选择</option>
                <c:forEach items="${provinces }" var="p">
                	<option value="${p.cityId }">${p.cityName }</option>
                </c:forEach>
            </select>
            <select name="market" id="market">
                <option value="">请选择</option>
            </select>
            <select name="area" id="area">
                <option value="">请选择</option>
            </select>
        </div>
        <div class="form-div">
            <label class="form-label">项目名<b>*</b></label>
            <select name="projectId" id="projectName">
                <option value="projectId" id="project">项目</option>
            </select>
        </div>
        <div class="form-div">
            <label class="form-label">结算方式</label>
            <input type="radio" name="rewardType" id="guding" value="0" checked="checked"><label class="form-label">固定方式</label>
            <input type="radio" name="rewardType" id="baifenbi" value="1"><label class="form-label">按比率方式</label>
            <label class="check" id="check"></label>
            <input type="text" name="reward" class="form-input project-name">
            <span class="change-unit"></span>
        </div>        
        <div class="form-div">
            <label class="form-label">最低成交价</label>
            <input type="text" name="minRewarMoney" class="form-input project-name">
        </div>
        <div class="form-div">
            <label class="form-label">最高成交价</label>
            <input type="text" name="MaxRewarMoney" class="form-input project-name">
        </div>
        <div class="btn">
            <input name="isSave" type="submit" class="btn1" value="保存">
            <font color="red">&nbsp;&nbsp;${data}</font>
        </div>
    </form>

</body>
</html>