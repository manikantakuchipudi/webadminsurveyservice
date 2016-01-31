package com.innovista.survey.util;


import java.io.File;
import java.io.IOException;
import java.util.List;
public class MultipartFileUploader {
	 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
    public static void main(String[] args) {
        String charset = "UTF-8";
      
        File uploadFile2 = new File("D:/InnovistaProjects/AndWeb/webadmin-survey/src/main/webapp/uploads/1_1_HINDI_Questions_Report.csv");
        String requestURL = "http://139.162.46.140:8080/websurvey-admin-service/rest/survey/getFileUploaderResult";
        System.out.println(requestURL);
        String userID="1";
        try {
            MultipartUtility multipart = new MultipartUtility(requestURL, charset);
            multipart.addHeaderField("User-Agent", "CodeJava");
            multipart.addHeaderField("Test-Header", "Header-Value");
            multipart.addFormField("description", "User Files");
            multipart.addFormField("charset", "UTF-8");
            
            multipart.addFormField("keywords", "Java,upload,Spring");
            multipart.addFormField("name", uploadFile2.getName());
            multipart.addFormField("folder", userID);
            multipart.addFilePart("file", uploadFile2);
            
            List<String> response = multipart.finish();
            System.out.println("SERVER REPLIED:");
             
            for (String line : response) {
                System.out.println(line);
            }
        } catch (IOException ex) {
        	ex.printStackTrace();
            System.err.println(ex);
        }
    }
    
    
    
    
   
    
    
    
}