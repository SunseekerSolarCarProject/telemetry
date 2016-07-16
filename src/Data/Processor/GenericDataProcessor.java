/**
 * Sunseeker Telemetry
 *
 * @author Alec Carpenter <alecgunnar@gmail.com>
 * @date July 16, 2016
 */

package sunseeker.telemetry;

import java.nio.ByteBuffer;

class GenericDataProcessor extends AbstractDataProcessor {
    public void receiveData (String data) {
        String[] lines = data.split("\\r\\n");

        for (String line : lines)
            if (!line.isEmpty())
                processValue(line);
    }

    protected void processValue (String value) {
        String[] data = value.split(",");

        if (data.length == 3) {
            try {
                sendValue(data[0], bytesFromHex(data[1]), bytesFromHex(data[2]));
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    protected byte[] bytesFromHex (String hex) throws NumberFormatException {
        if (hex.matches("0x[0-9A-F]{8}"))
            return toByteArray(Long.parseLong(hex.substring(2), 16));

        return toByteArray(0);
    }

    protected byte[] toByteArray (long val) {
        return new byte[] {
            processByteAtOffset(val, 0),
            processByteAtOffset(val, 8),
            processByteAtOffset(val, 16),
            processByteAtOffset(val, 24)
        };
    }

    protected byte processByteAtOffset (long val, int offset) {
        return (byte) (val >> offset & 0xFF);
    }
}
