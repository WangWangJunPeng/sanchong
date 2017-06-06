/* 
* @Author: Marte
* @Date:   2017-01-17 10:55:55
* @Last Modified by:   Marte
* @Last Modified time: 2017-01-19 10:19:13
*/
$(document).ready(function(){
    //$('#pageToolbar').Paging({pagesize:10,count:85,toolbar:true});
	// 全选全不选
    $("#allChoose").click(function(){ 
        $(".chooseSingle").prop("checked",this.checked);
    })
    $(".chooseSingle").click(function(){
        var flag=true;
        $(".chooseSingle").each(function() {
            if (!this.checked) {
                flag=false;
            };
        });
         $("#allChoose").prop("checked",flag);
      })
    // 批量删除
    $(".btn2").click(function(){
    	var sv = [];
    	//var v = 1;
		$("input[name='chooseOne']:checked").each(function(){ 
			var tv =$(this).val(); 
			sv.push(tv);
		})
		if(sv !=''){
            layer.confirm('确认要删除选中的房源信息吗？', {
            title:['操作确认'],
            btn: ['确定','取消'],
            btnAlign:'c'
        }, function(){
             layer.msg('删除成功!',{icon:1,time:1000},function(){
            	
    			$.ajax({
    				dataType: "json", 
       				type:"post",
       				url:"delete_more_house_infos",
    				data:{selectedHIds:sv},
    				traditional: true,
    				success: function(data){
    					window.location.href="to_house_manage_page";
    				},
    		       	error:function(){
    		       		//alert(1)
    		       	}
       			});
             }); 
            
        });
		}else{
			 layer.confirm('请选择要删除的房源信息', {
		            title:['操作确认'],
		            btn: ['确定','取消'],
		            btnAlign:'c'
		        })
		}
    })
    $(".btn3").click(function(){
        layer.open({
              title:['房源导入'],  
              type: 1,
              btn:['确定'],
              btnAlign:'c',
              skin: 'layui-layer-rim', //加上边框
              area: ['400px', '200px'], //宽高
              content: '<form id="excelInfo" enctype ="multipart/form-data"><input type="file" id="myFile" name="file"/></form>',
              yes:function(obj){
                var result = $("#myFile").val();
                var point = result.lastIndexOf("."); 
                var type = result.substr(point);
                 if ($("#myFile").val()!="" && type == ".xls") {
                    layer.confirm('确认要将文件中的信息导入到房源列表中吗？', {
                    title:['操作确认'],
                    btn: ['确定','取消'],
                    btnAlign:'c'
                    }, function(){
                        layer.msg('上传成功！', {icon: 1,time:1000},function(){
                        	console.log(new FormData($("#excelInfo").serialize()));
                			$.ajax({
                				url:"import_house_excel",
                   				type:"post",
                				data:new FormData($("#excelInfo")[0]),
                				processData: false,
                			    contentType: false,
                				success: function(data){
                					 layer.confirm(data.data, {
                		                    title:['导入详细'],
                		                    btn: ['确定'],
                		                    closeBtn: 0,
                		                    area: ['420px', '220px'],
                		                    btnAlign:'c'
                		                    },function(){
                		                    	window.location.href="to_house_manage_page";
                		                    })
                				}
                   			});
                         }); 
                        
                    })
                    
                 }else{
                    layer.alert("请正确上传文件");
                 } 

        }
    })

})
	$(".btnIn").click(function(){
		var sv = [];
		$("input[name='chooseOne']:checked").each(function(){ 
			var tv =$(this).val(); 
			sv.push(tv);
		})
		if(sv !=''){
            layer.confirm('确认要导出选中的房源吗？', {
            title:['操作确认'],
            btn: ['确定','取消'],
            btnAlign:'c'
        }, function(){
             layer.msg('开始下载...',{icon:1,time:3000},function(){
            	window.location.href = "export_house_info?type=house&selectedHIds="+sv;
            	// console.log(sv);
    			/*$.ajax({
    				dataType: "json", 
       				type:"post",
       				url:"export_house_info",
    				data:{type:'house',selectedHIds:sv},
    				traditional: true,
   				success: function(data){
   					alert(11111111111111111);
   				},
    		    error:function(){
   		       		alert("error")
   		       	}
       			});*/
             }); 
        });
		}else{
			 layer.confirm('请选择要导出的房源', {
		            title:['操作确认'],
		            btn: ['确定','取消'],
		            btnAlign:'c'
		        })
		}
	})



});
function checkBoxSelect(){
	// 全选全不选
    $("#allChoose").click(function(){ 
        $(".chooseSingle").prop("checked",this.checked);
    })
    $(".chooseSingle").click(function(){
        var flag=true;
        $(".chooseSingle").each(function() {
            if (!this.checked) {
                flag=false;
            };
        });
         $("#allChoose").prop("checked",flag);
      })
}