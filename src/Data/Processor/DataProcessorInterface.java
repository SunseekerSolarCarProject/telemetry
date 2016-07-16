/**
 * Sunseeker Telemetry
 *
 * @author Alec Carpenter <alecgunnar@gmail.com>
 * @date July 16, 2016
 */

package sunseeker.telemetry;

interface DataProcessorInterface extends ListenerObserverInterface {
    public void addObserver (DataProcessorObserverInterface observer);
}
