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

	//学生GPS签到
	@Override
	public String qdGPS(String student_phone, String course_id, String fb_time, String gps) {
		qiandao_table qd;
		try {
			qd=qdDao.findById(new course_time_key(course_id,fb_time)).get();
		}catch(Exception e) {
			return "签到不存在";
		}
		
		
		String time=Time.getTime();
		if(Time.getDate(qd.getJztime()).getTime() < Time.getDate(time).getTime()) 
			return "签到已截止";
		
		
		// GPS判断
		String[] locate;
		double lng1,lat1,lng2,lat2,s;
		try {
			locate=qd.getCode().split(",");
			lng1=Integer.parseInt(locate[0]);
			lat1=Integer.parseInt(locate[1]);
			locate=gps.split(",");
			lng2=Integer.parseInt(locate[0]);
			lat2=Integer.parseInt(locate[1]);
			s=getDistanceMeter(lng1, lat1, lng2, lat2);
		}catch(Exception e) {
			return "GPS计算错误";
		}
		
		if(s>500) {
			return "GPS位置过远";
		}
		
		
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

	//老师发布GPS
	@Override
	public String newQdGPS(String course_id, String name, String way, long jz_long, String gps) {
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
		temp.setCode(gps);
		qdDao.save(temp);
		return gps;
	}
	
	//以下是GPS计算
	private static double getDistanceMeter(double lng1, double lat1, double lng2, double lat2) {
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double a = radLat1 - radLat2;
		double b = rad(lng1) - rad(lng2);
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)+ Math.cos(radLat1) * Math.cos(radLat2)* Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		s = Math.round(s * 10000d) / 10000d;
		s = s * 1000;
		return s;
	}
	private static double rad(double d) {
        return d * PI / 180.0;
    }
	private static final String TAG = "GetDistanceUtils";
    private static final double EARTH_RADIUS = 6378.137;
    private static final double PI = 3.14159265;
}
