/* 
* @Author: Marte
* @Date:   2017-05-11 16:59:29
* @Last Modified by:   Marte
* @Last Modified time: 2017-05-11 17:00:20
*/
/* 
* @Author: Marte
* @Date:   2017-05-11 14:36:38
* @Last Modified by:   Marte
* @Last Modified time: 2017-05-11 16:48:42
*/

/* 
* @Author: Marte
* @Date:   2017-05-10 15:07:24
* @Last Modified by:   Marte
* @Last Modified time: 2017-05-11 14:10:30
*/

/* 
* @Author: Marte
* @Date:   2017-05-11 14:36:38
* @Last Modified by:   Marte
* @Last Modified time: 2017-05-11 16:48:42
*/

/* 
* @Author: Marte
* @Date:   2017-05-10 15:07:24
* @Last Modified by:   Marte
* @Last Modified time: 2017-05-11 14:10:30
*/

$(document).ready(function(){
	//加载客户所有标签
	getCustomerTagInfo();
	 $(".col8 .li").click(function(){
	        $(this).children().slideToggle("fast");
	    })
	 $(".aa").click(function() {
	        $(".editList").hide();
	        $(".addList").fadeToggle("normal","swing");
	        $('#16').attr("checked",true);//添加标签默认选中
	        $('#18').attr("checked",true);
	    });
	      
	      $('.btnOne').click(function(){
	      	
	    	  window.location.reload();
	     	
	     });
	      
	     $(".addButton").click(function(){
	        $(".listTwo").off();
	        $(this).before('<div class="labelEvery"><div style="margin-top:5px;margin-bottom:15px;" class="ioo" ><input type="text" name="parentTagName" class="listFirst"/><img src="static/images/du.png" class="delete"  onclick=deleteTagBtn(this)><input type="button" value="添加子标签" class="listTwo"/><input type="hidden" name="parentId" value="" ><input type="hidden" class="status"  name="parentTagStatus" value="1"/><input type="hidden"   name="originalTag" value="1"/></div></div>');
	      /*  $(".delete").click(function() {

	                $(this).parent().remove()
	        });*/
	        $(".listTwo").click(function() {
	                
	            $(this).parent().append('<div class="labelSecond"><input  type="text" name="childTagName" class="listSecond"/><img src="static/images/du.png" onclick=deleteTagBtn(this)><input type="hidden" name="childId" value="" ><input type="hidden" class="status"  name="childTagStatus" value="1"/></div>');
	        /*     $(".delete").click(function() {

	                $(this).parent().remove()
	        });*/
	        });

	     })


	     $('.addButtonTwo').click(function(){
	    	 $(".listTwo").off();
	    	 $(this).siblings(".labelEvery").append('<div style="margin-top:5px;margin-bottom:15px;" class="icc" ><input type="text" name="parentTagName" class="listFirst"/><img src="static/images/du.png" class="delete"  onclick=deleteTagBtn(this)><input type="button" value="添加子标签" class="listTwo"/><input type="hidden" name="parentId" value="" ><input type="hidden" class="status"  name="parentTagStatus" value="1"/><input type="hidden"   name="originalTag" value="1"/></div>');
	    	 
	    	 $(".listTwo").click(function() {
	    		 
	    		 $(this).parent().append('<div class="labelSecond"><input  type="text" name="childTagName" class="listSecond"/><img src="static/images/du.png" onclick=deleteTagBtn(this)><input type="hidden" name="childId" value="" ><input type="hidden" class="status"  name="childTagStatus" value="1"/></div>');
	    		/* $(".delete").click(function() {
	    			 
	    			 $(this).parent().remove()
	    		 });*/
	    	 });
	    	 
	     })
	     
	     //新增标签类目
	     $('#addSubBtn').click(function(){
	    	 var flag = false;
	    	 $("#produceForm input[type='text']").each(function(){
	 			
					if($(this).val().length > 8){
						alert("标签名称不超过8个字");
						flag = false;
						return false;
					}else if($(this).val() == ""){
						alert("标签名称不能为空");
						flag = false;
						return false;
					}else{
						flag = true;
						
					}
					})
			
			if(flag == true){
				 addProduceTagAndTagType();
			}
	    	
	    	
	    	 
	     });
	     
	     $('#tagSubmit').click(function(){
	    	 var flag = false;
	    	 $("#myTForm input[type='text']").each(function(){
	 			
					if($(this).val().length > 8){
						alert("标签名称不超过8个字");
						flag = false;
						return false;
					}else if($(this).val() == ""){
						alert("标签名称不能为空");
						flag = false;
						return false;
					}else{
						flag = true;
						
					}
					})
			
			if(flag == true){
				toAddTagSubmit();
			}
	    	
	    	
	    	
	     });
     
     
     

});


