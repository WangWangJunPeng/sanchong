/* 
* @Author: Marte
* @Date:   2017-05-10 11:33:23
* @Last Modified by:   Marte
* @Last Modified time: 2017-05-10 13:32:52
*/

$(document).ready(function(){
	
	getProjectTagInfo();
	
	
    $(".aa").click(function() {
        $(".right-col4").fadeToggle("normal","swing");
        
        //首先清理
        $('.right-row').remove();
        
        //获取项目tag
        getProjectUseTagInfo();
    });
    
    
    $('#sub').click(function(){
    	
    	//进行标签的提交
    	addProjectTagUse();
    	
    });
    
    $('#restBtn').click(function(){
    	
    	 $(".right-col4").fadeToggle("displaynone","swing");
    	
    });
    
   
    
});


function getProjectTagInfo(){
	$.ajax({
		type : "POST",
		url : "get_project_use_tag_info",
		async : false,
		success : function(data){
			console.log(data);
			for(var i=0; i<data.length; i++){
				fillProjectTagInfo(data[i]);
			}
			
			
		}
	});
	
}

var i = 0;
function fillProjectTagInfo(data){
	
	$.each(data,function(v,o){
		//console.log(o);
		s = "<div class='row'><div class='col-md-2 col4'>";
		s += "<span>"+data.tagTypeName+"<input name = 'tagTypeId' value="+o.tagTypeId+" type='hidden'></span></div>";
		s += "<div class='col-md-10 col8'>";
		
		$.each(data.tags,function(v,o){
			if(o.selected == 1)
			s += "<span style='background: #1c9efe;color:#ffffff;'>"+ o.tagName +"</span>";
			else
				s += "<span>"+ o.tagName +"</span>";
		});
		
		s += "</div></div>";
		i++;
	});
	
	if(data != ""){
		$("#myTag").append(s);
	}
	
}

function getProjectUseTagInfo(){
	
	$.ajax({
		type : "POST",
		url : "get_project_use_tag_info",
		async : false,
		success : function(data){
			for(var i=0; i<data.length; i++){
				fillProjectUseTagInfo(data[i]);
			}
			
			
		}
	});
	
}

var m=0;
function fillProjectUseTagInfo(data){
	$.each(data,function(){
		s = "<div class='row  right-row ' ><div class='col-md-3 right-col2'>";
		s += "<span>"+data.tagTypeName+"<input class='tagTypes' name = 'tagTypeIds' value="+data.tagTypeId+" type='hidden'></span></div>";
		s += "<div class='col-md-9'>";
		if(data.isMultiple == 0){ //0表示单选
			
			$.each(data.tags,function(v,o){
				if(o.selected == 1){ //为1表示用户已经选中了
					
					s += "<input type='radio' class='nn' name='"+data.tagTypeName+"' id='"+m+"' value='"+o.tagId+"' style='display:none;'  checked/><label for='"+m+"' class='mo'>"+o.tagName+"</label>";
					
				}else{
					
					s += "<input type='radio' class='nn' name='"+data.tagTypeName+"' id='"+m+"' value='"+o.tagId+"' style='display:none;'/><label for='"+m+"' class='mo'>"+o.tagName+"</label>";
				}
				m++;
			});
		}else{
			
			$.each(data.tags,function(v,o){
				if(o.selected == 1){ //为1表示用户已经选中了
					
					s += "<input type='checkbox' class='nn' name='"+data.tagTypeName+"' id='"+m+"' value='"+o.tagId+"' style='display:none;'  checked/><label for='"+m+"' class='mo'>"+o.tagName+"</label>";
					
				}else{
					
					s += "<input type='checkbox' class='nn' name='"+data.tagTypeName+"' id='"+m+"' value='"+o.tagId+"' style='display:none;'/><label for='"+m+"' class='mo'>"+o.tagName+"</label>";
				}
				m++;
			});
		}
		 
		s += "</div></div>";
	});
	
	if(data != ""){
		$("#tableHelper").before(s);
	}
	
}

function addProjectTagUse(){
	
	//获取所有选中的checkbox的值
	var idsstr = "";
	    $("input[class='nn']:checked").each(function(){ //遍历table里的全部checkbox
	        idsstr += $(this).val() + ","; //获取所有checkbox的值
	    });
	    if(idsstr.length > 0) //如果获取到
	        idsstr = idsstr.substring(0, idsstr.length - 1); //把最后一个逗号去掉
	    
	$.ajax({
		type : "POST",
		url : "project_start_use_tags",
		async : false,
		data : {
			
			tags : idsstr,
			status : 1,
			
		},
		success : function(data){
			if(data.data == 200){
				console.log(data.message);
				window.location.reload();
			}
			if(data.data == 202){
				console.log(data.message);
			}
		}
	});
	
}