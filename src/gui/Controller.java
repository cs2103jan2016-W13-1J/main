/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import logic.Response;
import logic.TaskProcessor;

import java.util.ArrayList;
/**
 *
 * @@author A0129845U
 * The controller is to receive message from the user input in gui and 
 * connect to other components
 */
public class Controller {

	private static final String INCOMPLETE = "incomplete";
	private static final String COMPLETED = "completed";
	private static final String UPCOMING = "upcoming";
	private static final String ALL = "all";
	private static final String HELP = "help";
	private static final String OPTION = "option";
	public static DisplayWindow DW;

	// @@author A0112184R
	public static void main(String args[]) throws Exception{
		initialize();
	}
	// @@author

	/* @@author A0112184R
	 * initialize the display window and all logic's components
	 */
	public static void initialize() throws Exception {
		DW = new DisplayWindow();
		DW.setVisible(true);
		TaskProcessor.initialize();
		displayTasks(TaskProcessor.getListToDisplay(), INCOMPLETE);
	}
	// @@author    

	// @@author A0129845U
	public static void sendCmd(String command){
		Response response = TaskProcessor.executeInput(command);
		displayFeedback(response);
	}

	public static void displayFeedback(Response response){
		String status = response.getMessage();
		ArrayList<String> tasks = response.getTaskList();
		String type = response.getType();
		displayStatus(status);
		displayTasks(tasks, type);
	}

	public static void displayStatus(String status){
		DW.displayStatusMsg(status);
	}


	public static void displayTasks(ArrayList<String> tasks, String type){
		switch (type) {
			case ALL:
				DW.clear();
				DW.displayAllTaskList(tasks);
				break;
			case UPCOMING:
				DW.clear();
				DW.displayUpcomingTaskList(tasks);
				break;
			case COMPLETED:   
				DW.clear();
				DW.displayCompletedTaskList(tasks);
				break;
			case INCOMPLETE:
				DW.clear();
				DW.displayIncompleteTaskList(tasks);
				break;
			case HELP:
				DW.clear();
				DW.displayHelpField(tasks);
				break; 
			case OPTION:
				DW.clear();
				DW.displayOptionField(tasks);
				break;
		}
	}
}
