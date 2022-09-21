

public class TreeNode {
    int key;
    TreeNode left;
    TreeNode right;
    TreeNode parent;

    public TreeNode(int key) {
        this.key = key;
        right = null;
        left = null;
        parent = null;
    }


    public void setKey(int key) {
        this.key = key;
    }
}