function deleteTagBtn(obj){
	 
	 $(obj).parent().hide();
	 $(obj).siblings(".listFirst").attr("type","hidden");
	 $(obj).siblings(".listSecond").attr("type","hidden");
	 $(obj).parent().children('.status').val(0);
	 $(obj).parent().children().children('.status').val(0);
		if($(obj).siblings("input[name='parentTagName']").attr("type")=="hidden" || $(obj).siblings("input[name='childTagName']").attr("type") == "hidden"){
			var aa = $(obj).siblings("input[name='parentTagName']").val();
			var kk = $(obj).siblings("input[name='childTagName']").val();
			$(obj).siblings("input[name='parentTagName']").parent().parent().siblings().children().children("input[name=parentTagName]").each(function(){
				var bb = $(this).val();
				if(aa == bb){
					$("#addSubBtn").removeAttr("disabled");
					 $("#addSubBtn").css({"background-color":"#1c9efe","color":"#fff"});
					 $("#tagSubmit").removeAttr("disabled");
					 $("#tagSubmit").css({"background-color":"#1c9efe","color":"#fff"});
				}else{
					$(this).parent().parent().siblings().children().children("input[name=parentTagName]").each(function(){
						if($(this).val() == $(this).parent().parent().siblings().children().children("input[name=parentTagName]").val()){
							 $("#addSubBtn").attr("disabled","disabled");
							 $("#addSubBtn").css({"background-color":"#eeeff0","color":"#333"});
							  $("#tagSubmit").attr("disabled","disabled");
							 $("#tagSubmit").css({"background-color":"#eeeff0","color":"#333"});
						}else{
							$("#addSubBtn").removeAttr("disabled");
							 $("#addSubBtn").css({"background-color":"#1c9efe","color":"#fff"});
							 $("#tagSubmit").removeAttr("disabled");
							 $("#tagSubmit").css({"background-color":"#1c9efe","color":"#fff"});
						}
					})
				}
			})
			$(obj).siblings("input[name='parentTagName']").parent().siblings().children("input[name=parentTagName]").each(function(){
				var cc = $(this).val();
				if(aa == cc){
					$("#addSubBtn").removeAttr("disabled");
					 $("#addSubBtn").css({"background-color":"#1c9efe","color":"#fff"});
					 $("#tagSubmit").removeAttr("disabled");
					 $("#tagSubmit").css({"background-color":"#1c9efe","color":"#fff"});
				}else{
					$(this).parent().siblings().children("input[name=parentTagName]").each(function(){
						if($(this).val() == $(this).parent().siblings().children("input[name=parentTagName]").val()){
							 $("#addSubBtn").attr("disabled","disabled");
							 $("#addSubBtn").css({"background-color":"#eeeff0","color":"#333"});
							  $("#tagSubmit").attr("disabled","disabled");
							 $("#tagSubmit").css({"background-color":"#eeeff0","color":"#333"});
						}else{
							$("#addSubBtn").removeAttr("disabled");
							 $("#addSubBtn").css({"background-color":"#1c9efe","color":"#fff"});
							 $("#tagSubmit").removeAttr("disabled");
							 $("#tagSubmit").css({"background-color":"#1c9efe","color":"#fff"});
						}
					})
				}
			})	
			$(obj).siblings("input[name='childTagName']").parent().siblings().children("input[name=childTagName]").each(function(){
				var dd = $(this).val();
				if(kk == dd){
					$("#addSubBtn").removeAttr("disabled");
					 $("#addSubBtn").css({"background-color":"#1c9efe","color":"#fff"});
					 $("#tagSubmit").removeAttr("disabled");
					 $("#tagSubmit").css({"background-color":"#1c9efe","color":"#fff"});
				}else{
					$(this).parent().siblings().children("input[name=childTagName]").each(function(){
						if($(this).val() == $(this).parent().siblings().children("input[name=childTagName]").val()){
							 $("#addSubBtn").attr("disabled","disabled");
							 $("#addSubBtn").css({"background-color":"#eeeff0","color":"#333"});
							  $("#tagSubmit").attr("disabled","disabled");
							 $("#tagSubmit").css({"background-color":"#eeeff0","color":"#333"});
						}else{
							$("#addSubBtn").removeAttr("disabled");
							 $("#addSubBtn").css({"background-color":"#1c9efe","color":"#fff"});
							 $("#tagSubmit").removeAttr("disabled");
							 $("#tagSubmit").css({"background-color":"#1c9efe","color":"#fff"});
						}
					})
				}
			})
			
		}
}

