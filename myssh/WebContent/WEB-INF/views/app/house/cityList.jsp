<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>

	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title></title>
		<link href="<%=request.getContextPath()%>/static/css/mui.min.css" rel="stylesheet" />
		<link href="<%=request.getContextPath()%>/static/css/mui.indexedlist.css" rel="stylesheet" />
		<style>
			html,
			body {
				height: 100%;
				overflow: hidden;
			}
			.mui-bar {
				-webkit-box-shadow: none;
				box-shadow: none;
			}
		</style>
	</head>

	<body>
		<header class="mui-bar mui-bar-nav">
			<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
			<h1 class="mui-title">全国城市列表</h1>
		</header>
		<div class="mui-content">
			<div id='list' class="mui-indexed-list">
				<div class="mui-indexed-list-search mui-input-row mui-search">
					<input type="search" class="mui-input-clear mui-indexed-list-search-input" placeholder="请输入城市名">
				</div>
				<div class="mui-indexed-list-bar">
					<a>A</a>
					<a>B</a>
					<a>C</a>
					<a>D</a>
					<a>E</a>
					<a>F</a>
					<a>G</a>
					<a>H</a>
					<a>I</a>
					<a>J</a>
					<a>K</a>
					<a>L</a>
					<a>M</a>
					<a>N</a>
					<a>O</a>
					<a>P</a>
					<a>Q</a>
					<a>R</a>
					<a>S</a>
					<a>T</a>
					<a>U</a>
					<a>V</a>
					<a>W</a>
					<a>X</a>
					<a>Y</a>
					<a>Z</a>
				</div>
				<div class="mui-indexed-list-alert"></div>
				<div class="mui-indexed-list-inner">
					<div class="mui-indexed-list-empty-alert">没有数据</div>
					<ul class="mui-table-view">
						<li data-group="A" class="mui-table-view-divider mui-indexed-list-group">A</li>
