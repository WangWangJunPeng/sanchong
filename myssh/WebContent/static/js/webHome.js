/* 
* @Author: Marte
* @Date:   2017-03-12 20:02:38
* @Last Modified by:   Marte
* @Last Modified time: 2017-03-28 17:40:06
*/

$(document).ready(function(){
    var swiper = new Swiper('.swiper-container',{
               /* pagination: '.swiper-pagination',
                nextButton: '.swiper-button-next',
                prevButton: '.swiper-button-prev',*/
                paginationClickable: true,
                spaceBetween: 0,
                centeredSlides: true,
                autoplay: 10000,
                autoplayDisableOnInteraction:true,
                /*effect: 'fade',*/
                loop:true
        });
    var swiper2 = new  Swiper('.swiper2',{
        paginationClickable:true,
        spaceBetween: 0,
        centeredSlides: true,
        autoplay: 5000,
        autoplayDisableOnInteraction:true,
        //effect: 'fade',
        loop:true
})   
    var swiper3 = new  Swiper('.swiper3',{
        paginationClickable:true,
        spaceBetween: 0,
        centeredSlides: true,
        autoplay: 4000,
        autoplayDisableOnInteraction:true,
        /*effect: 'fade',*/
        loop:true
    })             
    $("#dowebok").fullpage({   
            sectionsColor:['#ffffff', '#ffffff',"#ffffff",'#ffffff','#ffffff',"#e5e5e5"],
            css:true,
            navigation: true,
            anchors: ['page1', 'page2', 'page3', 'page4','page5', 'page6']                  
        });
    $(".down-line").mouseenter(function(){
        var num = $(this).index();
   
        $(this).eq(num).css({ "color": "#ff6161", "border-bottom": "1px solid #ff6161" }).parent().siblings().children().css({"color": "#ffffff", "border-bottom": "0px solid #ff6161" })
    }) 
    $(".every-lo").mouseenter(function(){
        var num = $(this).index();
      
      
        $(".every-lo").eq(num).css({'background': '#ff6161','border': '1px solid #ff6161'
        }).siblings().css({'background': 'none','border': '1px solid #ffffff'
        })
    }) 
    $(".every-lg").mouseenter(function(){
        var num = $(this).index();    
        $(".every-lg").eq(num).css({'background': '#ff6161','border': '1px solid #ff6161'
        }).siblings().css({'background': 'none','border': '1px solid #ffffff'
        })
    }) 
    $(".every-ld").mouseenter(function(){
        var num = $(this).index();
        $(".every-ld").eq(num).css({'background': '#ff6161','border': '1px solid #ff6161'
        }).siblings().css({'background': 'none','border': '1px solid #ffffff'
        })
    }) 
    $(".every-lk").mouseenter(function(){
        var num = $(this).index();
        $(".every-lk").eq(num).css({'background': '#ff6161','border': '1px solid #ff6161'
        }).siblings().css({'background': 'none','border': '1px solid #ffffff'
        })
    }) 



    $(".way-one").mouseover(function() {
        $(".bg1").attr("src","http://root-1252627680.cossh.myqcloud.com/static/images/scoll-4/redBg.png");
        $(".i1").attr("src","http://root-1252627680.cossh.myqcloud.com/static/images/scoll-4/redEye.png");
        $(".house1").css("color","#e74c3c");
        $(".read1").css({"background":"#e74c3c","box-shadow":"0px 5px 5px rgba(231,76,60,0.2)"})
    });
    $(".way-one").mouseleave(function() {
        $(".bg1").attr("src","http://root-1252627680.cossh.myqcloud.com/static/images/scoll-4/bgIcon.png");
        $(".i1").attr("src","http://root-1252627680.cossh.myqcloud.com/static/images/scoll-4/icon1.png");

        $(".house1").css("color","#999");
        $(".read1").css({"background":"#979faa","box-shadow":" 0px 5px 5px rgba(151,159,170,0.2)"})
    });

    $(".way-two").mouseover(function() {
        $(".bg2").attr("src","http://root-1252627680.cossh.myqcloud.com/static/images/scoll-4/redBg.png");
        $(".i2").attr("src","http://root-1252627680.cossh.myqcloud.com/static/images/scoll-4/redCycle.png");
        $(".house2").css("color","#e74c3c");
        $(".read2").css({"background":"#e74c3c","box-shadow":"0px 5px 5px rgba(231,76,60,0.2)"})
    });
    $(".way-two").mouseleave(function() {
        $(".bg2").attr("src","http://root-1252627680.cossh.myqcloud.com/static/images/scoll-4/bgIcon.png");
        $(".i2").attr("src","http://root-1252627680.cossh.myqcloud.com/static/images/scoll-4/icon2.png");
        $(".house2").css("color","#999");
        $(".read2").css({"background":"#979faa","box-shadow":" 0px 5px 5px rgba(151,159,170,0.2)"})
    });

    $(".way-three").mouseover(function() {
        $(".bg3").attr("src","http://root-1252627680.cossh.myqcloud.com/static/images/scoll-4/redBg.png");
        $(".i3").attr("src","http://root-1252627680.cossh.myqcloud.com/static/images/scoll-4/redEarth.png");
        $(".house3").css("color","#e74c3c");
        $(".read3").css({"background":"#e74c3c","box-shadow":"0px 5px 5px rgba(231,76,60,0.2)"})
    });
    $(".way-three").mouseleave(function() {
        $(".bg3").attr("src","http://root-1252627680.cossh.myqcloud.com/static/images/scoll-4/bgIcon.png");
        $(".i3").attr("src","http://root-1252627680.cossh.myqcloud.com/static/images/scoll-4/icon3.png");
        $(".house3").css("color","#999");
        $(".read3").css({"background":"#979faa","box-shadow":" 0px 5px 5px rgba(151,159,170,0.2)"})
    });

    $(".way-four").mouseover(function() {
        $(".bg4").attr("src","http://root-1252627680.cossh.myqcloud.com/static/images/scoll-4/redBg.png");
        $(".i4").attr("src","http://root-1252627680.cossh.myqcloud.com/static/images/scoll-4/redCar.png");
        $(".house4").css("color","#e74c3c");
        $(".read4").css({"background":"#e74c3c","box-shadow":"0px 5px 5px rgba(231,76,60,0.2)"})
    });
    $(".way-four").mouseleave(function() {
        $(".bg4").attr("src","http://root-1252627680.cossh.myqcloud.com/static/images/scoll-4/bgIcon.png");
        $(".i4").attr("src","http://root-1252627680.cossh.myqcloud.com/static/images/scoll-4/icon4.png");
        $(".house4").css("color","#999");
        $(".read4").css({"background":"#979faa","box-shadow":" 0px 5px 5px rgba(151,159,170,0.2)"})
    });
    $(".down1").mouseover(function(){
        $(this).css({'color':'#ff6161','border-bottom':'2px solid #ff6161'});
        $(".dist1").animate({width: "145px", height: "143px"}, 500)
    })
    $(".down1").mouseleave(function(){
        $(this).css({'color':'#484948','border-bottom':'0px solid #ff6161'});
        $(".dist1").animate({width: "116px", height: "114px"}, 500)
    })


    $(".down2").mouseover(function(){
        $(this).css({'color':'#ff6161','border-bottom':'2px solid #ff6161'});
        $(".dist2").animate({width: "145px", height: "143px"}, 500)
    })
    $(".down2").mouseleave(function(){
        $(this).css({'color':'#484948','border-bottom':'0px solid #ff6161'});
        $(".dist2").animate({width: "116px", height: "114px"}, 500)
    })

    $(".down3").mouseover(function(){
        $(this).css({'color':'#ff6161','border-bottom':'2px solid #ff6161'});
        $(".dist3").animate({width: "145px", height: "143px"}, 500)
    })
    $(".down3").mouseleave(function(){
        $(this).css({'color':'#484948','border-bottom':'0px solid #ff6161'});
        $(".dist3").animate({width: "116px", height: "114px"}, 500)
    })

    $(".down4").mouseover(function(){
        $(this).css({'color':'#ff6161','border-bottom':'2px solid #ff6161'});
        $(".dist4").animate({width: "145px", height: "143px"}, 500)
    })
    $(".down4").mouseleave(function(){
        $(this).css({'color':'#484948','border-bottom':'0px solid #ff6161'});
        $(".dist4").animate({width: "116px", height: "114px"}, 500)
    })

    $(".down5").mouseover(function(){
        $(this).css({'color':'#ff6161','border-bottom':'2px solid #ff6161'});
        $(".dist5").animate({width: "145px", height: "143px"}, 500)
    })
    $(".down5").mouseleave(function(){
        $(this).css({'color':'#484948','border-bottom':'0px solid #ff6161'});
        $(".dist5").animate({width: "116px", height: "114px"}, 500)
    })




    $(".dist1").mouseover(function() {
        $(this).animate({width: "145px", height: "143px"}, 500)
        $(".down1").css({'color':'#ff6161','border-bottom':'2px solid #ff6161'});
    });
    $(".dist1").mouseleave(function() {
        $(this).animate({width: "116px", height: "114px"}, 500)
        $(".down1").css({'color':'#484948','border-bottom':'0px solid #ff6161'});
    });

    $(".dist2").mouseover(function() {
        $(this).animate({width: "145px", height: "143px"}, 500)
        $(".down2").css({'color':'#ff6161','border-bottom':'2px solid #ff6161'});
    });
    $(".dist2").mouseleave(function() {
        $(this).animate({width: "116px", height: "114px"}, 500)
        $(".down2").css({'color':'#484948','border-bottom':'0px solid #ff6161'});
    });

    $(".dist3").mouseover(function() {
        $(this).animate({width: "145px", height: "143px"}, 500)
        $(".down3").css({'color':'#ff6161','border-bottom':'2px solid #ff6161'});
    });
    $(".dist3").mouseleave(function() {
        $(this).animate({width: "116px", height: "114px"}, 500)
        $(".down3").css({'color':'#484948','border-bottom':'0px solid #ff6161'});
    });

    $(".dist4").mouseover(function() {
        $(this).animate({width: "145px", height: "143px"}, 500)
        $(".down4").css({'color':'#ff6161','border-bottom':'2px solid #ff6161'});
    });
    $(".dist4").mouseleave(function() {
        $(this).animate({width: "116px", height: "114px"}, 500)
        $(".down4").css({'color':'#484948','border-bottom':'0px solid #ff6161'});
    });
    $(".dist5").mouseover(function() {
        $(this).animate({width: "145px", height: "143px"}, 500)
        $(".down5").css({'color':'#ff6161','border-bottom':'2px solid #ff6161'});
    });
    $(".dist5").mouseleave(function() {
        $(this).animate({width: "116px", height: "114px"}, 500)
        $(".down5").css({'color':'#484948','border-bottom':'0px solid #ff6161'});
    });



   /* $(".login").click(function(){
        popBox();
        $(".loginPop").show();
    })*/
});

function lyq(){
	window.location.href="tologinPage";
}
function shopReg(){
	window.location.href = "shop_regs";
}
 /*   function popBox(){
        var sWidth=document.body.scrollWidth;
        var sHeight=document.body.scrollHeight; 
        var wHeight=document.documentElement.clientHeight;
        var oMask=document.createElement("div");
            oMask.className="mask";
            oMask.style.height=sHeight+"px";
            oMask.style.width=sWidth+"px";
            document.body.appendChild(oMask);
            $('.delete').click(function(){
                $(".loginPop").hide();
                $(".mask").remove();
            })      
    }*/