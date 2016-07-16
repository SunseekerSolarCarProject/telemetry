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
        AbstractMenu menu = new CreateMenu();
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

}