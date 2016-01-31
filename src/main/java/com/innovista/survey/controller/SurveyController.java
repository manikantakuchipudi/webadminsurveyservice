package com.innovista.survey.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;









import org.apache.commons.io.IOUtils;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import ch.qos.logback.core.util.FileUtil;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.innovista.core.survey.model.SurveyCompanies;
import com.innovista.core.survey.model.SurveyCustomer;
import com.innovista.core.survey.model.SurveyLanguages;
import com.innovista.core.survey.model.SurveyQuestions;
import com.innovista.core.survey.model.SurveyQuestionsGridReport;
import com.innovista.core.survey.model.SurveyQuestionsReport;
import com.innovista.core.survey.model.SurveyUser;
import com.innovista.survey.dao.SurveyDaoImpl;
import com.innovista.survey.service.SurveyService;
import com.innovista.survey.util.CSVFileDBLoader;
import com.innovista.survey.util.FileProperties;
import com.innovista.survey.util.FileUtilProperties;
import com.innovista.survey.util.ServicePropertyFileUtility;
import com.innovista.survey.util.SerilizeFileDataReader;
import com.innovista.survey.util.SerilizeFileDataWriter;




@Controller
@RequestMapping("/rest/survey")
public class SurveyController {

	
	private static final Logger logger = LoggerFactory.getLogger(SurveyController.class);


	@Autowired
	private SurveyService surveyService;

	
	@Autowired
	private ServicePropertyFileUtility propertyfileUtility;



	private String fileDownloadPath="D://surveyQuestions//JSON";
	private String surveyUserFile="surveyUser";
	private String surveyLangaugeFile="surveyLanguages";
	private String surveyQuestionsFile="surveyQuestions";
	private String surveyAreasFile="surveyAreas";
	private String fileProperties="fileProperties";
	private String fileExtention=".ser";
	private String fileJSONExtention=".json";
	private String fileUploadPath="";

	
	private SessionFactory sessionFactory=null;
	
	
	public void setsessionFactory(SessionFactory sessionFactory)
	{
		this.sessionFactory=sessionFactory;
		surveyService.setSessionFactory(sessionFactory);
	}
	

	@PostConstruct
	public void init() {


		fileDownloadPath=propertyfileUtility.getStringValue("fileDownloadPath");
		surveyUserFile= propertyfileUtility.getStringValue("surveyUserFile");
		surveyLangaugeFile= propertyfileUtility.getStringValue("surveyLangaugeFile");
		surveyQuestionsFile= propertyfileUtility.getStringValue("surveyQuestionsFile");
		surveyAreasFile=propertyfileUtility.getStringValue("surveyAreasFile");
		fileProperties= propertyfileUtility.getStringValue("fileProperties");
		fileExtention=propertyfileUtility.getStringValue("fileExtention");
		fileJSONExtention=propertyfileUtility.getStringValue("fileJSONExtention");
		fileUploadPath=propertyfileUtility.getStringValue("fileUploadPath");



	}


