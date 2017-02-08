/**
 * EmployeeDao.java Created: Nov 7, 2016 JavierAngelH
 */

package com.app.attendance.dao;

import java.sql.Date;
import java.util.List;

import com.app.attendance.model.Bean;
import com.app.attendance.model.Department;
import com.app.attendance.model.Employee;
import com.app.attendance.model.LeaveApplication;
import com.app.attendance.model.Performance;
import com.app.attendance.model.TerminationForm;

/**
 * EmployeeDao -
 *
 */
public interface EmployeeDao {

	String QUERY_GET_ADMIN = "SELECT * FROM admin where username = ? and password = ?";

	String QUERY_GET_EMPLOYEE = "SELECT E.ID, E.FIRSTNAME, E.LASTNAME, E.USERNAME, E.PASSWORD, E.LOCATION, E.DEPARTMENT, "
			+ "E.EMPDATE, E.EMPLOYMENT_STATUS, E.ROLE, E.WORKED_HOURS, E.PROJECT_IDS, E.ID_FUNDER, E.PICTURE, CONCAT(D.firstname, ' ', D.lastname) as manager FROM EMPLOYEE E LEFT JOIN EMPLOYEE D ON  D.id = E.manager where UPPER(E.USERNAME) = UPPER(?) AND E.PASSWORD = ?";

	String QUERY_GET_WORKED_HOURS_PER_EMPLOYEE = "SELECT D.id, CONCAT(D.firstname, ' ', D.lastname) as name, GROUP_CONCAT(day(A.date) ORDER BY date) as day, "
			+ "GROUP_CONCAT(A.total_hours ORDER BY date) as hours, B.name as project, C.name as funder \n"
			+ "FROM time_sheet A, project B, funder C, employee D WHERE YEAR(date) = YEAR(?) AND MONTH(date) = MONTH(?) and \n"
			+ "A.id_project = B.id and A.id_funder = C.id and A.id_employee = D.id and D.manager = ? and A.status != 'A' group by name, project, funder ";

	String QUERY_GET_WORKED_HOURS = "SELECT D.id, CONCAT(D.firstname, ' ', D.lastname) as name, GROUP_CONCAT(day(A.date) ORDER BY date) as day, "
			+ "GROUP_CONCAT(A.total_hours ORDER BY date) as hours, B.name as project, C.name as funder \n"
			+ "FROM time_sheet A, project B, funder C, employee D WHERE YEAR(date) = YEAR(?) AND MONTH(date) = MONTH(?) and \n"
			+ "A.id_project = B.id and A.id_funder = C.id and A.id_employee = D.id and D.id = ? group by name, project, funder ";

	String QUERY_GET_NUMBER_OF_DAYS = "SELECT DAY(LAST_DAY(now())) as days";

	String QUERY_UPDATE_STATUS = "update time_sheet set status = ? where id_employee = ? and YEAR(date) = YEAR(?) AND MONTH(date) = MONTH(?)";

	String QUERY_GET_LEAVE_REQUESTS = "SELECT D.id, D.department, CONCAT(D.firstname, ' ', D.lastname) as name, min(A.date) as start_date,max(A.date) as end_date FROM time_sheet A, employee D WHERE "
			+ "A.id_employee = D.id and D.manager = ? and A.status = 'D' and A.total_hours = 'V' group by name";

	String QUERY_UPDATE_LEAVE_REQUEST = "update time_sheet set status = ? where id_employee = ? and status = 'D' and total_hours = 'V'";

	String QUERY_SAVE_LEAVE_RESPONSE_MESSAGE = "insert into message (employee_id, text) values(?, ?)";

	String QUERY_ABSENT_EMPLOYEES = "select emp.id, CONCAT(emp.firstname, ' ' ,emp.lastname) as name, "
			+ "emp.department, CONCAT(manager.firstname, ' ' , manager.lastname) as manager "
			+ "from employee emp, employee manager where emp.manager = manager.id AND "
			+ "emp.id NOT IN (select emp.id from employee emp, time_sheet sheet where month(sheet.date) = ? and year(sheet.date) = ?  and emp.id = sheet.id_employee)";

