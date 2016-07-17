/**
* Sunseeker Telemetry
*
* @author Alec Carpenter <alecgunnar@gmail.com>
* @date July 8, 2016
*/

package sunseeker.telemetry;

import javax.swing.JFrame;
import java.util.ArrayList;
import java.util.HashMap;
import java.lang.Thread;
import javax.swing.JOptionPane;

class DataController extends AbstractController {
    protected JFrame parent;

    protected HashMap<String, DataSourceInterface> dataSources;

    protected DataSourceInterface dataSource;

    protected Thread dataThread;

    public DataController (JFrame frame) {
        parent = frame;

        dataSources = new HashMap<String, DataSourceInterface>();

        /*
         * Register the known data source types
         */
        registerDataSource(new PseudoRandomDataSource());
        registerDataSource(new TenCarDataSource());

        /*
         * Force the user to pick a data source
         */
        promptForDataSource();
    }

    public void start () {
        if (dataSource == null)
            return;        

        /*
         * Start loading the data
         */
        dataThread = new Thread(dataSource, "DataSourceThread");

        dataThread.start();
    }

    public void stop () {
        if (dataThread == null)
            return;

        try {
            dataThread.join();
        } catch (InterruptedException e) {
            System.out.println("Could not stop the data source thread...");
        }
    }

    public void restart () {
        stop();
        start();
    }

    public DataSourceInterface getDataSource () {
        return dataSource;
    }

    protected void registerDataSource (DataSourceInterface source) {
        dataSources.put(source.getName(), source);
    }

    protected void promptForDataSource () {
        String source = (String) JOptionPane.showInputDialog(
            parent,
            "Choose a source for the data:",
            "Choose a Data Source",
            JOptionPane.PLAIN_MESSAGE,
            null,
            dataSources.keySet().toArray(),
            dataSource
        );

        dataSource = dataSources.get(source);

        if (dataSource instanceof AbstractSerialDataSource)
            promptForSerialPort();
    }

    protected void promptForSerialPort () {
        SerialHelper helper = new SerialHelper();

        String port = (String) JOptionPane.showInputDialog(
            parent,
            "Choose which serial port you would like to connect to:",
            "Choose a Serial Port",
            JOptionPane.PLAIN_MESSAGE,
            null,
            helper.getPortNames(),
            null
        );

        ((AbstractSerialDataSource) dataSource).setPort(helper.getIdentifier(port));
    }

}