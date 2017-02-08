/**
 * ReportsServiceImpl.java Created: Nov 12, 2016 JavierAngelH
 */

package com.app.attendance.service;

import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.app.attendance.model.Employee;
import com.app.attendance.utils.Utilities;
import com.vaadin.server.StreamResource;
import com.vaadin.ui.Embedded;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.constant.PageType;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * ReportsServiceImpl -
 *
 */
@Service
public class ReportsServiceImpl implements ReportsService {

	/**
	 * @see com.app.attendance.service.ReportsService#createTimesheetDataSource(java.util.List,
	 *      int)
	 */
	@Override
	public JRDataSource createTimesheetDataSource(List<Employee> employeeList, int days) {
		StringBuilder columns = new StringBuilder();
		columns.append("name");
		columns.append(",");
		columns.append("projectName");

		for (Integer i = 1; i <= days; i++) {
			columns.append(",");
			columns.append(i.toString());

		}

		DRDataSource dataSource = new DRDataSource(columns.toString().split(","));

		Date currentDate = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currentDate);

		for (Employee employee : employeeList) {
			StringBuilder columnsEmployee = new StringBuilder();

			columnsEmployee.append(employee.getFirstname());
			columnsEmployee.append(",");
			columnsEmployee.append(employee.getProjectName());

			List<String> hoursList = employee.getHoursArray();
			List<String> workedDaysList = employee.getDaysArray();
			for (Integer i = 1; i <= days; i++) {
				String value = "0";

				if (Utilities.isWeekend(i, calendar.get(Calendar.MONTH), calendar.get(Calendar.YEAR))) {
					value = "W";
				} else {
					for (int j = 0; j < workedDaysList.size(); j++) {
						if (workedDaysList.get(j).equals(i.toString())) {
							value = hoursList.get(j);
						}

					}
				}
				columnsEmployee.append(",");
				columnsEmployee.append(value);

			}
			dataSource.add(columnsEmployee.toString().split(","));
		}

