/**
 * Sunseeker Telemetry
 *
 * @author Alec Carpenter <alecgunnar@gmail.com>
 * @date July 2, 2016
 */

package sunseeker.telemetry;

import java.util.Random;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

class PseudoRandomDataSource extends AbstractDataSource implements DataSourceInterface{
    protected Timer scheduler;

    protected Random randGen;

    protected boolean scheduled;

    public PseudoRandomDataSource () {
        scheduler = new Timer();
        randGen   = new Random();
    }

    public String getName () {
        return "Pseudo Random Number Generator";
    }

    public void run () {
        for (String type : typeNames)
            putValue(type, 500 * ((randGen.nextDouble() * 2) - 1));

        if (!scheduled)
            scheduleTask();
    }

    protected void scheduleTask () {
        long delay = MainController.LINE_REFRESH_INTERVAL;

        final DataSourceInterface data = this;

        scheduler.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                data.run();
            }
        }, delay, delay);

        scheduled = true;
    }
}
