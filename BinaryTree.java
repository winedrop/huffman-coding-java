package Project_3;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;

public class BinaryTree implements Comparator<BinaryTree> {
    class Node{
        class Entry{
            Entry(){
                freq = 0;
                value = 0;
            }
            int freq;
            char value;
        }
        public Node(){
            e = new Entry();
            right = null;
            left = null;
            parent = null;
        }
        Entry e;
        Node left;
        Node right;
        Node parent;
    }

    public BinaryTree(){
    }

    public int compare(BinaryTree a, BinaryTree b) {
        if(a.root.e.freq < b.root.e.freq){
            return -1;
        }else if(a.root.e.freq == b.root.e.freq){
            return 0;
        }else{ //greater
            return 1;
        }
    }
    public void addRoot(int freq, char value) {
        root = new Node();
        root.e.freq = freq;
        root.e.value = value;
    }
    public BinaryTree merge(BinaryTree b){
        BinaryTree merged = new BinaryTree();
        int frequencySum = this.root.e.freq + b.root.e.freq;
        merged.addRoot(frequencySum, '\0');
        merged.root.e.freq = frequencySum; //root of new tree contains sum of frequencies of sub trees
        merged.root.left = this.root;         // point new root to existing trees
        merged.root.right = b.root;
        b.root.parent = merged.root;       // point existing trees to new root
        this.root.parent = merged.root;
        return merged;
    }
    public Boolean hasLeft(Node n){
        return !(n.left == null);
    }
    public Boolean hasRight(Node n){
        return !(n.right == null);
    }
    public Node left(Node n){
        return n.left;
    }
    public Node right(Node n){
        return n.right;
    }
    public Node root(){
        return root;
    }
    public Node addLeft(int freq, char value, Node N){
        Node n = new Node();
        N.left = n;
        n.parent = N.left;
        n.e.freq = freq;
        n.e.value = value;
        return n;
    }
    public Node addRight(int freq, char value, Node N){
        Node n = new Node();
        N.right = n;
        n.parent = N.right;
        n.e.freq = freq;
        n.e.value = value;
        return n;
    }
    public void generateHuffmanCode(String fileName){//generate codes from the tree and store in file
        String code = "";
        try{
            fw = new FileWriter(fileName);
            preorder(root, code, fw);
            fw.close();
        }catch(IOException e){
            System.out.println("file not found");
        }
        return;
    }
    private void preorder(Node root, String code,FileWriter fw) throws IOException {
        if(root.left != null && root.right != null){
            preorder(root.left, code.concat("0"),fw);
            preorder(root.right, code.concat("1"),fw);
        }else{//external node, write to file
            System.out.println(root.e.value + " : " + code);
            fw.write(root.e.value + " " + code+"\n");
            return;
        }
    }
    FileWriter fw;
    Node root;
}
