import java.io.*;
import java.util.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.time.Duration;
//import java.util.LinkedList;


public class MainAppLL
{
    public static void main(String args[]) throws LinkedList.EmptyListException
    {
        LinkedList bookingList = new LinkedList();
        LinkedList normalList = new LinkedList();
        LinkedList premiumList = new LinkedList();

        try{ 
            FileReader fr = new FileReader("inputKbook.txt");
            BufferedReader br = new BufferedReader(fr);

            String input = null;

            for(input = br.readLine(); input != null && input.length() > 0; input = br.readLine()) 
            {

                StringTokenizer st = new StringTokenizer(input, ";");

                String custID = st.nextToken();
                String custName = st.nextToken();
                String custTelNo = st.nextToken();
                String custPackType = st.nextToken();
                String ETime = st.nextToken();
                String OTime = st.nextToken();
                int newCustID = Integer.parseInt(custID);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                LocalTime Etime = LocalTime.parse(ETime, formatter);
                LocalTime Otime = LocalTime.parse(OTime, formatter);

                Karaoke krk = new Karaoke(newCustID, custName, custTelNo, custPackType, Etime, Otime);

                bookingList.insertAtBack(krk);

            }

        } catch (IOException var23) {
            System.out.println("problem : " + var23.getMessage());
        } finally {
            System.out.println();
            //System.out.println("* end program *");
        }

        System.out.println("--------------------------------------------------------------------------------------------------------------------");
        System.out.println("=========================================== CUSTOMER RESERVATIONS ==================================================");
        System.out.println("--------------------------------------------------------------------------------------------------------------------");
        System.out.println("                                              NORMAL PACKAGE                                                       |");
        System.out.println("--------------------------------------------------------------------------------------------------------------------");
        System.out.println("customer ID       |  customer name       |  telephone num   |  package type | enter time  |exit time  |   amount   |");
        System.out.println("--------------------------------------------------------------------------------------------------------------------");

        Karaoke krk1=(Karaoke)bookingList.getFirst();
        while(krk1!=null){
            if(krk1.getCustPackType().equalsIgnoreCase("normal "))
            {
                normalList.insertAtBack(krk1);
                System.out.println(krk1.toString());
            }
            krk1=(Karaoke)bookingList.getNext();
        }

        System.out.println(normalList.toString());
        System.out.println("--------------------------------------------------------------------------------------------------------------------");
        System.out.println("                                              PREMIUM PACKAGE                                                      |");
        System.out.println("--------------------------------------------------------------------------------------------------------------------");
        System.out.println("customer ID       |  customer name       |  telephone num   |  package type | enter time  |exit time  |   amount   |");
        System.out.println("--------------------------------------------------------------------------------------------------------------------");

        Karaoke krk2=(Karaoke)bookingList.getFirst();
        while(krk2!=null){
            if(krk2.getCustPackType().equalsIgnoreCase("premium"))
            {
                premiumList.insertAtBack(krk2);
                System.out.println(krk2.toString());
            }
            krk2=(Karaoke)bookingList.getNext();
        }

        System.out.println("--------------------------------------------------------------------------------------------------------------------");

        ArrayList<Karaoke> KListNormal = new ArrayList();
        ArrayList<Karaoke> KListPremium = new ArrayList();

        Karaoke krk3=(Karaoke)normalList.getFirst();
        while(krk3 != null)
        {
            KListNormal.add(krk3);
            krk3=(Karaoke)normalList.getNext();
        }

        Karaoke krkk3=(Karaoke)premiumList.getFirst();
        while(krkk3!=null)
        {
            KListPremium.add(krkk3);
            krkk3=(Karaoke)premiumList.getNext();
        }

        sortListAscNormal(KListNormal);
        //declare linkedList for new data that have been sorted
        LinkedList normalSorted=new LinkedList();

        for(int i = 0; i < KListNormal.size(); ++i) {
            Karaoke krk4 = (Karaoke)KListNormal.get(i);
            normalSorted.insertAtBack(krk4);
        }

        sortListAscPremium(KListPremium);
        //declare linkedList for new data that have been sorted
        LinkedList premiumSorted=new LinkedList();
        for(int i = 0; i < KListPremium.size(); ++i) {
            Karaoke krk5 = (Karaoke)KListPremium.get(i);
            premiumSorted.insertAtBack(krk5);
        }

        System.out.println("\n\n\n\n--------------------------------------------------------------------------------------------------------------------");
        System.out.println("==================== CUSTOMER RESERVATIONS THAT HAVE BEEN SORTED ACCORDING TO THEIR ENTER TIME =====================");
        System.out.println("--------------------------------------------------------------------------------------------------------------------");
        System.out.println("                                                NORMAL PACKAGE                                                     |");
        System.out.println("--------------------------------------------------------------------------------------------------------------------");
        System.out.println("customer ID       |  customer name       |  telephone num   |  package type | enter time  |exit time  |   amount   |");
        System.out.println("--------------------------------------------------------------------------------------------------------------------");

        Karaoke krk6=(Karaoke)normalSorted.getFirst();
        while(krk6!= null)
        {
            if(krk6.getCustPackType().equalsIgnoreCase("normal "))
            {
                System.out.println(krk6.toString());
            }
            krk6=(Karaoke)normalSorted.getNext();
        }

        System.out.println("--------------------------------------------------------------------------------------------------------------------");
        System.out.println("                                              PREMIUM PACKAGE                                                      |");
        System.out.println("--------------------------------------------------------------------------------------------------------------------");
        System.out.println("customer ID       |  customer name       |  telephone num   |  package type | enter time  |exit time  |   amount   |");
        System.out.println("--------------------------------------------------------------------------------------------------------------------");

        Karaoke krk7=(Karaoke)premiumSorted.getFirst();
        while(krk7 != null)
        {
            if(krk7.getCustPackType().equalsIgnoreCase("premium"))
            {
                System.out.println(krk7.toString());
            }
            krk7=(Karaoke)premiumSorted.getNext();
        }

        System.out.println("--------------------------------------------------------------------------------------------------------------------");

        //INFINITE LOOP for MAIN MENU
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("\n\n\n------------------------------------------------------------------------");
            System.out.println("______________________________ MAIN MENU _______________________________");
            System.out.println("\nselect one : ");
            System.out.println("1. search customer ID to display receipt ");
            System.out.println("2. display total customer throughout the day");
            System.out.println("3. display longest and shortest time session that customer has booked");
            System.out.println("4. display total income for the company throughout the day");
            System.out.println("5. total karaoke room that is still available");
            System.out.println("6. update customer's data");
            System.out.println("0. end program");
            System.out.println("your choice :");
            String choice = sc.nextLine();
            System.out.println("------------------------------------------------------------------------");
            if(choice.contains("1")){
                int search=1;
                while(search !=0){
                    System.out.print("Enter customer ID to display receipt: ");
                    int id=Integer.parseInt(sc.nextLine());
                    int i=0;

                    Karaoke krk8=(Karaoke)bookingList.getFirst();
                    ArrayList<Karaoke> KList = new ArrayList();
                    while(krk8 != null)
                    {
                        KList.add(krk8);
                        krk8=(Karaoke)bookingList.getNext();
                    }
                    int result = binarySearch(KList, id);

                    if (result != -1) {
                        Karaoke customer = KList.get(result);

                        System.out.println("\n\n--------------------------------------------------------------------------------------------------------------------");
                        System.out.println("                                     RECEIPT FOR CUSTOMER ID " + id);
                        System.out.println("--------------------------------------------------------------------------------------------------------------------");
                        System.out.println("customer ID       |  customer name       |  telephone num   |  package type | enter time  |exit time  |   amount   |");
                        System.out.println("--------------------------------------------------------------------------------------------------------------------");
                        System.out.println(customer.toString());
                        System.out.println("--------------------------------------------------------------------------------------------------------------------");
                        System.out.println("From " + customer.getETime() + " to " + customer.getOTime());
                        System.out.println("total hour : " + customer.calcTotalHour().toHoursPart() + " hours " + customer.calcTotalHour().toMinutesPart() + " minutes");
                        System.out.println("*********************************************************************************************************************");

                        if (customer.getCustPackType().equalsIgnoreCase("normal ")) {
                            System.out.println("RM 10.00 x " + "total hour : " + customer.calcTotalHour().toHoursPart() + " hours " + customer.calcTotalHour().toMinutesPart() + " minutes" + " = " + customer.calcAmount());
                        } else if (customer.getCustPackType().equalsIgnoreCase("premium")) {
                            System.out.println("RM 10.00 x " + "total hour : " + customer.calcTotalHour().toHoursPart() + " hours " + customer.calcTotalHour().toMinutesPart() + " minutes + RM 50.00" + " = " + customer.calcAmount());
                        }
                        System.out.println("--------------------------------------------------------------------------------------------------------------------");
                    } else {
                        System.out.println("Customer with ID " + id + " not found.");
                    }
                    System.out.print("\n\nContinue to search?[yes-1|no-0]: ");
                    search = Integer.parseInt(sc.nextLine());
                }
            }else if(choice.contains("2")){
                System.out.println("Total customer throughout the day is " +numOfCust(bookingList));
                System.out.println("------------------------------------------------------------------------");
            }else if(choice.contains("3")){
                System.out.println("\n\n===============================================================================================================================================");
                longestTimeSession(bookingList);
                System.out.println("================================================================================================================================================");
                shortestTimeSession(bookingList);
                System.out.println("================================================================================================================================================");
            }else if(choice.contains("4")){
                System.out.println("total income for the day: RM "+totalIncome(bookingList));
                System.out.println("------------------------------------------------------------------------");
            }else if(choice.contains("5")){
                availableTime(bookingList);
                
            }else if(choice.contains("6")){
                int search=1;
                while(search!=0){
                    System.out.print("Enter customer ID to update: ");
                    int id=Integer.parseInt(sc.nextLine());
                    int i=0;

                    Karaoke krk9=(Karaoke)bookingList.getFirst();
                    ArrayList<Karaoke> KList = new ArrayList();
                    while(krk9 != null)
                    {
                        KList.add(krk9);
                        krk9=(Karaoke)bookingList.getNext();
                    }

                    int result = binarySearch(KList,id);
                    if (result != -1){

                        updateCustomer(bookingList,id);
                    }else {
                        System.out.println("Customer with ID " + id + " not found.");
                    }
                    System.out.print("\n\nContinue to update?[yes-1|no-0]: ");
                    search = Integer.parseInt(sc.nextLine());
                }
            }else if(choice.contains("0")){
                System.out.println("you have exited the program");
                System.exit(0);
            }

        }
    }

    public static void sortListAscNormal(ArrayList<Karaoke>KListNormal) {
        for(int b = 0; b < KListNormal.size(); ++b) {
            for(int j = 0; j < KListNormal.size() - 1; ++j) {
                LocalTime first = ((Karaoke)KListNormal.get(j)).getETime();
                LocalTime second = ((Karaoke)KListNormal.get(j + 1)).getETime();
                if (first.compareTo(second) > 0) {
                    Karaoke temp = (Karaoke)KListNormal.get(j + 1);
                    KListNormal.set(j + 1, (Karaoke)KListNormal.get(j));
                    KListNormal.set(j, temp);
                }
            }
        }

    }

    public static void sortListAscPremium(ArrayList<Karaoke> KListPremium) {
        for(int b = 0; b < KListPremium.size(); ++b) {
            for(int j = 0; j < KListPremium.size() - 1; ++j) {
                LocalTime first = ((Karaoke)KListPremium.get(j)).getETime();
                LocalTime second = ((Karaoke)KListPremium.get(j + 1)).getETime();
                if (first.compareTo(second) > 0) {
                    Karaoke temp = (Karaoke)KListPremium.get(j + 1);
                    KListPremium.set(j + 1, (Karaoke)KListPremium.get(j));
                    KListPremium.set(j, temp);
                }
            }
        }

    }

    //Calculate totalIncome for the day
    public static double totalIncome(LinkedList bookingList) throws LinkedList.EmptyListException {
        double totalIncome = 0.00;

        Karaoke krk9=(Karaoke)bookingList.getFirst();
        while(krk9 != null) {
            totalIncome += krk9.calcAmount();
            krk9=(Karaoke)bookingList.getNext();
        }
        return totalIncome;
    }

    //Count number of customer for the day
    public static int numOfCust(LinkedList bookingList) throws LinkedList.EmptyListException {
        int count = 0;
        Karaoke krk10=(Karaoke)bookingList.getFirst();
        while(krk10 != null) {
            ++count;
            krk10=(Karaoke)bookingList.getNext();
        }
        return count;
    }

    //Binary search
    public static int binarySearch( ArrayList<Karaoke> KList, int id) throws LinkedList.EmptyListException {
        int left = 0, right = KList.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            Karaoke KRK = KList.get(mid);

            if (KRK.getCustID() == id)
                return mid;

            if ((KRK.getCustID() < id))
                left = mid + 1;

            else
                right = mid - 1;
        }

        return -1;
    }

    //Longest and shortest time session that customer has booked
    public static void longestTimeSession(LinkedList bookingList) throws LinkedList.EmptyListException {

        Duration longest=Duration.ZERO;
        Karaoke longestInfo=null;
        Karaoke krk11=(Karaoke)bookingList.getFirst();
        while(krk11 != null){
            if(krk11.calcTotalHour().compareTo(longest) > 0){
                longest=krk11.calcTotalHour();
                longestInfo=krk11;
            }
            krk11=(Karaoke)bookingList.getNext();
        }
        System.out.println("\n--------------------------------------------------------------------------------------------------------------------");
        System.out.println("                                          CUSTOMER WITH THE LONGEST TIME SESSION                                   |");
        System.out.println("--------------------------------------------------------------------------------------------------------------------");
        System.out.println("customer ID       |  customer name       |  telephone num   |  package type | enter time  |exit time  |   amount   |");
        System.out.println("--------------------------------------------------------------------------------------------------------------------");
        System.out.println(longestInfo.toString());
        System.out.println("--------------------------------------------------------------------------------------------------------------------");
        System.out.println("total hour : "+longest.toHoursPart()+" hours "+longest.toMinutesPart()+" minutes");
        System.out.println("--------------------------------------------------------------------------------------------------------------------");
    }

    //Shortest time session that customer has booked
    public static void shortestTimeSession(LinkedList bookingList) throws LinkedList.EmptyListException {
        Duration shortest=Duration.ofHours(99).plusMinutes(99);
        Karaoke shortestInfo=null;
        Karaoke krk12=(Karaoke)bookingList.getFirst();
        while(krk12 != null){
            if(krk12.calcTotalHour().compareTo(shortest)<0){
                shortest=krk12.calcTotalHour();
                shortestInfo=krk12;
            }
            krk12=(Karaoke)bookingList.getNext();
        }

        System.out.println("\n--------------------------------------------------------------------------------------------------------------------");
        System.out.println("                                         CUSTOMER WITH THE SHORTEST TIME SESSION                                   |");
        System.out.println("--------------------------------------------------------------------------------------------------------------------");
        System.out.println("customer ID       |  customer name       |  telephone num   |  package type | enter time  |exit time  |   amount   |");
        System.out.println("--------------------------------------------------------------------------------------------------------------------");
        System.out.println(shortestInfo.toString());
        System.out.println("--------------------------------------------------------------------------------------------------------------------");
        System.out.println("total hour : "+shortest.toHoursPart()+" hours "+shortest.toMinutesPart()+" minutes");
        System.out.println("--------------------------------------------------------------------------------------------------------------------");
    }

    
    // Update customer's data
    public static void updateCustomer(LinkedList bookingList, int customerId) throws LinkedList.EmptyListException {
        Scanner sc = new Scanner(System.in);

        // Find the customer in the linked list
        Karaoke current = bookingList.getFirst();
        Karaoke customerToUpdate = null;

        while (current != null) {

            if (current.getCustID() == customerId) {
                customerToUpdate = current;
                break; // Customer found, exit the loop
            }
            current = bookingList.getNext();
        }

        // If the customer was found, update their data
        if (customerToUpdate != null) {
            // Update customer data (modify attributes as needed)
            System.out.print("Enter new name: ");
            String newName = sc.nextLine();
            customerToUpdate.setCustName(newName);

            System.out.print("Enter new phone number: ");
            String newPhoneNumber = sc.nextLine();
            customerToUpdate.setCustTelNo(newPhoneNumber);

            System.out.print("Enter new package type: ");
            String newPackageType = sc.nextLine();
            customerToUpdate.setCustPackType(newPackageType);

            // Recalculate the amount based on the updated package type
            customerToUpdate.calcAmount();

            // Get new enter and exit times from user input or other source
            System.out.print("Enter new enter time (HH:mm): ");
            String newEnterTimeStr = sc.nextLine();
            System.out.print("Enter new exit time (HH:mm): ");
            String newExitTimeStr = sc.nextLine();

            // Parse strings into LocalTime objects
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime newETime = LocalTime.parse(newEnterTimeStr, formatter);
            LocalTime newOTime = LocalTime.parse(newExitTimeStr, formatter);

            // Set the parsed LocalTime objects
            customerToUpdate.setETime(newETime);
            customerToUpdate.setOTime(newOTime);

            System.out.println("Customer data updated successfully.");
        } else {
            System.out.println("Customer with ID " + customerId + " not found.");
        }
    }
    
    //to find available time session
    public static void availableTime(LinkedList ll) throws LinkedList.EmptyListException {
        boolean availableHoursPremium[] = new boolean[24];
        boolean availableHoursNormal[] = new boolean[24];
        Arrays.fill(availableHoursPremium, false); // initialize array as false
        Arrays.fill(availableHoursNormal, false); // initialize array as false
        
        // check available hours
        Karaoke kr=(Karaoke)ll.getFirst();
        while(kr!=null){
            int eHour = kr.getETime().getHour();
            int oHour = kr.getOTime().getHour();
            if (eHour <= oHour) {
                for(int h = eHour; h <= oHour; h++) {
                    if (kr.getCustPackType().equalsIgnoreCase("premium")) {
                        availableHoursPremium[h] = true;
                    } else {
                        availableHoursNormal[h] = true;
                    }
                    //System.out.println(String.format("%02d", h) + " : "+availableHoursPremium[h]+"|"+availableHoursNormal[h]);
                }
            }
            kr=ll.getNext();
        }
        
        // display avalable hours
        int totalBookedHoursPremium = 0;
        int totalBookedHoursNormal = 0;
        System.out.println("\n--------------------------------------------------------------------------------------------------------------------");
        System.out.println("                                                    AVAILABLE HOURS                                                |");
        System.out.println("--------------------------------------------------------------------------------------------------------------------");
        System.out.println("HOUR | PREMIUM   | NORMAL                                                                                            |");
        System.out.println("--------------------------------------------------------------------------------------------------------------------");
        for(int i = 0; i < 24; i++) {
            if (availableHoursPremium[i] && availableHoursNormal[i]) {
                System.out.println(String.format(" %02d", i) + "  | BOOKED    | BOOKED");
                totalBookedHoursPremium++;
                totalBookedHoursNormal++;
            } else if (availableHoursPremium[i]) {
                System.out.println(String.format(" %02d", i) + "  | BOOKED    | AVAILABLE");
                totalBookedHoursPremium++;
            } else if (availableHoursNormal[i]) {
                System.out.println(String.format(" %02d", i) + "  | AVAILABLE | BOOKED");
                totalBookedHoursNormal++;
            } else {
                System.out.println(String.format(" %02d", i) + "  | AVAILABLE | AVAILABLE");
            }
        }
        System.out.println("--------------------------------------------------------------------------------------------------------------------");
        System.out.println(" TOTAL BOOKED HOURS          PREMIUM : "+totalBookedHoursPremium+"   NORMAL : "+totalBookedHoursNormal);
        System.out.println(" TOTAL AVAILABLE HOURS       PREMIUM : "+(24 - totalBookedHoursPremium)+"   NORMAL : "+(24 - totalBookedHoursNormal));
        System.out.println("--------------------------------------------------------------------------------------------------------------------");
    }



}