package com.innovista.survey.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

public class FileUtilProperties {



	private static final Logger logger=Logger.getLogger(FileUtilProperties.class) ;
	private String fileDownloadPath="";
	private String fileName="";
	private String contentType="application/txt";
	private String fileExtention=".ser";
	private String fileStoreLocation="D:\\surveyQuestions\\JSON";
	private String filePropertyName="fileProperties";
	private String filePropertyNameExt=".txt";



	public void setFileName(String fileName)
	{
		this.fileName=fileName;
	}


	public void setContentType(String contentType)
	{
		this.contentType=contentType;
	}

	public void setFileExtention(String fileExtention)
	{
		this.fileExtention=fileExtention;
	}

	public void setFileStoreLocation(String fileLocation)
	{
		this.fileStoreLocation=fileLocation;
	}

	public void setfilePropertyName(String fileProperties)
	{
		this.filePropertyName=filePropertyName;
	}



	public HttpServletResponse fileWriteToResponse(HttpServletRequest request, HttpServletResponse response,boolean flag)
	{
		try
		{

			if(request.getParameter("fileName")!=null)
			{
				fileName = request.getParameter("fileName");
			}
			   response.setContentType("multipart/x-mixed-replace;boundary=END");
			   File file = new File(fileStoreLocation +"//"+fileName+fileExtention);
				InputStream in = new BufferedInputStream(new FileInputStream(file));
                response.setHeader("Content-Disposition", "attachment; filename="+fileName+fileExtention); 
				ServletOutputStream out = response.getOutputStream();
				//out.println(contentType);
				IOUtils.copy(in, out);
				if(true)
				response.flushBuffer();
				
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return response;
	}





	public boolean isEligibleForDownload(int version)
	{
		if(getFileVersion()<=version)
			return false;
		else
			return true;
	}
	

	
	public static void main(String[] args)
	{
		FileUtilProperties fup=new FileUtilProperties();
		fup.getFileVersion();
	}


	private int getFileVersion()
	{
		int version=0;
		BufferedReader br=null;
		String versionData="";
		String path=fileStoreLocation+File.separator+filePropertyName+filePropertyNameExt;

		try
		{
			br=new BufferedReader(new FileReader(path));
			versionData=br.readLine();
			System.out.println("versionData >>>>>:::::"+versionData);
			version=Integer.parseInt(versionData.split(":")[1].trim());
		}
		catch(FileNotFoundException e)
		{
			logger.info(e.getMessage());
			version=0;
			//e.printStackTrace();

		}
		catch(Exception e)
		{
			e.printStackTrace();

		}
		finally
		{
			try
			{
				if(br!=null)
				{
					br.close();
				}
			}
			catch(Exception e)
			{

			}
		}




		return version;
	}



	public void publishData(List<FileProperties>  dataArgs)
	{
		BufferedWriter bw=null;
		try
		{

			String path=fileStoreLocation+File.separator+filePropertyName+filePropertyNameExt;
			
			int version=getFileVersion();
			File file=new File(path);
			System.out.println("file>>>>>>>>>>>>>"+file);
			file.createNewFile();
			bw=new BufferedWriter(new FileWriter(path));
			version++;
			bw.write("version: "+version);
			bw.write("\n");
			for(FileProperties fileProperties:dataArgs)
			{

				bw.write("filename: "+fileProperties.getName()+",");
				bw.write("CreatedDate: "+fileProperties.getCreatedDate()+",");
				bw.write("CreatedBy: "+fileProperties.getUserId()+",");
				bw.write("\n");
			}
			bw.write("\n");
			bw.flush();
			bw.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(bw!=null)
			{
				try
				{
					bw.close();
				}
				catch(IOException ioe)
				{

				}
			}
		}
	}



}
