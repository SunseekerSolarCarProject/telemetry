/**
 * Sunseeker Telemetry
 *
 * @author Alec Carpenter <alecgunnar@gmail.com>
 * @date July 19, 2016
 */

package sunseeker.telemetry;

import java.io.File;

public class InitialProfileLoader extends FileProfileLoader implements ProfileLoaderGUIObserverInterface {
    protected ProfileLoaderObserverInterface observer;

    protected ProfileLoaderGUI profileLoaderGui;

    public InitialProfileLoader (DataSourceCollectionInterface dataSources) {
        super(dataSources);

        profileLoaderGui = new ProfileLoaderGUI(dataSources, this);
    }

    public void loadProfile (ProfileLoaderObserverInterface observer) {
        this.observer = observer;

        profileLoaderGui.prompt();
    }

    public void loadSaved (File file) {
        String data              = "";
        ProfileInterface profile = loadProfile(file);

        if (profile instanceof ProfileInterface) {
            if (unavailableDataTypes.size() > 0)
                triggerNotice();

            observer.receiveProfile(profile);
        } else {
            triggerError();
        }
    }

    public void createNew (DataSourceInterface dataSource) {
        observer.receiveProfile(new Profile(dataSource, true));
    }

    public void canceled () {
        observer.receiveProfile(null);
    }

    protected void triggerNotice () {
        // Tell the user which data types are not available
    }

    protected void triggerError () {
        // Tell the user the data source is not available
    }
}
