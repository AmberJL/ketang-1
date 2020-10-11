package com.example.demo.c;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
 
/**
 * @Description: WebSocket服务端代码，包含接收消息，推送消息等接口
 * userName是接收的参数
 */
@Component
@ServerEndpoint(value = "/ws/{userName}")
public class WebSocketServer {
	static ConcurrentHashMap<Session, String> map = new ConcurrentHashMap<Session, String>();     //存储系统的用户信息
	static ConcurrentHashMap<Session,Long> heart = new ConcurrentHashMap<Session,Long>();
	RedisClient redis = new RedisClient();
	/**
    * @param session
    */
   @OnOpen
   public void OnOpen(@PathParam("userName")String userName,Session session) {
       System.out.println("OnOpen()方法被执行...");
       System.out.println("websocket连接建立成功...");
   			System.out.println("新上线人物："+userName);
   			System.out.println("SessionId:"+session.getId());
   			//之前用户仍在线  断掉老连接
   			if(map.values().contains(userName)) {
   				Iterator it = map.keySet().iterator();
   				while(it.hasNext()) {
   					Session s = (Session) it.next();
   					if(map.get(s).equals(userName)) {
   						try {
   							System.out.println("断掉老连接 id:"+s.getId());
							s.close();
							heart.remove(s);
							map.remove(s);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							System.out.println("关闭老连接错误");
						}
   						
   					}
   				}
   			}
   			//加入新id
   			System.out.println("加入心跳监测SessionId:"+session.getId());
   			map.put(session, userName);
   			System.out.println("当前用户："+map.size());
   			heart.put(session, new Date().getTime());

   		for(Session entry:map.keySet()) {
   			System.out.println(entry.getId()+" "+map.get(entry));
   		}

   }

   /**
    * 连接关闭的方法
    */
   @OnClose
   public void OnClose(Session session) {
   	if (map.containsKey(session)) {
   		map.remove(session);
   		heart.remove(session);
		}
       System.out.println("OnClose()方法被执行...");
       System.out.println("websocket连接已经关闭...");
   }

   /**
    * 接收消息的方法
    * @param msg
    * @param session
    * @throws InterruptedException 
    */
   @OnMessage
   public void OnMessage(String msg, Session session) throws InterruptedException {
       System.out.println("已从客户端接收消息：" + msg);
       if(msg.equals("ping")) {
    	   heart.put(session, new Date().getTime());
       }
//       this.sendMessage(session, msg);
//       JSONObject res = JSONObject.parseObject(new String(msg));
//       System.out.println(res.get("msgData"));
//       redis.push("myList",msg);
//       System.out.println("向客户端发送数据完毕...");
   }

	/**
    * 出错的方法，注意参数不能错
    * @param session
    * @param error
    */
   @OnError
   public void OnError(Session session,Throwable error) {
       System.out.println("OnError()方法dewd被执行..."+"Sessionid:"+session.getId());
       System.out.println("websocket出错...");
	   error.printStackTrace();
//   	if (map.containsKey(session)) {
//   		map.remove(session);
//   		heart.remove(session);
//		}

   }
   /**
    * 推送数据的方法
    * @param session map里存的登录信息
    * @param message 推送数据
    */
   public void sendMessage(Session session,String message){
		try {
			session.getBasicRemote().sendText(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   }
}
