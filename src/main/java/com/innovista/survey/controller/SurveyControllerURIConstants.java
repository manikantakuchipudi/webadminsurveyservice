package com.innovista.survey.controller;

import javax.annotation.PostConstruct;

public class SurveyControllerURIConstants {

	@PostConstruct
	public void init() {
		
	}
	
	
	public static final  String  GENERATE_LANGUAGES_FILE="";
	public static final  String  GENERATE_USER_FILE="";
	public static final  String  GENERATE_QUESTIONS_FILE="";
	public static final  String  GET_LANGUAGES="";
	public static final  String  GET_USER="";
	public static final  String  GET_QUESTION="";
	
	public static final String GET_SURVEY_COMPANIES="getSurveyCompanies";
	
	
	
	public static final  String  GET_LANGUAGES_FILE="/getLanguagesFile/{version}";
	
	public static final  String  GET_USER_FILE="/getUserFile/{version}";
	
	
  // public static final  String  GET_QUESTION_FILE="/getQuestionsFile/{version}";
   
   
   public static final  String  GET_QUESTION_FILE="/getQuestionsFile/{laguage}/{version}";
   
   public static final  String  GET_PROPERTY_FILE="/getPropertyFileFile";
   
   
   public static final  String  GET_ALL_FILES_DOWNLOAD="/getAllFilesDownload/{version}";
	
   
   
   public static final  String  GET_SURVEYS_FILE="/getSurveysFile/{version}";
   
   
   
   public static final String STORE_RESULT="/getStoreResult";
   
   
   public static final String GRID_STORE_RESULT="/getGridStoreResult";
   
   
   public static final String FILE_UPLOAD="/getFileUploaderResult";
	
	
	
	
	
	
	
	
}
