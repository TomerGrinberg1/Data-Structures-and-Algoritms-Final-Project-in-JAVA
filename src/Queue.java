public class Queue {
    private LinkedList<TreeNode> linkedList;
    private LinkedList<TreeNode> tail;
    protected int count;

    public Queue()
    {
        this.linkedList = null;
        this.tail= null;
        this.count=0;

    }

    public void enqueue(TreeNode x)
    {
        LinkedList<TreeNode> node= new LinkedList<>(x);
        count++;
        if(this.tail != null)
        {
            this.tail.setNext(node);
            node.setPrev(this.tail);
            this.tail = node;
        }
        else
        {
            this.linkedList= node;
            this.tail= node;
        }
    }

    public TreeNode dequeue()
    {
        TreeNode node;
        if(this.linkedList == null)
        {
            return null;
        }
        node= this.linkedList.getHead().getValue();
        count--;
        this.linkedList = this.linkedList.getNext();
        if(this.linkedList == null)
        {
            this.tail =null;
        }
        else
        {
            this.linkedList.setPrev(null);
        }
        return node;
    }


}
