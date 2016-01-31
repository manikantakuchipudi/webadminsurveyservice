package com.innovista.core.refelection;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.innovista.core.survey.model.SurveyCompanies;
import com.innovista.core.survey.model.SurveyUser;


public class DataStoreSerilized implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args)
	{
		dataStoreToFile();
	}

	public static void dataStoreToFile()
	{
		ObjectOutputStream oos = null;
		FileOutputStream fout = null;
		try{
		     fout = new FileOutputStream("D:\\surveyQuestions\\surveyQuestion.ser", true);
		     oos = new ObjectOutputStream(fout);
		     SurveyUser su=new SurveyUser();
		     SurveyCompanies sc=new SurveyCompanies();
		     sc.setCompanyId(1);
		     su.setCompanyId(sc);
		    oos.writeObject(su);
		    System.out.println("object is write to stream");
		} catch (Exception ex) {
		    ex.printStackTrace();
		} finally {
		    if(oos  != null){
		    	try
		    	{
		        oos.close();
		    	}
		    	catch(IOException ioe)
		    	{
		    		ioe.printStackTrace();
		    	}
		    } 
		}
	}
	
	
}
