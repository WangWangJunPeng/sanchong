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
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/message.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/houseDetail.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/inventoryFile.css">
	<link rel="stylesheet" href="static/css/suitable.css" />
    <script src="<%=request.getContextPath()%>/static/js/flexible.js" type="text/javascript"></script>
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
    <script src="<%=request.getContextPath()%>/static/js/messageAPP.js" type="text/javascript"></script>
	<title>一房一档</title>
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
	</script>
	<style type="text/css">
		.mui-bar-nav~.mui-content {
			padding-top:.2rem; 
		}
	</style>
</head>
<body class="mui-ios mui-ios-9 mui-ios-9-1">
<header class="mui-bar mui-bar-nav mui-bar-transparent">
        <a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
        <h1 class="mui-title">${data.buildingNo }${data.unit }${data.floor }${data.houseNo }</h1>
</header>
<div class="mui-content">
	<div class="mid">
    <div class="banner">
        <div id="slider" class="mui-slider">
                <div class="mui-slider-group mui-slider-loop" style="transform: translate3d(-10rem, 0rem, 0rem) translateZ(0rem);transition-duration: 0.2s">
                    <!-- 额外增加的一个节点(循环轮播：第一个节点是最后一张轮播) -->
                    <c:if test="${fn:length(data.photo)>0}">
									<div class="mui-slider-item mui-slider-item-duplicate">
										<img src="${data.photo[fn:length(data.photo)-1]}" >
									</div>
	                    <c:forEach begin="0" end="${fn:length(data.photo)-1}" var="p">
									<div class="mui-slider-item ">
										<img class="exampleImg" onClick="$(this).ImgZoomIn();" src="${data.photo[p]}" >
									</div>
						</c:forEach>
									<div class="mui-slider-item mui-slider-item-duplicate">
										<img src="${data.photo[0]}" >
									</div>
                    </c:if>
                </div>
            </div>
        <p class="houseName">${data.projectName }${data.buildingNo }${data.unit }${data.floor }${data.houseNo }</p>
       	<ul class="tablist">
        		<!-- <li>路过</li>
        		<li>老带新</li>
        		<li>朋友介绍</li>
        		<li>只是随便了解</li>
        		<li>网络分享的消息</li>
        		<li>只是随便来了解的</li> -->
        </ul>
    </div>
    <div class="houseIntroduce introduce">
               <h2>房源介绍</h2>
                <table class="cssTable">
                    <tbody>
                        <tr>
                            <td class="cos1">房源类型</td>
                            <td class="cos2">${data.houseKind }</td>
                            <td class="cos1">预售证号</td>
                            <td class="cos2">${data.presalePermissionURL }</td>
                        </tr>
                        <tr>
                            <td class="cos1">区位号</td>
                            <td class="cos2">${data.district }</td>
                            <td class="cos1">朝向</td>
                            <td class="cos2">${data.direct }</td>
                        </tr>
                        <tr>
                            <td class="cos1">建筑面积</td>
                            <td class="cos2">${data.buildArea }m²</td>
                            <td class="cos1">使用面积</td>
                            <td class="cos2">${data.usefulArea }m²</td>
                        </tr>
                        <tr>
                            <td class="cos1">户型</td>
                            <td class="cos2">${data.houseType }</td>
                            <td class="cos1">装修标准</td>
                            <td class="cos2">${data.decorationStandard }</td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div class="priceMessage">
                <h2>价格信息</h2>
                <table>
                    <tbody>
                        <tr>
                            <td colspan="1">单价</td>
                            <td class="pri" colspan="1.5">${data.danjia }元/㎡</td>
                            <td class="allprice" colspan="1">总价</td>
                            <td class="pri" colspan="1.5">${data.listPrice }元</td>
                        </tr>
                        <%int i=0; %>
						<c:forEach items="${data.youhuiList }" var="list">
							
							<tr>
								<td colspan=1>优惠 <%i=i+1; out.print(i);%></td>
								<td colspan=5>${list.caption }</td>
							</tr>
						</c:forEach>
                        <tr>
                            <td colspan="6">最低成交价：${data.zuidiPrice }元，优惠力度：${data.lidu}% 最低成交优惠组合：${data.youhui }</td>
                        </tr>
                    </tbody>
                </table>

            </div>
            <div class="payCriterion">
                <h2>交付标准</h2>
                <!-- <ol>
                    <li>室内部分。内墙：抹灰；地坪：清理找平。顶棚：二遍水泥找平、内门口：净口取方室内窗台墙低于500加装不锈钢栏杆。</li>
                    <li>室内防水，卫生间及厨房；2遍防水卷材、蔽水试验合格。</li>
                    <li>公共电梯前厅  按图纸要求施工，未注明为；顶棚墙面白色乳胶漆，地坪600*600地砖。电梯门套线为；石材或地板砖加工线条。</li>
                    <li>公共步行楼梯间 水泥压光踏步，加护角筋、墙顶找平批白刷乳胶漆， 楼梯间隔墙装不锈钢扶手。</li>
                    <li>防盗门 符合图纸要求及消防要求，开启正常。</li>
                </ol> -->
                <ol>${data.deliverStandard }</ol>
            </div>
            <div class="introduce">
                <h2>项目介绍</h2>
                <table class="cssTable">
                    <tbody>
                        <tr>
                            <td class="cos1">项目名称</td>
                            <td colspan="3">${data.projectName}</td>
                        </tr>
                        <tr>
                            <td class="cos1">所在地</td>
                            <td colspan="3">${data.propertyAddress }</td>
                        </tr>
                        <tr>
                            <td class="cos1">用地面积</td>
                            <td class="cos2">${data.landArea }</td>
                            <td class="cos1">地上面积</td>
                            <td class="cos2">${data.groundArea }</td>
                        </tr>
                        <tr>
                            <td class="cos1">容积率</td>
                            <td class="cos2">${data.volumeRatio }</td>
                            <td class="cos1">密度</td>
                            <td class="cos2">${data.density }</td>
                        </tr>
                        <tr>
                            <td class="cos1">绿化率</td>
                            <td class="cos2">${data.afforestationRatio }</td>
                            <td class="cos1">物业管理</td>
                            <td class="cos2">${data.manager }</td>
                        </tr>
                        <tr class="hei1">
                            <td class="cos1">投资商</td>
                            <td class="cos2">${data.investor }</td>
                            <td class="cos1">开发商</td>
                            <td class="cos2">${data.developer }</td>
                        </tr>
                        <tr>
                            <td class="cos1">物业费</td>
                            <td class="cos2">${data.propertyCost }元/m²·月</td>
                            <td class="cos1">产权年限</td>
                            <td class="cos2">${data.rightsYears }年</td>
                        </tr>
                        <tr>
                            <td class="cos1">开工时间</td>
                            <td class="cos2">${data.startTime }</td>
                            <td class="cos1">交付时间</td>
                            <td class="cos2">${data.deliverTime }</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
</div>
 <script src="<%=request.getContextPath()%>/static/js/mui.min.js"></script>
    <script type="text/javascript"> 
        mui.init({
            swipeBack:true //启用右滑关闭功能
        });
        var slider = mui("#slider");
        slider.slider({
            interval: 5000
        });
    </script>
</body>
</html>