	String QUERY_HR_LEAVE_REQUESTS = "SELECT D.id, D.department, D.location, A.status, CONCAT(D.firstname, ' ', D.lastname) as name,\n"
			+ " min(A.date) as start_date,max(A.date) as end_date FROM time_sheet A, employee D WHERE\n"
			+ " A.id_employee = D.id and A.status != 'D' and A.total_hours = 'V' and YEAR(date) = YEAR(sysdate()) AND MONTH(date) = MONTH(sysdate()) group by name";

	String QUERY_HR_PROCESS_REQUEST = "update time_sheet set status = ? where id_employee = ? and status = 'A' and total_hours = 'V'";

	String QUERY_DEPARTMENT_LIST = "SELECT * FROM department";

	String QUERY_LOCATIONS_LIST = "select distinct(location) from employee";

	String QUERY_HR_TIMESHEETS = "SELECT D.id, CONCAT(D.firstname, ' ', D.lastname) as name, D.department, D.location, GROUP_CONCAT(day(A.date) ORDER BY date) as day, "
			+ "GROUP_CONCAT(A.total_hours ORDER BY date) as hours, B.name as project, C.name as funder "
			+ "FROM time_sheet A, project B, funder C, employee D WHERE YEAR(date) = YEAR(?) AND MONTH(date) = MONTH(?) and "
			+ "A.id_project = B.id and A.id_funder = C.id and A.id_employee = D.id and A.status != 'D' group by name, project, funder";

	String QUERY_MONTH = "SELECT monthname(NOW()) as month";

	String QUERY_YEAR = "SELECT year(NOW()) as year";

	String QUERY_CODE_LIST = "SELECT * FROM non_work_hours";

	String QUERY_GET_MANAGERS = "SELECT * FROM employee where UPPER(role) = 'MANAGER'";

	String QUERY_GET_PROJECTS = "SELECT * FROM project";

	String QUERY_INSERT_EMPLOYEE = "INSERT INTO `attendant_db`.`employee` (`id`, `firstname`, `lastname`,"
			+ "`username`, `password`, `location`, `department`, `manager`, `empdate`, `employment_status`, `role`,"
			+ "`picture`, `worked_hours`, `project_ids`, `id_funder`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	String QUERY_EMPLOYEE_LIST = "SELECT E.ID, E.FIRSTNAME, E.LASTNAME, E.USERNAME, E.PASSWORD, E.LOCATION, E.DEPARTMENT, E.EMPDATE, E.EMPLOYMENT_STATUS, E.ROLE, E.manager, E.PICTURE, E.WORKED_HOURS, E.PROJECT_IDS, E.ID_FUNDER FROM EMPLOYEE E";

	String QUERY_UPDATE_EMPLOYEE = "UPDATE EMPLOYEE SET firstname = ?, lastname = ?, username = ?, password = ?, location = ?, department = ?, manager = ?, empdate = ?, employment_status = ?, role = ?,"
			+ "picture = ?, worked_hours = ?, project_ids = ?, id_funder = ? where id = ?";

	String QUERY_REMOVE_EMPLOYEE = "DELETE FROM EMPLOYEE WHERE ID = ?";

	String QUERY_INSERT_DEPARTMENT = "INSERT INTO `attendant_db`.`department` (`id`,`name`,`managerid`,"
			+ "`description`) VALUES (?,?,?,?)";

	String QUERY_UPDATE_DEPARTMENT = "UPDATE `attendant_db`.`department` SET `name` = ?, "
			+ "`managerid` = ?, `description` = ? WHERE `id` = ?";

	String QUERY_REMOVE_DEPARTMENT = "DELETE FROM DEPARTMENT WHERE ID = ?";

	String QUERY_GET_FUNDERS = "SELECT * FROM FUNDER";

	String QUERY_FIND_FUNDERS = "SELECT * FROM FUNDER where ID = ?";

