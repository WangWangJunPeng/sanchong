/* 
* @Author: Marte
* @Date:   2017-03-15 14:31:59
* @Last Modified by:   Marte
* @Last Modified time: 2017-03-15 19:04:16
*/
$(document).ready(function(){
    // 基本信息
	//项目用地面积
    jQuery.validator.addMethod("landArea",function(value,element){  
                var site = /^(([1-9]\d*)|([1-9][0-9]\d*)|([0-9]*?\.\d{2}))$/;
                return this.optional(element) || (site.test(value));
            },"(请输入整数或保留两位小数)"); 
    //项目总建筑面积
    jQuery.validator.addMethod("buildArea",function(value,element){  
                var allArea = /^(([1-9]\d*)|([1-9][0-9]\d*)|([0-9]*?\.\d{2}))$/;
                return this.optional(element) || (allArea.test(value));
            },"(请输入整数或保留两位小数)"); 
    //地上建筑面积
    jQuery.validator.addMethod("groundArea",function(value,element){  
                var upbuild = /^(([1-9]\d*)|([1-9][0-9]\d*)|([0-9]*?\.\d{2}))$/;
                return this.optional(element) || (upbuild.test(value));
            },"(请输入整数或保留两位小数)");
    //地下建筑面积
    jQuery.validator.addMethod("underGroundArea",function(value,element){  
                var downbuild = /^(([1-9]\d*)|([1-9][0-9]\d*)|([0-9]*?\.\d{2}))$/;
                return this.optional(element) || (downbuild.test(value));
            },"(请输入整数或保留两位小数)");

//总户数
     jQuery.validator.addMethod("unitCount",function(value,element){  
                var totaln = /^\d+$/;
                return this.optional(element) || (totaln.test(value));
            },"(请输入正整数)");

//容积率
    jQuery.validator.addMethod("volumeRatio",function(value,element){  
                var plot = /^(([0-9]?\.\d{2}))$/;
                return this.optional(element) || (plot.test(value));
            },"(请输入整数或保留两位小数)");
    //密度
    jQuery.validator.addMethod("density",function(value,element){  
                var den = /^(100|[1-9]?\d(\.\d\d?)?)$/;
                return this.optional(element) || (den.test(value));
            },"(请输入整数或保留两位小数)");    
//绿化率
 jQuery.validator.addMethod("afforestationRatio",function(value,element){  
                var greenr = /^(100|[1-9]?\d(\.\d\d?)?)$/;
                return this.optional(element) || (greenr.test(value));
            },"(请输入整数或保留两位小数)"); 
 //均价
 jQuery.validator.addMethod("averagePrice",function(value,element){  
                var averagep = /^(([1-9]\d*)|([1-9][0-9]\d*)|([0-9]*?\.\d{2}))$/;
                return this.optional(element) || (averagep.test(value));
            },"(请输入整数或保留两位小数)"); 
 //物业费
jQuery.validator.addMethod("propertyCost",function(value,element){  
                var fee =  /^(([1-9]\d*)|([1-9][0-9]\d*)|([0-9]*?\.\d{2}))$/;
                return this.optional(element) || (fee.test(value));
            },"(请输入整数或保留两位小数)"); 

     jQuery.validator.addMethod("rightsYears",function(value,element){  
                var ed = /^\d+$/;
                return this.optional(element) || (ed.test(value));
            },"(请输入正整数)");

    $("#basicForm").validate({

    rules:{
    	landArea:{    
            required:true,
            number:true,
            maxlength:10,
            landArea:true    

        },
        buildArea:{
            required:true,
             number:true,
             maxlength:10,
             buildArea:true

        },
        groundArea:{
            required:true,
             number:true,
              maxlength:10,
              groundArea:true
        },
        underGroundArea:{
            required:true,
             number:true,
              maxlength:10,
              underGroundArea:true
        },
        unitCount:{
            required:true,
             number:true,
             unitCount:true
        },
        volumeRatio:{
            required:true,
             number:true,
             volumeRatio:true
        },
        density:{
            required:true,
             number:true,
             density:true
        },
        afforestationRatio:{
            required:true,
             number:true,
             afforestationRatio:true
        },
        saleAddress:{
            required:true,
            maxlength:100
             
        },
        saleAddressGis:{
        	required:true,
        },
        propertyAddress:{
              required:true,
               maxlength:100
        },
        propertyAddressGis:{
        	 required:true,
        },
        district:{
             required:true,
        },
        averagePrice:{
            required:true,
             number:true,
              maxlength:10,
             averagePrice:true
        },
        developer:{
                required:true,
        },
        investor:{
                required:true,
        },
        manager:{
             required:true,
        },
        propertyCost:{
             required:true,
             number:true,
              maxlength:10,
              propertyCost:true
        },
        deliverStandard:{
             required:true,
             maxlength:200
        },
       
        rightsYears:{
            required:true,
             number:true,
             rightsYears:true
        },
        description:{
           
             maxlength:200
        }

    },
    messages: {
    	landArea:{
                required:"（不能为空）",
                number:"（请输入数字）",
                maxlength:"(请正确输入)"

         },
         buildArea:{
              required:"（不能为空）",
                number:"（请输入数字）",
                maxlength:"(请正确输入)"
         },
         groundArea:{
                required:"（不能为空）",
                number:"（请输入数字）",
                 maxlength:"(请正确输入)"
         },
         underGroundArea:{
                required:"（不能为空）",
                number:"（请输入数字）",
                 maxlength:"(请正确输入)"
         },
         unitCount:{
                required:"（不能为空）",
                number:"（请输入数字）"
         },
         volumeRatio:{
                required:"（不能为空）",
                number:"（请输入数字）"
         },
         density:{
                required:"（不能为空）",
                number:"（请输入数字）"
         },
         afforestationRatio:{
             required:"（不能为空）",
                number:"（请输入数字）"
         },
         saleAddress:{
            required:"（不能为空）",
            maxlength:"(100字以内)"
         },
         saleAddressGis:{
        	 required:"（请正确选择地址）",
         },
         propertyAddress:{
             required:"（不能为空）",
             maxlength:"(100字以内)"
         },
         propertyAddressGis:{
        	 required:"(请正确选择地址)",
        },
         district:{
             required:"（不能为空）"
         },
         averagePrice:{
                required:"（不能为空）",
                number:"（请输入数字）",
                maxlength:"(请正确输入)"
         },
         developer:{
                 required:"（不能为空）"
        },
        investor:{
                required:"（不能为空）"
        },
        manager:{
             required:"（不能为空）"
        },
        propertyCost:{
             required:"（不能为空）",
                number:"（请输入数字）",
                 maxlength:"(请正确输入)"

        },
        deliverStandard:{
             required:"（不能为空）",
             maxlength:"（200字以内）"
        },
        
        rightsYears:{
            required:"（不能为空）",
                number:"（请输入数字）"
        },
        description:{
            
             maxlength:"（200字以内）"
        }
    },
    // 错误信息的位置
    errorPlacement: function(error, element){ 
        if (element.parent().children().hasClass('otherError') ) {
            
            error.insertAfter($(".eer"));

        }else if( element.parent().children().hasClass('otherErrorTwo')){
                error.insertAfter($(".eed"));
        }
        else{
            error.appendTo(element.parent()); 
        }
    },
    });  
    
    //效果图
    
    jQuery.validator.addMethod("pic", function(value, element) {
        var effPic = /\.(gif|jpg|jpeg|png|GIF|JPG|PNG|JPEG)$/;
        return this.optional(element) || (effPic.test(value));
     }, "图片格式只支持jpg,png,gif和jpeg");
    $("#effectionVerifyOne").validate({
        rules:{
        	pic:{
//        		required:true,
        		pic:true
        	},
        	caption:{
                required:true,
                maxlength:20
            },
            description:{
                    
                    maxlength:200
            }

        },
        messages:{
        	pic:{
//        		required:"(图片不能为空)",
        	},
        	caption:{
                    required:"(不能为空)",
                    maxlength:"(20个字以内)"
        	},
          description:{
                    
                    maxlength:"(200个字以内)"
            }
        },
        errorPlacement: function(error, element){ 
            if (element.parent().parent().parent().children().hasClass('single') ) {
                
            	error.appendTo(element.parent().parent());

            }
            else{
                error.appendTo(element.parent()); 
            }
        },
         
    });
    jQuery.validator.addMethod("pic", function(value, element) {
        var effPic = /\.(gif|jpg|jpeg|png|GIF|JPG|PNG|JPEG)$/;
        return this.optional(element) || (effPic.test(value));
     }, "图片格式只支持jpg,png,gif和jpeg");
    $("#effectionVerifyTwo").validate({
        rules:{
        	pic:{
//        		required:"(图片不能为空)",
        		pic:true
        	},
        	caption:{
                required:true,
                maxlength:20
            },
            description:{
                    
                    maxlength:200
            }

        },
        messages:{
        	pic:{
//        		required:"(图片不能为空)",
        	},
        	caption:{
                    required:"(不能为空)",
                    maxlength:"(20个字以内)"
          },
          description:{
                    
                    maxlength:"(200个字以内)"
            }
        },
        errorPlacement: function(error, element){ 
            if (element.parent().parent().parent().children().hasClass('single') ) {
                
            	error.appendTo(element.parent().parent());

            }
            else{
                error.appendTo(element.parent()); 
            }
        },
         
    });
    //户型图
    jQuery.validator.addMethod("area",function(value,element){  
        var are = /^(([1-9]\d*)|([1-9][0-9]\d*)|([0-9]*?\.\d{2}))$/;
        return this.optional(element) || (are.test(value));
    },"(请输入整数或保留两位小数)"); 
    
    jQuery.validator.addMethod("pic", function(value, element) {
       var geshi = /\.(gif|jpg|jpeg|png|GIF|JPG|PNG|JPEG)$/;
       return this.optional(element) || (geshi.test(value));
    }, "图片格式只支持jpg,png,gif和jpeg");
    $("#houseOneVerify").validate({
        rules:{
        	pic:{
        		required:true,
        		pic:true
        	},
        	caption:{
                  required:true,
                maxlength:20
            },
             area:{
                  required:true,
                  area:true,
                    maxlength:10
            },
            housTypeDesc:{
                   
                    maxlength:200
            }
        },
         messages:{
        	 pic:{
         		required:"(请上传图片)",
         		
         	},
        	 caption:{
                    required:"(不能为空)",
                    maxlength:"(20个字以内)"
          },
          area:{
                    required:"(不能为空)",
                    maxlength:"(20个字以内)"
            },
            housTypeDesc:{
                
                maxlength:"(200个字以内)"
            }
        },
        errorPlacement: function(error, element){ 
            
        	 if (element.parent().parent().children().hasClass('up-pic') ) {
                 
                 error.appendTo(element.parent().parent());

             }
             else{
                 error.appendTo(element.parent()); 
             }
        }
     });
    jQuery.validator.addMethod("area",function(value,element){  
        var are = /^(([1-9]\d*)|([1-9][0-9]\d*)|([0-9]*?\.\d{2}))$/;
        return this.optional(element) || (are.test(value));
    },"(请输入整数或保留两位小数)"); 
    jQuery.validator.addMethod("pic", function(value, element) {
        var geshi = /\.(gif|jpg|jpeg|png|GIF|JPG|PNG|JPEG)$/;
        return this.optional(element) || (geshi.test(value));
     }, "图片格式只支持jpg,png,gif和jpeg");
    $("#houseTwoVerify").validate({
        rules:{
        	pic:{
//        		required:true,
        		pic:true
        	},
        	caption:{
                  required:true,
                maxlength:20
            },
             area:{
                  required:true,
                  area:true,
                    maxlength:10
            },
            housTypeDesc:{
                  
                    maxlength:200
            }
        },
         messages:{
        	 pic:{
          		required:"(请上传图片)",
          		
          	},
        	 caption:{
                    required:"(不能为空)",
                    maxlength:"(20个字以内)"
          },
          area:{
                    required:"(不能为空)",
                    maxlength:"(20个字以内)"
            },
            housTypeDesc:{
              
                maxlength:"(200个字以内)"
            }
        },
        errorPlacement: function(error, element){ 
        	if (element.parent().parent().children().hasClass('up-pic') ) {
                
                error.appendTo(element.parent().parent());

            }
            else{
                error.appendTo(element.parent()); 
            }
        }
     });
    //银行账号
    jQuery.validator.addMethod("accountNo",function(value,element){  
        var ed =  /^[0-9]\d*$/;
        return this.optional(element) || (ed.test(value));
    },"(请正确输入)");

$("#bankVerify").validate({
    rules:{
    	bankName:{
    		 required:true,  
            maxlength:20
        },
        accountNo:{
        	 required:true,
        	accountNo:true,
              number:true,
              rangelength:[16,19]
        },
        accountName:{
        	 required:true,
                maxlength:20
        },
        description:{
              
                maxlength:200
        }

},
  messages:{
	  bankName:{
		  required:"（不能空）", 
            maxlength:"(20个字以内)"
        },
        accountNo:{
        	required:"（不能空）", 
               number:"(请输入数字)",
            rangelength:"(账号长度在16-19位之间)"
        },
        accountName:{
        	required:"（不能空）",
            maxlength:"(20个字以内)"
        },
        description:{
               
                maxlength:"(200个字以内)"
        }
    }

});
//带看业务定义
jQuery.validator.addMethod("daiKanMoney",function(value,element){  
                var waM =/^(([1-9][0-9])|([0-9]*?\.\d{2}))$/;
                return this.optional(element) || (waM.test(value));
            },"(请输入整数或保留两位小数)");
jQuery.validator.addMethod("fenXiaoMoney",function(value,element){  
                var saleM =/^(([1-9][0-9])|([0-9]*?\.\d{2}))$/;
                return this.optional(element) || (saleM.test(value));
            },"(请输入整数或保留两位小数)");
jQuery.validator.addMethod("validDays",function(value,element){  
                var electday = /^[1-9]\d*$/;
                return this.optional(element) || (electday.test(value));
            },"(请输入正整数)");
jQuery.validator.addMethod("yiDiSalesCommission",function(value,element){  
                var otherS =/^(([1-9][0-9])|([0-9]*?\.\d{2}))$/;
                return this.optional(element) || (otherS.test(value));
            },"(请输入整数或保留两位小数)");
jQuery.validator.addMethod("yiDiValidity",function(value,element){  
                var dT =/^[1-9]\d*$/;
                return this.optional(element) || (dT.test(value));
            },"(请输入正整数)");

 $("#serviceVerify").validate({
            rules:{
            	daiKanMoney:{
                     required:true,
                     daiKanMoney:true
                 },
                 fenXiaoMoney:{
                    required:true,
                    fenXiaoMoney:true
                 },
                 validDays:{
                     required:true,
                     validDays:true,

                 },
                 yiDiSalesCommission:{
                     required:true,
                     yiDiSalesCommission:true
                 },
                 yiDiValidity:{
                     required:true,
                     yiDiValidity:true,

                 },
                 description:{
                    
                     maxlength:200,

                 }
            },
            messages:{
            	daiKanMoney:{
                     required:"(不能为空)",
                    
                 },
                 fenXiaoMoney:{
                     required:"(不能为空)",
                    
                 },
                 validDays:{
                     required:"(不能为空)",
                    
                 },
                 yiDiSalesCommission:{
                     required:"(不能为空)",
                    
                 },
                 yiDiValidity:{
                     required:"(不能为空)",
                    
                 },
                 description:{
                    
                    maxlength:"(200字以内)"
                 },
             },
            errorPlacement: function(error, element){ 
            
                error.appendTo(element.parent()); 
            }
        
 });
//预售证管理
 /*jQuery.validator.addMethod("idManageNum",function(value,element){  
                var idum =  /^[0-9]\d*$/;
                return this.optional(element) || (idum.test(value));
            },"(请正确输入)");
*/
 jQuery.validator.addMethod("pic", function(value, element) {
     var idPic = /\.(gif|jpg|jpeg|png|GIF|JPG|PNG|JPEG)$/;
     return this.optional(element) || (idPic.test(value));
  }, "图片格式只支持jpg,png,gif和jpeg");
$("#idVerify").validate({
         rules:{
        	 pic:{
        		required:true,
         		pic:true
        	 },
        	 idManageNum:{
                     required:true   
                     
                 }
             },
        messages:{
        	 pic:{
         		required:"(请上传图片)",
          		
         	 },
        	idManageNum:{
                     required:"(不能为空)",
                  
                 },
        },
        errorPlacement: function(error, element){ 
        	if (element.parent().parent().children().hasClass('up-pic') ) {               
                error.appendTo(element.parent().parent());
            }
            else{
                error.appendTo(element.parent()); 
            }
        }
});
jQuery.validator.addMethod("pic", function(value, element) {
    var idPicTwo = /\.(gif|jpg|jpeg|png|GIF|JPG|PNG|JPEG)$/;
    return this.optional(element) || (idPicTwo.test(value));
 }, "图片格式只支持jpg,png,gif和jpeg");
$("#idVerifyTwo").validate({
        rules:{
       	 pic:{
//       		required:true,
        		pic:true
       	 },
       	 idManageNum:{
                    required:true   
                    
                }
            },
       messages:{
       	 pic:{
        		required:"(请上传图片)",
         		
        	 },
       	idManageNum:{
                    required:"(不能为空)",
                 
                },
       },
       errorPlacement: function(error, element){ 
       	if (element.parent().parent().children().hasClass('up-pic') ) {               
               error.appendTo(element.parent().parent());
           }
           else{
               error.appendTo(element.parent()); 
           }
       }
});
//购买规则
jQuery.validator.addMethod("dposit",function(value,element){  
                var dp = /^(([0-9]*?\.\d{2}))$/;
                return this.optional(element) || (dp.test(value));
            },"(请保留两位小数)"); 
$("#buyVerify").validate({
    rules:{
    	dposit:{
                    required:true,
                   
                    dposit:true
                 },
                 enterBuyRule:{
                     
                     maxlength:200
                 }
             },
        messages:{


        	dposit:{
                     required:"(不能为空)",
                  
                 },
                 enterBuyRule:{
                     
                      maxlength:"(200个字以内)"
                }
        },
         errorPlacement: function(error, element){ 
            
                error.appendTo(element.parent()); 
            }

});
//增加优惠条款
jQuery.validator.addMethod("fee",function(value,element){  
                var dp = /^(([0-9]*?\.\d{2}))$/;
                return this.optional(element) || (dp.test(value));
            },"(请保留两位小数)"); 
$("#manageClauseVerify").validate({
          rules:{
        	  benefitsName:{
                    required:true,
                   
                   
                 },
                 fee:{
                    required:true,
                    fee:true
                 },
                 benefitMoney:{
                    required:true,
                   
                 },
                 caption:{
                   
                    maxlength:200,
                 }
                 
             },
             messages:{
            	 benefitsName:{
                    required:"(不能为空)",
                    
                 },
                 fee:{
                    required:"(不能为空)",
                 },
                 benefitMoney:{
                    required:"(不能为空)",
                 },
                 caption:{
                   
                    maxlength:"(200个字以内)"
                 },
             },
              errorPlacement: function(error, element){ 
            
                error.appendTo(element.parent()); 
            }

   });
//修改优惠条款
jQuery.validator.addMethod("fee",function(value,element){  
                var dp = /^(([0-9]*?\.\d{2}))$/;
                return this.optional(element) || (dp.test(value));
            },"(请保留两位小数)"); 
$("#changeClauseVerify").validate({
          rules:{
        	  benefitsName:{
                    required:true,
                   
                   
                 },
                 fee:{
                    required:true,
                    fee:true
                 },
                 benefitMoney:{
                    required:true,
                   
                 },
                 caption:{
                   
                    maxlength:200,
                 }
                 
             },
             messages:{
            	 benefitsName:{
                    required:"(不能为空)",
                    
                 },
                 fee:{
                    required:"(不能为空)",
                 },
                 benefitMoney:{
                    required:"(不能为空)",
                 },
                 caption:{
                    
                    maxlength:"(200个字以内)"
                 },
             },
              errorPlacement: function(error, element){ 
            
                error.appendTo(element.parent()); 
            }

   });

//增加房源管理
jQuery.validator.addMethod("buildArea",function(value,element){  
                var bua = /^(([1-9]\d*)|([0-9]*?\.\d{2}))$/;
                return this.optional(element) || (bua.test(value));
            },"(请输入整数或保留两位小数)");
jQuery.validator.addMethod("usefulArea",function(value,element){  
                var useb = /^(([1-9]\d*)|([0-9]*?\.\d{2}))$/;
                return this.optional(element) || (useb.test(value));
            },"(请输入整数或保留两位小数)");
jQuery.validator.addMethod("listPrice",function(value,element){  
                var lp = /^(([0-9]*?\.\d{2}))$/;
                return this.optional(element) || (lp.test(value));
            },"(请保留两位小数)"); 
jQuery.validator.addMethod("minimumPrice",function(value,element){  
                var mp = /^(([0-9]*?\.\d{2}))$/;
                return this.optional(element) || (mp.test(value));
            },"(请保留两位小数)"); 
jQuery.validator.addMethod("shopPrice",function(value,element){  
                var sp = /^(([0-9]*?\.\d{2}))$/;
                return this.optional(element) || (sp.test(value));
            },"(请保留两位小数)"); 
$("#adhouseVerify").validate({

     rules:{
    	 houseNo:{
                    required:true,
                   
                   
                 },
                 district:{
                    required:true,
                    
                 },
                 buildingNo:{
                    required:true,
                   
                 },
                 unit:{
                    required:true,
                    
                 },
                 floor:{
                    required:true,
                    
                 },
                   direct:{
                    required:true,
                    
                 },
                 buildArea:{
                    required:true,
                    buildArea:true
                    
                 },
                 usefulArea:{
                    required:true,
                    usefulArea:true
                    
                 },
                 listPrice:{
                    required:true,
                    listPrice:true
                    
                 },
                 minimumPrice:{
                    required:true,
                    minimumPrice:true
                    
                 },
                 shopPrice:{
                    required:true,
                    shopPrice:true
                    
                 },
             },
             messages:{
            	 houseNo:{
                    required:"(不能为空)",
                    
                 },
                 district:{
                    required:"(不能为空)",
                 },
                 buildingNo:{
                    required:"(不能为空)",
                 },
                unit:{
                    required:"(不能为空)",
                    
                 },
                  floor:{
                    required:"(不能为空)",
                    
                 },
                 direct:{
                    required:"(不能为空)",
                    
                 },
                 buildArea:{
                    required:"(不能为空)",
                    
                 },
                 usefulArea:{
                    required:"(不能为空)",
                    
                 },
                 listPrice:{
                    required:"(不能为空)",
                    
                 },
                 minimumPrice:{
                    required:"(不能为空)",
                    
                 },
                 shopPrice:{
                    required:"(不能为空)",
                    
                 },
             },
              errorPlacement: function(error, element){ 
            
                error.appendTo(element.parent()); 
            }


});


//增加房源管理
jQuery.validator.addMethod("buildArea",function(value,element){  
                var bua = /^(([1-9]\d*)|([0-9]*?\.\d{2}))$/;
                return this.optional(element) || (bua.test(value));
            },"(请输入整数或保留两位小数)");
jQuery.validator.addMethod("usefulArea",function(value,element){  
                var useb = /^(([1-9]\d*)|([0-9]*?\.\d{2}))$/;
                return this.optional(element) || (useb.test(value));
            },"(请输入整数或保留两位小数)");
jQuery.validator.addMethod("listPrice",function(value,element){  
                var lp = /^(([0-9]*?\.\d{2}))$/;
                return this.optional(element) || (lp.test(value));
            },"(请保留两位小数)"); 
jQuery.validator.addMethod("minimumPrice",function(value,element){  
                var mp = /^(([0-9]*?\.\d{2}))$/;
                return this.optional(element) || (mp.test(value));
            },"(请保留两位小数)"); 
jQuery.validator.addMethod("shopPrice",function(value,element){  
                var sp = /^(([0-9]*?\.\d{2}))$/;
                return this.optional(element) || (sp.test(value));
            },"(请保留两位小数)"); 
$("#alertHouseVerify").validate({

     rules:{
    	 houseNo:{
                    required:true,
                   
                   
                 },
                 district:{
                    required:true,
                    
                 },
                 buildingNo:{
                    required:true,
                   
                 },
                 unit:{
                    required:true,
                    
                 },
                 floor:{
                    required:true,
                    
                 },
                   direct:{
                    required:true,
                    
                 },
                 buildArea:{
                    required:true,
                    buildArea:true
                    
                 },
                 usefulArea:{
                    required:true,
                    usefulArea:true
                    
                 },
                 listPrice:{
                    required:true,
                    listPrice:true
                    
                 },
                 minimumPrice:{
                    required:true,
                    minimumPrice:true
                    
                 },
                 shopPrice:{
                    required:true,
                    shopPrice:true
                    
                 },
             },
             messages:{
            	 houseNo:{
                    required:"(不能为空)",
                    
                 },
                 district:{
                    required:"(不能为空)",
                 },
                 buildingNo:{
                    required:"(不能为空)",
                 },
                unit:{
                    required:"(不能为空)",
                    
                 },
                  floor:{
                    required:"(不能为空)",
                    
                 },
                 direct:{
                    required:"(不能为空)",
                    
                 },
                 buildArea:{
                    required:"(不能为空)",
                    
                 },
                 usefulArea:{
                    required:"(不能为空)",
                    
                 },
                 listPrice:{
                    required:"(不能为空)",
                    
                 },
                 minimumPrice:{
                    required:"(不能为空)",
                    
                 },
                 shopPrice:{
                    required:"(不能为空)",
                    
                 },
             },
              errorPlacement: function(error, element){ 
            
                error.appendTo(element.parent()); 
            }


});
//增加账号
jQuery.validator.addMethod("userCaption",function(value,element){  
            var na = /^[\u4E00-\u9FA5]{2,10}$/;
            return this.optional(element) || (na.test(value));
        },"(只允许中文,在2-10个字符之间)");
jQuery.validator.addMethod("phone",function(value,element){  
            var tl = /(^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$)|(^1[34578][0-9]{9}$)/;
            return this.optional(element) || (tl.test(value));
        },"(请输入正确的手机号或固定电话)");
jQuery.validator.addMethod("idCard",function(value,element){  
            var ida = /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/;
            return this.optional(element) || (ida.test(value));
        },"(请输入正确的身份证号)");
$("#addCountVerify").validate({
     rules:{
    	 userCaption:{
                required:true,
                userCaption:true
               
             },
             phone:{
                required:true,
                phone:true
             },
             idCard:{
                required:true,
                idCard:true
             },
             
         },
          messages:{
        	  userCaption:{
                required:"(不能为空)",
                
             },
             phone:{
                required:"(不能为空)",
             },
             idCard:{
                required:"(不能为空)",
             },
           
         }

});
//修改账号
jQuery.validator.addMethod("userCaption",function(value,element){  
            var na = /^[\u4E00-\u9FA5]{2,10}$/;
            return this.optional(element) || (na.test(value));
        },"(只允许中文,在2-10个字符之间)");
jQuery.validator.addMethod("phone",function(value,element){  
            var tl = /(^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$)|(^1[34578][0-9]{9}$)/;
            return this.optional(element) || (tl.test(value));
        },"(请输入正确的手机号或固定电话)");
jQuery.validator.addMethod("idCard",function(value,element){  
            var ida = /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/;
            return this.optional(element) || (ida.test(value));
        },"(请输入正确的身份证号)");
$("#alertAccountVerify").validate({
     rules:{
    	 userCaption:{
                required:true,
                userCaption:true
               
             },
             phone:{
                required:true,
                phone:true
             },
             idCard:{
                required:true,
                idCard:true
             },
             
         },
          messages:{
        	  userCaption:{
                required:"(不能为空)",
                
             },
             phone:{
                required:"(不能为空)",
             },
             idCard:{
                required:"(不能为空)",
             },
           
         }

});
jQuery.validator.addMethod("oldPsw",function(value,element){  
    var pattern = /^\S+$/;
    return this.optional(element) || (pattern.test(value));
},"(密码不能有空格)");
jQuery.validator.addMethod("newPsw",function(value,element){  
    var pattern = /\S+$/;
    return this.optional(element) || (pattern.test(value));
},"(密码不能有空格)");
jQuery.validator.addMethod("surePsd",function(value,element){  
    var pattern = /\S+$/;
    return this.optional(element) || (pattern.test(value));
},"(密码不能有空格)");
$("#psd").validate({
    rules:{
    	oldPsw:{
            required:true,
            oldPsw:true
        },
        newPsw:{
            required:true,
        },
        surePsd:{
            required:true,
            equalTo:"#newPsw"
        }
    },
    messages:{
    	oldPsw:{
            required:"(不能为空)",
        },
        newPsw:{
            required:"(不能为空)",
        },
        surePsd:{
            required:"(不能为空)",
            equalTo:"(与新密码不一致)"
        }
    }
})
//平台验证
jQuery.validator.addMethod("sorting", function(value, element) {
    var sort = /^[0-9]*$/;
    return this.optional(element) || (sort.test(value));
 }, "0或正整数");
jQuery.validator.addMethod("picFile", function(value, element) {
    var geshi = /\.(gif|jpg|jpeg|png|GIF|JPG|PNG|JPEG)$/;
    return this.optional(element) || (geshi.test(value));
 }, "图片格式只支持jpg,png,gif和jpeg");
 $("#add_adver").validate({
     rules:{
    	 picFile:{
     		required:true,
     		picFile:true
     	},
     	adTitle:{
     		required:true,
     	},
     	sorting:{
     		required:true,
     		sorting:true
     	}
     	
     },
      messages:{
    	  picFile:{
      		required:"(请上传图片)",
      		
      	},
      	adTitle:{
     		required:"(不能为空)",
     	},
     	sorting:{
     		required:"(不能为空)",
     		
     	}
     },
     errorPlacement: function(error, element){ 
         
     	 if (element.parent().parent().children().hasClass('up-pic') ) {
              
              error.appendTo(element.parent().parent());

          }
          else{
              error.appendTo(element.parent()); 
          }
     }
  });

});