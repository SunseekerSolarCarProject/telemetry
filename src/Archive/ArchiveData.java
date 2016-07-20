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
    final String ERRNUL  = "Err. Null";
    final String MIN     = "Min.";
    final String MAX     = "Max.";


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
    * add headers to file in csv format;
    */
    protected void startFile (List<DataTypeInterface> data) {
        
        line += HEAD;

        for(int i = 0; i < data.size(); i++) {
            line += data.get(i).getName() + DELIM + MIN + DELIM + MAX + DELIM;
        }
        line += ERRORS;

        writeData(line);
        this.counter++;
    }

    protected String packageData (List<DataTypeInterface> data) {
        line = "";

        /*
        * Write headers to file
        */
        if(counter == 0){
            startFile(data);
            counter++;
        }
            
        /*
        * Package data with "," as DELIM
        */
        line = counter + DELIM;
        for(int i = 0; i < data.size(); i++) {
            line += data.get(i).getCurrentValue() + DELIM;
            line += data.get(i).getMinimumValue() + DELIM;
            line += data.get(i).getMaximumValue() + DELIM;
        }

        line += ERRNUL;

        
        counter++;

        return line;
    }

    /*
    * add a line of text to file
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
    * properly close file(s)
    */
    public void closeAll () throws IOException {
        try{
            write.close();
            System.out.println("File closed.");
        } catch(IOException e) {
            System.out.println("Failed to close file... \n Error:" + e);
        } catch(Exception e){
            System.out.println("Probably your fault... " + e);
        }
    }

    public void saveFile () throws IOException {

        this.closeAll();
        
        try{
            write = new FileWriter (this.file, true);
            System.out.println("File Saved.");
        } catch (IOException e) {
            System.out.println("Failed to properly reopen file.");
        } catch (Exception e) {
            System.out.println("Failed to save file");
        }

    }

}
