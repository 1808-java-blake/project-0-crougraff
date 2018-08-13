package com.revature.launcher;

import org.apache.log4j.Logger;

import com.revature.screens.LoginScreen;
import com.revature.screens.Screen;

public class Launcher {
	private static Logger log = Logger.getRootLogger();
	public static void main(String[] args) throws InterruptedException {
		Screen s = new LoginScreen();
		while(true) {
			s = s.start();
		}
	}
}
