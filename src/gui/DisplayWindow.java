/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.ArrayList;
import javax.swing.KeyStroke;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.JComponent;
import javax.swing.JTabbedPane;
import javax.swing.table.DefaultTableModel;

import storage.StorageController;

/* @@author A0129845U
 * The DisplayWindow is to initialize the GUI with shortcut and be able to display or retrieve 
 * information
 */
public class DisplayWindow extends DisplayWindowLayout {

	private static final long serialVersionUID = 1L;
	private static final String COMMAND_DISPLAY_INCOMPLETE_TASK_LIST = "display incomplete";
	private static final String COMMAND_DISPLAY_UPCOMING_TASK_LIST = "display upcoming";     
	private static final String COMMAND_DISPLAY_COMPLETED_TASK_LIST = "display completed";
	private static final String COMMAND_DISPLAY_ALL_TASK_LIST = "display all";
	private static final String COMMAND_DISPLAY_HELP = "help";
	private static final String COMMAND_DISPLAY_OPTION = "display option";
	private static ArrayList<String> cmdHistory = new ArrayList<String>();
	private static int numberOfUp = 0;
	private static int tabIndex;
	/**
	 * Creates new form DisplayWindow
	 *
	 * @throws Exception
	 */
	public DisplayWindow() throws Exception {
		super();
		initShortCutKey();
		// initTabChange();
		tabIndex = 0;
		sendCommandWithEnter();
	}

	public void clear() {
		DefaultTableModel model;
		switch (tabIndex) {
			case 0:
				model = (DefaultTableModel) getIncompleteTaskTable().getModel();
				break;
			case 1:
				model = (DefaultTableModel) getUpcomingTaskTable().getModel();
				break;
			case 2:   
				model = (DefaultTableModel) getCompletedTaskTable().getModel();
				break;
			case 3:
				model = (DefaultTableModel) getAllTaskTable().getModel();
				break;
			default:
				model = (DefaultTableModel) getIncompleteTaskTable().getModel();
				break;                
		}
		int size = model.getRowCount();
		if (size > 0){
			for (int i = 0; i < size; i++) {
				model.removeRow(0);
			}
		}
	}

	public void displayStatusMsg(String status) {
		getStatusField().setText("");
		getStatusField().setText(status);
	}

	public void displayIncompleteTaskList(ArrayList<String> tasks) {
		getTaskTabbedPane().setSelectedIndex(0);            
		int size = tasks.size();
		DefaultTableModel model = (DefaultTableModel) getIncompleteTaskTable().getModel();
		model.setRowCount(0);
		for (int i = 0; i < size; i++) {
			String entryString = (i+1) + ";" + tasks.get(i);
			String[] entry = entryString.split(";");
			model.addRow(entry);
		}
		tabIndex = 0;
	}
	public void displayUpcomingTaskList(ArrayList<String> tasks) {
		getTaskTabbedPane().setSelectedIndex(1);
		int size = tasks.size();                
		DefaultTableModel model = (DefaultTableModel) getUpcomingTaskTable().getModel();
		model.setRowCount(0);
		for (int i = 0; i < size; i++) {
			String entryString = (i+1) + ";" + tasks.get(i);
			String[] entry = entryString.split(";");
			model.addRow(entry);
		}
		tabIndex = 1;                
	}        
	public void displayCompletedTaskList(ArrayList<String> tasks) {
		getTaskTabbedPane().setSelectedIndex(2);
		int size = tasks.size();
		DefaultTableModel model = (DefaultTableModel) getCompletedTaskTable().getModel();
		model.setRowCount(0);
		for (int i = 0; i < size; i++) {
			String entryString = (i+1) + ";" + tasks.get(i);
			String[] entry = entryString.split(";");
			model.addRow(entry);
		}
		tabIndex = 2;
	}          

