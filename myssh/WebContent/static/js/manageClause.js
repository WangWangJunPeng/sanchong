/* 
* @Author: Marte
* @Date:   2017-01-22 10:21:53
* @Last Modified by:   Marte
* @Last Modified time: 2017-01-22 10:22:02
*/

$(document).ready(function(){
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
});