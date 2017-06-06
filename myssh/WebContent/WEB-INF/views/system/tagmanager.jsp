<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
<head>
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <title>平台运管管理中心-广告管理</title>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/commend.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/index.css">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/houseManage.css" />
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/reset.css" />
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/lib/laydate/need/laydate.css" />
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/SimpleTree.css"/>
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/jquery.dialogbox.css">
        <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-3.1.1.min.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/SimpleTree.js"></script>
         <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery.dialogBox.js"></script>
        <script type="text/javascript">
        $(function(){
        	
        	$("#btn_ensure_b").click(function(){
        		$.ajax({
       			 url: 'addTagType',
                    type: 'post',
                    dataType: 'json',
                    data: $("#addTagType_form").serialize(),
                    success: function (data) {
                            if(data.status==200){
                            	$('div.b').hide();
                           	 	alert(data.message);
                           	 	window.location.reload();
                            }
                    }
       			});
        	});
        	$("#btn_ensure_c").click(function(){
        		$.ajax({
       			 url: 'alertTagType',
                    type: 'post',
                    dataType: 'json',
                    data: $("#alter_TagType_form").serialize(),
                    success: function (data) {
                            if(data.status==200){
                            	$('div.c').hide();
                           	 	alert(data.message);
                           	 	window.location.reload();
                            }
                    }
       			});
        	});
        	$("#btn_ensure_a").click(function(){
        		var tagTypeId = $("#addTagPtt").val();
        		var tagName = $("#tagName_a").val();
        		$.ajax({
       			 url: 'addTag',
                    type: 'post',
                    dataType: 'json',
                    data: $("#addTag_form").serialize(),
                    success: function (data) {
                            if(data.status==200){
                            	$('div.a').hide();
                           	 	alert(data.message);
                           	 	$('<li><a href="#" >'+tagName+'</a></li>').appendTo($("#"+tagTypeId));
                            }
                    }
       			});
        	});
        	//修改标签请求
        	$("#btn_ensure_d").click(function(){
        		$.ajax({
          			 url: 'alertTag',
                       type: 'post',
                       dataType: 'json',
                       data: $("#alter_Tag_form").serialize(),
                       success: function (data) {
                               if(data.status==200){
                               	$('div.d').hide();
                              	alert(data.message);
                              	window.location.reload();
                               }
                       }
          			});
        	});
        	
        	$(".btn_back").click(function(){
        		$('div.a').hide();
        		$('div.c').hide();
        		$('div.b').hide();
        		$('div.d').hide();
        	})
        	
        	
        	function forE(data,tagTypeId){
        		$.each(data,function(v, o) {
        			$('<li><a href="#" ref="'+o.tagTypeId+'" name="'+o.hasTags+'">'+o.tagTypeName+'</a></li>'+
                		'<ul id="'+o.tagTypeId+'"></ul>').appendTo($("#"+tagTypeId));
        			if(o.tags){
        				var tag = "";
        				$.each(o.tags,function(i,t){
        					tag += '<li><a href="#" ref="'+t.tagId+'">'+t.tagName+'</a></li>';
        				})
        				$(tag).appendTo("#"+o.tagTypeId);
        			}
        			forE(o.tagLibs,o.tagTypeId);
        		})
        		//$('<li><a href="#" name="addtagType_a">新增标签类目</a></li>').appendTo($("#"+tagTypeId));
        		//$('<li><a href="#" name="addtag_a">新增标签</a></li>').appendTo($("#"+tagTypeId));
        	}
        	$.post("showTagLib",
					  function(data){
			        		$.each(data,function(v, o) {
			        			$('<li><a href="###" ref="'+o.tagTypeId+'" name="'+o.hasTags+'">'+o.tagTypeName+'</a></li>'+
			                		'<ul id="'+o.tagTypeId+'"></ul>').appendTo($("#0"));
			        			if(o.tags){
			        				var tag = "";
			        				$.each(o.tags,function(i,t){
			        					tag += '<li><a href="#" ref="'+t.tagId+'">'+t.tagName+'</a></li>';
			        				})
			        				$(tag).appendTo("#"+o.tagTypeId);
			        			}
			        			forE(o.tagLibs,o.tagTypeId);
			        		})
			        		//$('<li><a href="#" name="addtagType_a" >新增标签类目</a></li>').appendTo($("#0"));
			        		//$('<li><a href="#" name="addtag_a" >新增标签</a></li>').appendTo($("#0"));
			        		
				        	$(".st_tree").SimpleTree({
				       		 click:function(a){
				       			var b = $(a).attr("hasChild");
				       			if(b!="true"){ //没有子类目
				       				if($(a).attr("ref")){ // 属于标签
					       				var tagId = $(a).attr("ref");
				       					var tagName = $(a).html();
				       					$("#right_div").html("");
				       					$('<h1>标签:</h1><br><p>'+tagName+'</p><br>').appendTo($("#right_div"));
				       					$('<input type="button" value="修改标签" onclick="alter_tag('+"'"+tagName+"'"+','+tagId+')"><br>').appendTo($("#right_div"));
				       					$('<input type="button" value="删除" onclick="delete_tag('+"'"+tagName+"'"+','+tagId+')"><br>').appendTo($("#right_div"));
				       				}
				       			}else{//有子目录
				       				var id = $(a).attr("ref");
				       				var hasTags = $(a).attr("name");
				       				var tagTypeName = $(a).html();
				       				//alert(tagTypeName);
				       				if(hasTags==1){//含有子标签
				       					//alert(id);
				       					//$("#addTagPtt").val(id);
				       					$("#right_div").html("");
				       					$("<h1>标签类目:</h1><br><p>"+tagTypeName+"</p><br><input type='button' value='新增标签类目' onclick='add_tagType("+id+")'><br><input type='button' value='新增标签' onclick='add_tag("+id+")'><br>").appendTo($("#right_div"));
				       					$('<input type="button" value="修改标签类目" onclick="alter_tagType('+"'"+tagTypeName+"'"+','+id+')"><br>').appendTo($("#right_div"));
				       					$('#mid_div').show();
				       					$('#right_div').show();
				       				}else{//没有子标签
				       					//$("#addTagTypePtt").val(id);
				       					$("#right_div").html("");
				       					$("<h1>标签类目:</h1><br><p>"+tagTypeName+"</p><br><input type='button' value='新增标签类目' onclick='add_tagType("+id+")'><br>").appendTo($("#right_div"));
				       					$('<input type="button" value="修改标签类目" onclick="alter_tagType('+"'"+tagTypeName+"'"+','+id+')"><br>').appendTo($("#right_div"));
				       					$('#mid_div').show();
				       					$('#right_div').show();
				       				}
				       				//alert(tagTypeName);
				       				$('<input type="button" value="删除" onclick="delete_tagType('+"'"+tagTypeName+"'"+','+id+')"><br>').appendTo($("#right_div"));
				       			}
				       			
					        } 
					        	});
					  });
        	
        })
        
        	function add_tagType(id){
        	//alert(id);
        		$("#addTagTypePtt").val(id);
        		$('div.b').slideToggle();
        	}
	        function add_tag(id){
	        //	alert(id);
	        	$("#addTagPtt").val(id);
	        	$('div.a').slideToggle();	
	        }
	        function delete_tagType(name,id){
	        	$('#simple-dialogBox').dialogBox({
					width: 300,
					height: 200,
					hasMask: true,
					hasClose: true,
					hasBtn: true,
					confirmValue: '确定',
					confirm: function(){
						 $.post("deleteTagType",
								{
								    "tagTypeId":id
								  },
								  function(data){
										  alert(data.message);
										  window.location.reload();
								  }); 
					},
					cancelValue: '取消',
					content: '您确定要删除'+name+"吗?"
				});
	        }
	    
	        function alter_tagType(name,id){
	        	$("#alterTagTypeId").val(id);
	        	$("#alter_tagType_name").val(name);
	        	$('div.c').slideToggle();
	        }
	        //修改标签
	        function alter_tag(name,id){
	        	
	        	$("#alterTagId").val(id);
	        	$("#alter_tag_name").val(name);
	        	$.post("findTagById",
						{
						    "tagId":id
						  },
						  function(data){
								  if(data.status==200){
									  $("#alter_Tag_form").html("");
									  var inp = '<input id="alterTagId" type="hidden" name="tagId" value="'+data.data.tagId+'"/>'
											    +'标签名称:<input id="alter_tag_name" name="tagName" type="text" value="'+data.data.tagName+'"><br>'
											    +'标签描述:<textarea rows="5" cols="20" name="dic">'+data.data.dic+'</textarea><br>';
									  if(data.data.isMultiple!=1){
										  inp+='是否多选:<select name="isMultiple">'
										    	+'<option value="0">单选</option>'
										    	+'<option value="1">多选</option>'
										        +'</select><br>';
									  }else{
										  inp+='是否多选:<select name="isMultiple">'
										    	+'<option value="1">多选</option>'
										    	+'<option value="0">单选</option>'
										        +'</select><br>';
									  }
									  $(inp).prependTo($("#alter_Tag_form"));
							          $('div.d').slideToggle();
								  }
						  }); 
	        }
	        function delete_tag(name,id){
	        	$('#simple-dialogBox').dialogBox({
					width: 300,
					height: 200,
					hasMask: true,
					hasClose: true,
					hasBtn: true,
					confirmValue: '确定',
					confirm: function(){
						 $.post("deleteTag",
								{
								    "tagId":id
								  },
								  function(data){
										  alert(data.message);
										  window.location.reload();
								  }); 
					},
					cancelValue: '取消',
					content: '您确定要删除'+name+"吗?"
				});
	        }
        </script>
