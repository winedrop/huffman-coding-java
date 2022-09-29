package Project_3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class HuffmanCode {


    public static String huffmanCodeTable(String input, String fileName){//stores the huffman code in the inputted file
        BinaryTree comparator = new BinaryTree();
        PriorityQueue<BinaryTree> hT = new PriorityQueue<BinaryTree>((Comparator)comparator);
        int[] frequencies = frequencyCount(input);
        inputFrequencies(frequencies, hT);
        while(hT.size() > 1){
            BinaryTree a = hT.remove();
            BinaryTree b = hT.remove();
            hT.add(a.merge(b));//merge subtrees
        }
        BinaryTree huffmanCodeTree = hT.remove();
        huffmanCodeTree.generateHuffmanCode(fileName);//generate codes using the tree
        return "";
    }
    public static String encodeString(String text, String fileName){//decodes string based on provided huffman code file
        try {//read in huffman code table and store data
            List<String> input = Files.readAllLines(Paths.get(fileName));
            char[] characters = new char[input.size()];
            String[] code = new String[input.size()];
            for(int i = 0; i < input.size(); i++){
                String line = input.get(i);
                characters[i] = line.charAt(0);//first char of each line is the character
                code[i] = line.substring(2);   //corresponding to the code that follows
            }
            // loop through string and encode corresponding characters
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < text.length(); i++){
                for(int j = 0; j < characters.length; j++){
                    if(characters[j] == text.charAt(i)){
                        sb.append(code[j] + " ");
                    }
                }
            }
            String encodedText = sb.toString();
            encodedText.replaceAll(" ", "");
            return encodedText;
        } catch (IOException e) {
            System.out.println("file not found");
        }
        return "";
    }
    public static String decodeString(String encodedString, String fileName){
        try {//read in huffman code table and store data
            List<String> input = Files.readAllLines(Paths.get(fileName));
            char[] characters = new char[input.size()];
            String[] code = new String[input.size()];
            for(int i = 0; i < input.size(); i++){
                String line = input.get(i);
                characters[i] = line.charAt(0);//first char of each line is the character
                code[i] = line.substring(2);   //corresponding to the code that follows
            }

            // loop through table and construct binary tree
            BinaryTree huffmanTree = new BinaryTree();
            huffmanTree.addRoot(0,'\0');
            for(int i = 0; i < input.size(); i++){
                BinaryTree.Node current = huffmanTree.root();
                for(int j = 0; j < code[i].length(); j++){

                    if(code[i].charAt(j) == '0'){
                        if(huffmanTree.hasLeft(current)){
                            current = current.left;
                            continue;
                        }
                        if(code[i].length() == j+1){ //at external node or end of code, add corresponding character
                            current = huffmanTree.addLeft(0,characters[i],current);

                        }else { //other values can be ignored
                            current = huffmanTree.addLeft(0, '\0', current);
                            if(huffmanTree.hasLeft(current)){
                                current = current.left;
                            }
                        }
                    }else{// == 1
                        if(huffmanTree.hasRight(current)){
                            current = current.right;
                            continue;
                        }
                        if(code[i].length() == j+1){ //end of code/at external node, add corresponding character
                            current = huffmanTree.addRight(0,characters[i], current);
                        }else { //other values can be ignored
                            current = huffmanTree.addRight(0, '\0', current);
                            if(huffmanTree.hasRight(current)){
                                current = current.right;
                            }
                        }
                    }
                }
            }
            //loop through encoded string and follow binary tree
            StringBuilder sb = new StringBuilder();
            BinaryTree.Node current = huffmanTree.root();
            for (int i = 0; i < encodedString.length(); i++) {
                if (encodedString.charAt(i) == '0') {
                    if(!huffmanTree.hasLeft(current)){
                        sb.append(current.e.value);
                        current = huffmanTree.root();
                    }else {
                        current = huffmanTree.left(current);
                    }
                } else { // == 1
                    if(!huffmanTree.hasRight(current)){
                        sb.append(current.e.value);
                        current = huffmanTree.root();
                    }else {
                        current = huffmanTree.right(current);
                    }
                }
            }
            String decodedString = sb.toString();

            return decodedString;
        } catch (IOException e) {
            System.out.println("file not found");
        }
        return "";
    }
    private static int[] frequencyCount(String input){//counts frequency of each character in string
        int[] frequencies = new int[128];
        for(int i = 0; i < input.length(); i++){
            int asciiValue = (int)input.charAt(i);
            frequencies[asciiValue]++;
        }
        return frequencies;
    }
    private static void inputFrequencies(int[] frequencyTable, PriorityQueue<BinaryTree> huffmanTree){//put all frequencies and characters into a priorityQueue
        for(int i = 0; i < frequencyTable.length; i++){
            if(frequencyTable[i] > 0){
                BinaryTree entry = new BinaryTree();
                entry.addRoot(frequencyTable[i], ((char)i));
                huffmanTree.add(entry);
            }
        }
    }

}
