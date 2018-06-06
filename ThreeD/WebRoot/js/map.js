$(function(){
	//GPS坐标
	var xx = 119.2157459206;//
	var yy = 34.5975640209;//
	var gpsPoint = new BMap.Point(xx,yy);//
//地图初始化
	var map = new BMap.Map("container");    // 创建Map实例
	map.centerAndZoom(gpsPoint, 30);  // 初始化地图,设置中心点坐标和地图级别
	map.addControl(new BMap.MapTypeControl());   //添加地图类型控件
	map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
	    //地图平移缩放控件
	map.addControl(new BMap.NavigationControl());
//坐标转换完之后的回调函数
//	function getgpsx(x){
//		$.getJSON("http://192.168.1.104:8080/ThreeD/DataGet",
//	        	function(data){
//	        		var x=data.lat;
//	        		console.log(x);
//	        		return x;
//	        });
//	}
//	function getgpsy(y){
//		$.getJSON("http://192.168.1.104:8080/ThreeD/DataGet",
//	        	function(data){
//			console.log(x);
//	        		var y=data.lon;
//	        });
//	}
	translateCallback = function (point){
		map.clearOverlays();
	    var marker = new BMap.Marker(point);
	    map.addOverlay(marker);
	    var label = new BMap.Label("当前位置",{offset:new BMap.Size(20,-10)});
	    marker.setLabel(label); //添加百度label
	    map.setCenter(point);
	}
	var inter = self.setInterval(function(){
//		var x = getgpsx(xx);
//		var y = getgpsy(yy);
//		var x=199.129665;
//		var y=34.36459;
//		var gpsPoint =new BMap.Point(xx,yy);
		BMap.Convertor.translate(gpsPoint,0,translateCallback);
	},
		1000);
});
	


	
