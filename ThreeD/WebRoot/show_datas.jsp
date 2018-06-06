<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
<title>智能探识感知大数据平台</title>
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<link href="css/style_index.css" rel="stylesheet" type="text/css" />
<link href="css/goal-thermometer.css" rel="stylesheet" type="text/css" />
<link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
<link href="css/button.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="js/commonss.min.js"></script>
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="js/Chart.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=dofjhukTDUtiDrEa0Fe0VBHEmAidksZz"></script>
<script type="text/javascript" src="http://developer.baidu.com/map/jsdemo/demo/convertor.js"></script>
<script type="text/javascript" src="js/map.js" ></script>
	<style>
		canvas{
			position: absolute;
			z-index: 50;
			margin: 10px auto;
			}
		*{
			padding: 0;
			margin: 0;
		}
		body{
			margin-top: -20px;
		}
        #container{height:90%} 
	</style>

</head>
<body onLoad="sendRequest()" >
	<div id="list"></div>
	<div class="videozz"></div>
	<video  autoplay muted loop poster="images/fallba1ck.jpg">
		<source src="images/movs.mp4">		
		你的游览器不支持video支持
	</video>
	<div class="box" >
		
		<div class="box-a">
			<div class="tem">
				<span>
					<canvas id="canvas1" height="230" width="430"></canvas>	
				</span>
			</div>
			<div class="water">
				<span>
					<canvas id="canvas2" height="230" width="430"></canvas>	
				</span>
			</div>
			<div class="animal">
				<span>
					<div class="red_poiont"></div>
					<img src="images/radio.gif" width="420px" height="250px" style="margin-left: 10px"/>
					<div class="red_poiont"></div>
				</span>
			</div>
			<div class="gas">
				<canvas id="canvas3" height="230" width="430"></canvas>
			</div>
			<div class="map">
				<div id="container"></div>
				<div class="button-group midlldes">
				    <button type="button" class="button button-primary  button-rounded" onclick="alert('relase oxgyen');">Realease Oxygen!</button>
				    <button type="button"  class="button button-royal button-rounded button-raised  button-caution" onclick="alert('Explore Environment!');">Explore Environment!</button>
				    <!--<button type="button" class="button button-primary">Option 3</button>-->
				</div>
			</div>
	    		<div class="m-xz7"></div>
	    		<div class="m-xz8 xzleft"></div>
	    		<div class="m-xz9"></div>
		    <div class="m-xz9-1"></div>
		    <div class="m-8"></div>
		    <div class="m-9">
		    		<div class="masked1" id="sx8">智能探识感知大数据平台</div>
		    </div> 
		    <div class="footer">
				<p>项目名称:<a target="_blank">教育部高教司-达内科技集团产学合作协同育人项目</a></p>
			</div>
		</div>
