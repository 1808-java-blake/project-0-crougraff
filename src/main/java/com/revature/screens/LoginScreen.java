package com.revature.screens;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.beans.User;
import com.revature.daos.UserDao;

public class LoginScreen implements Screen {
	private Scanner scan = new Scanner(System.in);
	private UserDao ud = UserDao.currentUserDao;
	private Logger log = Logger.getRootLogger();

	@Override
	public Screen start() {
		log.debug("started login screen");
		System.out.println(
				"                                                                                                          \r\n" + 
				"                                                                                                          \r\n" + 
				"  meWel  meWe  omeWe comeWelcome   comeWe           eWelc       omeWel   eWelc       omeWe comeWelcome    \r\n" + 
				"  eWelc  eWel  meWel omeWelcomeWe  omeWel         meWelcomeW   omeWelco  Welco       meWel omeWelcomeWe   \r\n" + 
				"   elco  Welc  eWel  meWe    eWel    Wel         meW   omeWe  omeW  come   come      eWe   meWe    eWel   \r\n" + 
				"    com   lco  Wel    Wel            el         meW     eWel  meW    meW   omeWe    eWel    Wel           \r\n" + 
				"    ome  lcom  el     elcome         lc         eWe          meWe    eWel  meWel    W lc    elcome        \r\n" + 
				"     eW  comeWelc     lcomeWe        co         We           eWel    Welc  eWelco  We co    lcomeWe       \r\n" + 
				"     WelcomeWelco     comeWel        ome        el            elc    elc   We come el om    comeWel       \r\n" + 
				"      lcomeWelcom     ome           omeW    me  lc       om   lco    lco   el omeWel  meW   ome           \r\n" + 
				"       ome  lcom      meWe    eWe    eWe    eW  com     ome   come  lcom   lco eWelc meWe   meWe    eWe   \r\n" + 
				"       me   come     meWelco eWel  meWelcomeWe   meWelcomeW    meWelcom  elcome elc meWelc meWelco eWel   \r\n" + 
				"       eW    me      eWelcomeWelc  eWelcomeWel    Welcome       Welcom    comeW lc  eWelco eWelcomeWelc   \r\n" + 
				"                                                                                                          \r\n" + 
				"                                                                                                          ");
		System.out.println(
				"                                                                                                                                                     \r\n" + 
				"                                                                                                                                                     \r\n" + 
				"  e BANK!to th    NK!to           o the BANK!t  the    K!to t e BANK!to t         o the BANK          NK!       BANK!   the BA  !to th   ANK  o th   \r\n" + 
				"  K!to the BAN   o the BA          BANK!to the BANK!   the B  K!to the BAN         BANK!to th        o the      to th   ANK!to  he BA    o t   BAN   \r\n" + 
				"  the  ANK  o   e BA  !to         !to  he   NK  o t     NK!   the     !to         !to t   BAN         BANK        BANK    the    K!t    e     !to    \r\n" + 
				"       o t      K!t    e B             NK!       BA      th    NK!                   BA   to        K!to t       !to th  BAN     the   NK     he B   \r\n" + 
				"        BA      the    K!to             th      !to     BAN     the B               !to the B       the BAN      he BANK to      ANK!to       NK!t   \r\n" + 
				"       !t      BANK    the             BA       he BANK!to     BANK!to              he BANK!to     BANK!to t     NK!to t e B     o the B       the   \r\n" + 
				"       he       o t    ANK             to       NK!to t e B    to the               NK!to the B    to the BA      th  BANK!t      BANK!t      BAN    \r\n" + 
				"       NK!       BA    o t             e B       th     K!t    e B                   th    NK!t   he B   !to     BAN !to the     !to  he       o     \r\n" + 
				"        th      !to   e BA             K!t      BAN     the    K!to     BA          BANK!to the   NK!     e B    to   e BANK     he   NK!       B    \r\n" + 
				"     e BANK!     e BANK!t            o the B   !to t   BANK!   the BA K!to        K!to the BAN  to the   NK!to  he BA  !to t    ANK!t  the BA        \r\n" + 
				"     K!to th      !to th              BANK!t   he BA   to th  BANK!to the         the BANK!to   e BANK    the B NK!to   e BA   to the BANK!to the    \r\n" + 
				"                                                                                                                                              ANK!   \r\n" + 
				"                                                                                                                                                     ");
		System.out.println("Enter Username or type Register to sign up: ");
		String username = scan.nextLine();
		log.trace("username = " + username);
		if ("register".equalsIgnoreCase(username)) {
			return new RegisterUserScreen();
		}
		
		System.out.println("Enter Password: ");
		String password = scan.nextLine();
		
		log.debug("received users credentials");
		User currentUser = ud.findByUsernameAndPassword(username, password);
		if (currentUser != null) {
			if(username.equals("Admin") && password.equals("Admin")){
				return new AdminScreen();
			}
			log.info("user succefully logged in");
			return new HomeScreen(currentUser);
		}

		System.out.println("unable to login");
		return this;
	}

}
