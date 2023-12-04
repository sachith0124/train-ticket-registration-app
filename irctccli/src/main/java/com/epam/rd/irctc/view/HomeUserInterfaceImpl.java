package com.epam.rd.irctc.view;

import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

public class HomeUserInterfaceImpl implements HomeUserInterface {
	
	private Logger logger;
	
	public HomeUserInterfaceImpl() {
		logger = Logger.getLogger(HomeUserInterfaceImpl.class);
	}

	@Override
	public void logMessage(String message) {		
		logger.info(message);
	}

	@Override
	public void logError(String errorMessage) {
		logger.error(errorMessage);
	}

	@Override
	public void logMenu(String menuTitle, List<String> menu) {
		
		logger.info(menuTitle);
		for(String menuItem: menu) {
			logger.info(menuItem);
		}
	}

	@Override
	public int getUserOption() {
		
		Scanner scanner = new Scanner(System.in);
		logger.info("Enter Option :");
		int option = scanner.nextInt();
		//scanner.close();
		return option;
	}

}
