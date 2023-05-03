/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Date;
import javax.servlet.http.Part;

/**
 *
 * @author SIMBA SINGH
 */
public class FileUpload {

    public static String addFile(Part uploadImage, String folderpath) throws IOException {
        String imagename = "";
        try (InputStream input = uploadImage.getInputStream()) {
                Date date = new Date();
                date.getTime();

                imagename = uploadImage.getSubmittedFileName();
                int i = imagename.lastIndexOf(".");
                String ext = imagename.substring(i);
                System.out.println("Extention ::: " + imagename.substring(i));

                imagename = date.getTime() + ext;

                System.out.println("image name is=" + imagename);
                 //Copies  to destination.
                Files.copy(input, new File(folderpath, imagename).toPath());
        }catch(IOException ex){
            ex.printStackTrace();
        }
        return imagename;
    }
    
     public static boolean removeFile(String folderpath,String image){
        
        System.out.println("folderpath : =" +folderpath);
        
        File file = new File(folderpath+"/"+image);
        if(file.exists()){
            System.out.println("file exists.....");
            if(file.delete()){
                System.out.println(file.getName()+" Delete....");
                return true;
            }else{
                System.out.println(file.getName()+"Error in Deleting....");
            }
        }
        return false;
    }
}
