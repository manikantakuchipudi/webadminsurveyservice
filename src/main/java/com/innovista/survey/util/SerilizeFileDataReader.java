package com.innovista.survey.util;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.innovista.core.survey.model.SurveyLanguages;
import com.innovista.core.survey.model.SurveyQuestionTypes;
import com.innovista.core.survey.model.SurveyQuestions;
import com.innovista.core.survey.model.SurveyUser;

public class SerilizeFileDataReader implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6752387690285651957L;


	public static void main(String[] args)
	{
		
		String dataReadFile="D:\\surveyQuestions\\surveyQuestions.ser";
		readData(dataReadFile);
	}


	public static List<SurveyQuestions> readData(String readFile)
	{
		InputStream inputdata = null;
		ObjectInputStream objectInput = null;
		List<SurveyQuestions> list=new ArrayList<>();
		try {
			inputdata = new FileInputStream(readFile);
			objectInput = new ObjectInputStream(inputdata);
	    	Object dataObject=null;
	    	SurveyUser surveyUser=null;
	    	
			while((dataObject=objectInput.readObject())!=null)
			{
				if(dataObject instanceof SurveyUser)
				{
					surveyUser =(SurveyUser)dataObject;
					//list.add(surveyUser);
					System.out.println(surveyUser);
				}
				else if(dataObject instanceof SurveyLanguages)
				{
					SurveyLanguages surveyLanguages =(SurveyLanguages)dataObject;
					System.out.println(surveyLanguages);
				}
				else if(dataObject instanceof SurveyQuestions)
				{
					SurveyQuestions surveyQuestions =(SurveyQuestions)dataObject;
					list.add(surveyQuestions);
					System.out.println(surveyQuestions);
				
					String value = new String(surveyQuestions.getQuestion().getBytes("utf-8"), "utf-8");
					System.out.println(surveyQuestions.getQuestion().getBytes());
				}
				
				else if(dataObject instanceof SurveyQuestionTypes)
				{
					SurveyQuestionTypes surveyQuestionTypes =(SurveyQuestionTypes)dataObject;
					System.out.println(surveyQuestionTypes);
				}
				
				
			}
			
		} 
		catch (EOFException e) {
		//	e.printStackTrace();
		} 	
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if(objectInput != null)
					objectInput.close();
			} catch (Exception ex){

			}
		}
		return list;
	}

}
