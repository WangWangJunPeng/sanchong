/* 
* @Author: Marte
* @Date:   2017-01-05 13:11:09
* @Last Modified by:   Marte
* @Last Modified time: 2017-01-19 08:58:41
*/

$(document).ready(function(){
    $(".no-btn").click(function(){
      $(".cancel-box").hide();
    })
    //效果弹出
    $("#addPic").click(function(){
    		popBox();
          $(".popCon").show();
    })
    //银行弹出
    $("#addBtn").click(function(){
    		popBox();
         $(".popCon").show();
    })
    // 关闭   
    $(".close").click(function(){
        $(".popCon").hide();
        $(".popChange").hide();
        $(".popConh").hide();
        $(".popChangeh").hide();
    })
    
    // 户型弹出
    $("#addHouse").click(function(){
    	popBox();
         $(".popConh").show();
    })
   
    $("#addId").click(function(){
    	popBox();
        $(".popConh").show();
    })
   
    
});
//透明层
function popBox(){
    var sWidth=document.body.scrollWidth;
    var sHeight=document.body.scrollHeight; 
    //获取页面的可视区域高度和宽度
    var wHeight=document.documentElement.clientHeight;
    var oMask=document.createElement("div");
        oMask.className="mask";
        oMask.style.height=sHeight+"px";
        oMask.style.width=sWidth+"px";
        document.body.appendChild(oMask);       
     $(".close").click(function(){
         location.reload();
        // $(".popCon").hide();
        // $(".mask").remove();
        // $(".popChange").hide();
    })
        
}
// 弹框成功之后的关闭方法
function agree(){
    $(".cancel-box").hide();
  }
// 垃圾箱删除
var array = [];
function showd(obj) {
	$(".dustbin").off();
	var num = $(obj).index()
	$(obj).children().show().siblings().children().hide();
	$(".dustbin").click(function() {
		var index = $(obj).data("index");
		array.push(index);
		//$(this).parent().remove();
		$(this).parent().parent().children("label").remove();
		$(this).parent().remove();	
	})
}
function hided(){
    $(".dustbin").hide();
    $(".dustbinUrl").hide();
}
// 效果图上传图片
var n=0;
var filesList = [];//声明文件数组
function fileupload(obj){
	var allFiles = [];
	//获取所有图片输入域
	var domList = $("input[name='pic']");
	//遍历所有图片域，将图片放入文件数组
	domList.each(function(){
		var domFileList = this.files;
		if(domFileList.length>0){
			for(var n=0;n<domFileList.length;n++){
				allFiles.push(domFileList[n]);
			}
		}
	});
	filesList = allFiles;
	addPics(filesList);
}

//添加图片
function addPics(arry){
	var windowURL = window.URL || window.webkitURL;
    var fid = "#file" + n;
    $(fid).hide();
    n= n+1;
	var s = "";
	var d= "<input type='file' multiple='multiple' class='picture' id='file" + n + "' name='pic' onchange='fileupload(this)'/>";
    for(var i = 0; i< arry.length;i++){
    	var loadImg = windowURL.createObjectURL(arry[i]);
		s+="<div class='biger' data-index="+i+" style='background:url(" + loadImg + ") no-repeat center;background-size:cover;'  onmouseover='showd(this)' onmouseleave='hided()'>" +
	       "<div class='dustbin' data-value='file" +i + "'></div></div>";
    }
    $(".biger").remove();
	$(".addPic").before(s);
	$("#add").after(d);
}


// 效果图修改图片
var m = 0;
function filechange(obj){
	var allFiles = [];
	//获取所有图片输入域
	var domList = $("input[name='pic']");
	//遍历所有图片域，将图片放入文件数组
	domList.each(function(){
		var domFileList = this.files;
		if(domFileList.length>0){
			for(var n=0;n<domFileList.length;n++){
				allFiles.push(domFileList[n]);
			}
		}
	});
	filesList = allFiles;
	changeAddPics(filesList);
}

function changeAddPics(arry){
	var windowURL = window.URL || window.webkitURL;
	 var afid = "#afile" + m;
	    $(afid).hide();
	    m= m+1;
	var s = "";
	var d = "<input type='file' class='picture' id='afile" + m + "' name='pic' onchange='filechange(this)'/>"
	for(var i = 0; i< arry.length;i++){
		var loadImg = windowURL.createObjectURL(arry[i]);
		s+="<div class='biger' data-index="+i+" style='background:url(" + loadImg + ") no-repeat center;background-size:cover;'  onmouseover='showd(this)' onmouseleave='hided()'>" +
        	"<div class='dustbin' data-value='afile" + i + "'></div></div>";
		
		
	}
	$(".biger").remove();
    $(".addPic").before(s);
    $("#add2").after(d);
}

// 户型图，预售证管理上传图片弹出框
 function oldFile(){
	/* if($("#oFile").val() == '' ){
		 $("#oldImg").attr("src","");
	 }*/
	 var windowURL = window.URL || window.webkitURL;
	 var loadImg = windowURL.createObjectURL(document.getElementById('oFile').files[0]);
	 document.getElementById('oldImg').setAttribute('src',loadImg);	 
}
// 户型图，预售证管理上传图片修改弹出框
function newFile() {
	/*if($("#nFile").val() == ''){
		 $("#newImg").attr("src","")
	 }*/
    var windowURL = window.URL || window.webkitURL;
    var loadImg = windowURL.createObjectURL(document.getElementById('nFile').files[0]);
    document.getElementById('newImg').setAttribute('src',loadImg);   
}       