/**
 * EmployeeMapper.java Created: Nov 7, 2016 JavierAngelH
 */

package com.app.attendance.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.app.attendance.model.Employee;

/**
 * EmployeeMapper -
 *
 */
public class EmployeeMapper implements RowMapper<Employee> {

	@Override
	public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
		Employee employee = new Employee();

		employee.setId(rs.getString("id"));
		employee.setFirstname(rs.getString("firstname"));
		employee.setLastname(rs.getString("lastname"));
		employee.setUsername(rs.getString("username"));
		employee.setLocation(rs.getString("location"));
		employee.setDepartment(rs.getString("department"));
		employee.setManager(rs.getString("manager"));
		employee.setEmpdate(rs.getDate("empdate"));
		employee.setEmployment_status(rs.getString("employment_status"));
		employee.setRole(rs.getString("role"));
		employee.setPictureURL(rs.getString("picture"));
		employee.setPassword(rs.getString("password"));

		return employee;
	}

}
