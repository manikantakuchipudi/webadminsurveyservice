package com.innovista.survey.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import com.innovista.core.survey.model.SurveyCompanies;
import com.innovista.core.survey.model.SurveyCustomer;
import com.innovista.core.survey.model.SurveyGrid;
import com.innovista.core.survey.model.SurveyGridsColumns;
import com.innovista.core.survey.model.SurveyQuestions;
import com.innovista.core.survey.model.SurveyQuestionsGridReport;
import com.innovista.core.survey.model.SurveyQuestionsGridReportPK;
import com.innovista.core.survey.model.SurveyQuestionsReport;
import com.innovista.core.survey.model.SurveyQuestionsReportPK;
import com.innovista.core.survey.model.SurveyUser;
import com.innovista.core.survey.model.Surveys;

public class CSVFileDBLoader {


	private Logger logger=Logger.getLogger(CSVFileDBLoader.class);
	private String inputFile="D:\\InnovistaProjects\\AndWeb\\webadmin-survey\\src\\main\\webapp\\uploads";
	private String inputFile1="D:\\InnovistaProjects\\AndWeb\\webadmin-survey\\src\\main\\webapp\\uploads\\backup";


	private String csvFileName="abcd.csv";




	public static void main(String[] args)
	{

		CSVFileDBLoader csvfile=new CSVFileDBLoader();
		System.out.println(csvfile.inputFileReader());
	}


	public void setInputFilePath(String inputFile)
	{
		this.inputFile=inputFile;
	}
	
	
	public void setInputFileName(String csvFileName)
	{
		this.csvFileName=csvFileName;
	}



	private String getCSVFile()
	{
		return inputFile+File.separator+csvFileName;
	}

	private String getDestCSVFile()
	{
		return inputFile1+File.separator+csvFileName;
	}



