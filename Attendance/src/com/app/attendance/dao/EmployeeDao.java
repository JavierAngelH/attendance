/**
 * EmployeeDao.java Created: Nov 7, 2016 JavierAngelH
 */

package com.app.attendance.dao;

import java.sql.Date;
import java.util.List;

import com.app.attendance.model.Advert;
import com.app.attendance.model.Bean;
import com.app.attendance.model.Department;
import com.app.attendance.model.Employee;
import com.app.attendance.model.EmployeeSS;
import com.app.attendance.model.Interview;
import com.app.attendance.model.LeaveApplication;
import com.app.attendance.model.Orientation;
import com.app.attendance.model.Performance;
import com.app.attendance.model.PerformanceReview;
import com.app.attendance.model.Probation;
import com.app.attendance.model.TerminationForm;
import com.app.attendance.model.Volunteer;

/**
 * EmployeeDao -
 *
 */
public interface EmployeeDao {

	String QUERY_GET_ADMIN = "SELECT * FROM admin where username = ? and password = ?";

	String QUERY_GET_EMPLOYEE = "SELECT E.ID, E.FIRSTNAME, E.LASTNAME, E.USERNAME, E.PASSWORD, E.LOCATION, E.DEPARTMENT, "
			+ "E.EMPDATE, E.EMPLOYMENT_STATUS, E.ROLE, E.PICTURE, CONCAT(D.firstname, ' ', D.lastname) as manager FROM EMPLOYEE E LEFT JOIN EMPLOYEE D ON  D.id = E.manager where UPPER(E.USERNAME) = UPPER(?) AND E.PASSWORD = ?";

	
	String QUERY_HR_TIMESHEETS_2_MONTHS = "SELECT D.id, CONCAT(D.firstname, ' ', D.lastname) as name, D.department, D.location, GROUP_CONCAT(day(A.date) ORDER BY date) as day, "
			+ "GROUP_CONCAT(A.total_hours ORDER BY date) as hours, B.name as project, C.name as funder, D.manager FROM time_sheet A, project B, funder C, employee D WHERE "
			+ "((YEAR(date) = YEAR(DATE_SUB(?, INTERVAL 1 MONTH)) AND MONTH(date) = MONTH(DATE_SUB(?, INTERVAL 1 MONTH)) and  DAYOFMONTH(date) > 25 OR YEAR(date) = YEAR(?) "
			+ "AND MONTH(date) = MONTH(sysdate()))) and A.id_project = B.id and A.id_funder = C.id and A.id_employee = D.id and A.status != 'D' group by name, project, funder";	

	
	
	String QUERY_GET_WORKED_HOURS_PER_EMPLOYEE = "SELECT D.id, CONCAT(D.firstname, ' ', D.lastname) as name, GROUP_CONCAT(day(A.date) ORDER BY date) as day, "
			+ "GROUP_CONCAT(A.total_hours ORDER BY date) as hours, B.name as project, C.name as funder \n"
			+ "FROM time_sheet A, project B, funder C, employee D WHERE ((YEAR(date) = YEAR(DATE_SUB(?, INTERVAL 1 MONTH)) AND MONTH(date) = MONTH(DATE_SUB(?, INTERVAL 1 MONTH)) and  DAYOFMONTH(date) > 25 OR YEAR(date) = YEAR(?) "
			+ "AND MONTH(date) = MONTH(sysdate()))) and \n"
			+ "A.id_project = B.id and A.id_funder = C.id and A.id_employee = D.id and D.manager = ? and A.status != 'A' group by name, project, funder ";

	String QUERY_GET_WORKED_HOURS = "SELECT D.id, CONCAT(D.firstname, ' ', D.lastname) as name, GROUP_CONCAT(day(A.date) ORDER BY date) as day, "
			+ "GROUP_CONCAT(A.total_hours ORDER BY date) as hours, B.name as project, C.name as funder, A.id_project, A.id_funder \n"
			+ "FROM time_sheet A, project B, funder C, employee D WHERE YEAR(date) = YEAR(?) AND MONTH(date) = MONTH(?) and \n"
			+ "A.id_project = B.id and A.id_funder = C.id and A.id_employee = D.id and D.id = ? group by name, project, funder ";

