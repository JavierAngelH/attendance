/** 
 * LeaveApplicationMapper.java Created: Dec 12, 2016 JavierAngelH
 */

package com.app.attendance.dao.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.app.attendance.model.LeaveApplication;

/**
 * LeaveApplicationMapper - 
 *
 */
public class LeaveApplicationMapper implements RowMapper<LeaveApplication> {

	@Override
	public LeaveApplication mapRow(ResultSet rs, int rowNum) throws SQLException {
		LeaveApplication application = new LeaveApplication();
		application.setId(rs.getInt("id"));
		application.setType(rs.getString("type"));
		application.setExplanation(rs.getString("explanation"));
application.setBackstoping(rs.getString("backstoping"));
application.setBalance(rs.getInt("balance"));
application.setStartDate(rs.getDate("start_date"));
application.setEndDate(rs.getDate("end_date"));
		return application;
	}

}
