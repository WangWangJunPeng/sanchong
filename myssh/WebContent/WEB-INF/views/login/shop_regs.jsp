<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
    <head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link  rel="icon" href="static/images/titleLogo.png"  />
<title>中介门店注册</title>
<link rel="stylesheet" type="text/css" href="static/css/reset.css" />
<link rel="stylesheet" type="text/css" href="static/css/validation.css" />
<link rel="stylesheet" type="text/css"
	href="static/lib/laydate/need/laydate.css" />
<link rel="stylesheet" type="text/css" href="static/css/shopRegisterPage.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/jquery.dialogbox.css">
 <script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=7d1ed249eb6d3503e90179890468dd74&plugin=AMap.Autocomplete,AMap.PlaceSearch"></script>

<script type="text/javascript" src="static/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="static/lib/laydate/laydate.js"></script>
<script type="text/javascript" src="static/js/plugIn.js"></script>
<script type="text/javascript" src="static/js/jquery.validate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery.dialogBox.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/shopReg.js"></script>
<script type="text/javascript">
       
</script>
</head>
    <body>
        <div class="header">
            <img src="static/images/logofter.png" alt="" />
            <span class="line"></span>
            <span class="header-word">注册门店申请加盟</span>
        </div>
        <div class="container">
            <form action="shop_register" class="form-box" id="form-box" method="post" enctype="multipart/form-data" >
                <div class="form-div" >
                    <label  class="form-label" >公司名称<b>*</b></label>
                    <input type="text" name="companyName" class="form-input"  />
                </div>
                <div class="form-div" >
                    <label  class="form-label" >门店名称<b>*</b></label>
                    <input type="text" name="shopName" class="form-input"  />
                </div>
                <div class="form-div" id="" >
                    <label  class="form-label" >所在地<b>*</b></label>
						<select id="province" name="province" class="prov" style="margin-left:10px;">
						<option value="">请选择</option>
						<c:forEach var="p" items="${provinces}">
							<option value="${p.cityId }">${p.cityName }</option>
						</c:forEach>
						</select> 
						<select id="market" name="market" class="city">
							<option value="">请选择</option>
						</select> <select id="area" name="area" class="dist">
							<option value="">请选择</option>
						</select>
                </div>
                <div class="form-div" >
                    <label  class="form-label" >门店地址<b>*</b></label>
                    <input type="text" name="address"  id="address" class="form-input eer"  />
                    <input id="lngLat" type="hidden" name="lngLat">
                     <div  id="addressBox" style="width:300px;height: 200px;position:relative;left:140px;top:10px;" class="otherError"></div>
                </div>
                <div class='form-div'>
                    <label class="form-label">门头照片<b>*</b></label>
                    <div class="up-pic">
                        <img src="" id="newImg" > 
                        <span>+</span>
                        <input type="file" id="photoPicTwo"   hidden="hidden"/>
                        <input type="file" id="photoPic" name="photoPic" class="form-input file-pic"/>
                    </div>
                </div>
                 <div class='form-div'>
                    <label class="form-label">营业执照复印件<b>*</b></label>
                    <div class="up-pic">
                        <img src="" id="oldImg" > 
                        <span>+</span>
                        <input type="file" id="licensePic"  name="licensePic"  class="form-input file-pic"/>
                        
                    </div>
                </div>
                 <div class="form-div" >
                    <label  class="form-label" >门店营业执照号<b>*</b></label>
                    <input type="text" name="licenseNo" id="licenseNo" class="form-input"  />
                </div>
                 <div class="form-div" >
                    <label  class="form-label" >负责人姓名<b>*</b></label>
                    <input type="text" name="contactPerson" id="contactPerson" class="form-input"  />
                </div>
                 <div class="form-div" >
                    <label  class="form-label" >联系电话<b>*</b></label>
                    <input type="text" name="phone" id="phone" class="form-input"  /><a href="#" style="color:#2772DB;font-size: 10px;" id="phone_ok"></a><a href="#" style="color:#ff0000;font-size: 10px;" id="phone_exist"></a>
                </div>
                 <div class="form-div" >
                    <label  class="form-label" >Email<b>*</b></label>
                    <input type="text" name="email" id="email" class="form-input"  />
                </div>
                 <div class="form-div" >
                    <label  class="form-label" >银行卡号<b>*</b></label>
                    <input type="text" name="bank_cards" id="bank_cards" class="form-input"  />
                </div>
                 <div class="form-div" >
                    <label  class="form-label" >开户行名称<b>*</b></label>
                    <input type="text" name="bankName" id="bankName" class="form-input"  />
                </div>
                 <div class="form-div" >
                    <label  class="form-label" >银行卡开户名<b>*</b></label>
                    <input type="text" name="representative" id="representative" class="form-input"  />
                </div>
                <div class="applyJoin">
                    <input id="sub" type="submit" value="申请加盟" />
                    <font color="red" size="1">${data}</font>
                    <span id="noPhoto" style="color:red; size:1"></span>
                </div>
            </form>
        </div>
        <div id="photo_error"></div>
    </body>
    <script type="text/javascript">
    		//注册地图
    	var map = new AMap.Map("addressBox", {
           resizeEnable: true,
              zoom:15,
           	  
        });
        
        var autoOptions = {
            input: "address"
        };
       
        var auto = new AMap.Autocomplete(autoOptions);
        var placeSearch = new AMap.PlaceSearch({
            map: map
        });
       /* placeSearch.search($("#saleAddress").val()) */ 
        AMap.event.addListener(map, 'click', getLnglat);
        AMap.event.addListener(auto, "select", select);//注册监听，当选中某条记录时会触发
      function select(e){
    	
        	var aa = e.poi.location.lng;
        	var bb = e.poi.location.lat;
        	var an = aa + "," + bb;
        	
        	$("#lngLat").val(an);
        	
             placeSearch.setCity(e.poi.adcode);
            placeSearch.search(e.poi.name);//关键字查询查询
        } 
     
     
        //鼠标在地图上点击，获取经纬度坐标
    function getLnglat(e) {
            map.clearMap();
            Lng = e.lnglat.getLng();
            Lat = e.lnglat.getLat();
            var ll = Lng + "," + Lat
            $("#lngLat").val(ll);
            addMarker(e.lnglat.getLng(), e.lnglat.getLat());
            var lnglatXY=new AMap.LngLat(Lng,Lat);
            map.plugin(["AMap.Geocoder"],function(){                                                   
                geocoder=new AMap.Geocoder({
                    radius:1000, //以已知坐标为中心点，radius为半径，返回范围内兴趣点和道路信息
                    extensions: "all"//返回地址描述以及附近兴趣点和道路信息，默认"base"
                });
                //逆地理编码
                geocoder.getAddress(lnglatXY,function(status,result){
                    if (status === 'complete' && result.info === 'OK') {
                        geocoder_CallBack(result);
                    }
                });
            });
       }  
    function addMarker(j, w){
           var marker = new AMap.Marker({
            icon:"static/images/location.png",
               position: new AMap.LngLat(j, w),
               title: new AMap.LngLat(j, w),
               draggable: true
           });
           marker.setMap(map);
            
    }
    function geocoder_CallBack(data) {
        var address = data.regeocode.formattedAddress; //返回地址描述
        document.getElementById("address").value = address;
    }
    
    		
            //门头照片
            function newFile() {
            	//console.log($("#photoPic").get(0).files[0]);
            	
                    var windowURL = window.URL || window.webkitURL;
                    var loadImg = windowURL.createObjectURL(document.getElementById('photoPic').files[0]);
                   
                    document.getElementById('newImg').setAttribute('src',loadImg);
            }  
            //营业执照复印件
            function oldFile() {
                var windowURL = window.URL || window.webkitURL;
                var loadImg = windowURL.createObjectURL(document.getElementById('licensePic').files[0]);
                document.getElementById('oldImg').setAttribute('src',loadImg);
            } 
    </script>
</html>