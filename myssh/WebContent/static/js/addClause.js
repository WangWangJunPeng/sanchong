/* 
* @Author: Marte
* @Date:   2017-02-15 17:26:12
* @Last Modified by:   Marte
* @Last Modified time: 2017-02-15 17:30:14
*/

$(document).ready(function(){
    $(".fixed-amount").click(function(){
        $(".change-money").html("优惠金额<b>*</b>");
        $(".change-unit").html("元");

    })
    $(".percentage").click(function(){
        $(".change-money").html("优惠金额百分比<b>*</b>");
        $(".change-unit").html("%");
    })
});