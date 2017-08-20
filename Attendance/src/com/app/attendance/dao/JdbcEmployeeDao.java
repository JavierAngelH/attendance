/**
 * JdbcEmployeeDao.java Created: Nov 7, 2016 JavierAngelH
 */

package com.app.attendance.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.app.attendance.dao.mapper.AdminMapper;
import com.app.attendance.dao.mapper.EmployeeMapper;
import com.app.attendance.dao.mapper.LeaveApplicationMapper;
import com.app.attendance.model.Bean;
import com.app.attendance.model.Department;
import com.app.attendance.model.Employee;
import com.app.attendance.model.EmployeeSS;
import com.app.attendance.model.LeaveApplication;
import com.app.attendance.model.Performance;
import com.app.attendance.model.PerformanceReview;
import com.app.attendance.model.TerminationForm;
/**
 * JdbcEmployeeDao -
 *
 */
@Repository
public class JdbcEmployeeDao implements EmployeeDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired(required = true)
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/**
	 * @see com.app.attendance.dao.EmployeeDao#getEmployee(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public Employee getEmployee(String username, String password) {
		try {
			Employee employee = this.jdbcTemplate.queryForObject(EmployeeDao.QUERY_GET_EMPLOYEE, new EmployeeMapper(),
					username, password);
			return employee;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}
	
	
	@Override
	public List<Employee> getHRTimesheetsTwoMonths(Date date) {
		List<Employee> list = new ArrayList<>();
		try {
			List<Map<String, Object>> listEmployee = this.jdbcTemplate.queryForList(EmployeeDao.QUERY_HR_TIMESHEETS_2_MONTHS,
					date, date, date);
			for (Map<String, Object> map : listEmployee) {
				Employee employee = new Employee();
				employee.setId(map.get("id").toString());
				employee.setFirstname(map.get("name").toString());
				employee.setProjectName(map.get("project").toString());
				employee.setFunderName(map.get("funder").toString());
				employee.setHoursList(map.get("hours").toString());
				employee.setDaysList(map.get("day").toString());
				employee.setLocation(map.get("location").toString());
				employee.setDepartment(map.get("department").toString());
				employee.setManager(map.get("manager").toString());
				
				list.add(employee);
			}

		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();

		}
		
		return list;

	}

	/**
	 * @see com.app.attendance.dao.EmployeeDao#workedHoursPerEmployee(java.util.Date,
	 *      java.lang.String)
	 */
	@Override
	public List<Employee> workedHoursPerEmployee(Date date, String managerId) {
		List<Employee> list = new ArrayList<>();
		try {
			List<Map<String, Object>> listEmployee = this.jdbcTemplate
					.queryForList(EmployeeDao.QUERY_GET_WORKED_HOURS_PER_EMPLOYEE, date, date, date, managerId);
			for (Map<String, Object> map : listEmployee) {
				Employee employee = new Employee();
				employee.setId(map.get("id").toString());
				employee.setFirstname(map.get("name").toString());
				employee.setProjectName(map.get("project").toString());
				employee.setFunderName(map.get("funder").toString());
				employee.setHoursList(map.get("hours").toString());
				employee.setDaysList(map.get("day").toString());
				list.add(employee);

			}

			System.out.println(listEmployee.toString());
		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
		}

		return list;
	}

	/**
	 * @see com.app.attendance.dao.EmployeeDao#getDaysOfMonth()
	 */
	@Override
	public Integer getDaysOfMonth() {
		return this.jdbcTemplate.queryForInt(EmployeeDao.QUERY_GET_NUMBER_OF_DAYS);
	}
	
	
	@Override
	public Integer getDaysOfPreviousMonth() {
		return this.jdbcTemplate.queryForInt(EmployeeDao.QUERY_GET_NUMBER_OF_DAYS_LAST_MONTH);
	}

	/**
	 * @see com.app.attendance.dao.EmployeeDao#updateStatus(java.lang.String,
	 *      java.sql.Date, java.lang.String)
	 */
	@Override
	public void updateStatus(String status, Date date, String employeeId) {

	this.jdbcTemplate.update(EmployeeDao.QUERY_UPDATE_STATUS, status, employeeId, date, date);
	
	}

	/**
	 * @see com.app.attendance.dao.EmployeeDao#getPendingLeaveRequests(java.lang.String)
	 */
	@Override
	public List<Employee> getPendingLeaveRequests(String managerId) {
		List<Employee> list = new ArrayList<>();
		try {
			List<Map<String, Object>> listEmployee = this.jdbcTemplate
					.queryForList(EmployeeDao.QUERY_GET_LEAVE_REQUESTS, managerId);
			for (Map<String, Object> map : listEmployee) {
				Employee employee = new Employee();
				employee.setId(map.get("id").toString());
				employee.setFirstname(map.get("name").toString());
				employee.setDepartment(map.get("department").toString());
				employee.setStartDate((Date) map.get("start_date"));
				employee.setEndDate((Date) map.get("end_date"));

				list.add(employee);

			}

			System.out.println(listEmployee.toString());
		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
		}

		return list;
	}


	@Override
	public void updateLeaveRequest(String status, String employeeId) {
		this.jdbcTemplate.update(EmployeeDao.QUERY_UPDATE_LEAVE_REQUEST, status, employeeId);

	}

	/**
	 * @see com.app.attendance.dao.EmployeeDao#saveResponseMessage(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public void saveResponseMessage(String employeeId, String text) {
		this.jdbcTemplate.update(EmployeeDao.QUERY_SAVE_LEAVE_RESPONSE_MESSAGE, employeeId, text);

	}

	/**
	 * @see com.app.attendance.dao.EmployeeDao#getAbsentEmployees(java.sql.Date)
	 */
	@Override
	public List<Employee> getAbsentEmployees(Date date) {
		List<Employee> list = new ArrayList<>();
		try {
			List<Map<String, Object>> listEmployee = this.jdbcTemplate.queryForList(EmployeeDao.QUERY_ABSENT_EMPLOYEES,
					date, date);
			for (Map<String, Object> map : listEmployee) {
				Employee employee = new Employee();
				employee.setId(map.get("id").toString());
				employee.setFirstname(map.get("name").toString());
				employee.setDepartment(map.get("department").toString());
				employee.setManager(map.get("manager").toString());

				list.add(employee);

			}

			System.out.println(listEmployee.toString());
		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();

		}

		return list;
	}

	/**
	 * @see com.app.attendance.dao.EmployeeDao#getHRLeaveRequests()
	 */
	@Override
	public List<Employee> getHRLeaveRequests() {
		List<Employee> list = new ArrayList<>();
		try {
			List<Map<String, Object>> listEmployee = this.jdbcTemplate
					.queryForList(EmployeeDao.QUERY_HR_LEAVE_REQUESTS);
			for (Map<String, Object> map : listEmployee) {
				Employee employee = new Employee();
				employee.setId(map.get("id").toString());
				employee.setFirstname(map.get("name").toString());
				employee.setDepartment(map.get("department").toString());
				employee.setStartDate((Date) map.get("start_date"));
				employee.setEndDate((Date) map.get("end_date"));
				employee.setLocation(map.get("location").toString());
				list.add(employee);

			}

		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();

		}

		return list;
	}
	
	

	/**
	 * @see com.app.attendance.dao.EmployeeDao#processRequestByHR(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public void processRequestByHR(String status, String employeeId) {
		this.jdbcTemplate.update(EmployeeDao.QUERY_HR_PROCESS_REQUEST, status, employeeId);

	}

	/**
	 * @see com.app.attendance.dao.EmployeeDao#getDepartmentList()
	 */
	@Override
	public List<Department> getDepartmentList() {
		List<Department> list = new ArrayList<>();
		try {
			List<Map<String, Object>> listDepartment = this.jdbcTemplate
					.queryForList(EmployeeDao.QUERY_DEPARTMENT_LIST);
			for (Map<String, Object> map : listDepartment) {
				Department department = new Department();

				department.setId(map.get("id").toString());
				department.setName(map.get("name").toString());
				department.setDescription(map.get("description").toString());
				department.setManageirid(map.get("managerid").toString());

				list.add(department);

			}

		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();

		}

		return list;
	}

	/**
	 * @see com.app.attendance.dao.EmployeeDao#getStringList()
	 */
	@Override
	public List<String> getLocationList() {
		List<String> list = new ArrayList<>();
		try {
			List<Map<String, Object>> listDepartment = this.jdbcTemplate.queryForList(EmployeeDao.QUERY_LOCATIONS_LIST);
			for (Map<String, Object> map : listDepartment) {

				list.add(map.get("location").toString());

			}

		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();

		}

		return list;
	}

	/**
	 * @see com.app.attendance.dao.EmployeeDao#getHRTimesheets(java.sql.Date)
	 */
	@Override
	public List<Employee> getHRTimesheets(Date date) {
		List<Employee> list = new ArrayList<>();
		try {
			List<Map<String, Object>> listEmployee = this.jdbcTemplate.queryForList(EmployeeDao.QUERY_HR_TIMESHEETS,
					date, date);
			for (Map<String, Object> map : listEmployee) {
				Employee employee = new Employee();
				employee.setId(map.get("id").toString());
				employee.setFirstname(map.get("name").toString());
				employee.setProjectName(map.get("project").toString());
				employee.setFunderName(map.get("funder").toString());
				employee.setHoursList(map.get("hours").toString());
				employee.setDaysList(map.get("day").toString());
				employee.setLocation(map.get("location").toString());
				employee.setDepartment(map.get("department").toString());
				employee.setManager(map.get("manager").toString());
				if(map.get("signature")!= null)
				employee.setSignatureUrl(map.get("signature").toString());
				list.add(employee);
			}

		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();

		}

		return list;
	}
	



	/**
	 * @see com.app.attendance.dao.EmployeeDao#getMonthName()
	 */
	@Override
	public String getMonthName() {
		return this.jdbcTemplate.queryForObject(EmployeeDao.QUERY_MONTH, String.class);
	}

	/**
	 * @see com.app.attendance.dao.EmployeeDao#getYear()
	 */
	@Override
	public String getYear() {
		return this.jdbcTemplate.queryForObject(EmployeeDao.QUERY_YEAR, String.class);

	}

	/**
	 * @see com.app.attendance.dao.EmployeeDao#getCodeList()
	 */
	@Override
	public List<Bean> getCodeList() {
		List<Bean> list = new ArrayList<>();
		try {
			List<Map<String, Object>> listCodes = this.jdbcTemplate.queryForList(EmployeeDao.QUERY_CODE_LIST);
			for (Map<String, Object> map : listCodes) {
				Bean bean = new Bean();

				bean.setId((Integer) map.get("id"));
				bean.setCode(map.get("code").toString());
				bean.setDescription(map.get("code").toString() + " - " + map.get("description").toString());
				list.add(bean);

			}

		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();

		}

		return list;
	}

	/**
	 * @see com.app.attendance.dao.EmployeeDao#getAdmin(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public Employee getAdmin(String username, String password) {
		try {
			Employee employee = this.jdbcTemplate.queryForObject(EmployeeDao.QUERY_GET_ADMIN, new AdminMapper(),
					username, password);
			return employee;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}

	/**
	 * @see com.app.attendance.dao.EmployeeDao#getManagers()
	 */
	@Override
	public List<Employee> getManagers() {
		List<Employee> list = new ArrayList<>();
		try {
			List<Map<String, Object>> listEmployee = this.jdbcTemplate.queryForList(EmployeeDao.QUERY_GET_MANAGERS);
			for (Map<String, Object> map : listEmployee) {
				Employee employee = new Employee();
				employee.setId(map.get("id").toString());
				employee.setFirstname(map.get("firstname").toString());
				employee.setLastname(map.get("lastname").toString());
				employee.setUsername(map.get("firstname").toString() + " " + map.get("lastname").toString());
				
				if(map.get("signature")!= null)
				employee.setSignatureUrl(map.get("signature").toString());
				list.add(employee);

			}

			System.out.println(listEmployee.toString());
		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();

		}

		return list;
	}

	/**
	 * @see com.app.attendance.dao.EmployeeDao#getProjectList()
	 */
	@Override
	public List<Bean> getProjectList() {
		List<Bean> list = new ArrayList<>();
		try {
			List<Map<String, Object>> listCodes = this.jdbcTemplate.queryForList(EmployeeDao.QUERY_GET_PROJECTS);
			for (Map<String, Object> map : listCodes) {
				Bean bean = new Bean();

				bean.setId((Integer) map.get("id"));
				bean.setName(map.get("name").toString());
				list.add(bean);

			}

		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();

		}

		return list;
	}

	/**
	 * @see com.app.attendance.dao.EmployeeDao#insertEmployee(com.app.attendance.model.Employee)
	 */
	@Override
	public void insertEmployee(Employee employee) {
		this.jdbcTemplate.update(EmployeeDao.QUERY_INSERT_EMPLOYEE, employee.getId(), employee.getFirstname(),
				employee.getLastname(), employee.getUsername(), employee.getPassword(), employee.getLocation(),
				employee.getDepartment(), employee.getManager(), employee.getEmpdate(), employee.getEmployment_status(),
				employee.getRole(), employee.getPictureURL());
	}

	/**
	 * @see com.app.attendance.dao.EmployeeDao#getEmployeeList()
	 */
	@Override
	public List<Employee> getEmployeeList() {
		List<Employee> list = new ArrayList<>();
		try {
			List<Map<String, Object>> listEmployee = this.jdbcTemplate.queryForList(EmployeeDao.QUERY_EMPLOYEE_LIST);
			for (Map<String, Object> map : listEmployee) {
				Employee employee = new Employee();
				employee.setId(map.get("id").toString());
				employee.setFirstname(map.get("firstname").toString());
				employee.setLastname(map.get("lastname").toString());
				employee.setEmployment_status(map.get("employment_status").toString());
				employee.setRole(map.get("role").toString());
				employee.setPictureURL(map.get("picture").toString());
				employee.setUsername(map.get("username").toString());
				employee.setPassword(map.get("password").toString());
				employee.setLocation(map.get("location").toString());
				employee.setDepartment(map.get("department").toString());
				employee.setManager(map.get("manager").toString());
				employee.setEmpdate((Date) map.get("empdate"));
				employee.setRole(map.get("role").toString());
				employee.setPictureURL(map.get("picture").toString());
				
				list.add(employee);

			}

			System.out.println(listEmployee.toString());
		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();

		}

		return list;
	}

	/**
	 * @see com.app.attendance.dao.EmployeeDao#updateEmployee(com.app.attendance.model.Employee)
	 */
	@Override
	public void updateEmployee(Employee employee) {
		this.jdbcTemplate.update(EmployeeDao.QUERY_UPDATE_EMPLOYEE, employee.getFirstname(), employee.getLastname(),
				employee.getUsername(), employee.getPassword(), employee.getLocation(), employee.getDepartment(),
				employee.getManager(), employee.getEmpdate(), employee.getEmployment_status(), employee.getRole(),
				employee.getPictureURL(), employee.getId());
	}

	/**
	 * @see com.app.attendance.dao.EmployeeDao#removeEmployee(java.lang.String)
	 */
	@Override
	public void removeEmployee(String id) {
		this.jdbcTemplate.update(EmployeeDao.QUERY_REMOVE_EMPLOYEE, id);

	}

	/**
	 * @see com.app.attendance.dao.EmployeeDao#insertDepartment(com.app.attendance.model.Department)
	 */
	@Override
	public void insertDepartment(Department department) {
		this.jdbcTemplate.update(EmployeeDao.QUERY_INSERT_DEPARTMENT, department.getId(), department.getName(),
				department.getManageirid(), department.getDescription());

	}

	/**
	 * @see com.app.attendance.dao.EmployeeDao#updateDepartment(com.app.attendance.model.Department)
	 */
	@Override
	public void updateDepartment(Department department) {
		this.jdbcTemplate.update(EmployeeDao.QUERY_UPDATE_DEPARTMENT, department.getName(), department.getManageirid(),
				department.getDescription(), department.getId());

	}

	/**
	 * @see com.app.attendance.dao.EmployeeDao#removeDepartment(java.lang.String)
	 */
	@Override
	public void removeDepartment(String id) {
		this.jdbcTemplate.update(EmployeeDao.QUERY_REMOVE_DEPARTMENT, id);
	}

	/**
	 * @see com.app.attendance.dao.EmployeeDao#getFunderList()
	 */
	@Override
	public List<Bean> getFunderList() {
		List<Bean> list = new ArrayList<>();
		try {
			List<Map<String, Object>> listCodes = this.jdbcTemplate.queryForList(EmployeeDao.QUERY_GET_FUNDERS);
			for (Map<String, Object> map : listCodes) {
				Bean bean = new Bean();

				bean.setId((Integer) map.get("id"));
				bean.setName(map.get("name").toString());
				list.add(bean);

			}

		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();

		}

		return list;
	}

	/**
	 * @see com.app.attendance.dao.EmployeeDao#timesheetRecordId(java.sql.Date,
	 *      java.lang.String, java.lang.Integer)
	 */
	@Override
	public Integer timesheetRecordId(java.util.Date date, String idEmployee, Integer idProject) {
		try {
			return this.jdbcTemplate.queryForInt(EmployeeDao.QUERY_TIMESHEET_DAY_ENTRY, date, date, date, idEmployee,
					idProject);

		} catch (Exception e) {
			return null;
		}
	}

	
	@Override
	public void insertIntoTimeSheet(String idEmployee, java.util.Date date, String totalHours, Integer idProject,
			Integer idFunder) {
		this.jdbcTemplate.update(EmployeeDao.INSERT_INTO_TIMESHEET, idEmployee, date, totalHours, idProject, idFunder);

	}

	/**
	 * @see com.app.attendance.dao.EmployeeDao#updateTimesheetEntry(java.lang.String,
	 *      java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public void updateTimesheetEntry(String totalHours, Integer idProject, Integer idFunder, Integer id) {
		this.jdbcTemplate.update(EmployeeDao.QUERY_UPDATE_TIMESHEET, totalHours, idProject, idFunder, id);

	}

	/**
	 * @see com.app.attendance.dao.EmployeeDao#getFunderName(java.lang.Integer)
	 */
	@Override
	public String getFunderName(Integer id) {
		// return this.jdbcTemplate.queryForMap(sql, args, argTypes)
		return null;
	}

	/**
	 * @see com.app.attendance.dao.EmployeeDao#workedHours(java.sql.Date,
	 *      java.lang.String)
	 */
	@Override
	public List<Employee> workedHours(Date date, String employeeId) {
		List<Employee> list = new ArrayList<>();
		try {
			List<Map<String, Object>> listEmployee = this.jdbcTemplate.queryForList(EmployeeDao.QUERY_GET_WORKED_HOURS,
					date, date, employeeId);
			for (Map<String, Object> map : listEmployee) {
				Employee employee = new Employee();
				employee.setId(map.get("id").toString());
				employee.setFirstname(map.get("name").toString());
				employee.setProjectName(map.get("project").toString());
				employee.setFunderName(map.get("funder").toString());
				employee.setHoursList(map.get("hours").toString());
				employee.setDaysList(map.get("day").toString());
				Bean project = new Bean();
				project.setId((Integer)map.get("id_project"));
				employee.setProject(project);
				
				Bean funder = new Bean();
				funder.setId((Integer)map.get("id_funder"));
				employee.setFunder(funder);
				

				
				list.add(employee);

			}

			System.out.println(listEmployee.toString());
		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();

		}

		return list;
	}


	@Override
	public List<String> getEmployeeMessages(String employeeId) {
		List<String> list = new ArrayList<>();
		try {
			List<Map<String, Object>> messagesList = this.jdbcTemplate.queryForList(EmployeeDao.QUERY_GET_MESSAGES,
					employeeId);
			for (Map<String, Object> map : messagesList) {

				list.add(map.get("text").toString());

			}

		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();

		}

		return list;
	}

	/**
	 * @see com.app.attendance.dao.EmployeeDao#removeMessages(java.lang.String)
	 */
	@Override
	public void removeMessages(String employeeId) {
		this.jdbcTemplate.update(EmployeeDao.QUERY_DELETE_MESSAGES, employeeId);

	}

	/**
	 * @see com.app.attendance.dao.EmployeeDao#verifyLeaveDates(java.lang.String)
	 */
	@Override
	public Employee verifyLeaveDates(String employeeId) {
		Employee employee = new Employee();
		try {
			Map<String, Object> result = this.jdbcTemplate.queryForMap(EmployeeDao.QUERY_VERIFY_VACATION, employeeId);

			employee.setStartDate((Date) result.get("start_date"));
			employee.setEndDate((Date) result.get("end_date"));

		} catch (EmptyResultDataAccessException e) {
			System.out.println(e.getMessage());
			return null;
		}

		return employee;
	}

	/**
	 * @see com.app.attendance.dao.EmployeeDao#deletePendingVacation(java.lang.String)
	 */
	@Override
	public void deletePendingVacation(String employeeId) {
		this.jdbcTemplate.update(EmployeeDao.QUERY_DELETE_VACATION, employeeId);

	}

	/**
	 * @see com.app.attendance.dao.EmployeeDao#updateUsedVacation(java.lang.String)
	 */
	@Override
	public void updateUsedVacation(String employeeId) {
		this.jdbcTemplate.update(EmployeeDao.QUERY_UPDATE_VACATION, employeeId);
	}

	/** 
	 * @see com.app.attendance.dao.EmployeeDao#insertLeaveApplication(java.lang.String, java.lang.String, java.lang.String, int, java.lang.String)
	 */
	@Override
	public void insertLeaveApplication(String employeeId, String type, String explanation, int balance,
			String backstopping, Date startDate, Date endDate) {
		this.jdbcTemplate.update(EmployeeDao.QUERY_INSERT_LEAVE_APPLICATION, type, explanation, backstopping, balance, employeeId, startDate, endDate);
		String hrText = "Employee " + employeeId + " has made a leave request";
		saveHRMessage(hrText);

	}

	/** 
	 * @see com.app.attendance.dao.EmployeeDao#getLeaveApplicationByEmployee(java.lang.String)
	 */
	@Override
	public LeaveApplication getLeaveApplicationByEmployee(String employeeId) {
		try {
			LeaveApplication application = this.jdbcTemplate.queryForObject(EmployeeDao.QUERY_GET_LEAVE_APPLICATION_BY_EMPLOYEE, new LeaveApplicationMapper(),
					employeeId);
			return application;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}

	/** 
	 * @see com.app.attendance.dao.EmployeeDao#updateLeaveApplication(java.lang.String, java.lang.String, java.lang.String, int, java.lang.String)
	 */
	@Override
	public void updateLeaveApplication(int applicationId, String type, String explanation, int balance,
			String backstopping, Date startDate, Date endDate) {
		this.jdbcTemplate.update(EmployeeDao.QUERY_UPDATE_LEAVE_APPLICATION, type, explanation, backstopping, balance, startDate, endDate, applicationId);
		String hrText = "Leave request " + applicationId + " updated";
		saveHRMessage(hrText);
		
	}

	/** 
	 * @see com.app.attendance.dao.EmployeeDao#getLeaveApplications()
	 */
	@Override
	public List<LeaveApplication> getLeaveApplications() {
		List<LeaveApplication> list = new ArrayList<>();
		try {
			List<Map<String, Object>> listApplications = this.jdbcTemplate
					.queryForList(EmployeeDao.QUERY_GET_LEAVE_APPLICATIONS);
			for (Map<String, Object> map : listApplications) {
				LeaveApplication application = new LeaveApplication();
				application.setId(Integer.parseInt(map.get("id").toString()));
				application.setType(map.get("type").toString());
				application.setExplanation(map.get("explanation").toString());
		application.setBackstoping(map.get("backstoping").toString());
		application.setBalance(Integer.parseInt(map.get("balance").toString()));
		application.setEmployeeId(map.get("employee_id").toString());
		application.setEmployeeName(map.get("name").toString());
			application.setDesignation(map.get("department").toString());
			application.setLocation(map.get("location").toString());
application.setStartDate((java.util.Date) map.get("start_date"));
application.setEndDate((java.util.Date) map.get("end_date"));
				list.add(application);

			}

		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public void insertTerminationForm(String employeeId, String leavingReason, String possibleReturn,
			String recommendation, String managementReason, String suggestions, String comments,
			String rehire, String terminationReason, String managementPrevention,String satisfaction,
			String likeEmployment, String dislikeEmployment, String considerReapply, String keepContact,
			String phoneNumber){
		this.jdbcTemplate.update(EmployeeDao.QUERY_INSERT_TERMINATION_FORM, employeeId, leavingReason, possibleReturn, recommendation, managementReason,
				suggestions, comments, rehire, terminationReason, managementPrevention, satisfaction, likeEmployment, dislikeEmployment, considerReapply, keepContact, phoneNumber);

	}

	
	
	/** 
	 * @see com.app.attendance.dao.EmployeeDao#getTerminationForms()
	 */
	@Override
	public List<TerminationForm> getTerminationForms() {
		List<TerminationForm> list = new ArrayList<>();
		try {
			List<Map<String, Object>> listTerminationForms = this.jdbcTemplate
					.queryForList(EmployeeDao.QUERY_GET_TERMINATION_FORMS);
			for (Map<String, Object> map : listTerminationForms) {
				TerminationForm termination = new TerminationForm();
				termination.setComments(map.get("comments").toString());
				termination.setDesignation(map.get("department").toString());
				termination.setEmployeeId(map.get("id").toString());
				termination.setEmployeeName(map.get("name").toString());
				termination.setEmploymentDate((java.util.Date) map.get("empDate"));		
				termination.setLeavingReason(map.get("leaving_reason").toString());
				termination.setManagementReason(map.get("management_reason").toString());
				termination.setPossibleReturn(map.get("possible_return").toString());
				termination.setRecommendation(map.get("recommendation").toString());
				termination.setRehire(map.get("rehire").toString());
				termination.setSuggestions(map.get("suggestions").toString());
				termination.setTerminationReason(map.get("termination_reason").toString());
				termination.setId((int) map.get("id_termination"));
				termination.setManagementPrevention(map.get("management_prevention").toString());
				termination.setSatisfaction(map.get("satisfaction").toString());
				termination.setLikeEmployment(map.get("like_employment").toString());
				termination.setDislikeEmployment(map.get("dislike_employment").toString());
				termination.setConsiderReapply(map.get("consider_reapply").toString());
				termination.setKeepContact(map.get("keep_contact").toString());
				termination.setPhoneNumber(map.get("phone_number").toString());
				
				int reviewed = (int) map.get("hr_reviewed");
				termination.setHrReviewed(reviewed);
				if(reviewed == 1){
				termination.setFormalResignation(map.get("formal_resignation").toString());
				termination.setHandoverNote(map.get("handover_note").toString());
				termination.setHandoverProperties(map.get("handover_properties").toString());
				termination.setMedicalCoverage(map.get("medical_coverage").toString());
				termination.setBenefitsPaid(map.get("benefits_paid").toString());
				termination.setMinimumNotice(map.get("minimum_notice").toString());
				termination.setFinalPayment(map.get("final_payment").toString());
				termination.setElegibleRehire(map.get("elegible_rehire").toString());
				}
				list.add(termination);
			}

		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
		}

		return list;
	}

	/** 
	 * @see com.app.attendance.dao.EmployeeDao#saveHRMessage(java.lang.String)
	 */
	@Override
	public void saveHRMessage(String text) {

		this.jdbcTemplate.update(EmployeeDao.QUERY_SAVE_MESSAGE_FOR_HR, "HR", text);

	}

	/** 
	 * @see com.app.attendance.dao.EmployeeDao#insertEmployeePerformance(com.app.attendance.model.Performance)
	 */
	@Override
	public void insertEmployeePerformance(Performance performance, String employeeId) {
		java.util.Date date = new java.util.Date();
		this.jdbcTemplate.update(EmployeeDao.QUERY_CREATE_PERFORMANCE, employeeId, 
				performance.getObjective1(), performance.getObjective2(), performance.getObjective3(),
				performance.getReason(),date);
	
	}

	/** 
	 * @see com.app.attendance.dao.EmployeeDao#getPerformanceByManager(java.lang.String)
	 */
	@Override
	public List<Performance> getPerformanceByManager(String idManager) {
		
        List<Performance> list = jdbcTemplate.query(EmployeeDao.QUERY_GET_PERFORMANCE_BY_MANAGER, new BeanPropertyRowMapper(Performance.class), idManager);

        return list;
	}

	/** 
	 * @see com.app.attendance.dao.EmployeeDao#updatePerformance(com.app.attendance.model.Performance)
	 */
	@Override
	public void updatePerformance(Performance performance) {
		this.jdbcTemplate.update(EmployeeDao.QUERY_APPROVE_PERFORMANCE_BY_MANAGER, 
				performance.getManagerComment1(), performance.getManagerComment2(), performance.getManagerComment3(),
				performance.getTechnicalScore(), performance.getNonTechnicalScore(),
				performance.getTotalScore(), performance.getRatingCategory(), performance.getSupervisorComment(), 1, performance.getId());

	}

	/** 
	 * @see com.app.attendance.dao.EmployeeDao#getPerformanceByHR()
	 */
	@Override
	public List<Performance> getPerformanceByHR() {
		  List<Performance> list = jdbcTemplate.query(EmployeeDao.QUERY_GET_PERFORMANCE_BY_HR, new BeanPropertyRowMapper(Performance.class));

	        return list;
	}

	/** 
	 * @see com.app.attendance.dao.EmployeeDao#updateTerminationForm(com.app.attendance.model.TerminationForm)
	 */
	@Override
	public void updateTerminationForm(TerminationForm form) {
		this.jdbcTemplate.update(EmployeeDao.QUERY_UPDATE_TERMINATION_FORM, form.getFormalResignation(), form.getHandoverNote(),
				form.getHandoverProperties(), form.getMedicalCoverage(), form.getBenefitsPaid(), form.getMinimumNotice(), form.getFinalPayment(),
				form.getElegibleRehire(), form.getHrReviewed(), form.getId());
	}

	/** 
	 * @see com.app.attendance.dao.EmployeeDao#savePerformanceReview(com.app.attendance.model.PerformanceReview)
	 */
	@Override
	public void savePerformanceReview(PerformanceReview review) {
		
		
		this.jdbcTemplate.update(EmployeeDao.QUERY_INSERT_PERFORMANCE_REVIEW, review.getPunctualStaff(), review.getInformsLatenessStaff(),
				review.getPersonalAssignmentsStaff(), review.getListenInstructionsStaff(), review.getFollowsInstructionsStaff(),
				review.getWillingToAssistStaff(), review.getWillingToAcceptStaff(), review.getLiquidatesFinantialStaff(), review.getFinantialRequestsTimelyStaff(),
				review.getPrudentFinancesStaff(), review.getInitiativeStaff(), review.getResolvesChallengesStaff(), review.getQualityProductsStaff(),
				review.getTimelyProductsStaff(), review.getIntegrityStaff(), review.getRespectStaff(), review.getOralCommunicationStaff(),
				review.getWrittenCommunicationStaff(), review.getErrorResponsibilityStaff(), review.getCommitmentStaff(), review.getSupervisesStaff(),
				review.getEmployeeId(), review.getReviewDate(), review.getPeriod(), review.getReviewerName());
	}

	/** 
	 * @see com.app.attendance.dao.EmployeeDao#getPerformanceReviewsByManager(java.lang.String)
	 */
	@Override
	public List<PerformanceReview> getPerformanceReviewsByManager(String idManager) {
		List<PerformanceReview> list = jdbcTemplate.query(EmployeeDao.QUERY_GET_PERFORMANCE_REVIEWS_FOR_MANAGER, new BeanPropertyRowMapper(PerformanceReview.class), idManager);
        return list;
	}

	/** 
	 * @see com.app.attendance.dao.EmployeeDao#getEmployeeById(java.lang.String)
	 */
	@Override
	public Employee getEmployeeById(String id) {
		try {
			Employee employee = this.jdbcTemplate.queryForObject(EmployeeDao.QUERY_GET_EMPLOYEE_BY_ID, new EmployeeMapper(),
					id);
			return employee;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	/** 
	 * @see com.app.attendance.dao.EmployeeDao#updatePerformanceReview(com.app.attendance.model.PerformanceReview)
	 */
	@Override
	public void updatePerformanceReview(PerformanceReview review) {
		this.jdbcTemplate.update(EmployeeDao.QUERY_UPDATE_PERFORMANCE_REVIEW, review.getPunctualSupervisor(), review.getPunctualComments(), 
				review.getInformsLatenessSupervisor(), review.getInformsLatenessComments(), review.getPersonalAssignmentsSupervisor(),
				review.getPersonalAssignmentsComments(), review.getListenInstructionsSupervisor(), review.getListenInstructionsComments(),
				review.getFollowsInstructionsSupervisor(), review.getFollowsInstructionsComments(), review.getWillingToAssistSupervisor(),
				review.getWillingToAssistComments(), review.getWillingToAcceptSupervisor(), review.getWillingToAcceptComments(),
				review.getLiquidatesFinantialSupervisor(), review.getLiquidatesFinantialComments(), review.getFinantialRequestsTimelySupervisor(),
				review.getFinantialRequestsTimelyComments(), review.getPrudentFinancesSupervisor(), review.getPrudentFinancesComments(),
				review.getInitiativeSupervisor(), review.getInitiativeComments(), review.getResolvesChallengesSupervisor(), review.getResolvesChallengesComments(),
				review.getQualityProductsSupervisor(), review.getQualityProductsComments(), review.getTimelyProductsSupervisor(), review.getTimelyProductsComments(),
				review.getIntegritySupervisor(), review.getIntegrityComments(), review.getRespectSupervisor(), review.getRespectComments(),
				review.getOralCommunicationSupervisor(), review.getOralCommunicationComments(), review.getWrittenCommunicationSupervisor(),
				review.getWrittenCommunicationComments(), review.getErrorResponsibilitySupervisor(), review.getErrorResponsibilityComments(),
				review.getCommitmentSupervisor(), review.getCommitmentComments(), review.getSupervisesSupervisor(), review.getSupervisesComments(),
				review.getScore(), review.getPercentage(), review.getRank(), review.getConclusions(), review.getId()
				);
	}

	/** 
	 * @see com.app.attendance.dao.EmployeeDao#getPerformanceReviewsByHR(java.lang.String)
	 */
	@Override
	public List<PerformanceReview> getPerformanceReviewsByHR() {
		List<PerformanceReview> list = jdbcTemplate.query(EmployeeDao.QUERY_GET_PERFORMANCE_REVIEWS_FOR_HR, new BeanPropertyRowMapper(PerformanceReview.class));
        return list;
	}

	@Override
	public void saveSelfService(EmployeeSS ess) {
		this.jdbcTemplate.update(EmployeeDao.QUERY_INSERT_ESS, ess.getId(),
				ess.getAge(), ess.getSex(), ess.getPensionNumber(), ess.getPosition(), ess.getTeamMembers(),
				ess.getPhoneNumber(), ess.getEmail(), ess.getEmergencyContact(), ess.getNok(), ess.getTaxidNumber(),
				ess.getExperienceYears(), ess.getSalaryLevel(), ess.getBirthday(), ess.getCvUrl(), ess.getBio(),
				ess.getSupervisor(), ess.getEd(),ess.getJobDescriptionUrl(), ess.getIdNumber());

	}


	@Override
	public void updateSelfService(EmployeeSS ess) {
		this.jdbcTemplate.update(EmployeeDao.QUERY_UPDATE_EMPLOYEE_ESS, 
				ess.getAge(), ess.getSex(), ess.getPensionNumber(), ess.getPosition(), ess.getTeamMembers(),
				ess.getPhoneNumber(), ess.getEmail(), ess.getEmergencyContact(), ess.getNok(), ess.getTaxidNumber(),
				ess.getExperienceYears(), ess.getSalaryLevel(), ess.getBirthday(), ess.getCvUrl(), ess.getBio(),
				ess.getSupervisor(), ess.getEd(), ess.getJobDescriptionUrl(), ess.getIdNumber(), ess.getId());

	}

	/** 
	 * @see com.app.attendance.dao.EmployeeDao#getSelfService(java.lang.String)
	 */
	@Override
	public EmployeeSS getSelfService(String employeeId) {
		try {
			EmployeeSS employeeSS = (EmployeeSS) this.jdbcTemplate.queryForObject(EmployeeDao.QUERY_GET_ESS, new BeanPropertyRowMapper(EmployeeSS.class),
					employeeId);
			return employeeSS;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public List<EmployeeSS> getListEmployeeSS() {
		List<EmployeeSS> list = jdbcTemplate.query(EmployeeDao.QUERY_GET_ALL_ESS, new BeanPropertyRowMapper(EmployeeSS.class));
        return list;
	}


}
