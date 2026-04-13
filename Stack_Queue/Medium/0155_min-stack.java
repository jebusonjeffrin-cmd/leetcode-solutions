class MinStack {
    int[] stack = new int[10000];
    int ctr=-1;
    public MinStack() {
        
    }
    
    public void push(int val) {
        stack[++ctr] = val;
    }
    
    public void pop() {
        stack[ctr--] = 0;
    }
    
    public int top() {
        return stack[ctr];
    }
    
    public int getMin() {
        if(ctr<0)return -1;
        int min=Integer.MAX_VALUE;
        for(int i=0;i<=ctr;i++){
            if(stack[i] < min)min=stack[i];
        }
        return min;
    }
    private boolean isEmpty(){
        if(ctr<0){
            return true;
        }return false;
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */