package com.ray.utils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import com.ray.enitity.ReadyClass;
import com.ray.enitity.ReadyCourse;
import com.ray.enitity.ReadyTeacher;
import com.ray.enitity.Schedule;
import com.ray.service.ManaService;
import com.ray.service.ManaServiceimpl;

public class Arrange {
	//准备好存放区间
	private List<ReadyCourse> readyCourses  = new ArrayList<ReadyCourse>() ;//课程名，课程的剩余需要安排的次数
	private ReadyClass readyClass = new ReadyClass();//班级名，准备储存班级周一到周五到课程安排
	private List<ReadyTeacher> readyTeachers = new ArrayList<ReadyTeacher>();//教师名，获取教师每周的工作日和每天至多的工作次数
	private ReadyTeacher rTeacher = new ReadyTeacher();
	//准备接入服务层
	private ManaService mana= new ManaServiceimpl(); 
	
//	private ManaCourseAction mcAction = new ManaCourseAction();
	
	public String doMana() throws SQLException{
		doArrange("class1");
		List<Schedule> schedule1 = doPush(readyClass.classArrange,readyClass.getClassName(),readyClass.getTimeOf());
		int class1 = mana.addSchedule(schedule1);
//		int j=0;
//		while(j<schedule1.size()){
//			System.out.println(schedule1.get(j).getCName());
//			System.out.println(schedule1.get(j).getSMonday()+" "+
//						schedule1.get(j).getSTuesday()+"  "+
//						schedule1.get(j).getSWensday()+"  "+
//						schedule1.get(j).getSThursday()+"  "+
//						schedule1.get(j).getSFriday()+"  "
//					);
//			j++;
//		}
		doArrange("class2");
		List<Schedule> schedule2 = doPush(readyClass.classArrange,readyClass.getClassName(),readyClass.getTimeOf());
		int class2 = mana.addSchedule(schedule2);
		doArrange("class3");
		List<Schedule> schedule3 = doPush(readyClass.classArrange,readyClass.getClassName(),readyClass.getTimeOf());
		int class3 = mana.addSchedule(schedule3);
		doArrange("class4");
		List<Schedule> schedule4 = doPush(readyClass.classArrange,readyClass.getClassName(),readyClass.getTimeOf());
		int class4 = mana.addSchedule(schedule4);
		if(class1==1&&class2==1&&class3==1&&class4==1){
			return "courses for class1 ... class4 have arranged!！";
		}else{
			return "class1～class4's courses already arrange,please reset them！！";
		}
//		int i=0;
//		while(i<schedule4.size()){
//			System.out.println(schedule4.get(i).getCName());
//			System.out.println(schedule4.get(i).getSMonday()+" "+
//						schedule4.get(i).getSTuesday()+"  "+
//						schedule4.get(i).getSWensday()+"  "+
//						schedule4.get(i).getSThursday()+"  "+
//						schedule4.get(i).getSFriday()+"  "
//					);
//			i++;
//		}
	}
	
	public  List<Schedule> doPush(String[][] courseArrange,String className,String timeOf){
		//调用service层方法操作
		List<Schedule> schedule = new ArrayList<Schedule>();
		//通过循环，分解二维数组的每一周的课程安排
		for(int i=0;i<courseArrange.length;i++){//第一节
			//周1～5
			schedule.add(new Schedule(className,timeOf,(i+1)+"",courseArrange[i][0],
					courseArrange[i][1],courseArrange[i][2],courseArrange[i][3],courseArrange[i][4]));
		}
		return schedule;
	}
	
