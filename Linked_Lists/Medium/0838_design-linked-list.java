class MyLinkedList {
    public class Node{
        private int val;
        private Node next;

        public Node(int val){
            this.val = val;
            this.next = null;
        }
        public Node(int val,Node next){
            this.val = val;
            this.next = next;
        }
    }
    private Node head;
    private Node tail;
    public int size;
    public MyLinkedList() {
        this.size=0;
    }
    
    public int get(int index) {
        if(index<0 || index>=size)return -1;
        Node temp = head;
        for(int i=0;i<index;i++){
            temp=temp.next;
        }
        return temp.val;
    }
    
    public void addAtHead(int val) {
        Node node = new Node(val);
        node.next = head;
        head = node;

        if(tail==null){
            tail = head;
        }
        size+=1;
    }
    
    public void addAtTail(int val) {
        if(tail == null){
            addAtHead(val);
            return;
        }
        Node node = new Node(val);
        tail.next = node;
        tail = node;
        size+=1;
    }
    
    public void addAtIndex(int index, int val) {
        if(index < 0 || index > size){
    return;
}
        if(index == 0){
            addAtHead(val);
            return;
        }
        if(index == size){
            addAtTail(val);
            return;
        }
        Node temp = head;
        for(int i=1;i<index;i++){
            temp=temp.next;
        }
        Node node = new Node(val,temp.next);
        temp.next = node;
        size++;
    }
    
    public void deleteAtIndex(int index) {
        if(index < 0 || index >= size){
            return;
}
        if(index == 0){
            DeleteFirst();
            return;
        }
        if(index == size-1){
            DeleteLast();
            return;
        }
        Node temp = head;
        for(int i=1;i<index;i++){
            temp = temp.next;
        }
        temp.next = temp.next.next;
        size--;
    }
    

    public void DeleteFirst(){
        if(head == null){
            System.out.println("List is empty");
            return;
        }
        head = head.next;
        if(head == null){
            tail = null;
        }
        size--;
    }
    public void DeleteLast(){
        if(head == null){
            System.out.println("List is empty");
            return;
        }
        if(head == tail){
            head = null;
            tail = null;
            size--;
            return;
        }
        Node temp = head;
        while(temp.next !=tail){
            temp=temp.next;
        }
        tail = temp;
        temp.next = null;
        size--;
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */