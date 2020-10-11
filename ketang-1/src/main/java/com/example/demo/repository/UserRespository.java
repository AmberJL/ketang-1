package com.example.demo.repository;

import com.example.demo.entity.user_table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRespository extends JpaRepository<user_table,String>{
	//查找所有用户
    @Query(value="select u from userTable u where u.userPhone = ?1 and u.userPwd=?2",nativeQuery=true)
    List<user_table> findUser(String phone, String pwd);
    //登录
	@Query(value="select * from user_table t where t.user_phone =:id1 and t.user_pwd =:id2", nativeQuery=true)
	public List<user_table> login(@Param("id1") String phone,@Param("id2")String pwd);
	//重置密码
	@Transactional
	@Modifying
	@Query(value="update user_table t set t.user_pwd =:id1 where t.user_phone =:id2",nativeQuery=true)
	public void forget_pwd(@Param("id1") String pwd,@Param("id2") String phone);
}
