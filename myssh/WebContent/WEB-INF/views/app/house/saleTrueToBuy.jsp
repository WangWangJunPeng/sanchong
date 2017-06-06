<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
    <meta charset="utf-8">
    <meta content="yes" name="apple-mobile-web-app-capable">
	<meta content="yes" name="apple-touch-fullscreen">
	<meta content="telephone=no,email=no" name="format-detection">
    <link href="<%=request.getContextPath()%>/static/css/mui.css" rel="stylesheet"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/spareResult.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/apply.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/suitable.css" />
    <script src="<%=request.getContextPath()%>/static/js/flexible.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/static/js/mui.min.js"></script>
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
    <script type="text/javascript" charset="utf-8">
      	mui.init();
      	
      	$(document).ready(function() {
      		getDateInfo();
      	});
      	function getDateInfo(){
			$.ajax({
				type : "post",
				//async : false,
				url : "to_saleTureBuy",
				data : {recordNo:$("#recordNo").val()},
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
      		if(data.hasOwnProperty("recordNo")){
      			$("#houseNum").val(data.houseNum);
      			$("#houseNo").val(data.houseNo);
      			$("#presalePermissionInfo").val(data.presalePermissionInfo);
      			$("#buildingNo").val(data.buildingNo);
      			$("#caption").val(data.caption);
      			$("#listPrice").val(data.listPrice);
      			$("#customerName").val(data.customerName);
      			$("#customerPhone").val(data.customerPhone);
      			$("#description").val(data.dposit + data.description);
      			
      		}
      	}
      	
    </script>
     <title>确认下定</title>
</head>
<body>
	<header class="mui-bar mui-bar-nav mui-bar-transparent">
		<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
		<h1 class="mui-title">确认下定</h1>
	</header> 
	<div class="mui-content">
		
		<form id="" class="listMess" action="to_confirmMoneyAgent" method="post" enctype="multipart/form-data">
			<input type="hidden" name="recordNo" value="${data }" id="recordNo">
			<p>
				<img src="<%=request.getContextPath()%>/static/images/1.png" alt="" />
				<span class="leftName">ID</span>
				<input readonly="readonly" class="rightVal" id="houseNum" name="houseNum"/>
			</p>
			<p>
				<img src="<%=request.getContextPath()%>/static/images/2.png" alt="" />
				<span class="leftName">房号</span>
				<input readonly="readonly" class="rightVal" id="houseNo" name="houseNo"/>
			</p>
			<p>
				<img src="<%=request.getContextPath()%>/static/images/3.png" alt="" />
				<span class="leftName">预售证号</span>
				<input readonly="readonly" class="rightVal" id="presalePermissionInfo" name="presalePermissionInfo"/>
			</p>
			<p>
				<img src="<%=request.getContextPath()%>/static/images/4.png" alt="" />
				<span class="leftName">楼栋号</span>
				<input readonly="readonly" class="rightVal" id="buildingNo" name="buildingNo"/>
			</p>
			<p>
				<img src="<%=request.getContextPath()%>/static/images/5.png" alt="" />
				<span class="leftName">户型</span>
				<input readonly="readonly" class="rightVal" id="caption" name="caption"/>
			</p>
			<p>
				<img src="<%=request.getContextPath()%>/static/images/6.png" alt="" />
				<span class="leftName">价格</span>
				<input readonly="readonly" class="rightVal" id="listPrice" name="listPrice"/>
			</p>
			<p>
				<img src="<%=request.getContextPath()%>/static/images/7.png" alt="" />
				<span class="leftName">客户姓名</span>
				<input readonly="readonly" class="rightVal" id="customerName" name="customerName"/>
			</p>
			<p>
				<img src="<%=request.getContextPath()%>/static/images/8.png" alt="" />
				<span class="leftName">联系电话</span>
				<input readonly="readonly" class="rightVal" id="customerPhone" name="customerPhone"/>
			</p>
			<p>
				<img src="<%=request.getContextPath()%>/static/images/12.png" alt="" />
				<lable class="leftName">身份证号</lable>
				<input type="text" placeholder="请填写" name="customerIDCard" id="customerIDCard"/>
			</p>
			<div class="evidence">
				<img src="<%=request.getContextPath()%>/static/images/10.png" alt="" />
				<span class="leftName">上传凭证</span>	
					<input type="radio" name="payWay" id="wangyin" value="wangyin" />
					<label for="wangyin">网银汇款</label>
					<input type="radio" name="payWay" id="face" value="face" />
					<label for="face">现场交款</label>
					<input type="radio" name="payWay" id="airplay" value="airplay" />
					<label for="airplay">支付宝</label>
				<input type="file" id="referPhoto" name="pic"/>
				<img id="takephoto" src="<%=request.getContextPath()%>/static/images/takephoto.png"/>
			</div>
			<p>
				<img src="<%=request.getContextPath()%>/static/images/11.png" alt="" />
				<input readonly="readonly" class="leftName" id="description" name="description"/><span>说明</span>
			</p>
			<textarea name="" rows="8" cols="30" autofocus="autofocus" id="description">
				</textarea>
			<button class="apply-btn" type="submit">确定打款</button>
		</form>
	</div>

</body>
	<script src="<%=request.getContextPath()%>/static/js/mui.min.js" type="text/javascript" charset="utf-8"></script>
	<script>
		mui.init({
			swipeBack:true //启用右滑关闭功能
		});
		/*ios点击照片img触发*/
		 document.getElementById('takephoto').addEventListener('click', function() {
			 function setupWebViewJavascriptBridge(callback) {
				    if (window.WebViewJavascriptBridge) { return callback(WebViewJavascriptBridge); }
				    if (window.WVJBCallbacks) { return window.WVJBCallbacks.push(callback); }
				    window.WVJBCallbacks = [callback];
				    var WVJBIframe = document.createElement('iframe');
				    WVJBIframe.style.display = 'none';
				    WVJBIframe.src = 'https://__bridge_loaded__';
				    document.documentElement.appendChild(WVJBIframe);
				    setTimeout(function() { document.documentElement.removeChild(WVJBIframe) }, 0)
				}
			 setupWebViewJavascriptBridge(function(bridge) {

				    /* Initialize your app here */

				    bridge.registerHandler('JS Echo', function(data, responseCallback) {
				        console.log("JS Echo called with:", data)
				        responseCallback(data)
				    })
				    bridge.callHandler('ObjC Echo', {'key':'value'}, function responseCallback(responseData) {
				        console.log("JS received response:", responseData)
				    })
				})

		 }, false); 
		/*点击照片img触发*/
		 document.getElementById('takephoto').addEventListener('click', function() {
			 if (mui.os.plus) { 
                var a = [{ 
                    title: "拍照" 
                }, { 
                    title: "从手机相册选择" 
                }]; 
                plus.nativeUI.actionSheet({ 
                    title: "修改用户头像", 
                    cancel: "取消", 
                    buttons: a 
                }, function(b) { /*actionSheet 按钮点击事件*/ 
                    switch (b.index) { 
                        case 0: 
                            break; 
                        case 1: 
                            getImage(); /*拍照*/ 
                            break; 
                        case 2: 
                            galleryImg();/*打开相册*/ 
                            break; 
                        default: 
                            break; 
                    } 
                }) 
            } 
        }, false); 
		
		//拍照 
        function getImage() { 
            var c = plus.camera.getCamera(); 
            c.captureImage(function(e) { 
                plus.io.resolveLocalFileSystemURL(e, function(entry) { 
                    var s = entry.toLocalURL() + "?version=" + new Date().getTime(); 
                    uploadHead(s); /*上传图片*/ 
                }, function(e) { 
                    console.log("读取拍照文件错误：" + e.message); 
                }); 
            }, function(s) { 
                console.log("error" + s); 
            }, { 
                filename: "_doc/head.png" 
            }) 
        } 

		//本地相册选择 
        function galleryImg() { 
            plus.gallery.pick(function(a) { 
                plus.io.resolveLocalFileSystemURL(a, function(entry) { 
                    plus.io.resolveLocalFileSystemURL("_doc/", function(root) { 
                        root.getFile("head.png", {}, function(file) { 
                            //文件已存在 
                            file.remove(function() { 
                                console.log("file remove success"); 
                                entry.copyTo(root, 'head.png', function(e) { 
                                        var e = e.fullPath + "?version=" + new Date().getTime(); 
                                        uploadHead(e); /*上传图片*/ 
                                        //变更大图预览的src 
                                        //目前仅有一张图片，暂时如此处理，后续需要通过标准组件实现 
                                    }, 
                                    function(e) { 
                                        console.log('copy image fail:' + e.message); 
                                    }); 
                            }, function() { 
                                console.log("delete image fail:" + e.message); 
                            }); 
                        }, function() { 
                            //文件不存在 
                            entry.copyTo(root, 'head.png', function(e) { 
                                    var path = e.fullPath + "?version=" + new Date().getTime(); 
                                    uploadHead(path); /*上传图片*/ 
                                }, 
                                function(e) { 
                                    console.log('copy image fail:' + e.message); 
                                }); 
                        }); 
                    }, function(e) { 
                        console.log("get _www folder fail"); 
                    }) 
                }, function(e) { 
                    console.log("读取拍照文件错误：" + e.message); 
                }); 
            }, function(a) {}, { 
                filter: "image" 
            }) 
        }; 

		//上传头像图片 
        function uploadHead(imgPath) { 
            console.log("imgPath = " + imgPath); 
            mainImage.src = imgPath; 
            mainImage.style.width = "60px"; 
            mainImage.style.height = "60px"; 
 
            var image = new Image(); 
            image.src = imgPath; 
            image.onload = function() { 
                var imgData = getBase64Image(image); 
                /*在这里调用上传接口*/ 
//              mui.ajax("图片上传接口", { 
//                  data: { 
//                       
//                  }, 
//                  dataType: 'json', 
//                  type: 'post', 
//                  timeout: 10000, 
//                  success: function(data) { 
//                      console.log('上传成功'); 
//                  }, 
//                  error: function(xhr, type, errorThrown) { 
//                      mui.toast('网络异常，请稍后再试！'); 
//                  } 
//              }); 
            } 
    } 
        //将图片压缩转成base64 
        function getBase64Image(img) { 
            var canvas = document.createElement("canvas"); 
            var width = img.width; 
            var height = img.height; 
            // calculate the width and height, constraining the proportions 
            if (width > height) { 
                if (width > 100) { 
                    height = Math.round(height *= 100 / width); 
                    width = 100; 
                } 
            } else { 
                if (height > 100) { 
                    width = Math.round(width *= 100 / height); 
                    height = 100; 
                } 
            } 
            canvas.width = width;   /*设置新的图片的宽度*/ 
            canvas.height = height; /*设置新的图片的长度*/ 
            var ctx = canvas.getContext("2d"); 
            ctx.drawImage(img, 0, 0, width, height); /*绘图*/ 
            var dataURL = canvas.toDataURL("image/png", 0.8); 
            return dataURL.replace("data:image/png;base64,", ""); 
        }    

	</script>
</html>
