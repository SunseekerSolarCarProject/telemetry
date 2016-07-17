/**
 * Sunseeker Telemetry
 *
 * @author Kai Gray <kai.a.gray@wmich.edu>
 * @date July 12, 2016
 */

package sunseeker.telemetry;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.Dimension;

import java.io.IOException;

class CreateMenu extends AbstractMenu implements ActionListener {

	protected JMenu source;
	protected JMenu file;
	protected JMenu session;

	protected JMenuItem selectSource;

	protected JMenuItem selectFile;
	protected JMenuItem saveFile;
	protected JMenuItem closeFile;

	protected JMenuItem startSess;
	protected JMenuItem restart;
	protected JMenuItem endSess;

	//protected Actions action;

	CreateMenu () {
		setMinimumSize(new Dimension(WIDTH, HEIGHT));

		menus();
		menuItems();

		source.add(selectSource);
		add(source);
		
		file.add(selectFile);
		file.add(closeFile);
		file.add(saveFile);
		add(file);
		
		session.add(startSess);
		session.add(restart);
		session.add(endSess);
		add(session);

		this.telemetry = telemetry;
		//action = new Actions();

	}

	public void actionPerformed (ActionEvent e) {

		switch(e.getActionCommand()) {
			case ACTION_SOURCE:
				telemetry.getDataController().promptForDataSource();
				break;
			case ACTION_FILE_SELECT:
				telemetry.getArchiveController.promptForDataSource();
				break;
			case ACTION_FILE_CLOSE:
				action.menuFunctions(3);
				break;
			case ACTION_FILE_SAVE:
				action.menuFunctions(4);
				break;
			case ACTION_DATA_START:
				action.menuFunctions(5);
				break;
			case ACTION_DATA_RESTART:
				action.menuFunctions(6);
				break;
			case ACTION_DATA_END:
				action.menuFunctions(7);
				break;
		}
		/*
		switch(e.getActionCommand()) {
			case ACTION_SOURCE:
				action.menuFunctions(1);
				break;
			case ACTION_FILE_SELECT:
				action.menuFunctions(2);
				break;
			case ACTION_FILE_CLOSE:
				action.menuFunctions(3);
				break;
			case ACTION_FILE_SAVE:
				action.menuFunctions(4);
				break;
			case ACTION_DATA_START:
				action.menuFunctions(5);
				break;
			case ACTION_DATA_RESTART:
				action.menuFunctions(6);
				break;
			case ACTION_DATA_END:
				action.menuFunctions(7);
				break;
		}*/

	}

	protected void menus () {
		source = new JMenu(SOURCE, true);

		file = new JMenu(FILE, true);

		session = new JMenu(DATA, true);
	}

	protected void menuItems () {
		selectSource = new JMenuItem(ACTION_SOURCE);

		selectFile   = new JMenuItem(ACTION_FILE_SELECT);
		closeFile    = new JMenuItem(ACTION_FILE_CLOSE);
		saveFile     = new JMenuItem(ACTION_FILE_SAVE);
		
		startSess    = new JMenuItem(ACTION_DATA_START);
		restart      = new JMenuItem(ACTION_DATA_RESTART);
		endSess      = new JMenuItem(ACTION_DATA_END);


		selectSource.setActionCommand(ACTION_SOURCE);

		selectFile.setActionCommand(ACTION_FILE_SELECT);
		closeFile.setActionCommand(ACTION_FILE_CLOSE);
		saveFile.setActionCommand(ACTION_FILE_SAVE);

		startSess.setActionCommand(ACTION_DATA_START);
		restart.setActionCommand(ACTION_DATA_RESTART);
		endSess.setActionCommand(ACTION_DATA_END);


		selectSource.addActionListener(this);

		selectFile.addActionListener(this);
		closeFile.addActionListener(this);
		saveFile.addActionListener(this);

		startSess.addActionListener(this);
		restart.addActionListener(this);
		endSess.addActionListener(this);
	}

	protected void getTelemetry (Telemetry telemetry) {
		
	}
	
 
}