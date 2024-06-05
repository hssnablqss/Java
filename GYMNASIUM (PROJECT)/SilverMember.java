/**
 * Write a description of class SilverMember here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SilverMember extends Gymnasium
{
    //attributes
    private String type;
    private int entry;

    //normal constructor
    public SilverMember(String name,String icNo,String gender,String category,String type,int entry)
    {
        super(name,icNo,gender,category);
        this.type = type;
        this.entry=entry;
    }
    //getter method
    public String getdayEntry()
    {
        return type;
    }

    public int getentry()
    {
        return entry;
    }

    //processsor
    public double calcSilverCharges()
    {
        double total = 0;
        double charge = 0;
        if (getdayEntry().equalsIgnoreCase ("attend classes"))
        {
            charge = 10.00;
            total = (charge * getentry());
        }
        else 
        {
            charge = 5.00;
            total = (charge * getentry());
        }

        return total;

    }

    //to display
    public String toString() {
        return super.toString() + " \nThe Day Activity: " + type + " \ntotal: RM  " + calcSilverCharges();
    }

}