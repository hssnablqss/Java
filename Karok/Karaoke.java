import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.time.*;

public class Karaoke {
    private int custID;
    private String custName;
    private String custTelNo;
    private String custPackType;
    private LocalTime ETime;
    private LocalTime OTime;

    // normal constructor
    public Karaoke(int custID, String custName, String custTelNo, String custPackType, LocalTime ETime, LocalTime OTime) {
        this.custID = custID;
        this.custName = custName;
        this.custTelNo = custTelNo;
        this.custPackType = custPackType;
        this.ETime = ETime;
        this.OTime = OTime;
    }

    // setter methods
    public void setCustID(int custID) {
        this.custID = custID;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public void setCustTelNo(String custTelNo) {
        this.custTelNo = custTelNo;
    }

    public void setCustPackType(String custPackType) {
        this.custPackType = custPackType;
    }

    public void setETime(LocalTime ETime) {
        this.ETime = ETime;
    }

    public void setOTime(LocalTime OTime) {
        this.OTime = OTime;
    }

    // getter methods
    public int getCustID() {
        return custID;
    }

    public String getCustName() {
        return custName;
    }

    public String getCustTelNo() {
        return custTelNo;
    }

    public String getCustPackType() {
        return custPackType;
    }

    public LocalTime getETime() {
        return ETime;
    }

    public LocalTime getOTime() {
        return OTime;
    }

    public Duration calcTotalHour() {
        return Duration.between(ETime, OTime);
    }

    double amount=0.0;
    public double calcAmount() {
        double oneHour = 10.00;

        // Calculate total hours
        double totalHours = calcTotalHour().toMinutes() / 60.0;

        // Ensure minimum charge for one hour
        totalHours = Math.max(totalHours, 1.0);

        // Calculate amount based on package type
        if (getCustPackType().equalsIgnoreCase("normal ")) {
            amount = totalHours * oneHour * 1;
        } else if (getCustPackType().equalsIgnoreCase("premium")) {
            amount = totalHours * oneHour + 50.00;
        }

        return amount;
    }

    // printer method
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        return (custID + "\t          |" + custName + "\t |" + custTelNo + "\t    |" + custPackType + "\t    |" + ETime.format(formatter) + "\t  |" + OTime.format(formatter) + "      |" + "RM " + calcAmount() + "     |");
    }

}
