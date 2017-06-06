<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link  rel="icon" href="static/images/titleLogo.png"  />
        <title>案场助理个人中心-案场信息维护</title>
        <link rel="stylesheet" type="text/css" href="static/css/messageProtect.css" />
        <link rel="stylesheet" type="text/css" href="static/css/validation.css" />
        <link rel="stylesheet" type="text/css" href="static/css/reset.css" />
        <link rel="stylesheet" type="text/css" href="static/lib/laydate/need/laydate.css" />
        <link rel="stylesheet" href="http://cache.amap.com/lbs/static/main1119.css"/>
        <script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=7d1ed249eb6d3503e90179890468dd74&plugin=AMap.Autocomplete,AMap.PlaceSearch"></script>
        <script type="text/javascript" src="static/js/jquery-3.1.1.min.js"></script>
        <script type="text/javascript" src="static/js/jquery.validate.js"></script>
         <script type="text/javascript" src="static/js/validation.js"></script>
        <script type="text/javascript" src="static/lib/laydate/laydate.js"></script>
        <script type="text/javascript" src="static/js/messageProtect.js"></script>
		<script type="text/javascript">
			$(document).ready(function() {
				getProvinceInfo();
				bindingChange();
				getDataAllAppoint();
			});
			function getDataAllAppoint(){
				$.ajax({
					type : "post", 
					async : false,
					url:"to_msg_pro",
					success:function(data){
						data = eval("("+data+")");
						renderAllAppoint(data.data);
					},
					error:function(){
						alert("信息获取失败");
					}
				});
			}
			
			function renderAllAppoint(data){
				//alert(data.hasOwnProperty("projectId"));
				if(data.hasOwnProperty("projectId")){
					$("#projectName").val(data.projectName);
					$("#city").val(data.city);
					var array = data.city.split("-");
					
					if(array.length>0){
						$("#prov4").find("option[value="+array[0]+"]").prop("selected",true);
						getShiMenue();
						$("#city4").find("option[value="+array[1]+"]").prop("selected",true);
						changeMarket();
						$("#area4").find("option[value="+array[2]+"]").prop("selected",true);
						
						$("#hidprov4").val($("#prov4").val());
						$("#hidcity4").val($("#city4").val());
						$("#hidarea4").val($("#area4").val());
					}
					$("#landArea").val(data.landArea);
					$("#buildArea").val(data.buildArea);
					$("#groundArea").val(data.groundArea);
					$("#underGroundArea").val(data.underGroundArea);
					$("#unitCount").val(data.unitCount);
					$("#volumeRatio").val(data.strVolumeRatio);
					$("#density").val(data.strDensity);
					$("#afforestationRatio").val(data.strAfforestationRatio);
					if(data.propertyType==0){
						$("#propertyType").find("option[value=0]").prop("selected",true);
					}else if(data.propertyType==1){
						$("#propertyType").find("option[value=1]").prop("selected",true);
					}else if(data.propertyType==2){
						$("#propertyType").find("option[value=2]").prop("selected",true);
					}else if(data.propertyType==3){
						$("#propertyType").find("option[value=3]").prop("selected",true);
					}
					$("#saleAddress").val(data.saleAddress);
					$("#propertyAddress").val(data.propertyAddress);
					$("#district").val(data.district);
					$("#averagePrice").val(data.strAveragePrice);
					$("#developer").val(data.developer);
					$("#investor").val(data.investor);
					$("#manager").val(data.manager);
					$("#propertyCost").val(data.strPropertyCost);
					$("#decorationStandard").val(data.decorationStandard);
					$("#deliverStandard").val(data.deliverStandard);
					$("#start").val(data.startTime);
					$("#end").val(data.deliverTime);
					$("#rightsYears").val(data.rightsYears);
					$("#presalePermissionURL").val(data.presalePermissionURL);
					$("#picURL").attr("src",data.presalePermissionURL);
					$("#tags").val(data.tags);
					$("#description").val(data.description);
					$("#projectId").val(data.projectId);
					$("#saleAddressGis").val(data.saleAddressGis);
					$("#propertyAddressGis").val(data.propertyAddressGis);
					console.log("sale:"+data.saleAddressGis);
					console.log("proper:"+data.propertyAddressGis);
				}
			}
			
			function getProvinceInfo(){
				$.ajax({
					type : "post",
					async : false,
					url : "menu_list_province_first",
					success : function(data) {
						data = eval("(" + data + ")");
						setProvinceInfo(data.root);
					}
				});
			}
			
			function setProvinceInfo(data){
				for(var i=0;i<data.length;i++){
					$("<option value='"+data[i].cityId+"'>"+data[i].cityName+"</option> ").appendTo($("#prov4"))
				}
				getShiMenue();
			}
			
			function bindingChange(){
				$("#prov4").change(function(){
					getShiMenue();
				})
				$("#city4").change(function(){
					changeMarket();
				})
			}
			
			function getShiMenue(){
        			var pId = $("#prov4").val();
        			$("#city4").empty();
        			$.ajax({
    					type : "post",
    					async : false,
    					url : "menu_list_city_area",
    					data:{"shengOrShi":pId},
    					success : function(data) {
    						data = eval("(" + data + ")");
    						for(var i=0;i<data.root.length;i++){
    	    					$("<option value='"+data.root[i].cityId+"'>"+data.root[i].cityName+"</option> ").appendTo($("#city4"))
    	    				}
    	    				changeMarket();
    					}
    				});
			}
			
       		function changeMarket(){
       			var pId = $("#city4").val();
       			$("#area4").empty();
       			$.ajax({
					type : "post",
					async : false,
					url : "menu_list_city_area",
					data:{"shengOrShi":pId},
					success : function(data) {
						data = eval("(" + data + ")");
						for(var i=0;i<data.root.length;i++){
							 $("<option value='"+data.root[i].cityId+"'>"+data.root[i].cityName+"</option> ").appendTo($("#area4"));
						 }
					}
				});
       		}
		</script>
	</head>
    <body>
        <!-- <div class="header">
            <a href="###" class="self-center">案场助理个人中心</a>
            <ul>
                <li><a href="to_pro_index">首页</a></li>
               <li><a href="">案场信息维护</a></li>
               <li><a href="">价格优惠条款</a></li>
               <li><a href="">房源管理</a></li>
               <li><a href="">上下架管理</a></li>
               <li><a href="">客户管理</a></li>
               <li><a href="">成交业务</a></li>
               <li><a href="">账号管理</a></li>
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
                        <li><a href="to_pro_baseInfo" style="color:#199ed8">基本信息</a></li>
                        <li><a href="to_effectPic">效果图</a></li>
                        <li><a href="">户型</a></li>
                        <li><a href="">银行账号</a></li>
                        <li><a href="">带看业务定义</a></li>
                        <li><a href="">预售证管理</a></li>
                        <li><a href="">认购规则</a></li>
                    </ul>
                </div> -->
                <div class="main-content">
                    <div class="basic-information">
                    	<input id="saleAddressGis" type="hidden">
                    	<input id="propertyAddressGis" type="hidden">
                        <form class="form-box" action="save_pro_info" method="post" id="basicForm">
                            <div class="form-div">
                                <label  class="form-label" >项目名称<b>*</b></label>
                                <input id="projectName" type="text" name="projectName" class="form-input project-name" readonly="readonly"/>
                            </div>
                            <div class="form-div">
                                 <label class="form-label" >所在地<b>*</b></label>
                                 <div id="city_4" class="city-action">
                                     <select name="province" class="prov" id="prov4" disabled="disabled"></select>
                                     <select name="market" class="city" id="city4" disabled="disabled"></select>
                                     <select name="area" class="dist" id="area4" disabled="disabled"></select>
                                     <input id="hidprov4" type="hidden" name="province">
                                     <input id="hidcity4" type="hidden" name="market">
                                     <input id="hidarea4" type="hidden" name="area">
                                 </div>
                            </div>
                            <div class="form-div">
                                 <label class="form-label" >项目用地面积<b>*</b></label>
                                 <input id="landArea" type="text" name="landArea" class="form-input project-area1"/>
                                 <span>平方米</span>
                            </div>
                            <div class="form-div">
                                 <label  class="form-label">项目总建筑面积<b>*</b></label>
                                 <input id="buildArea" type="text" name="buildArea" class="form-input project-area1"/>
                                 <span>平方米</span>
                            </div>
                            <div class="form-div">
                                 <label  class="form-label">地上建筑面积<b>*</b></label>
                                 <input id="groundArea" type="text" name="groundArea" class="form-input project-area1" />
                                 <span>平方米</span>
                            </div>
                            <div class="form-div">
                                 <label  class="form-label">地下建筑面积<b>*</b></label>
                                 <input id="underGroundArea" type="text" name="underGroundArea" class="form-input project-area1"/>
                                 <span>平方米</span>
                            </div>
                            <div class="form-div">
                                 <label  class="form-label">总户数<b>*</b></label>
                                 <input id="unitCount" type="text" name="unitCount" class="form-input project-area1"/>
                                 <span>户</span>
                            </div>
                            <div class="form-div">
                                 <label  class="form-label">容积率<b>*</b></label>
                                 <input id="volumeRatio" type="text" name="volumeRatio" class="form-input project-area1"/>
                            </div>
                            <div class="form-div">
                                 <label  class="form-label">密度<b>*</b></label>
                                 <input id="density" type="text" name="density" class="form-input project-area1"/>
                                 <span>%</span>
                            </div>
                            <div class="form-div">
                                 <label  class="form-label">绿化率<b>*</b></label>
                                 <input id="afforestationRatio" type="text" name="afforestationRatio" class="form-input project-area1"/>
                                  <span>%</span>
                            </div>
                            <div class="form-div" style="position:relative">
                                 <label  class="form-label">售楼处地址<b>*</b></label>
                                 <input id="saleAddress" type="text" name="saleAddress" class="form-input area2 eer" />
                                 <input id="saleGis" type="hidden" name="saleAddressGis">
                                 <div  id="containerOne" style="width:400px;height: 200px;position:relative;left:140px;top:10px;" class="otherError"></div>
                            </div>
                            <div class="form-div">
                                 <label  class="form-label">物业类型<b>*</b></label>
                                 <select id="propertyType" name="propertyType"  class="standard ">
                                     <option value="0">住宅物业</option>
                                     <option value="1">商业物业</option>
                                     <option value="2">工业物业</option>
                                     <option value="3">其他物业</option>
                                 <select>
                                 <!-- <input id="propertyType" type="text" name="propertyType" class="form-input property-style"/> -->
                            </div>
                            <div class="form-div" style="position:relative">
                                 <label  class="form-label">项目地址<b>*</b></label>
                                 <input id="propertyAddress" type="text" name="propertyAddress" class="form-input area2 eed"/>
                                 <input id="proGis" type="hidden" name="propertyAddressGis">
                                 <div  id="containerTwo" style="width:400px;height: 200px;position:relative;left:140px;top:10px;" class="otherErrorTwo"></div>
                            </div>
                            <div class="form-div">
                                 <label  class="form-label">分区<b>*</b></label>
                                 <input id="district" type="text" name="district" class="form-input zone"/>
                            </div>
                            <div class="form-div">
                                 <label  class="form-label">均价<b>*</b></label>
                                 <input id="averagePrice" type="text" name="averagePrice" class="form-input average-price"/>
                                 <span>元/平方米</span>
                            </div>
                            <div class="form-div">
                                 <label  class="form-label">开发商<b>*</b></label>
                                 <input id="developer" type="text" name="developer" class="form-input area2"/>
                            </div>
                            <div class="form-div">
                                 <label  class="form-label">投资商<b>*</b></label>
                                 <input id="investor" type="text" name="investor" class="form-input area2"/>
                            </div>
                            <div class="form-div">
                                 <label  class="form-label">物业管理归属<b>*</b></label>
                                 <input id="manager" type="text"  name="manager" class="form-input area2"/>
                            </div>
                            <div class="form-div">
                                 <label  class="form-label">物业费<b>*</b></label>
                                 <input id="propertyCost" type="text" name="propertyCost" class="form-input property-fee"/>
                                 <span>元/平方米</span>
                            </div>
                            <div class="form-div">
                                 <label  class="form-label">装修标准<b>*</b></label>
                                 <select id="decorationStandard" name="decorationStandard"  class="standard">
                                     <option value="0">毛坯</option>
                                     <option value="1">普通装修</option>
                                     <option value="2">精装修</option>
                                     <option value="3">家具全配</option>
                                     <option value="4">家电全配</option>
                                 </select>
                            </div>
                            <div class="form-div">
                                 <label  class="form-label">交付标准<b>*</b></label>
                                 <textarea id="deliverStandard" name="deliverStandard"  cols="50" rows="10"></textarea>
                            </div>
                            <div class="form-div">
                                 <label  class="form-label">开工时间<b>*</b></label>
                                 <input class="laydate-icon icon-li form-input start-time" name="startTime" id="start" />
                            </div>
                            <div class="form-div">
                                 <label  class="form-label">交付时间<b>*</b></label>
                                 <input class="laydate-icon icon-li form-input end-time" name="deliverTime" id="end" />
                            </div>
                            <div class="form-div">
                                <label  class="form-label">产权年限<b>*</b></label>
                                 <input id="rightsYears" type="text" name="rightsYears" class="form-input equity-date"/>
                                 <span>年</span>
                            </div>
                            <div class="form-div"> 
                                <label  class="form-label">说明</label>
                                <textarea id="description" name="description"  cols="50" rows="5"></textarea>
                            </div>
                            <input id="projectId" type="hidden" name="projectId"/>
                            <input id="presalePermissionURL" type="hidden" name="presalePermissionURL"/>
                            <div class="btn">
                                <input name="isSave" type="submit" class="btn1" value="保存" />
                                <input name="isSave" type="submit"  class="btn2" value="保存并进入效果图" />
                            </div>
                        </form>
                    </div>
                    
                </div>
        </div>
        
    </body>
   <script type="text/javascript">
   //三级联动
   /*  var selectVal = new CitySelect({
        data   : data,
        provId : "#prov4",
        cityId : '#city4',
        areaId : '#area4'
    }); */
    //日期
    var start = {
          elem: '#start',
          format: 'YYYY-MM-DD hh:mm:ss',
          //min: laydate.now(), //设定最小日期为当前日期
          max: '2099-06-16 23:59:59', //最大日期
          istime: true,
          istoday: false,
          choose: function(datas){
             end.min = datas; //开始日选好后，重置结束日的最小日期
             end.start = datas  //将结束日的初始值设定为开始日
          }
        };
    var end = {
      elem: '#end',
      format: 'YYYY-MM-DD hh:mm:ss',
      //min: laydate.now(),
      max: '2099-06-16 23:59:59',
      istime: true,
      istoday: false,
      choose: function(datas){
        start.max = datas; //结束日选好后，重置开始日的最大日期
      }
    };
    laydate(start);
    laydate(end); 
    </script>
    <script>
    $(document).ready(function(){ 
    	var ff;
    	var gg;
    	if($("#saleAddressGis").val() != ""){
    		ff = $("#saleAddressGis").val().split(",")[0];
    		gg = $("#saleAddressGis").val().split(",")[1];
    		$("#saleGis").val($("#saleAddressGis").val())
    	}else{
    		ff = 116.397428;
    		gg = 39.90923;
    		$("#saleGis").val($("#saleAddressGis").val())
    	}
        var map = new AMap.Map("containerOne", {
            resizeEnable: true,
              zoom:15,
           	center:[ff,gg]   
        });
        addMarker(ff,gg);
        var autoOptions = {
            input: "saleAddress"
        };
        var auto = new AMap.Autocomplete(autoOptions);
        var placeSearch = new AMap.PlaceSearch({
            map: map
        });
       /* placeSearch.search($("#saleAddress").val()) */ 
        AMap.event.addListener(map, 'click', getLnglat);
        AMap.event.addListener(auto, "select", select);//注册监听，当选中某条记录时会触发
      function select(e){
    	   console.log(e.poi)
        	var aa = e.poi.location.lng;
        	var bb = e.poi.location.lat;
        	var an = aa + "," + bb;
        	console.log(an)
        	$("#saleGis").val(an);
        	
        	
        	
            placeSearch.setCity(e.poi.adcode);
            placeSearch.search(e.poi.name);
            
           
            
        }
   
        //鼠标在地图上点击，获取经纬度坐标
     function getLnglat(e) {
            map.clearMap();
            
            Lng = e.lnglat.getLng();
            Lat = e.lnglat.getLat();
            var ll = Lng + "," + Lat;
            
            	$("#saleGis").val(ll);
            
            
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
        document.getElementById("saleAddress").value = address;
    }
    })
    </script>
    <script>
    
