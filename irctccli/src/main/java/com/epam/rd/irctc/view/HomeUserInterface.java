package com.epam.rd.irctc.view;

import java.util.List;

public interface HomeUserInterface {

	void logMessage(String message);
	
	void logError(String errorMessage);
	
	void logMenu(String menuTitle, List<String> menu);
	
	int getUserOption();
}
