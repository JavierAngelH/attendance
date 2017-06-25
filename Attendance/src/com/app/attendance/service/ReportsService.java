/**
 * ReportsService.java Created: Nov 12, 2016 JavierAngelH
 */

package com.app.attendance.service;

import java.util.List;

import com.app.attendance.model.Employee;
import com.vaadin.ui.Embedded;

import net.sf.jasperreports.engine.JRDataSource;

/**
 * ReportsService -
 *
 */
public interface ReportsService {

	JRDataSource createTimesheetDataSource(List<Employee> employeeList, List<Integer> days);

	JRDataSource createDataSource(List<Employee> employeeList);

	Embedded buildTimesheetReport(List<Integer> days, List<Employee> employeeList, String month, String year);

	Embedded buildLeavesReport(List<Employee> employeeList, String month, String year);

}
