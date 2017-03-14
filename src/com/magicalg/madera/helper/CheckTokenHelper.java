package com.magicalg.madera.helper;

import javax.servlet.http.HttpSession;

import com.magicalg.madera.bdd.dao.LoginDao;

public class CheckTokenHelper {

	public static boolean checkToken(String token, HttpSession httpSession) {
		try{
			String[] parts = token.split("\\.");
			String name = AEScrypt.decrypt(parts[0]); 
			String pwd = AEScrypt.decrypt(parts[1]); 
			String session = parts[3];
			if(null != LoginDao.checkLogin(name, pwd) && session.equals(httpSession.getId())){
				return true;
			} 
		}catch (Exception e){
			
		}
		return false; 
	}
}