	public void doArrange(String whichClass) throws SQLException{
		//预备工作
		readyCourses  = mana.findReadyCourseViewByClassName(whichClass);
		readyTeachers = mana.findReadyTeacherViewByClassName(whichClass);
		
		readyClass.setClassName(whichClass);

		for(int i=0;i<4;i++){//每天4节课
			for(int j=0;j<5;j++){//周5天上课
				
				if(i==0 || i==2 ){
					//上午、下午第一节课为主科
					for(int m =0;m<readyCourses.size();m++){
						//遍历主科课程
						if(readyCourses.get(m).getCourseIs() == 1 && readyCourses.get(m).getCourseTime()>0){
							//如果=1，是主科,且该课程还没有安排满,查找本班级授课老师
							rTeacher = findTeacherByID(readyCourses.get(m).getTid(),readyTeachers);
							
							if(rTeacher != null){
								if(rTeacher.mana[0]==0&&rTeacher.mana[1]==0&&rTeacher.mana[2]==0&&rTeacher.mana[3]==0&&rTeacher.mana[4]==0)
									exChangeTeacheryMana(rTeacher);
								//找到授课老师，查看他今天上不上班， 且今天次数是否安排满
								if(rTeacher.mana[j]!=0 && rTeacher.workNum[j]<=2){
									//不等于0意味着上班,<=2意味着还能安排
									//将任职教师的课插入课表,将时间段插入表格
									if(i==0){
										readyClass.setTimeOf("7:45~9:30");
									}else if(i==2){
										readyClass.setTimeOf("2:00~3:30");
									}
									readyClass.classArrange[i][j] = rTeacher.getCourseName() ; 
//									System.out.print("第"+(j+1)+"周，第"+(i+1)+"节，"+readyClass.classArrange[i][j]+"    ");
									//课程被安排过一次，本周本班次数减1
									int times = readyCourses.get(m).getCourseTime();
										times--;
										int num = rTeacher.workNum[j];
										num--;
										rTeacher.workNum[j]=num;
									readyCourses.get(m).setCourseTime(times);
									//将这一节课的工作安排发给老师
									rTeacher.classArrange[i][j]=readyClass.getClassName();
//									System.out.print("第"+(j+1)+"周，第"+(i+1)+"节，"+rTeacher.classArrange[i][j]+"    ");
								break;
								}else if(rTeacher.mana[j]!=0){
									//等于0 意味着不上班,该老师不行，跳出本循环，换个课程
									 continue;
									
								}else if(rTeacher.workNum[j]>2){
									//>2 意味着安排满了，老师当天不能再安排课了
									 continue;
								}
							}else if(rTeacher == null){
								//如果该门课程没有找到老师。。可以补充要求添加的功能
								continue;
							}else {
								JOptionPane.showConfirmDialog(null, "没有找到老师，也没有找不到老师？？课程安排满咯");
								 continue;
							}
						}
					}
					
				}else if(i==1 || i==3){
					//上午下午第二节课为副科
					for(int m =0;m<readyCourses.size();m++){
						//遍历副科课程
						if(readyCourses.get(m).getCourseIs() == 0 && readyCourses.get(m).getCourseTime()>0){
							//如果=0，是副科,且该课程还没有安排满,查找本班级授课老师
							rTeacher = findTeacherByID(readyCourses.get(m).getTid(),readyTeachers);
							
							if(rTeacher != null){
								if(rTeacher.mana[0]==0&&rTeacher.mana[1]==0&&rTeacher.mana[2]==0&&rTeacher.mana[3]==0&&rTeacher.mana[4]==0){
								exChangeTeacheryMana(rTeacher);}
								//找到授课老师，查看他今天上不上班， 且今天次数是否安排满
								if(rTeacher.mana[j]!=0 && rTeacher.workNum[j]<=2){
									//不等于0意味着上班,<=2意味着还能安排
									if(i==1){
										readyClass.setTimeOf("9:45~11:30");
									}else if(i==3){
										readyClass.setTimeOf("3:45～5:30");
									}
									readyClass.classArrange[i][j] = rTeacher.getCourseName() ; //将任职教师的课插入课表
//									System.out.print("第"+(j+1)+"周，第"+(i+1)+"节，"+readyClass.classArrange[i][j]+"    ");
									//课程被安排过一次，本周本班次数减1
									int times = readyCourses.get(m).getCourseTime();
										times--;
									readyCourses.get(m).setCourseTime(times);
									//将这一节课的工作安排发给老师
									rTeacher.classArrange[i][j]=readyClass.getClassName();
//									System.out.print("第"+(j+1)+"周，第"+(i+1)+"节，"+rTeacher.classArrange[i][j]+"    ");
									
									break;
								}else if(rTeacher.mana[j]!=0){
									//等于0 意味着不上班,该老师不行，跳出本循环，换个课程
									 continue;
									
								}else if(rTeacher.workNum[j]>2){
									//>2 意味着安排满了，老师当天不能再安排课了
									 continue;
								}
							}else if(rTeacher == null){
								//如果该门课程没有找到老师。。可以补充要求添加的功能
								continue;
							}else {
								JOptionPane.showConfirmDialog(null, "没有找到老师，也没有找不到老师？？课程安排满咯");
								 continue;
							}
						}
					}
				}
			}
			System.out.println();
		}
		System.out.println();
		
	}
	
	
	private ReadyTeacher findTeacherByID (String TID,List<ReadyTeacher> readyTeacher){
		for(int i=0;i<readyTeacher.size();i++){
			if(readyTeacher.get(i).getTID().equals(TID)){
				return readyTeacher.get(i);
			}
		}
		return null;
		
	}
	
	private void exChangeTeacheryMana(ReadyTeacher readyTeacher){
		int tmana = readyTeacher.getTmana();
		for(int i=5;i>=1;i--){
			if(tmana%10==i){
				readyTeacher.mana[i-1] = tmana%10;
				tmana = tmana/10;
			}else{
				readyTeacher.mana[i-1] = 0;
			}
		}
	}
	
//	public static void main(String[] args) {
//		Arrange a= new Arrange();
//		try {
//			a.doMana();
//		} catch (SQLException e) {
//			
//			e.printStackTrace();
//		}
//	}
}