	public List<SurveyQuestionsReport> inputFileReader()
	{

		BufferedReader br=null;
		String readLine=null;
		List<SurveyQuestionsReport> retunData=null;
		try
		{
			FileInputStream filestream=new FileInputStream(getCSVFile());
			br=new BufferedReader(new InputStreamReader(filestream,"UTF-8"));
			
			
			retunData=new ArrayList<SurveyQuestionsReport>();
			while((readLine=br.readLine())!=null&&readLine.trim().length()>0)
			{
				
				retunData.add(getReportData(readLine));
				//System.out.println(new String(readLine.getBytes(),"UTF-8"));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.error(e.getLocalizedMessage());
		}
		finally
		{
			try
			{
				if(br!=null)
				{

					br.close();
					br=null;
				}
			}
			catch(IOException ioe)
			{

			}
		}
		return retunData;

	}
	
	
	
	public List<SurveyQuestionsGridReport> inputGridFileReader()
	{

		BufferedReader br=null;
		String readLine=null;
		List<SurveyQuestionsGridReport> retunData=null;
		String fileName=getCSVFile();
		logger.info("fileName >>"+fileName);
		try
		{
			FileInputStream filestream=new FileInputStream(fileName);
			br=new BufferedReader(new InputStreamReader(filestream,"UTF-8"));
			retunData=new ArrayList<SurveyQuestionsGridReport>();
			while((readLine=br.readLine())!=null&&readLine.trim().length()>0)
			{
				retunData.add(getGridReportData(new String(readLine.getBytes(),"UTF-8")));
				//retunData.add(getGridReportData(readLine));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.error(e.getLocalizedMessage());
		}
		finally
		{
			try
			{
				if(br!=null)
				{

					br.close();
					br=null;
				}
			}
			catch(IOException ioe)
			{

			}
		}
		return retunData;

	}
	
	
	
	
	public List<SurveyCustomer> inputCustomerFileReader()
	{

		BufferedReader br=null;
		String readLine=null;
		List<SurveyCustomer> retunData=null;
		try
		{
			FileInputStream filestream=new FileInputStream(getCSVFile());
			br=new BufferedReader(new InputStreamReader(filestream,"UTF-8"));
			retunData=new ArrayList<SurveyCustomer>();
			while((readLine=br.readLine())!=null&&readLine.trim().length()>0)
			{
				retunData.add(getSurveyCustomerData(new String(readLine.getBytes(),"UTF-8")));
				//retunData.add(getSurveyCustomerData(readLine));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.error(e.getLocalizedMessage());
		}
		finally
		{
			try
			{
				if(br!=null)
				{

					br.close();
					br=null;
				}
			}
			catch(IOException ioe)
			{

			}
		}
		return retunData;

	}






	public SurveyQuestionsReport getReportData(String stline)throws Exception
	{
		String[] splitdata=stline.split("\\,");
		SurveyQuestionsReport quesReport=null;

		if(splitdata.length==7)
		{
		quesReport=new SurveyQuestionsReport();	
		int questionID=Integer.parseInt(splitdata[0]);
		int companyID=Integer.parseInt(splitdata[1]);
		String answerValue=splitdata[2];
		int surveyID=Integer.parseInt(splitdata[3]);
		int userID=Integer.parseInt(splitdata[4]);
		String customerName=splitdata[5];
		String customerID=splitdata[6];
		SurveyQuestionsReportPK spk=new SurveyQuestionsReportPK(questionID,companyID, surveyID, userID, customerID);
		quesReport.setSurveyCompanies(new SurveyCompanies(companyID));
		quesReport.setSurveyQuestions(new SurveyQuestions(questionID));
		quesReport.setSurveyUser(new SurveyUser(userID));
		quesReport.setSurveys(new Surveys(surveyID));
		quesReport.setSurveyQuestionsReportPK(spk);
		quesReport.setAnswer(new String(answerValue.getBytes(),"UTF-8"));
		quesReport.setCustomerName(customerName);
		//return quesReport;
		}
		else
		{
			quesReport=null;
		}
		return quesReport;
	}
	
	
	
	
	
	private SurveyCustomer getSurveyCustomerData(String stline)
	{
		String[] splitdata=stline.split("\\,");
		SurveyCustomer surveyCustomer=new SurveyCustomer();
		
		int userID=Integer.parseInt(splitdata[0]);
		int surveyId=Integer.parseInt(splitdata[1]);
		String customerID=splitdata[2];
		String date=splitdata[3];
		
		int reportID=Integer.parseInt(splitdata[0]);
		surveyCustomer.setUserId(new SurveyUser(userID));
		surveyCustomer.setCustomerId(customerID);
		surveyCustomer.setSurveyId(new Surveys(surveyId));
		surveyCustomer.setSurveyDate(new Date(date));
		
		return surveyCustomer;
		
		
		
		
	}
	
	public SurveyQuestionsGridReport getGridReportData(String stline)
	{
		
		
		SurveyQuestionsGridReport quesReport=null;
		try
		{
		String[] splitdata=stline.split("\\,");
		
		if(splitdata.length==10)
		{
		quesReport=new SurveyQuestionsGridReport();

		 int gridID=   Integer.parseInt(splitdata[0]);
		 int gridRowID=   Integer.parseInt(splitdata[1]);
		 int questionID=Integer.parseInt(splitdata[2]);
		 int companyID=Integer.parseInt(splitdata[3]);
		 int gridColumnID=   Integer.parseInt(splitdata[4]);
		 String answerValue=splitdata[5];
		 int surveyID=Integer.parseInt(splitdata[6]);
		int userID=Integer.parseInt(splitdata[7]);
		String customerName=splitdata[8];
		String customerID=splitdata[9];
		SurveyQuestionsGridReportPK gridreportpk=new SurveyQuestionsGridReportPK(gridID,gridRowID,questionID,companyID,gridColumnID, surveyID, userID, customerID);
		quesReport.setSurveyCompanies(new SurveyCompanies(companyID));
		quesReport.setSurveyQuestions(new SurveyQuestions(questionID));
		quesReport.setSurveyUser(new SurveyUser(userID));
		quesReport.setSurveys(new Surveys(surveyID));
		quesReport.setSurveyGrid(new SurveyGrid(gridID));
		quesReport.setSurveyGridsColumns(new SurveyGridsColumns(gridColumnID));
		quesReport.setSurveyQuestionsGridReportPK(gridreportpk);
		quesReport.setAnswer(new String(answerValue.getBytes(),"UTF-8"));
		quesReport.setCustomerName(customerName);
		
		return quesReport;
		}
		else {
			{
				System.out.println("stline "+stline);
			}
		}
		}
		catch(Exception e)
		{
			quesReport=null;
		}
		return quesReport;
	}
	

	public void copyFiles()
	{

		File srcFile=new File(getCSVFile());
		File destFile=new File(getDestCSVFile());
		try {
			FileUtils.copyFile(srcFile, destFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(srcFile.isFile())
			srcFile.delete();
	}





}
