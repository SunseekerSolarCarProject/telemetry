/**
 * Sunseeker Telemetry
 *
 * @author Alec Carpenter <alecgunnar@gmail.com>
 * @date July 16, 2016
 */

package sunseeker.telemetry;

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
            return toByteArray(Integer.parseInt(hex.substring(2), 16));

        return new byte[] {0, 0, 0, 0};
    }

    protected byte[] toByteArray (int bytes) {
        return new byte[] {
            (byte) (bytes >> 0 & 0xff),
            (byte) (bytes >> 8 & 0xff), 
            (byte) (bytes >> 16 & 0xff),
            (byte) (bytes >> 24 & 0xff)
        };
    }
}
