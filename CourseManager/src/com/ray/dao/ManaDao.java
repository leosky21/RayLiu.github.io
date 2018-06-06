package com.ray.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.ray.enitity.ReadyCourse;
import com.ray.enitity.ReadyTeacher;
import com.ray.enitity.Schedule;
import com.ray.utils.DBOperator;

public class ManaDao {
	DBOperator dbo = new DBOperator();
	
	//获取course的courseName，courseTime
	public  List<ReadyCourse> findReadyCourseViewByClassName(String CName) throws SQLException{
		List<ReadyCourse> list = new ArrayList<ReadyCourse>();
		String sql = "select CourseName,CourseTime,CourseIs,TID from classCourse_degree where CName='"+CName+"'";
		ResultSet rs = dbo.query(sql);
		while(rs.next()){
			list.add(new ReadyCourse(rs.getString("CourseName"),rs.getInt("CourseTime"),rs.getInt("CourseIs"),rs.getString("TID")));
		}
		return list;
	}
	
	
	//获取TID，TName
	public  List<ReadyTeacher> findReadyTeacherViewByClassName(String CName) throws SQLException{
		List<ReadyTeacher> list = new ArrayList<ReadyTeacher>();
		String sql = "select TID,TName,TMana,CourseName from classCourse_degree where CName='"+CName+"'";
		ResultSet rs = dbo.query(sql);
		while(rs.next()){
			list.add(new ReadyTeacher(rs.getString("TID"),rs.getString("TName"),rs.getInt("Tmana"),rs.getString("CourseName")));
		}
		return list;
	}

	
	
	public int addSchedule(List<Schedule> schedule1) {
		String sql = "insert into schedule (CName,TimeID,SMonday,STusday,SWensday,SThurDay,SFriday) values(?,?,?,?,?,?,?)";
		String sql2 = "select * from schedule where CName='class4'";
		boolean bol = true;
		try {
			if(dbo.query(sql2).next()){
				bol = false;
				return 0;
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		if(bol){
			for(int i=0;i<schedule1.size();i++){
			dbo.executeInsert(sql,schedule1.get(i).getCName(),schedule1.get(i).getTimeID(),schedule1.get(i).getSMonday(),
					schedule1.get(i).getSTuesday(),schedule1.get(i).getSWensday(),schedule1.get(i).getSThursday(),schedule1.get(i).getSFriday());
			System.out.println(sql+";"+schedule1.get(i).getCName()+";"+schedule1.get(i).getTimeID()+";"+schedule1.get(i).getSMonday()+";"+
					schedule1.get(i).getSTuesday()+";"+schedule1.get(i).getSWensday()+";"+schedule1.get(i).getSThursday()+";"+schedule1.get(i).getSFriday());
			}
			
		}
		return 1;
	}


	public int resetAll() {
		String sql = "drop table schedule";
		String sql2 = 
						"create table schedule ("+
//							"SID int primary key auto_increment,"+
							"SID int primary IDENTITY(1,1) key ,"+
							"CName varchar(50) not null,"+
							"TimeID varchar(50) not null,"+
							"SWeek char(5),"+
							"SMonday varchar(50),"+
							"STusday varchar(50),"+
							"SWensday varchar(50),"+
							"SThurDay varchar(50),"+
							"SFriday varchar(50),"+
							"SSaturday varchar(50),"+
							"SSunday varchar(50),"+
								"foreign key (CName) references class (CName),"+
								"foreign key (TimeID) references Time (TimeID)"+	
						")";
		dbo.executeDb(sql);
		dbo.executeDb(sql2);
		return 1;
	}


	public List<Schedule> findReadyCourseByClassName(String whichClass) throws SQLException {
		List<Schedule> list = new ArrayList<Schedule>();
		String sql = "select CName,TimeID,TimeOf,SMonday,STusday,SWensday,SThurday,SFriday from schedule_degree where CName='"+whichClass+"'";
		ResultSet rs = dbo.query(sql);
		while(rs.next()){
			list.add(new Schedule(rs.getString("CName"),rs.getString("TimeOf"),rs.getString("TimeID"),
					rs.getString("SMonday"),rs.getString("STusday"),rs.getString("SWensday")
					,rs.getString("SThurday"),rs.getString("SFriday")));
		}
		return list;
	}
}
