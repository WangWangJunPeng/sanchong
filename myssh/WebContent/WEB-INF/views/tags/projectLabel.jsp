<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>案场助理个人中心</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- Loading Bootstrap -->
    <link href="static/css/bootstrap/css/bootstrap.css" rel="stylesheet">

    <!-- Loading Stylesheets -->    
    <link href="static/css/archon.css" rel="stylesheet">
    <link href="static/css/responsive.css" rel="stylesheet">
    <link href="static/css/timeline.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="static/css/projectLabel.css" />
   

    <link  rel="icon" href="http://root-1252955170.cossh.myqcloud.com/static/images/titleLogo.png"  />

    
</head>
<body>
   
<%@ include file="publicLabelHead.jsp" %>
            <div id="main-content" class="white" >
                <div class="row all-row">
                   <div class="col-md-8">
                    <div class="row profile-head" >
                        <p><span>*</span><a href="">首页</a>><a href="">案场信息维护</a>><a href="">标签选择</a>><a href="">项目标签</a></p>
                       <a class="aa">选择标签列表</a>
                    </div>
                    <div class="row profile-body ">
                        <div class="col-md-9" id="myTag">
                            <!-- <div class="row">
                               <div class="col-md-2 col4"><span>规模</span></div>
                               <div class="col-md-10 col8">
                                    <span>超大盘</span>
                                    <span>大盘</span>
                                    <span>中盘</span>
                                    <span>小盘</span>
                                    <span>中盘</span>
                                    <span>小盘</span>
                                    <span>中盘</span>
                                    <span>小盘</span>
                               </div>
                            </div>
                            <div class="row">
                               <div class="col-md-2 col4"><span>位置</span></div>
                               <div class="col-md-10 col8">
                                    <span>超大盘</span>
                                    <span>大盘</span>
                                    <span>中盘</span>
                                    <span>小盘</span>
                               </div>
                            </div>
                             <div class="row">
                               <div class="col-md-2 col4"><span>方向</span></div>
                               <div class="col-md-10 col8">
                                    <span>超大盘</span>
                                    <span>大盘</span>
                                    <span>中盘</span>
                                    <span>小盘</span>
                               </div>
                            </div>
                             <div class="row">
                               <div class="col-md-2 col4"><span>物业</span></div>
                               <div class="col-md-10 col8">
                                    <span>超大盘</span>
                                    <span>大盘</span>
                                    <span>中盘</span>
                                    <span>小盘</span>
                               </div>
                            </div>
                             <div class="row">
                               <div class="col-md-2 col4"><span>配套</span></div>
                               <div class="col-md-10 col8">
                                    <span>超大盘</span>
                                    <span>大盘</span>
                                    <span>中盘</span>
                                    <span>小盘</span>
                               </div>
                            </div>
                             <div class="row">
                               <div class="col-md-2 col4"><span>环境</span></div>
                               <div class="col-md-10 col8">
                                    <span>超大盘</span>
                                    <span>大盘</span>
                                    <span>中盘</span>
                                    <span>小盘</span>
                               </div>
                            </div> -->
                        </div>
                      
                    </div>

                   </div>
                   <div class="col-md-4 right-col4" style="display:none;">
                       <div class="row right-list">
                           <p>标签列表</p>
                       </div>
                       <form action="" id="tagTable">
                       	<a id="tableHelper" style="display:none;"></a>
                          <!--  <div class="row  right-row " >
                               <div class="col-md-3 right-col2">
                                   <span>规模</span>
                               </div>
                               <div class="col-md-9">
                                    <label for="0" class="mo">超大盘</label>
                                   <input type="radio" name="gui" id="0" style="display:none;"/>
                                    <label for="1" class="mo">大盘</label>
                                   <input type="radio" name="gui" id="1" style="display:none;"/>
                                    <label for="2" class="mo">中盘</label>
                                   <input type="radio" name="gui" id="2" style="display:none;"/>
                                    <label for="3" class="mo">小盘</label>
                                   <input type="radio" name="gui" id="3" style="display:none;"/>
                               </div>
                           </div>
                             <div class="row  right-row " >
                               <div class="col-md-3 right-col2">
                                   <span>位置</span>
                               </div>
                               <div class="col-md-9">
                                    <label for="4" class="mo">二环</label>
                                   <input type="radio" name="wei" id="4" style="display:none;"/>
                                    <label for="5" class="mo">三环</label>
                                   <input type="radio" name="wei" id="5" style="display:none;"/>
                                    <label for="6" class="mo">四环</label>
                                   <input type="radio" name="wei" id="6" style="display:none;"/>
                                    <label for="7" class="mo">五环</label>
                                   <input type="radio" name="wei" id="7" style="display:none;"/>

                               </div>
                           </div>
                            <div class="row  right-row " >
                               <div class="col-md-3 right-col2">
                                   <span>方向</span>
                               </div>
                               <div class="col-md-9">
                                    <label for="8" class="mo">东南</label>
                                   <input type="radio" name="fang" id="8" style="display:none;"/>
                                    <label for="9" class="mo">西南</label>
                                   <input type="radio" name="fang" id="9" style="display:none;"/>
                               </div>
                           </div>
                             <div class="row  right-row " >
                               <div class="col-md-3 right-col2">
                                   <span>物业</span>
                               </div>
                               <div class="col-md-9">
                                    <label for="10" class="mo">高层</label>
                                   <input type="radio" name="wu" id="10" style="display:none;"/>
                                    <label for="11" class="mo">超高层</label>
                                   <input type="radio" name="wu" id="11" style="display:none;"/>
                               </div>
                           </div>
                            <div class="row  right-row " >
                               <div class="col-md-3 right-col2">
                                   <span>配套</span>
                               </div>
                               <div class="col-md-9">
                                    <label for="12" class="mo">学校</label>
                                   <input type="radio" name="pei" id="12" style="display:none;"/>
                                    <label for="13" class="mo">超市</label>
                                   <input type="radio" name="pei" id="13"  style="display:none;"/>
                               </div>
                           </div>
                            <div class="row  right-row " >
                               <div class="col-md-3 right-col2">
                                   <span>环境</span>
                               </div>
                               <div class="col-md-9">
                                    <label for="14" class="mo">湖景</label>
                                   <input type="radio" name="wu" id="14"  style="display:none;" />
                                    <label for="15" class="mo">公园</label>
                                   <input type="radio" name="wu" id="15" style="display:none;"/>
                               </div>
                           </div> -->
                          <!--  <div class="row   row-start">
                               <div class="col-md-3 right-col2">
                                   <span>是否启动</span>
                               </div>
                               <div class="col-md-9">
                                    
                                   <input type="radio" name="status" id="16" value="1"  class="no"  checked />
                                   <label for="16" class="yn">是</label>
                                    
                                   <input type="radio" name="status" id="17" value="0" class="no" />
                                   <label for="17" class="yn">否</label>
                               </div>
                           </div> -->
                           <div class="row  row-button " >
                              <label>${data.message }</label>
                                  <input type="button" value="取消" class="btnOne" id="restBtn"/>
                                   <input type="button" value="保存" class="btnTwo" id="sub"/>
                            
                           </div>
                       </form>
                   </div>
                </div>
               

               
            </div>
      


   
   
    <script type="text/javascript" src="static/js/projectLabel.js"></script>
    
</body>
</html>
