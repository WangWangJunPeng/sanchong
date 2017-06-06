<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>地图</title>
        <link rel="stylesheet" href="http://cache.amap.com/lbs/static/main1119.css"/>
        <script type="text/javascript" src="static/js/jquery-3.1.1.min.js"></script>
         
        <script type="text/javascript" src="city2.js"></script>
         <script type="text/javascript" src="citySelect2.js"></script>

        <script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=7d1ed249eb6d3503e90179890468dd74&plugin=AMap.Autocomplete,AMap.PlaceSearch"></script>
        
    </head>
    <body>
	    <div id="city_4" style="position:fixed;z-index:1000">
	        <select class="prov" id="prov4" ></select>
	        <select class="city" id="city4" ></select>
	        <input type="hidden" id="jin" >
	        <input type="hidden" id="wei" >
	        <button onclick="getGis()">搜索</button>
	    </div>
        
        <div id="container"  ></div>
    <script type="text/javascript">
		$(document).ready(function(){
			getProvinceInfo();
			bindingChange();
		})
           //省市动态菜单
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
					$("<option value='"+data.root[i].cityId+"' class='gh'>"+data.root[i].cityName+"</option> ").appendTo($("#city4"))
				}
				
			}
		});
}
    function bindingChange(){
		$("#prov4").change(function(){
			getShiMenue();
		})
		
	}
    function getGis(){
    	
    	$.ajax({
			type : "post",
			dataType: 'json',
			//async : false,
			url : "getProAndShopInfo",
			data:{prvo:$("#prov4").val(),city:$("#city4").val()},
			success : function(data){
				 //console.log(data.pro)
				 searchInfo(data.shop,data.pro);
				
 
				
			}
		});
    }
    var outArry = [];
	var proArry = [];
    function searchInfo(info,pro){
    	
    	//门店
    	$.each(info,function(v,o){
    		
    		if (o.lngLat != null && o.lngLat !=""){
	    		
	    		var cc = o.lngLat;
	    		var bb = cc.split(",");
	    		bb.push(o.address);
	    		bb.push(o.shopName);
	    		bb.push(o.phone);
	    		bb.push(o.inSystemStutas)
	    		
	    		
	    		outArry.push(bb);	
	    		
    		}	
    	})
    	//案场
    	$.each(pro,function(v,o){
    		
    		if (o.saleAddressGis != null && o.saleAddressGis !=""){
	    		
	    		var cc = o.saleAddressGis;
	    		var bb = cc.split(",");
	    		bb.push(o.saleAddress);
	    		bb.push(o.projectName);
	    		
	    		bb.push(o.proInSystemStutas)
	    		
	    
	    		proArry.push(bb);	
    		}	
    	})
    	
    	/* $("#jin").val(proArry[0][0]);
    	
    	$("#wei").val(proArry[0][1]) */
    	sendLngLat(outArry,proArry);
    	//console.log(outArry[0][0])
    	$("#jin").val(outArry[0][0])
    }
   
        var map = new AMap.Map('container', {
		resizeEnable: true,
        zoom:13,
        
        //center: [$("#jin").val(),$("#wei").val()]
        });
        
        
	     map.plugin(["AMap.ToolBar"], function() {
	        map.addControl(new AMap.ToolBar());
	    }); 
         
        

	    var infoWindow = new AMap.InfoWindow({
	
	    });     


    //var main_id = 0;
	    function sendLngLat(lnglats,proGis){
	    	//门店
	    	console.log($("#jin").val());
	    	
	    	   for(var i= 0;i<lnglats.length;i++){
	    	     if(lnglats[i][5]==1){  
	    	    	 
	                var  marker=new AMap.Marker({
                         position:lnglats[i],
                         map:map,
                        
                         icon: new AMap.Icon({
                             //size: new AMap.Size(20, 30),
                             
                             image: "static/images/shopG.png"     //自定义的标记图片样式(图片需自己准备)
                         })
	                })
	    	      }
	    	     if(lnglats[i][5]==0){       	    
		                var  marker=new AMap.Marker({
	                         position:lnglats[i],
	                         map:map,
	                        
	                         icon: new AMap.Icon({
	                             //size: new AMap.Size(20, 30),
	                             
	                             image: "static/images/shopH.png"     //自定义的标记图片样式(图片需自己准备)
	                         })
		                })
		    	      }
	                 content = [];
	             	 content.push("中介名称："+lnglats[i][3]);
	               		content.push("电话："+lnglats[i][4]);
	               		content.push("地址："+lnglats[i][2]);
	              
	               marker.content = content;
	                 

	               marker.on('dblclick',openAmap);     //这里采用调到新页面方式导航，也可直接定义带导航功能的信息窗体，请参考http://lbs.amap.com/api/javascript-api/example/infowindow/infowindow-has-search-function/
	               //给Marker绑定单击事件
	               marker.on('mouseover', markerOver);
	           } 
	    	  //案场
	     	  for(var i= 0;i<proGis.length;i++){
		    	     if(proGis[i][4]==1){       	    
		                var  marker=new AMap.Marker({
	                         position:proGis[i],
	                         map:map,
	                        
	                         icon: new AMap.Icon({
	                             size: new AMap.Size(40, 40),
	                             
	                             image: "static/images/anG.png"     //自定义的标记图片样式(图片需自己准备)
	                         })
		                })
		    	      }
		    	     if(proGis[i][4]==0){       	    
			                var  marker=new AMap.Marker({
		                         position:proGis[i],
		                         map:map,
		                        
		                         icon: new AMap.Icon({
		                             size: new AMap.Size(40, 40),
		                             
		                             image: "static/images/anH.png"     //自定义的标记图片样式(图片需自己准备)
		                         })
			                })
			    	      }
		                 content = [];
		             	 content.push("案场名称："+proGis[i][3]);
		               		
		               		content.push("地址："+proGis[i][2]);
		              
		               marker.content = content;
		                 

		               marker.on('dblclick',openAmap);     //这里采用调到新页面方式导航，也可直接定义带导航功能的信息窗体，请参考http://lbs.amap.com/api/javascript-api/example/infowindow/infowindow-has-search-function/
		               //给Marker绑定单击事件
		               marker.on('mouseover', markerOver);
		           }
	    	   
	    	  
	    }
  

    map.setFitView();
    //跳至地图当中导航
    function openAmap(e){
        e.target.markOnAMAP({
            name:e.target.title,
            position:e.target.getPosition()
        })
    }
    //信息窗口
    function markerOver(e){
        infoWindow.setContent(e.target.content.join('<br/>'));
        infoWindow.open(map, e.target.getPosition());
    }
    
   
    </script>
    </body>
</html>
