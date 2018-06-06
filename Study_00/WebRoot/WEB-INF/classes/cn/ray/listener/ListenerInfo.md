#Servlet监听器的分类
> 在Servlet规范中定义了多种类型的监听器，它们用于监听的事件源分别为ServletContext，HttpSession和ServletRequest这三个域对象。

Servlet规范针对这三个对象上的操作，又把多种类型的监听器划分为三种类型：
   
- 监听三个域对象创建和销毁的事件监听器。(监听 域对象的创建与销毁)
- 监听域对象中的属性的增加和删除的事件监听器.(对象中 "属性"删改)
- 监听绑定到HttpSession域中的某个对象的状态的事件监听器。 (监听 session对象的状态)

###监听ServletContext域对象的创建和销毁
> ServletContextListener接口用于监听ServletContext对象的创建和销毁事件。实现了ServletContextListener接口的类都可以对ServletContext对象的创建和销毁进行监听。

- 当ServletContext对象被创建时，激发contextInitialized (ServletContextEvent sce)方法。
- 当ServletContext对象被销毁时，激发contextDestroyed(ServletContextEvent sce)方法。

```

问题：ServletContext域对象何时创建和销毁？ 
    答：创建：服务器启动针对每一个Web应用创建ServletContext。
销毁：服务器关闭前先关闭代表每一个Web应用的ServletContext。
```


```

public class MyServletContextListener implements ServletContextListener {

    // 当ServletContext被创建的时候(什么时候创建ServletContext呢？将web工程发布到web服务器里面去了，只要一启动web服务器，web服务器会针对每一个web应用创建ServletContext)，下面方法执行
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("ServletContext被创建了！！！");
    }

    // 当ServletContext被销毁的时候(停止服务器，服务器就会把针对于每一个web应用的ServletContext摧毁)，下面方法执行
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("ServletContext被销毁了！！！");
    }

}

-- web.xml
<listener>
    <listener-class>cn.itcast.web.listener.MyServletContextListener</listener-class>
</listener>
```
经过这两个步骤，我们就完成了监听器的编写和注册，Web服务器在启动时，就会自动把在web.xml中配置的监听器注册到ServletContext对象上，这样开发好的MyServletContextListener监听器就可以对ServletContext对象进行监听了。 
注意以下三点：

1. 和编写其它事件监听器一样，编写servlet监听器也需要实现一个特定的接口，并针对相应动作覆盖接口中的相应方法。
2. 和其它事件监听器略有不同的是，servlet监听器的注册不是直接注册在事件源上，而是由WEB容器负责注册，开发人员只需在web.xml文件中使用<listener>标签配置好监听器，web容器就会自动把监听器注册到事件源中。
3. 一个web.xml文件中可以配置多个Servlet事件监听器，web服务器按照它们在web.xml文件中的注册顺序来加载和注册这些Serlvet事件监听器。

**这个技术在开发里面用在哪里呢？什么情况下我们才需要监听ServletContext的创建和销毁呢？**

1. 在做实际开发时，有时候希望web应用启动时，就初始化一些资源
2. web应用一启动时，希望启动一些定时器来定时的执行某些任务。只要把启动定时器的代码写到contextInitialized方法里面，这个web应用一启动，定时器就启动了
3. 其实Spring的启动代码就是写在一个ServletContext监听器的contextInitialized方法里面的。Spring是一个框架，我们希望web应用一启动的时候，就把Spring框架启动起来。

###监听HttpSession域对象的创建和销毁
HttpSessionListener接口用于监听HttpSession对象的创建和销毁。

- 创建一个Session时，激发sessionCreated(HttpSessionEvent se)方法。
- 销毁一个Session时，激发sessionDestroyed(HttpSessionEvent se)方法。

```
问题：Session域对象创建和销毁的时机。 

答：
创建：用户每一次访问时，服务器创建session。
销毁：如果用户的session30分钟没有使用，服务器就会销毁session，我们在web.xml里面也可以配置session失效时间。    
```


