class MyHashSet {
    int[] MyHashSet = new int[1000000];
    int count=0;
    public MyHashSet() {
        
    }
    
    public void add(int key) {
        boolean flag = true;
        for(int i=0;i<count;i++){
            if(MyHashSet[i] == key)flag=false;
        }
        if(flag)MyHashSet[count++] = key;
    }
    
    public void remove(int key) {
        int start=0;
        for(int i=0;i<=count;i++){
            if(MyHashSet[i] == key){
                start = i;
                for(int k=start;k<count;k++){
                    MyHashSet[k] = MyHashSet[k+1];
                }
                count--;
                return;
            }
        }
    }
    
    public boolean contains(int key) {
        boolean found = false;
        for(int i=0;i<count;i++){
            if(MyHashSet[i] == key){
                found = true;
                break;
            }
        }
        if(found)return true;
        return false;
    }
}

/**
 * Your MyHashSet object will be instantiated and called as such:
 * MyHashSet obj = new MyHashSet();
 * obj.add(key);
 * obj.remove(key);
 * boolean param_3 = obj.contains(key);
 */