<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <html>  
    <head>  
    <meta charset="utf-8" />  
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />  
    <title>Stupid ChatRoom</title>  
    <script src="http://libs.baidu.com/jquery/1.9.0/jquery.js"></script>  
    <style type="text/css">  
        .jz {  
            margin: 0 auto;   
            text-align: center;  
        }  
        .tw {  
            border:1px dotted red;  
            height: 60px;  
            width:600px;  
            margin:0px auto;  
            padding-left:10px;  
            padding-top:10px;  
            margin-top:5px;  
        }  
        .top {  
              
        }  
        .buttom {  
            padding-top:10px;  
        }  
        #send-box {  
            margin-top:10px;  
        }  
    </style>  
    </head>  
    <body>  
    <div class="jz" style="font-size:30px;">Stupid ChatRoom</div>  
    <div class="jz" style="font-size:20px;">Welcome ${sessionScope.uname}</b></div>  
    <div class="jz" id="err-box"></div>  
    <div id="msg-box">  
        <div class="tw">  
            <div class="top">admin  2017-1-07</div>  
            <div class="buttom">请大家随意畅谈</div>  
        </div>  
    </div>  
    <div id="send-box" class="jz">  
        <input type="text" id="text-msg" style="width:300px;"/>  
        <input type="button" onclick="send_msg()" value="Send"/>  
    </div>  
    <script type="text/javascript">  
        var username = ${sessionScope.uname};  
        var ws = new WebSocket('ws://127.0.0.1:9090/tweets/list');  
  
        ws.onclose = function(event) {   
            var msg = JSON.stringify({'username':username, 'type':'3'});    
            ws.send(msg);  
        };   
      
        ws.onmessage = function(event)   
        {  
            var data = JSON.parse(event.data);  
            if(data.type == '2')  
            {  
                render_data(data.username, data.data);  
            }  
            else if(data.type == '1')  
            {  
                $('#pcount').html(data.pcount);  
                render_data('<span style="color:red;">系统信息</span>', data.username + "加入系统");  
            }  
        };  
      
        function render_data(username, data)  
        {  
            var msg = [];  
            msg.push('<div class="tw">');  
            msg.push('<div class="top">' + username + '  2015-07-05</div>');  
            msg.push('<div class="buttom"> ' + data + ' </div>');  
            msg.push('</div>');  
            $('#msg-box').append(msg.join(''));  
        }  
          
        function start()   
        {  
            if(username != '')  
            {  
                var msg = JSON.stringify({'username':username, 'type':'1'});    
                ws.send(msg);  
            }  
        }  
      
        function send_msg()  
        {  
            var text_msg = $('#text-msg').val();  
            if(text_msg != '')  
            {  
                var msg = JSON.stringify({'username':username, 'type':'2', 'data': text_msg});    
                ws.send(msg);  
                $('#text-msg').val('');  
            }  
        }  
          
        (function(){  
            if(username == '')  
            {  
                alert('用户名为空，无法发布动弹');  
                $('#send-box').hide();  
            }  
        })();  
    </script>  
	<div id="menu" style="width:20%; height:400px;; overflow:auto;float:right;">
<dl>
 <dt><a href="User/RankList/" class="a_">Click here for UserRank</a></dt>	
</dl>
</div>
    </body>  
    </html>  