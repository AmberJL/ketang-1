package com.example.demo.Serviceimp;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Service.MessageService;
import com.example.demo.data.messageData;
import com.example.demo.entity.message_table;
import com.example.demo.repository.MessageRepository;
@Service
public class MessageServiceimp implements MessageService{
	@Autowired
	private MessageRepository messageRep;
	@Override
	public List<messageData> unReadMessage(String my_id) {
		// TODO Auto-generated method stub
		List<message_table> data = this.messageRep.unReadMessage(my_id, 0);
		List<messageData> ms = data.stream().map(entity -> {
			messageData user = new messageData();
			BeanUtils.copyProperties(entity, user);
			return user;
		}).collect(Collectors.toList());
		
		return ms;
	}

	@Override
	public void changeToRead(String to_user_id, String from_user_id, long time) {
		// TODO Auto-generated method stub
		this.messageRep.toRead(to_user_id, time, from_user_id);
	}

	@Override
	public List<messageData> historyMessage(String user, long time) {
		// TODO Auto-generated method stub
		List<message_table> table = this.messageRep.historyMessage(user, time);
		List<messageData> data = table.stream().map(entity->{
			messageData d = new messageData();
			BeanUtils.copyProperties(entity, d);
			return d;
		}).collect(Collectors.toList());
		System.out.println("查找长度："+data.size());
		return data;
	}

	@Override
	public List<messageData> allHistoryMessage(String user) {
		// TODO Auto-generated method stub
		List<message_table> table = this.messageRep.allHistoryMessage(user);
		List<messageData> data = table.stream().map(entity->{
			messageData d = new messageData();
			BeanUtils.copyProperties(entity, d);
			System.out.println(d.getContent());
			return d;
		}).collect(Collectors.toList());
		return data;
	}

	@Override
	public void saveMessage(messageData message) {
		// TODO Auto-generated method stub
		System.out.println(message.getContent());
		message_table ms = new message_table();
		ms.setContent(message.getContent());
		ms.setFrom_user_id(message.getFrom_user_id());
		ms.setTime(message.getTime());
		ms.setState(message.getState());
		ms.setTo_user_id(message.getTo_user_id());
		
		this.messageRep.save(ms);
	}

}
