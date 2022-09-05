import java.io.DataOutputStream;
import java.io.IOException;

class Tree {
    TreeNode root;
  //  static int maxLevel = -1;
    //static TreeNode res = null;
    // static int treeSize = 0;

    //int nodeIndex=0;
    public Tree(int rootData) {

        root = new TreeNode(rootData);
        //  root.value = rootData;
    }

    public Tree() {
        root = null;
        //  root.value = rootData;
    }
    public static void swap(TreeNode r, TreeNode c){
    int tempKey = r.key;
    r.key = c.key;
    c.key = tempKey;
    }
    /*
    public int countNumNodes(TreeNode root) {
        if (root == null)
            return (0);
        return (1 + countNumNodes(root.left) + countNumNodes(root.right));
    }
    static TreeNode leafDelete(TreeNode root) {
        if (root == null) {
            return null;
        }
        if (root.left == null && root.right == null) {
            return null;
        }
        root.left = leafDelete(root.left);
        root.right = leafDelete(root.right);

        return root;
    }

*/
    /*
        public static TreeNode InitialTree(int[] arr, int i) {
        //TreeNode root = null;
        // Base case for recursion
        // if(this.root==null)
          //  if(root.left!=null)

        TreeNode root=null;
        if (i < arr.length) {
            root = new TreeNode(arr[i]);

            if(root.left!=null)
                root.left.parent=root;
            if(root.right!=null)
                root.right.parent=root;
            //treeSize++;
            // insert left child
            //Heap.HeapifyTree(root);
            root.left = InitialTree(arr, 2 * i +1 );

            // insert right child

            root.right = InitialTree(arr, 2 * i + 2);
        }
        //  this.root=root;
        return root;
    }
*/

    public  void printByLayer(DataOutputStream out) throws IOException {
        // Base Case
        if (this.root == null)
            return;
        // Create an empty queue for level order traversal
        Queue q = new Queue();
        // Enqueue Root and initialize height
        q.enqueue(this.root);
        while (true) {
            // nodeCount (queue size) indicates number of nodes
            // at current level.
            int nodeCount = q.count;
            if (nodeCount == 0)
                break;

            // Dequeue all nodes of current level and Enqueue all
            // nodes of next level
            while (nodeCount > 0) {
                TreeNode node = q.dequeue();
                out.writeBytes("" + node.key);
                if (nodeCount > 1)
                    out.writeBytes(",");
                if (node.left != null)
                    q.enqueue(node.left);
                if (node.right != null)
                    q.enqueue(node.right);
                nodeCount--;
            }
            out.writeBytes(System.lineSeparator());
        }
    }
        public static int log2(int N)
    {

        // calculate log2 N indirectly
        // using log() method
        int result = (int)(Math.log(N) / Math.log(2));

        return result;
    }


    static TreeNode find(TreeNode root, int index) {
        int arraySize= log2(index);
       // System.out.println("arraysuze is :"+arraySize);
        int A[]=new int[arraySize];
        int  i=0;
        while(index!=1&& i<A.length) {
            A[i]=index;
            index/=2;
            i++;
        }
       // for(int k=A.length-1;k>=0;k--)
           // System.out.println(A[k]);
        for(int k=arraySize-1;k>=0;k--)
            if(A[k]%2==0)
                root=root.left;
            else
            root=root.right;


        return root;
    }

    // Returns value of deepest node
    /*
    static TreeNode deepestNode(TreeNode root) {
        // Initialize result and max level
        /* int res = -1;

        // Updates value "res" and "maxLevel"
        // Note that res and maxLen are passed
        // by reference.
        find(root, 0);
        return res;

    }
*/
}



