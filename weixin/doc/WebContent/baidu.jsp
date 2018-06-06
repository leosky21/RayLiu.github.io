<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=o8sbst8vwTB39gYlgfgu1siDF0M9I4RX"></script>
	<script type="text/javascript" src="http://api.map.baidu.com/library/Heatmap/2.0/src/Heatmap_min.js"></script>
</head>
<body>
	<!-- 眉头  -->
	<div class="site-top">
		<!-- 系统logo  -->
		<div class="site-logo">
			<img src="<%=path %>/static/images/logo1.png" />
		</div>
		<!-- 右侧功能按钮  -->
		<div class="site-top-tools">
			
		</div>
	</div> 
	<div class="site-top-line"></div> 
	<div class="content">
		<!-- 操作栏  -->
		<div class="action-bar">
			<span class="actionbar_span">城市：</span>
			<select id="city" class="actionbar_select" onchange="changeplace()">
				<option value="0">连云港</option>
				<option value="1">南京</option>
				<option value="2">上海</option>
				<option value="3">深圳</option>
				<option value="4">北京</option>
			</select>
			<input id="txtarea" class="actionbar_inp1" type="text" size="50" readonly="readonly" />
			<input class="actionbar_btn1" type="button" onclick="getCityLine();" value="获取轮廓线" />
			<span class="actionbar_span">时间：</span>
			<input id="start" class="actionbar_inp1" type="text" value="2018-04-08" size="10" />
			<span class="actionbar_span">~&nbsp;</span>
			<input id="end" class="actionbar_inp1" type="text" value="2018-04-08" size="10" />
			
			<input class="actionbar_btn1" type="button" onclick="eventsMark(-1,0,0);" value="L<< " />
			<input  class="actionbar_btn1" type="button" onclick="eventsMark(1,0,0);" value="L>> " />
			<input  class="actionbar_btn1" type="button" onclick="eventsMark(0,-1,0);" value="R<< " />
			<input  class="actionbar_btn1" type="button" onclick="eventsMark(0,1,0);" value="R>> " />
			<input class="actionbar_btn1" type="button" value="热点事件分析" onclick="eventsMark(0,0,1)" />
			
			&nbsp;&nbsp;&nbsp;&nbsp;
			
			
		</div>
		<!-- 热点事件列表 -->
		<div class="content-table">
			<table class="table-vertical" style="width: 1899px; ">
				<thead>
					<tr>
						<th width="5%">序号</th>
						<th width="24%">热点专题</th>
						<th width="44%">关键词</th>
						<th width="6%">分值</th>
						<th width="15%">开始时间</th>
						<th width="6%">标注颜色</th>
					</tr>
				</thead>
				<tbody id="gridtbody">
					<tr><td>1</td><td/><td/><td/><td/><td/></tr>
					<tr><td>2</td><td/><td/><td/><td/><td/></tr>
					<tr><td>3</td><td/><td/><td/><td/><td/></tr>
					<tr><td>4</td><td/><td/><td/><td/><td/></tr>
				</tbody>
			</table>
		</div>
		<!-- 热点词关系图与热点地图-->
		<div class="content-datapic">
			<!-- 热点词关系图-->
			<div id="echarts1" class="line-container"></div>
			<!-- 左侧：地图-->
			<div id="container" class="map-container" ></div>
		</div>
		<table class="table-vertical"  style="width: 1899px;margin-top:10px; ">
				<thead>
					<tr>
						<th width="20%">发布地址</th>
						<th width="44%">微博内容</th>
						<th width="15%">发布时间</th>
						<th width="15%">微博链接</th>
						
						
					</tr>
				</thead>
				<tbody id="gridtbody1">
					<tr><td>1</td><td/><td/><td/></tr>
					<tr><td>2</td><td/><td/><td/></tr>
					<tr><td>3</td><td/><td/><td/></tr>
					<tr><td>4</td><td/><td/><td/></tr>
					<tr><td>5</td><td/><td/><td/></tr>
				</tbody>
			</table>
	</div>
	<!-- 热点关系图 -->
	<script type="text/javascript">
		/*执行热点分析过程*/
		function handleHotspot(city, startTime, endTime) {
			$.ajax({
				url : "/weixin/selectController/handleHotspot",
				type : "post",
				data : {
					'city' : city ,
					'startTime' : startTime,
					'endTime' : endTime
				},
				error : function(){
					alert("热点分析出错，请查询后台日志分析问题！");
					
				},
				success : function(data) {
					if (data == "paramError"){
						alert("参数错误，请规范输入日期.");
					} else if (data != "success"){
						alert("热点分析失败.");
					}
				}
			});
			sleep(1000);
		}
			
		/* 休眠（秒）*/
		function sleep(numberMillis) { 
			var now = new Date(); 
			var exitTime = now.getTime() + numberMillis; 
			while (true) { 
				now = new Date(); 
				if (now.getTime() > exitTime) 
					return; 
			}
		}
		
		// 词共现关系
		function getWordCoccurrence() {
			var myChart = echarts.init(document.getElementById('echarts1'));
			//获取时间限制条件
			$.ajax({
				url : "/weixin/selectController/getWordCoccurrence",
				type : "post",
				dataType : "json",
				success : function(data) {
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
					alert(data);
				}
			});
		}
		
	</script>
	<!-- 地图 -->
	<script type="text/javascript">
		// 地图显示
		var map = new BMap.Map("container");
		map.addControl(new BMap.NavigationControl());
		map.addControl(new BMap.ScaleControl());
		map.addControl(new BMap.OverviewMapControl());
		map.addControl(new BMap.MapTypeControl());

		var points = [];
		//标记城市轮廓
		$(function() {
			var bdary = new BMap.Boundary();
			var name = $('#city option:selected').text();
			bdary.get(name, function(rs) { //获取行政区域
				map.clearOverlays(); //清除地图覆盖物        
				var poi = rs.boundaries[0];
				var arr = poi.split(";");
				var count = arr.length; //边界点有多少个
				for (var i = 0; i < count; i++) {
					var point = arr[i].split(",");
					points.push(new BMap.Point(point[0], point[1]));
				}
				var ply = new BMap.Polyline(points, {
					strokeWeight : 2,
					strokeColor : "#ff0000"
				});
				map.addOverlay(ply); //添加覆盖物
				map.setViewport(ply.getPath(), 2000); //调整视野      
			});
		});
		if (!isSupportCanvas()) {
			alert('热力图目前只支持有canvas支持的浏览器,您所使用的浏览器不能使用热力图功能~')
		}
		
		//标记子地域轮廓
		function getCityLine(){
			var bdary = new BMap.Boundary();
			var name = $('#city option:selected').text();
			bdary.get(name, function(rs) { //获取行政区域
				//	map.clearOverlays(); //清除地图覆盖物       
				var poi = rs.boundaries[0];

				var arr = poi.split(";");
				var newpoints = [];
				var count = arr.length; //边界点有多少个
				for (var i = 0; i < count; i++) {
					var point = arr[i].split(",");
					newpoints.push(new BMap.Point(point[0], point[1]));
				}
				var ply = new BMap.Polyline(newpoints, {
					strokeWeight : 2,
					strokeColor : "#ff0000"
				});
				map.addOverlay(ply); //添加覆盖物
				map.setViewport(ply.getPath()); //调整视野      
				map.enableScrollWheelZoom(); // 允许滚轮缩放
			});
		}

		/*
		切换地域,绘制地域轮廓 
		 */
		function changeplace() {
			var place = $('#city option:selected').text();
			
			// 创建地址解析器实例 
			var myGeo = new BMap.Geocoder();
			myGeo.getPoint(place, function(point) {
				if (point) {
					
					map.clearOverlays(); //清除地图覆盖物       
					var pointMarker = new BMap.Point(point.lng, point.lat);
					 
					map.centerAndZoom(pointMarker, 11);
					geocodeSearch(pointMarker);
					map.addOverlay(new BMap.Marker(pointMarker));
					var poi = [];
					poi.push(pointMarker);
					 
				} else
					alert("搜索不到结果");
			}, "全国");
		}
		
		/**
		获得地点名称所对应的区域 
		 */
		function geocodeSearch(pt) {
			var myGeo = new BMap.Geocoder();
			myGeo.getLocation(pt, function(rs) {
				var addComp = rs.addressComponents;
				console.log(typeof (addComp));
				console.log(addComp);
				document.getElementById("txtarea").value = addComp.province
						+ ", " + addComp.city + ", " + addComp.district;
			});
		}
		heatmapOverlay = new BMapLib.HeatmapOverlay({
			"radius" : 20
		});
		map.addOverlay(heatmapOverlay);
		
		/**
		 */
		function eventsMarkPoint(pointss, ColorHex) {
			// 创建地址解析器实例 
			if (pointss) {
				var poi = [];
				var colorD = ColorHex;
				map.clearOverlays(); //清除地图覆盖物   
			
				if (document.createElement('canvas').getContext) { // 判断当前浏览器是否支持绘制海量点
					var points = []; // 添加海量点数据
					for (var i = 0; i < pointss.length; i++) {
						points.push(new BMap.Point(pointss[i].lng,
								pointss[i].lat));
					}
					var options = {
						size : BMAP_POINT_SIZE_SMALL,
						shape : BMap_Symbol_SHAPE_POINT,
						color : colorD
					}
					var pointCollection = new BMap.PointCollection(points,
							options); // 初始化PointCollection
					pointCollection.addEventListener('click', function(e) {
					});
					map.addOverlay(pointCollection); // 添加Overlay
				} else {
					alert('请在chrome、safari、IE8+以上浏览器查看本示例');
				}
				map.setViewport(poi); //调整视野      
			}
		}
		
		/**
		事件分析及事件地点标注 
		 */
		function tra(date){
				
    			var y = date.getFullYear();  
   			var m = date.getMonth() + 1;  
   			 m = m < 10 ? '0' + m : m;  
   			 var d = date.getDate();  
    			d = d < 10 ? ('0' + d) : d;  
    			return y + '-' + m + '-' + d;  
			}


