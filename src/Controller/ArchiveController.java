/**
 * Sunseeker Telemetry
 *
 * @author Kai Gray <kai.a.gray@wmich.edu>
 * @date July 9, 2016
 */

package sunseeker.telemetry;

import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.lang.Thread;

class ArchiveController {
    protected FileSelect select;

    protected ArchiveData archive;

    protected OpenData open;

    ArchiveController () {
        select = new FileSelect();
    }

    public void start () throws IOException {
        
        promptForSaveFile();
        
    }        

    public void stop () throws IOException {
        try {
            archive.closeAll();
        } catch (IOException e) {
            System.out.println("could not close file...");
        }
    }

    public void promptForSaveFile () throws IOException {

        /*
         * select and open file
         */
        try{            
            archive = new ArchiveData(select.chooseSaveFile());
        }
        catch (IOException e){
            System.out.println("Could not write to file" + e);
        }
        catch (Exception e){
            System.out.println("failure: " + e);
        }
          
    }

    public void saveFile () {
        try{            
            archive.saveFile();
        }
        catch (IOException e){
            System.out.println("Could not write to file" + e);
        }
        catch (Exception e){
            System.out.println("failure: " + e);
        }
    }

}
