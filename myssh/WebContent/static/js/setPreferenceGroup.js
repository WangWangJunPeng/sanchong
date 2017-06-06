/* 
* @Author: Marte
* @Date:   2017-01-23 09:11:37
* @Last Modified by:   Marte
* @Last Modified time: 2017-02-07 13:48:59
*/

$(document).ready(function(){
     // 全选全不选
    $("#allChoose").click(function(){ 
        $(".chooseSingle").prop("checked",this.checked);
    })
    $(".chooseSingle").click(function(){
        var flag=true;
        $(".chooseSingle").each(function(){
            if (!this.checked){
                flag=false;
            };
        });
        $("#allChoose").prop("checked",flag);
      })
     // 表格拖拽排序
     // $("#table-drag").tableDnD();
        $("#table-drag").tableDnD({
            onDragClass:'myDragClass',
           onDrop:function(table,row){           
            } 
        }); 
});