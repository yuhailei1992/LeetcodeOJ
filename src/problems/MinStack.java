package problems;
import java.util.Stack;

class MinStack {
    
    private Stack<Integer> data = new Stack<Integer>();
    private Stack<Integer> min = new Stack<Integer>();
    
    public void push(int x) {
        data.push(x);
        if (min.isEmpty() || x <= getMin())// <= is very subtle and clever
        {
            min.push(x);
        }
    }

    public void pop() {
        if (data.peek().equals(min.peek()))
        {
            min.pop();
        }
        data.pop();
    }

    public int top() {
        return data.peek();
    }

    public int getMin() {
        if (data.isEmpty())
            return Integer.MAX_VALUE;
        else
        {
            return min.peek();
        }
    }
    
    public void test(){
    	MinStack a = new MinStack();
    	a.push(-3);
    	System.out.println(a.getMin());
    }
}