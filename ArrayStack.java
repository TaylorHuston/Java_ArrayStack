/*
Array Based Stack Implementation
Based on Algorithms, 4th Edition, Section 1.3
 */


import java.util.Iterator;
        
public class ArrayStack<Item> implements Iterable<Item> {
    private Item[] stack;
    private int top = 0;  //Position of the top of the stack
    
    //Constructor
    public ArrayStack(int size) {
        //Generic array creation isn't allowed, using cast
        stack = (Item[]) new Object[size]; 
    } //End Constructor
    
    //Add item to top of stack
    public void push(Item item) {
        //If you've reached top of stack, increase it's size by 5
        if (top == stack.length) {
            resize(stack.length + 5);
        }
        
        stack[top++] = item;
        StdOut.println(stack[top-1] + " Added to stack");
    } //End push
    
    //Removes item from top of stack
    public Item pop() {
            Item toReturn = stack[--top];
            stack[top] = null;  //Garbage cleanup
            
            if (top < stack.length-10) {
                resize(stack.length -10);
            }
            return toReturn;
    } //End pop
    
    //Simple true or false if the stack is empty or not
    public boolean isEmpty() {
        return (top == 0);
    } //End isEmpty
    
    //Returns the iterator
    public Iterator<Item> iterator() {
       return new StackIterator();
    } //End iterator
    
    public class StackIterator implements Iterator<Item> {
        private int i = top;
    
        @Override
        //True until i is the bottom of the stack
        public boolean hasNext() {
            return (i > 0); 
        } //End hasNext
        
        @Override
        //Returns the item at the top of the stack
        public Item next() {
            return stack[--i];
        } //End next
        
        @Override
        //Not implemented
        public void remove() { }
    } //End StackIterator
    
    private void resize(int size) {
        StdOut.println("Resizing stack to " + size);
        Item[] tempStack = (Item[]) new Object[size]; //New array for values
        
        //Copies values into new stack
        for (int i = 0; i < top; i++) {
            tempStack[i] = stack[i];  
        }
        stack = tempStack;
        
    } //End resize
    
    //Test client
    public static void main(String[] args) {
        int size = 5;
        //Have to use wrapper Integer since it's expecting an object
        ArrayStack<Integer> myStack  = new ArrayStack<Integer>(size);  
        
        int choice = 3;
        while (choice != 0) {
            StdOut.println();
            StdOut.println("1: Add number to top of stack");
            StdOut.println("2: Remove item from top of stack");
            StdOut.println("3: Display stack");
            StdOut.println("0: Quit");
            StdOut.print("Choice: ");
            choice = StdIn.readInt();
            
            if (choice == 1) {
                StdOut.print("Number to add: ");
                Integer toAdd = StdIn.readInt();
                myStack.push(toAdd);
            }
            
            if (choice == 2) {
                myStack.pop();
                
            }
            
            if (choice == 3) {
                for (Integer x : myStack) {
                    StdOut.println(x + " ");
                }
            }
        } //End while
    } //End main
} //End ArrayStack
