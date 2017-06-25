/** 
 * PerformanceReview.java Created: Mar 12; 2017 Copyright (c) 2013 LEB
 */

package com.app.attendance.model;

import java.util.Date;

/**
 * PerformanceReview - 
 *
 */
public class PerformanceReview {
	
	private Integer id;
	private Integer punctualStaff;
	private Integer informsLatenessStaff;
	private Integer personalAssignmentsStaff;
	private Integer listenInstructionsStaff;
	private Integer followsInstructionsStaff;
	private Integer willingToAssistStaff;
	private Integer willingToAcceptStaff;
	private Integer  liquidatesFinantialStaff;
	private Integer  finantialRequestsTimelyStaff;
	private Integer prudentFinancesStaff;
	private Integer initiativeStaff;
	private Integer  resolvesChallengesStaff;
	private Integer qualityProductsStaff;
	private Integer timelyProductsStaff;
	private Integer integrityStaff;
	private Integer respectStaff;
	private Integer oralCommunicationStaff;
	private Integer writtenCommunicationStaff;
	private Integer errorResponsibilityStaff;
	private Integer commitmentStaff;
	private Integer   supervisesStaff;
	
	
	
	private Integer willingToAcceptSupervisor;
	private String willingToAcceptComments;

	private Integer informsLatenessSupervisor;
	private String informsLatenessComments;
	private Integer personalAssignmentsSupervisor;
	private String personalAssignmentsComments;
	private Integer listenInstructionsSupervisor;
	private String listenInstructionsComments;
	private Integer followsInstructionsSupervisor;
	private String followsInstructionsComments;
	private Integer willingToAssistSupervisor;
	private String  willingToAssistComments;
	private Integer  liquidatesFinantialSupervisor;
	private String   liquidatesFinantialComments;
	private Integer   finantialRequestsTimelySupervisor;
	private String  finantialRequestsTimelyComments;
	
	




	private Integer  prudentFinancesSupervisor;
	private String    prudentFinancesComments;
	private Integer initiativeSupervisor;
	private String initiativeComments;
	private Integer  resolvesChallengesSupervisor;
	private String   resolvesChallengesComments;
	private Integer  qualityProductsSupervisor;
	private String qualityProductsComments;
	private Integer timelyProductsSupervisor;
	private String timelyProductsComments;
	private Integer integritySupervisor;
	private String integrityComments;
	private Integer  respectSupervisor;
	private String  respectComments;
	private Integer oralCommunicationSupervisor;
	private String  oralCommunicationComments;
	private Integer writtenCommunicationSupervisor;
	private String  writtenCommunicationComments;
	private Integer  errorResponsibilitySupervisor;
	private String  errorResponsibilityComments;
	private Integer commitmentSupervisor;
	private String   commitmentComments;
	private Integer   supervisesSupervisor;
	private String supervisesComments;
	private Integer score;
	private Integer percentage;
	private Integer rank;
	private String  conclusions;
	
	private String employeeId;
	
	private String employeeName;
	
	private String designation;
	
	private String location;
	
	private Date reviewDate;
	
	private String period;
	
	private String reviewerName;

	private Integer punctualSupervisor;
	private String punctualComments;
	
	
	
