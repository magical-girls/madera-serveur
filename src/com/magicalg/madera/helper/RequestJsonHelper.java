package com.magicalg.madera.helper;

import java.io.BufferedReader;

import javax.servlet.http.HttpServletRequest;

public class RequestJsonHelper {

	public static String getJsonFromRequest(HttpServletRequest request) throws Exception {

		StringBuffer jb = new StringBuffer();
		String line = null;

		BufferedReader reader = request.getReader();
		while ((line = reader.readLine()) != null){
			jb.append(line);
		}
		return jb.toString();
	}

}
