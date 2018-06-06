<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录界面</title>
<link href="${pageContext.request.contextPath }/css/auto.css" rel="stylesheet" type="text/css" />
</head>
<body>
    <div class="header wrapfix">
        <a href="###" target="_blank"><img src="${pageContext.request.contextPath }/images/toplogo.gif"
            class="logo" alt="首页" /></a>
        <div class="help">
            <a href="###" target="_blank" title="首页">首页</a> | <a href="###"
                target="_blank" title="帮助中心">帮助中心</a> <span class="tel">客服热线:
                13......</span>
        </div>
    </div>
    <div class="clearfix"></div>
    <!-- 清除浮动 -->
    <!-- wrap -->
    <div class="wrap">
        <!-- Login -->
        <div class="Login">
            <div class="Login_L"></div>
            <div class="Login_R">
                <div class="login_zc">


                    <form name='form1' method='POST' action='${pageContext.request.contextPath }/LoginServlet'>
                        <table width="100%" border="0" cellpadding="2" cellspacing="2">
                            <tr>
                                <td width="50" align="right">用户名：</td>
                                <td height="24" colspan="2"><input type="hidden"
                                    name="fmdo" value="login"> <input type="hidden"
                                    name="dopost" value="login"> <input type="hidden"
                                    name="gourl" value="<?php if(!empty($gourl)) echo $gourl;?>">
                                    <input name="username" type="text" class="log_input" /></td>
                            </tr>
                            <tr>
                                <td align="right">密 码：</td>
                                <td height="24" colspan="2"><input name="password"
                                    type="password" class="log_input" /></td>
                            </tr>
                            <tr>
                                <td align="right">验证码：</td>
                                <td height="24" colspan="2"><input name="vdcode"
                                    type="text" class="log_input" /> <img
                                    src="../include/vdimgck.php" name='img1' id="img1" alt="换一张"
                                    onClick="document.getElementById('img1').src='/include/vdimgck.php?rnd=' + Math.random()"
                                    style="cursor: hand" /></td>
                            </tr>
                            <tr>
                                <td height="45">&nbsp;</td>
                                <td width="106" height="45" valign="middle"><input
                                    type="submit" name="Submit" value="登 录" class="Btn" /></td>
                                <td width="74" height="48" valign="middle"><a
                                    href="index_do.php?fmdo=user&dopost=regnew" class="Blue">免费注册</a></td>
                            </tr>

                        </table>

                    </form>
                </div>
            </div>
        </div>
        <!--End: Login -->
        <!-- server_box会员服务 -->
        <div class="server_box">
            <h3>虾米婚嫁网注册会员享受多重服务</h3>
            <div class="server_con">
                <div class="PPbox1">
                    <img src="images/ico_01.gif" alt="图" align="absmiddle" class="Img" />
                    <p>
                        <b>超酷个人主页</b><br /> 免费注册获得博客，相册等强大的个人门户服务。
                    </p>
                </div>

                <div class="PPbox1">
                    <img src="images/ico_02.gif" alt="图" align="absmiddle" class="Img" />
                    <p>
                        <b>城市消费资讯</b><br /> 及时订阅和获取本地最丰富最及时的商家促销资讯。
                    </p>
                </div>

                <div class="PPbox1">
                    <img src="images/ico_03.gif" alt="图" align="absmiddle" class="Img" />
                    <p>
                        <b>商家消费点评</b><br /> 点评商家服务，分享消费体验，揭发不良商家。
                    </p>
                </div>

                <div class="PPbox1">
                    <img src="${pageContext.request.contextPath }/images/ico_04.gif" alt="图" align="absmiddle" class="Img" />
                    <p>
                        <b>消费互动社区</b><br /> 消费分享超人气社区，免费参加吃喝玩乐购活动。
                    </p>
                </div>
                <div class="clearfix"></div>
                <!-- 清除浮动 -->
                <div class="server_but">
                    <a href="index_do.php?fmdo=user&dopost=regnew" title="免费注册"><img
                        src="images/logbtn.gif" alt="免费注册" align="absmiddle" /></a>
                </div>
            </div>
        </div>
        <!--End: server_box会员服务 -->
        <div class="clearfix"></div>

        <div align="center" style="padding-bottom: 30px;">
            <form name='form2' action="index_do.php">
                取回密码 请输入您注册的Email： <input type="hidden" name="fmdo" value="login"><input
                    type="hidden" name="dopost" value="getpwd"><input
                    name="email" type="text" class="log_input2" /> 验证码：<input
                    name="vdcode" type="text" class="log_input2" /> <img
                    src="../include/vdimgck.php" name='img2' id="img2" alt="换一张"
                    onClick="document.getElementById('img2').src='/include/vdimgck.php?rnd=' + Math.random()"
                    style="cursor: hand" /> 
                    <input type="submit" name="Submit2"
                    value="取回密码" class="login_button" />
            </form>
        </div>


    </div>
    <!--End: wrap -->


    <div class="clearfix"></div>
    <!-- 清除浮动 -->
    <div class="footet_box">
        <div class="footet_dh">
            <a href="/abouts/index.html">网站简介</a> - <a href="/abouts/team.html">管理团队</a>
            - <a href="/abouts/services.html">产品与服务</a> - <a
                href="/abouts/jobs.html">招贤纳士</a> - <a href="/abouts/marketing.html">网络营销</a>
            - <a href="/abouts/contact.html">联系我们</a>
        </div>
        <div class="footet_bj">
            <?php echo $cfg_powerby?>
        </div>
    </div>
</body>
</html>