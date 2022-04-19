package com.mycompany.login;

import org.springframework.stereotype.Service;

@Service
public class LoginService {

	public boolean isUserValid(String user, String pass) {
		if (user.equals("asd") && pass.equals("asd")) {
			return true;
		}

		return false;
	}
}
