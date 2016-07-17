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

class CreateMenu extends AbstractMenu implements IActions {

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

	CreateMenu (ActionListener listen) {
		setMinimumSize(new Dimension(WIDTH, HEIGHT));

		menus();
		menuItems(listen);

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


	}

	protected void menus () {
		source = new JMenu(SOURCE, true);

		file = new JMenu(FILE, true);

		session = new JMenu(DATA, true);
	}

	protected void menuItems (ActionListener listen) {
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


		selectSource.addActionListener(listen);

		selectFile.addActionListener(listen);
		closeFile.addActionListener(listen);
		saveFile.addActionListener(listen);

		startSess.addActionListener(listen);
		restart.addActionListener(listen);
		endSess.addActionListener(listen);
	}
 
}