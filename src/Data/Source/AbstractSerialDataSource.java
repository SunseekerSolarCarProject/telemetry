/**
 * Sunseeker Telemetry
 *
 * @author Alec Carpenter <alecgunnar@gmail.com>
 * @date July 9, 2016
 */

package sunseeker.telemetry;

import java.nio.ByteBuffer;
import gnu.io.CommPortIdentifier;

abstract class AbstractSerialDataSource extends AbstractDataSource {
    protected CommPortIdentifier port;

    protected SerialClient client;

    public AbstractSerialDataSource () {
        client = getClient();
    }

    public void setPort (CommPortIdentifier port) {
        this.port = port;
    }

    public void run () throws RuntimeException {
        if (port == null)
            throw new RuntimeException("A port has not been provided!");

        client.connect(port);
    }

    public void stop () {
        client.disconnect();
    }

    public void receiveValue (String field, byte[] high, byte[] low) {
        ByteBuffer highBuff = ByteBuffer.wrap(high);
        ByteBuffer lowBuff = ByteBuffer.wrap(low);

        double hi = new Double(highBuff.getFloat());
        double lo = new Double(lowBuff.getFloat());

        receiveValue(field, hi, lo);
    }

    abstract protected void receiveValue(String field, double high, double low);

    abstract protected SerialClient getClient();
}