</head>
<body>
<%@include file="public/systempublicpage.jsp"%>
<div style="width:1200px;margin:0 auto;" >
<div id="left_div" class="st_tree" style="width:400px;margin:0 auto;float:left;">
	<div class="account-list">
		<ul id="0">
		
		</ul>
	</div>
</div>
<div id="mid_div" style="width:1px;height:600px;float:left;background:#ff6161;display: none;"></div>
<div id="right_div" style="float:left; display: none;">

</div>

</div>
<!-- 添加标签类目div -->
<div class="b"  style="width: 300px; height: 200px; background:#fff; display: none; border:1px solid #333; position:absolute; top:20%; left:40%;">
	<form id="addTagType_form" method="post">
			<input id="addTagTypePtt" type="hidden" name="parentTagTpyeId" />
		    标签类目名称:<input name="tagTypeName" type="text"><br>
		 是否含有子标签:
		 <select name="hasTags">
		 	<option value="1">含有子标签</option>
		 	<option value="0">不含子标签</option>	
		 </select> <br>
		 <input id="btn_ensure_b" type="button" value="确定"><input class="btn_back" type="button" value="返回">
	</form> 
</div>
<!-- 修改标签div -->
<div class="d"  style="width: 300px; height: 200px; background:#fff; display: none; border:1px solid #333; position:absolute; top:20%; left:40%;">
	<form id="alter_Tag_form" method="post">
	</form> <br>
	<input id="btn_ensure_d" type="button" value="确定"><input class="btn_back" type="button" value="返回">