	String QUERY_GET_NUMBER_OF_DAYS = "SELECT DAY(LAST_DAY(now())) as days";
	
	String QUERY_GET_NUMBER_OF_DAYS_LAST_MONTH = "SELECT DAY(LAST_DAY(DATE_SUB(now(), INTERVAL 1 MONTH))) as days";


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
			+ "GROUP_CONCAT(A.total_hours ORDER BY date) as hours, B.name as project, C.name as funder, D.signature, D.manager "
			+ "FROM time_sheet A, project B, funder C, employee D WHERE YEAR(date) = YEAR(?) AND MONTH(date) = MONTH(?) and "
			+ "A.id_project = B.id and A.id_funder = C.id and A.id_employee = D.id and A.status != 'D' group by name, project, funder";

	String QUERY_MONTH = "SELECT monthname(NOW()) as month";

	String QUERY_YEAR = "SELECT year(NOW()) as year";

	String QUERY_CODE_LIST = "SELECT * FROM non_work_hours";

	String QUERY_GET_MANAGERS = "SELECT * FROM employee where UPPER(role) = 'MANAGER'";

	String QUERY_GET_PROJECTS = "SELECT * FROM project";

	String QUERY_INSERT_EMPLOYEE = "INSERT INTO `attendant_db`.`employee` (`id`, `firstname`, `lastname`,"
			+ "`username`, `password`, `location`, `department`, `manager`, `empdate`, `employment_status`, `role`,"
			+ "`picture`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	String QUERY_EMPLOYEE_LIST = "SELECT E.ID, E.FIRSTNAME, E.LASTNAME, E.USERNAME, E.PASSWORD, E.LOCATION, E.DEPARTMENT, E.EMPDATE, E.EMPLOYMENT_STATUS, E.ROLE, E.manager, E.PICTURE FROM EMPLOYEE E";

	String QUERY_UPDATE_EMPLOYEE = "UPDATE EMPLOYEE SET firstname = ?, lastname = ?, username = ?, password = ?, location = ?, department = ?, manager = ?, empdate = ?, employment_status = ?, role = ?,"
			+ "picture = ? where id = ?";

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

	String QUERY_INSERT_LEAVE_APPLICATION = "insert into leave_mgt values(null, ?, ?,?,?,?, ?, ?)";
	
	String QUERY_GET_LEAVE_APPLICATION_BY_EMPLOYEE = "select * from leave_mgt where employee_id = ?";

	String QUERY_UPDATE_LEAVE_APPLICATION = "update leave_mgt set type = ?, explanation = ?, backstoping = ?, balance = ?, start_date = ?, end_date = ? where id = ? ";

	String QUERY_GET_LEAVE_APPLICATIONS = "select CONCAT(D.firstname, ' ', D.lastname) as name, D.location, D.department, L.id, L.type, L.explanation, L.backstoping, L.balance, L.start_date, L.end_date, L.employee_id from leave_mgt L, employee D where D.id = L.employee_id";
		
	String QUERY_INSERT_TERMINATION_FORM = "insert into exit_form(id,id_employee,leaving_reason,possible_return,recommendation,management_reason,suggestions,"
			+ "comments,rehire,termination_reason,management_prevention, satisfaction, like_employment, dislike_employment, consider_reapply, keep_contact, phone_number) "
			+ "values (null,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	String QUERY_GET_TERMINATION_FORMS = "select CONCAT(D.firstname, ' ', D.lastname) as name,D.department, D.empdate, D.id, L.leaving_reason, L.possible_return, "
			+ "L.recommendation, L.management_reason, L.suggestions, L.comments, L.rehire, L.termination_reason, L.formal_resignation, L.handover_note, "
			+ "L.handover_properties, L.medical_coverage, L.benefits_paid, L.minimum_notice, L.final_payment, L.elegible_rehire, "
			+ "L.hr_reviewed, L.id as id_termination, L.management_prevention, L.satisfaction, L.like_employment, L.dislike_employment, "
			+ "L.consider_reapply, L.keep_contact, L.phone_number from exit_form L, employee D where D.id = L.id_employee";
	
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
	
