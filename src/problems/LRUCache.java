package problems;
import java.util.HashMap;

public class LRUCache {
    // some variables
	private int curr_size;
    private int capacity;
    private HashMap<Integer, Entry> hm;
    private Entry head;
    private Entry tail;
    // functions
    public LRUCache(int capacity) {
        head = new Entry();
        tail = new Entry();
        head.next = tail;
        tail.prev = head;
        
        this.capacity = capacity;
        this.curr_size = 0;
        hm = new HashMap<Integer, Entry>();
    }
    
    public int get(int key) {
    	Entry node = hm.get(key);
    	if (node != null)
    	{
    		move_to_head(node);
    		return node.value;
    	}
    	else
    	{
    		return -1;
    	}
    }
    
    public void set(int key, int value) {
        if (this.curr_size == 0)//initial insert
        {
        	// create a new node
        	Entry node = new Entry();
        	node.key = key;
        	node.value = value;
        	// update the cache
        	head.next = node;
        	node.prev = head;
        	node.next = tail;
        	tail.prev = node;
        	
        	this.curr_size++;
        	// update the hashmap
        	hm.put(key, node);
        }
        else
        {
        	//check if the key already exist
        	Entry node = hm.get(key);
        	if (node == null)//doesn't exist
        	{
        		// create a new node
        		node = new Entry();
        		node.key = key;
        		node.value = value;
        		// update the cache
        		
        		set_new_head(node);
        		
        		this.curr_size++;
        		if (this.curr_size > this.capacity)
        		{
        			hm.remove(tail.prev.key);
        		
        			remove_tail();
        			this.curr_size--;
        		}
        		// update the hashmap
        		hm.put(key, node);
        		
        	}
        	else if  (node.key == key && node.value != value)// duplicate
        	{
        		//update
        		node.value = value;
        		hm.put(node.key, node);
        		move_to_head(node);
        	}
        	else // already exist. size doesn't change
        	{
        		move_to_head(node);
        	}
        }
    	
    }
    
    public void remove_tail () 
    {
    	if (tail.prev == head)
    	{
    	    return;
    	}
    	else
    	{
    	    tail.prev = tail.prev.prev;
    	    tail.prev.next = tail;
    	}
    }
    
    public void set_new_head(Entry node)
    {
        node.next = head.next;
        node.next.prev = node;
        
    	head.next = node;
    	node.prev = head;
    	
    }
    
    public void move_to_head (Entry node)
    {
    	if (head.next == tail) return;
    	else
    	{
    	    node.prev.next = node.next;
    	    node.next.prev = node.prev;
    	    
    	    set_new_head(node);
    	}
    	
    }
    
    public void show ()
    {
    	System.out.println("currsize is " + curr_size);
    	if (head == null)
    	{
    		System.out.println("empty");
    	}
    	else
    	{
    		Entry tmp = head;
    		// print the keys
    		while (tmp != null)
    		{
    			System.out.print(tmp.key + "->");
    			tmp = tmp.next;
    		}
    		System.out.println();
    		// print the values
    		tmp = head;
    		while (tmp != null)
    		{
    			System.out.print(tmp.value + "<-");
    			tmp = tmp.next;
    		}
    		System.out.println();
    		System.out.println();
    	}
    }
    
    public static void test ()
    {

    	/*
    	LRUCache t = new LRUCache(2);
    	t.set(2, 1);
    	t.set(2, 2);
    	
    	System.out.println(t.get(2));
    	System.out.println("after getting 2");
    	t.show();
    	
    	t.set(4, 1);
    	System.out.println("after setting 4");
    	t.show();
    	
    	t.get(1);
    	t.get(2);
    	System.out.println("after getting 1 and 2");
    	t.show();
    	*/
    	LRUCache t = new LRUCache(1);
    	t.set(2, 1);
    	t.show();
    	t.get(2);
    	t.show();
    	t.set(3, 2);
    	t.show();
    	t.get(2);
    	t.show();
    	t.get(3);
    	t.show();
    }
}

class Entry {
	int key;
	int value;
	Entry prev;
	Entry next;
}