```
public class MyHttpSessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println(se.getSession() + "被创建了！！！");
        System.out.println("创建好的HttpSession的id是：" + se.getSession().getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("session被销毁了！！！");
    }

}

-- web.xml
<listener>
    <listener-class>cn.itcast.web.listener.MyHttpSessionListener</listener-class>
</listener>
<!-- 配置HttpSession对象的销毁时机 -->
<session-config>
    <!-- 配置HttpSession对象1分钟之后销毁 -->
    <session-timeout>1</session-timeout>
</session-config>
```

> 当我们访问jsp页面时，HttpSession对象就会创建，此时就可以在HttpSessionListener观察到HttpSession对象的创建过程了，我们可以写一个jsp页面观察HttpSession对象创建的过程。 


```
<body>
     一访问JSP页面，HttpSession就创建了，创建好的Session的Id是：${pageContext.session.id}
</body>
```


```
- 现在在客户端把cookie给禁用了，那么再次点击刷新按钮，有没有session被创建？ 
答：有，现在在客户端把cookie给禁了，意味着你再去访问服务器，没有带sessionid号过去，服务器看你没有带sessionid，它认为你又是一个新的来访者，它又会帮你创建session。

- 现在将浏览器窗口关了，能不能看见session被销毁？ 
答：不能，session会驻留在内存里面，TimeOut后没人用了服务器才将其摧毁。
```

> 这个技术在开发里面用在哪里呢？
>       统计当前在线人数。在实际开发里面，只要统计内存里面有多少session，就能知道当前有多少在线人数。为了统计当前在线人数，这时可以写一个这样的监听器，只要有一个session被创建就让一个变量count+1，session被销毁就让变量count-1，输出count这个值，就能知道当前有多少在线人数.
> 注:**统计出来的当前在线人数只是一个近似值。**

###监听ServletRequest域对象的创建和销毁
ServletRequestListener接口用于监听ServletRequest对象的创建和销毁。

- Request对象被创建时，监听器的requestInitialized(ServletRequestEvent sre)方法将会被调用。
- Request对象被销毁时，监听器的requestDestroyed(ServletRequestEvent sre)方法将会被调用。


```
问题：ServletRequest域对象创建和销毁时机。 
答：

创建：用户每一次访问都会创建request对象。
销毁：当前访问结束，request对象就会销毁。

```


```
这个技术在开发里面用在哪里呢？ 
答：这个监听器可以用来做网站性能统计，针对每一个请求，都会有一个request对象创建。
```

```
public class MyServletRequestListener implements ServletRequestListener {

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        System.out.println("request被销毁！！！");
        count++;
        //输出count的值，就可以统计网站一天的点击量。
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        System.out.println("request被创建！！！");
        sre.getServletRequest().getRemoteAddr();
        /*可以知道当前这个请求是由哪个IP发出来的，在后台可以通过这个监听器监听到哪些IP在给你发请求，这样做的目的是为了防止坏人，有些坏人恶意点击，写机器人点击，在后台写这样的一个监听器可以监听到某个时间段有某个IP重复点击，如果发生这种情况，就说明这个人是坏人，就可以屏蔽其IP。*/
    }

}

<listener>
    <listener-class>cn.itcast.web.listener.MyServletRequestListener</listener-class>
</listener>
```

##监听域对象中属性的变更的监听器
Servlet规范定义了监听ServletContext, HttpSession, HttpServletRequest这三个对象中的属性变更信息事件的监听器。 
	分别是:
		- ServletContextAttributeListener，
		- HttpSessionAttributeListener
		- ServletRequestAttributeListener

>这三个接口中都定义了三个方法来处理被监听对象中的属性的增加，删除和替换的事件，同一个事件在这三个接口中对应的方法名称完全相同，只是接受的参数类型不同。

