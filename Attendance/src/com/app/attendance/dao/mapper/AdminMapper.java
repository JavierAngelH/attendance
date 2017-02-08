/**
 * AdminMapper.java Created: Nov 13, 2016 JavierAngelH
 */

package com.app.attendance.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.app.attendance.model.Employee;

/**
 * AdminMapper -
 *
 */
public class AdminMapper implements RowMapper<Employee> {

	@Override
	public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
		Employee employee = new Employee();

		employee.setEmail(rs.getString("email"));
		employee.setFirstname(rs.getString("name"));
		employee.setUsername(rs.getString("username"));

		return employee;
	}

}