import java.io.DataOutputStream;
import java.io.IOException;

class Tree {
    TreeNode root;


    public Tree(int rootData) {

        root = new TreeNode(rootData);

    }

    public Tree() {
        root = null;
    }

    public static void swap(TreeNode r, TreeNode c) {
        int tempKey = r.key;
        r.key = c.key;
        c.key = tempKey;
    }

    public void printByLayer(DataOutputStream out) throws IOException {
        if (this.root == null)
            return;
        Queue q = new Queue();
        q.enqueue(this.root);
        while (true) {
            int nodeCount = q.count;
            if (nodeCount == 0)
                break;

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

    public static int log2(int N) {

        int result = (int) (Math.log(N) / Math.log(2));

        return result;
    }


    static TreeNode find(TreeNode root, int index) {
        int arraySize = log2(index);
        int A[] = new int[arraySize];
        int i = 0;
        while (index != 1 && i < A.length) {
            A[i] = index;
            index /= 2;
            i++;
        }
        for (int k = arraySize - 1; k >= 0; k--)
            if (A[k] % 2 == 0)
                root = root.left;
            else
                root = root.right;


        return root;
    }


}



