/**
 * Sunseeker Telemetry
 *
 * @author Kai Gray <kai.a.gray@wmich.edu>
 * @date July 12, 2016
 */

package sunseeker.telemetry;

import java.io.IOException;

class Actions extends Telemetry {

	protected int action;

	Actions () {
		
	}
/*
	public void menuFunctions (int activate) {

		switch (activate) {
			case 0:
				break;
			case 1:
				dataController.promptForDataSource();
				break;
			case 2:
				try{
					archiveController.promptForSaveFile();
				} catch(IOException e){}                
				break;
			case 3:
				try{
					archiveController.stop();;
				} catch(IOException e){}  
				break;
			case 4:
				archiveController.saveFile();
				break;
			case 5:
				dataController.start();
				break;
			case 6:
				dataController.restart();
				break;
			case 7:
				dataController.stop();
				break;
		}
	}*/
}