	String QUERY_UPDATE_TERMINATION_FORM = "UPDATE exit_form SET formal_resignation = ?, handover_note = ?, handover_properties = ?,"
			+ "medical_coverage = ?, benefits_paid = ?, minimum_notice = ?, final_payment = ?, elegible_rehire = ?, hr_reviewed = ? WHERE id = ?";
	
	
	String QUERY_INSERT_PERFORMANCE_REVIEW = "INSERT INTO `performance_review` (`punctual_staff`, `informs_lateness_staff`,"
			+ "`personal_assignments_staff`, `listen_instructions_staff`, `follows_instructions_staff`,`willing_to_assist_staff`,"
			+ "`willing_to_accept_staff`, `liquidates_finantial_staff`, `finantial_requests_timely_staff`, `prudent_finances_staff`,"
			+ "`initiative_staff`, `resolves_challenges_staff`, `quality_products_staff`, `timely_products_staff`, `integrity_staff`,"
			+ "`respect_staff`, `oral_communication_staff`,`written_communication_staff`,`error_responsibility_staff`,`commitment_staff`,"
			+ "`supervises_staff`,`employee_id`,`review_date`,`review_period`,`reviewer_name`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
			+ "?,?,?,?,?,?,?)";
	
	String QUERY_GET_PERFORMANCE_REVIEWS_FOR_MANAGER = "SELECT p1.`id`, p1.`employee_id` as employeeId, p1.`punctual_staff` as punctualStaff, "
			+ "p1.`informs_lateness_staff` as informsLatenessStaff, p1.`personal_assignments_staff` as personalAssignmentsStaff, "
			+ "p1.`listen_instructions_staff` as listenInstructionsStaff, p1.`follows_instructions_staff` as followsInstructionsStaff, "
			+ "p1.`willing_to_assist_staff` as willingToAssistStaff, p1.`willing_to_accept_staff` as willingToAcceptStaff, "
			+ "p1.`liquidates_finantial_staff` as liquidatesFinantialStaff, p1.`finantial_requests_timely_staff` as "
			+ "finantialRequestsTimelyStaff, p1.`prudent_finances_staff` as prudentFinancesStaff, p1.`initiative_staff` as initiativeStaff, "
			+ "p1.`resolves_challenges_staff` as resolvesChallengesStaff, p1.`quality_products_staff` as qualityProductsStaff, "
			+ "p1.`timely_products_staff` as timelyProductsStaff, p1.`integrity_staff` as integrityStaff, p1.`respect_staff` as "
			+ "respectStaff, p1.`oral_communication_staff` as oralCommunicationStaff, p1.`written_communication_staff` as "
			+ "writtenCommunicationStaff, p1.`error_responsibility_staff` as errorResponsibilityStaff, p1.`commitment_staff` as "
			+ "commitmentStaff, p1.`supervises_staff` as supervisesStaff, p1.`review_period` as period, p1.`review_period` as "
			+ "reviewPeriod, p1.`review_date` as reviewDate FROM performance_review p1, employee emp WHERE p1.`review_date` = (SELECT MAX(p2.review_date) FROM performance_review p2 "
			+ "WHERE p2.employee_id = p1.employee_id) and p1.employee_id = emp.id and emp.manager =?";
	