	/**
	 * @return the id
	 */
	public Integer getId() {
		return this.id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
		
	}
	/**
	 * @return the punctualStaff
	 */
	public Integer getPunctualStaff() {
		return this.punctualStaff;
	}
	/**
	 * @param punctualStaff the punctualStaff to set
	 */
	public void setPunctualStaff(Integer punctualStaff) {
		this.punctualStaff = punctualStaff;
		
	}
	/**
	 * @return the punctualSupervisor
	 */
	public Integer getPunctualSupervisor() {
		return this.punctualSupervisor;
	}
	/**
	 * @param punctualSupervisor the punctualSupervisor to set
	 */
	public void setPunctualSupervisor(Integer punctualSupervisor) {
		this.punctualSupervisor = punctualSupervisor;
		
	}
	/**
	 * @return the punctualComments
	 */
	public String getPunctualComments() {
		return this.punctualComments;
	}
	/**
	 * @param punctualComments the punctualComments to set
	 */
	public void setPunctualComments(String punctualComments) {
		this.punctualComments = punctualComments;
		
	}
	/**
	 * @return the informsLatenessStaff
	 */
	public Integer getInformsLatenessStaff() {
		return this.informsLatenessStaff;
	}
	/**
	 * @param informsLatenessStaff the informsLatenessStaff to set
	 */
	public void setInformsLatenessStaff(Integer informsLatenessStaff) {
		this.informsLatenessStaff = informsLatenessStaff;
		
	}
	/**
	 * @return the informsLatenessSupervisor
	 */
	public Integer getInformsLatenessSupervisor() {
		return this.informsLatenessSupervisor;
	}
	/**
	 * @param informsLatenessSupervisor the informsLatenessSupervisor to set
	 */
	public void setInformsLatenessSupervisor(Integer informsLatenessSupervisor) {
		this.informsLatenessSupervisor = informsLatenessSupervisor;
		
	}
	/**
	 * @return the informsLatenessComments
	 */
	public String getInformsLatenessComments() {
		return this.informsLatenessComments;
	}
	/**
	 * @param informsLatenessComments the informsLatenessComments to set
	 */
	public void setInformsLatenessComments(String informsLatenessComments) {
		this.informsLatenessComments = informsLatenessComments;
		
	}
	/**
	 * @return the personalAssignmentsStaff
	 */
	public Integer getPersonalAssignmentsStaff() {
		return this.personalAssignmentsStaff;
	}
	/**
	 * @param personalAssignmentsStaff the personalAssignmentsStaff to set
	 */
	public void setPersonalAssignmentsStaff(Integer personalAssignmentsStaff) {
		this.personalAssignmentsStaff = personalAssignmentsStaff;
		
	}
	/**
	 * @return the personalAssignmentsSupervisor
	 */
	public Integer getPersonalAssignmentsSupervisor() {
		return this.personalAssignmentsSupervisor;
	}
	/**
	 * @param personalAssignmentsSupervisor the personalAssignmentsSupervisor to set
	 */
	public void setPersonalAssignmentsSupervisor(Integer personalAssignmentsSupervisor) {
		this.personalAssignmentsSupervisor = personalAssignmentsSupervisor;
		
	}
	/**
	 * @return the personalAssignmentsComments
	 */
	public String getPersonalAssignmentsComments() {
		return this.personalAssignmentsComments;
	}
	/**
	 * @param personalAssignmentsComments the personalAssignmentsComments to set
	 */
	public void setPersonalAssignmentsComments(String personalAssignmentsComments) {
		this.personalAssignmentsComments = personalAssignmentsComments;
		
	}
	/**
	 * @return the listenInstructionsStaff
	 */
	public Integer getListenInstructionsStaff() {
		return this.listenInstructionsStaff;
	}
	/**
	 * @param listenInstructionsStaff the listenInstructionsStaff to set
	 */
	public void setListenInstructionsStaff(Integer listenInstructionsStaff) {
		this.listenInstructionsStaff = listenInstructionsStaff;
		
	}
	/**
	 * @return the listenInstructionsSupervisor
	 */
	public Integer getListenInstructionsSupervisor() {
		return this.listenInstructionsSupervisor;
	}
	/**
	 * @param listenInstructionsSupervisor the listenInstructionsSupervisor to set
	 */
	public void setListenInstructionsSupervisor(Integer listenInstructionsSupervisor) {
		this.listenInstructionsSupervisor = listenInstructionsSupervisor;
		
	}
	/**
	 * @return the listenInstructionsComments
	 */
	public String getListenInstructionsComments() {
		return this.listenInstructionsComments;
	}
	/**
	 * @param listenInstructionsComments the listenInstructionsComments to set
	 */
	public void setListenInstructionsComments(String listenInstructionsComments) {
		this.listenInstructionsComments = listenInstructionsComments;
		
	}
	/**
	 * @return the followsInstructionsStaff
	 */
	public Integer getFollowsInstructionsStaff() {
		return this.followsInstructionsStaff;
	}
	/**
	 * @param followsInstructionsStaff the followsInstructionsStaff to set
	 */
	public void setFollowsInstructionsStaff(Integer followsInstructionsStaff) {
		this.followsInstructionsStaff = followsInstructionsStaff;
		
	}
	/**
	 * @return the followsInstructionsSupervisor
	 */
	public Integer getFollowsInstructionsSupervisor() {
		return this.followsInstructionsSupervisor;
	}
	/**
	 * @param followsInstructionsSupervisor the followsInstructionsSupervisor to set
	 */
	public void setFollowsInstructionsSupervisor(Integer followsInstructionsSupervisor) {
		this.followsInstructionsSupervisor = followsInstructionsSupervisor;
		
	}
	/**
	 * @return the followsInstructionsComments
	 */
	public String getFollowsInstructionsComments() {
		return this.followsInstructionsComments;
	}
	/**
	 * @param followsInstructionsComments the followsInstructionsComments to set
	 */
	public void setFollowsInstructionsComments(String followsInstructionsComments) {
		this.followsInstructionsComments = followsInstructionsComments;
		
	}
	/**
	 * @return the willingToAssistStaff
	 */
	public Integer getWillingToAssistStaff() {
		return this.willingToAssistStaff;
	}
	/**
	 * @param willingToAssistStaff the willingToAssistStaff to set
	 */
	public void setWillingToAssistStaff(Integer willingToAssistStaff) {
		this.willingToAssistStaff = willingToAssistStaff;
		
	}
	/**
	 * @return the willingToAssistSupervisor
	 */
	public Integer getWillingToAssistSupervisor() {
		return this.willingToAssistSupervisor;
	}
	/**
	 * @param willingToAssistSupervisor the willingToAssistSupervisor to set
	 */
	public void setWillingToAssistSupervisor(Integer willingToAssistSupervisor) {
		this.willingToAssistSupervisor = willingToAssistSupervisor;
		
	}
	/**
	 * @return the willingToAssistComments
	 */
	public String getWillingToAssistComments() {
		return this.willingToAssistComments;
	}
	/**
	 * @param willingToAssistComments the willingToAssistComments to set
	 */
	public void setWillingToAssistComments(String willingToAssistComments) {
		this.willingToAssistComments = willingToAssistComments;
		
	}
	/**
	 * @return the willingToAcceptStaff
	 */
	public Integer getWillingToAcceptStaff() {
		return this.willingToAcceptStaff;
	}
	/**
	 * @param willingToAcceptStaff the willingToAcceptStaff to set
	 */
	public void setWillingToAcceptStaff(Integer willingToAcceptStaff) {
		this.willingToAcceptStaff = willingToAcceptStaff;
		
	}
	/**
	 * @return the willingToAcceptSupervisor
	 */
	public Integer getWillingToAcceptSupervisor() {
		return this.willingToAcceptSupervisor;
	}
	/**
	 * @param willingToAcceptSupervisor the willingToAcceptSupervisor to set
	 */
	public void setWillingToAcceptSupervisor(Integer willingToAcceptSupervisor) {
		this.willingToAcceptSupervisor = willingToAcceptSupervisor;
		
	}
	/**
	 * @return the willingToAcceptComments
	 */
	public String getWillingToAcceptComments() {
		return this.willingToAcceptComments;
	}
	/**
	 * @param willingToAcceptComments the willingToAcceptComments to set
	 */
	public void setWillingToAcceptComments(String willingToAcceptComments) {
		this.willingToAcceptComments = willingToAcceptComments;
		
	}
	/**
	 * @return the liquidatesFinantialStaff
	 */
	public Integer getLiquidatesFinantialStaff() {
		return this.liquidatesFinantialStaff;
	}
	/**
	 * @param liquidatesFinantialStaff the liquidatesFinantialStaff to set
	 */
	public void setLiquidatesFinantialStaff(Integer liquidatesFinantialStaff) {
		this.liquidatesFinantialStaff = liquidatesFinantialStaff;
		
	}
	/**
	 * @return the liquidatesFinantialSupervisor
	 */
	public Integer getLiquidatesFinantialSupervisor() {
		return this.liquidatesFinantialSupervisor;
	}
	/**
	 * @param liquidatesFinantialSupervisor the liquidatesFinantialSupervisor to set
	 */
	public void setLiquidatesFinantialSupervisor(Integer liquidatesFinantialSupervisor) {
		this.liquidatesFinantialSupervisor = liquidatesFinantialSupervisor;
		
	}
	/**
	 * @return the liquidatesFinantialComments
	 */
	public String getLiquidatesFinantialComments() {
		return this.liquidatesFinantialComments;
	}
	/**
	 * @param liquidatesFinantialComments the liquidatesFinantialComments to set
	 */
	public void setLiquidatesFinantialComments(String liquidatesFinantialComments) {
		this.liquidatesFinantialComments = liquidatesFinantialComments;
		
	}
	/**
	 * @return the finantialRequestsTimelyStaff
	 */
	public Integer getFinantialRequestsTimelyStaff() {
		return this.finantialRequestsTimelyStaff;
	}
	/**
	 * @param finantialRequestsTimelyStaff the finantialRequestsTimelyStaff to set
	 */
	public void setFinantialRequestsTimelyStaff(Integer finantialRequestsTimelyStaff) {
		this.finantialRequestsTimelyStaff = finantialRequestsTimelyStaff;
		
	}
	/**
	 * @return the finantialRequestsTimelySupervisor
	 */
	public Integer getFinantialRequestsTimelySupervisor() {
		return this.finantialRequestsTimelySupervisor;
	}
	/**
	 * @param finantialRequestsTimelySupervisor the finantialRequestsTimelySupervisor to set
	 */
	public void setFinantialRequestsTimelySupervisor(Integer finantialRequestsTimelySupervisor) {
		this.finantialRequestsTimelySupervisor = finantialRequestsTimelySupervisor;
		
	}
	/**
	 * @return the finantialRequestsTimelyComments
	 */
	public String getFinantialRequestsTimelyComments() {
		return this.finantialRequestsTimelyComments;
	}
	/**
	 * @param finantialRequestsTimelyComments the finantialRequestsTimelyComments to set
	 */
	public void setFinantialRequestsTimelyComments(String finantialRequestsTimelyComments) {
		this.finantialRequestsTimelyComments = finantialRequestsTimelyComments;
		
	}
	/**
	 * @return the prudentFinancesStaff
	 */
	public Integer getPrudentFinancesStaff() {
		return this.prudentFinancesStaff;
	}
	/**
	 * @param prudentFinancesStaff the prudentFinancesStaff to set
	 */
	public void setPrudentFinancesStaff(Integer prudentFinancesStaff) {
		this.prudentFinancesStaff = prudentFinancesStaff;
		
	}
	/**
	 * @return the prudentFinancesSupervisor
	 */
	public Integer getPrudentFinancesSupervisor() {
		return this.prudentFinancesSupervisor;
	}
	/**
	 * @param prudentFinancesSupervisor the prudentFinancesSupervisor to set
	 */
	public void setPrudentFinancesSupervisor(Integer prudentFinancesSupervisor) {
		this.prudentFinancesSupervisor = prudentFinancesSupervisor;
		
	}
	/**
	 * @return the prudentFinancesComments
	 */
	public String getPrudentFinancesComments() {
		return this.prudentFinancesComments;
	}
	/**
	 * @param prudentFinancesComments the prudentFinancesComments to set
	 */
	public void setPrudentFinancesComments(String prudentFinancesComments) {
		this.prudentFinancesComments = prudentFinancesComments;
		
	}
	/**
	 * @return the initiativeStaff
	 */
	public Integer getInitiativeStaff() {
		return this.initiativeStaff;
	}
	/**
	 * @param initiativeStaff the initiativeStaff to set
	 */
	public void setInitiativeStaff(Integer initiativeStaff) {
		this.initiativeStaff = initiativeStaff;
		
	}
	/**
	 * @return the initiativeSupervisor
	 */
	public Integer getInitiativeSupervisor() {
		return this.initiativeSupervisor;
	}
	/**
	 * @param initiativeSupervisor the initiativeSupervisor to set
	 */
	public void setInitiativeSupervisor(Integer initiativeSupervisor) {
		this.initiativeSupervisor = initiativeSupervisor;
		
	}
	/**
	 * @return the initiativeComments
	 */
	public String getInitiativeComments() {
		return this.initiativeComments;
	}
	/**
	 * @param initiativeComments the initiativeComments to set
	 */
	public void setInitiativeComments(String initiativeComments) {
		this.initiativeComments = initiativeComments;
		
	}
	/**
	 * @return the resolvesChallengesStaff
	 */
	public Integer getResolvesChallengesStaff() {
		return this.resolvesChallengesStaff;
	}
	/**
	 * @param resolvesChallengesStaff the resolvesChallengesStaff to set
	 */
	public void setResolvesChallengesStaff(Integer resolvesChallengesStaff) {
		this.resolvesChallengesStaff = resolvesChallengesStaff;
		
	}
	/**
	 * @return the resolvesChallengesSupervisor
	 */
	public Integer getResolvesChallengesSupervisor() {
		return this.resolvesChallengesSupervisor;
	}
	/**
	 * @param resolvesChallengesSupervisor the resolvesChallengesSupervisor to set
	 */
	public void setResolvesChallengesSupervisor(Integer resolvesChallengesSupervisor) {
		this.resolvesChallengesSupervisor = resolvesChallengesSupervisor;
		
	}
	/**
	 * @return the resolvesChallengesComments
	 */
	public String getResolvesChallengesComments() {
		return this.resolvesChallengesComments;
	}
	/**
	 * @param resolvesChallengesComments the resolvesChallengesComments to set
	 */
	public void setResolvesChallengesComments(String resolvesChallengesComments) {
		this.resolvesChallengesComments = resolvesChallengesComments;
		
	}
	/**
	 * @return the qualityProductsStaff
	 */
	public Integer getQualityProductsStaff() {
		return this.qualityProductsStaff;
	}
	/**
	 * @param qualityProductsStaff the qualityProductsStaff to set
	 */
	public void setQualityProductsStaff(Integer qualityProductsStaff) {
		this.qualityProductsStaff = qualityProductsStaff;
		
	}
	/**
	 * @return the qualityProductsSupervisor
	 */
	public Integer getQualityProductsSupervisor() {
		return this.qualityProductsSupervisor;
	}
	/**
	 * @param qualityProductsSupervisor the qualityProductsSupervisor to set
	 */
	public void setQualityProductsSupervisor(Integer qualityProductsSupervisor) {
		this.qualityProductsSupervisor = qualityProductsSupervisor;
		
	}
	/**
	 * @return the qualityProductsComments
	 */
	public String getQualityProductsComments() {
		return this.qualityProductsComments;
	}
	/**
	 * @param qualityProductsComments the qualityProductsComments to set
	 */
	public void setQualityProductsComments(String qualityProductsComments) {
		this.qualityProductsComments = qualityProductsComments;
		
	}
	/**
	 * @return the timelyProductsStaff
	 */
	public Integer getTimelyProductsStaff() {
		return this.timelyProductsStaff;
	}
	/**
	 * @param timelyProductsStaff the timelyProductsStaff to set
	 */
	public void setTimelyProductsStaff(Integer timelyProductsStaff) {
		this.timelyProductsStaff = timelyProductsStaff;
		
	}
	/**
	 * @return the timelyProductsSupervisor
	 */
	public Integer getTimelyProductsSupervisor() {
		return this.timelyProductsSupervisor;
	}
	/**
	 * @param timelyProductsSupervisor the timelyProductsSupervisor to set
	 */
	public void setTimelyProductsSupervisor(Integer timelyProductsSupervisor) {
		this.timelyProductsSupervisor = timelyProductsSupervisor;
		
	}
	/**
	 * @return the timelyProductsComments
	 */
	public String getTimelyProductsComments() {
		return this.timelyProductsComments;
	}
	/**
	 * @param timelyProductsComments the timelyProductsComments to set
	 */
	public void setTimelyProductsComments(String timelyProductsComments) {
		this.timelyProductsComments = timelyProductsComments;
		
	}
	/**
	 * @return the integrityStaff
	 */
	public Integer getIntegrityStaff() {
		return this.integrityStaff;
	}
	/**
	 * @param integrityStaff the integrityStaff to set
	 */
	public void setIntegrityStaff(Integer integrityStaff) {
		this.integrityStaff = integrityStaff;
		
	}
	/**
	 * @return the integritySupervisor
	 */
	public Integer getIntegritySupervisor() {
		return this.integritySupervisor;
	}
	/**
	 * @param integritySupervisor the integritySupervisor to set
	 */
	public void setIntegritySupervisor(Integer integritySupervisor) {
		this.integritySupervisor = integritySupervisor;
		
	}
	/**
	 * @return the integrityComments
	 */
	public String getIntegrityComments() {
		return this.integrityComments;
	}
	/**
	 * @param integrityComments the integrityComments to set
	 */
	public void setIntegrityComments(String integrityComments) {
		this.integrityComments = integrityComments;
		
	}
	/**
	 * @return the respectStaff
	 */
	public Integer getRespectStaff() {
		return this.respectStaff;
	}
	/**
	 * @param respectStaff the respectStaff to set
	 */
	public void setRespectStaff(Integer respectStaff) {
		this.respectStaff = respectStaff;
		
	}
	/**
	 * @return the respectSupervisor
	 */
	public Integer getRespectSupervisor() {
		return this.respectSupervisor;
	}
	/**
	 * @param respectSupervisor the respectSupervisor to set
	 */
	public void setRespectSupervisor(Integer respectSupervisor) {
		this.respectSupervisor = respectSupervisor;
		
	}
	/**
	 * @return the respectComments
	 */
	public String getRespectComments() {
		return this.respectComments;
	}
	/**
	 * @param respectComments the respectComments to set
	 */
	public void setRespectComments(String respectComments) {
		this.respectComments = respectComments;
		
	}
	/**
	 * @return the oralCommunicationStaff
	 */
	public Integer getOralCommunicationStaff() {
		return this.oralCommunicationStaff;
	}
	/**
	 * @param oralCommunicationStaff the oralCommunicationStaff to set
	 */
	public void setOralCommunicationStaff(Integer oralCommunicationStaff) {
		this.oralCommunicationStaff = oralCommunicationStaff;
		
	}
	/**
	 * @return the oralCommunicationSupervisor
	 */
	public Integer getOralCommunicationSupervisor() {
		return this.oralCommunicationSupervisor;
	}
	/**
	 * @param oralCommunicationSupervisor the oralCommunicationSupervisor to set
	 */
	public void setOralCommunicationSupervisor(Integer oralCommunicationSupervisor) {
		this.oralCommunicationSupervisor = oralCommunicationSupervisor;
		
	}
	/**
	 * @return the oralCommunicationComments
	 */
	public String getOralCommunicationComments() {
		return this.oralCommunicationComments;
	}
	/**
	 * @param oralCommunicationComments the oralCommunicationComments to set
	 */
	public void setOralCommunicationComments(String oralCommunicationComments) {
		this.oralCommunicationComments = oralCommunicationComments;
		
	}
	/**
	 * @return the writtenCommunicationStaff
	 */
	public Integer getWrittenCommunicationStaff() {
		return this.writtenCommunicationStaff;
	}
	/**
	 * @param writtenCommunicationStaff the writtenCommunicationStaff to set
	 */
	public void setWrittenCommunicationStaff(Integer writtenCommunicationStaff) {
		this.writtenCommunicationStaff = writtenCommunicationStaff;
		
	}
	/**
	 * @return the writtenCommunicationSupervisor
	 */
	public Integer getWrittenCommunicationSupervisor() {
		return this.writtenCommunicationSupervisor;
	}
	/**
	 * @param writtenCommunicationSupervisor the writtenCommunicationSupervisor to set
	 */
	public void setWrittenCommunicationSupervisor(Integer writtenCommunicationSupervisor) {
		this.writtenCommunicationSupervisor = writtenCommunicationSupervisor;
		
	}
	/**
	 * @return the writtenCommunicationComments
	 */
	public String getWrittenCommunicationComments() {
		return this.writtenCommunicationComments;
	}
	/**
	 * @param writtenCommunicationComments the writtenCommunicationComments to set
	 */
	public void setWrittenCommunicationComments(String writtenCommunicationComments) {
		this.writtenCommunicationComments = writtenCommunicationComments;
		
	}
	/**
	 * @return the errorResponsibilityStaff
	 */
	public Integer getErrorResponsibilityStaff() {
		return this.errorResponsibilityStaff;
	}
	/**
	 * @param errorResponsibilityStaff the errorResponsibilityStaff to set
	 */
	public void setErrorResponsibilityStaff(Integer errorResponsibilityStaff) {
		this.errorResponsibilityStaff = errorResponsibilityStaff;
		
	}
	/**
	 * @return the errorResponsibilitySupervisor
	 */
	public Integer getErrorResponsibilitySupervisor() {
		return this.errorResponsibilitySupervisor;
	}
	/**
	 * @param errorResponsibilitySupervisor the errorResponsibilitySupervisor to set
	 */
	public void setErrorResponsibilitySupervisor(Integer errorResponsibilitySupervisor) {
		this.errorResponsibilitySupervisor = errorResponsibilitySupervisor;
		
	}
	/**
	 * @return the errorResponsibilityComments
	 */
	public String getErrorResponsibilityComments() {
		return this.errorResponsibilityComments;
	}
	/**
	 * @param errorResponsibilityComments the errorResponsibilityComments to set
	 */
	public void setErrorResponsibilityComments(String errorResponsibilityComments) {
		this.errorResponsibilityComments = errorResponsibilityComments;
		
	}
	/**
	 * @return the commitmentStaff
	 */
	public Integer getCommitmentStaff() {
		return this.commitmentStaff;
	}
	/**
	 * @param commitmentStaff the commitmentStaff to set
	 */
	public void setCommitmentStaff(Integer commitmentStaff) {
		this.commitmentStaff = commitmentStaff;
		
	}
	/**
	 * @return the commitmentSupervisor
	 */
	public Integer getCommitmentSupervisor() {
		return this.commitmentSupervisor;
	}
	/**
	 * @param commitmentSupervisor the commitmentSupervisor to set
	 */
	public void setCommitmentSupervisor(Integer commitmentSupervisor) {
		this.commitmentSupervisor = commitmentSupervisor;
		
	}
	/**
	 * @return the commitmentComments
	 */
	public String getCommitmentComments() {
		return this.commitmentComments;
	}
	/**
	 * @param commitmentComments the commitmentComments to set
	 */
	public void setCommitmentComments(String commitmentComments) {
		this.commitmentComments = commitmentComments;
		
	}
	/**
	 * @return the supervisesStaff
	 */
	public Integer getSupervisesStaff() {
		return this.supervisesStaff;
	}
	/**
	 * @param supervisesStaff the supervisesStaff to set
	 */
	public void setSupervisesStaff(Integer supervisesStaff) {
		this.supervisesStaff = supervisesStaff;
		
	}
	/**
	 * @return the supervisesSupervisor
	 */
	public Integer getSupervisesSupervisor() {
		return this.supervisesSupervisor;
	}
	/**
	 * @param supervisesSupervisor the supervisesSupervisor to set
	 */
	public void setSupervisesSupervisor(Integer supervisesSupervisor) {
		this.supervisesSupervisor = supervisesSupervisor;
		
	}
	/**
	 * @return the supervisesComments
	 */
	public String getSupervisesComments() {
		return this.supervisesComments;
	}
	/**
	 * @param supervisesComments the supervisesComments to set
	 */
	public void setSupervisesComments(String supervisesComments) {
		this.supervisesComments = supervisesComments;
		
	}
	/**
	 * @return the score
	 */
	public Integer getScore() {
		return this.score;
	}
	/**
	 * @param score the score to set
	 */
	public void setScore(Integer score) {
		this.score = score;
		
	}
	/**
	 * @return the percentage
	 */
	public Integer getPercentage() {
		return this.percentage;
	}
	/**
	 * @param percentage the percentage to set
	 */
	public void setPercentage(Integer percentage) {
		this.percentage = percentage;
		
	}
	/**
	 * @return the rank
	 */
	public Integer getRank() {
		return this.rank;
	}
	/**
	 * @param rank the rank to set
	 */
	public void setRank(Integer rank) {
		this.rank = rank;
		
	}
	/**
	 * @return the conclusions
	 */
	public String getConclusions() {
		return this.conclusions;
	}
	/**
	 * @param conclusions the conclusions to set
	 */
	public void setConclusions(String conclusions) {
		this.conclusions = conclusions;
		
	}
	/**
	 * @return the employeeId
	 */
	public String getEmployeeId() {
		return this.employeeId;
	}
	/**
	 * @param employeeId the employeeId to set
	 */
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	/**
	 * @return the employeeName
	 */
	public String getEmployeeName() {
		return this.employeeName;
	}
	/**
	 * @param employeeName the employeeName to set
	 */
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	/**
	 * @return the designation
	 */
	public String getDesignation() {
		return this.designation;
	}
	/**
	 * @param designation the designation to set
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
}
	/**
	 * @return the location
	 */
	public String getLocation() {
		return this.location;
	}
	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	/**
	 * @return the reviewDate
	 */
	public Date getReviewDate() {
		return this.reviewDate;
	}
	/**
	 * @param reviewDate the reviewDate to set
	 */
	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}
	/**
	 * @return the period
	 */
	public String getPeriod() {
		return this.period;
	}
	/**
	 * @param period the period to set
	 */
	public void setPeriod(String period) {
		this.period = period;
	}
	/**
	 * @return the reviewerName
	 */
	public String getReviewerName() {
		return this.reviewerName;
	}
	/**
	 * @param reviewerName the reviewerName to set
	 */
	public void setReviewerName(String reviewerName) {
		this.reviewerName = reviewerName;
	}
	
	
	

	

}