	String QUERY_TIMESHEET_DAY_ENTRY = "select id from time_sheet where DAY(date) = DAY(?)"
			+ " and MONTH(date) = MONTH(?) and YEAR(date) = YEAR(?) and id_employee = ? and id_project = ?";

	String INSERT_INTO_TIMESHEET = "insert into time_sheet values (null,?,?,?,?,?,null,'D')";

	String QUERY_UPDATE_TIMESHEET = "update time_sheet set total_hours = ?, id_project = ?, id_funder = ? where id = ?";

	String QUERY_GET_MESSAGES = "SELECT text FROM message where employee_id = ?";

	String QUERY_DELETE_MESSAGES = "delete FROM message where employee_id = ?";

	String QUERY_VERIFY_VACATION = "SELECT min(A.date) as start_date,max(A.date) as end_date FROM time_sheet A, employee D WHERE \n"
			+ "A.id_employee = D.id and A.id_employee = ? and A.status = 'P' and A.total_hours = 'V'";

	String QUERY_DELETE_VACATION = "delete from time_sheet where id_employee = ? and year(date) = year(sysdate()) and month(date) = month(sysdate()) and day(date) >= day(sysdate())";

	String QUERY_UPDATE_VACATION = "update time_sheet set status = 'U' where id_employee = ? and status = 'P' and total_hours = 'V'";

	String QUERY_INSERT_LEAVE_APPLICATION = "insert into leave_mgt values(null, ?, ?,?,?,?)";
	
	String QUERY_GET_LEAVE_APPLICATION_BY_EMPLOYEE = "select * from leave_mgt where employee_id = ?";

	String QUERY_UPDATE_LEAVE_APPLICATION = "update leave_mgt set type = ?, explanation = ?, backstoping = ?, balance = ? where id = ? ";

	String QUERY_GET_LEAVE_APPLICATIONS = "select CONCAT(D.firstname, ' ', D.lastname) as name, D.location, D.department, L.id, L.type, L.explanation, L.backstoping, L.balance, L.employee_id from leave_mgt L, employee D where D.id = L.employee_id";
		
	String QUERY_INSERT_TERMINATION_FORM = "insert into exit_form values(null, ?,?,?,?,?,?,?,?,?)";

	String QUERY_GET_TERMINATION_FORMS = "select CONCAT(D.firstname, ' ', D.lastname) as name,D.department, D.empdate, D.id, L.leaving_reason, L.possible_return, "
			+ "L.recommendation, L.management_reason, L.suggestions, L.comments, L.rehire, L.termination_reason from exit_form L, employee D where D.id = L.id_employee";
	
	String QUERY_SAVE_MESSAGE_FOR_HR = "insert into message (employee_id, text) values(?, ?)";

	
	String QUERY_CREATE_PERFORMANCE = "insert into performance (id_employee, objective_1, objective_2,"
			+ "objective_3, reason, creation_date) VALUES (?,?,?,?,?,?)";
	
	String QUERY_GET_PERFORMANCE_BY_MANAGER = "SELECT p1.`id`, p1.`id_employee`, p1.`objective_1` as objective1,"
			+ " p1.`objective_2` as objective2, p1.`objective_3` as objective3, p1.`manager_comment_obj_1` as managerComment1, CONCAT(emp.firstname, ' ', emp.lastname) "
			+ "as name, emp.location, emp.department,"
			+ " p1.`manager_comment_obj_2` as managerComment2, p1.`manager_comment_obj_3` as managerComment3, p1.`technical_score` as technicalScore,"
			+ " p1.`non_technical_score` as nonTechnicalScore, p1.`total_score` as totalScore, p1.`rating_category` as ratingCategory, "
			+ "p1.`supervisor_comment` as supervisorComment, p1.`reason` FROM performance p1, employee emp "
			+ "WHERE p1.`creation_date` = (SELECT MAX(p2.creation_date) FROM performance p2 "
			+ "WHERE p2.id_employee = p1.id_employee) and p1.id_employee = emp.id and emp.manager = ?";
	
	
	String QUERY_GET_PERFORMANCE_BY_HR = "SELECT p1.`id`, p1.`id_employee`, p1.`objective_1` as objective1,"
			+ " p1.`objective_2` as objective2, p1.`objective_3` as objective3, p1.`manager_comment_obj_1`"
			+ " as managerComment1, CONCAT(emp.firstname, ' ', emp.lastname) as name, emp.location, emp.department,"
			+ "p1.`manager_comment_obj_2` as managerComment2, p1.`manager_comment_obj_3` as managerComment3, "
			+ "p1.`technical_score` as technicalScore, p1.`non_technical_score` as nonTechnicalScore, "
			+ "p1.`total_score` as totalScore, p1.`rating_category` as ratingCategory, "
			+ "p1.`supervisor_comment` as supervisorComment, p1.`reason` FROM performance p1, employee emp "
			+ "WHERE p1.`creation_date` = (SELECT MAX(p2.creation_date) FROM performance p2 "
			+ "WHERE p2.id_employee = p1.id_employee) and p1.id_employee = emp.id and p1.approved = 1";
	
