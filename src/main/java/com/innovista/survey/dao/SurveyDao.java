package com.innovista.survey.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;

import com.innovista.core.survey.model.SurveyCompanies;
import com.innovista.core.survey.model.SurveyCustomer;
import com.innovista.core.survey.model.SurveyLanguages;
import com.innovista.core.survey.model.SurveyQuestions;
import com.innovista.core.survey.model.SurveyQuestionsGridReport;
import com.innovista.core.survey.model.SurveyQuestionsReport;
import com.innovista.core.survey.model.SurveyUser;



public interface SurveyDao {
	
	public void setSessionFactory(SessionFactory sessionFactory);
	public List<SurveyLanguages> getSurveyLanguages();
	public List<Map> getSurveyLanguagesByMap();
	
	//public List<Map> getSurveyQuestions();
	//public List<Map> getSurveyUsers();
	public List<Map> getSurveys();
	
	public List<SurveyCompanies> getSurveyCompanies();
	public List<SurveyUser> getSurveyUsers();
	public List<SurveyUser> getSurveyUser(String userID) ;
	public List<SurveyQuestions> getSurveyQuestions() ;
	public List<SurveyLanguages> getSurveyLanguageNameQuestion(String langName);
	public List<SurveyLanguages> getSurveyLanguageCodeQuestion(String langCode);
	public List<SurveyQuestions> getSurveyQuestions(SurveyLanguages languageName) ;
	
	
	public void  saveSurveyReport(List<SurveyQuestionsReport> questionReport) ;
	
	public void  saveSurveyGridReport(List<SurveyQuestionsGridReport> questionReport) ;
	public void  saveCustomerReport(List<SurveyCustomer> customerReport);
	
	
public List<SurveyQuestionsReport> getAllSurveyReports();
	
	
	public List<SurveyQuestionsReport> getAllSurveyGridReports();
	

}
