package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.course_table;
import com.example.demo.entity.message_table;
import com.example.demo.entity.user_table;


public interface MessageRepository extends JpaRepository<message_table,Long> {
	//查询未读消息
	@Query(value="select * from message_table t where t.to_user_id =:id1 and t.state =:id2", nativeQuery=true)
	public List<message_table> unReadMessage(@Param("id1") String id,@Param("id2")int state);
	
	//查询历史消息
	@Query(value="select * from message_table t where t.to_user_id =:id1 and t.time < :id2", nativeQuery=true)
	public List<message_table> historyMessage(@Param("id1") String id,@Param("id2")long time);
	
	//查询全部历史消息
	@Query(value="select * from message_table t where t.to_user_id =:id1 or t.from_user_id = :id1", nativeQuery=true)
	public List<message_table> allHistoryMessage(@Param("id1") String id);
	
	
	
//	//发消息存数据库
//	@Transactional
//	@Modifying
//	@Query(value="update message_table t set t.state = 1 where t.to_user_id =:id1 and t.time < id2 and t.from_user_id =:id3",nativeQuery=true)
//	public void forget_pwd(@Param("id1") String to,@Param("id2") long time,@Param("id3") String from);
}