//提交表单
function toAddTagSubmit(){



var num = 0;
var numSb = 0;

var totalArry = [];
var bigArry;
var smallArry;
$(".icc").each(function(){
	bigArry = [];
	var parentTagName = $("input[name='parentTagName']").eq(num).attr('name')+'\"'+":"+'\"'+$("input[name='parentTagName']").eq(num).val();
	var parentId = $("input[name='parentId']").eq(num).attr('name')+'":"'+$("input[name='parentId']").eq(num).val();
	var parentTagStatus = $("input[name='parentTagStatus']").eq(num).attr('name')+'":"'+$("input[name='parentTagStatus']").eq(num).val();
	var originalTag = $("input[name='originalTag']").eq(num).attr('name')+'":"'+$("input[name='originalTag']").eq(num).val()+'","tags" :<';
	bigArry.push(parentTagName);
	bigArry.push(parentId);
	bigArry.push(parentTagStatus);
	bigArry.push(originalTag);
	$(this).children(".labelSecond").each(function(){
		smallArry = [];
		var childTagName = $("input[name='childTagName']").eq(numSb).attr('name')+'":"'+$("input[name='childTagName']").eq(numSb).val();
		var childId = $("input[name='childId']").eq(numSb).attr('name')+'":"'+$("input[name='childId']").eq(numSb).val();
		var childTagStatus = $("input[name='childTagStatus']").eq(numSb).attr('name')+'":"'+$("input[name='childTagStatus']").eq(numSb).val();
		smallArry.push(childTagName);
		smallArry.push(childId);
		smallArry.push(childTagStatus);
		numSb++;
		bigArry.push(smallArry);
	});
	bigArry.push('>');
	
	num ++;
	totalArry.push(bigArry)
});
/*console.log($('#tagTypeId').val())
console.log($('#tagTypeStatus').val())
console.log($('#isMultiple').val())*/
console.log(JSON.stringify(totalArry))

//console.log($("input[name='tagTypeStatus']:checked").val())
$.ajax({
	type : "POST",
	url : "add_tagtype_and_tags",
	traditional : true,
	async : true,
	//contentType: 'application/json; charset=utf-8',
	dataType : 'json', 
	data : {
		
		totalArray : JSON.stringify(totalArry),
		tagTypeId : $('#tagTypeId').val(),
		tagTypeStatus : $("input[name='tagTypeStatus']:checked").val(),
		isMultiple : $("input[name='isMultiple']:checked").val(),
		tagTypeName : $('#tagTypeName').val(),
		superTagType : $('#superTagType').val(),
		originalTagType : $('#originalTagType').val()
		
		},
	success : function(data){
		console.log(data);
		if(data.data == 200){
			
			window.location.reload();
		}
	}

});

}


function addChildTag(obj){
$(".listTwo").off();
$(obj).parent().append('<div class="labelSecond"><input name="childTagName" value="" type="text" class="listSecond"/><input type="hidden" name="childId" value="" ><img src="static/images/du.png" class="delete" onclick=deleteTagBtn(this) ><input type="hidden" class="status"  name="childTagStatus" value="1"/></div>');

}


//获取房源标签
function getCustomerTagInfo(){

$.ajax({
	type : "POST",
	url : "get_customer_need_all_tag_info",
	async : false,
	success : function(data){
		console.log(data);
		//for(var i=0; i<data.length; i++){
			fillProduceUseTagInfo(data.useTagList);
			fillProduceUnUseTagInfo(data.unUseTagList);
		//}
			//alert($('#superTagType').val());
	}
});

}
function editPic(obj){

console.log(obj);
$(".labelEvery").remove();

$('#qidong').attr("checked",false);
$('#noqidong').attr("checked",false);
$('#duoxuan').attr("checked",false);
$('#noduoxuan').attr("checked",false);

getCurrentTag(obj);
$(".addList").hide();
$(".editList").fadeToggle("normal","swing");


}





