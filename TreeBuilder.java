
 

/**
 * Builds a tree representing a dictionary
 * @author Jason Isaacs
 * @version 1.0
 */
import java.util.Scanner;

public class TreeBuilder {
    
    //String filePath;
    
    public TreeBuilder (String filePath) {
        //this.filePath = filePath;
    }
    
    public static PredictionTree buildDictionaryTree(String dictionaryPath) {
        FileIO fileRetriever = new FileIO();
        String dictionaryString = fileRetriever.getFileContents(dictionaryPath);
        PredictionTree dictionaryTree = new PredictionTree();
        Scanner scanDictionaryString = new Scanner(dictionaryString);
        
        while(scanDictionaryString.hasNextLine()) {
            String entry = scanDictionaryString.nextLine();
            dictionaryTree.insertWord(entry.toLowerCase());
        }
        return dictionaryTree;       
    }
}
