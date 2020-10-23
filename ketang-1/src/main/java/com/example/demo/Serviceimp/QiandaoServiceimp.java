package com.example.demo.Serviceimp;

import java.util.ArrayList;
import java.util.List;


import com.example.demo.data.courseLogData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Service.QiandaoService;
import com.example.demo.entity.course_log_table;
import com.example.demo.entity.qiandao_log_table;
import com.example.demo.entity.qiandao_table;
import com.example.demo.entity.key.course_time_key;
import com.example.demo.entity.key.course_time_phone_key;
import com.example.demo.repository.CourseLogRepositpry;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.QiandaoLogRepository;
import com.example.demo.repository.QiandaoRespository;
import com.example.demo.tool.SJM;
import com.example.demo.tool.Time;

@Service
public class QiandaoServiceimp implements QiandaoService {
	
	private final QiandaoRespository qdDao;
	private final QiandaoLogRepository qdLogDao;
	private final CourseLogRepositpry cLogDao;
	private final CourseRepository cDao;

	public QiandaoServiceimp(@Autowired QiandaoRespository qdDao,@Autowired QiandaoLogRepository qdLogDao,@Autowired CourseLogRepositpry cLogDao,@Autowired CourseRepository cDao) {
		this.qdDao=qdDao;
		this.qdLogDao=qdLogDao;
		this.cLogDao=cLogDao;
		this.cDao=cDao;
	}

	//老师获取签到列表
	@Override
	public List<qiandao_table> getListT(String course_id) {
		return qdDao.findByCourseidOrderByFbtimeDesc(course_id);
	}

	//老师发布签到
	@Override
	public String newQd(String course_id, String name, String way, long jz_long) {
		
		try {
			cDao.findById(course_id).get();
		}catch(Exception e) {
			return "课程不存在";
		}
		
		qiandao_table temp=new qiandao_table();
		temp.setCourseid(course_id);
		temp.setFbtime(Time.getTime());
		temp.setJztime(Time.getTimeNext(jz_long));
		temp.setName(name);
		temp.setWay(way);
		String sjm=SJM.getNum(4);
		temp.setCode(sjm);
		qdDao.save(temp);
		return sjm;
	}

	//老师获取单次签到人数
	@Override
	public String getSize(String course_id, String fb_time) {
		return qdLogDao.countByCourseidAndFbtimeAndValue(course_id,fb_time,"已签到")+"/"+cLogDao.countByCourseid(course_id);
	}
	
	//老师获取单次签到详情
	@Override
	public List<qiandao_log_table> getValue(String course_id, String fb_time) {
		List<course_log_table> students=cLogDao.findAllByCourseid(course_id);
		List<qiandao_log_table> temp=new ArrayList<qiandao_log_table>();
		for(int i=0;i<students.size();i++) {
			temp.add(getLog(course_id,fb_time,students.get(i).getStudentphone()));
		}
		
		return temp;
	}
	private qiandao_log_table getLog(String course_id,String fb_time,String student_phone) {
		qiandao_log_table temp;
		try {
			temp=qdLogDao.findById(new course_time_phone_key(course_id,fb_time,student_phone)).get();
		}catch(Exception e) {
			e.printStackTrace();
			temp=new qiandao_log_table();
			temp.setCourseid(course_id);
			temp.setFbtime(fb_time);
			temp.setStuphone(student_phone);
			temp.setValue("未签到");
		}
		return temp;
	}

	//老师修改签到状态
	@Override
	public String change(String course_id, String fb_time, String student_phone, String value) {
		
		try {
			qdDao.findById(new course_time_key(course_id,fb_time)).get();
		}catch(Exception e) {
			return "签到不存在";
		}
		
		qiandao_log_table temp;
		try {
			temp=qdLogDao.findById(new course_time_phone_key(course_id,fb_time,student_phone)).get();
		}catch(Exception e) {
			temp=new qiandao_log_table();
			temp.setCourseid(course_id);
			temp.setFbtime(fb_time);
			temp.setStuphone(student_phone);
			temp.setValue(value);
		}
		qdLogDao.save(temp);
		return "修改成功";
	}

	//老师删除签到
	@Override
	public String remove(String course_id, String fb_time) {
		qdLogDao.deleteAllByCourseidAndFbtime(course_id,fb_time);
		qdDao.deleteById(new course_time_key(course_id,fb_time));
		return "删除签到";
	}

	//学生获取签到列表
	@Override
	public List<qiandao_table> getListS(String student_phone, String course_id) {
		List<qiandao_table> temp=qdDao.findByCourseidOrderByFbtimeDesc(course_id);
		for(int i=0;i<temp.size();i++) {
			qiandao_table temp2=temp.get(i);
			try {
				temp2.setCode(qdLogDao.findById(new course_time_phone_key(course_id,temp2.getFbtime(),student_phone)).get().getValue());
			}catch(Exception e) {
				temp2.setCode("未签到");
			}
		}
		return temp;
	}

	//学生签到
	@Override
	public String qd(String student_phone, String course_id, String fb_time, String code) {
		qiandao_table qd;
		try {
			qd=qdDao.findById(new course_time_key(course_id,fb_time)).get();
		}catch(Exception e) {
			return "签到不存在";
		}
		
		if(!qd.getCode().equals(code))return "签到码错误";
		
		String time=Time.getTime();
		
		if(Time.getDate(qd.getJztime()).getTime() < Time.getDate(time).getTime()) 
			return "签到已截止";
		
		qiandao_log_table qdLog;
		try {
			qdLog=qdLogDao.findById(new course_time_phone_key(course_id,fb_time,student_phone)).get();
			qdLog.setQdtime(time);
			qdLog.setValue("已签到");
		}catch(Exception e) {
			qdLog=new qiandao_log_table();
			qdLog.setCourseid(course_id);
			qdLog.setFbtime(fb_time);
			qdLog.setQdtime(time);
			qdLog.setStuphone(student_phone);
			qdLog.setValue("已签到");
		}
		
		qdLogDao.save(qdLog);
		
		return "签到成功";
	}
	
	//老师重置签到时间
	@Override
	public String resetTime(String course_id, String fb_time, long jz_long) {
		try {
			qiandao_table temp=qdDao.findById(new course_time_key(course_id,fb_time)).get();
			temp.setJztime(Time.getTimeNext(jz_long));
			qdDao.save(temp);
			return "修改成功";
		}catch(Exception e) {
			return "签到不存在";
		}
	}
}
