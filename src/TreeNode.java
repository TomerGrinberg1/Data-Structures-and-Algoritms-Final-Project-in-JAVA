

public class TreeNode {
        int key;
        TreeNode left;
        TreeNode right;
        TreeNode parent;
       // int index;
     //   int d;
       // String color="white";
       // TreeNode rightSibling;
      public  TreeNode(int key) {
            this.key = key;
            right = null;
            left = null;
            parent=null;
          //  rightSibling=null;
           // if(isLeft())
           ///     this.index=this.parent.index*2+1;
       //   if(isRight())
              //  this.index=this.parent.index*2+2;
        }

    public TreeNode () {


    }
    public void setKey(int key){
        this.key=key;
    }
/*
    public void setD(int depth){
         this.d=depth;
            }

         boolean isLeft(){
            if(this.parent!=null &&this.parent.left==this)
                return true;
            return false;
        }
        boolean isRight(){
            if(this.parent!=null &&this.parent.right==this)
                return true;
            return false;

        }

*/
    }

