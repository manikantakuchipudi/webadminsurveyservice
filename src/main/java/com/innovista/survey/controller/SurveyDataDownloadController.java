package com.innovista.survey.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;




import com.innovista.core.survey.model.SurveyLanguages;
import com.innovista.core.survey.model.SurveyQuestions;
import com.innovista.survey.service.SurveyService;
import com.innovista.survey.util.FileUtilProperties;
import com.innovista.survey.util.SerilizeFileDataReader;



@Controller
@RequestMapping("/rest/survey/download")
public class SurveyDataDownloadController {

	private String fileDownloadPath="D://surveyQuestions/JSON";
	private String surveyUserFile="surveyUser";
	private String surveyLangaugeFile="surveyLanguages";
	private String surveyQuestionsFile="surveyQuestions";
	private String fileExtention=".ser";
	private String fileProperties="fileProperties";
	private String fileJSONExtention=".json";
	
	
	@Autowired
	private SurveyService surveyService;

	
	@RequestMapping(value = { SurveyControllerURIConstants.GET_PROPERTY_FILE }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public void getSurveyFileProperties()
	{
		
		
		fileExtention=".txt";
		FileUtilProperties fileutil=new FileUtilProperties();
		fileutil.setFileName(fileProperties);
		fileutil.setFileStoreLocation(fileDownloadPath);
		fileutil.setContentType("application/txt");
		fileutil.setFileExtention(fileExtention);
		
	}



	@RequestMapping(value = { SurveyControllerURIConstants.GET_USER_FILE}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getSurveyUserFileDownload(@PathVariable String version,HttpServletRequest request, HttpServletResponse response)
	{
		
		FileUtilProperties fileutil=new FileUtilProperties();
		boolean iseligible=fileutil.isEligibleForDownload(Integer.parseInt(version));
		if(iseligible)
		{
		fileutil.setFileName(surveyUserFile);
		fileutil.setFileStoreLocation(fileDownloadPath);
		fileutil.setContentType("application/txt");
		fileutil.setFileExtention(fileJSONExtention);
		fileutil.fileWriteToResponse(request, response,false);
		return "Sucess";
		}
		return "UPTO DATE";
	}


	@RequestMapping(value = {SurveyControllerURIConstants.GET_QUESTION_FILE }, method = RequestMethod.GET)
	@ResponseBody
	public String  getSurveyQuestionsDownload(@PathVariable String laguage,@PathVariable String version,HttpServletRequest request, HttpServletResponse response)
	{
		FileUtilProperties fileutil=new FileUtilProperties();	
		List<SurveyLanguages> surveyLanguages=null;
		boolean iseligible=fileutil.isEligibleForDownload(Integer.parseInt(version));
		if(iseligible)
		{
		if(laguage==null)
	     surveyLanguages=surveyService.getSurveyLanguages();
		else
			 surveyLanguages=surveyService.getSurveyLanguageNameQuestion(laguage);	
		for(SurveyLanguages surveyLanguge:surveyLanguages)
		{
		String filePath=surveyQuestionsFile+"_"+surveyLanguge.getLangCode();
		
		fileutil.setFileName(filePath);
		fileutil.setContentType("application/txt");
		fileutil.setFileStoreLocation(fileDownloadPath);
		//fileutil.setFileExtention(fileExtention);
		fileutil.setFileExtention(fileJSONExtention);
		
		fileutil.fileWriteToResponse(request, response,false);
		}
		return "Sucess";
		}
		return "UPTO DATE";
	
	}


	@RequestMapping(value = {SurveyControllerURIConstants.GET_LANGUAGES_FILE }, method = RequestMethod.GET)
	public String getSurveyLaguagesDownload(@PathVariable String version,HttpServletRequest request, HttpServletResponse response)
	{
		FileUtilProperties fileutil=new FileUtilProperties();
		boolean iseligible=fileutil.isEligibleForDownload(Integer.parseInt(version));
		
		if(iseligible)
		{
		fileutil.setFileName(surveyLangaugeFile);
		fileutil.setContentType("application/txt");
		fileutil.setFileStoreLocation(fileDownloadPath);
	//	fileutil.setFileExtention(fileExtention);
		fileutil.setFileExtention(fileJSONExtention);
		
		fileutil.fileWriteToResponse(request, response,false);
		return "Sucess";
		}
		else
			return "UP TO DATE";
	}

	
	

	
	
	
	@RequestMapping(value = { "/getSurveyQuestionsFromFile/{langCode}" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<SurveyQuestions> getSurveyQuestionsFromFile(@PathVariable String langCode)
	{
		
		
		String filePath=fileDownloadPath+"//"+surveyQuestionsFile+"_"+langCode+fileExtention;
		return SerilizeFileDataReader.readData(filePath);
		

	}
	
	
	
	
	
	
	
	@RequestMapping(value = { SurveyControllerURIConstants.GET_PROPERTY_FILE }, method = RequestMethod.GET)
	@ResponseBody
	public String  getPropertyFileDownload(@PathVariable String version,HttpServletRequest request, HttpServletResponse response)
	{
		
		FileUtilProperties fileutil=new FileUtilProperties();
		boolean eligible=fileutil.isEligibleForDownload(Integer.parseInt(version));
		if(eligible)
		{
		
		fileutil.setFileName(fileProperties);
		fileutil.setContentType("application/txt");
		fileutil.setFileStoreLocation(fileDownloadPath);
		fileutil.setFileExtention(".txt");
		fileutil.fileWriteToResponse(request, response,true);
		return "SUCESS";
		}
		return "UPTO DATE";
	}
	
	@RequestMapping(value = { SurveyControllerURIConstants.GET_ALL_FILES_DOWNLOAD }, method = RequestMethod.GET)
	@ResponseBody
	public String  getALLFilesDownload(@PathVariable String version,HttpServletRequest request, HttpServletResponse response)
	{
		
		
		FileUtilProperties fileutil=new FileUtilProperties();
		boolean eligible=fileutil.isEligibleForDownload(Integer.parseInt(version));
		if(eligible)
		{
			getSurveyUserFileDownload(version,request,response);

			getSurveyLaguagesDownload(version,request,response);
			getSurveyQuestionsDownload(null,version,request,response);
			getPropertyFileDownload(version,request,response);
			return "Download Sucussfully";
		}
		else
		{
			return "Files are already uptoDate";
		}
		
		
	}
	
	
	
	
	
	
}