function pre(step1,step2){
		//获取开始结束时间			
		var starttime = $("#start").val();
		var endtime = $("#end").val();
		var d1=new Date(starttime);
		var d2=new Date(endtime);
		d1.setDate(d1.getDate()+step1);
		d2.setDate(d2.getDate()+step2);
		var formatDate1=tra(d1);
		var formatDate2=tra(d2);
		//alert(formatDate1+"-"+formatDate2+"");
		//alert(d1.format("yyyy-MM-dd"));
		document.getElementById("start").value=formatDate1;//.pattern("yyyy-MM-dd")
		document.getElementById("end").value=formatDate2;//.pattern("yyyy-MM-dd")
				
		var city = $('#city option:selected').text();
		//alert(city);
		//alert(step);
		//var step=-1;
		$.ajax({
			url : "/weixin/selectController/getSelectPoiCount?",
			type : "post",
			data : {
					'city' : city ,
					'startTime' : formatDate1,
					'endTime' : formatDate2
				},
			success : function(data) {

		//alert("程序运行到此111!!!");
		var jsonObj = eval(data.result); //获得jsonObj对象

		//数据格式转换 
		heatmapOverlay.setDataSet({
			data : jsonObj,
			max : 100
		});	
	
		heatmapOverlay.show();
		},
			error : function(result) {
			alert("程序运行到此000000!!!");
			alert(data);
				}
			});		
				
}
///////////////////////////////////////////////////////////////
		function eventsMark(step1,step2,step3) {
		
			//if(step1!=0||step2!=0)
			//{
				pre(step1,step2);
			//}
			if(step3==0){
				return ;
			}
			var city = $('#city option:selected').text();
			var startTime = $('#start').val().trim();
			var endTime = $('#end').val().trim();
			if (startTime == ""){
				alert("请输入正确的日期，格式：“yyyy-MM-dd”");
				return;
			}
			// 首先执行分析过程
			handleHotspot(city, startTime, endTime);
			
			// 获取关系
			getWordCoccurrence();
			
			// 获取热点话题
			$.ajax({
				url : "/weixin/selectController/getSelectHotEvent",
				type : "post",
				data : {
					'city' : city ,
					'startTime' : startTime,
					'endTime' : endTime
				},
				success : function(data) {

					//得到了热点事件  

					var jsonObj = eval(data.result); //获得jsonObj对象
					var html;
					//红色,绿色,蓝色,紫色,橙色
					var ColorName = new Array('红色', '绿色', '蓝色', '紫色', '橙色');
					var ColorHex = new Array('#FF0000', '#00FF00', '#0000FF',
							'#9932CD', '#FF1CAE');
					
					for (var i = 0; i < jsonObj.length; i++) {
						
						html += '<tr data="'+jsonObj[i].id+'"><td>'
								+ jsonObj[i].id + '</td><td>'
								+ jsonObj[i].topic + '</td><td>'
								+ jsonObj[i].keyword + '</td><td>'
								+ jsonObj[i].score + '</td><td>'
								+ jsonObj[i].begintime + '</td>'
								+ '<td><input type="button" class="bttn_style bttn_bg1" onClick=btn("'+jsonObj[i].hotwid+'","'+ColorHex[i]+'"); value="' + ColorName[i]
								+ '" /></td></tr>';
							
					}
				
					$('#gridtbody').empty();
					$('#gridtbody1').empty();
					$('#gridtbody').append(html);
					
					
					

				},
				error : function(result) {
					alert(data);
				}
			});
		};
		
		//事件所在地 画点 
		function btn(id, ColorHex) {
			getCityLine();
			
			var hotwid = id;
			var color = ColorHex;
			
			$.ajax({
				url : "/weixin/selectController/getSelectHoteventStatusRelate?" + 'hotwid=' + hotwid,
				type : "post",
				success : function(data) {
					var jsonObj = eval(data.result); //获得jsonObj对象
					eventsMarkPoint(jsonObj, color);
					var html;
					for (var i = 0; i < jsonObj.length; i++) {
						html += '<tr data="'+jsonObj[i].place+'"><td>'
								+ jsonObj[i].place + '</td><td>'
								+ jsonObj[i].infortext + '</td><td>'
								+ jsonObj[i].publicTime + '</td><td>'
								+ jsonObj[i].url + ' </td></tr>';
							
					}
				
					$('#gridtbody1').empty();
					
					$('#gridtbody1').append(html);
					
				},
				error : function(result) {
					alert("程序运行到此000000!!!");
					alert(data);
				}
			});
		}
		
		function setGradient() {
			/*格式如下所示:
			{
				0:'rgb(102, 255, 0)',
			 	.5:'rgb(255, 170, 0)',
			 	1:'rgb(255, 0, 0)'
			}*/
			var gradient = {};
			var colors = document.querySelectorAll("input[type='color']");
			colors = [].slice.call(colors, 0);
			colors.forEach(function(ele) {
				gradient[ele.getAttribute("data-key")] = ele.value;
			});
			heatmapOverlay.setOptions({
				"gradient" : gradient
			});
		}
		//判断浏览区是否支持canvas
		function isSupportCanvas() {
			var elem = document.createElement('canvas');
			return !!(elem.getContext && elem.getContext('2d'));
		}
		
		
		
	</script><br>
	
</body>
</html>
