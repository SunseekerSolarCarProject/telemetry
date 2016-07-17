/**
 * Sunseeker Telemetry
 *
 * @author Alec Carpenter <alecgunnar@gmail.com>
 * @date July 9, 2016
 */

package sunseeker.telemetry;

import java.nio.ByteBuffer;
import java.util.Map;
import java.util.HashMap;
import gnu.io.CommPortIdentifier;

abstract class AbstractSerialDataSource extends AbstractDataSource {
    protected CommPortIdentifier port;

    protected SerialClient client;

    protected Map<String, DataTypeInterface[]> mappings;

    public AbstractSerialDataSource () {
        client = getClient();

        mappings = new HashMap<String, DataTypeInterface[]>();

        registerDataTypes();
    }

    public void setPort (CommPortIdentifier port) {
        this.port = port;
    }

    public void run () throws RuntimeException {
        if (port == null)
            throw new RuntimeException("A port has not been provided!");

        client.connect(port);
    }

    public void receiveValue (String field, byte[] high, byte[] low) {
        ByteBuffer highBuff = ByteBuffer.wrap(high);
        ByteBuffer lowBuff = ByteBuffer.wrap(low);

        receiveValue(
            field,
            highBuff.getFloat(),
            lowBuff.getFloat()
        );
    }

    protected void registerDataMapping (String field, DataTypeInterface high, DataTypeInterface low) {
        mappings.put(field, new DataTypeInterface[] {
            high, low
        });
    }

    protected void receiveValue(String field, double high, double low) {
        if (mappings.containsKey(field)) {
            DataTypeInterface[] types = mappings.get(field);

            if (types[0] != null)
                types[0].putValue(high);

            if (types[1] != null)
                types[1].putValue(low);
        }
    }

    abstract protected void registerDataTypes();

    abstract protected SerialClient getClient();
}
