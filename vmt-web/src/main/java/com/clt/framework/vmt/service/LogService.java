package com.clt.framework.vmt.service;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class LogService {
	private String fileDb = "LogVMT-TT.db";
	String url="";
	private DriverManagerDataSource dataSource;
	
	public DriverManagerDataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DriverManagerDataSource dataSource) {
		this.dataSource = dataSource;
	}

	public LogService(){
		CreateDb();
		CreateTable();
	}
	
	private void CreateDb(){
		 String pathTomcatRoot=System.getProperty("catalina.base")+"/Files/";
		 File dir = new File(pathTomcatRoot);
		 boolean exists = dir.exists();
		 if (!exists) {
		    dir.mkdir();
		}
		 pathTomcatRoot=System.getProperty("catalina.base")+File.separator+"Files"+File.separator+fileDb;
		 url = "jdbc:sqlite:" + pathTomcatRoot;
		 dir=new File(pathTomcatRoot);
		
		 
		 try{
			 dataSource=new DriverManagerDataSource();
			 dataSource.setDriverClassName("org.sqlite.JDBC");
			 dataSource.setUrl(url);
			 Connection connection = dataSource.getConnection();
			 if (connection != null) {
					 if(!dir.exists()&&!dir.isDirectory()){
					 	DatabaseMetaData meta = connection.getMetaData();
		                System.out.println("The driver name is " + meta.getDriverName());
		                System.out.println("A new database has been created.");
					 }
				 	
			 }
		 }catch (Exception e){
			 //System.out.println(e.getMessage());
		 }
	}
	private void CreateTable(){
		 try {
			 Connection connection = dataSource.getConnection();
			 Statement statement = connection.createStatement();
			 statement.execute("DROP TABLE IF EXISTS TT_LOG");
			 statement.executeUpdate(
				     "CREATE TABLE TT_LOG(" +
				     "TTNO TEXT not null, " +
				     "CONTR_NO TEXT , " +
				     "POSITION_ON_CHASSIS TEXT , " +
				     "YT_STS TEXT , " +
				     "YC_STS TEXT , " +
				     "QC_STS TEXT , " +
				     "FROM_LOCATION TEXT , " +
				     "TO_LOCATION TEXT , " +
				     "JOB_TYPE TEXT ,"+
				     "CREATE_DATE TEXT not null)"
				     );
			 statement.close();
			 connection.close();
		 }
		 catch (SQLException e) {
			   e.printStackTrace();
		 }
	}
	
	/*
	public void InsertLog(VmtWorkOrder vmtOrder){
		String sql = "INSERT INTO TT_LOG(TTNO,CONTR_NO,POSITION_ON_CHASSIS,YT_STS,YC_STS,QC_STS,FROM_LOCATION,TO_LOCATION,JOB_TYPE,CREATE_DATE) "
			+ "	VALUES(?,?,?,?,?,?,?,?,?,?)";
		 try {
			 	Connection connection = dataSource.getConnection();
			 	connection.setAutoCommit(false);
			 	PreparedStatement pstmt = connection.prepareStatement(sql);
			 	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			 	Date date = new Date();
			 	
	            pstmt.setString(1, vmtOrder.getYtNo());
	            pstmt.setString(2, vmtOrder.getCntrNo());
	            pstmt.setString(3, vmtOrder.getPositionOnChassis());
	            pstmt.setString(4, vmtOrder.getYtSts());
	            pstmt.setString(5, vmtOrder.getYcSts());
	            pstmt.setString(6, vmtOrder.getYcSts());
	            pstmt.setString(7, vmtOrder.getFmLocation());
	            pstmt.setString(8, vmtOrder.getToLocation());
	            pstmt.setString(9, vmtOrder.getJobTp());
	            pstmt.setString(10, dateFormat.format(date));
	            pstmt.executeUpdate();
	            pstmt.closeOnCompletion();
	            connection.commit();
	            connection.close();
        } catch (SQLException e) {
        	e.printStackTrace();
        }
	}
	*/
	
}
