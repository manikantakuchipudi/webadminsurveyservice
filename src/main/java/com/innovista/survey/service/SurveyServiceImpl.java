package com.innovista.survey.service;

import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.innovista.core.survey.model.SurveyCompanies;
import com.innovista.core.survey.model.SurveyCustomer;
import com.innovista.core.survey.model.SurveyLanguages;
import com.innovista.core.survey.model.SurveyQuestions;
import com.innovista.core.survey.model.SurveyQuestionsGridReport;
import com.innovista.core.survey.model.SurveyQuestionsReport;
import com.innovista.core.survey.model.SurveyUser;
import com.innovista.survey.dao.SurveyDaoImpl;


@Service
public class SurveyServiceImpl implements SurveyService {
	
	@Autowired
	private SurveyDaoImpl surveyDao;
	public List<SurveyLanguages> getSurveyLanguages()
	{
		return surveyDao.getSurveyLanguages();
	}
	public List<SurveyQuestions> getSurveyQuestions()  {
		return surveyDao.getSurveyQuestions();
	}
	public List<SurveyUser> getSurveyUsers()  {
		return surveyDao.getSurveyUsers();
	}
	public List<Map> getSurveys() {
		return surveyDao.getSurveys();
	}

	public List<SurveyCompanies> getSurveyCompanies()
	{
		return surveyDao.getSurveyCompanies();	
	}
	public List<SurveyUser> getSurveyUser(String userID)
	{
		return surveyDao.getSurveyUser(userID);	
	}
	
	public List<Map> getSurveyLanguagesMap()
	{
		return surveyDao.getSurveyLanguagesByMap();
	}
	
	public List<SurveyLanguages> getSurveyLanguageNameQuestion(String langName)
	{
		return surveyDao.getSurveyLanguageNameQuestion(langName);
	}
	public List<SurveyLanguages> getSurveyLanguageCodeQuestion(String langCode)
	{
		return surveyDao.getSurveyLanguageCodeQuestion(langCode);
	}
	
	public List<SurveyQuestions> getSurveyQuestions(SurveyLanguages languageName) 
	{
		return surveyDao.getSurveyQuestions(languageName);
	}

	public void setSessionFactory(SessionFactory sessionFactory)
	{
		surveyDao.setSessionFactory(sessionFactory);
	}
	
	public void  saveSurveyReport(List<SurveyQuestionsReport> questionReport) 
	{
		surveyDao.saveSurveyReport(questionReport);
	}
	
	public void  saveSurveyGridReport(List<SurveyQuestionsGridReport> questionReport) 
	{
		surveyDao.saveSurveyGridReport(questionReport);
	}
	
	public void  saveCustomerReport(List<SurveyCustomer> customerReport) 
	{
		surveyDao.saveCustomerReport(customerReport);
	}
	
	
	
	
	public List<SurveyQuestionsReport> getAllSurveyReports()
	{
		return surveyDao.getAllSurveyReports();
	}
	
	
	public List<SurveyQuestionsReport> getAllSurveyGridReports()
	{
		return surveyDao.getAllSurveyGridReports();
	}
	
	
	
}
