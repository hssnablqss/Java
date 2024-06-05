public class GoldMember extends Gymnasium
{
    private String type;
    private int entry;

    //normal constructor
    public GoldMember ( String name,String icNo,String gender,String category,String type,int entry)
    {
        super(name,icNo,gender,category);
        this.type=type;
        this.entry=entry;
    }
   
    // getter method
    public String getmembertype()
    {
        return type;
    }

    public int getTotalMonth()
    {
        return entry;
    }

    //processor
    public double calcGoldCharges()
    {
        double total = 0;
        double charge = 0;
        if (getmembertype().equalsIgnoreCase ("student"))
        {
            charge = 60.00;
            total = (charge * getTotalMonth());
        }
        else 
        {
            charge = 5.00;
            total = (charge * getTotalMonth());
        }
        return total;
    }

    //to display
    public String toString() {
        return super.toString() + " \nMember type: " + type + " \ntotal: RM  " +    calcGoldCharges();
    }

}
