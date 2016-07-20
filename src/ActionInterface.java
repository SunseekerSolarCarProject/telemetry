/**
 * Sunseeker Telemetry
 *
 * @author Kai Gray <kai.a.gray@wmich.edu>
 * @date July 12, 2016
 */

package sunseeker.telemetry;

interface ActionInterface {

    /*
    * menu actions
    */
    final String SOURCE = "Data Source";
    final String ACTION_SOURCE      = "Select Data Source";

    final String FILE   = "File";
    final String ACTION_FILE_SELECT = "New File";
    final String ACTION_FILE_SAVE   = "Save File";
    final String ACTION_FILE_CLOSE  = "Close File";

    final String DATA   = "Data";
    final String ACTION_DATA_START   = "Start Session";
    final String ACTION_DATA_END     = "End Session";
    final String ACTION_DATA_RESTART = "Restart Data";
}