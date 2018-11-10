package com.znbl.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtils {

	/**
	 * String to Enity
	 * @author Gray
	 * @return
	 */
	public static <T> T strToEnity(Object obj, Class<T> type) {
		//Gson gson = new Gson();
		Gson gson=  new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		return gson.fromJson(obj.toString(), type);
	}

	/**
	 * successCallback
	 * @author Gray
	 * @return
	 */
	public static String successCallback(Object obj) {
		//Gson gson = new Gson();
		Gson gson=  new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		return "successCallback(" + gson.toJson(obj) + ")";
	}
	
	public static String toJson(Object obj){
		Gson gson=  new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		return  gson.toJson(obj);
	}
	
	public static String loadJson (String url) {  
        StringBuilder json = new StringBuilder();  
        try {  
            URL urlObject = new URL(url);  
            URLConnection uc = urlObject.openConnection();  
            BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));  
            String inputLine = null;  
            while ( (inputLine = in.readLine()) != null) {  
                json.append(inputLine);  
            }  
            in.close();  
        } catch (MalformedURLException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return json.toString();  
    } 
}
