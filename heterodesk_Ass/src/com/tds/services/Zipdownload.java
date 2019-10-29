package com.tds.services;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.FileFilter;

import org.apache.commons.io.filefilter.DirectoryFileFilter;
public class Zipdownload extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Zip File Download --------------->");
		HttpSession session=request.getSession();
		Map Basics=(Map)session.getAttribute("Basic");
		String Sub_FilePath=null;
		String Date_Path=session.getAttribute("SUBPATH").toString();
	/*	if(Basics.get("BuName").toString()!=null){
			Sub_FilePath=Basics.get("BuName").toString();
		}else{
			System.exit(0);
		}*/
		String path = "C:\\TDS_FORECAST\\"+Date_Path+"\\FORECAST";
	//	path=path.concat(Sub_FilePath);
        ArrayList FileList=new ArrayList();
        
		 File directory = new File(path);
		 File directory1=null;
		 String[] files=null;
		 String[] files1=null;
		File[] subdirs = directory.listFiles((FileFilter) DirectoryFileFilter.DIRECTORY);
		int i=0;
		for (File dir : subdirs) {
			path = "C:\\TDS_FORECAST\\"+Date_Path+"\\FORECAST\\";
			path=path.concat(dir.getName());
			directory1 = new File(path);
			files=directory1.list();
			for (String fileName : files){
				FileList.add(dir.getName()+"\\"+fileName);
			}
			System.out.println("Directory: " + dir.getName());
		}
	    if (FileList != null && FileList.size() > 0) {
            byte[] zip = zipFiles(directory, FileList);
            ServletOutputStream sos = response.getOutputStream();
            response.setContentType("application/zip");
            response.setHeader("Content-Disposition", "attachment; filename=\"TDSFORECAST.zip\"");
            sos.write(zip);
            sos.flush();
        }
    }
    private byte[] zipFiles(File directory, ArrayList files) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZipOutputStream zos = new ZipOutputStream(baos);
        byte bytes[] = new byte[4096*10000];
        FileInputStream fis=null;
        ListIterator namesIterator = files.listIterator();
       // for (String fileName : files) {
        while(namesIterator.hasNext()){
        String	fileName=namesIterator.next().toString();
            try {
            	    fis = new FileInputStream(directory.getPath()+ "/" + fileName);
                    BufferedInputStream bis = new BufferedInputStream(fis);
                zos.putNextEntry(new ZipEntry(fileName));
                int bytesRead;
                while ((bytesRead = bis.read(bytes)) != -1) {
                    zos.write(bytes, 0, bytesRead);
                }
                zos.closeEntry();
            }catch(Exception ert){
            	ert.printStackTrace();
            }
        }
        zos.flush();
        baos.flush();
        zos.close();
        baos.close();
        return baos.toByteArray();
    }
 }



