/**
 * Sunseeker Telemetry
 *
 * @author Alec Carpenter <alecgunnar@gmail.com>
 * @date July 19, 2016
 */

package sunseeker.telemetry;

import java.lang.Runnable;

class Application implements Runnable {
    DataController dataController;

    MainController mainController;

    InitialProfileLoader profileLoader;

    public void run () {
        /*
         * Create the data controller
         */
        dataController = new DataController();

        /*
         * Create the main controller
         */
        mainController = new MainController();

        /*
         * Allow the user to select their profile
         */
        profileLoader = new InitialProfileLoader(dataController.getDataSources());
        loadProfile();
    }

    protected void loadProfile () {
        profileLoader.loadProfile(new ProfileLoaderObserverInterface() {
            public void receiveProfile (ProfileInterface profile) {
                if (profile instanceof ProfileInterface) {
                    if (!dataController.start(profile.getDataSource())) {
                        loadProfile();
                        return;
                    }

                    mainController.start(profile);

                    /*
                     * This will allow us to respond to the user shutting the app down
                     */
                    Runtime.getRuntime().addShutdownHook(new ShutdownController(profile));
                } else {
                    System.exit(1);
                }
            }
        });
    }
}