	String QUERY_GET_PERFORMANCE_REVIEWS_FOR_HR =  "SELECT p1.`id`, p1.`employee_id` as employeeId, p1.`punctual_staff` as punctualStaff, "
			+ "p1.`informs_lateness_staff` as informsLatenessStaff, p1.`personal_assignments_staff` as personalAssignmentsStaff, "
			+ "p1.`listen_instructions_staff` as listenInstructionsStaff, p1.`follows_instructions_staff` as followsInstructionsStaff, "
			+ "p1.`willing_to_assist_staff` as willingToAssistStaff, p1.`willing_to_accept_staff` as willingToAcceptStaff, "
			+ "p1.`liquidates_finantial_staff` as liquidatesFinantialStaff, p1.`finantial_requests_timely_staff` as finantialRequestsTimelyStaff, "
			+ "p1.`prudent_finances_staff` as prudentFinancesStaff, p1.`initiative_staff` as initiativeStaff, p1.`resolves_challenges_staff` as "
			+ "resolvesChallengesStaff, p1.`quality_products_staff` as qualityProductsStaff, p1.`timely_products_staff` as timelyProductsStaff, "
			+ "p1.`integrity_staff` as integrityStaff, p1.`respect_staff` as respectStaff, p1.`oral_communication_staff` as oralCommunicationStaff, "
			+ "p1.`written_communication_staff` as writtenCommunicationStaff, p1.`error_responsibility_staff` as errorResponsibilityStaff, "
			+ "p1.`commitment_staff` as commitmentStaff, p1.`supervises_staff` as supervisesStaff, p1.`punctual_supervisor` as punctualSupervisor, "
			+ "p1.`informs_lateness_supervisor` as informsLatenessSupervisor, p1.`personal_assignments_supervisor` as personalAssignmentsSupervisor, "
			+ "p1.`listen_instructions_supervisor` as listenInstructionsSupervisor, p1.`follows_instructions_supervisor` as "
			+ "followsInstructionsSupervisor, p1.`willing_to_assist_supervisor` as willingToAssistSupervisor, p1.`willing_to_accept_supervisor` as "
			+ "willingToAcceptSupervisor, p1.`liquidates_finantial_supervisor` as liquidatesFinantialSupervisor, "
			+ "p1.`finantial_requests_timely_supervisor` as finantialRequestsTimelySupervisor, p1.`prudent_finances_supervisor` as "
			+ "prudentFinancesSupervisor, p1.`initiative_supervisor` as initiativeSupervisor, p1.`resolves_challenges_supervisor` as "
			+ "resolvesChallengesSupervisor, p1.`quality_products_supervisor` as qualityProductsSupervisor, p1.`timely_products_supervisor` as "
			+ "timelyProductsSupervisor, p1.`integrity_supervisor` as integritySupervisor, p1.`respect_supervisor` as respectSupervisor, "
			+ "p1.`oral_communication_supervisor` as oralCommunicationSupervisor, p1.`written_communication_supervisor` as "
			+ "writtenCommunicationSupervisor, p1.`error_responsibility_supervisor` as errorResponsibilitySupervisor, p1.`commitment_supervisor` as "
			+ "commitmentSupervisor, p1.`supervises_supervisor` as supervisesSupervisor, p1.`punctual_comments` as punctualComments, "
			+ "p1.`informs_lateness_comments` as informsLatenessComments, p1.`personal_assignments_comments` as personalAssignmentsComments, "
			+ "p1.`listen_instructions_comments` as listenInstructionsComments, p1.`follows_instructions_comments` as followsInstructionsComments, "
			+ "p1.`willing_to_assist_comments` as willingToAssistComments, p1.`willing_to_accept_comments` as willingToAcceptComments, "
			+ "p1.`liquidates_finantial_comments` as liquidatesFinantialComments, p1.`finantial_requests_timely_comments` as "
			+ "finantialRequestsTimelyComments, p1.`prudent_finances_comments` as prudentFinancesComments, p1.`initiative_comments` as "
			+ "initiativeComments, p1.`resolves_challenges_comments` as resolvesChallengesComments, p1.`quality_products_comments` as "
			+ "qualityProductsComments, p1.`timely_products_comments` as timelyProductsComments, p1.`integrity_comments` as "
			+ "integrityComments, p1.`respect_comments` as respectComments, p1.`oral_communication_comments` as oralCommunicationComments, "
			+ "p1.`written_communication_comments` as writtenCommunicationComments, p1.`error_responsibility_comments` as "
			+ "errorResponsibilityComments, p1.`commitment_comments` as commitmentComments, p1.`supervises_comments` as supervisesComments,"
			+ "p1.score, p1.percentage, p1.rank, p1.conclusions, p1.`review_period` as period, p1.`review_period` as reviewPeriod, p1.`review_date` as "
			+ "reviewDate FROM performance_review p1, employee emp WHERE p1.`review_date` = (SELECT MAX(p2.review_date) FROM performance_review p2 "
			+ "WHERE p2.employee_id = p1.employee_id) and p1.employee_id = emp.id and p1.approved =1";

	
	
