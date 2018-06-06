<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link href="${pageContext.request.contextPath }/css/auto.css" rel="stylesheet" type="text/css" />
<title>注册界面</title>
</head>
<body>
    <div class="header wrapfix">
        <a href="###" target="_blank"><img src="${pageContext.request.contextPath }/images/toplogo.gif"
            class="logo" alt="首页" /></a>
        <div class="help">
            <a href="###" target="_blank" title="">首页</a> | <a href="###"
                target="_blank" title="帮助中心">帮助中心</a> <span class="tel">客服热线:
                13......</span>
        </div>
    </div>
    <div class="clearfix"></div>
    <!-- 清除浮动 -->

    <div class="wrap">

        <div class="login_main_white">
            <div class="login_main_announce">
                <ol>
                    <b>注册须知：</b>
                    <li>1:在本站注册的会员，必须遵守《互联网电子公告服务管理规定》，不得在本站发表诽谤他人，侵犯他人隐私，侵犯他人知识产权，传播病毒，政治言论，商业机密等信息。</li>
                    <li>2:在所有在本站发表的文章，本站都具有最终编辑权，并且保留用于印刷或向第三方发表的权利，如果你的资料不齐全，我们将有权不作任何通知使用你在本站发布的作品。</li>
                </ol>
            </div>
            <div class="clearfix"></div>
        </div>


        <dl>
            <form name="form2" action="${pageContext.request.contextPath }/RegisterServlet" method="post">

                <dd>登陆帐号：
                <input type="hidden" name="fmdo" value="user"><input
                    type="hidden" name="dopost" value="regok"><input
                    name="username" value="${form.username }" type="text" class="log_input2"
                    onchange="TestUserOk()" />
                <span id='testCanReg'>${form.errors.username }</span>
                </dd>

                <dd>
                    登陆密码： <input name="password" value="${form.password }" type="password" class="log_input2" />
                    <span id='testCanReg'>${form.errors.password }</span>
                </dd>

                <dd>
                    重复密码： <input name="password2" value="${form.password2 }" type="password" class="log_input2" />
                    <span id='testCanReg'>${form.errors.password2 }</span>
                </dd>

                <dd>
                    电子邮箱： <input name="email" value="${form.email }" type="text" class="log_input2" />
                    <span id='testCanReg'>${form.errors.email }</span>
                </dd>

                <dd>
                    生日： 　　<input name="birthday" value="${form.birthday }" type="text" class="sang_Calender" />
                    <span id='testCanReg'>${form.errors.birthday }</span>
                </dd>
                <script type="text/javascript" src="${pageContext.request.contextPath }/js/ShowCalendar.js"></script>

                <dd>
                    您的昵称： <input name="nickname" value="${form.nickname }" type="text" class="log_input2" />
                    <span id='testCanReg'>${form.errors.nickname }</span>
                </dd>

                <dd id="com" style="display: none;">
                    公司名称： <input name="comname" type="text" class="log_input2" />（公司用户填写）
                </dd>

                <dd>
                    验证码： 　<input name="vdcode" type="text" class="log_input2" />
                    <img src="" name='1' id="1" alt="换一张" />
                </dd>

                <dd>
                    <br />
                    <input type="submit" name="Submit2" value="确定注册" class="Btn" />
                    &nbsp;&nbsp; <input type="reset" name="Submit22" value="重 置"
                        class="Btn"> <br />
                    <br />
                </dd>
            </form>
        </dl>

    </div>


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
        </div>
    </div>
</body>
</html>