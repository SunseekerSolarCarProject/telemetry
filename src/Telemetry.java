/**
 * Sunseeker Telemetry
 *
 * @author Alec Carpenter <alecgunnar@gmail.com>
 * @date July 2, 2016
 *
 * @modified by Kai Gray <kai.a.gray@wmich.edu>
 * @date July 10, 2016
 */

package sunseeker.telemetry;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.lang.Runnable;

import java.lang.Exception;
import java.io.IOException;

class Telemetry implements Runnable {

    DataTypeInterface collection;

    protected DataTypeCollectionInterface dataTypes;

    protected MainController mainController;
    protected DataController dataController;
    protected ArchiveController archiveController;
    protected AbstractMenu menu;
    
    protected AbstractGraphPanel graphPanel;
    protected AbstractDataSelectPanel dataSelectPanel;
    protected AbstractLiveDataPanel liveDataPanel;

	public static void main (String[] args) throws IOException {
        EventQueue.invokeLater(new Telemetry());
	}

    public Telemetry () {

        dataTypes = new DataTypeCollection();

        /*
         * Add the known data types
         */
        registerDataType("speed", "mph");
        registerDataType("voltage", "volts");
        registerDataType("current", "amps");
        registerDataType("array", "watts");
    }

    public void run () {
        /*
         * This is the main frame which appears
         */
        AbstractMainFrame mainFrame = new MainFrame();

        /*
         * Controls the rendering of the main window interface
         */
        mainController = new MainController(mainFrame);

        /*
        * Displays menu
        */
        menu = new CreateMenu();
        menu.getTelemetry(this);
        mainController.useMenu(menu);

        /*
         * The graph to display the data
         */
        graphPanel = new GraphPanel();
        mainController.useGraphPanel(graphPanel);

        /*
         * Options regarding which data to display
         */
        dataSelectPanel = new DataSelectPanel();
        mainController.useDataSelectPanel(dataSelectPanel);

        /*
         * Display for the most recent values of the data being displayed
         */
        liveDataPanel = new LiveDataPanel();
        mainController.useLiveDataPanel(liveDataPanel);

        /*
         * Create the data controller and get the source
         */
        dataController = new DataController(mainFrame);

        makeAwareOfTypes();

        /*
        * create controller to store data
        */
        archiveController = new ArchiveController();

        /*
         * Start the application
         */
        mainController.start();
    }


    protected void registerDataType (String type, String units) {
        collection = new DataType(type, units);

        dataTypes.add(collection);
    }

    protected AbstractLinePanel[] getLinePanels () {
        AbstractLinePanel[] panels = new AbstractLinePanel[dataTypes.size()];
        int i = 0;

        for (DataTypeInterface type : dataTypes)
            panels[i++] = new LinePanel(type);

        return panels;
    }

    protected void makeAwareOfTypes () {
        DataTypeCollectionInterface types = dataController.getDataSource().getTypes();

        liveDataPanel.setTypes(types);
    }

    public void useControllers () {

        class ControlActions {

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

            }

        }

    }

}