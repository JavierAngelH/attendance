/**
 * EmployeeServiceImpl.java Created: Nov 7, 2016 JavierAngelH
 */

package com.app.attendance.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.attendance.dao.EmployeeDao;
import com.app.attendance.model.Bean;
import com.app.attendance.model.Department;
import com.app.attendance.model.Employee;
import com.app.attendance.model.LeaveApplication;
import com.app.attendance.model.Performance;
import com.app.attendance.model.TerminationForm;
import com.app.attendance.utils.Utilities;
import com.vaadin.server.VaadinSession;

/**
 * EmployeeServiceImpl -
 *
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeDao employeeDao;

	
	@Override
	public Boolean verifyLogin(String username, String password) {

		Employee admin = this.employeeDao.getAdmin(username, password);
		if (admin != null) {
			admin.setRole("ADMIN");
			VaadinSession.getCurrent().setAttribute("employee", admin);
			return true;
		}
		Employee employee = this.employeeDao.getEmployee(username, password);
		if (employee == null) {
			return false;
		}

		VaadinSession.getCurrent().setAttribute("employee", employee);
		return true;
	}

	/**
	 * @see com.app.attendance.service.EmployeeService#workedHoursByEmployees(java.util.Date,
	 *      java.lang.String)
	 */
	@Override
	public List<Employee> workedHoursByEmployees(Date month, String managerId) {
		java.sql.Date sqlDate = new java.sql.Date(month.getTime());

		return this.employeeDao.workedHoursPerEmployee(sqlDate, managerId);
	}

	/**
	 * @see com.app.attendance.service.EmployeeService#listOfDays()
	 */
	@Override
	public List<Integer> listOfDays() {
		List<Integer> list = new ArrayList<>();
		Integer top = this.employeeDao.getDaysOfMonth();

		for (int i = 1; i <= top; i++) {
			list.add(i);
		}

		return list;
	}

	/**
	 * @see com.app.attendance.service.EmployeeService#approveTimesheet(java.util.Date,
	 *      java.lang.String)
	 */
	@Override
	public void approveTimesheet(Date month, String employeeId) {
		java.sql.Date sqlDate = new java.sql.Date(month.getTime());

		this.employeeDao.updateStatus("A", sqlDate, employeeId);

	}

	/**
	 * @see com.app.attendance.service.EmployeeService#declineTimesheet(java.util.Date,
	 *      java.lang.String)
	 */
	@Override
	public void declineTimesheet(Date month, String employeeId) {
		java.sql.Date sqlDate = new java.sql.Date(month.getTime());

		this.employeeDao.updateStatus("D", sqlDate, employeeId);
	}

	/**
	 * @see com.app.attendance.service.EmployeeService#approveAll(java.util.Date,
	 *      java.util.List)
	 */
	@Override
	public void approveAll(Date month, List<Employee> employeeList) {
		for (Employee employee : employeeList) {
			this.approveTimesheet(month, employee.getId());
		}

	}

	/**
	 * @see com.app.attendance.service.EmployeeService#leaveRequestsByManager(java.lang.String)
	 */
	@Override
	public List<Employee> leaveRequestsByManager(String managerId) {
		return this.employeeDao.getPendingLeaveRequests(managerId);
	}


	@Override
	public void approveRequestByManager(Date startDate, Date endDate, String employeeId) {
		this.employeeDao.updateLeaveRequest("A", employeeId);

		String text = "Your leave for " + Utilities.dateFormat.format(startDate) + " until "
				+ Utilities.dateFormat.format(endDate) + " has been approved and sent to HR.";

		this.employeeDao.saveResponseMessage(employeeId, text);
	}

	
	@Override
	public void declineRequestByManager(Date startDate, Date endDate, String employeeId) {
		this.employeeDao.updateLeaveRequest("D", employeeId);

		String text = "Sorry, your leave for " + Utilities.dateFormat.format(startDate) + " until "
				+ Utilities.dateFormat.format(endDate) + " has been declined.";

		this.employeeDao.saveResponseMessage(employeeId, text);

	}

	
	@Override
	public void approveAllRequests(List<Employee> employeeList) {
		for (Employee employee : employeeList) {
			this.approveRequestByManager(employee.getStartDate(), employee.getEndDate(), employee.getId());
		}
	}

	/**
	 * @see com.app.attendance.service.EmployeeService#absentEmployees(java.util.Date)
	 */
	@Override
	public List<Employee> absentEmployees(Date month) {
		java.sql.Date sqlDate = new java.sql.Date(month.getTime());

		return this.employeeDao.getAbsentEmployees(sqlDate);
	}

	/**
	 * @see com.app.attendance.service.EmployeeService#leaveRequestsByHR()
	 */
	@Override
	public List<Employee> leaveRequestsByHR() {

		return this.employeeDao.getHRLeaveRequests();
	}

	/**
	 * @see com.app.attendance.service.EmployeeService#approveRequestByHR(java.util.Date,
	 *      java.util.Date, java.lang.String)
	 */
	@Override
	public void approveRequestByHR(Date startDate, Date endDate, String employeeId) {
		this.employeeDao.processRequestByHR("P", employeeId);

		String text = "Your leave for " + Utilities.dateFormat.format(startDate) + " until "
				+ Utilities.dateFormat.format(endDate)
				+ " has been approved. Please confirm your resumption on your return date. Thank you.";

		this.employeeDao.saveResponseMessage(employeeId, text);
	}

	/**
	 * @see com.app.attendance.service.EmployeeService#declineRequestbyHR(java.util.Date,
	 *      java.util.Date, java.lang.String)
	 */
	@Override
	public void declineRequestbyHR(Date startDate, Date endDate, String employeeId) {
		this.employeeDao.processRequestByHR("D", employeeId);

		String text = "Your leave for " + Utilities.dateFormat.format(startDate) + " until "
				+ Utilities.dateFormat.format(endDate)
				+ " has not been approved contact your supervisor for further details.";

		this.employeeDao.saveResponseMessage(employeeId, text);
	}

	/**
	 * @see com.app.attendance.service.EmployeeService#departmentList()
	 */
	@Override
	public List<Department> departmentList() {
		return this.employeeDao.getDepartmentList();
	}

	/**
	 * @see com.app.attendance.service.EmployeeService#locationList()
	 */
	@Override
	public List<String> locationList() {
		return this.employeeDao.getLocationList();
	}

	/**
	 * @see com.app.attendance.service.EmployeeService#HRTimesheet(java.util.Date)
	 */
	@Override
	public List<Employee> HRTimesheet(Date month) {
		java.sql.Date sqlDate = new java.sql.Date(month.getTime());

		return this.employeeDao.getHRTimesheets(sqlDate);
	}

	/**
	 * @see com.app.attendance.service.EmployeeService#getMonthName()
	 */
	@Override
	public String getMonthName() {
		return this.employeeDao.getMonthName();
	}

	/**
	 * @see com.app.attendance.service.EmployeeService#getYear()
	 */
	@Override
	public String getYear() {
		return this.employeeDao.getYear();
	}

	/**
	 * @see com.app.attendance.service.EmployeeService#codesList()
	 */
	@Override
	public List<Bean> codesList() {
		return this.employeeDao.getCodeList();
	}

	/**
	 * @see com.app.attendance.service.EmployeeService#managerList()
	 */
	@Override
	public List<Employee> managerList() {
		return this.employeeDao.getManagers();
	}

	/**
	 * @see com.app.attendance.service.EmployeeService#statusList()
	 */
	@Override
	public List<String> statusList() {
		List<String> list = new ArrayList<>();
		list.add("Permanent");
		list.add("Contract");
		return list;
	}

	/**
	 * @see com.app.attendance.service.EmployeeService#roleList()
	 */
	@Override
	public List<String> roleList() {
		List<String> list = new ArrayList<>();
		list.add("Staff");
		list.add("Manager");
		list.add("HR");
		list.add("Admin");

		return list;
	}

	/**
	 * @see com.app.attendance.service.EmployeeService#projectsList()
	 */
	@Override
	public List<Bean> projectsList() {
		return this.employeeDao.getProjectList();
	}

	/**
	 * @see com.app.attendance.service.EmployeeService#insertEmployee(com.app.attendance.model.Employee)
	 */
	@Override
	public void insertEmployee(Employee employee) {
		this.employeeDao.insertEmployee(employee);
	}

	/**
	 * @see com.app.attendance.service.EmployeeService#getEmployeeList()
	 */
	@Override
	public List<Employee> getEmployeeList() {
		return this.employeeDao.getEmployeeList();
	}

	/**
	 * @see com.app.attendance.service.EmployeeService#updateEmployee(com.app.attendance.model.Employee)
	 */
	@Override
	public void updateEmployee(Employee employee) {
		this.employeeDao.updateEmployee(employee);

	}

	/**
	 * @see com.app.attendance.service.EmployeeService#removeEmployee(java.lang.String)
	 */
	@Override
	public void removeEmployee(String id) {
		this.employeeDao.removeEmployee(id);

	}

	/**
	 * @see com.app.attendance.service.EmployeeService#insertDepartment(com.app.attendance.model.Department)
	 */
	@Override
	public void insertDepartment(Department department) {
		this.employeeDao.insertDepartment(department);

	}

	/**
	 * @see com.app.attendance.service.EmployeeService#updateDepartment(com.app.attendance.model.Department)
	 */
	@Override
	public void updateDepartment(Department department) {
		this.employeeDao.updateDepartment(department);

	}

	/**
	 * @see com.app.attendance.service.EmployeeService#removeDepartment(java.lang.String)
	 */
	@Override
	public void removeDepartment(String id) {
		this.employeeDao.removeDepartment(id);
	}

	/**
	 * @see com.app.attendance.service.EmployeeService#funderList()
	 */
	@Override
	public List<Bean> funderList() {
		return this.employeeDao.getFunderList();
	}


	@Override
	public void saveTimesheet(Date startDate, Date endDate, String idEmployee, String totalHours, String idsProject,
			Integer idFunder) {
		Calendar cal = Calendar.getInstance();
		Calendar calStart = Calendar.getInstance();
		Calendar calEnd = Calendar.getInstance();
		calStart.setTime(startDate);
		calEnd.setTime(endDate);
		Integer startDay = calStart.get(Calendar.DAY_OF_YEAR);
		Integer endDay = calEnd.get(Calendar.DAY_OF_YEAR);
		String projectsArray[] = idsProject.split(",");

		try {
			BigDecimal hoursInt = BigDecimal.valueOf(Double.valueOf(totalHours));
			hoursInt = hoursInt.divide(BigDecimal.valueOf(projectsArray.length), 1, RoundingMode.CEILING);
			totalHours = hoursInt.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (String idProject : projectsArray) {
			int projectId = Integer.parseInt(idProject);
			for (int day = startDay; day <= endDay; day++) {
				cal.set(Calendar.DAY_OF_YEAR, day);
				Integer id = this.employeeDao.timesheetRecordId(cal.getTime(), idEmployee, projectId);
				if (id == null) {
					this.employeeDao.insertIntoTimeSheet(idEmployee, cal.getTime(), totalHours, projectId, idFunder);
				} else {
					this.employeeDao.updateTimesheetEntry(totalHours, projectId, idFunder, id);
				}
			}
		}

	}

	/**
	 * @see com.app.attendance.service.EmployeeService#getEmployeeTimesheet(java.util.Date,
	 *      java.lang.String)
	 */
	@Override
	public List<Employee> getEmployeeTimesheet(Date month, String employeeId) {
		java.sql.Date sqlDate = new java.sql.Date(month.getTime());

		return this.employeeDao.workedHours(sqlDate, employeeId);
	}

	/**
	 * @see com.app.attendance.service.EmployeeService#getEmployeetMessages(java.lang.String)
	 */
	@Override
	public List<String> getEmployeetMessages(String employeeId) {
		List<String> list = this.employeeDao.getEmployeeMessages(employeeId);
		this.employeeDao.removeMessages(employeeId);
		return list;
	}

	/**
	 * @see com.app.attendance.service.EmployeeService#onVacation(java.lang.String)
	 */
	@Override
	public Boolean onVacation(String employeeId) {
		Employee employee = this.employeeDao.verifyLeaveDates(employeeId);
		Date currentDate = new Date();
		if (employee.getStartDate() != null) {
			if (employee.getStartDate().before(currentDate)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @see com.app.attendance.service.EmployeeService#updateVacationStatus(java.lang.String)
	 */
	@Override
	public void updateVacationStatus(String employeeId) {
		this.employeeDao.deletePendingVacation(employeeId);
		this.employeeDao.updateUsedVacation(employeeId);
	}

	/** 
	 * @see com.app.attendance.service.EmployeeService#submitLeaveApplication(java.lang.String, java.lang.String, java.lang.String, int, java.lang.String)
	 */
	@Override
	public void submitLeaveApplication(String employeeId, String type, String explanation, int days,
			String backstopping) throws Exception {
		int balance = 26;
	LeaveApplication application = employeeDao.getLeaveApplicationByEmployee(employeeId);
		if(balance - days < 0)
			throw new Exception ("Number of days exceeds the remaining balance");
	
		if(application!=null){
			balance = application.getBalance();
			if(balance - days < 0)
				throw new Exception ("Number of days exceeds the remaining balance");
		
			employeeDao.updateLeaveApplication(application.getId(), type, explanation, balance - days, backstopping);

			
		}else{
			employeeDao.insertLeaveApplication(employeeId, type, explanation, balance - days, backstopping);
		}
	}

	/** 
	 * @see com.app.attendance.service.EmployeeService#getLeaveApplications()
	 */
	@Override
	public List<LeaveApplication> getLeaveApplications() {
		return employeeDao.getLeaveApplications();
	}

	/** 
	 * @see com.app.attendance.service.EmployeeService#submitTerminationForm(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void submitTerminationForm(String employeeId, String leavingReason, String possibleReturn,
			String recommendation, String managementReason, String suggestions, String comments, String rehire, String terminationReason) {
	employeeDao.insertTerminationForm(employeeId, leavingReason, possibleReturn, recommendation, managementReason, suggestions, comments, rehire, terminationReason);
	}

	/** 
	 * @see com.app.attendance.service.EmployeeService#getTerminationForms()
	 */
	@Override
	public List<TerminationForm> getTerminationForms() {
		return employeeDao.getTerminationForms();
	}

	/** 
	 * @see com.app.attendance.service.EmployeeService#saveStaffPerformance(java.lang.String, com.app.attendance.model.Performance)
	 */
	@Override
	public void saveStaffPerformance(String employeeId, Performance performance) {
	employeeDao.insertEmployeePerformance(performance, employeeId);
	}

	/** 
	 * @see com.app.attendance.service.EmployeeService#getEmployeePerformancesByManager(java.lang.String)
	 */
	@Override
	public List<Performance> getEmployeePerformancesByManager(String managerId) {

		return employeeDao.getPerformanceByManager(managerId);
	}

	/** 
	 * @see com.app.attendance.service.EmployeeService#updatePerformance(com.app.attendance.model.Performance)
	 */
	@Override
	public void updatePerformance(Performance performance) {
		employeeDao.updatePerformance(performance);
	}

	/** 
	 * @see com.app.attendance.service.EmployeeService#getEmployeePerformancesByHR()
	 */
	@Override
	public List<Performance> getEmployeePerformancesByHR() {
		return employeeDao.getPerformanceByHR();
	}



}