	@RequestMapping(value = { "/getSurveyLanguageQuestions" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<SurveyLanguages> getSurveyLanguages(HttpServletRequest request, HttpServletResponse response) {
		//String filePath=fileDownloadPath+"//"+surveyLangaugeFile+fileExtention;
		//SerilizeFileDataWriter.dataStoreToFile(surveyService.getSurveyQuestions(), filePath, false);
		Object object=surveyService.getSurveyLanguages();
		Map surveyQuestions=new HashMap<String, List<SurveyQuestions>>();
		surveyQuestions.put("survey_questions",object);

		propertyfileUtility.getStringValue("abc");

		SerilizeFileDataWriter.dataStoreToJsonFile(surveyQuestions,"D:\\surveyQuestions\\surveyQuesion.json",false);
		try
		{
			ObjectWriter ow = new ObjectMapper().writer();
			String json = ow.writeValueAsString(object);

			//System.out.println(json);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		return (List<SurveyLanguages>)object;
	}

	@RequestMapping(value = { "/getSurveyLanguages" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map getSurveyLanguagesMap(HttpServletRequest request, HttpServletResponse response) {

		//String filePath=fileDownloadPath+"//"+surveyLangaugeFile+fileExtention;
		//SerilizeFileDataWriter.dataStoreToFile(surveyService.getSurveyQuestions(), filePath, false);

		Map surveyLangugaes=new HashMap<String, List<SurveyLanguages>>();
		surveyLangugaes.put("languages",surveyService.getSurveyLanguages());
		return surveyLangugaes;
	}


	@RequestMapping(value = { "/getSurveyLanguageQuestions/{languageName}" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<SurveyLanguages> getSurveyLanguagesByName(@PathVariable String languageName,HttpServletRequest request, HttpServletResponse response) {
		return surveyService.getSurveyLanguageNameQuestion(languageName);
	}







	@RequestMapping(value = { "/getSurveyAllQuestions" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String,List<SurveyQuestions>> getSurveyQuestions()
	{

		Map surveyQuestions=new HashMap<String, List<SurveyQuestions>>();
		surveyQuestions.put("survey_questions",surveyService.getSurveyQuestions());

		//List<SurveyLanguages> surveyLanguages=surveyService.getSurveyLanguages();
		///String filePath=fileDownloadPath+"//"+surveyQuestionsFile+fileExtention;
		//SerilizeFileDataWriter.dataStoreToFile(surveyService.getSurveyQuestions(), filePath, false);
		return surveyQuestions;


	}


	@RequestMapping(value = { "/getSurveyQuestions/{languageName}" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String,List<SurveyQuestions>> getSurveyQuestionsByLanguageName(@PathVariable String languageName)
	{
		Map<String, List<SurveyQuestions>> surveyQuestions=new HashMap<String, List<SurveyQuestions>>();



		List<SurveyLanguages> surveyLanguages=surveyService.getSurveyLanguageNameQuestion(languageName);

		if(surveyLanguages.size()>0)
		{
			surveyQuestions.put("survey_questions",surveyService.getSurveyQuestions(surveyLanguages.get(0)));
			return surveyQuestions;
		}
		//return surveyService.getSurveyQuestions(surveyLanguages.get(0));
		else
			return null;


	}


	@RequestMapping(value = { "/getSurveyQuestions" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<SurveyQuestions> getSurveyQuestionsByLanguageAll()
	{

		List<SurveyLanguages> surveyLanguages=surveyService.getSurveyLanguages();
		for(SurveyLanguages surveyLanguge:surveyLanguages)
		{
			String filePath=fileDownloadPath+"//"+surveyQuestionsFile+"_"+surveyLanguge.getLangCode()+fileExtention;
			//SerilizeFileDataWriter.dataStoreToFile(surveyService.getSurveyQuestions(surveyLanguge), filePath, false);
		}
		return surveyService.getSurveyQuestions(surveyLanguages.get(0));
	}








	@RequestMapping(value = { "/getSurveyUsers" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	//@ResponseBody
	public @ResponseBody Map  getSurveyUsers()
	{


		Map surveyUsers=new HashMap<String, List<SurveyUser>>();
		surveyUsers.put("users",surveyService.getSurveyUsers());

		//System.out.println(obj);
		//String filePath=fileDownloadPath+"//"+surveyUserFile+fileExtention;
		//SerilizeFileDataWriter.dataStoreToFile(surveyService.getSurveyQuestions(), filePath, true);
		return surveyUsers;
	}



	@RequestMapping(value = { "/getSurveyUsers/{userID}" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<SurveyUser> getSurveyUsers(@PathVariable String userID)
	{
		//SerilizeFileDataWriter.dataStoreToFile(surveyService.getSurveyUser(userID), "D:\\surveyQuestions\\user.ser", true);
		return surveyService.getSurveyUser(userID);
	}



	@RequestMapping(value = { "/getSurveyAreas" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Map> getSurveyAreas()
	{
		return surveyService.getSurveys();
	}





	@RequestMapping(value = { "/getSurveyCompanies" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<SurveyCompanies> getSurveyCompanies()
	{
		///SerilizeFileDataWriter.dataStoreToFile(surveyService.getSurveyCompanies(), fileDownloadPath+"//surveyCompanies.ser", true);
		return surveyService.getSurveyCompanies();
	}



	@RequestMapping(value = { "/getSurveys" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, List<Map>> getSurveys()
	{
		Map<String, List<Map>> surveys=new HashMap<String, List<Map>>();

		surveys.put("surveys", surveyService.getSurveys());
		///SerilizeFileDataWriter.dataStoreToFile(surveyService.getSurveyCompanies(), fileDownloadPath+"//surveyCompanies.ser", true);
		return surveys;
	}






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
	public String getSurveyUserFileDownload(@PathVariable String version,HttpServletRequest request, HttpServletResponse response,boolean isDownload)
	{

		FileUtilProperties fileutil=new FileUtilProperties();
		boolean iseligible=fileutil.isEligibleForDownload(Integer.parseInt(version));
		if(iseligible)
		{
			fileutil.setFileName(surveyUserFile);
			fileutil.setFileStoreLocation(fileDownloadPath);
			fileutil.setContentType("application/txt");
			fileutil.setFileExtention(fileJSONExtention);
			fileutil.fileWriteToResponse(request, response,isDownload);
			return "Sucess";
		}
		return "UPTO DATE";
	}


	@RequestMapping(value = {SurveyControllerURIConstants.GET_QUESTION_FILE }, method = RequestMethod.GET)
	@ResponseBody
	public String  getSurveyQuestionsDownload(@PathVariable String version,HttpServletRequest request, HttpServletResponse response,boolean isDownload)
	{
		FileUtilProperties fileutil=new FileUtilProperties();	
		boolean iseligible=fileutil.isEligibleForDownload(Integer.parseInt(version));
		if(iseligible)
		{
			List<SurveyLanguages> surveyLanguages=surveyService.getSurveyLanguages();
			for(SurveyLanguages surveyLanguge:surveyLanguages)
			{
				String filePath=surveyQuestionsFile+"_"+surveyLanguge.getLangCode();

				fileutil.setFileName(filePath);
				fileutil.setContentType("application/txt");
				fileutil.setFileStoreLocation(fileDownloadPath);
				//fileutil.setFileExtention(fileExtention);
				fileutil.setFileExtention(fileJSONExtention);

				fileutil.fileWriteToResponse(request, response,isDownload);
			}
			return "Sucess";
		}
		return "UPTO DATE";

	}


	@RequestMapping(value = {SurveyControllerURIConstants.GET_LANGUAGES_FILE }, method = RequestMethod.GET)
	@ResponseBody
	public String getSurveyLaguagesDownload(@PathVariable String version,HttpServletRequest request, HttpServletResponse response,boolean isDownload)
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

			fileutil.fileWriteToResponse(request, response,isDownload);
			return "Sucess";
		}
		else
			return "UP TO DATE";
	}


	@RequestMapping(value = {SurveyControllerURIConstants.GET_SURVEYS_FILE }, method = RequestMethod.GET)
	@ResponseBody
	public String getSurveysDownload(@PathVariable String version,HttpServletRequest request, HttpServletResponse response,boolean isDownload)
	{
		FileUtilProperties fileutil=new FileUtilProperties();
		boolean iseligible=fileutil.isEligibleForDownload(Integer.parseInt(version));

		if(iseligible)
		{
			fileutil.setFileName(surveyAreasFile);
			fileutil.setContentType("application/txt");
			fileutil.setFileStoreLocation(fileDownloadPath);
			//	fileutil.setFileExtention(fileExtention);
			fileutil.setFileExtention(fileJSONExtention);
			fileutil.fileWriteToResponse(request, response,isDownload);
			return "";
		}
		else
			return "UP TO DATE";
	}





	@RequestMapping(value = { "/domain/{entityId}/publishData" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	//@ResponseBody
	public String publishData(HttpServletRequest request, HttpServletResponse response) {


		FileUtilProperties fileutil=new FileUtilProperties();
		File f=new File(fileDownloadPath);
		if(!f.isDirectory())
			f.mkdirs();
		List<FileProperties> publishfilesList=PublicDbDataToFile(request,response);
		fileutil.setFileStoreLocation(fileDownloadPath);
		fileutil.setFileName(fileProperties);
		fileutil.setFileExtention(".txt");
		fileutil.publishData(publishfilesList);
		return "publish-view";
	}



	@RequestMapping(value = { "/getSurveyQuestionsFromFile/{langCode}" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<SurveyQuestions> getSurveyQuestionsFromFile(@PathVariable String langCode)
	{


		String filePath=fileDownloadPath+"//"+surveyQuestionsFile+"_"+langCode+fileExtention;
		return SerilizeFileDataReader.readData(filePath);


	}




	public List<FileProperties> PublicDbDataToFile(HttpServletRequest request,HttpServletResponse response)
	{

		//String path = request.getContextPath();
		//logger.info("path: >>>>>>>"+path);
		List<FileProperties> fpList=new ArrayList<FileProperties>();
		//fileDownloadPath=path+File.separator+"ABCDEFG";
		Date date=new Date();
		String languageFilePath=fileDownloadPath+"//"+surveyLangaugeFile+fileJSONExtention;
		List<SurveyLanguages> surveyLanguages=surveyService.getSurveyLanguages();
		FileProperties fp=new FileProperties();
		fp.setName(surveyLangaugeFile);
		fp.setCreatedDate(date);
		fpList.add(fp);
		Map surveyLanguagesMap=new HashMap<String, List<SurveyQuestions>>();
		surveyLanguagesMap.put("languages",surveyLanguages);

		SerilizeFileDataWriter.dataStoreToJsonFile(surveyLanguagesMap, languageFilePath, false);
		String filePath="";
		for(SurveyLanguages surveyLanguge:surveyLanguages)
		{
			filePath=fileDownloadPath+"//"+surveyQuestionsFile+"_"+surveyLanguge.getLangCode()+fileJSONExtention;

			Map surveyQuestions=new HashMap<String, List<SurveyQuestions>>();
			surveyQuestions.put("survey_questions",surveyService.getSurveyQuestions(surveyLanguge));


			SerilizeFileDataWriter.dataStoreToJsonFile(surveyQuestions, filePath, false);


			//SerilizeFileDataWriter.dataStoreToFile(surveyService.getSurveyQuestions(surveyLanguge), filePath, false);
			fp=new FileProperties();
			fp.setName(surveyQuestionsFile+"_"+surveyLanguge.getLangCode());
			fp.setCreatedDate(date);
			fpList.add(fp);

		}
		filePath=fileDownloadPath+"//"+surveyUserFile+fileJSONExtention;

		Map surveyUsers=new HashMap<String, List<SurveyUser>>();
		surveyUsers.put("users",surveyService.getSurveyUsers());

		SerilizeFileDataWriter.dataStoreToJsonFile(surveyUsers, filePath, false);
		//SerilizeFileDataWriter.dataStoreToFile(surveyService.getSurveyLanguages(), filePath, false);
		fp=new FileProperties();
		fp.setName(surveyUserFile);
		fp.setCreatedDate(date);
		fpList.add(fp);


		filePath=fileDownloadPath+"//"+surveyAreasFile+fileJSONExtention;


		Map surveyAreas=new HashMap<String, List<SurveyUser>>();
		surveyAreas.put("surveys",surveyService.getSurveys());

		SerilizeFileDataWriter.dataStoreToJsonFile(surveyAreas, filePath, false);
		//SerilizeFileDataWriter.dataStoreToFile(surveyService.getSurveyLanguages(), filePath, false);
		fp=new FileProperties();
		fp.setName(surveyAreasFile);
		fp.setCreatedDate(date);
		fpList.add(fp);




		return fpList;
	}


	@RequestMapping(value = { SurveyControllerURIConstants.GET_PROPERTY_FILE }, method = RequestMethod.GET)
	@ResponseBody
	public String  getPropertyFileDownload(@PathVariable String version,HttpServletRequest request, HttpServletResponse response,boolean isDownload)
	{

		FileUtilProperties fileutil=new FileUtilProperties();
		boolean eligible=fileutil.isEligibleForDownload(Integer.parseInt(version));
		if(eligible)
		{

			fileutil.setFileName(fileProperties);
			fileutil.setContentType("application/txt");
			fileutil.setFileStoreLocation(fileDownloadPath);
			fileutil.setFileExtention(".txt");
			fileutil.fileWriteToResponse(request, response,isDownload);
		}
		return "Sucess";
	}

	@RequestMapping(value = { SurveyControllerURIConstants.GET_ALL_FILES_DOWNLOAD }, method = RequestMethod.GET)
	@ResponseBody
	public String  getALLFilesDownload(@PathVariable String version,HttpServletRequest request, HttpServletResponse response)
	{


		FileUtilProperties fileutil=new FileUtilProperties();
		boolean eligible=fileutil.isEligibleForDownload(Integer.parseInt(version));
		if(eligible)
		{
			getSurveyUserFileDownload(version,request,response,false);
			getSurveyLaguagesDownload(version,request,response,false);
			getSurveyQuestionsDownload(version,request,response,false);
			getPropertyFileDownload(version,request,response,true);
			return "Download Sucussfully";
		}
		else
		{
			return "Files are already uptoDate";
		}


	}


	@RequestMapping(value = { SurveyControllerURIConstants.STORE_RESULT }, method = RequestMethod.GET)
	@ResponseBody
	public String  resultsLoadToDatabase(HttpServletRequest request, HttpServletResponse response)
	{
		System.out.println("STORE RESULT");
		CSVFileDBLoader csvReader=new CSVFileDBLoader();
		csvReader.setInputFilePath(fileUploadPath);
		List<SurveyQuestionsReport> reportData=csvReader.inputFileReader();
		surveyService.saveSurveyReport(reportData);
		csvReader.copyFiles();
		System.out.println("STORE RESULT");
		return "sucess";
	}

	@RequestMapping(value = { SurveyControllerURIConstants.GRID_STORE_RESULT }, method = RequestMethod.GET)
	@ResponseBody
	public String  gridresultsLoadToDatabase(HttpServletRequest request, HttpServletResponse response)
	{
		System.out.println("GRID STORE RESULT");
		CSVFileDBLoader csvReader=new CSVFileDBLoader();
		csvReader.setInputFilePath(fileUploadPath);
		List<SurveyQuestionsGridReport> reportData=csvReader.inputGridFileReader();
		if(reportData!=null)
			surveyService.saveSurveyGridReport(reportData);	
		csvReader.copyFiles();
		System.out.println("GRID STORE RESULT");
		return "sucess";
	}




	public void fileLoadToDb(String fileName)

	{
		CSVFileDBLoader csvfileReader=new CSVFileDBLoader();
		csvfileReader.setInputFilePath("");
		csvfileReader.setInputFileName(fileName);
		if(fileName.toLowerCase().contains("grid"))
		{

			List<SurveyQuestionsGridReport> reportData=csvfileReader.inputGridFileReader();
			surveyService.saveSurveyGridReport(reportData);	
		}

		else if(fileName.toLowerCase().contains("customer"))
		{

			List<SurveyCustomer> reportData=csvfileReader.inputCustomerFileReader();
			surveyService.saveCustomerReport(reportData);
		}
		else
		{
			System.out.println("abcdddde"+fileName);
			List<SurveyQuestionsReport> reportData=csvfileReader.inputFileReader();
			surveyService.saveSurveyReport(reportData);
		}

	}


	@RequestMapping(value = {SurveyControllerURIConstants.FILE_UPLOAD}, method=RequestMethod.POST)
	public @ResponseBody String handleFileUpload(@RequestParam("name") String name,@RequestParam("folder") String folder, 
			@RequestParam("file") MultipartFile file){

		
		System.out.println(System.getProperty("file.encoding"));
		
		System.setProperty("file.encoding", "UTF-8");
		
		String fileName=fileUploadPath+File.separator+folder ;
		logger.info("file Folder Location is "+folder);
		File f=new File(fileName);
		if(!f.isDirectory())
			f.mkdirs();
		fileName+=File.separator+name ;
		logger.info("file name is "+fileName);
		if (!file.isEmpty()) {
			try {
			
				
				byte[] bytes = file.getBytes();
				BufferedOutputStream stream =
						new BufferedOutputStream(new FileOutputStream(new File(fileName)));
			
				stream.write(bytes);
				stream.close();
				logger.info("file is uploaded to server sucessfully");
				fileLoadToDb(fileName);
				logger.info("file is uploaded to db sucessfully");
				return "200";
			} catch (Exception e) {
				return "400";
			}
		} else {
			return "400";
		}
	}



	//	@RequestMapping(value = {SurveyControllerURIConstants.FILE_UPLOAD}, method=RequestMethod.POST)
	public @ResponseBody String handleFileUpload2(MultipartFile file){

		System.out.println(file);
		return "uploaded";
		/*if (file != null && !file.isEmpty()) {
	        try {
	        	 System.out.println(fileName);
	            byte[] bytes = file.getBytes();
	            String fileName=fileUploadPath+File.separator+name;
	            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(fileName)));
	            stream.write(bytes);
	            stream.close();
	            System.out.println(fileName);
	            fileLoadToDb(fileName);
	            return "You successfully uploaded " + name + " into " + name + "-uploaded !";
	        } catch (Exception e) {
	            return "You failed to upload " + name + " => " + e.getMessage();
	        }
	    } else {
	        return "You failed to upload " + name + " because the file was empty.";
	    }*/
	}




	private static String getImageDirPath() {
		String envVal = null;

		if (envVal == null) {
			String rootPath = System.getProperty("catalina.home");

			// variable rootPath will contain root path of tomcat server

			File dir = new File(rootPath + File.separator + "tmpFiles");
			if (!dir.exists())
				dir.mkdirs();

			return dir.getAbsolutePath()
					+ File.separator;

		} else {
			return envVal;
		}
	}



	
	
	
	
	

}
