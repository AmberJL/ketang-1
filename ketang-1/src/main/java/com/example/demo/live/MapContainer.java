package com.example.demo.live;
import java.net.Socket;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

public class MapContainer {
	
	private static final ConcurrentHashMap<String, ConcurrentHashMap<String,Player>> playerMap=new ConcurrentHashMap<String, ConcurrentHashMap<String,Player>>();
	private static final ConcurrentHashMap<String, Pusher> pusherMap=new ConcurrentHashMap<String, Pusher>();
	
	public static void addPusher(String course_id,Socket socket,String phone) {
		if(pusherMap.containsKey(course_id)) {
			pusherMap.get(course_id).shutDown();
		}
		Pusher pusher=new Pusher(socket,course_id,phone);
		pusherMap.put(course_id, pusher);
		new Thread(pusher).start();
	}
	
	public static void removePusher(String course_id) {
		if(pusherMap.containsKey(course_id)) {
			pusherMap.remove(course_id);
		}
	}
	
	public static void addPlayer(String course_id,Socket socket,String phone) {
		
		ConcurrentHashMap<String,Player> classMap;
		
		if(playerMap.containsKey(course_id)) {
			classMap=playerMap.get(course_id);
		}else {
			classMap=new ConcurrentHashMap<String,Player>();
			playerMap.put(course_id, classMap);
		}
		
		if(classMap.containsKey(phone)) {
			Player temp=classMap.get(phone);
			classMap.put(phone, new Player(socket,course_id,phone));
			temp.shutDown();
		}else {
			classMap.put(phone, new Player(socket,course_id,phone));
		}
	}
	
	public static void removePlayer(String course_id,String phone) {
		ConcurrentHashMap<String,Player> classMap;
		
		if(playerMap.containsKey(course_id)) {
			classMap=playerMap.get(course_id);
		}else {
			return;
		}
		
		classMap.remove(phone);
	}
	
	public static void push(String course_id,int size,byte[] data) {
		if(!playerMap.containsKey(course_id))return;
		ConcurrentHashMap<String, Player> mapTemp=playerMap.get(course_id);
		
		for(String s:mapTemp.keySet()) {
			try {
				Player temp=mapTemp.get(s);
				new Thread(new Runnable() {
					@Override
					public void run() {
						temp.push(size,data);
					}
				}).start();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		/*Iterator<Entry<String, Player>> entries = mapTemp.entrySet().iterator();
		
		while (entries.hasNext()) {
			Entry<String, Player> entry = entries.next();
			
			Player temp=entry.getValue();
			
			//System.out.println(entry.getKey());
			
			new Thread(new Runnable() {
				@Override
				public void run() {
					temp.push(size,data);
				}
			}).start();
		}*/
	}
}
