
/**
 * Write a description of class LinkedList here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class LinkedList
{
    private Node firstNode;
    private Node lastNode;
    private Node currNode;
    Karaoke kr;

    public LinkedList(){
        firstNode = null;
        lastNode = null;
        currNode = null;
        kr = null;
    }

    public LinkedList(Karaoke krk){
        firstNode = null;
        lastNode = null;
        currNode = null;
        kr = krk;
    }

    public boolean isEmpty(){
        return (firstNode == null);
    }

    public void insertAtFront(Karaoke krk){
        if(isEmpty())
            firstNode = lastNode = new Node (krk);
        else
            firstNode = new Node (krk,firstNode);
    }

    public void insertAtBack(Karaoke krk){
        if(isEmpty())
            firstNode = lastNode = new Node (krk);
        else{
            lastNode.next = new Node(krk);
            lastNode = lastNode.next;
        }
    }

    public Karaoke removeFromFront() throws EmptyListException{
        Karaoke removeItem = null;
        if(isEmpty())
            throw new EmptyListException(kr);

        removeItem = (Karaoke)firstNode.data;

        if(firstNode.equals(lastNode))
            firstNode = lastNode = null;
        else 
            firstNode = firstNode.next;

        return removeItem;
    }

    public Karaoke removeFromBack() throws EmptyListException{
        Karaoke removeItem = null;
        if (isEmpty())
            throw new EmptyListException(kr);

        removeItem = (Karaoke)lastNode.data;

        if(firstNode.equals(lastNode))
            firstNode = lastNode = null;
        else{
            Node current = firstNode;
            while(current.next != lastNode)
                current = current.next;

            lastNode = current;
            current.next = null;
        }
        return removeItem;
    }

    public static class EmptyListException extends Exception{
        public EmptyListException (Karaoke kr){
            System.out.println("the list is empty.");
        }
    }
    
    public Karaoke getFirst(){
        if(isEmpty())
            return null;
        else{
            currNode = firstNode;
            return (Karaoke) currNode.data;
        }
    }
    
    public Karaoke getLast(){
        if(isEmpty())
            return null;
        else{
            currNode = lastNode;
            return (Karaoke) currNode.data;
        }
    }
    
    public Karaoke getNext(){
        if (currNode != lastNode)
        {
            currNode = currNode.next;
            return (Karaoke)currNode.data;
        }
        else
            return null;
    }
    
    public String toString()
    {
        if (isEmpty())
            System.out.println("List is empty.");
        else
        {
            Node current = firstNode;
            while (current != null)
            {
                System.out.print(current.data +"->");
                current = current.next;
            }
        }
        
        return null;
    }
}
