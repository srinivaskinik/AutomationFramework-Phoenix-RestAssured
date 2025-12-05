package com.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.database.DatabaseManager;
import com.database.model.MapJobProblemModel;

public class MapJobProblemDao {
	private static final String PROBLEM_QUERY=
			"""
			select * from map_job_problem where tr_job_head_id =?;
			""";
	
	private MapJobProblemDao() {
		
	}
	public  static MapJobProblemModel getProblemDetails(int tr_job_head_id) {
		MapJobProblemModel mapJobProblemModel=null;
		try {
			Connection connection = DatabaseManager.getConnection();
			PreparedStatement ps = connection.prepareStatement(PROBLEM_QUERY);
			ps.setInt(1, tr_job_head_id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				mapJobProblemModel=new MapJobProblemModel(
						rs.getInt("id"), 
						rs.getInt("tr_job_head_id"), 
						rs.getInt("mst_problem_id"), 
						rs.getString("remark"));
			}
		} catch (SQLException e) {
			System.err.print(e.getMessage());
		}
		return mapJobProblemModel;
	}
}
