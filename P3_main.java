package Project_3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class P3_main {

    public static void main(String[] args) {
        //generate a huffman code table and store in file
        HuffmanCode.huffmanCodeTable("a fast runner need never be afraid of the dark", "huffmanTable1.txt");
        //encode string using file
        String encoded1 = HuffmanCode.encodeString("a fast runner need never be afraid of the dark", "huffmanTable1.txt");
        //decode string using file
        String decoded1 = HuffmanCode.decodeString(encoded1, "huffmanTable1.txt");
        System.out.println("original text: " + "a fast runner need never be afraid of the dark");
        System.out.println("encoded text : " + encoded1);
        System.out.println("decoded text : " + decoded1);
        System.out.println();

        HuffmanCode.huffmanCodeTable("this is a test", "huffmanTable2.txt");
        String encoded2 = HuffmanCode.encodeString("this is a test", "huffmanTable2.txt");
        String decoded2 = HuffmanCode.decodeString(encoded2, "huffmanTable2.txt");
        System.out.println("original text: " + "this is a test");
        System.out.println("encoded text : " + encoded2);
        System.out.println("decoded text : " + decoded2);
        System.out.println();

        HuffmanCode.huffmanCodeTable("here is a simple example", "huffmanTable3.txt");
        String encoded3 = HuffmanCode.encodeString("here is a simple example", "huffmanTable3.txt");
        String decoded3 = HuffmanCode.decodeString(encoded3, "huffmanTable3.txt");
        System.out.println("original text: " + "here is a simple example");
        System.out.println("encoded text : " + encoded3);
        System.out.println("decoded text : " + decoded3);
        System.out.println();

        HuffmanCode.huffmanCodeTable("computer science is a fun major", "huffmanTable4.txt");
        String encoded4 = HuffmanCode.encodeString("computer science is a fun major", "huffmanTable4.txt");
        String decoded4 = HuffmanCode.decodeString(encoded4, "huffmanTable4.txt");
        System.out.println("original text: " + "computer science is a fun major");
        System.out.println("encoded text : " + encoded4);
        System.out.println("decoded text : " + decoded4);
        System.out.println();
    }
}
