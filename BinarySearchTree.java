import java.util.Scanner;

public class BinarySearchTree {
    // class for node
    static class Node {
        // a node has data, and may have up to two child nodes
        int data;
        Node left;
        Node right;

        // constructor for a node
        public Node(int input_data){
            data = input_data;
            left = right = null;
        }
    }

    // a BST has a root
    Node root;
    // count is for part a
    int count = 0;
    // sum is for part b
    int sum = 0;

    // constructor for BST
    public BinarySearchTree(){
        // empty tree means root is null
        root = null;
    }

    // insert method
    public Node insert(Node root, int input_data){
        // If tree is empty
        if (root == null){
            root = new Node(input_data);
            return root;
        }

        // Tree isn't empty
        // If data is less than or equal to the root, go left
        if (input_data <= root.data){
            root.left = insert(root.left, input_data);
        }
        // Else, the data is larger than the root, so go right
        else {
            root.right = insert(root.right, input_data);
        }
        return root;
    }

    // method for part c
    public int find_maximum_value(Node root){
        // if tree is empty
        if (root == null){
            return -1;
        }
        // in a BST, the maximum_value will be the rightmost node,
        // so I keep searching right until it's null
        while(root.right != null){
            root = root.right;
        }
        return root.data;
    }

    // method for part a and b
    public void count_nodes_and_calculate_sum(Node root){
        if (root != null){
            // update the two counters
            count += 1;
            sum += root.data;
            // do the same for both child nodes
            count_nodes_and_calculate_sum(root.left);
            count_nodes_and_calculate_sum(root.right);
        }
    }

    public static void main(String[] args){
        // create BST
        BinarySearchTree BST = new BinarySearchTree();

        System.out.println("Input an non-negative integer as data for the BST.");
        System.out.println("After you have inputted all data, type - to finish input");

        // boolean that allows the user to keep on inputting data
        boolean input = true;

        // create scanner
        Scanner scan = new Scanner(System.in);

        // data_input takes the string from the scanner, data_int is to put into the insert method
        String data_input;
        int data_int;

        while(input){
            System.out.println("\nInput integer: ");

            // retrieve input
            data_input = scan.nextLine();

            // if the input is -, then stop it from looping
            if (data_input.equals("-")){
                input = false;
            }
            // else, make sure the input is valid
            else {
                try {
                    // use parseInt to turn string input into integer
                    data_int = Integer.parseInt(data_input);
                    // if the data is larger than or equal to 0, it is valid
                    if (data_int >= 0){
                        // so, insert the data into the BST
                        BST.root = BST.insert(BST.root, data_int);
                    }
                    // if not, then it is not valid
                    else{
                        System.out.println("Inputted data isn't valid.");
                    }
                }
                // catches the error when the input isn't an integer
                catch (NumberFormatException e) {
                    System.out.println("Inputted data isn't valid.");
                }
            }
        }

        // call method for a and b
        BST.count_nodes_and_calculate_sum(BST.root);

        // call method for c
        int maximum_value = BST.find_maximum_value(BST.root);

        // print out results
        // part a
        System.out.println("\na. Number of items/nodes: " + BST.count);
        // part b
        System.out.println("\nb. Sum of all keys: " + BST.sum);
        // part c
        System.out.println("\nc. Maximum value: " + maximum_value);
    }
}
