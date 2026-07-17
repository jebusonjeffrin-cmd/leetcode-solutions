class Solution {
    public int calPoints(String[] operations) {
        Stack<Integer> stk = new Stack<>();
        for(String operation:operations){
           if(operation.equals("+")){
                int n1 = stk.pop();int n2 = stk.pop();
                stk.push(n2);stk.push(n1);
                stk.push(n1+n2);
           } else if(operation.equals("D")){
                int top = stk.pop();
                stk.push(top);stk.push(2*top);
           }else if(operation.equals("C")){
                stk.pop();
           }else{
                stk.push(Integer.parseInt(operation)); 
           }
        }
        int ans=0;
        while(!stk.isEmpty()){
            ans += stk.pop();
        }
        return ans;
    }
}