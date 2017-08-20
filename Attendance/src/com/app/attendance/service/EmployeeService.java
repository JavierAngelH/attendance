/**
 * EmployeeService.java Created: Nov 7, 2016 JavierAngelH
 */

package com.app.attendance.service;

import java.util.Date;
import java.util.List;

import com.app.attendance.model.Bean;
import com.app.attendance.model.Department;
import com.app.attendance.model.Employee;
import com.app.attendance.model.EmployeeSS;
import com.app.attendance.model.LeaveApplication;
import com.app.attendance.model.Performance;
import com.app.attendance.model.PerformanceReview;
import com.app.attendance.model.TerminationForm;

/**
 * EmployeeService -
 *
 */
public interface EmployeeService {

	Boolean verifyLogin(String username, String password);

	List<Employee> workedHoursByEmployees(Date month, String managerId);

	List<Integer> listOfDays();
	
	List<Integer> listOfDays2Months();

	void approveTimesheet(Date month, String employeeId);

	void declineTimesheet(Date month, String employeeId);

	void approveAll(Date month, List<Employee> employeeList);

	List<Employee> leaveRequestsByManager(String managerId);

	void approveRequestByManager(Date startDate, Date endDate, String employeeId);

	void declineRequestByManager(Date startDate, Date endDate, String employeeId);

	void approveAllRequests(List<Employee> employeeList);

	List<Employee> absentEmployees(Date month);

	List<Employee> leaveRequestsByHR();

	void approveRequestByHR(Date startDate, Date endDate, String employeeId);

	void declineRequestbyHR(Date startDate, Date endDate, String employeeId);

	List<Department> departmentList();

	List<String> locationList();

	List<Employee> HRTimesheet(Date month);

	String getMonthName();

	String getYear();

	List<Bean> codesList();

	List<Employee> managerList();

	List<String> statusList();

	List<String> roleList();

	List<Bean> projectsList();

	void insertEmployee(Employee employee);

	List<Employee> getEmployeeList();

	void updateEmployee(Employee employee);

	void removeEmployee(String id);

	void insertDepartment(Department department);

	void updateDepartment(Department department);

	void removeDepartment(String id);

	List<Bean> funderList();

	void saveTimesheet(Date startDate, Date endDate, String idEmployee, String totalHours, Integer idProject,
			Integer idFunder);

	List<Employee> getEmployeeTimesheet(Date month, String employeeId);

	List<String> getEmployeetMessages(String employeeId);

	Boolean onVacation(String employeeId);

	void updateVacationStatus(String employeeId);
	
	void submitLeaveApplication(String employeeId, String type, String explanation, int days, String backstopping, Date startDate, Date endDate) throws Exception;

	void submitTerminationForm(String employeeId, String leavingReason, String possibleReturn,
			String recommendation, String managementReason, String suggestions, String comments, String rehire, String terminationReason, String managementPrevention,String satisfaction, String likeEmployment, String dislikeEmployment, String considerReapply, String keepContact,
			String phoneNumber);
	
	List<LeaveApplication> getLeaveApplications();
	
	List<TerminationForm> getTerminationForms();

	void saveStaffPerformance(String employeeId, Performance performance);
	
	List<Performance> getEmployeePerformancesByManager(String managerId);
	
	void updatePerformance(Performance performance);

	List<Performance> getEmployeePerformancesByHR();
	
	int getLeaveBalance(String idEmployee);
	
	void updateTerminationFormHR(int idTermination, String formalResignation, String handoverNote, String handoverProperties, String medicalCoverage,
			 String benefitsPaid, String minimumNotice, String finalPayment, String elegibleRehire);

	void savePerformanceReview(PerformanceReview review);
	
	void updatePerformanceReview(PerformanceReview review);

	List<PerformanceReview> getPerformanceReviewsByManager(String managerId);
	
	List<PerformanceReview> getPerformanceReviewsByHR();
	
	Employee getEmployee(String id);
	
	void insertEss(EmployeeSS ess);
	
	void updateEss(EmployeeSS ess);

	EmployeeSS getEss(String essId);
	
	List<EmployeeSS> getESSList();

}