</div>
</body>
<script type="text/javascript">
			var time=new Array("0","0","0","0","0","0","0");
			var tem_lineChartData = {
			labels : ["8:59:30","8:59:31","8:59:32","8:59:33","8:59:34","8:59:35","8:59:36"],
			datasets : [
				{
					fillColor : "rgba(200,60,60,0.5)",
					strokeColor : "rgba(230,35,50,1)",
					pointColor : "rgba(240,15,15,1)",
					pointStrokeColor : "#fff",
					data : [0,59,55,53,50,49,48]
				}
			]
		}
			var water_lineChartData = {
					labels : ["8:59:30","8:59:31","8:59:32","8:59:33","8:59:34","8:59:35","8:59:36"],
					datasets : [
						{
							fillColor : "rgba(151,187,205,0.5)",
							strokeColor : "rgba(151,187,205,1)",
							pointColor : "rgba(151,187,205,1)",
							pointStrokeColor : "#fff",
							data : [0,35,40,41,45,50,60]
						}
					]
			}
			var barChartData = {
			labels : ["January","February","March","April","May","June","July"],
			datasets : [
				{
					fillColor : "rgba(100,100,100,0.5)",
					strokeColor : "rgba(151,187,205,1)",
					data : [0,59,90,81,56,55,40]
				}
			]	
		}
			var configs  = {
				scaleShowLabels : true,
				pointDot : true
			}
			
		function get_rightform(chagnetime){
			chagnetime=new Date(chagnetime);
			var hour = chagnetime.getHours();
			var mintnues = chagnetime.getMinutes();
			var seconds =  chagnetime.getSeconds();
			var	result = hour+":"+mintnues+":"+seconds;
			if(mintnues<=9&&seconds>9)
		    		var result = hour+":0"+mintnues+":"+seconds;
		    	if(mintnues<=9&&seconds<=9)
		    		var result = hour+":0"+mintnues+":0"+seconds;
		    	if(mintnues>9&&seconds<=9)
		    		var result = hour+":"+mintnues+":0"+seconds;
		    	return result;
		}//通过毫秒数得到时间
	
	
		function clock()
		{
		    var d = new Date();
		    for(var j=2;j<=6;j++){
		    		tem_lineChartData.labels[j-1]=tem_lineChartData.labels[j];
		    		water_lineChartData.labels[j-1]=water_lineChartData.labels[j];		
		    		tem_lineChartData.datasets[0].data[j-1]=tem_lineChartData.datasets[0].data[j];
		    		water_lineChartData.datasets[0].data[j-1]=water_lineChartData.datasets[0].data[j];
		    		barChartData.labels[j-1]=barChartData.labels[j];
		    		barChartData.datasets[0].data[j-1]=barChartData.datasets[0].data[j];
		    }//前移一个
		    var realtime = new Date(d.getTime()-d.getTime()%1000);
		    tem_lineChartData.labels[6]=get_rightform(realtime);
		   	water_lineChartData.labels[6]=get_rightform(realtime);
		   	$.getJSON("http://192.168.1.104:8080/ThreeD/DataGet",
	        	function(data){
	        		var tems=data.temperature;
	        		var water=data.humidity;
	        		var per=data.person;
	        		water_lineChartData.datasets[0].data[6]=water;
		   			tem_lineChartData.datasets[0].data[6]=tems;
		   			if(per.equals("1")){
						$(".red_poiont").fadeOut(100).fadeIn(100);	
					}else{
						$(".red_poiont").hide();
					}
	        });
		   	
		   	//是否有生命存在
		   	barChartData.datasets[0].data[6]=Math.random()*50;//瓦斯浓度
		   	//补充新的数据
		   	// 更换成从数据库中读取温度和水分以及其他的值
		   	
		    
		    myLine1 = new Chart(document.getElementById("canvas1").getContext("2d")).Line(tem_lineChartData,configs);
			myLine2 = new Chart(document.getElementById("canvas2").getContext("2d")).Line(water_lineChartData,configs);
			myLine3 = new Chart(document.getElementById("canvas3").getContext("2d")).Bar(barChartData,configs);
			
			//显示新的数据
		}
		
	
		var myLine1 = new Chart(document.getElementById("canvas1").getContext("2d")).Line(tem_lineChartData);
		var myLine2 = new Chart(document.getElementById("canvas2").getContext("2d")).Line(water_lineChartData);
		var myLine3 = new Chart(document.getElementById("canvas3").getContext("2d")).Bar(barChartData);
		init_time();
		function init_time(){
			var dates = new Date();
			var realtimess = new Date(dates.getTime()-dates.getTime()%1000);
		    for(var i=0;i<=6;i++){
		    		tem_lineChartData.labels[i]=get_rightform(realtimess-(6-i)*1000);
		    		water_lineChartData.labels[i]=get_rightform(realtimess-(6-i)*1000);
		    		barChartData.labels[i]=get_rightform(realtimess-(6-i)*1000);
		    }
		}
		var ints=self.setInterval("clock()",1000);
</script>
</html>