</div>
<!-- 修改标签类目DIV -->
<div class="c"  style="width: 300px; height: 200px; background:#fff; display: none; border:1px solid #333; position:absolute; top:20%; left:40%;">
	<form id="alter_TagType_form" method="post">
			<input id="alterTagTypeId" type="hidden" name="tagTypeId" />
		  标签类目名称:<input id="alter_tagType_name" name="tagTypeName" type="text"><br>
		 <input id="btn_ensure_c" type="button" value="确定"><input class="btn_back" type="button" value="返回">
	</form> 
</div>
<!-- 添加标签Div -->
<div class="a"  style="width: 300px; height: 200px; background:#fff; display: none; border:1px solid #333; position:absolute; top:20%; left:40%;">
	<form id="addTag_form" method="post">
		<input id="addTagPtt" type="hidden" name="tagTypeId" />
		    标签名称:<input id="tagName_a" name="tagName" type="text"><br>
		   是否多选:<select name="isMultiple">
		    	<option value="0">单选</option>
		    	<option value="1">多选</option>
		    </select><br>
		    标签描述:<textarea rows="5" cols="20" name="dic"></textarea><br>
		 <input id="btn_ensure_a" type="button" value="确定"><input class="btn_back" type="button" value="返回">
	</form> 
</div>
<div id="simple-dialogBox"></div>
</body>
</html>