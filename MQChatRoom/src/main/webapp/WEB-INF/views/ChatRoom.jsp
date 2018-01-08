<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<title>Stupid ChatRoom</title>

    <script src="http://libs.baidu.com/jquery/1.9.0/jquery.js"></script>  
	<!-- 新 Bootstrap 核心 CSS 文件 -->
	<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
	
<style type="text/css"> 
html,body,div,h1,h2,h3,h4,h5,h6,p,span{
	padding: 0;
	margin: 0;
	overflow: hidden;
}

body{
	padding-top: 10px;
	overflow: auto;
}


.chat-container{
	position: relative;
}

.banner{
	border: 1px solid black;
	height: 80px;

}

.historyMsg{
	border: 1px solid black;
	height: 400px;
	padding: 5px;
	position: relative;
	overflow: auto;
}

.newMsg{
	text-align: left;
	margin-top: 5px;
}

.nickname{
	font-weight: bold;
}

.timespan{
	color: #ddd;
}

.myMsg{
	text-align: right;
	margin-top: 5px;
}

/*系统消息*/
.system{
	text-align: center;
	background-color: grey;
	color: white;
	border-radius: 5px;
	opacity: 0.6;
	margin: 5px 10px;
}

.control{
	border: 1px solid black;
	height: 115px;
}

.control-row{
	margin-top: 10px;
}

.inputMsg{
	height: 50px !important;
	resize: none;
}

.sendBtn{
	height: 50px;
}

footer{
	text-align: center;
}

.loginWrapper{
	position: fixed;
	top: 0;
	right: 0;
	bottom: 0;
	left: 0;
	text-align: center;
	display: block;
	background-color: rgba(5,5,5,0.5);
	padding-top: 200px;
}

.info{
	color: white;
	font-size: 20px;
}

.nickWrapper{
	display: none;
}

/* Tablets: 768px */
@media screen and (max-width: 760px){

	.historyMsg{
		font-size: 10px;
		border: 0px;
		height: 330px;
		padding: 5px;
		position: relative;
		overflow: auto;
	}

	.banner{
		border: 0px;
		border-bottom: 1px solid gray;
	}

	.inputMsg{
		margin-bottom: 2px;
	}

	.control{
		border: 0px;
	}

	footer{
		font-size: 15px;
	}
}
</style> 
</head>
<body>
	
	<div class="container chat-container">
		<!-- 标题header -->
		<div class="row">
			<div class="col-md-6 col-md-offset-3 col-sm-12 banner">
				<h1>Stupid ChatRoom</h1>
				<span>Welecome Back </span>
				<span id="uname">${sessionScope.uname}</span>
				
			</div>
		</div>
		
		<!-- 历史消息 -->
		<div class="row">
			<div class="col-md-6 col-md-offset-3 col-sm-12 historyMsg" id="historyMsg">
				<!-- <p class="newMsg">
					<span class="nickname">kyrieliu</span>
					<span class="timespan">(21:00:15):</span>
					去去去去去去去去去去去去去
				</p>

				<p class="myMsg">
					<span class="timespan">(22:12:11):</span>
					了了了了了了了了了了了了
				</p>


				<p class="system"><span class="nickname">kyrieliu</span>加入了群聊</p> -->
				
			</div>
		</div>
		
		<!-- 控制台 -->
		<div class="row">
			<div class="col-md-6 col-md-offset-3 col-sm-12 control">
				<div class="row control-row">
					<div class="col-md-8 col-sm-8">
						<textarea id="inputMsg" class="inputMsg form-control"></textarea>
					</div>
					<div class="col-md-4 col-sm-4">
						<button id="sendBtn" class="form-control sendBtn btn btn-primary">send</button>
					</div>
				</div>
			</div>
		</div>


			
		</div>
	</div>



	<footer>
		<small>RankList <a href="User/RankList/" target="_blank">Click Here</a></small>
	</footer>
	

	    <script type="text/javascript">
	    var uname="";
	    function sendMsg() {
	        $.ajax({
	            type: "GET",
	            url: "User/PushMessage",

	            data: "pushMsg=" + $("#inputMsg").val(),
	            success: function(v) {
	            	 uname=v;
	                displayNewMsg('', $("#inputMsg").val(), 'myMsg');
	                $("#inputMsg").val("");
	                	
	            }
	        });
	    }
	    //点击"发送"按钮的时候发送 
	    $("#sendBtn").click(sendMsg);
	    //在文本框上按回车也能发送 
	    $("#inputMsg").keypress(function() {
	        if (event.keyCode == 13) { //判断按键是回车 
	            sendMsg();
	        }
	    });

	    function getMsg() {
	        $.getJSON('User/PullMessage',
	        function(json) {
	        	

	            for (var d in json){
	            	if (json[d].uname != uname)
	            	{displayNewMsg(json[d].uname, json[d].text, 'newMsg');}
	            }
	        });
	    }
	    //每两秒从服务器取一次数据 
	    setInterval("getMsg()", 2000);
	    		
	    function add0(m){return m<10?'0'+m:m };  
	    function getDate(shijianchuo) {  
	      var time = new Date(parseInt(shijianchuo));  
	      var y = time.getFullYear();  
	      var m = time.getMonth()+1;  
	      var d = time.getDate();  
	      var h = time.getHours();  
	      var mm = time.getMinutes();  
	      var s = time.getSeconds();  
	      return y+'-'+add0(m)+'-'+add0(d)+' '+add0(h)+':'+add0(mm)+':'+add0(s);  
	    }; 
	    function displayNewMsg (nickname, msg, who){
			var historyMsg = document.getElementById('historyMsg');

			var p = document.createElement('p');
			p.setAttribute('class',who);

			var span_nickname = document.createElement('span');
			span_nickname.setAttribute('class','nickname');
			span_nickname.textContent = nickname;

			var span_timespan = document.createElement('span');
			span_timespan.setAttribute('class','timespan');
			var time = '(' + new Date().toTimeString().substr(0, 8) + ')';
			span_timespan.textContent = time;

			var text = document.createTextNode(msg);

			p.appendChild(span_nickname);
			p.appendChild(span_timespan);
			p.appendChild(text);

			historyMsg.appendChild(p);

			//控制滚动条自动滚到底部
			historyMsg.scrollTop = historyMsg.scrollHeight;

		};
	    
		</script>
	

</body>
</html>
