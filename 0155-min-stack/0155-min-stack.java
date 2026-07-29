class MinStack {
    Stack<Integer> stk,minstk;
    public MinStack() {
        stk = new Stack<>();
        minstk = new Stack<>();
    }
    
    public void push(int value) {
        stk.push(value);
        //System.out.println("stk :"+stk.peek());
        if(minstk.isEmpty() || value <= minstk.peek()){
            minstk.push(value);
            //System.out.println("minstk :"+minstk.peek());
        }
    }
    
    public void pop() {
        //int stkTop  = stk.peek() , minStkTop = minstk.peek();
        if(stk.peek().equals(minstk.peek())){
            //System.out.println("minstk pop:"+minstk.peek());
            minstk.pop();
        }
    
    stk.pop();
        
    }
    
    public int top() {
        return stk.peek();
    }
    
    public int getMin() {
        //System.out.println(minstk.peek());
        return minstk.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(value);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */