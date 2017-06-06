<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html lang="en">
<head>
	<meta charset="UTF-8">
    <meta content="yes" name="apple-mobile-web-app-capable">
	<meta content="yes" name="apple-touch-fullscreen">
	<meta content="telephone=no,email=no" name="format-detection">
    <link href="<%=request.getContextPath()%>/static/css/mui.css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/app.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/message.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/houseDetail.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/suitable.css" />
    <style type="text/css">
			.mui-scroll-wrapper {
				overflow: auto;
			}
			.mui-control-content {
				background-color: white;
				min-height: 17rem;
			}
			.mui-control-content .mui-loading {
				margin-top:.66rem;
			}
			#slider {
				margin-top: .8rem;
			}
			#ImgZoomInBG ~ img {
				width:100%;
			}
	</style>
    <script src="<%=request.getContextPath()%>/static/js/flexible.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/static/js/mui.min.js"></script>
    <script src="<%=request.getContextPath()%>/static/js/jquery-3.1.1.min.js"></script>
	<script type="text/javascript">
	$.fn.ImgZoomIn = function () {
		 
		bgstr = '<div id="ImgZoomInBG" style=" background:#000000; filter:Alpha(Opacity=70); opacity:1; position:fixed; left:0; top:0; z-index:10000; width:100%; height:100%; display:none;"><iframe src="about:blank" frameborder="0px" scrolling="yes" style="width:100%; height:100%;"></iframe></div>';
		//alert($(this).attr('src'));
		imgstr = '<img id="ImgZoomInImage" src="' + $(this).attr('src')+'" onclick=$(\'#ImgZoomInImage\').hide();$(\'#ImgZoomInBG\').hide(); style="cursor:pointer; display:none; position:fixed; z-index:10001;" />';
		if ($('#ImgZoomInBG').length < 1) {
		$('body').append(bgstr);
		}
		if ($('#ImgZoomInImage').length < 1) {
		$('body').append(imgstr);
		}
		else {
		$('#ImgZoomInImage').attr('src', $(this).attr('src'));
		}
		//alert($(window).scrollLeft());
		//alert( $(window).scrollTop());
		$('#ImgZoomInImage').css('left', $(window).scrollLeft() + ($(window).width() - $('#ImgZoomInImage').width()) / 2);
		$('#ImgZoomInImage').css('top', $(window).scrollTop() + ($(window).height() - $('#ImgZoomInImage').height()) / 2);
		$('#ImgZoomInBG').show();
		$('#ImgZoomInImage').show();
		};
		 
		$(document).ready(function () {
		$("#imgTest").bind("click", function () {
		$(this).ImgZoomIn();
		});
		});
	 $(document).ready(function(){
		 
		 /* var $btn=$('#btn-up');
			var $footer =$('.mui-bar-footer');
			var $height=$('.mui-bar-footer').height();

			function goTop(){
				$footer.animate({height: 14+'rem', top: 5+'rem'}, 1000)
			}
			function goDown(){
				$footer.animate({height: 1.02+'rem', top: 16.8+'rem'}, 1000)
			} */
			
			//项目详情
			getBiaoqian();
			getDateInfo();
			getHouseTypeMenus();
			getUsefulAreaMenus();
			getFloorMenus();
			//房源列表
			getHouseInfo();
			$("#option-one").change(function(){		
				getHouseInfoTwo();
			})
			$("#option-two").change(function(){		
				getHouseInfoTwo();
			})
			$("#option-three").change(function(){		
				getHouseInfoTwo();
			})
			getCityName();
		});
	 /* 获得标签 */
	 function getBiaoqian(){
		 $.ajax({
			 type:"post",
			 async:false,
			 url:"findProjectOrHousesTags",
			 data : {targetId :$("#projectId").val()},
			 success : function(data) {
					if(data.length>0){
						var s ='';
						$.each(data,function(v,o){
							s+= '<li>'+o.tagName+'</li>';
						})
						$(".tablist").html(s);
					}else{
						$(".tablist").html("");
					}
			},
			error:function(){
			}
		 })
	 }
	 //项目详情
	 function getDateInfo(){
			$.ajax({
				type : "post",
				async : false,
				url : "to_getMidOneProjectInfo",
				data : {projectId:$("#projectId").val()},
				success : function(data) {
					data = eval("(" +data+")");
					renderDateInfo(data.data);
					
				},
				/* error:function(){
					alert("信息获取失败");
				} */
			});
		}
	 function renderDateInfo(data){
		 if(data.hasOwnProperty("projectId")){
			 var dateString = data.deliverTime;
			 var dateT = dateString.substr(0,10);
			 var dateString2 = data.startTime;
			 var dateT2 = dateString2.substr(0,10);
			 if (data.adPhoto != null && data.adPhoto !=""){
				 $("#adPhoto").attr("src",data.adPhoto);
			 }
			 $("#projectName").html(data.projectName);
			 $("#city").html(data.city);
			 $("#unitCount").html(data.unitCount);
			 if (data.isDaiKan == "支持带看"){
				 $("#isDaiKan").html(data.isDaiKan);
			 }else {
				 $('.btn-see').remove();
				 $("#isDaiKan").remove();
			 }
			 if (data.isYiDi == "异地备案"){
				 $("#isYiDi").html(data.isYiDi);
			 }else {
				 $("#isYiDi").remove();
			 }
			 if (data.isFast == "快速结佣"){
				 $("#isFast").html(data.isFast);
			 }else {
				 $("#isFast").remove();
			 }
			 if (data.validDays > 0){
				 $("#validDays").html(data.validDays);
			 }else {
				 $("#validDays").remove();
			 }
			 $("#buildArea").html(data.buildArea);
			 $("#volumeRatio").html(data.volumeRatio);
			 $("#afforestationRatio").html(data.afforestationRatio);
			 $("#investor").html(data.investor);
			 $("#startTime").html(dateT2);
			 $("#propertyAddress").html(data.propertyAddress);
			 $("#groundArea").html(data.groundArea);
			 $("#undergroundArea").html(data.underGroundArea);
			 $("#maxcount").html(data.zonghushu);
			 $("#density").html(data.density);
			 $("#developer").html(data.developer);
			 $("#manager").html(data.manager);
			 $("#rightsYears").html(data.rightsYears);
			 $("#deliverTime").html(dateT);
			 $("#projectName2").html(data.projectName);
			 $("#propertyCost").html(data.propertyCost);
			 
			 
			 //优惠list
			 $("#youhuiSize").html(data.youhuiList.length)
			 var s ='';
			
			 $.each(data.youhuiList,function(v,o){
				 v++;
				 s += '<tr><td>'+'优惠'+v+':'+'</td><td>'+o.caption+'</td></tr>';
			 });
			 if (data.youhuiList.length >0) {
				 $(".benefits").html(s);
			 }
			 
			 var ss = '';
			 $.each(data.allPhotos,function(v,o){
				 if (v == 0){
					ss += '<div class="mui-slider-item mui-slider-item-duplicate"><a href="#">';
					ss += '<img class="setImg" src="'+o+'"></a></div>'; 
				 }else {
					ss += '<div class="mui-slider-item"><a href="#">';
					ss += '<img class="setImg" src="'+o+'"></a></div>'; 
				 }
			 });
			 if (data.allPhotos.length >0){
				 $('.mui-slider-group mui-slider-loop').html(ss);
			 }
		 }
	 }
	 
	 function getCityName(){
			
		 $.ajax({
			 type : "post",
			 async : false,
			 url:"to_getCityNameById",
			 data:{shengOrShi:$('#city').html()},
			 success : function (data) {
				 data = eval("(" +data+")");
				 $('.cityName').html(data.data.cityName)
				
			 }
		 });
	 }
	
	 //房源列表
	 function getHouseInfo(){
		
		/*  var numOne = $(".option-one").index();
		 var numTwo = $(".option-two").index();
		 var numThree = $(".option-three").index();
		console.log(numOne);
		console.log($(".option-one").eq(numOne+2).val()); */
			$.ajax({
				type : "post",
				async : false,
				url : "to_jingjirenSearchHouse",
				data : {projectId:$("#projectId").val()},
				success : function(data) {
					data = eval("(" +data+")");
					renderHouseInfo(data.root);
					
				},
				/* error:function(){
					alert("信息获取失败");
				} */
			});
		}
	 function getHouseInfoTwo(){

		
		 $.ajax({
				type : "post",
				async : false,
				url : "to_jingjirenSearchHouse",
				data : {projectId:$("#projectId").val(),
					houseType:$("#option-one").val(),
					usefulArea:$("#option-two").val(),
					floor:$("#option-three").val()},
				success : function(data) {
					data = eval("(" +data+")");
					renderHouseInfo(data.root);
					
				},
				/* error:function(){
					alert("信息获取失败");
				} */
			});
	 }
	 function renderHouseInfo(data) {
					
			 var s = '';
			 $.each(data,function(v,o){
				 var num =o.listPrice/o.buildArea;
				 num = num.toFixed(2);

				 s += '<section><a href="to_jingjirenOneHouse?houseNum='+o.houseNum +'"><h3 class="title">'+o.buildingNo+'栋'+o.unit+'单元'+o.houseNo +'号('+o.dealNum+')</h3>';
				 s += '<p><img src="static/images/icon1APP.png" alt=""><span>'+ ' '+o.houseStyle +'</span>';
				 s += '<span>  |  面积'+o.buildArea +'平方米</span></p>';
				 s += '<p><img src="static/images/icon2APP.png" alt=""><span></span><span>'+ ' '+num +'元/平方米'+'</span>';
				 s += '<span>  |  总价共</span><span>'+o.listPrice +'</span><span>元</span></p></a>';
				 if (o.isSupport == 1){
						 s += '<a class="successLink" href="to_goToContractRecord?houseNum='+o.houseNum +'">发起认购</a>';
				 }else {
						 s += '<a class="successLink" href="###" style="background:#999;">发起认购</a>';
				 }
				 s += '</section>';
			 });
			 if(data.length>0){
    			$('#selectHouseInfo').html(s);
			 }else {
				 $("#selectHouseInfo").html("<tr><td style='width:10%;height:30px;margin:0 auto;'>暂无数据</td></tr>");
			 }
	 }
	 
     function getHouseTypeMenus(){
     	$.ajax({
				type:"post",
				async : false,
				url:"app_menu_list_house_houseType",
				data :{projectId:$("#projectId").val()},
				success:function(data){
					data=eval("("+data+")");
					initHouseType(data.root);
				}
			});
     }
     function initHouseType(data){
			$.each(data,function(rowNum,o){
				$("#htNext").after('<option  value="'+o+'">'+o+'</option>');
				
			});
		}
     function getUsefulAreaMenus(){
      	$.ajax({
 				type:"post",
 				async : false,
 				url:"menu_list_house_usefulArea",
 				data : {projectId:$("#projectId").val()},
 				success:function(data){
 					data=eval("("+data+")");
 					initHouseUsefulArea(data.root);
 				}
 			});
      }
      function initHouseUsefulArea(data){
 			$.each(data,function(rowNum,o){
 				$("#areaNext").after('<option  value="'+o+'">'+o+'</option>');
 			});
 		}
      function getFloorMenus(){
        	$.ajax({
   				type:"post",
   				async : false,
   				url:"menu_list_house_floor",
   				data : {projectId:$("#projectId").val()},
   				success:function(data){
   					data=eval("("+data+")");
   					initHouseFloor(data.root);
   				}
   			});
        }
        function initHouseFloor(data){
   			$.each(data,function(rowNum,o){
   				$("#floorNext").after('<option  value="'+o+'">'+o+'</option>');
   			});
   		}
	</script>
	<title>项目详情</title>
</head>
<body class="mui-ios mui-ios-9 mui-ios-9-1" style="padding-top:0;">
	<header class="mui-bar mui-bar-nav mui-bar-transparent">
		<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
		<h1 class="mui-title">项目详情</h1>
	</header>
	<div class="mui-content">
		<input type="hidden" name="projectId" value="${dataInfo }" id="projectId">
		<div class="message">
			<section class="listStyle contain">
	    		<div class="leftbox">
	    			<img src="<%=request.getContextPath()%>/static/images/morentupian.png" alt="" class="exampleImg" id="adPhoto" onClick="$(this).ImgZoomIn();"/>
	    		</div>
	    		<div class="rightbox">
	    			<h1 class="list1" id="projectName"></h1>

	    			<a class="btn-see" href="to_addFavoriteToCollection?projectId=${dataInfo }">发起备案</a>

	    			<p class="list2">可售房源<span id="unitCount"></span><span>套</span></p>
	    		</div>	
	    		<div class="buttom-box">
	    		<a href="get_projectGuideRule?projectId=${dataInfo }">
	    			<span class="guize">分销规则</span>
	    			<ul>
	    				<li id="isDaiKan"></li>
	    				<li id="isYiDi"></li>
	    				<li id="isFast"></li>
	    				<li>备案有效<span id="validDays"></span>天</li>
	    				<span class="mui-icon mui-icon-arrowright rightIC"></span>
	    			</ul>
	    		</a>
	    		</div>    		
	    	</section>
		</div>
		<ul class="tablist"></ul>
		<!-- 切换 -->
		<div id="slider2" class="mui-slider">
				<div id="sliderSegmentedControl" class="mui-slider-indicator mui-segmented-control mui-segmented-control-inverted">
					<a class="mui-control-item" href="#item1mobile">
				项目详情
			</a>
					<a class="mui-control-item" href="#item2mobile">
				房源列表
			</a>
				</div>
				<div id="sliderProgressBar" class="mui-slider-progress-bar mui-col-xs-6"></div>
				<div class="mui-slider-group">
					<div id="item1mobile" class="mui-slider-item mui-control-content mui-active">
						<div id="scroll1" class="mui-scroll-wrapper">
							<div class="mui-scroll">
								<div class="midd">
								<div class="page page1">
			<div class="banner">
				<div id="slider" class="mui-slider">
				<div class="mui-slider-group mui-slider-loop" style="transform: translate3d(-9.2rem, 0rem, 0rem) translateZ(0rem);transition-duration: 0.2s">
					<!-- 额外增加的一个节点(循环轮播：第一个节点是最后一张轮播) -->
				</div>
				<!-- <div class="mui-slider-indicator mui-text-right">
					<div class="mui-indicator mui-active"></div>
					<div class="mui-indicator"></div>
					<div class="mui-indicator"></div>
					<div class="mui-indicator"></div>
				</div>  -->
				</div>
			</div>
			<div class="favourable">
				<h2>更多优惠</h2>
				<div class="benefits">
					<!-- <li>优惠1：交一百减100000</li>
					<li>优惠1：交一百减100000</li> -->
				</div>
			</div>
			<div class="introduce">
				<h2>项目介绍</h2>
				<table class="cssTable">
					<tbody>
						<tr>
							<td class="cos1">项目名称</td>
							<td colspan="3" id="projectName2"></td>
						</tr>
						<tr>
							<td class="cos1">所在地</td>
							<td colspan="3" id="propertyAddress"></td>
						</tr>
						<tr>
							<td class="cos1">用地面积</td>
							<td class="cos2" id="buildArea"></td>
							<td class="cos1">地上面积</td>
							<td class="cos2" id="groundArea"></td>
						</tr>
						<tr>
							<td class="cos1">地下面积</td>
							<td class="cos2" id="undergroundArea"></td>
							<td class="cos1">总户数</td>
							<td class="cos2" id="maxcount"></td>
						</tr>
						<tr>
							<td class="cos1">容积率</td>
							<td class="cos2" id="volumeRatio"></td>
							<td class="cos1">密度</td>
							<td class="cos2" id="density"></td>
						</tr>
						<tr>
							<td class="cos1">绿化率</td>
							<td class="cos2" id="afforestationRatio"></td>
							<td class="cos1">物业管理</td>
							<td class="cos2" id="manager"></td>
						</tr>
						<tr class="hei1">
							<td class="cos1">投资商</td>
							<td class="cos2" id="investor"></td>
							<td class="cos1">开发商</td>
							<td class="cos2" id="developer"></td>
						</tr>
						<tr>
							<td class="cos1">物业费</td>
							<td class="cos2" id="propertyCost"></td>
							<td class="cos1">产权年限</td>
							<td class="cos2" id="rightsYears"></td>
						</tr>
						<tr>
							<td class="cos1">开工时间</td>
							<td class="cos2" id="startTime"></td>
							<td class="cos1">交付时间</td>
							<td class="cos2" id="deliverTime"></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
							</div>
							</div>
						</div>
					</div>
					<div id="item2mobile" class="mui-slider-item mui-control-content">
						<div id="scroll2" class="mui-scroll-wrapper">
							<div class="mui-scroll">
								<div class="page page2">
			<div class="selectNav">
				
				<select class="mui-btn btn-block1" name="houseType" id="option-one">
					<option value="" id="htNext">户型</option>
					<!-- <option value="item-2">二室两厅一卫</option>
					<option value="item-3">四室两厅两卫</option>
					<option value="item-4">二室两厅一卫</option>
					<option value="item-5">三室两厅一卫</option>
					<option value="item-6">一室一厅两卫</option> -->
				</select>
				
				<select class="mui-btn btn-block2" name="usefulArea" id="option-two">
					
					<option value="" id="areaNext">面积</option>
					<!-- <option value="">二室两厅一卫</option>
					<option value="">四室两厅两卫</option>
					<option value="">二室两厅一卫</option>
					<option value="">三室两厅一卫</option>
					<option value="">一室一厅两卫</option> -->
				</select>

				<select class="mui-btn btn-block" name="floor" id="option-three">
					<option value="" id="floorNext">楼层</option>
					<!-- <option value="">二室两厅一卫</option>
					<option value="">四室两厅两卫</option>
					<option value="">二室两厅一卫</option>
					<option value="">三室两厅一卫</option>
					<option value="">一室一厅两卫</option> -->
				</select>
				<span class="mui-icon mui-icon-arrowdown pulldown1"></span>
				<span class="mui-icon mui-icon-arrowdown pulldown2"></span>
				<span class="mui-icon mui-icon-arrowdown pulldown3"></span>
			</div>
			<div class="messageList" id="selectHouseInfo">
			
			</div>
		</div>	
							</div>
						</div>

					</div>
				</div>
			</div>
		<!-- 结束 -->
		
		
		</div>
	</div>

		
		
	
	

	<script type="text/javascript">	
     
		
	// 轮播
		mui.init({
			swipeBack:true //启用右滑关闭功能
		});
		var gallery = mui('#slider');
		gallery.slider({
			interval:4000//自动轮播周期，若为0则不自动播放，默认为0；
	});
			 
    </script>



   
</body>
</html>