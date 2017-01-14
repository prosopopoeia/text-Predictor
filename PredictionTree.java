 



/*
 * Tree to serve as the data structure of the text prediction tool.
 * 
 * @author Jason Isaacs
 * @version 1.0
 */
import java.util.*;

public class PredictionTree {
    private final static int ALPHABET_SIZE = 26;
    private Node root;
    
    /*
    * constructor creates a single root node
    * with its char set to null
    */
    public PredictionTree() {
        root = new Node();
    }
    /**
     * Insert a new word into the '26-ary' tree
     * 
     * @param word the word to be inserted into the tree
     */
    public void insertWord (String word) {
        Node current = root;
        char[] characters = word.toCharArray();
        for (int i = 0; i < word.length(); i++) {
            //if the node for the character
            if (current.children[characters[i] - 'a'] == null) {
                current.children[characters[i] - 'a'] 
                        = new Node(characters[i]);
                current = current.children[characters[i] - 'a'];                
            }
            else {
                current = current.children[characters[i] - 'a'];
            }
        }
        current.isWord = true;
    }
    
    public boolean hasWord(String word) {
        char[] letters = word.toCharArray();
        Node current = root;
        for (int i : letters) {
            current = current.children[i - 'a'];
        }
        return current.isWord;
    }
    public void printTree(Node root) {
        
    }
    
    /**
     * finds words based on the prefix supplied
     * 
     * @param prefix prefix used as a basis for the search
     * @return an array of up to 10 words that start with the supplied prefix
     */
    public String[] predictText(String prefix) {
        Node afterPrefix = traverseToEndOfPrefix(prefix);
        ArrayList<String> wordset
                = getWords(afterPrefix, prefix, new ArrayList<>());
        String[] words = new String[10];
        
        for (int i = 0; i < wordset.size(); i++) {
            words[i] = wordset.get(i);
        }        
        return words;
    }
    
    /**
     * traverses through the tree until the node that represents the letter
     * at the end of the prefix is reached
     * @param prefix prefix guide to desired node
     * @return node corresponding to the last letter in prefix
     */
    private Node traverseToEndOfPrefix(String prefix) {
        char[] letters = prefix.toCharArray();
        Node current = this.root;
        
        for (int i : letters) {
            if(current != null) {
            current = current.children[i - 'a'];
            }
        }
        return current;    
    }
    
    /**
     * gets a list of 10 words that have <em>prefix</em> as a prefix
     * @param root current node
     * @param prefix current prefix
     * @param wordList list of words that share the originating prefix
     * @return an Arraylist of words that start with prefix
     */
    private ArrayList<String> getWords(
            Node root, String prefix, ArrayList<String> wordList) {
        
        ArrayList<String> currentWordList = wordList;
        Node current = root;
        
        if (current == null) {
            return currentWordList;
        }
        
        if (currentWordList.size() == 10) {//name the number of words --also null check isn't necessary, 
            return currentWordList;          
        }
        if (current.isWord) {
            currentWordList.add(prefix);
        }
        
        for (Node node : current.children) {
            if (node != null && !prefix.equals("")) {
                //add next letter to prefix before recursive call
                String currentPrefix = prefix + node.letter;
                currentWordList = getWords(node, currentPrefix, currentWordList);
            }
        }
        return currentWordList;
    }
    
    /**
     * static inner class to create nodes for predictive tree
     */
    private static class Node {
        private char letter;
        private Node[] children;
        private boolean isWord;
        
        /**constructor for all non-root nodes
         * 
         * @param letter character value node contains 
         */
        public Node(char letter) {
            this.letter = letter;
            children = new Node[ALPHABET_SIZE];
            isWord = false;
        }
        
        /**constructor for the root node which requires no
         * value for the letter variable.
         * 
         */
        public Node() {
            children = new Node[ALPHABET_SIZE];
            isWord = false;
        }
    }//end Node class
    
    
}
