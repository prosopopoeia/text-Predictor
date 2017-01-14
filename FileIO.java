 
/**
 * Performs basic file i/o. 
 * 
 * @author Jason Isaacs
 * @version v. 1.2
 */
import java.io.*;

public class FileIO
{
    private String filename;
   /**
     * Takes a file path and returns a string representing the contents of the 
     * file found at given file path
     * 
     * @param  filename name of desired file
     * @return String of contents of file
     */
    public String getFileContents(String filename) {
        String filepath = filename;
        StringBuilder textFromFile = new StringBuilder();
        BufferedReader inputStream = null;
        try {
            inputStream = new BufferedReader(new FileReader(filepath));
            String line;
            while ((line = inputStream.readLine()) != null) {
                   textFromFile.append(line);
                   textFromFile.append(System.lineSeparator());
            }
        } 
        catch (IOException e) {
            System.out.println("An error has occurred, the inputStream will" +
                    " be closed, make sure your filepath is valid:\n" + e);  
        } finally {
            if (inputStream != null) {
                try {
                     inputStream.close();
                } catch (IOException e){
                    System.out.println("An error has occurred, the inputStream" 
                            + "did not close \n" + e);
                }
            }
        }
        return textFromFile.toString();
    }// end getFileContents
    
}