<li data-value="513200" data-tags="ABaCangZuQiangZuZiZhiZhou" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=513200&citySigle=choiced">阿坝藏族羌族自治州</a></li> 
<li data-value="652900" data-tags="AKeSuDiQu" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=652900&citySigle=choiced">阿克苏地区</a></li> 
<li data-value="659002" data-tags="ALaErShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=659002&citySigle=choiced">阿拉尔市</a></li> 
<li data-value="152900" data-tags="ALaShanMeng" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=152900&citySigle=choiced">阿拉善盟</a></li> 
<li data-value="654300" data-tags="ALeTaiDiQu" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=654300&citySigle=choiced">阿勒泰地区</a></li> 
<li data-value="542500" data-tags="ALiDiQu" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=542500&citySigle=choiced">阿里地区</a></li> 
<li data-value="610900" data-tags="AnKangShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=610900&citySigle=choiced">安康市</a></li> 
<li data-value="340800" data-tags="AnQingShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=340800&citySigle=choiced">安庆市</a></li> 
<li data-value="520400" data-tags="AnShunShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=520400&citySigle=choiced">安顺市</a></li> 
<li data-value="410500" data-tags="AnYangShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=410500&citySigle=choiced">安阳市</a></li> 
<li data-value="210300" data-tags="AnShanShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=210300&citySigle=choiced">鞍山市</a></li> 
<li data-group="B" class="mui-table-view-divider mui-indexed-list-group">B</li> 
<li data-value="150800" data-tags="BaYanNeErShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=150800&citySigle=choiced">巴彦淖尔市</a></li> 
<li data-value="652800" data-tags="BaYinGuoLengMengGuZiZhiZhou" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=652800&citySigle=choiced">巴音郭楞蒙古自治州</a></li> 
<li data-value="511900" data-tags="BaZhongShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=511900&citySigle=choiced">巴中市</a></li> 
<li data-value="220800" data-tags="BaiChengShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=220800&citySigle=choiced">白城市</a></li> 
<li data-value="469025" data-tags="BaiShaLiZuZiZhiXian" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=469025&citySigle=choiced">白沙黎族自治县</a></li> 
<li data-value="220600" data-tags="BaiShanShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=220600&citySigle=choiced">白山市</a></li> 
<li data-value="620400" data-tags="BaiYinShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=620400&citySigle=choiced">白银市</a></li> 
<li data-value="451000" data-tags="BaiSeShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=451000&citySigle=choiced">百色市</a></li> 
<li data-value="340300" data-tags="BangBuShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=340300&citySigle=choiced">蚌埠市</a></li> 
<li data-value="150200" data-tags="BaoTouShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=150200&citySigle=choiced">包头市</a></li> 
<li data-value="610300" data-tags="BaoJiShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=610300&citySigle=choiced">宝鸡市</a></li> 
<li data-value="130600" data-tags="BaoDingShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=130600&citySigle=choiced">保定市</a></li> 
<li data-value="530500" data-tags="BaoShanShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=530500&citySigle=choiced">保山市</a></li> 
<li data-value="469029" data-tags="BaoTingLiZuMiaoZuZiZhiXian" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=469029&citySigle=choiced">保亭黎族苗族自治县</a></li> 
<li data-value="450500" data-tags="BeiHaiShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=450500&citySigle=choiced">北海市</a></li> 
<li data-value="110100" data-tags="BeiJingShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=110100&citySigle=choiced">北京市</a></li> 
<li data-value="210500" data-tags="BenXiShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=210500&citySigle=choiced">本溪市</a></li> 
<li data-value="520500" data-tags="BiJieShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=520500&citySigle=choiced">毕节市</a></li> 
<li data-value="371600" data-tags="BinZhouShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=371600&citySigle=choiced">滨州市</a></li> 
<li data-value="341600" data-tags="BoZhouShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=341600&citySigle=choiced">亳州市</a></li> 
<li data-value="652700" data-tags="BoErTaLaMengGuZiZhiZhou" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=652700&citySigle=choiced">博尔塔拉蒙古自治州</a></li> 
<li data-group="C" class="mui-table-view-divider mui-indexed-list-group">C</li> 
<li data-value="130900" data-tags="CangZhouShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=130900&citySigle=choiced">沧州市</a></li> 
<li data-value="542100" data-tags="ChangDuDiQu" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=542100&citySigle=choiced">昌都地区</a></li> 
<li data-value="652300" data-tags="ChangJiHuiZuZiZhiZhou" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=652300&citySigle=choiced">昌吉回族自治州</a></li> 
<li data-value="469026" data-tags="ChangJiangLiZuZiZhiXian" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=469026&citySigle=choiced">昌江黎族自治县</a></li> 
<li data-value="220100" data-tags="ChangChunShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=220100&citySigle=choiced">长春市</a></li> 
<li data-value="430100" data-tags="ChangShaShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=430100&citySigle=choiced">长沙市</a></li> 
<li data-value="140400" data-tags="ChangZhiShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=140400&citySigle=choiced">长治市</a></li> 
<li data-value="430700" data-tags="ChangDeShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=430700&citySigle=choiced">常德市</a></li> 
<li data-value="320400" data-tags="ChangZhouShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=320400&citySigle=choiced">常州市</a></li> 
<li data-value="211300" data-tags="ChaoYangShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=211300&citySigle=choiced">朝阳市</a></li>
<li data-value="500100" data-tags="ChongQingShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=500100&citySigle=choiced">重庆市</a></li>  
<li data-value="445100" data-tags="ChaoZhouShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=445100&citySigle=choiced">潮州市</a></li> 
<li data-value="431000" data-tags="ChenZhouShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=431000&citySigle=choiced">郴州市</a></li> 
<li data-value="510100" data-tags="ChengDuShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=510100&citySigle=choiced">成都市</a></li> 
<li data-value="130800" data-tags="ChengDeShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=130800&citySigle=choiced">承德市</a></li> 
<li data-value="469023" data-tags="ChengMaiXian" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=469023&citySigle=choiced">澄迈县</a></li> 
<li data-value="341700" data-tags="ChiZhouShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=341700&citySigle=choiced">池州市</a></li> 
<li data-value="150400" data-tags="ChiFengShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=150400&citySigle=choiced">赤峰市</a></li> 
<li data-value="451400" data-tags="ChongZuoShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=451400&citySigle=choiced">崇左市</a></li> 
<li data-value="341100" data-tags="ChuZhouShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=341100&citySigle=choiced">滁州市</a></li> 
<li data-value="532300" data-tags="ChuXiongYiZuZiZhiZhou" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=532300&citySigle=choiced">楚雄彝族自治州</a></li> 
<li data-group="D" class="mui-table-view-divider mui-indexed-list-group">D</li> 
<li data-value="511700" data-tags="DaZhouShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=511700&citySigle=choiced">达州市</a></li> 
<li data-value="532900" data-tags="DaLiBaiZuZiZhiZhou" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=532900&citySigle=choiced">大理白族自治州</a></li> 
<li data-value="210200" data-tags="DaLianShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=210200&citySigle=choiced">大连市</a></li> 
<li data-value="230600" data-tags="DaQingShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=230600&citySigle=choiced">大庆市</a></li> 
<li data-value="140200" data-tags="DaTongShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=140200&citySigle=choiced">大同市</a></li> 
<li data-value="232700" data-tags="DaXingAnLingDiQu" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=232700&citySigle=choiced">大兴安岭地区</a></li> 
<li data-value="210600" data-tags="DanDongShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=210600&citySigle=choiced">丹东市</a></li> 
<li data-value="469003" data-tags="DanZhouShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=469003&citySigle=choiced">儋州市</a></li> 
<li data-value="533100" data-tags="DeHongDaiZuJingPoZuZiZhiZhou" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=533100&citySigle=choiced">德宏傣族景颇族自治州</a></li> 
<li data-value="510600" data-tags="DeYangShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=510600&citySigle=choiced">德阳市</a></li> 
<li data-value="371400" data-tags="DeZhouShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=371400&citySigle=choiced">德州市</a></li> 
<li data-value="533400" data-tags="DiQingCangZuZiZhiZhou" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=533400&citySigle=choiced">迪庆藏族自治州</a></li> 
<li data-value="469021" data-tags="DingAnXian" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=469021&citySigle=choiced">定安县</a></li> 
<li data-value="621100" data-tags="DingXiShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=621100&citySigle=choiced">定西市</a></li> 
<li data-value="469007" data-tags="DongFangShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=469007&citySigle=choiced">东方市</a></li> 
<li data-value="441900" data-tags="DongGuanShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=441900&citySigle=choiced">东莞市</a></li> 
<li data-value="370500" data-tags="DongYingShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=370500&citySigle=choiced">东营市</a></li> 
<li data-group="E" class="mui-table-view-divider mui-indexed-list-group">E</li> 
<li data-value="150600" data-tags="EErDuoSiShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=150600&citySigle=choiced">鄂尔多斯市</a></li> 
<li data-value="420700" data-tags="EZhouShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=420700&citySigle=choiced">鄂州市</a></li> 
<li data-value="422800" data-tags="EnShiTuJiaZuMiaoZuZiZhiZhou" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=422800&citySigle=choiced">恩施土家族苗族自治州</a></li> 
<li data-group="F" class="mui-table-view-divider mui-indexed-list-group">F</li> 
<li data-value="450600" data-tags="FangChengGangShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=450600&citySigle=choiced">防城港市</a></li> 
<li data-value="440600" data-tags="FuShanShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=440600&citySigle=choiced">佛山市</a></li> 
<li data-value="350100" data-tags="FuZhouShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=350100&citySigle=choiced">福州市</a></li> 
<li data-value="210400" data-tags="FuShunShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=210400&citySigle=choiced">抚顺市</a></li> 
<li data-value="361000" data-tags="FuZhouShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=361000&citySigle=choiced">抚州市</a></li> 
<li data-value="210900" data-tags="FuXinShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=210900&citySigle=choiced">阜新市</a></li> 
<li data-value="341200" data-tags="FuYangShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=341200&citySigle=choiced">阜阳市</a></li> 
<li data-group="G" class="mui-table-view-divider mui-indexed-list-group">G</li> 
<li data-value="623000" data-tags="GanNanCangZuZiZhiZhou" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=623000&citySigle=choiced">甘南藏族自治州</a></li> 
<li data-value="513300" data-tags="GanZiCangZuZiZhiZhou" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=513300&citySigle=choiced">甘孜藏族自治州</a></li> 
<li data-value="360700" data-tags="GanZhouShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=360700&citySigle=choiced">赣州市</a></li> 
<li data-value="640400" data-tags="GuYuanShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=640400&citySigle=choiced">固原市</a></li> 
<li data-value="511600" data-tags="GuangAnShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=511600&citySigle=choiced">广安市</a></li> 
<li data-value="510800" data-tags="GuangYuanShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=510800&citySigle=choiced">广元市</a></li> 
<li data-value="440100" data-tags="GuangZhouShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=440100&citySigle=choiced">广州市</a></li> 
<li data-value="450800" data-tags="GuiGangShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=450800&citySigle=choiced">贵港市</a></li> 
<li data-value="520100" data-tags="GuiYangShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=520100&citySigle=choiced">贵阳市</a></li> 
<li data-value="450300" data-tags="GuiLinShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=450300&citySigle=choiced">桂林市</a></li> 
<li data-value="632600" data-tags="GuoLuoCangZuZiZhiZhou" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=632600&citySigle=choiced">果洛藏族自治州</a></li> 
<li data-group="H" class="mui-table-view-divider mui-indexed-list-group">H</li> 
<li data-value="230100" data-tags="HaErBinShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=230100&citySigle=choiced">哈尔滨市</a></li> 
<li data-value="652200" data-tags="HaMiDiQu" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=652200&citySigle=choiced">哈密地区</a></li> 
<li data-value="632200" data-tags="HaiBeiCangZuZiZhiZhou" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=632200&citySigle=choiced">海北藏族自治州</a></li> 
<li data-value="630200" data-tags="HaiDongShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=630200&citySigle=choiced">海东市</a></li> 
<li data-value="460100" data-tags="HaiKouShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=460100&citySigle=choiced">海口市</a></li> 
<li data-value="632500" data-tags="HaiNanCangZuZiZhiZhou" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=632500&citySigle=choiced">海南藏族自治州</a></li> 
<li data-value="632800" data-tags="HaiXiMengGuZuCangZuZiZhiZhou" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=632800&citySigle=choiced">海西蒙古族藏族自治州</a></li> 
<li data-value="130400" data-tags="HanDanShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=130400&citySigle=choiced">邯郸市</a></li> 
<li data-value="610700" data-tags="HanZhongShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=610700&citySigle=choiced">汉中市</a></li> 
<li data-value="330100" data-tags="HangZhouShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=330100&citySigle=choiced">杭州市</a></li> 
<li data-value="340100" data-tags="HeFeiShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=340100&citySigle=choiced">合肥市</a></li> 
<li data-value="653200" data-tags="HeTianDiQu" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=653200&citySigle=choiced">和田地区</a></li> 
<li data-value="451200" data-tags="HeChiShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=451200&citySigle=choiced">河池市</a></li> 
<li data-value="441600" data-tags="HeYuanShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=441600&citySigle=choiced">河源市</a></li> 
<li data-value="371700" data-tags="HeZeShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=371700&citySigle=choiced">菏泽市</a></li> 
<li data-value="451100" data-tags="HeZhouShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=451100&citySigle=choiced">贺州市</a></li> 
<li data-value="410600" data-tags="HeBiShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=410600&citySigle=choiced">鹤壁市</a></li> 
<li data-value="230400" data-tags="HeGangShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=230400&citySigle=choiced">鹤岗市</a></li> 
<li data-value="231100" data-tags="HeiHeShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=231100&citySigle=choiced">黑河市</a></li> 
<li data-value="131100" data-tags="HengShuiShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=131100&citySigle=choiced">衡水市</a></li> 
<li data-value="430400" data-tags="HengYangShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=430400&citySigle=choiced">衡阳市</a></li> 
<li data-value="532500" data-tags="HongHeHaNiZuYiZuZiZhiZhou" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=532500&citySigle=choiced">红河哈尼族彝族自治州</a></li> 
<li data-value="150100" data-tags="HuHeHaoTeShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=150100&citySigle=choiced">呼和浩特市</a></li> 
<li data-value="150700" data-tags="HuLunBeiErShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=150700&citySigle=choiced">呼伦贝尔市</a></li> 
<li data-value="330500" data-tags="HuZhouShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=330500&citySigle=choiced">湖州市</a></li> 
<li data-value="211400" data-tags="HuLuDaoShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=211400&citySigle=choiced">葫芦岛市</a></li> 
<li data-value="431200" data-tags="HuaiHuaShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=431200&citySigle=choiced">怀化市</a></li> 
<li data-value="320800" data-tags="HuaiAnShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=320800&citySigle=choiced">淮安市</a></li> 
<li data-value="340600" data-tags="HuaiBeiShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=340600&citySigle=choiced">淮北市</a></li> 
<li data-value="340400" data-tags="HuaiNanShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=340400&citySigle=choiced">淮南市</a></li> 
<li data-value="421100" data-tags="HuangGangShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=421100&citySigle=choiced">黄冈市</a></li> 
<li data-value="632300" data-tags="HuangNanCangZuZiZhiZhou" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=632300&citySigle=choiced">黄南藏族自治州</a></li> 
<li data-value="341000" data-tags="HuangShanShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=341000&citySigle=choiced">黄山市</a></li> 
<li data-value="420200" data-tags="HuangShiShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=420200&citySigle=choiced">黄石市</a></li> 
<li data-value="441300" data-tags="HuiZhouShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=441300&citySigle=choiced">惠州市</a></li> 
<li data-group="J" class="mui-table-view-divider mui-indexed-list-group">J</li> 
<li data-value="230300" data-tags="JiXiShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=230300&citySigle=choiced">鸡西市</a></li> 
<li data-value="360800" data-tags="JiAnShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=360800&citySigle=choiced">吉安市</a></li> 
<li data-value="220200" data-tags="JiLinShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=220200&citySigle=choiced">吉林市</a></li> 
<li data-value="370100" data-tags="JiNanShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=370100&citySigle=choiced">济南市</a></li> 
<li data-value="370800" data-tags="JiNingShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=370800&citySigle=choiced">济宁市</a></li> 
<li data-value="230800" data-tags="JiaMuSiShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=230800&citySigle=choiced">佳木斯市</a></li> 
<li data-value="330400" data-tags="JiaXingShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=330400&citySigle=choiced">嘉兴市</a></li> 
<li data-value="620200" data-tags="JiaYuGuanShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=620200&citySigle=choiced">嘉峪关市</a></li> 
<li data-value="440700" data-tags="JiangMenShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=440700&citySigle=choiced">江门市</a></li> 
<li data-value="410800" data-tags="JiaoZuoShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=410800&citySigle=choiced">焦作市</a></li> 
<li data-value="445200" data-tags="JieYangShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=445200&citySigle=choiced">揭阳市</a></li> 
<li data-value="620300" data-tags="JinChangShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=620300&citySigle=choiced">金昌市</a></li> 
<li data-value="330700" data-tags="JinHuaShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=330700&citySigle=choiced">金华市</a></li> 
<li data-value="210700" data-tags="JinZhouShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=210700&citySigle=choiced">锦州市</a></li> 
<li data-value="140500" data-tags="JinChengShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=140500&citySigle=choiced">晋城市</a></li> 
<li data-value="140700" data-tags="JinZhongShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=140700&citySigle=choiced">晋中市</a></li> 
<li data-value="420800" data-tags="JingMenShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=420800&citySigle=choiced">荆门市</a></li> 
<li data-value="421000" data-tags="JingZhouShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=421000&citySigle=choiced">荆州市</a></li> 
<li data-value="360200" data-tags="JingDeZhenShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=360200&citySigle=choiced">景德镇市</a></li> 
<li data-value="360400" data-tags="JiuJiangShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=360400&citySigle=choiced">九江市</a></li> 
<li data-value="620900" data-tags="JiuQuanShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=620900&citySigle=choiced">酒泉市</a></li> 
<li data-group="K" class="mui-table-view-divider mui-indexed-list-group">K</li> 
<li data-value="653100" data-tags="KaShiDiQu" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=653100&citySigle=choiced">喀什地区</a></li> 
<li data-value="410200" data-tags="KaiFengShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=410200&citySigle=choiced">开封市</a></li> 
<li data-value="650200" data-tags="KeLaMaYiShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=650200&citySigle=choiced">克拉玛依市</a></li> 
<li data-value="653000" data-tags="KeZiLeSuKeErKeZiZiZhi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=653000&citySigle=choiced">克孜勒苏柯尔克孜自治</a></li> 
<li data-value="530100" data-tags="KunMingShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=530100&citySigle=choiced">昆明市</a></li> 
<li data-group="L" class="mui-table-view-divider mui-indexed-list-group">L</li> 
<li data-value="540100" data-tags="LaSaShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=540100&citySigle=choiced">拉萨市</a></li> 
<li data-value="451300" data-tags="LaiBinShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=451300&citySigle=choiced">来宾市</a></li> 
<li data-value="371200" data-tags="LaiWuShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=371200&citySigle=choiced">莱芜市</a></li> 
<li data-value="620100" data-tags="LanZhouShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=620100&citySigle=choiced">兰州市</a></li> 
<li data-value="131000" data-tags="LangFangShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=131000&citySigle=choiced">廊坊市</a></li> 
<li data-value="469027" data-tags="LeDongLiZuZiZhiXian" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=469027&citySigle=choiced">乐东黎族自治县</a></li> 
<li data-value="511100" data-tags="LeShanShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=511100&citySigle=choiced">乐山市</a></li> 
<li data-value="530700" data-tags="LiJiangShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=530700&citySigle=choiced">丽江市</a></li> 
<li data-value="331100" data-tags="LiShuiShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=331100&citySigle=choiced">丽水市</a></li> 
<li data-value="320700" data-tags="LianYunGangShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=320700&citySigle=choiced">连云港市</a></li> 
<li data-value="513400" data-tags="LiangShanYiZuZiZhiZhou" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=513400&citySigle=choiced">凉山彝族自治州</a></li> 
<li data-value="211000" data-tags="LiaoYangShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=211000&citySigle=choiced">辽阳市</a></li> 
<li data-value="220400" data-tags="LiaoYuanShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=220400&citySigle=choiced">辽源市</a></li> 
<li data-value="371500" data-tags="LiaoChengShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=371500&citySigle=choiced">聊城市</a></li> 
<li data-value="542600" data-tags="LinZhiDiQu" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=542600&citySigle=choiced">林芝地区</a></li> 
<li data-value="530900" data-tags="LinCangShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=530900&citySigle=choiced">临沧市</a></li> 
<li data-value="141000" data-tags="LinFenShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=141000&citySigle=choiced">临汾市</a></li> 
<li data-value="469024" data-tags="LinGaoXian" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=469024&citySigle=choiced">临高县</a></li> 
<li data-value="622900" data-tags="LinXiaHuiZuZiZhiZhou" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=622900&citySigle=choiced">临夏回族自治州</a></li> 
<li data-value="371300" data-tags="LinYiShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=371300&citySigle=choiced">临沂市</a></li> 
<li data-value="469028" data-tags="LingShuiLiZuZiZhiXian" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=469028&citySigle=choiced">陵水黎族自治县</a></li> 
<li data-value="450200" data-tags="LiuZhouShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=450200&citySigle=choiced">柳州市</a></li> 
<li data-value="341500" data-tags="LiuAnShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=341500&citySigle=choiced">六安市</a></li> 
<li data-value="520200" data-tags="LiuPanShuiShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=520200&citySigle=choiced">六盘水市</a></li> 
<li data-value="350800" data-tags="LongYanShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=350800&citySigle=choiced">龙岩市</a></li> 
<li data-value="621200" data-tags="LongNanShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=621200&citySigle=choiced">陇南市</a></li> 
<li data-value="431300" data-tags="LouDiShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=431300&citySigle=choiced">娄底市</a></li> 
<li data-value="510500" data-tags="LuZhouShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=510500&citySigle=choiced">泸州市</a></li> 
<li data-value="141100" data-tags="LvLiangShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=141100&citySigle=choiced">吕梁市</a></li> 
<li data-value="410300" data-tags="LuoYangShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=410300&citySigle=choiced">洛阳市</a></li> 
<li data-value="411100" data-tags="LeiHeShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=411100&citySigle=choiced">漯河市</a></li> 
<li data-group="M" class="mui-table-view-divider mui-indexed-list-group">M</li> 
<li data-value="340500" data-tags="MaAnShanShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=340500&citySigle=choiced">马鞍山市</a></li> 
<li data-value="440900" data-tags="MaoMingShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=440900&citySigle=choiced">茂名市</a></li> 
<li data-value="511400" data-tags="MeiShanShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=511400&citySigle=choiced">眉山市</a></li> 
<li data-value="441400" data-tags="MeiZhouShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=441400&citySigle=choiced">梅州市</a></li> 
<li data-value="510700" data-tags="MianYangShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=510700&citySigle=choiced">绵阳市</a></li> 
<li data-value="231000" data-tags="MuDanJiangShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=231000&citySigle=choiced">牡丹江市</a></li> 
<li data-group="N" class="mui-table-view-divider mui-indexed-list-group">N</li> 
<li data-value="511000" data-tags="NaJiangShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=511000&citySigle=choiced">内江市</a></li> 
<li data-value="542400" data-tags="NaQuDiQu" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=542400&citySigle=choiced">那曲地区</a></li> 
<li data-value="360100" data-tags="NanChangShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=360100&citySigle=choiced">南昌市</a></li> 
<li data-value="511300" data-tags="NanChongShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=511300&citySigle=choiced">南充市</a></li> 
<li data-value="320100" data-tags="NanJingShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=320100&citySigle=choiced">南京市</a></li> 
<li data-value="450100" data-tags="NanNingShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=450100&citySigle=choiced">南宁市</a></li> 
<li data-value="350700" data-tags="NanPingShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=350700&citySigle=choiced">南平市</a></li> 
<li data-value="320600" data-tags="NanTongShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=320600&citySigle=choiced">南通市</a></li> 
<li data-value="411300" data-tags="NanYangShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=411300&citySigle=choiced">南阳市</a></li> 
<li data-value="330200" data-tags="NingBoShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=330200&citySigle=choiced">宁波市</a></li> 
<li data-value="350900" data-tags="NingDeShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=350900&citySigle=choiced">宁德市</a></li> 
<li data-value="533300" data-tags="NuJiangLiSuZuZiZhiZhou" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=533300&citySigle=choiced">怒江傈僳族自治州</a></li> 
<li data-group="P" class="mui-table-view-divider mui-indexed-list-group">P</li> 
<li data-value="510400" data-tags="PanZhiHuaShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=510400&citySigle=choiced">攀枝花市</a></li> 
<li data-value="211100" data-tags="PanJinShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=211100&citySigle=choiced">盘锦市</a></li> 
<li data-value="410400" data-tags="PingDingShanShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=410400&citySigle=choiced">平顶山市</a></li> 
<li data-value="620800" data-tags="PingLiangShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=620800&citySigle=choiced">平凉市</a></li> 
<li data-value="360300" data-tags="PingXiangShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=360300&citySigle=choiced">萍乡市</a></li> 
<li data-value="350300" data-tags="PuTianShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=350300&citySigle=choiced">莆田市</a></li> 
<li data-value="410900" data-tags="PuYangShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=410900&citySigle=choiced">濮阳市</a></li> 
<li data-value="530800" data-tags="PuErShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=530800&citySigle=choiced">普洱市</a></li> 
<li data-group="Q" class="mui-table-view-divider mui-indexed-list-group">Q</li> 
<li data-value="230900" data-tags="QiTaiHeShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=230900&citySigle=choiced">七台河市</a></li> 
<li data-value="230200" data-tags="QiQiHaErShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=230200&citySigle=choiced">齐齐哈尔市</a></li> 
<li data-value="429005" data-tags="QianJiangShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=429005&citySigle=choiced">潜江市</a></li> 
<li data-value="522600" data-tags="QianDongNanMiaoZuDongZuZiZhiZhou" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=522600&citySigle=choiced">黔东南苗族侗族自治州</a></li> 
<li data-value="522700" data-tags="QianNanBuYiZuMiaoZuZiZhiZhou" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=522700&citySigle=choiced">黔南布依族苗族自治州</a></li> 
<li data-value="522300" data-tags="QianXiNanBuYiZuMiaoZuZiZhi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=522300&citySigle=choiced">黔西南布依族苗族自治</a></li> 
<li data-value="450700" data-tags="QinZhouShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=450700&citySigle=choiced">钦州市</a></li> 
<li data-value="130300" data-tags="QinHuangDaoShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=130300&citySigle=choiced">秦皇岛市</a></li> 
<li data-value="370200" data-tags="QingDaoShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=370200&citySigle=choiced">青岛市</a></li> 
<li data-value="441800" data-tags="QingYuanShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=441800&citySigle=choiced">清远市</a></li> 
<li data-value="621000" data-tags="QingYangShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=621000&citySigle=choiced">庆阳市</a></li> 
<li data-value="469002" data-tags="QiongHaiShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=469002&citySigle=choiced">琼海市</a></li> 
<li data-value="469030" data-tags="QiongZhongLiZuMiaoZuZiZhiXian" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=469030&citySigle=choiced">琼中黎族苗族自治县</a></li> 
<li data-value="530300" data-tags="QuJingShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=530300&citySigle=choiced">曲靖市</a></li> 
<li data-value="330800" data-tags="QuZhouShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=330800&citySigle=choiced">衢州市</a></li> 
<li data-value="350500" data-tags="QuanZhouShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=350500&citySigle=choiced">泉州市</a></li> 
<li data-group="R" class="mui-table-view-divider mui-indexed-list-group">R</li> 
<li data-value="542300" data-tags="RiKaZeDiQu" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=542300&citySigle=choiced">日喀则地区</a></li> 
<li data-value="371100" data-tags="RiZhaoShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=371100&citySigle=choiced">日照市</a></li> 
<li data-group="S" class="mui-table-view-divider mui-indexed-list-group">S</li> 
<li data-value="411200" data-tags="SanMenXiaShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=411200&citySigle=choiced">三门峡市</a></li> 
<li data-value="350400" data-tags="SanMingShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=350400&citySigle=choiced">三明市</a></li> 
<li data-value="460300" data-tags="SanShaShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=460300&citySigle=choiced">三沙市</a></li> 
<li data-value="460200" data-tags="SanYaShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=460200&citySigle=choiced">三亚市</a></li> 
<li data-value="542200" data-tags="ShanNanDiQu" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=542200&citySigle=choiced">山南地区</a></li> 
<li data-value="440500" data-tags="ShanTouShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=440500&citySigle=choiced">汕头市</a></li> 
<li data-value="441500" data-tags="ShanWeiShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=441500&citySigle=choiced">汕尾市</a></li> 
<li data-value="611000" data-tags="ShangLuoShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=611000&citySigle=choiced">商洛市</a></li> 
<li data-value="411400" data-tags="ShangQiuShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=411400&citySigle=choiced">商丘市</a></li> 
<li data-value="310100" data-tags="ShangHaiShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=310100&citySigle=choiced">上海市</a></li> 
<li data-value="361100" data-tags="ShangRaoShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=361100&citySigle=choiced">上饶市</a></li> 
<li data-value="440200" data-tags="ShaoGuanShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=440200&citySigle=choiced">韶关市</a></li> 
<li data-value="430500" data-tags="ShaoYangShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=430500&citySigle=choiced">邵阳市</a></li> 
<li data-value="330600" data-tags="ShaoXingShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=330600&citySigle=choiced">绍兴市</a></li> 
<li data-value="440300" data-tags="ShenChouShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=440300&citySigle=choiced">深圳市</a></li> 
<li data-value="429021" data-tags="ShenNongJiaLinQu" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=429021&citySigle=choiced">神农架林区</a></li> 
<li data-value="210100" data-tags="ShenYangShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=210100&citySigle=choiced">沈阳市</a></li> 
<li data-value="420300" data-tags="ShiYanShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=420300&citySigle=choiced">十堰市</a></li> 
<li data-value="130100" data-tags="ShiJiaZhuangShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=130100&citySigle=choiced">石家庄市</a></li> 
<li data-value="640200" data-tags="ShiZuiShanShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=640200&citySigle=choiced">石嘴山市</a></li> 
<li data-value="230500" data-tags="ShuangYaShanShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=230500&citySigle=choiced">双鸭山市</a></li> 
<li data-value="140600" data-tags="ShuoZhouShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=140600&citySigle=choiced">朔州市</a></li> 
<li data-value="220300" data-tags="SiPingShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=220300&citySigle=choiced">四平市</a></li> 
<li data-value="220700" data-tags="SongYuanShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=220700&citySigle=choiced">松原市</a></li> 
<li data-value="320500" data-tags="SuZhouShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=320500&citySigle=choiced">苏州市</a></li> 
<li data-value="321300" data-tags="SuQianShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=321300&citySigle=choiced">宿迁市</a></li> 
<li data-value="341300" data-tags="SuZhouShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=341300&citySigle=choiced">宿州市</a></li> 
<li data-value="231200" data-tags="SuiHuaShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=231200&citySigle=choiced">绥化市</a></li> 
<li data-value="421300" data-tags="SuiZhouShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=421300&citySigle=choiced">随州市</a></li> 
<li data-value="510900" data-tags="SuiNingShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=510900&citySigle=choiced">遂宁市</a></li> 
<li data-group="T" class="mui-table-view-divider mui-indexed-list-group">T</li> 
<li data-value="654200" data-tags="TaChengDiQu" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=654200&citySigle=choiced">塔城地区</a></li> 
<li data-value="331000" data-tags="TaiZhouShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=331000&citySigle=choiced">台州市</a></li> 
<li data-value="140100" data-tags="TaiYuanShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=140100&citySigle=choiced">太原市</a></li> 
<li data-value="370900" data-tags="TaiAnShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=370900&citySigle=choiced">泰安市</a></li> 
<li data-value="321200" data-tags="TaiZhouShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=321200&citySigle=choiced">泰州市</a></li> 
<li data-value="130200" data-tags="TangShanShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=130200&citySigle=choiced">唐山市</a></li> 
<li data-value="120100" data-tags="TianJinShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=120100&citySigle=choiced">天津市</a></li> 
<li data-value="429006" data-tags="TianMenShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=429006&citySigle=choiced">天门市</a></li> 
<li data-value="620500" data-tags="TianShuiShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=620500&citySigle=choiced">天水市</a></li> 
<li data-value="211200" data-tags="TieLingShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=211200&citySigle=choiced">铁岭市</a></li> 
<li data-value="220500" data-tags="TongHuaShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=220500&citySigle=choiced">通化市</a></li> 
<li data-value="150500" data-tags="TongLiaoShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=150500&citySigle=choiced">通辽市</a></li> 
<li data-value="610200" data-tags="TongChuanShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=610200&citySigle=choiced">铜川市</a></li> 
<li data-value="340700" data-tags="TongLingShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=340700&citySigle=choiced">铜陵市</a></li> 
<li data-value="520600" data-tags="TongRenShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=520600&citySigle=choiced">铜仁市</a></li> 
<li data-value="659003" data-tags="TuMuShuKeShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=659003&citySigle=choiced">图木舒克市</a></li> 
<li data-value="652100" data-tags="TuLuFanDiQu" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=652100&citySigle=choiced">吐鲁番地区</a></li> 
<li data-value="469022" data-tags="TunChangXian" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=469022&citySigle=choiced">屯昌县</a></li> 
<li data-group="W" class="mui-table-view-divider mui-indexed-list-group">W</li> 
<li data-value="469006" data-tags="WanNingShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=469006&citySigle=choiced">万宁市</a></li> 
<li data-value="371000" data-tags="WeiHaiShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=371000&citySigle=choiced">威海市</a></li> 
<li data-value="370700" data-tags="WeiFangShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=370700&citySigle=choiced">潍坊市</a></li> 
<li data-value="610500" data-tags="WeiNanShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=610500&citySigle=choiced">渭南市</a></li> 
<li data-value="330300" data-tags="WenZhouShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=330300&citySigle=choiced">温州市</a></li> 
<li data-value="469005" data-tags="WenChangShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=469005&citySigle=choiced">文昌市</a></li> 
<li data-value="532600" data-tags="WenShanZhuangZuMiaoZuZiZhiZhou" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=532600&citySigle=choiced">文山壮族苗族自治州</a></li> 
<li data-value="150300" data-tags="WuHaiShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=150300&citySigle=choiced">乌海市</a></li> 
<li data-value="150900" data-tags="WuLanChaBuShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=150900&citySigle=choiced">乌兰察布市</a></li> 
<li data-value="650100" data-tags="WuLuMuQiShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=650100&citySigle=choiced">乌鲁木齐市</a></li> 
<li data-value="320200" data-tags="WuXiShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=320200&citySigle=choiced">无锡市</a></li> 
<li data-value="640300" data-tags="WuZhongShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=640300&citySigle=choiced">吴忠市</a></li> 
<li data-value="340200" data-tags="WuHuShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=340200&citySigle=choiced">芜湖市</a></li> 
<li data-value="450400" data-tags="WuZhouShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=450400&citySigle=choiced">梧州市</a></li> 
<li data-value="659004" data-tags="WuJiaQuShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=659004&citySigle=choiced">五家渠市</a></li> 
<li data-value="420100" data-tags="WuHanShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=420100&citySigle=choiced">武汉市</a></li> 
<li data-value="620600" data-tags="WuWeiShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=620600&citySigle=choiced">武威市</a></li> 
<li data-group="X" class="mui-table-view-divider mui-indexed-list-group">X</li> 
<li data-value="610100" data-tags="XiAnShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=610100&citySigle=choiced">西安市</a></li> 
<li data-value="630100" data-tags="XiNingShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=630100&citySigle=choiced">西宁市</a></li> 
<li data-value="532800" data-tags="XiShuangBanNaDaiZuZiZhiZhou" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=532800&citySigle=choiced">西双版纳傣族自治州</a></li> 
<li data-value="152500" data-tags="XiLinGuoLeMeng" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=152500&citySigle=choiced">锡林郭勒盟</a></li> 
<li data-value="350200" data-tags="XiaMenShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=350200&citySigle=choiced">厦门市</a></li> 
<li data-value="429004" data-tags="XianTaoShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=429004&citySigle=choiced">仙桃市</a></li> 
<li data-value="421200" data-tags="XianNingShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=421200&citySigle=choiced">咸宁市</a></li> 
<li data-value="610400" data-tags="XianYangShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=610400&citySigle=choiced">咸阳市</a></li> 
<li data-value="430300" data-tags="XiangTanShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=430300&citySigle=choiced">湘潭市</a></li> 
<li data-value="433100" data-tags="XiangXiTuJiaZuMiaoZuZiZhiZhou" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=433100&citySigle=choiced">湘西土家族苗族自治州</a></li> 
<li data-value="420600" data-tags="XiangYangShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=420600&citySigle=choiced">襄阳市</a></li> 
<li data-value="420900" data-tags="XiaoGanShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=420900&citySigle=choiced">孝感市</a></li> 
<li data-value="140900" data-tags="XinZhouShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=140900&citySigle=choiced">忻州市</a></li> 
<li data-value="410700" data-tags="XinXiangShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=410700&citySigle=choiced">新乡市</a></li> 
<li data-value="360500" data-tags="XinYuShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=360500&citySigle=choiced">新余市</a></li> 
<li data-value="411500" data-tags="XinYangShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=411500&citySigle=choiced">信阳市</a></li> 
<li data-value="152200" data-tags="XingAnMeng" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=152200&citySigle=choiced">兴安盟</a></li> 
<li data-value="130500" data-tags="XingTaiShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=130500&citySigle=choiced">邢台市</a></li> 
<li data-value="320300" data-tags="XuZhouShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=320300&citySigle=choiced">徐州市</a></li> 
<li data-value="411000" data-tags="XuChangShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=411000&citySigle=choiced">许昌市</a></li> 
<li data-value="341800" data-tags="XuanChengShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=341800&citySigle=choiced">宣城市</a></li> 
<li data-group="Y" class="mui-table-view-divider mui-indexed-list-group">Y</li> 
<li data-value="511800" data-tags="YaAnShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=511800&citySigle=choiced">雅安市</a></li> 
<li data-value="370600" data-tags="YanTaiShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=370600&citySigle=choiced">烟台市</a></li> 
<li data-value="610600" data-tags="YanAnShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=610600&citySigle=choiced">延安市</a></li> 
<li data-value="222400" data-tags="YanBianChaoXianZuZiZhiZhou" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=222400&citySigle=choiced">延边朝鲜族自治州</a></li> 
<li data-value="320900" data-tags="YanChengShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=320900&citySigle=choiced">盐城市</a></li> 
<li data-value="321000" data-tags="YangZhouShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=321000&citySigle=choiced">扬州市</a></li> 
<li data-value="441700" data-tags="YangJiangShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=441700&citySigle=choiced">阳江市</a></li> 
<li data-value="140300" data-tags="YangQuanShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=140300&citySigle=choiced">阳泉市</a></li> 
<li data-value="230700" data-tags="YiChunShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=230700&citySigle=choiced">伊春市</a></li> 
<li data-value="654000" data-tags="YiLiHaSaKeZiZhiZhou" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=654000&citySigle=choiced">伊犁哈萨克自治州</a></li> 
<li data-value="511500" data-tags="YiBinShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=511500&citySigle=choiced">宜宾市</a></li> 
<li data-value="420500" data-tags="YiChangShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=420500&citySigle=choiced">宜昌市</a></li> 
<li data-value="360900" data-tags="YiChunShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=360900&citySigle=choiced">宜春市</a></li> 
<li data-value="430900" data-tags="YiYangShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=430900&citySigle=choiced">益阳市</a></li> 
<li data-value="640100" data-tags="YinChuanShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=640100&citySigle=choiced">银川市</a></li> 
<li data-value="360600" data-tags="YingTanShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=360600&citySigle=choiced">鹰潭市</a></li> 
<li data-value="210800" data-tags="YingKouShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=210800&citySigle=choiced">营口市</a></li> 
<li data-value="431100" data-tags="YongZhouShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=431100&citySigle=choiced">永州市</a></li> 
<li data-value="610800" data-tags="YuLinShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=610800&citySigle=choiced">榆林市</a></li> 
<li data-value="450900" data-tags="YuLinShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=450900&citySigle=choiced">玉林市</a></li> 
<li data-value="632700" data-tags="YuShuCangZuZiZhiZhou" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=632700&citySigle=choiced">玉树藏族自治州</a></li> 
<li data-value="530400" data-tags="YuXiShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=530400&citySigle=choiced">玉溪市</a></li> 
<li data-value="430600" data-tags="YueYangShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=430600&citySigle=choiced">岳阳市</a></li> 
<li data-value="445300" data-tags="YunFuShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=445300&citySigle=choiced">云浮市</a></li> 
<li data-value="140800" data-tags="YunChengShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=140800&citySigle=choiced">运城市</a></li> 
<li data-group="Z" class="mui-table-view-divider mui-indexed-list-group">Z</li> 
<li data-value="370400" data-tags="ZaoZhuangShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=370400&citySigle=choiced">枣庄市</a></li> 
<li data-value="440800" data-tags="ZhanJiangShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=440800&citySigle=choiced">湛江市</a></li> 
<li data-value="430800" data-tags="ZhangJiaJieShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=430800&citySigle=choiced">张家界市</a></li> 
<li data-value="130700" data-tags="ZhangJiaKouShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=130700&citySigle=choiced">张家口市</a></li> 
<li data-value="620700" data-tags="ZhangYeShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=620700&citySigle=choiced">张掖市</a></li> 
<li data-value="350600" data-tags="ZhangZhouShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=350600&citySigle=choiced">漳州市</a></li> 
<li data-value="530600" data-tags="ZhaoTongShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=530600&citySigle=choiced">昭通市</a></li> 
<li data-value="441200" data-tags="ZhaoQingShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=441200&citySigle=choiced">肇庆市</a></li> 
<li data-value="321100" data-tags="ZhenJiangShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=321100&citySigle=choiced">镇江市</a></li> 
<li data-value="410100" data-tags="ZhengZhouShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=410100&citySigle=choiced">郑州市</a></li> 
<li data-value="442000" data-tags="ZhongShanShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=442000&citySigle=choiced">中山市</a></li> 
<li data-value="640500" data-tags="ZhongWeiShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=640500&citySigle=choiced">中卫市</a></li> 
<li data-value="330900" data-tags="ZhouShanShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=330900&citySigle=choiced">舟山市</a></li> 
<li data-value="411600" data-tags="ZhouKouShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=411600&citySigle=choiced">周口市</a></li> 
<li data-value="430200" data-tags="ZhuZhouShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=430200&citySigle=choiced">株洲市</a></li> 
<li data-value="440400" data-tags="ZhuHaiShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=440400&citySigle=choiced">珠海市</a></li> 
<li data-value="411700" data-tags="ZhuMaDianShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=411700&citySigle=choiced">驻马店市</a></li> 
<li data-value="512000" data-tags="ZiYangShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=512000&citySigle=choiced">资阳市</a></li> 
<li data-value="370300" data-tags="ZiBoShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=370300&citySigle=choiced">淄博市</a></li> 
<li data-value="510300" data-tags="ZiGongShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=510300&citySigle=choiced">自贡市</a></li> 
<li data-value="520300" data-tags="ZunYiShi" class="mui-table-view-cell mui-indexed-list-item"><a href="to_returnbackChoice?shengOrShi=520300&citySigle=choiced">遵义市</a></li> 
  
 							
					</ul>
				</div>
			</div>
		</div>
		<script src="static/js/mui.min.js"></script>
		<script src="static/js/mui.indexedlist.js"></script>
		<script type="text/javascript" charset="utf-8">
			mui.init();
			mui.ready(function() {
				var header = document.querySelector('header.mui-bar');
				var list = document.getElementById('list');
				//calc hieght
				list.style.height = (document.body.offsetHeight - header.offsetHeight) + 'px';
				//create
				window.indexedList = new mui.IndexedList(list);
			});
		</script>
	</body>

</html>