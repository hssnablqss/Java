
/**
 * Write a description of class Node here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Node
{
    Karaoke data;
    Node next;
    
    public Node (Karaoke item){
        data=item;
        next=null;
    }
    
    public Node (Karaoke item, Node nextNode){
        data=item;
        next=nextNode;
    }
    
    public Karaoke getData(){
        return data;
    }
    
    public Node getNext(){
        return next;
    }
}
