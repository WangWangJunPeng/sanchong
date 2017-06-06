<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link  rel="icon" href="static/images/titleLogo.png"  />
    <title>门店管理后台</title>
    <link rel="stylesheet" type="text/css" href="static/css/reset.css">
    <link rel="stylesheet" type="text/css" href="static/css/commend.css">
    <link rel="stylesheet" type="text/css" href="static/css/shopSureBill.css">
    <link rel="stylesheet" href="static/layui/plugins/layui/css/layui.css" media="all" />
     <!-- <link rel="stylesheet" type="text/css" href="static/lib/laydate/need/laydate.css" /> -->
     <link rel="stylesheet" href="static/layui/css/global.css" media="all"> 
    <!-- <link rel="icon"  sizes="20*18" href="../images/webLogn.png" /> -->
    <script type="text/javascript" src="static/js/jquery-3.1.1.min.js"></script>
    <!-- <script type="text/javascript" src="static/lib/laydate/laydate.js"></script> -->
    <script type="text/javascript" src="static/layui/plugins/layui/layui.js"></script>
    <script type="text/javascript" src="static/js/shopSureBill.js"></script>
   
</head>
<body>
<div class="admin-main">
	<div class="layui-tab layui-tab-card" lay-filter="demo">
	  <ul class="layui-tab-title">
	    <li class="layui-this">中介对账单</li>
	    <li>经纪人数据排行</li>
	    <li>项目信息排行</li>
	  </ul>
	  <div class="layui-form-pane" style="margin-top: 20px;margin-left: 10px;">
		  <form>
			  <input type="hidden" id="hidNum" value="0"/>
			  <div class="layui-inline">
				 <input class="layui-input" placeholder="开始时间" id="start" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm:ss'})">
			  </div>
			  <div class="layui-inline">
				 <input class="layui-input" placeholder="结束时间" id="end" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm:ss'})">
			  </div>
			  <div class="layui-input-inline">
			      <select name="proId" id="projectSelect" lay-ignore style="width:260px;height:38px;padding-left:5px;border:1px solid #e6e6e6;color:#666;">
	                  <option value="">全部案场</option>
	                  
	              </select>
		      </div>
		      <button class="layui-btn" type="button" onclick="selectDate()">搜索</button>
		  </form>
	  </div>
	  <div class="layui-tab-content">
	    <div class="layui-tab-item layui-show">
				<span style="margin-left: 20px; color: #0c95db;" onclick="tableToExcel1('table')">将输出结果导出Excel</span>
		    <fieldset class="layui-elem-field" style="margin-top: 10px;">
				<legend>中介对账单</legend>
				<div class="layui-field-box layui-form">
					<table class="layui-table admin-table" id="shoperBillListInfo">
						
					</table>
				</div>
			</fieldset>
			<div class="admin-table-page">
					<div id="paged1" class="page">
					</div>
				</div>
	    </div>
	    <div class="layui-tab-item">
	    	<span style="margin-left: 20px; color: #0c95db;" onclick="tableToExcel2('table')">将输出结果导出Excel</span>
	    	<fieldset class="layui-elem-field" style="margin-top: 10px;">
				<legend>经纪人数据排行</legend>
				<div class="layui-field-box layui-form">
					<table class="layui-table admin-table" id="mediDateRankingInfo">
						
					</table>
				</div>
			</fieldset>
			<div class="admin-table-page">
					<div id="paged2" class="page">
					</div>
				</div>
	    </div>
	    <div class="layui-tab-item">
	    	<span style="margin-left: 20px; color: #0c95db;" onclick="tableToExcel3('table')">将输出结果导出Excel</span>
	    	<fieldset class="layui-elem-field" style="margin-top: 10px;">
				<legend>项目信息排行</legend>
				<div class="layui-field-box layui-form">
					<table class="layui-table admin-table" id="proDateRankingInfo">
						
					</table>
				</div>
			</fieldset>
			<div class="admin-table-page">
					<div id="paged3" class="page">
					</div>
				</div>
	    </div>
	  </div>
	</div>
</div>
    <div class="sure-pop" style="display:none;">
                <div class="cancel-commission">
                    <h1>确认佣金到款</h1>
                </div>
                <p>请输入确认理由</p>
                <form action="shoper_check_bill_list_enter_or_cancel" method="post">
                	<input name="doSingle" value="enterReceive">
                	<input id="sureHidRecordNo" name="recordNo" type="hidden">
                    <textarea name="desc" id="reasonOne" cols="30" rows="8"></textarea>
                    <div class="btn">
                        <input type="submit" value="确定" class="sub-popOne" />
                        <input type="button" value="关闭" class="close-popOne" />
                    </div>
                </form>     
    </div>
    <div class="cancel-pop" style="display:none;">
                <div class="cancel-commission">
                    <h1>取消佣金结款</h1>
                </div>
                <p>请输入取消原因<span class="error-warning"></span></p>
                <form action="shoper_check_bill_list_enter_or_cancel" method="post">
                	<input name="doSingle" value="cancelReceive">
                	<input id="cancelHidRecordNo" name="recordNo" type="hidden">
                    <textarea name="desc" id="reasonTwo" cols="30" rows="8"></textarea>
                    <div class="btn">
                        <input type="submit" value="确定" class="sub-popTwo" />
                        <input type="button" value="关闭" class="close-popTwo" />
                    </div>
                </form>     
    </div>
</body>
<script type="text/javascript">

	
</script> 
</html>