	String QUERY_APPROVE_PERFORMANCE_BY_MANAGER = "UPDATE performance SET manager_comment_obj_1 = ?, manager_comment_obj_2 = ?, "
			+ "manager_comment_obj_3 = ?, technical_score = ?, non_technical_score = ?, total_score = ?, rating_category = ?,"
			+ " supervisor_comment = ?, approved = ? WHERE ID = ?";
	
	void insertIntoTimeSheet(String idEmployee, java.util.Date date, String totalHours, Integer idProject,
			Integer idFunder);

	void updateTimesheetEntry(String totalHours, Integer idProject, Integer idFunder, Integer id);

	Employee getEmployee(String username, String password);

	List<Employee> workedHoursPerEmployee(Date date, String managerId);

	Integer getDaysOfMonth();

	void updateStatus(String status, Date date, String employeeId);

	List<Employee> getPendingLeaveRequests(String managerId);

	void updateLeaveRequest(String status, String employeeId);

	void saveResponseMessage(String employeeId, String text);
	
	void saveHRMessage(String text);


	List<Employee> getAbsentEmployees(Date date);

	List<Employee> getHRLeaveRequests();

	void processRequestByHR(String status, String employeeId);

	List<Department> getDepartmentList();

	List<String> getLocationList();

	List<Employee> getHRTimesheets(Date date);

	String getMonthName();

	String getYear();

	List<Bean> getCodeList();

	Employee getAdmin(String username, String password);

	List<Employee> getManagers();

	List<Bean> getProjectList();

	void insertEmployee(Employee employee);

	List<Employee> getEmployeeList();

	void updateEmployee(Employee employee);

	void removeEmployee(String id);

	void insertDepartment(Department department);

	void updateDepartment(Department department);

	void removeDepartment(String id);

	List<Bean> getFunderList();

	Integer timesheetRecordId(java.util.Date date, String idEmployee, Integer idProject);

	String getFunderName(Integer id);

	List<Employee> workedHours(Date date, String employeeId);

	List<String> getEmployeeMessages(String employeeId);

	void removeMessages(String employeeId);

	Employee verifyLeaveDates(String employeeId);

	void deletePendingVacation(String employeeId);

	void updateUsedVacation(String employeeId);
	
	void insertLeaveApplication(String employeeId, String type, String explanation, int balance,
			String backstopping);
	
	void insertTerminationForm(String employeeId, String leavingReason, String possibleReturn,
			String recommendation, String managementReason, String suggestions, String comments,
			String rehire, String terminationReason);
	
	LeaveApplication getLeaveApplicationByEmployee(String employeeId);
	
	void updateLeaveApplication(int applicationId, String type, String explanation, int balance,
			String backstopping);
	
	List<LeaveApplication> getLeaveApplications();

	List<TerminationForm> getTerminationForms();
	
	void insertEmployeePerformance(Performance performance, String employeeId);
	
	List<Performance> getPerformanceByManager(String idManager);
	
	List<Performance> getPerformanceByHR();
	
	void updatePerformance(Performance performance);

}
