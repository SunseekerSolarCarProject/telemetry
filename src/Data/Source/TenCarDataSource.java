/**
 * Sunseeker Telemetry
 *
 * @author Alec Carpenter <alecgunnar@gmail.com>
 * @date July 9, 2016
 */

package sunseeker.telemetry;

import javax.swing.JFrame;
import java.nio.ByteBuffer;

class TenCarDataSource extends AbstractSerialDataSource implements DataProcessorObserverInterface {
    public TenCarDataSource () {
        registerDataType("Motor Controller 1 Speed", "RPM");
    }

    public String getName () {
        return "2010 Car Data Source";
    }

    protected void receiveValue(String field, double high, double low) {
        
    }

    protected SerialClient getClient () {
        DataProcessorInterface processor = new GenericDataProcessor();
        processor.addObserver(this);

        ListenerInterface listener = new GenericListener();
        listener.addObserver(processor);

        return new SerialClient(new ModemConnection(), listener);
    }
}
