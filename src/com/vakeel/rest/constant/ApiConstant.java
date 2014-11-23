package com.vakeel.rest.constant;

public class ApiConstant {
	public static final String Driver_NAME = "oracle.jdbc.driver.OracleDriver";
	public static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	public static final String USER_NAME = "DWINES";
	public static final String PASSWORD = "DWINES";
	public static final String QUERY_MOBILE = "select * from RESTAPI_TEST where PHONENUMBER = ?";

	public static final String QUERY_ALL_RECORD = "select * from RESTAPI_TEST";
	public static final String QUERY_INSERT_RECORD = "insert into RESTAPI_TEST "
			+ "(NAME, INTEREST,DEVELOPER,CTC,HOMETOWN,COMPANY,POSITION,TECHNOLOGY,PHONENUMBER) "
			+ "VALUES ( ?, ?, ?, ?, ? ,?, ?, ?, ?) ";
	public static final String QUERY_UPDATE="update RESTAPI_TEST "+"set COMPANY = ? " +" where PHONENUMBER = ? ";
	public static final String QUERY_DELETE="delete from RESTAPI_TEST "+ "where PHONENUMBER = ? ";

}
