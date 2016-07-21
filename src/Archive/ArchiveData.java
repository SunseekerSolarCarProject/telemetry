/**
 * Sunseeker Telemetry
 *
 * @author Kai Gray <kai.a.gray@wmich.edu>
 * @date July 9, 2016
 */

package sunseeker.telemetry;

import java.io.File;
import java.io.FileWriter;
//import java.io.OutputStreamWriter;

import java.lang.Exception;
import java.io.IOException;

import java.util.List;

public class ArchiveData extends AbstractArchive {

    protected File file;

    protected FileWriter write;

    protected int counter = 0;
    protected String line = "";

    final String HEAD    = "Counter,";
    final String TIME    = "Time";
    final String SPEED   = "Speed";
    final String VOLTAGE = "Voltage";
    final String CURRENT = "Current";
    final String ERRORS  = "Errors";
    final String ERRNUL  = "NULL";
    final String MIN     = "Min";
    final String MAX     = "Max";


    ArchiveData (String fileName) throws IOException{

        /*
        * create text file with fileName from FileSelect class
        */
        file = new File(fileName + ".txt");
        
        try{
            write = new FileWriter(file);
        } catch (IOException e) {
            System.out.println("Failed to open file... ");
        } catch (Exception e) {
            System.out.println("Probably your fault... " + e);
        }

    }

    /*
    * Write headers to txt file in csv format;
    */
    protected void startFile (DataTypeCollectionInterface types) {
        
        line += HEAD;

        if (types == null)
            return;

        /* 
        * Grabs available headers, adds max/min, write to file
        */
        for(int i = 0; i < types.size(); i++) {
            line += types.get(i).getName() + DELIM + MIN + DELIM + MAX + DELIM;
        }
        
        line += ERRORS;

        writeData(line);
        this.counter++;
        System.out.println(line);
    }

    protected String packageData (DataTypeCollectionInterface types) {
        line = "";

        /*
        * Write headers to file
        */
        if(counter == 0)
            startFile(types);
            
        /*
        * Package data with "," as Delimiter
        */
        line = counter + DELIM;
        for(int i = 0; i < types.size(); i++) {
            line += types.get(i).getCurrentValue() + DELIM;
            line += types.get(i).getMinimumValue() + DELIM;
            line += types.get(i).getMaximumValue() + DELIM;
        }
        line += ERRNUL;

        
        this.counter++;
        return line;
    }

    /*
    * Write a line of text to file
    */
    protected void writeData (String line) { 

        try{
            write.append(line + "\n");

        } catch (IOException e){
            System.err.println("IOException");
        } catch (Exception e){
            System.err.println("Unknown Error" + e);
        }
            
    }

    /*
    * Properly close file(s)
    */
    public void closeAll () {
        try{
            write.close();
            System.out.println("File closed.");
        } catch(IOException e) {
            System.out.println("Failed to close file... \n Error:" + e);
        } catch(Exception e){
            System.out.println("Probably your fault... " + e);
        }
    }

    /*
    * Close and reopen as appendable
    */
    public void saveFile () {

        this.closeAll();
        
        try{
            write = new FileWriter (this.file, true);
            System.out.println("File Reopened.");
        } catch (IOException e) {
            System.out.println("Failed to properly reopen file.");
        } catch (Exception e) {
            System.out.println("Failed to save file");
        }

    }

}