	String QUERY_GET_EMPLOYEE_BY_ID = "SELECT E.ID, E.FIRSTNAME, E.LASTNAME, E.USERNAME, E.PASSWORD, E.LOCATION, E.DEPARTMENT, "
			+ "E.EMPDATE, E.EMPLOYMENT_STATUS, E.ROLE, E.PICTURE, CONCAT(D.firstname, ' ', D.lastname) as manager FROM EMPLOYEE E LEFT JOIN EMPLOYEE D ON  D.id = E.manager where E.id = ?";

	String QUERY_UPDATE_PERFORMANCE_REVIEW = "UPDATE `attendant_db`.`performance_review` SET `punctual_supervisor` = ?, `punctual_comments` = ?, `informs_lateness_supervisor` = ?,"
			+ "`informs_lateness_comments` = ?, `personal_assignments_supervisor` = ?, `personal_assignments_comments` = ?, `listen_instructions_supervisor` = ?, `listen_instructions_comments` = ?,"
			+ "`follows_instructions_supervisor` = ?, `follows_instructions_comments` = ?, `willing_to_assist_supervisor` = ?, `willing_to_assist_comments` = ?, `willing_to_accept_supervisor` = ?,"
			+ "`willing_to_accept_comments` = ?, `liquidates_finantial_supervisor` = ?, `liquidates_finantial_comments` = ?, "
			+ "`finantial_requests_timely_supervisor` = ?, `finantial_requests_timely_comments` = ?, `prudent_finances_supervisor` = ?, `prudent_finances_comments` = ?, "
			+ "`initiative_supervisor` = ?, `initiative_comments` = ?, `resolves_challenges_supervisor` = ?, `resolves_challenges_comments` = ?, `quality_products_supervisor` = ?,"
			+ "`quality_products_comments` = ?, `timely_products_supervisor` = ?, `timely_products_comments` = ?, `integrity_supervisor` = ?, `integrity_comments` = ?,"
			+ "`respect_supervisor` = ?, `respect_comments` = ?, `oral_communication_supervisor` = ?, `oral_communication_comments` = ?, `written_communication_supervisor` = ?, "
			+ "`written_communication_comments` = ?, `error_responsibility_supervisor` = ?, `error_responsibility_comments` = ?, `commitment_supervisor` = ?, `commitment_comments` = ?,"
			+ "`supervises_supervisor` = ?, `supervises_comments` = ?, `score` = ?, `percentage` = ?, `rank` = ?, `conclusions` = ?, approved = 1 WHERE `id` = ?";
	
	String QUERY_INSERT_ESS = "INSERT INTO `ess` (`id`, `age`,`sex`,`pension_number`,`position`,`team_members`,`phone_number`,`email`,`emergency_contact`,`nok`,`taxid_number`,"
			+ "`experience_years`,`salarylevel`,`birthday`,`cv_url`,`bio`,`supervisor`,`ed`,`job_description_url`,`id_number`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	String QUERY_UPDATE_EMPLOYEE_ESS = "UPDATE `ess` SET `age` = ?, `sex` = ?, `pension_number` = ?, `position` = ?, `team_members` = ?, "
			+ "`phone_number` = ?, `email` = ?, `emergency_contact` = ?, `nok` = ?, `taxid_number` =?, `experience_years` = ?, "
			+ "`salarylevel` = ?, `birthday` = ?, `cv_url` = ?, `bio` = ?, `supervisor` = ?, `ed` = ?, `job_description_url` = ?, ,`id_number` = ? "
			+ "WHERE `id` = ?;"; 
	
	String QUERY_GET_ESS = "SELECT * FROM `ess` WHERE `id` = ?";
	
	String QUERY_GET_ALL_ESS = "SELECT * FROM `ess`";

	String QUERY_SAVE_ADVERT = "INSERT INTO `advert` (`position_to _be_filled`, `date_opened`,`location`,"
			+ "`unit`, `website_link`, `mode_of_advertisment`, `site1`, `site2`, `site3`, `upload_shortlist`,"
			+ "`upload_advert_created`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
	
