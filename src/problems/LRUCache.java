package problems;

import java.util.HashMap;

public class LRUCache {
	private int curr_size;
    private int capacity;
    private HashMap<Integer, Entry> hm;
    private Entry head;
    private Entry tail;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.curr_size = 0;
        hm = new HashMap<Integer, Entry>();
        head = null;
        tail = null;
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
        	head = node;
        	tail = node;
        	this.curr_size++;
        	// update the hashmap
        	hm.put(key, node);
        }
        else
        {
        	this.show();
        	//check if the key already exist
        	Entry node = hm.get(key);
        	if (node == null)//doesn't exist
        	{
        		// create a new node
        		node = new Entry();
        		node.key = key;
        		node.value = value;
        		// update the cache
        		
        		move_to_head(node);
        		
        		this.curr_size++;
        		if (this.curr_size > this.capacity)
        		{
        			System.out.println("remove");
        			remove_tail();
        		}
        		// update the hashmap
        		hm.put(key, node);
        	}
        	else if  (node.key == key && node.value != value)// duplicate
        	{
        		Entry t = new Entry();
        		t.key = key;
        		t.value = value;
        		move_to_head(t);
        		this.curr_size++;
        		if (this.curr_size > this.capacity)
        		{
        			System.out.println("remove");
        			remove_tail();
        		}
        		// update the hashmap
        		hm.put(key, node);
        		
        	}
        	else // already exist. size doesn't change
        	{
        		move_to_head(node);
        	}
        	System.out.println("tail is " + tail.key);
        }
    	
    }
    
    public void remove_tail () 
    {
    	System.out.println("tail" + tail.key);
    	if (tail == null)
    	{
    		return;
    	}
    	else if (tail.prev == null)
    	{
    		head = null;
    		tail = null;
    	}
    	else
    	{
    		hm.remove(tail.key);
    		tail = tail.prev;
    		tail.next = null;
    	}
    }
    
    public void move_to_head (Entry node)
    {
    	if (node == null || node.prev == null)
    	{
    	
    	}
    	
    	else 
    	{
    		node.prev.next = node.next;
    		if (node.next != null)
    		{
    			node.next.prev = node.prev;
    		}
    		node.next = head;
    		head = node;
    	}
    }
    
    public void show ()
    {
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
    	LRUCache te = new LRUCache(4);
    	te.set(2, 3);
    	te.set(3, 5);
    	te.set(5, 7);
    	te.show_cache();
    	te.set(4, 8);
    	te.set(6, 7);
    	te.show_cache();
    	te.get(3);
    	te.show_cache();
    	*/
    	// set(2,1),set(1,1),get(2),set(4,1),get(1),get(2)
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
    }
}

class Entry {
	int key;
	int value;
	Entry prev;
	Entry next;
}