		return dataSource;
	}

	/**
	 * @see com.app.attendance.service.ReportsService#createDataSource(java.util.List)
	 */
	@Override
	public JRDataSource createDataSource(List<Employee> employeeList) {
		StringBuilder columns = new StringBuilder();
		columns.append("name");
		columns.append(",");
		columns.append("department");
		columns.append(",");
		columns.append("startDate");
		columns.append(",");
		columns.append("endDate");

		DRDataSource dataSource = new DRDataSource(columns.toString().split(","));

		for (Employee employee : employeeList) {
			StringBuilder columnsEmployee = new StringBuilder();

			columnsEmployee.append(employee.getFirstname());
			columnsEmployee.append(",");
			columnsEmployee.append(employee.getDepartment());
			columnsEmployee.append(",");
			columnsEmployee.append(employee.getStartDate());
			columnsEmployee.append(",");
			columnsEmployee.append(employee.getEndDate());

			dataSource.add(columnsEmployee.toString().split(","));

		}

		return dataSource;
	}

	/**
	 * @see com.app.attendance.service.ReportsService#buildTimesheetReport(java.lang.Integer,
	 *      java.util.List, java.lang.String, java.lang.String)
	 */
	@Override
	public Embedded buildTimesheetReport(Integer days, List<Employee> employeeList, String month, String year) {
		Embedded object = new Embedded();
		StreamResource.StreamSource source = new StreamResource.StreamSource() {
			private static final long serialVersionUID = 1L;

			@Override
			public InputStream getStream() {
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				StyleBuilder boldStyle = DynamicReports.stl.style().bold();
				StyleBuilder boldCenteredStyle = DynamicReports.stl.style(boldStyle)
						.setHorizontalTextAlignment(HorizontalTextAlignment.CENTER);
				StyleBuilder columnTitleStyle = DynamicReports.stl.style().bold()
						.setHorizontalTextAlignment(HorizontalTextAlignment.CENTER)
						.setBorder(DynamicReports.stl.pen1Point()).setBackgroundColor(Color.LIGHT_GRAY);

				try {
					JasperReportBuilder report = DynamicReports.report(); // create
																			// new
																			// report
					// design
					report.setColumnTitleStyle(columnTitleStyle);
					report.setPageFormat(PageType.A4, PageOrientation.LANDSCAPE);
					report.setHighlightDetailEvenRows(true);

					report.addTitle(DynamicReports.cmp
							.image("C:/Users/Virgilio Melo/Documents/upwork timesheet/Attendant/web/header.jpg"));
					/** change for your logo **/
					report.addPageHeader(
							DynamicReports.cmp.text("Monthly Time Sheet / Effort Report " + month + " " + year)
									.setStyle(boldCenteredStyle.setBottomPadding(25)));
					report.addPageFooter(DynamicReports.cmp.pageXofY().setStyle(boldCenteredStyle));
					report.addColumn(DynamicReports.col.column("Name", "name", DynamicReports.type.stringType())
							.setStyle(DynamicReports.stl.style().setBorder(DynamicReports.stl.penThin())));
					report.addColumn(
							DynamicReports.col.column("Project", "projectName", DynamicReports.type.stringType())
									.setStyle(DynamicReports.stl.style().setBorder(DynamicReports.stl.penThin())));

					for (Integer i = 1; i <= days; i++) {
						report.addColumn(
								DynamicReports.col.column(i.toString(), i.toString(), DynamicReports.type.stringType())
										.setFixedWidth(20).setHorizontalTextAlignment(HorizontalTextAlignment.CENTER)
										.setStyle(DynamicReports.stl.style().setBorder(DynamicReports.stl.penThin())));
					}
					report.setDataSource(ReportsServiceImpl.this.createTimesheetDataSource(employeeList, days));

					report.toPdf(outputStream);
				} catch (DRException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return new ByteArrayInputStream(outputStream.toByteArray());
			}

		};
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String filename = "timesheetReport" + df.format(new Date());
		System.out.println(source.getStream().toString());
		StreamResource resource = new StreamResource(source, filename);
		resource.setCacheTime(0);
		resource.setMIMEType("application/pdf");
		object = new Embedded();
		object.setSizeFull();
		object.setType(Embedded.TYPE_BROWSER);
		object.setSource(resource);

		return object;
	}

	/**
	 * @see com.app.attendance.service.ReportsService#buildLeavesReport(java.util.List,
	 *      java.lang.String, java.lang.String)
	 */
	@Override
	public Embedded buildLeavesReport(List<Employee> employeeList, String month, String year) {
		Embedded object = new Embedded();
		StreamResource.StreamSource source = new StreamResource.StreamSource() {
			private static final long serialVersionUID = 1L;

			@Override
			public InputStream getStream() {
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				StyleBuilder boldStyle = DynamicReports.stl.style().bold();
				StyleBuilder boldCenteredStyle = DynamicReports.stl.style(boldStyle)
						.setHorizontalTextAlignment(HorizontalTextAlignment.CENTER);
				StyleBuilder columnTitleStyle = DynamicReports.stl.style().bold()
						.setHorizontalTextAlignment(HorizontalTextAlignment.CENTER)
						.setBorder(DynamicReports.stl.pen1Point()).setBackgroundColor(Color.LIGHT_GRAY);

				try {
					JasperReportBuilder report = DynamicReports.report(); // create
																			// new
																			// report
					report.setColumnTitleStyle(columnTitleStyle);
					report.setPageFormat(PageType.A4, PageOrientation.LANDSCAPE);
					report.setHighlightDetailEvenRows(true);
					/** change for your logo **/
					report.addTitle(DynamicReports.cmp
							.image("C:/Users/Virgilio Melo/Documents/upwork timesheet/Attendant/web/header.jpg"));
					report.addPageHeader(DynamicReports.cmp.text("Leaves Report " + month + " " + year)
							.setStyle(boldCenteredStyle.setBottomPadding(25)));
					report.addPageFooter(DynamicReports.cmp.pageXofY().setStyle(boldCenteredStyle));
					report.addColumn(DynamicReports.col.column("Name", "name", DynamicReports.type.stringType())
							.setStyle(DynamicReports.stl.style().setBorder(DynamicReports.stl.penThin())
									.setHorizontalTextAlignment(HorizontalTextAlignment.CENTER)));
					report.addColumn(
							DynamicReports.col.column("Department", "department", DynamicReports.type.stringType())
									.setStyle(DynamicReports.stl.style().setBorder(DynamicReports.stl.penThin())
											.setHorizontalTextAlignment(HorizontalTextAlignment.CENTER)));
					report.addColumn(
							DynamicReports.col.column("Start Date", "startDate", DynamicReports.type.stringType())
									.setStyle(DynamicReports.stl.style().setBorder(DynamicReports.stl.penThin())
											.setHorizontalTextAlignment(HorizontalTextAlignment.CENTER)));
					report.addColumn(DynamicReports.col.column("End Date", "endDate", DynamicReports.type.stringType())
							.setStyle(DynamicReports.stl.style().setBorder(DynamicReports.stl.penThin())
									.setHorizontalTextAlignment(HorizontalTextAlignment.CENTER)));

					report.setDataSource(ReportsServiceImpl.this.createDataSource(employeeList));

					report.toPdf(outputStream);
				} catch (DRException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return new ByteArrayInputStream(outputStream.toByteArray());
			}

		};
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String filename = "timesheetReport" + df.format(new Date());
		System.out.println(source.getStream().toString());
		StreamResource resource = new StreamResource(source, filename);
		resource.setCacheTime(0);
		resource.setMIMEType("application/pdf");
		object = new Embedded();
		object.setSizeFull();
		object.setType(Embedded.TYPE_BROWSER);
		object.setSource(resource);

		return object;
	}

}