	String QUERY_SAVE_INTERVIEW = "INSERT INTO `interview` (`name`,`age`,`sex`,`education_above_quilfication`,"
			+ "`work_experience`,`srhr_knowledge`,`team_work`,`confidence`,`writing_abilites`,`technical_ability`,"
			+ "`interpersonnal_comm`,`open_ideas`,`microsoft_packages`,`total`,`salary_expect`,`comments`)"
			+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	String QUERY_SAVE_ORIENTATION = "INSERT INTO `orientation` (`name`,`date_of_orientation`,`work_buddy`,`job_contact`,`policy`,"
			+ "`youth_protection`,`admin_policy`,`copy_admin_policy`,`finance_policy`,`finance_tools`,`id_card`,`salary_account`,"
			+ "`office_tour`,`introduced_supervisor`,`introduce_team_members`,`meeting_supervisor`) VALUES "
			+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	String QUERY_SAVE_PROBATION = "INSERT INTO `probation`(`name_of_staff`,`position`,`location`,`date_of_hire`,`performance_conducted`,`score_perfromance`,"
			+ "`recommendation`,`enroll_pension`,`enroll_medical`,`enroll_insurance`,`enroll_nsitf`,`confrim_letter`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
	
	String QUERY_SAVE_VOLUNTEER = "INSERT INTO `volunteer` (`name`,`socio_demographics`,`educational_background`,`area_of_intrest`,`organization_placement`, "
			+ "`objectives`,`duration`,`work_days`,`location`,`unit_of_intrest`,`manager_comment`,`ed_comment`,`upload_url`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	String QUERY_GET_PENDING_PROBATIONS = "SELECT `id`, `name_of_staff`, `position`,`location`, `date_of_hire`, `performance_conducted`, `score_perfromance`, "
			+ "`recommendation`, `enroll_pension`, `enroll_medical`, `enroll_insurance`, `enroll_nsitf`, `confrim_letter` FROM `probation` WHERE status = 'P'";
	
	String QUERY_APPROVE_PROBATION = "UPDATE `probation` SET `status` = 'A' where id = ? ";
	
	void insertIntoTimeSheet(String idEmployee, java.util.Date date, String totalHours, Integer idProject,
			Integer idFunder);

	void updateTimesheetEntry(String totalHours, Integer idProject, Integer idFunder, Integer id);

	Employee getEmployee(String username, String password);
	
	Employee getEmployeeById(String id);

	List<Employee> workedHoursPerEmployee(Date date, String managerId);

	Integer getDaysOfMonth();
	
	Integer getDaysOfPreviousMonth();

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
	
	List<Employee> getHRTimesheetsTwoMonths(Date date);

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
			String backstopping, Date startDate, Date endDate);
	
	void insertTerminationForm(String employeeId, String leavingReason, String possibleReturn,
			String recommendation, String managementReason, String suggestions, String comments,
			String rehire, String terminationReason, String managementPrevention,String satisfaction,
			String likeEmployment, String dislikeEmployment, String considerReapply, String keepContact,
			String phoneNumber);
	
	LeaveApplication getLeaveApplicationByEmployee(String employeeId);
	
	void updateLeaveApplication(int applicationId, String type, String explanation, int balance,
			String backstopping, Date startDate, Date endDate);
	
	List<LeaveApplication> getLeaveApplications();

	List<TerminationForm> getTerminationForms();
	
	void insertEmployeePerformance(Performance performance, String employeeId);
	
	List<Performance> getPerformanceByManager(String idManager);
	
	List<PerformanceReview> getPerformanceReviewsByManager(String idManager);
	
	List<PerformanceReview> getPerformanceReviewsByHR();
	
	List<Performance> getPerformanceByHR();
	
	void updatePerformance(Performance performance);
	
	void updateTerminationForm(TerminationForm form);
	
	void savePerformanceReview(PerformanceReview review);
	
	void updatePerformanceReview(PerformanceReview review);

	void saveSelfService(EmployeeSS ess);
	
	void updateSelfService(EmployeeSS ess);
	
	EmployeeSS getSelfService(String employeeId);
	
	List<EmployeeSS> getListEmployeeSS();

	void saveAdvert(Advert advert);

	void saveInterview(Interview interview);
	
	void saveOrientation(Orientation orientation);
	
	void saveProbation(Probation probation);
	
	void saveVolunteer(Volunteer volunteer);
	
	List<Probation> getPendingProbations();
	
	void approveProbation(Integer idProbation);


}
