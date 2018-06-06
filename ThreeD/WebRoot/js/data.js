		var time=new Array("0","0","0","0","0","0","0");
			var tem_lineChartData = {
			labels : ["8:59:30","8:59:31","8:59:32","8:59:33","8:59:34","8:59:35","8:59:36"],
			datasets : [
				{
					fillColor : "rgba(200,60,60,0.5)",
					strokeColor : "rgba(230,35,50,1)",
					pointColor : "rgba(240,15,15,1)",
					pointStrokeColor : "#fff",
					data : [65,59,55,53,50,49,48]
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
							data : [27,35,40,41,45,50,60]
						}
					]
			}
			function init_time(){
			var dates = new Date();
			var realtimess = new Date(dates.getTime()-dates.getTime()%1000);
		    for(var i=0;i<=6;i++){
		    		water_lineChartData.labels[i]=get_rightform(realtimess-(6-i)*1000);
		    		tem_lineChartData.labels[i]=get_rightform(realtimess-(6-i)*1000);
		    }
		}
		$(function(){
			
		var myLine1 = new Chart(document.getElementById("canvas1").getContext("2d")).Line(tem_lineChartData);
		var myLine2 = new Chart(document.getElementById("canvas2").getContext("2d")).Line(water_lineChartData);
		init_time();
		var ints=self.setInterval("clock()",1000);
		});
		
		
	
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
		    for(var j=1;j<=6;j++){
		    		tem_lineChartData.labels[j-1]=tem_lineChartData.labels[j];
		    		water_lineChartData.labels[j-1]=water_lineChartData.labels[j];		
		    		tem_lineChartData.datasets[0].data[j-1]=tem_lineChartData.datasets[0].data[j];
		    		water_lineChartData.datasets[0].data[j-1]=water_lineChartData.datasets[0].data[j];
		    }//前移一个
		    var realtime = new Date(d.getTime()-d.getTime()%1000);
		    tem_lineChartData.labels[6]=get_rightform(realtime);
		   	water_lineChartData.labels[6]=get_rightform(realtime);
		   	water_lineChartData.datasets[0].data[6]=Math.random()*50;//温度
		   	tem_lineChartData.datasets[0].data[6]=Math.random()*50;//水分
		   	//是否有生命存在
		   	//瓦斯浓度
		   	//烟雾浓度
		    myLine1 = new Chart(document.getElementById("canvas1").getContext("2d")).Line(tem_lineChartData);
			myLine2 = new Chart(document.getElementById("canvas2").getContext("2d")).Line(water_lineChartData);
			//显示新的数据
		}
		