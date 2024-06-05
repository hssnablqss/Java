
/**
 * Write a description of class Queue here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Queue extends LinkedList
{
    //just return function from LinkedList
    
    public void enqueue(Karaoke item){
        insertAtBack(item);
    }
    
    public Karaoke dequeue()throws EmptyListException{
        return removeFromFront();
    }
    
    public Karaoke getFront(){
        return getFirst();
    }
    
    public Karaoke getEnd(){
        return getLast();
    }
}