	public void displayAllTaskList(ArrayList<String> tasks) {
		getTaskTabbedPane().setSelectedIndex(3);
		int size = tasks.size();
		DefaultTableModel model = (DefaultTableModel) getAllTaskTable().getModel();
		model.setRowCount(0);
		for (int i = 0; i < size; i++) {
			String entryString = (i+1) + ";" + tasks.get(i);
			String[] entry = entryString.split(";");
			model.addRow(entry);
		}
		tabIndex = 3;	
	}  

	public void displayHelpField(ArrayList<String> tasks) {
		getTaskTabbedPane().setSelectedIndex(4);
		getHelpField().setText("");
		int size = tasks.size();
		for (int i = 0; i < size; i++) {
			getHelpField().append(tasks.get(i));
		}
		tabIndex = 4;	
	}  

	public void displayOptionField(ArrayList<String> tasks) {
		getTaskTabbedPane().setSelectedIndex(5);
		tabIndex = 5;	
	}  

	private void initShortCutKey() {
		getCommandField().registerKeyboardAction(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				int size = cmdHistory.size();
				if (size - numberOfUp > 0){
					getCommandField().setText(cmdHistory.get(size - numberOfUp - 1));
					numberOfUp++;
				} 
			}
		}, KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);

		getCommandField().registerKeyboardAction(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				int size = cmdHistory.size();
				if ( numberOfUp > 1){
					getCommandField().setText(cmdHistory.get(size - numberOfUp + 1));
					numberOfUp--;
				}
			}
		}, KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);

		getCommandField().registerKeyboardAction(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				Controller.sendCmd("undo");
			}
		}, KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);

		getCommandField().registerKeyboardAction(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				Controller.sendCmd(COMMAND_DISPLAY_INCOMPLETE_TASK_LIST);
			}
		}, KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);

		getCommandField().registerKeyboardAction(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				Controller.sendCmd(COMMAND_DISPLAY_UPCOMING_TASK_LIST);
			}
		}, KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);   

		getCommandField().registerKeyboardAction(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				Controller.sendCmd(COMMAND_DISPLAY_COMPLETED_TASK_LIST);
			}
		}, KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0), JComponent.WHEN_IN_FOCUSED_WINDOW); 

		getCommandField().registerKeyboardAction(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				Controller.sendCmd(COMMAND_DISPLAY_ALL_TASK_LIST);
			}
		}, KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0), JComponent.WHEN_IN_FOCUSED_WINDOW); 

		getCommandField().registerKeyboardAction(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				Controller.sendCmd(COMMAND_DISPLAY_HELP);
			}
		}, KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0), JComponent.WHEN_IN_FOCUSED_WINDOW); 

		getCommandField().registerKeyboardAction(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				Controller.sendCmd(COMMAND_DISPLAY_OPTION);
			}
		}, KeyStroke.getKeyStroke(KeyEvent.VK_F6, 0), JComponent.WHEN_IN_FOCUSED_WINDOW); 

	}
	
	private void initTabChange() {
		JTabbedPane taskPane = getTaskTabbedPane();
		ChangeListener tabChanger = new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				Object source = event.getSource();
				if (source == taskPane) {
					int index = taskPane.getSelectedIndex();
					switch (index) {
						case 0:
							StorageController.setTabType("incomplete");
							break;
						case 1:
							StorageController.setTabType("upcoming");;
							break;
						case 2:
							StorageController.setTabType("completed");;
							break;
						case 3:
							StorageController.setTabType("all");;
							break;
						case 4:
							String helpContent = getHelpField().getText();
							if (helpContent.equals("")) {
								Controller.sendCmd(COMMAND_DISPLAY_HELP);
							}
							break;
						default:
					}
				}
			}
		};
		getTaskTabbedPane().addChangeListener(tabChanger);
	}
	
	public void sendCommandWithEnter(){
		getCommandField().addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent e){
				String command = getCommandField().getText();
				getCommandField().setText("");
				Controller.sendCmd(command);
				cmdHistory.add(command);
				numberOfUp = 0;
			}});                                     
	}
}
