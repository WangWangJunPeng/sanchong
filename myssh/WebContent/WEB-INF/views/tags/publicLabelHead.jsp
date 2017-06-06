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
 	
      
    <link href="static/css/archon.css" rel="stylesheet">
    <link href="static/css/responsive.css" rel="stylesheet">
    <link href="static/css/timeline.css" rel="stylesheet">
   <link rel="stylesheet" type="text/css" href="static/css/publicHead.css" />
   

    <link  rel="icon" href="http://root-1252955170.cossh.myqcloud.com/static/images/titleLogo.png"  />

    
</head>
<body>
    <div class="frame">
        <div class="sidebar">
            <div class="wrapper">

                <!-- Replace the src of the image with your logo -->
                <a href="###" class="logo"><img src="static/images/logoer.png" alt="" /></a>
                <ul class="nav  nav-list">
                    <li ><p class="title">案场助理个人中心</p></li>
                    <li><a href="to_pro_index" class="noDrop"><span>首页</span></a></li>
                    <li>
                        <a class="dropdown" href="#"><span>案场信息维护</span><i class="icon-chevron-down right-icon" style="margin-right:23px;"></i></a>
                        <ul>
                            <li><a href="###"><i class="icon icon-table"></i>基本信息</a></li>
                            <li><a href="###"><i class="icon-picture"></i>效果图</a></li>
                            <li><a href="###"><i class="icon-inbox"></i>户型图</a></li>
                            <li><a href="###"><i class="icon-credit-card"></i>银行账号</a></li>
                            <li><a href="###"><i class="icon-edit"></i>带看业务定义</a></li>
                            <li><a href="###"><i class="icon-briefcase" ></i>预售证管理</a></li>
                            <li><a href="###"><i class="icon-check" ></i>认购规则</a></li>
                            <li>
                                <a href="###" class="dropdown " id="dropTwo"><i class="icon-tags" ></i>标签选择<i class="icon-chevron-down right-icon"></i></a>
                                <ul style="display:none;"  class="yy">
                                    <li class="li"><a href="to_project_all_tag_page">项目标签</a></li>
                                    <li class="li"><a href="to_product_all_tag_page">产品标签</a></li>
                                    <li class="li"><a href="###" class="dropdown " id="dropThree">客户标签<i class="icon-chevron-down right-icon" style="margin-right:23px;"></i></a>
                                        
                                    </li>
                                        <ul class="kk" style="display:none;">
                                            <li class="litt"><a href="to_customer_info_tag_page" class="tt">身份信息</a></li>
                                            <li class="litt"><a href="to_customer_desired_info_tag_page" class="tt">客户需求</a></li>
                                             <li class="litt"><a href="to_customer_need_info_tag_page" class="tt">客户意向</a></li>
                                        </ul>
                                </ul>
                            </li>
                        </ul>   
                    </li>
                    <li><a href="###" class="noDrop"><span>价格优惠条款</span></a></li>

                    <!-- Sidebar header @add class nav-header for sidebar header -->
                    <!-- <li class="nav-header">Pages</li> -->
                    <li><a href="###" class="noDrop"><span>房源管理</span></a></li>
                    <li><a href="###" class="noDrop"><span>上下架管理</span></a></li>
                    <li><a href="###" class="noDrop"><span>客户管理</span></a></li>
                    <li> <!-- Example for second level menu -->
                        <a  href="#" class="noDrop"><span>成交业务</span></a>
                         
                    </li>
                    <li>
                        <a  href="#" class="noDrop"><span>对账单</span></a>
                        
                    </li>

                  
                   
                </ul>

            </div><!-- /Wrapper -->
        </div><!-- /Sidebar -->

        <!-- Main content starts here-->
        <div class="content">
            <div class="navbar">
                <a href="#" onclick="return false;" class="btn pull-left toggle-sidebar "><i class="icon-list"></i></a>
               
                <ul class="nav navbar-nav user-menu pull-right">
                   
                 <!--   <li class="dropdown hidden-xs"> 
                        <a class="dropdown-toggle" data-toggle="dropdown"><i class="icon-envelope-alt"></i></a>
                        <ul class="dropdown-menu right inbox">
                            <li class="dropdown-menu-title">
                                信息 <span>25</span>
                            </li>
                            <li>
                                <img src="static/images/theme/avatarTwo.png" alt="" class="avatar">
                                <div class="message">
                                    <span class="username">汪俊鹏</span> 
                                    <span class="mini-details">(6) <i class="icon-paper-clip"></i></span>
                                    <span class="time pull-right"> 06:58 PM</span>
                                    <p>汪汪汪</p>
                                </div>
                            </li>
                            <li>
                                <img src="static/images/theme/avatarFive.png" alt="" class="avatar">
                                <div class="message">
                                    <span class="username">陈诗雨</span> 
                                    <span class="mini-details">(2) <i class="icon-paper-clip"></i></span>
                                    <span class="time pull-right"> 09:58 AM</span>
                                    <p>鱼鱼鱼</p>
                                </div>
                            </li>
                            <li>
                                <img src="static/images/theme/avatarSix.png" alt="" class="avatar">
                                <div class="message">
                                    <span class="username">郭志成</span> 
                                    <span class="mini-details">(6) <i class="icon-paper-clip"></i></span>
                                    <span class="time pull-right">Yesterday</span>
                                    <p>贱贱贱</p>
                                </div>
                            </li>
                           
                            <li class="dropdown-menu-footer">
                                <a href="#">更多消息</a>
                            </li>
                        </ul>
                    </li>
                 <li class="dropdown hidden-xs">
                        <a class="dropdown-toggle" data-toggle="dropdown"><i class="icon-bell"></i></a>
                        <ul class="dropdown-menu right notifications">
                            <li class="dropdown-menu-title">
                               提醒
                            </li>
                            <li>
                                <i class="icon-cog avatar text-success"></i>
                                <div class="message">
                                    <span class="username text-success">New settings activated</span> 
                                    <span class="time pull-right"> 06:58 PM</span>
                                </div>
                            </li>
                            <li>
                                <i class="icon-shopping-cart avatar text-danger"></i>
                                <div class="message">
                                    <span class="username text-danger">You have 2 returns</span> 
                                    <span class="time pull-right"> 04:29 PM</span>
                                </div>
                            </li>
                            <li>
                                <i class="icon-user avatar text-success"></i>
                                <div class="message">
                                    <span class="username text-success">New User registered</span> 
                                    <span class="time pull-right"> Yesterday</span>
                                </div>
                            </li>
                            <li>
                                <i class="icon-comment avatar text-info"></i>
                                <div class="message">
                                    <span class="username text-info">New Comment received</span> 
                                    <span class="time pull-right"> Yesterday</span>
                                </div>
                            </li>
                            <li>
                                <i class="icon-cog avatar text-warning"></i>
                                <div class="message">
                                    <span class="username text-warning">User deleted</span> 
                                    <span class="time pull-right"> 2 days ago</span>
                                </div>
                            </li>
                            <li>
                                <i class="icon-dollar avatar"></i>
                                <div class="message">
                                    <span class="username">Earned 200 points</span> 
                                    <span class="time pull-right">3 days ago</span>
                                </div>
                            </li>
                            <li>
                                <i class="icon-hdd avatar text-danger"></i>
                                <div class="message">
                                    <span class="username text-danger">Memory size exceeded </span> 
                                    <span class="time pull-right"> 1 week ago</span>
                                </div>
                            </li>

                            <li class="dropdown-menu-footer">
                                <a href="#">View All Notifications</a>
                            </li>
                        </ul>
                    </li>/ dropdown -->

                    <li class="dropdown user-name">
                        <a class="dropdown-toggle" data-toggle="dropdown"><img src="static/images/theme/avatarSeven.png" class="user-avatar" alt="" />李永强</a>
                            <ul class="dropdown-menu right inbox user">
                                <li class="user-avatar">
                                    <img src="static/images/theme/avatarSeven.png" class="user-avatar" alt="" />
                                    三重
                                </li>
                            <li>

                                <i class="icon-user avatar"></i>
                                <div class="message">
                                   <a href="#">修改密码</a>
                                </div>
                            </li>
                           
                            <li><a href="#">Logout</a></li>
                        </ul>
                    </li>               
                </ul>

            </div>

          
        </div>

       
    </div> 

 
   <script src="static/js/jquery-3.1.1.min.js"></script>
   	<script src="static/js/layer.js"></script>
    <script src="static/js/jquery-ui-1.10.3.custom.min.js"></script>
    <script src="static/js/jquery.ui.touch-punch.min.js"></script>
    <script src="static/js/bootstrap.min.js"></script>
    <script src="static/js/bootstrap-select.js"></script>
    <script src="static/js/bootstrap-switch.js"></script>
   
    <script src="static/js/bootstrap-typeahead.js"></script>
    <script src="static/js/application.js"></script>
   
   <script src="static/js/flatui-checkbox.js"></script>
    <script src="static/js/flatui-radio.js"></script>
    <script src="static/js/jquery.tagsinput.js"></script>
    <script src="static/js/jquery.placeholder.js"></script> 
   
   <script src="static/js/moment.min.js"></script>
    <script src="static/js/jquery.dataTables.min.js"></script>
    <script src="static/js/jquery.sortable.js"></script>
    <script type="text/javascript" src="static/js/jquery.gritter.js"></script>


   
    <script src="static/js/archon.js"></script>
   <script >
  
     	$(document).ready(function(){
     		
     			$(".content").append($("#main-content"));
     		
     		
     	})
     
     </script>
  
    
</body>
</html>
