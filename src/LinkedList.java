public class LinkedList<T> {

    T value;
    private LinkedList<T> head;
    private LinkedList<T> next;
    private LinkedList<T> prev;


    public LinkedList(T value) {
        this.value = value;
        this.next = null;
        this.prev= null;
        this.head= this;
    }

    public LinkedList() {
        this.value = null;
        this.next = null;
        this.prev= null;
        this.head= this;
    }

    public T getValue(){return value;}
    public LinkedList<T> getNext() {return this.next;}
    public void setNext(LinkedList<T> node){this.next= node;}
    public LinkedList getPrev() {return this.prev;}
    public void setPrev(LinkedList<T> node){this.prev= node;}
    public void setValue(T value) {this.value = value;}
    public LinkedList<T> getHead() {return head;}
    public void setHead(LinkedList<T> head) {this.head = head;}

}