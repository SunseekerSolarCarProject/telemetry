/**
 * Sunseeker Telemetry
 *
 * @author Kai Gray <kai.a.gray@wmich.edu>
 * @date July 9, 2016
 */

package sunseeker.telemetry;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

class ArchiveController extends AbstractArchive {
    protected DataTypeCollectionInterface types;
    
    protected FileSelect select;

    protected ArchiveData archive;

    protected OpenData open;

    protected boolean writing = false;

    ArchiveController (DataTypeCollectionInterface dataTypes) {
        select = new FileSelect();

        this.types = dataTypes;
    }

    public void start () {
        if(archive == null)
            promptForSaveFile();

        archive.writeData(archive.packageData(types));

        setWriting(true);
    }

    public void refresh () {
        try{
            archive.writeData(archive.packageData(types));
        } catch (Exception e) {}
    }

    public void stop () {
        archive.closeAll();
        
        setWriting(false);
    }

    public void promptForSaveFile () {

        /*
         * select and open file
         */
        try{            
            archive = new ArchiveData(select.chooseSaveFile());
        } catch (IOException e) {
            System.out.println("Could not write to file" + e);
        } catch (Exception e) {
            System.out.println("failure: " + e);
        }
          
    }

    public void saveFile () {          
        archive.saveFile();
    }


    public void setTypes (DataTypeCollectionInterface types) {
        this.types = types;
    }

    protected void setWriting (boolean b) {
        this.writing = b;
    }

    public boolean getWriting () {
        return this.writing;
    }
}
