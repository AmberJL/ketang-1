package com.example.demo.Service;
import java.util.List;

import com.example.demo.data.messageData;
public interface MessageService {
	//查询未读消息
	public List<messageData> unReadMessage(String my_id);
	//未读变已读
	public void changeToRead(String to_user_id,String from_user_id,long time);
	//查询历史消息
	public List<messageData> historyMessage(String user,long time);
	//全部历史消息
	public List<messageData> allHistoryMessage(String user);
}
