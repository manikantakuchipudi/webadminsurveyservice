package com.innovista.survey.controller;

import com.innovista.survey.util.HibernateUtil;

public class SurveyControllerTest {

	
	
	
	

	public static void main(String[] args)
	{
		testSurveyLanguagse();
	}
	
	
	public static void testSurveyLanguagse()
	{
		SurveyController sc=new SurveyController();
		sc.setsessionFactory(HibernateUtil.getSessionFactory());
		sc.getSurveyCompanies();
	}
	
	
	
	
}
