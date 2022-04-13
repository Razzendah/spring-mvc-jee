package com.mycompany.login;

import org.springframework.stereotype.Service;

@Service
public class LoginService {

	public boolean isUserValid(String user, String pass) {
		if (user.equals("admin") && pass.equals("dummy")) {
			return true;
		}

		return false;
	}
}
