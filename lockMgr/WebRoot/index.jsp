<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>0791锁匠</title>
    <link rel="SHORTCUT ICON" href="structure_page/bitbug_favicon.ico" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

<META http-equiv=Content-Type content="text/html; charset=utf-8">
<STYLE type=text/css>BODY {
	FONT-SIZE: 12px; COLOR: #ffffff; FONT-FAMILY: 宋体
}
TD {
	FONT-SIZE: 12px; COLOR: #ffffff; FONT-FAMILY: 宋体
}
a:link,a:visited{
 text-decoration:none;  /*超链接无下划线*/
}
a:hover{
 text-decoration:none;  /*鼠标放上去有下划线*/
}
</STYLE>

<link rel="stylesheet" type="text/css" href="css/default.css" />
		<link id="easyuiTheme" rel="stylesheet" type="text/css"
			href="js/themes/default/easyui.css" />
		<link rel="stylesheet" type="text/css" href="js/themes/icon.css" />
<link rel="stylesheet" href="css/validationEngine.jquery.css" type="text/css"></link>
<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
<script src="js/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
<script src="js/common_fn.js" type="text/javascript"></script>
<script type="text/javascript" src="js/datagrid-detailview.js"></script>
<script type="text/javascript" src="js/login.js"></script>
<script type="text/javascript" src="js/jquery.validationEngine-zh_CN.js"></script>
<script type="text/javascript" src="js/jquery.validationEngine.js"></script>
<style type="text/css">
body{
	background:#ecece2;;
}
.form-signin {
	max-width: 400px;
	width:400px;
	height: auto;
	margin: auto ;
}
.layout{
	max-width: 340px;
	width:340px;
	background-color: #fff;
	padding: 40px 29px 29px;
	border: 1px solid #e5e5e5;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	border-radius: 5px;
	-webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
	-moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
	box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
	margin:40px 0 0 ;
}
.form-signin .form-signin-heading,.form-signin .checkbox {
	margin-bottom: 10px;
}

.form-signin input[type="text"],.form-signin input[type="password"] {
	font-size: 16px;
	height: auto;
	margin-bottom: 0px;
	padding: 7px 9px;
}
.btn{
	display: inline-block;
	padding: 4px 12px;
	margin-bottom: 0;
	font-size: 14px;
	line-height: 20px;
	color: white;
	text-align: center;
	vertical-align: middle;
	cursor: pointer;
	border: 1px solid #ccc;
	border-color: #e6e6e6 #e6e6e6 #bfbfbf;
	border-color: rgba(0,0,0,0.1) rgba(0,0,0,0.1) rgba(0,0,0,0.25);
	border-bottom-color: #b3b3b3;
	-webkit-border-radius: 4px;
	-moz-border-radius: 4px;
	border-radius: 4px;
	background-color: #006dcc;
	background-image: -moz-linear-gradient(top,#08c,#04c);
	background-image: -webkit-gradient(linear,0 0,0 100%,from(#08c),to(#04c));
	background-image: -webkit-linear-gradient(top,#08c,#04c);
	background-image: -o-linear-gradient(top,#08c,#04c);
	background-image: linear-gradient(to bottom,#08c,#04c);
	background-repeat: repeat-x;
	border-color: #04c #04c #002a80;
	border-color: rgba(0,0,0,0.1) rgba(0,0,0,0.1) rgba(0,0,0,0.25);
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#ff0088cc',endColorstr='#ff0044cc',GradientType=0);
	filter: progid:DXImageTransform.Microsoft.gradient(enabled=false);
}
</style>
<META content="MSHTML 6.00.6000.16809" name=GENERATOR>
	
  </head>
  
 <BODY  onkeydown="keyLogin(event);" class="easyui-layout">
<div align="center">
<div style="margin-top:100px;">
	
	<div class="container">
		<form class="form-signin" id="form1">
		<div>
			<img src="images/LOGO-1.png" style="height:50px;"/>
			<img src="images/top.png" style="height:50px;"/>
			</div>
		<div class="layout easyui-layout">
			
			<div style="">
			<table>
			<tr><td>
				<span  class="west" style="text-align:right;width:80px;font-size:1.6em;display:inline-block; color:black;">用户名</span>
				
			
				</td>
				<td>
				<INPUT id=username type="text" style="width:150px;"
                  name="account"  class="validate[required]">
                  </td>
                  </tr>
                  </table>
                 
			</div>
			<div>
			<br>
			<table>
			<tr><td>
				<span class="west" style="text-align:right;display:inline-block;font-size:1.6em;width:80px; color:black;">密  码</span>
			</td>
			<td>
				<input type="password" id="password"  style="width:150px;" class="validate[required]"
				 />
				 </td>
				 </tr>
				 </table>
			</div>
			<div>
			<table>
			<tr><td>
				<span class="west spandisplay" style="display:inline-block;text-align:right;visibility: hidden;width:80px; color:black;font-size:1.0em;">验证码</span>
				  </td>
				<td>
				<INPUT id=validateCode type="text"
                  style="WIDTH: 60px;visibility: hidden;padding:0;" name="validateCode" class="spandisplay"/>
               
                  <SPAN class="spandisplay"
                  style="visibility: hidden;">
                <img src=""  id="img" style="vertical-align: top;padding-top: 4px !important;">
                <a onmouseover="mOver2()" onmouseout="mOut2()"href="javascript:changCode();" ><font id="font2" color="white">看不清,换一张... </font> </a></SPAN>
			</td>
			</tr>
			</table>
			</div>
			<div style="text-align:center;">
				<input class="btn btn-large btn-danger" type="button" id="btn"  value="登录">
				<input class="btn btn-large btn-danger" type="reset" id="cancel" value="重置"/>
			</div>
</div>
		</form>
	</div>
</div>
</div>
</BODY>
</html>