function fillProduceUseTagInfo(data){

//console.log(data);
var s = "";

$.each(data,function(v,k){
	//console.log(data);
	if(k.tagTypeStatus != 0){
		
		
		if(k.tags.length > 0){
		
					
					s += "<div class='row'><div class='col-md-2 col4'>";
					
					//console.log(o);
					//console.log(o.parentTagId);
					s += "<span>"+k.tagTypeName+"<input class='tagType' value='"+k.tagTypeId+"' type='hidden'></span></div><div class='col-md-9 col8'>";
					$.each(k.tags, function(v,t){
						//console.log(t);
						if(t.parentTagId == null && t.tagStatus != 0){
							s += "<ul><li class='li'>"+t.tagName+"<input class='tag' value='"+t.tagId+"' type='hidden'><ul  class='ul' >";
							$.each(k.tags, function(v,p){
								if(p.parentTagId == t.tagId && p.parentTagId != null && p.tagStatus !=0){
									s+="<li>"+p.tagName+"</li>"
								}
							});
							s+="</ul></li></ul>"
						
						}
					});
					s += "</div><div class='col-md-1 right-edit'></div></div>";
				}
	}
});

if(data != ""){
	$('#myUseProduce').append(s);
}



}

//获取未启用标签
function fillProduceUnUseTagInfo(data){

	var s = "";

	$.each(data,function(v,k){
		//console.log(data);
		if(k.tagTypeStatus == 0){
			
			
			if(k.tags.length > 0){
			
						
						s += "<div class='row'><div class='col-md-2 col4'>";
						
						//console.log(o);
						//console.log(o.parentTagId);
						s += "<span>"+k.tagTypeName+"<input class='tagType' value='"+k.tagTypeId+"' type='hidden'></span></div><div class='col-md-9 col8'>";
						$.each(k.tags, function(v,t){
							//console.log(t);
							if(t.parentTagId == null && t.tagStatus != 0){
								s += "<ul><li class='li'>"+t.tagName+"<input class='tag' value='"+t.tagId+"' type='hidden'><ul  class='ul' >";
								$.each(k.tags, function(v,p){
									if(p.parentTagId == t.tagId && p.parentTagId != null && p.tagStatus !=0){
										s+="<li>"+p.tagName+"</li>"
									}
								});
								s+="</ul></li></ul>"
							
							}
						});
						s += "</div><div class='col-md-1 right-edit'></div></div>";
					}
		}
	});
if(data != ""){
	$('#myUnUseProduce').append(s);
}


}

function getCurrentTag(obj){

$.ajax({
	type : "POST",
	url : "get_produce_current_tag",
	async : false,
	data : { tagTypeId : obj},
	success : function(data){
		console.log(data.list);
		fillCurrentTag(data.list)
	}
});

}

function fillCurrentTag(data){

var s = "";
$.each(data,function(v,k){
	
	$('#tagTypeName').val(k.tagTypeName);
	$('#tagTypeId').val(k.tagTypeId);
	
	if(k.originalTagType == 1){ //如果是用户自定义的可以修改
		$('#tagTypeName').attr("readonly",false);
	}
	
	if(k.tagTypeStatus == 1){
		$('#qidong').attr("checked",'checked');
	}else{
		$('#noqidong').attr("checked",'checked');
	}
	
	//如果是系统默认额就不允许修改是否单选多选
	if(k.originalTagType == 0){
		
		if(k.isMultiple == 1){
			$('#duoxuan').attr("checked",'checked');
			$('#duoxuan').attr("disabled", true);
			$('#noduoxuan').attr("disabled", true);
		}else{
			$('#noduoxuan').attr("checked",'checked');
			$('#duoxuan').attr("disabled", true);
			$('#noduoxuan').attr("disabled", true);
		}
	}else{
		
		if(k.isMultiple == 1){
			$('#duoxuan').attr("checked",'checked');
		}else{
			$('#noduoxuan').attr("checked",'checked');
		}
		
	}
	//console.log('k'+k);
	s = "<div class='labelEvery'>";
	$.each(k.tags,function(v,o){
		console.log('k.tags'+k.tags);
		if(o.tagStatus != 0){
			
			if(o.parentTagId == "" || o.parentTagId == null){
				
				if(o.originalTag == 0){
					s += "<div style='margin-top:5px;margin-bottom:15px;' class='icc' ><input  name='parentTagName' type='text' value='"+o.tagName+"' class='listFirst' readonly/><input type='button' value='添加子标签' class='listTwo' onclick=addChildTag(this) /><input type='hidden' name='parentId' value='"+o.tagId+"' ><input type='hidden' class='status'  name='parentTagStatus' value='1'/><input type='hidden'   name='originalTag' value='0'/>";
					$.each(k.tags, function(v,t){
						
						if(t.parentTagId == o.tagId && t.tagStatus != 0){
							if(t.originalTag == 0){
								
								s += "<div class='labelSecond'><input name='childTagName' type='text' value='"+t.tagName+"' class='listSecond' readonly/><input type='hidden' name='childId' value='"+t.tagId+"' ><input type='hidden' class='status'  name='childTagStatus' value='1'/></div>";
								
							}else{
								s += "<div class='labelSecond'><input name='childTagName' type='text' value='"+t.tagName+"' class='listSecond' readonly/><input type='hidden' name='childId' value='"+t.tagId+"' ><img src='static/images/du.png' class='delete' onclick=deleteTagBtn(this) ><input type='hidden' class='status'  name='childTagStatus' value='1'/></div>"
							}
						}
						
					});
					s += "</div>";
				}else{
					
					s += "<div style='margin-top:5px;margin-bottom:15px;' class='icc'><input  type='text' name='parentTagName' value='"+o.tagName+"' class='listFirst' /><img src='static/images/du.png' class='delete'  onclick=deleteTagBtn(this)><input type='button' value='添加子标签' class='listTwo' onclick=addChildTag(this) ><input type='hidden' name='parentId' value='"+o.tagId+"' ><input type='hidden' class='status'  name='parentTagStatus' value='1'/><input type='hidden'   name='originalTag' value='1'/>";
					$.each(k.tags, function(v,c){
						//console.log('parent:'+t.parentTagId);
						//console.log('child:'+o.tagId);
						
						if(c.parentTagId == o.tagId && c.tagStatus != 0)
							s += "<div class='labelSecond'><input  name='childTagName' type='text' value='"+c.tagName+"' class='listSecond' /><input type='hidden' name='childId' value='"+c.tagId+"' ><img src='static/images/du.png' class='delete'  onclick=deleteTagBtn(this)><input type='hidden' class='status'  name='childTagStatus' value='1'/></div>"
							
					});
					s += "</div>";
				}
			}
		}
		
			
	});
	//s += "<div id='endDiv'></div>"
	s += "</div>";
	
});


$('#beforeBtn').before($(s));

}

