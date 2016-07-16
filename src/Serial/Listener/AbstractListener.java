/**
 * Sunseeker Telemetry
 *
 * @author Alec Carpenter <alecgunnar@gmail.com>
 * @date July 16, 2016
 */

package sunseeker.telemetry;

import java.util.List;
import java.util.ArrayList;

abstract class AbstractListener implements ListenerInterface {
    protected List<ListenerObserverInterface> observers;

    public AbstractListener () {
        observers = new ArrayList<ListenerObserverInterface>();
    }

    public void addObserver (ListenerObserverInterface observer) {
        observers.add(observer);
    }

    protected void sendData (String data) {
        for (ListenerObserverInterface observer : observers)
            observer.receiveData(data);
    }
}