####attributeAdded方法
当向被监听对象中增加一个属性时，web容器就调用事件监听器的attributeAdded方法进行响应，这个方法接收一个事件类型的参数，监听器可以通过这个参数来获得正在增加属性的域对象和被保存到域中的属性对象。 
	```
	public void attributeAdded(ServletContextAttributeEvent scae)
	public void attributeReplaced(HttpSessionBindingEvent hsbe)
	public void attributeRmoved(ServletRequestAttributeEvent srae)
	```

####attributeRemoved方法
当删除被监听对象中的一个属性时，web容器调用事件监听器的attributeRemoved方法进行响应。 

```
public void attributeRemoved(ServletContextAttributeEvent scae)
public void attributeRemoved (HttpSessionBindingEvent hsbe)
public void attributeRemoved (ServletRequestAttributeEvent srae)
```
####attributeReplaced方法
当监听器的域对象中的某个属性被替换时，web容器调用事件监听器的attributeReplaced方法进行响应

```
public void attributeReplaced(ServletContextAttributeEvent scae)
public void attributeReplaced (HttpSessionBindingEvent hsbe)
public void attributeReplaced (ServletRequestAttributeEvent srae)
```

> HttpSessionAttributeListener监听器可以用来做显示当前有多少登录用户，每一个用户登录，都往session里面存一个user属性，到时在做开发时，可以写一个监听器来监听session，session里面只要加了user属性，即一个用户登录进来了，到时可以在后台显示当前这个网站到底有多少用户登录进来了。

###感知Session绑定的事件监听器
- 保存在Session域中的对象可以有多种状态：绑定(session.setAttribute(“bean”,Object))到Session中；从Session域中解除(session.removeAttribute(“bean”))绑定；随Session对象持久化到一个存储设备中(钝化)；随Session对象从一个存储设备中恢复(活化)。 
- Servlet规范中定义了两个特殊的监听器接口”HttpSessionBindingListener和HttpSessionActivationListener”来帮助JavaBean对象了解自己在Session域中的这些状态，实现这两个接口的类不需要在web.xml文件中进行注册。

实现了HttpSessionBindingListener接口的JavaBean对象可以感知自己被绑定到Session中和从Session中删除的事件。

- 当对象被绑定到HttpSession对象中时，web服务器调用该对象的void valueBound(HttpSessionBindingEvent event)方法。
- 当对象从HttpSession对象中解除绑定时，web服务器调用该对象的void valueUnbound(HttpSessionBindingEvent event)方法。


```
// 注意：现在User既是监听器又是事件源，即监听器监听自己，所以不需要注册到事件源上
public class User implements HttpSessionBindingListener {

    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        System.out.println("user对象存到session中了！！！");
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        System.out.println("user对象从session解除绑定了！！！");
    }

}
```


```
我们知道当对象被绑定到HttpSession对象中时，web服务器调用该对象的void valueBound(HttpSessionBindingEvent event)方法，所以session类的源代码可能类似于：

class Session {
    setAttribute(String key, Object value) {
        if(value instanceof HttpSessionBindingListener) {
            HttpSessionBindingListener listener = (HttpSessionBindingListener)value;
            value.valueBound();
        }
        set(key, value);
    }
}
```

###HttpSessionActivationListener接口
实现了HttpSessionActivationListener接口的JavaBean对象可以感知自己被活化(反序列化)和钝化(序列化)的事件。

- 当绑定到HttpSession对象中的javabean对象将要随HttpSession对象被钝化(序列化)之前，web服务器调用该javabean对象的void sessionWillPassivate(HttpSessionEvent event)方法。 
这样javabean对象就可以知道自己将要和HttpSession对象一起被序列化(钝化)到硬盘中。
- 当绑定到HttpSession对象中的javabean对象将要随HttpSession对象被活化(反序列化)之后，web服务器调用该javabean对象的void sessionDidActive(HttpSessionEvent event)方法。 
这样javabean对象就可以知道自己将要和HttpSession对象一起被反序列化(活化)回到内存中。

