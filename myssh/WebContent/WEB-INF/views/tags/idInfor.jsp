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
    <link rel="stylesheet" type="text/css" href="static/css/idInfor.css" />
   

    <link  rel="icon" href="http://root-1252955170.cossh.myqcloud.com/static/images/titleLogo.png"  />

    
</head>
<body>
  
<%@ include file="publicLabelHead.jsp" %>
            <div id="main-content" class="white" >
                <div class="row all-row">
                   <div class="col-md-8">
                    <div class="row profile-head" >
                        <p><span>*</span><a href="">首页</a>><a href="">案场信息维护</a>><a href="">标签选择</a>><a href="">客户标签</a>><a href="">身份信息</a></p>
                       <a class="aa">新建标签列表</a>
                        <input name="superTagType" value="${superTagType }" id="superTagType" style="display:none;"/>
                    </div>
                    <div class="row profile-body ">
                        <div class="col-md-12"  id="myUseProduce">
                            <!-- <div class="row">
                               <div class="col-md-2 col4"><span>规模</span></div>
                               <div class="col-md-9 col8">
                                    <span>超大盘</span>
                                    <span>大盘</span>
                                    <span>中盘</span>
                                    <span>小盘</span>
                                   
                               </div>
                               <div class="col-md-1 right-edit"><img src="static/images/editer.png" alt=""  class="editPic" /></div>
                            </div> -->
                            
                        </div>
                      
                    </div>
                    <div class="row row-bottomOne">
                        <p>未被启用标签</p>
                    </div>
                    <div class="row ko">
                        <div class="col-md-12" id="myUnUseProduce">
                            <!-- <div class="row">
                               <div class="col-md-2 col4"><span>规模</span></div>
                               <div class="col-md-9 col8">
                                    <span>超大盘</span>
                                    <span>大盘</span>
                                    <span>中盘</span>
                                    <span>小盘</span>
                                   
                               </div>
                               <div class="col-md-1 right-edit"><img src="static/images/editer.png" alt=""  class="editPic" /></div>
                            </div> -->
                            
                        </div>
                    </div>
                   </div>
                   <div class="col-md-4 right-col4 addList" style="display:none;position:fixed;top:30px;right:-100px; overflow:scroll;height:1200px;border-left:2px solid #e4e5e7;">
                       <div class="row right-list">
                           <p>标签列表</p>
                       </div>
                      <form action="" id="produceForm">
                           <div class="row  right-row " >
                               <div class="col-md-3 right-col2">
                                   <span><b style="color:#ff6161;">*</b>标签类目名称</span>
                               </div>
                               <div class="col-md-9">
                                   
                                   <input type="text" name="newTagTypeName"  class="addOne" id="newTagTypeName"/>
                                   <input type="hidden" name="newTagTypeId"  id="newTagTypeId" >
                                   <input type="hidden"  id="newOriginalTagType"  name="newOriginalTagType" value="1"/>
                               </div>
                           </div>
                             
                           
                        
                           <div class="row  right-row " >
                               <div class="col-md-3 right-col2">
                                   <span>启动</span>
                               </div>
                               <div class="col-md-9">
                                    
                                   <input type="radio" name="newTagTypeStatus"  id="16"  class="no"  value="1" />
                                   <label for="" class="yn">是</label>
                                    
                                   <input type="radio" name="newTagTypeStatus"  id="17" class="no" value="0"/>
                                   <label for="" class="yn">否</label>
                               </div>
                           </div>
                            <div class="row  right-row " >
                               <div class="col-md-3 right-col2">
                                   <span>多选</span>
                               </div>
                               <div class="col-md-9">
                                    
                                   <input type="radio" name="newIsMultiple"  id="18"   class="no"  value="1" />
                                   <label for="" class="yn">是</label>
                                    
                                   <input type="radio" name="newIsMultiple" id="19" class="no" value="0"/>
                                   <label for="" class="yn">否</label>
                               </div>
                           </div>
                              <div class="row  right-row " >
                               <div class="col-md-3 right-col2">
                                   <span><b style="color:#ff6161;">*</b>标签列表</span>
                               </div>
                               <div class="col-md-9" id="addPut">
                                   
                                   <input type="button"   class="addButton" value="添加标签" />
                                   
                               </div>
                           </div>


                            

                           <div class="row  row-button " >
                              
                                  <input type="button" value="取消" class="btnOne" />
                                   <input type="button" value="保存" class="btnTwo" id="addSubBtn" />
                            
                           </div>
                       </form>
                   </div>
                    <div class="col-md-4 right-col4 editList" style="display:none;position:fixed;top:30px;right:-100px; overflow:scroll;height:1200px;border-left:2px solid #e4e5e7;">
                       <div class="row right-list">
                           <p>编辑标签</p>
                       </div>
                       <form action="add_tagtype_and_tags" id="myTForm">
                             <div class="row  right-row " >
                               <div class="col-md-3 right-col2">
                                   <span><b style="color:#ff6161;">*</b>标签类目名称</span>
                               </div>
                               <div class="col-md-9">
                                   
                                   <input type="text" name="tagTypeName"  class="addOne" id="tagTypeName" readonly/>
                                   <input type="hidden" name="tagTypeId"  id="tagTypeId" >
                                   <input type="hidden"  id="originalTagType"  name="originalTagType" value="0"/>
                               </div>
                           </div>
                             
                           
                        
                           <div class="row  right-row " >
                               <div class="col-md-3 right-col2">
                                   <span>启动</span>
                               </div>
                               <div class="col-md-9">
                                    
                                   <input type="radio" name="tagTypeStatus"  id="qidong"  class="no"  value="1" />
                                   <label for="" class="yn">是</label>
                                    
                                   <input type="radio" name="tagTypeStatus" id="noqidong" id="17" class="no" value="0"/>
                                   <label for="" class="yn">否</label>
                               </div>
                           </div>
                            <div class="row  right-row " >
                               <div class="col-md-3 right-col2">
                                   <span>多选</span>
                               </div>
                               <div class="col-md-9">
                                    
                                   <input type="radio" name="isMultiple"  id="duoxuan"  class="no"  value="1" />
                                   <label for="" class="yn">是</label>
                                    
                                   <input type="radio" name="isMultiple" id="noduoxuan" class="no" value="0"/>
                                   <label for="" class="yn">否</label>
                               </div>
                           </div>
                              <div class="row  right-row " >
                               <div class="col-md-3 right-col2">
                                   <span><b style="color:#ff6161;">*</b>标签列表</span>
                               </div>
                               <div class="col-md-9" id="editPut">
                                   <div class="labelEvery"></div>
                                   <input type="button"  id="beforeBtn" class="addButtonTwo" value="添加标签" />
                                   
                               </div>
                           </div> 
 

                            

                           <div class="row  row-button " >
                              
                                  <input type="button" value="取消" class="btnOne" id=""/>
                                   <input type="button" value="保存" class="btnTwo" id="tagSubmit" />
                            
                           </div>
                       </form>
                   </div>
                </div>
               

                <!-- Modal -->
            </div><!-- /Main Content  @7 -->
      

   

  
    <script type="text/javascript" src="static/js/idInfor.js"></script>
    
</body>
</html>
    