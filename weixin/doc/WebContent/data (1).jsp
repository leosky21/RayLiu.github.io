<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>热点事件分析</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link type="text/css" rel="stylesheet" href="<%=path %>/static/css/style.css" />
	<script type="text/javascript" src="<%=path %>/static/js/echarts-all.js"></script>
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.6.3.min.js"></script>
</head>
<body>

	<!--上部分：标题、地域下拉框   获取轮廓线  -->
	<br />
	<br />
	<!-- 右侧：热点事件-->
	<div>
		<div>
			<input id="eventsMark" type="button" value="热点事件分析" onclick="getWordCoccurrence()" />
		</div>
		<br />
		<div
			style="width: 800px; height: 600px; border: 1px solid gray; float: left;"
			id="echarts1"></div>

		<script type="text/javascript">
			var newnodes = [];
			var newlikes = [];
			var myChart = echarts.init(document.getElementById('echarts1'));
		
			function getWordCoccurrence1() {
				//获取时间限制条件
				$.ajax({
							url : "/weixin/selectController/getWordCoccurrence",
							type : "post",
							success : function(data) {

								// alert("程序运行到此111!!!");
								var jsonnode = eval(data.node); ///获得jsonObj对象
								var jsonlink = eval(data.link); //获得jsonObj对象
								//数据格式转换 
								option = {
									title : {
										text : '词共现关系',
										subtext : '',
										x : 'right',
										y : 'bottom'
									},
									tooltip : {
										trigger : 'item',
										formatter : '{a} : {b}'
									},
									toolbox : {
										show : true,
										feature : {
											restore : {
												show : true
											},
											magicType : {
												show : true,
												type : [ 'force', 'chord' ]
											},
											saveAsImage : {
												show : true
											}
										}
									},
									legend : {
										x : 'left',
										data : [ '家人', '朋友' ]
									},
									series : [ {
										type : 'force',
										name : "人物关系",
										ribbonType : false,
										categories : [ {
											name : '共现'
										} ],
										itemStyle : {
											normal : {
												label : {
													show : true,
													textStyle : {
														color : '#333'
													}
												},
												nodeStyle : {
													brushType : 'both',
													borderColor : 'rgba(255,215,0,0.4)',
													borderWidth : 1
												},
												linkStyle : {
													type : 'line'
												}
											},
											emphasis : {
												label : {
													show : false
												// textStyle: null      // 默认使用全局文本样式，详见TEXTSTYLE
												},
												nodeStyle : {
												//r: 30
												},
												linkStyle : {}
											}
										},
										useWorker : false,
										minRadius : 15,
										maxRadius : 25,
										gravity : 1.1,
										scaling : 1.1,
										roam : 'move',
										nodes : jsonnode,
										links : jsonlink
									} ]
								};
								myChart.setOption(option);
							},
							error : function(result) {
								alert("程序运行到此000000!!!");
								alert(data);
							}
						});
			}
			
			function getWordCoccurrence() {
				//获取时间限制条件
				$.ajax({
							url : "/weixin/selectController/getWordCoccurrence",
							type : "post",
							success : function(data) {

								// alert("程序运行到此111!!!");
								var jsonnode = eval(data.node); ///获得jsonObj对象
								var jsonlink = eval(data.link); //获得jsonObj对象
								option = {
									    title : {
									        text: '共现关系',
									        subtext: '',
									        x:'right',
									        y:'bottom'
									    },
									    tooltip : {
									        trigger: 'item',
									        formatter: '{a} : {b}'
									    },
									    toolbox: {
									        show : true,
									        feature : {
									            restore : {show: true},
									            magicType: {show: true, type: ['force', 'chord']},
									            saveAsImage : {show: true}
									        }
									    },
									    legend: {        x: 'left',
									        data:['共现']},
									    series : [
									        {
									            type:'force',
									            name : "共现关系",
									            ribbonType: false,
									            categories : [
									                          {
									                              name: '共现'
									                          }
									            ],
									            itemStyle: {
									                normal: {
									                    label: {
									                        show: true,
									                        textStyle: {
									                            color: '#333'
									                        }
									                    },
									                    nodeStyle : {
									                        brushType : 'both',
									                        borderColor : 'rgba(255,215,0,0.4)',
									                        borderWidth : 1
									                    }
									                },
									                emphasis: {
									                    label: {
									                        show: false
									                        // textStyle: null      // 默认使用全局文本样式，详见TEXTSTYLE
									                    },
									                    nodeStyle : {
									                        //r: 30
									                    },
									                    linkStyle : {}
									                }
									            },
									            minRadius : 15,
									            maxRadius : 25,
									            gravity: 1.1,
									            scaling: 1.2,
									            draggable: false,
									            linkSymbol: 'arrow',
									            steps: 10,
									            coolDown: 0.9,
									            //preventOverlap: true,
									            nodes:jsonnode,
									            links : jsonlink
									            	
									        }
									    ]
									};
									myChart.setOption(option);

									                    
							},
							error : function(result) {
								alert("程序运行到此000000!!!");
								alert(data);
							}
						});			                    
			}
		</script>
	</div>
</body>
</html>
