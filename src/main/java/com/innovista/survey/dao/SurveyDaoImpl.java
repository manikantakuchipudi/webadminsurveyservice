package com.innovista.survey.dao;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.innovista.core.survey.model.SurveyCompanies;
import com.innovista.core.survey.model.SurveyCustomer;
import com.innovista.core.survey.model.SurveyLanguages;
import com.innovista.core.survey.model.SurveyQuestions;
import com.innovista.core.survey.model.SurveyQuestionsGridReport;
import com.innovista.core.survey.model.SurveyQuestionsReport;
import com.innovista.core.survey.model.SurveyUser;
import com.innovista.db.util.DBUtil;



@Repository
public class SurveyDaoImpl implements SurveyDao{

	
	@Autowired
	private SessionFactory sessionFactory;

	private Logger logger=Logger.getLogger(SurveyDaoImpl.class);

	
	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.sessionFactory=sessionFactory;
	}
	
	
	
	
	public List<Map> getSurveyLanguagesByMap()
	{
		String query = "SELECT * FROM survey_languages";
		return DBUtil.getRecordsFromDB(sessionFactory, query);
	}
	

	

	public List<Map> getSurveys() {
		String query = "SELECT * FROM surveys";
		return DBUtil.getRecordsFromDB(sessionFactory, query);
	}
	
	
	
	
	private Session session;
	
	public List<SurveyCompanies> getSurveyCompanies() {
	session = sessionFactory.openSession();
	session.beginTransaction();
	Query query = session.getNamedQuery("SurveyCompanies.findAll");
	//session.close();
	return query.list();
	}
	
	public List<SurveyUser> getSurveyUsers() {
		session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.getNamedQuery("SurveyUser.findAll");
		return query.list();
	}
	public List<SurveyUser> getSurveyUser(String userID)
	{
		session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.getNamedQuery("SurveyUser.findByUserId");
		query.setParameter("userId", Integer.parseInt(userID));
		return query.list();
		
	}
	
	public List<SurveyQuestions> getSurveyQuestions() {
		session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.getNamedQuery("SurveyQuestions.findAll");
		return query.list();
	}
	
	
	public List<SurveyLanguages> getSurveyLanguages()
	{
		session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.getNamedQuery("SurveyLanguages.findAll");
	
		return query.list();
	}
	
	public List<SurveyLanguages> getSurveyLanguageNameQuestion(String langName)
	{
		session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.getNamedQuery("SurveyLanguages.findByLangName");
		query.setParameter("langName", langName);
		return query.list();
	}
	
	public List<SurveyLanguages> getSurveyLanguageCodeQuestion(String langCode)
	{
		session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.getNamedQuery("SurveyLanguages.findByLangCode");
		query.setParameter("langCode", langCode);
		return query.list();
	}
	public List<SurveyQuestions> getSurveyQuestions(SurveyLanguages surveyLanguages) 
	{
		session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.getNamedQuery("SurveyQuestions.findByLangid");
		query.setParameter("langid", surveyLanguages);
		return query.list();
	}

	public void  saveSurveyReport(List<SurveyQuestionsReport> questionReport) 
	{
		session = sessionFactory.openSession();
		session.beginTransaction();
		for(SurveyQuestionsReport surveyReport:questionReport)
		{
			try
			{
			session.save(surveyReport);
			logger.info(surveyReport);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				logger.error(e);
				logger.error(surveyReport);
			}
		}
		session.getTransaction().commit();
		logger.info("Data Saved Sucussfully");
		
	}

	public void  saveSurveyGridReport(List<SurveyQuestionsGridReport> questionReport) 
	{
		session = sessionFactory.openSession();
		session.beginTransaction();
		for(SurveyQuestionsGridReport surveyReport:questionReport)
		{
			try
			{
			session.save(surveyReport);
			}
			catch(Exception e)
			{
				session.getTransaction().rollback();
				e.printStackTrace();
				logger.error(e);
				logger.error(surveyReport);
			}
		}
		session.getTransaction().commit();
		logger.info("Data Saved Sucussfully");
	}
	
	
	
	
	public void  saveCustomerReport(List<SurveyCustomer> customerReports) 
	{
		session = sessionFactory.openSession();
		session.beginTransaction();
		for(SurveyCustomer customerReport:customerReports)
		{
			try
			{
			session.save(customerReport);
			}
			catch(Exception e)
			{
				session.getTransaction().rollback();
				e.printStackTrace();
				logger.error(e);
				logger.error(customerReport);
			}
		}
		session.getTransaction().commit();
		logger.info("Data Saved Sucussfully");
	}

	
	
	
	public List<SurveyQuestionsReport> getAllSurveyReports()
	{
		session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.getNamedQuery("SurveyQuestionsReport.findAll");
		
		return query.list();
	}
	
	public List<SurveyQuestionsReport> getAllSurveyGridReports()
	{
		session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.getNamedQuery("SurveyQuestionsGridReport.findAll");
		return query.list();
	}
	
	
}
