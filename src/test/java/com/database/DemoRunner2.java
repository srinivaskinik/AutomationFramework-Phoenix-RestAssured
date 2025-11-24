package com.database;

import java.sql.SQLException;

public class DemoRunner2 {
	
	public static void main(String[] args) throws SQLException {
		DatabaseManagerOLD.createConnection();
		long startTime = System.currentTimeMillis();
		for(int i=1;i<=1000;i++)
		{
			DatabaseManagerOLD.createConnection();
			DatabaseManagerOLD.createConnection();
			DatabaseManagerOLD.createConnection();
		}
		
		long endTime=System.currentTimeMillis();
		System.out.println("Duration "+(endTime-startTime)+"ms");
	}

}
