package com.innovista.survey.util;


import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.gson.Gson;




public class SerilizeFileDataWriter {
	private static final long serialVersionUID = 1L;





	private String fileStorePath="";

	public static void main(String[] args)
	{
		System.out.println("एस.सी");
		dataStoreToFile(null,"D:\\surveyQuestions\\surveyQuestion.ser",false);
	}


	public void setObject()
	{

	}



	public static void dataStoreToFile(List<?> listobject,String outfile,boolean append)
	{
		ObjectOutputStream objectOutput = null;
		FileOutputStream fileWriter = null;
		try{
			//File outfileData=new File(outfile,"UTF-8");
			fileWriter = new FileOutputStream(outfile,append);
			//objectOutput = new ObjectOutputStream(fileWriter);
			//Gson gson = new Gson();

			// convert java object to JSON format,
			// and returned as JSON formatted string
			//String json = gson.toJson(listobject);

			//FileWriter writer = new FileWriter("D:\\surveyQuestions\\outfile.json");
			//writer.write(json);
			//writer.close();

			for(Object object:listobject)
			{
				if(object!=null)	
					objectOutput.writeObject(object);
			}
			objectOutput.flush();
			System.out.println("object is write to stream");
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if(objectOutput  != null){
				try
				{
					objectOutput.close();
				}
				catch(IOException ioe)
				{
					ioe.printStackTrace();
				}
			} 
		}
	}





	public static void dataStoreToJsonFile(Map<String, List<?>> listobject,String outfile,boolean append)
	{
		BufferedWriter out=null;
        
		try
		{
			ObjectWriter ow = new ObjectMapper().writer();
			String json = ow.writeValueAsString(listobject);
			 out = new BufferedWriter
				    (new OutputStreamWriter(new FileOutputStream(outfile),"UTF-8"));
			
			out.write(json);
			out.flush();



		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if(out  != null){
				try
				{
					out.close();
				}
				catch(IOException ioe)
				{
					ioe.printStackTrace();
				}
			} 
		}
	}









}