//新增标签类目
function addProduceTagAndTagType(){

var num = 0;
var numSb = 0;

var totalArry = [];
var bigArry;
var smallArry;
$(".ioo").each(function(){
	bigArry = [];
	var parentTagName = $("input[name='parentTagName']").eq(num).attr('name')+'\"'+":"+'\"'+$("input[name='parentTagName']").eq(num).val();
	var parentId = $("input[name='parentId']").eq(num).attr('name')+'":"'+$("input[name='parentId']").eq(num).val();
	var parentTagStatus = $("input[name='parentTagStatus']").eq(num).attr('name')+'":"'+$("input[name='parentTagStatus']").eq(num).val();
	var originalTag = $("input[name='originalTag']").eq(num).attr('name')+'":"'+$("input[name='originalTag']").eq(num).val()+'","tags" :<';
	bigArry.push(parentTagName);
	bigArry.push(parentId);
	bigArry.push(parentTagStatus);
	bigArry.push(originalTag);
	$(this).children(".labelSecond").each(function(){
		smallArry = [];
		var childTagName = $("input[name='childTagName']").eq(numSb).attr('name')+'":"'+$("input[name='childTagName']").eq(numSb).val();
		var childId = $("input[name='childId']").eq(numSb).attr('name')+'":"'+$("input[name='childId']").eq(numSb).val();
		var childTagStatus = $("input[name='childTagStatus']").eq(numSb).attr('name')+'":"'+$("input[name='childTagStatus']").eq(numSb).val();
		smallArry.push(childTagName);
		smallArry.push(childId);
		smallArry.push(childTagStatus);
		numSb++;
		bigArry.push(smallArry);
	});
	bigArry.push('>');
	
	num ++;
	totalArry.push(bigArry)
});
/*console.log($('#tagTypeId').val())
console.log($('#tagTypeStatus').val())
console.log($('#isMultiple').val())*/
console.log(JSON.stringify(totalArry))

//console.log($("input[name='tagTypeStatus']:checked").val())
$.ajax({
	type : "POST",
	url : "add_tagtype_and_tags",
	traditional : true,
	async : false,
	//contentType: 'application/json; charset=utf-8',
	dataType : 'json', 
	data : {
		
		totalArray : JSON.stringify(totalArry),
		tagTypeId : $('#newTagTypeId').val(),
		tagTypeStatus : $("input[name='newTagTypeStatus']:checked").val(),
		isMultiple : $("input[name='newIsMultiple']:checked").val(),
		tagTypeName : $('#newTagTypeName').val(),
		superTagType : $('#superTagType').val(),
		originalTagType : $('#newOriginalTagType').val()
		
		},
	success : function(data){
		console.log(data);
		if(data.data == 200){
			
			window.location.reload();
		}
	}

});
}