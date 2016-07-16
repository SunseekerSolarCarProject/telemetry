/**
 * Sunseeker Telemetry
 *
 * @author Alec Carpenter <alecgunnar@gmail.com>
 * @date July 9, 2016
 */

package sunseeker.telemetry;

import gnu.io.CommPortIdentifier;
import gnu.io.CommPort;
import gnu.io.SerialPort;
import gnu.io.PortInUseException;
import gnu.io.UnsupportedCommOperationException;
import java.util.TooManyListenersException;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

class SerialClient {
    protected ConnectionInterface connection;
    protected ListenerInterface listener;

    protected CommPort connected;

    public SerialClient (ConnectionInterface conn, ListenerInterface list) {
        connection = conn;
        listener = list;
    }

    public boolean connect (CommPortIdentifier port) {
        try {
            connected = connection.getConnection(port);

            SerialPort serial = (SerialPort) connected;
            serial.enableReceiveThreshold(1);
            serial.enableReceiveTimeout(10);
            serial.addEventListener(listener);

            listener.setInputStream(serial.getInputStream());

            connection.set(getWriter());

            return true;
        } catch (PortInUseException e) {
            System.out.println("Port in use...");
        } catch (UnsupportedCommOperationException e) {
            System.out.println("Cannot connect: " + e.getMessage());
        } catch (TooManyListenersException e) {
            System.out.println("Could not connect: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }

        return false;
    }

    public void disconnect () {
        if (connected != null) {
            try {
                connection.unset(getWriter());
            } catch (IOException e) { }

            connected.close();

            SerialPort serial = (SerialPort) connected;
            serial.removeEventListener();
        }
    }

    protected BufferedWriter getWriter() throws IOException {
        return new BufferedWriter(
            new OutputStreamWriter(connected.getOutputStream())
        );
    }
}