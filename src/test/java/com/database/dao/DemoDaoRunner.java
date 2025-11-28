package com.database.dao;

import java.sql.SQLException;

import com.database.model.JobHeadModel;

public class DemoDaoRunner {
	public static void main(String[] args) throws SQLException {
		JobHeadModel jobHeadModel = JobHeadDao.getJobHeadInfo(113901);
		System.out.println(jobHeadModel);
	}

}