$(document).ready(function(){ 
    	   //物业地址
   		var kk;
    	var hh;   	
    	if($("#propertyAddressGis").val() != ""){
    		kk = $("#propertyAddressGis").val().split(",")[0];
    		hh = $("#propertyAddressGis").val().split(",")[1];
    		$("#proGis").val($("#propertyAddressGis").val());
    	}else{
    		kk = 116.397428;
    		hh = 39.90923;
    		$("#proGis").val($("#propertyAddressGis").val());
    	}
    var mapTwo = new AMap.Map("containerTwo", {
        resizeEnable: true,
         zoom:15,
         center:[kk,hh] 
    });
    addMarkerT(kk,hh);
    var autoOptionTwo = {
        input: "propertyAddress"
    };
    var autoTwo = new AMap.Autocomplete(autoOptionTwo);
    var placeSearchTwo = new AMap.PlaceSearch({
        map: mapTwo
    });
      /* placeSearchTwo.search($("#propertyAddress").val())  */ 
    AMap.event.addListener(mapTwo, 'click', getLnglatT);
    AMap.event.addListener(autoTwo, "select", selectT);

function selectT(d) {
 
    	var ab = d.poi.location.lng;
    	var cd = d.poi.location.lat;
    	var aw = ab + "," + cd;
    	$("#proGis").val(aw); 
            placeSearchTwo.setCity(d.poi.adcode);
            placeSearchTwo.search(d.poi.name);  //关键字查询查询
        }
function getLnglatT(d){
        mapTwo.clearMap();
        Lng = d.lnglat.getLng();
        Lat = d.lnglat.getLat();
        var ll = Lng+","+Lat;
        $("#proGis").val(ll);
        addMarkerT(d.lnglat.getLng(), d.lnglat.getLat());  
        var lnglatXYTwo=new AMap.LngLat(Lng,Lat);
        mapTwo.plugin(["AMap.Geocoder"],function(){                                                   
            geocoder=new AMap.Geocoder({
                radius:1000, //以已知坐标为中心点，radius为半径，返回范围内兴趣点和道路信息
                extensions: "all"//返回地址描述以及附近兴趣点和道路信息，默认"base"
            });
            //逆地理编码
            geocoder.getAddress(lnglatXYTwo,function(status,result){
                if (status === 'complete' && result.info === 'OK') {
                    geocoder_CallBackTwo(result);
                }
            });
        });
   }
function addMarkerT(j,w){
       var markerTwo= new AMap.Marker({
          icon:"static/images/location.png",
           position: new AMap.LngLat(j, w),
           title: new AMap.LngLat(j, w),
           draggable: true
       });
       markerTwo.setMap(mapTwo);
}
function geocoder_CallBackTwo(data) {
    var addressTwo = data.regeocode.formattedAddress; //返回地址描述
    document.getElementById("propertyAddress").value = addressTwo;
}
    
    }) 

   </script>
</html>