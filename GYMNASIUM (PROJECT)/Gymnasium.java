public class Gymnasium
{
    //attributes
    private String name;
    private String icNo;
    private String gender;
    private String category;

    //Normal Constructor
    public Gymnasium(String name, String icNo, String gender, String category)
    {
        this.name = name;
        this.icNo = icNo;
        this.gender = gender;
        this.category = category;
    }    
    //getter method
    public String getName()
    {
        return name;
    }

    public String getIc()
    {
        return icNo;
    }

    public String getGender()
    {
        return gender;
    }

    public String getcategory()
    {
        return category;
    }

    //to display
    public String toString()
    {
        return (" \nName: " +name+ " \nIc Number: " +icNo+ "\nGender:" +gender); 
    }
}
