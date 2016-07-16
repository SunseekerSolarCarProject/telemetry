/**
 * Sunseeker Telemetry
 *
 * @author Alec Carpenter <alecgunnar@gmail.com>
 * @date July 16, 2016
 */

package sunseeker.telemetry;

interface DataProcessorObserverInterface {
    public void receiveValue (String field, byte[] high, byte[] low);
}
