<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">  
         <meta name="viewport" content="width=device-width, initial-scale=1" />
         <link  rel="icon" href="http://root-1252955170.cossh.myqcloud.com/static/images/titleLogo.png"  />
        <title>九邑-中国房产线上分销平台</title>
        <link rel="stylesheet" href="static/css/jquery.fullPage.css">
        <link rel="stylesheet" href="static/css/swiper.css">
        <link rel="stylesheet" href="static/css/webHome.css">
    </head>
    <body>
        <div id="dowebok"> 
            <div class="section swiper-container" >
                 <div  class="header">
                    <div class="neck">						 
                        <a href="###" class="logo"><img src="http://root-1252955170.cossh.myqcloud.com/static/images/scoll-1/logo.png" alt=""  style="width:100%;"/></a>
                        <ul id='menu'>
                            <li data-menuanchor="page1"><a href="#page1" class='down-line' style="color:#ff6161;border-bottom:1px solid #ff6161;">首页</a></li>
                            <li data-menuanchor="page2"><a href="#page2" class='down-line'>关于我们</a> </li>                               
                            <li data-menuanchor="page3"><a href="#page3" class='down-line'>九邑案场大师</a></li>
                            <li data-menuanchor="page4"><a href="#page4" class='down-line'>九邑Sales</a></li> 
                            <li data-menuanchor="page5"><a href="#page5" class='down-line'>九邑分销</a></li>  
                            <li data-menuanchor="page6"><a href="#page6" class='down-line'>数据安全</a></li>  
                        </ul>
                    </div> 
                </div>
                <div class="swiper-wrapper" >  
                    <div class="swiper-slide" >
                        <img alt="" src="http://root-1252955170.cossh.myqcloud.com/static/images/scoll-1/banner2.jpg" style="width:100%;height:95%;position:absolute;">
                         <div class="banner-word">
                            <p class="title-word">JOININ&nbsp;HOUSE&nbsp;PROPERTY</p>
                            <h1 class="jiuyi">九邑房产SaaS</h1>
                            <P class="disc">科技服务销售</P>
                            <div class="decoration">
                                <img src="http://root-1252955170.cossh.myqcloud.com/static/images/scoll-1/decoration.png" alt="" />
                            </div>
                            <div class="reg-login">
                                <a href="###" style="margin-right:15px;background: #ff6161;border:1px solid #ff6161;" class="every-lg" onclick="lyq()">登录</a>
                                <a href="###" style="margin-left:15px;margin-right:15px;" class="every-lg">开发商申请</a>
                                <a href="###" style="margin-left:15px;" class="every-lg" onclick="shopReg()">门店注册</a>
                            </div>
                             <div class="down">
                                <img src="http://root-1252955170.cossh.myqcloud.com/static/images/scoll-1/down.png" alt="" />
                            </div>
                        </div>
                    </div> 

                    <div class="swiper-slide" >
                        <img alt="" src="http://root-1252955170.cossh.myqcloud.com/static/images/scoll-1/banner1.jpg" style="width:100%;height:95%;position:absolute;">
                        <div class="banner-word ">
                            <p class="title-word">JOININ&nbsp;MASTER</p>
                            <h1 class="jiuyi">九邑案场大师</h1>
                            <P class="disc">国内第一款实现内外场一体化管理的案场SAAS系统 </P>
                            <div class="decoration">
                                <img src="http://root-1252955170.cossh.myqcloud.com/static/images/scoll-1/decoration.png" alt="" />
                            </div>
                            <div class="reg-login">
                                <a href="###" style="margin-right:15px;background-color: #ff6161;border:1px solid #ff6161;" class="every-lo" onclick="lyq()">登录</a>
                                <a href="###" style="margin-left:15px;margin-right:15px;" class="every-lo">开发商申请</a>
                                <a href="###" style="margin-left:15px;" class="every-lo" onclick="shopReg()">门店注册</a>
                            </div>
                            <div class="down">
                                <img src="http://root-1252955170.cossh.myqcloud.com/static/images/scoll-1/down.png" alt="" />
                            </div>
                        </div>
                    </div>
                                                        
                    <div class="swiper-slide" >
                        <img alt="" src="http://root-1252955170.cossh.myqcloud.com/static/images/scoll-1/banner3.jpg" style="width:100%;height:95%;position:absolute;">
                         <div class="banner-word">
                            <p class="title-word">JOININ&nbsp;NEW&nbsp;PROPERTY</p>
                            <h1 class="jiuyi">九邑Sales</h1>
                            <P class="disc">国内第一款与开发商数据实时对接的门店SaaS系统</P>
                            <div class="decoration">
                                <img src="http://root-1252955170.cossh.myqcloud.com/static/images/scoll-1/decoration.png" alt="" />
                            </div>
                            <div class="reg-login">
                              <a href="###" style="margin-right:15px;background-color: #ff6161;border:1px solid #ff6161;" class="every-ld" onclick="lyq()">登录</a>
                                 <a href="###" style="margin-left:15px;margin-right:15px;" class="every-ld">开发商申请</a>
                                <a href="###" style="margin-left:15px;" class="every-ld" onclick="shopReg()">门店注册</a>
                            </div>
                             <div class="down">
                                <img src="http://root-1252955170.cossh.myqcloud.com/static/images/scoll-1/down.png" alt="" />
                            </div>
                        </div>
                    </div>

                    <div class="swiper-slide" >
                        <img alt="" src="http://root-1252955170.cossh.myqcloud.com/static/images/scoll-1/banner4.jpg" style="width:100%;height:95%;position:absolute;">
                        <div class="banner-word">
                            <p class="title-word">JOININ&nbsp;DISTRIBUTION</p>
                            <h1 class="jiuyi">九邑分销</h1>
                            <P class="disc">国内第一款以企业SaaS系统为基础的第三方房产全程电子商务平台 </P>
                            <div class="decoration">
                                <img src="http://root-1252955170.cossh.myqcloud.com/static/images/scoll-1/decoration.png" alt="" />
                            </div>
                            <div class="reg-login">
                               <a href="###" style="margin-right:15px;background-color: #ff6161;border:1px solid #ff6161;" class="every-lk" onclick="lyq()">登录</a>
                                 <a href="###" style="margin-left:15px;margin-right:15px;" class="every-lk">开发商申请</a>
                                <a href="###" style="margin-left:15px;" class="every-lk" onclick="shopReg()">门店注册</a>
                            </div>
                             <div class="down">
                                <img src="http://root-1252955170.cossh.myqcloud.com/static/images/scoll-1/down.png" alt="" />
                            </div>
                        </div>
                    </div>
                </div>    
            </div>

            <div class="section">
                <div class="banner-about">
                    <div class="about-us">
                        <img src="static/images/scoll-2/aboutUs.png" alt="" />
                    </div>
                    <div class="about-word">
                        <img src="http://root-1252955170.cossh.myqcloud.com/static/images/lineLeft.png" alt="" />
                        <span>关于我们</span>
                        <img src="http://root-1252955170.cossh.myqcloud.com/static/images/lineRight.png" alt="" />
                    </div>
                    <div class="about-intro">
	                   <p>九邑房产SaaS是服务于房地产行业的新一代智慧式SaaS系统。</p>
	                   <p>目前拥有三大产品：专门服务于开发商项目销售的九邑案场大师、服务于中介机构的九邑sales以及服务于一二手联动业务的九邑房产线上分销平台。</p>
	                    <p>是目前业内实现销售业务线上化的最佳解决方案。</p>
	                  </div>
                    <div class="icons">
                        <div class="single lonePic">
                            <div class="single-pic">
                                <img src="http://root-1252955170.cossh.myqcloud.com/static/images/scoll-2/addPro.png" alt="" />
                            </div>
                            <p class="icon-one">目前加盟项目</p>
                            <div class="shape">
                                <img src="http://root-1252955170.cossh.myqcloud.com/static/images/Shape.png" alt="" />
                            </div>
                            <p class="icon-two">25</p>
                        </div>
                         <div class="single others">
                            <div class="single-pic">
                                <img src="http://root-1252955170.cossh.myqcloud.com/static/images/scoll-2/haveHouse.png" alt="" />
                            </div>
                            <p class="icon-one">目前拥有房源</p>
                            <div class="shape">
                                <img src="http://root-1252955170.cossh.myqcloud.com/static/images/Shape.png" alt="" />
                            </div>
                            <p class="icon-two">1025</p>
                        </div>
                         <div class="single others">
                            <div class="single-pic">
                                <img src="http://root-1252955170.cossh.myqcloud.com/static/images/scoll-2/regShop.png" alt="" />
                            </div>
                            <p class="icon-one">目前注册门店</p>
                            <div class="shape">
                                <img src="http://root-1252955170.cossh.myqcloud.com/static/images/Shape.png" alt="" />
                            </div>
                            <p class="icon-two">2342</p>
                        </div>
                         <div class="single others">
                            <div class="single-pic">
                                <img src="http://root-1252955170.cossh.myqcloud.com/static/images/scoll-2/lineAgent.png" alt="" />
                            </div>
                            <p class="icon-one">目前在线经纪人</p>
                            <div class="shape">
                                <img src="http://root-1252955170.cossh.myqcloud.com/static/images/Shape.png" alt="" />
                            </div>
                            <p class="icon-two">21006</p>
                        </div>
                    </div>
                    
                    <div class="auto-play">
                        <div class="left-auto ">
                            <div class="swiper-container swiper2 auto-one" style="background:url(static/images/scoll-2/mac.png)top center no-repeat;">
                                <div class="swiper-wrapper" style="top:22px;">

                                    <div class="swiper-slide" ><img src="static/images/scoll-2/action.png" alt="" style="width:92%;"/></div>
                                    <div class="swiper-slide" ><img src="static/images/scoll-2/master.png" alt=""  style="width:92%;"/></div> 

                                </div>
                            </div>
                            <div class="auto-two swiper-container swiper3" style="background:url(static/images/scoll-2/ipone.png)top center no-repeat;">                          
                                <div class="swiper-wrapper" style="top:25px;">
                                    <div class="swiper-slide"><img src="static/images/scoll-2/agent.png" alt="" style="width:90%;"/></div>
                                    <div class="swiper-slide"><img src="static/images/scoll-2/houseList.png" alt=""  style="width:90%;"/></div>
                                    <div class="swiper-slide"><img src="static/images/scoll-2/work.png" alt=""  style="width:90%;"/></div>

                                </div>
                            </div>
                        </div>
                        <div class="right-auto" >                        
                           <div class="erwei" style="float:left;margin-left:80px;margin-top:130px;">
                           		 <img src="static/images/andriod.png" alt="" />
                           		 <p style="font-size:18px;color:#666666;margin-top:30px;">下载安卓版</p>
                           </div>
                           <div class="erwei" style="float:left;margin-left:74px;margin-top:130px;">
                           		 <img src="static/images/ios.png" alt="" />
                           		 <p style="font-size:18px;color:#666666;margin-top:30px;">下载IOS版</p>
                           </div>                          
                            <div class="read-more">
                                  <a href="###">READ&nbsp;MORE</a>
                            </div>
                        </div>
                    </div>   
                </div>           
            </div>

            <div class="section">
                <div class="master">
                    <div class="master-joinin">
                        <img src="static/images/scoll-3/master.png" alt="" />
                    </div>
                    <div class="master-word">
                        <img src="http://root-1252955170.cossh.myqcloud.com/static/images/lineLeft.png" alt="" />
                        <span>九邑案场大师</span>
                        <img src="http://root-1252955170.cossh.myqcloud.com/static/images/lineRight.png" alt="" />
                    </div>
                     <p>新一代智慧式案场管理系统</p>
                     <div class="many">
                         <div class="master-pic" style="background:url(static/images/scoll-3/firstPic.png)">
                             
                         </div>
                         <div class="master-pic master-otherOne" style="background:url(static/images/scoll-3/bottomOne.png)" >
                          
                            <img src="http://root-1252955170.cossh.myqcloud.com/static/images/scoll-3/decLine.png" alt="" />
                            <div  class="vistor-sure">
                                <h3>访客无漏确认</h3>
                                <p>Visitors&nbsp;without &nbsp;confirmation</p>
                                <div class="read-more">
                                    <a href="###">READ&nbsp;MORE</a>
                                </div>
                            </div>
                         </div>
                         <div class="master-pic" style="background:url(static/images/scoll-3/secondPic.png)">
                             
                         </div>
                        <div class="master-pic master-otherOne" style="background:url(static/images/scoll-3/bottomTwo.png)">
                            <img src="http://root-1252955170.cossh.myqcloud.com/static/images/scoll-3/decLine.png" alt="" />
                            <div  class="vistor-sure">
                                <h3>任务自动跟踪</h3>
                                <p>Automatic&nbsp; tracking &nbsp;the&nbsp; task</p>
                                <div class="read-more">
                                    <a href="###">READ&nbsp;MORE</a>
                                </div>
                            </div>
                         </div>
                          <div class="master-pic master-otherTwo" style="background:url(static/images/scoll-3/bottomThree.png)">
                            <img src="http://root-1252955170.cossh.myqcloud.com/static/images/scoll-3/decLine.png" alt="" />
                            <div  class="vistor-sure">
                                <h3>在线销控</h3>
                                <p>Line &nbsp;report&nbsp; later</p>
                                <div class="read-more">
                                    <a href="###">READ&nbsp;MORE</a>
                                </div>
                            </div>
                         </div>
                          <div class="master-pic" style="background:url(static/images/scoll-3/thirdPic.png)">
                         </div>
                          <div class="master-pic master-otherTwo" style="background:url(static/images/scoll-3/bottomFour.png)">
                            <img src="http://root-1252955170.cossh.myqcloud.com/static/images/scoll-3/decLine.png" alt="" />
                            <div  class="vistor-sure">
                                <h3>移动销售</h3>
                                <p>Stores&nbsp; subscribe</p>
                                <div class="read-more">
                                    <a href="###">READ&nbsp;MORE</a>
                                </div>
                            </div>
                         </div>
                          <div class="master-pic" style="background:url(static/images/scoll-3/fourthPic.png)">
                         </div>
                     </div>
                </div>
            </div>

            <div class="section" style="background:url(http://root-1252955170.cossh.myqcloud.com/static/images/scoll-4/linedec.png)center no-repeat">
                <div class="new-house">
                    <div class="new-joinin">
                        <img src="static/images/scoll-4/joininHouse.png" alt="" />
                    </div>
                    <div class="new-word">
                        <img src="http://root-1252955170.cossh.myqcloud.com/static/images/lineLeft.png" alt="" />
                        <span>九邑Sales</span>
                        <img src="http://root-1252955170.cossh.myqcloud.com/static/images/lineRight.png" alt="" />
                    </div>
                     <p>一二手联动业务的最佳线上解决方案</p>
                    <div class="house-style">
                        <div class="every-way way-one" >
                            <img src="http://root-1252955170.cossh.myqcloud.com/static/images/scoll-4/bgIcon.png" alt=""  class="images1 bg1" />
                            <img src="http://root-1252955170.cossh.myqcloud.com/static/images/scoll-4/iconBottom.png" alt=""  class="images2" />  
                            <img src="http://root-1252955170.cossh.myqcloud.com/static/images/scoll-4/icon1.png" alt=""  class="images3 i1"/>
                            <div class="house-word">
                                <h3 class="house1">新房公盘</h3>
                                <div class="read-more">
                                    <a href="###"  class="read1">READ&nbsp;MORE</a>
                                </div>
                            </div>
                        </div>
                        <div class="every-way way-two">
                            <img src="http://root-1252955170.cossh.myqcloud.com/static/images/scoll-4/bgIcon.png" alt="" class="images1 bg2"/>
                            <img src="http://root-1252955170.cossh.myqcloud.com/static/images/scoll-4/iconBottom.png" alt=""  class="images2" />  
                            <img src="http://root-1252955170.cossh.myqcloud.com/static/images/scoll-4/icon2.png" alt=""  class="images3 i2"  />
                            <div class="house-word">
                                <h3  class="house2">房源同步</h3>
                                <div class="read-more">
                                    <a href="###" class="read2">READ&nbsp;MORE</a>
                                </div>
                            </div>
                        </div>
                        <div class="every-way way-three">
                            <img src="http://root-1252955170.cossh.myqcloud.com/static/images/scoll-4/bgIcon.png" alt="" class="images1 bg3"/>
                             <img src="http://root-1252955170.cossh.myqcloud.com/static/images/scoll-4/iconBottom.png" alt=""  class="images2" />  
                            <img src="http://root-1252955170.cossh.myqcloud.com/static/images/scoll-4/icon3.png" alt=""  class="images3 i3"  />
                            <div class="house-word">
                                <h3 class="house3">在线销控</h3>
                                <div class="read-more">
                                    <a href="###"  class="read3">READ&nbsp;MORE</a>
                                </div>
                            </div>
                        </div>
                        <div class="every-way way-four">
                            <img src="http://root-1252955170.cossh.myqcloud.com/static/images/scoll-4/bgIcon.png" alt="" class="images1 bg4"/>
                             <img src="http://root-1252955170.cossh.myqcloud.com/static/images/scoll-4/iconBottom.png" alt=""  class="images2" />  
                            <img src="http://root-1252955170.cossh.myqcloud.com/static/images/scoll-4/icon4.png" alt=""  class="images3 i4"  />
                            <div class="house-word">
                                <h3 class="house4">移动销售</h3>
                                <div class="read-more">
                                    <a href="###"  class="read4">READ&nbsp;MORE</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>     
            </div>

            <div class="section " style="background:url(http://root-1252955170.cossh.myqcloud.com/static/images/scoll-5/bgTwo.png)center no-repeat">
               <div class="distribution">
                    <div class="distribution-joinin">
                        <img src="static/images/scoll-5/dist.png" alt="" />
                    </div>
                    <div class="distribution-word">
                        <img src="http://root-1252955170.cossh.myqcloud.com/static/images/lineLeft.png" alt="" />
                        <span>九邑分销</span>
                        <img src="http://root-1252955170.cossh.myqcloud.com/static/images/lineRight.png" alt="" />
                    </div>
                     <p>一二手联动业务的最佳线上解决方案</p>
                     <div class="save-way">
                         <ul>
                            <li style="margin-left:80px;"><a href="###" class="down1">业务完全线上化</a></li>
                            <li><a href="###" class="down2">真实实时房源</a></li>
                            <li><a href="###" class="down3">第三方平台确客</a></li>
                            <li><a href="###" class="down4">快速结佣担保</a></li>
                            <li><a href="###" class="down5">快速认购担保</a></li>
                         </ul>
                        <div style="width:95.5%;position:absolute;top:274px;height:10px;left:0;">
                            <img src="http://root-1252955170.cossh.myqcloud.com/static/images/scoll-5/length.png" alt="" style="width:100%;"/>
                        </div>
                     </div>  
                     <div class="dist-icon">
                        <div class="dist-box">          
                             <div class="every-dist" style="margin-left:0px;margin-top:110px">
                                 <div class="dist-pic">
                                     <img src="http://root-1252955170.cossh.myqcloud.com/static/images/scoll-5/distOne.png" alt="" class="dist1" />
                                 </div>
                                 <div style="height:85px;width:1px;background:#f4f5f6;margin:-25px auto;"></div>
                                 <p style="margin-top:50px;">业务完全线上化</p>
                                 <p style="font-size:12px;color:#999999;">Business&nbsp;online</p>
                             </div>
                             <div class="every-dist" style="margin-left:0px;margin-top:90px;">
                                 <div class="dist-pic">
                                     <img src="http://root-1252955170.cossh.myqcloud.com/static/images/scoll-5/distTwo.png" alt="" class="dist2"/>
                                 </div>
                                  <div style="height:85px;width:1px;background:#f4f5f6;margin:-25px auto;"></div>
                                 <p style="margin-top:50px;">真实实时房源</p>
                                  <p style="font-size:12px;color:#999999;">Real  &nbsp;estate</p>
                             </div>
                             <div class="every-dist" style="margin-left:0px;margin-top:140px;">
                                 <div class="dist-pic">
                                     <img src="http://root-1252955170.cossh.myqcloud.com/static/images/scoll-5/distThree.png" alt="" class="dist3"/>
                                 </div>
                                  <div style="height:85px;width:1px;background:#f4f5f6;margin:-25px auto;"></div>
                                 <p style="margin-top:50px;">第三平台确客</p>
                                  <p style="font-size:12px;color:#999999;">Third—party  &nbsp;platform</p>
                             </div>
                             <div class="every-dist" style="margin-left:0px;margin-top:160px;">
                                 <div class="dist-pic">
                                     <img src="http://root-1252955170.cossh.myqcloud.com/static/images/scoll-5/distFour.png" alt="" class="dist4"/>
                                 </div>
                                  <div style="height:85px;width:1px;background:#f4f5f6;margin:-25px auto;"></div>
                                 <p style="margin-top:50px;">快速结佣担保</p>
                                  <p style="font-size:12px;color:#999999;">Payment &nbsp; warranty</p>
                             </div>
                             <div class="every-dist" style="margin-left:0px;margin-top:110px;">
                                 <div class="dist-pic">
                                     <img src="http://root-1252955170.cossh.myqcloud.com/static/images/scoll-5/distFive.png" alt="" class="dist5"/>
                                 </div>
                                  <div style="height:85px;width:1px;background:#f4f5f6;margin:-25px auto;"></div>
                                 <p style="margin-top:50px;">快速认购担保</p>
                                  <p style="font-size:12px;color:#999999;">Order &nbsp;warranty</p>
                             </div>
                        </div>
                        <div class="read-more">
                        	<a href="###" >READ&nbsp;MORE</a>
                    	</div>
                     </div>
                     
               </div>  
            </div>

            <div class="section ">
                <div class="data-safe">
                    <div>
                        <img src="static/images/scoll-6/dataSecurity.png" alt="" />
                    </div>
                    <div class="data-word">
                        <img src="http://root-1252955170.cossh.myqcloud.com/static/images/lineLeft.png" alt="" />
                        <span>数据安全</span>
                        <img src="http://root-1252955170.cossh.myqcloud.com/static/images/lineRight.png" alt="" />
                    </div>
                    <p>一二手联动业务的最佳线上解决方案</p>
                    <div class="fank-ali">
                        <div class="aLiDiv" >
                            <div style="margin-top:50px;">
                                <img src="http://root-1252955170.cossh.myqcloud.com/static/images/scoll-6/liIcon.png" alt="" />
                            </div>
                            <div style="margin-top:15px;">
                                <img src="http://root-1252955170.cossh.myqcloud.com/static/images/scoll-6/aLiYun.png" alt="" />
                            </div>
                            <div style="margin-top:15px;">
                                <img src="http://root-1252955170.cossh.myqcloud.com/static/images/Shape.png" alt="" />
                            </div>
                            <div style="margin:30px auto 0 auto;width:90%;">
                                <img src="http://root-1252955170.cossh.myqcloud.com/static/images/scoll-6/yunWord.png" alt="" style="width:100%;"/>
                                <!-- <p style="font-size:16px;line-height:20px;letter-spacing:5px;">九邑案场大师的关键数据置于全球领先、安全、稳定的云计算产品--阿里云平台之上，覆盖从服务器、网络、数据、业务的完整安全所需，实现安全的立体防护。而数据传输和展示及存储则采用独创“数据黑盒”技术，保障数据在传输、存储、展示、比对等环节中的安全。</p> -->
                            </div>
                            <div class="read-more">
                                    <a href="###">READ&nbsp;MORE</a>
                            </div>
                        </div>
                        <div class="rightPenPic">
                            <img src="static/images/scoll-6/penPic.png" alt="" />
                        </div>
                    </div>
                </div>
                <div class="footer" >
                    <div class="foot-word" style="margin-top:25px;">
                        <p style="color:#999;font-size:14px;">Copyright © 2004-2016 三重奏科技有限公司 版权所有&nbsp;  &nbsp;备案号：浙ICP备16001756号&nbsp;  &nbsp;TEL:0571-85152408</p>
                    </div>
                </div>
            </div> 
    </div>
  
       <script type="text/javascript" src="static/js/jquery-3.1.1.min.js"></script>
        <script type="text/javascript" src="static/js/webHome.js"></script>
        <script type="text/javascript" src="static/js/swiper.jquery.min.js"></script>
        <script type="text/javascript" src="static/js/jquery.fullPage.min.js"></script>
    </body>
</html>