/**
 * Sunseeker Telemetry
 *
 * @author Alec Carpenter <alecgunnar@gmail.com>
 * @date July 16, 2016
 */

package sunseeker.telemetry;

import java.util.List;
import java.util.ArrayList;

abstract class AbstractDataProcessor implements DataProcessorInterface {
    protected List<DataProcessorObserverInterface> observers;

    public AbstractDataProcessor () {
        observers = new ArrayList<DataProcessorObserverInterface>();
    }

    public void addObserver (DataProcessorObserverInterface observer) {
        observers.add(observer);
    }

    public void sendValue (String field, byte[] high, byte[] low) {
        for (DataProcessorObserverInterface observer : observers)
            observer.receiveValue(field, high, low);
    }
}
