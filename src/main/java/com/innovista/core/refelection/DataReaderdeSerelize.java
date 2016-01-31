package com.innovista.core.refelection;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;

import com.innovista.core.survey.model.SurveyUser;



public class DataReaderdeSerelize implements Serializable {



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public static void main(String[] args)
	{
		readData();
	}


	public static void readData()
	{
		InputStream inputdata = null;
		ObjectInputStream objectInput = null;
		try {
			inputdata = new FileInputStream("D:\\surveyQuestions\\surveyQuestion.ser");
			objectInput = new ObjectInputStream(inputdata);
	    	Object dataObject=null;
	    	SurveyUser surveyUser=null;
			while((dataObject=objectInput.readObject())!=null)
			{
				surveyUser =(SurveyUser)dataObject;
				System.out.println(surveyUser);
			}
			
			
			System.out.println(surveyUser.getCompanyId());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if(objectInput != null) objectInput.close();
			} catch (Exception ex){

			}
		}
	}


}
