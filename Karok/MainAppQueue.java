
/**
 * Write a description of class MainAppQueue here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.io.*;
import java.util.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.time.Duration;

public class MainAppQueue
{
    public static void main(String[] args) throws LinkedList.EmptyListException {
        Scanner sc = new Scanner(System.in);
        Queue karaokeQ = new Queue();
        Queue normalQ = new Queue();
        Queue premiumQ = new Queue();

        try {
            FileReader fr = new FileReader("inputKbook.txt");
            BufferedReader br = new BufferedReader(fr);
            String input = null;

            for(input = br.readLine(); input != null && input.length() > 0; input = br.readLine()) {
                StringTokenizer st = new StringTokenizer(input, ";");
                String custID = st.nextToken();
                String custName = st.nextToken();
                String custTelNo = st.nextToken();
                String custPackType = st.nextToken();
                String ETime = st.nextToken();
                String OTime = st.nextToken();
                int newCustID = Integer.parseInt(custID);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                LocalTime newETime = LocalTime.parse(ETime, formatter);
                LocalTime newOTime = LocalTime.parse(OTime, formatter);

                Karaoke krk = new Karaoke(newCustID, custName, custTelNo, custPackType, newETime, newOTime);

                //insert data into Queue
                karaokeQ.enqueue(krk);
            }
        } catch (IOException var23) {
            System.out.println("problem : " + var23.getMessage());
        } finally {
            System.out.println();
            System.out.println("* end program *");
        }

        System.out.println("--------------------------------------------------------------------------------------------------------------------");
        System.out.println("=========================================== CUSTOMER RESERVATIONS ==================================================");
        System.out.println("--------------------------------------------------------------------------------------------------------------------");
        System.out.println("                                              NORMAL PACKAGE                                                       |");
        System.out.println("--------------------------------------------------------------------------------------------------------------------");
        System.out.println("customer ID       |  customer name       |  telephone num   |  package type | enter time  |exit time  |   amount   |");
        System.out.println("--------------------------------------------------------------------------------------------------------------------");
        //to display customer's reservation
        Queue temp1=new Queue();
        while(!karaokeQ.isEmpty()) {
            Karaoke kr1 = karaokeQ.dequeue();
            if (kr1.getCustPackType().equalsIgnoreCase("normal ")) {
                System.out.println(kr1.toString());
                normalQ.enqueue(kr1);
            }
            temp1.enqueue(kr1);
        }
        while(!temp1.isEmpty()) {
            karaokeQ.enqueue(temp1.dequeue());
        }

        System.out.println("--------------------------------------------------------------------------------------------------------------------");
        System.out.println("                                              PREMIUM PACKAGE                                                      |");
        System.out.println("--------------------------------------------------------------------------------------------------------------------");
        System.out.println("customer ID       |  customer name       |  telephone num   |  package type | enter time  |exit time  |   amount   |");
        System.out.println("--------------------------------------------------------------------------------------------------------------------");

        Queue temp2 = new Queue();
        while(!karaokeQ.isEmpty()) {
            Karaoke kr2 = karaokeQ.dequeue();
            if (kr2.getCustPackType().equalsIgnoreCase("premium")) {
                System.out.println(kr2.toString());
                premiumQ.enqueue(kr2);
            }
            temp2.enqueue(kr2);
        }
        while(!temp2.isEmpty()) {
            karaokeQ.enqueue(temp2.dequeue());
        }

        System.out.println("--------------------------------------------------------------------------------------------------------------------");

        ArrayList<Karaoke> KListNormal = new ArrayList();
        ArrayList<Karaoke> KListPremium = new ArrayList();

        while(!normalQ.isEmpty()) {
            Karaoke kr3P = normalQ.dequeue();
            KListNormal.add(kr3P);
        }

        while(!premiumQ.isEmpty()) {
            Karaoke kr3P = premiumQ.dequeue();
            KListPremium.add(kr3P);
        }

        sortListAscNormal(KListNormal);

        for(int i = 0; i < KListNormal.size(); ++i) {
            Karaoke krN = (Karaoke)KListNormal.get(i);
            normalQ.enqueue(krN);
        }

        sortListAscPremium(KListPremium);

        for(int i = 0; i < KListPremium.size(); ++i) {
            Karaoke krP = (Karaoke)KListPremium.get(i);
            premiumQ.enqueue(krP);
        }

        System.out.println("\n\n\n\n--------------------------------------------------------------------------------------------------------------------");
        System.out.println("==================== CUSTOMER RESERVATIONS THAT HAVE BEEN SORTED ACCORDING TO THEIR ENTER TIME =====================");
        System.out.println("--------------------------------------------------------------------------------------------------------------------");
        System.out.println("                                                NORMAL PACKAGE                                                     |");
        System.out.println("--------------------------------------------------------------------------------------------------------------------");
        System.out.println("customer ID       |  customer name       |  telephone num   |  package type | enter time  |exit time  |   amount   |");
        System.out.println("--------------------------------------------------------------------------------------------------------------------");

        Queue temp4=new Queue();
        while(!normalQ.isEmpty()) {
            Karaoke kr4 = normalQ.dequeue();
            if (kr4.getCustPackType().equalsIgnoreCase("normal ")) {
                System.out.println(kr4.toString());
            }
            temp4.enqueue(kr4);
        }
        while(!temp4.isEmpty()) {
            normalQ.enqueue(temp4.dequeue());
        }

        System.out.println("--------------------------------------------------------------------------------------------------------------------");
        System.out.println("                                              PREMIUM PACKAGE                                                      |");
        System.out.println("--------------------------------------------------------------------------------------------------------------------");
        System.out.println("customer ID       |  customer name       |  telephone num   |  package type | enter time  |exit time  |   amount   |");
        System.out.println("--------------------------------------------------------------------------------------------------------------------");

        Queue temp5=new Queue();
        while(!premiumQ.isEmpty()) {
            Karaoke kr5 = premiumQ.dequeue();
            if (kr5.getCustPackType().equalsIgnoreCase("premium")) {
                System.out.println(kr5.toString());
            }
            temp5.enqueue(kr5);
        }
        while(!temp5.isEmpty()) {
            premiumQ.enqueue(temp5.dequeue());
        }

        System.out.println("--------------------------------------------------------------------------------------------------------------------");

        //INFINITE LOOP for MAIN MENU
        for(;;){
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

                    Queue temporary=new Queue();
                    ArrayList<Karaoke> KList = new ArrayList();
                    while(!karaokeQ.isEmpty()) {
                        Karaoke kr = karaokeQ.dequeue();
                        KList.add(kr);
                        temporary.enqueue(kr);
                    }
                    //sortListAsc(KList);
                    while(!temporary.isEmpty()){
                        karaokeQ.enqueue(temporary.dequeue());
                    }
                    int result=binarySearch(KList,id);
                    // ...

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

                    // ...

                    System.out.print("\n\nContinue to search?[yes-1|no-0]: ");
                    search = Integer.parseInt(sc.nextLine());
                }
            }else if(choice.contains("2")){
                System.out.println("Total customer throughout the day is " +numOfCust(karaokeQ));
                System.out.println("------------------------------------------------------------------------");
            }else if(choice.contains("3")){
                System.out.println("\n\n===============================================================================================================================================");
                longestTimeSession(karaokeQ);
                System.out.println("================================================================================================================================================");
                shortestTimeSession(karaokeQ);
                System.out.println("================================================================================================================================================");
            }else if(choice.contains("4")){
                System.out.println("total income for the day: RM "+totalIncome(karaokeQ));
                System.out.println("------------------------------------------------------------------------");
            }else if(choice.contains("5")){
                availableTime(karaokeQ);
            }else if(choice.contains("6")){
                int search=1;
                while(search!=0){
                    System.out.print("Enter customer ID to update: ");
                    int id=Integer.parseInt(sc.nextLine());
                    int i=0;

                    Queue temporary=new Queue();
                    ArrayList<Karaoke> KList = new ArrayList();
                    while(!karaokeQ.isEmpty()) {
                        Karaoke kr = karaokeQ.dequeue();
                        KList.add(kr);
                        temporary.enqueue(kr);
                    }
                    //sortListAsc(KList);
                    while(!temporary.isEmpty()){
                        karaokeQ.enqueue(temporary.dequeue());
                    }
                    int result=binarySearch(KList,id);
                    if (result != -1){

                        updateCustomer(karaokeQ,id);
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

    //to calculate totalIncome for the day
    public static double totalIncome(Queue karaokeQ) throws LinkedList.EmptyListException {
        double totalIncome = 0.00;
        Queue temptotalQ = new Queue();

        while(!karaokeQ.isEmpty()) {
            Karaoke K = karaokeQ.dequeue();
            totalIncome += K.calcAmount();
            temptotalQ.enqueue(K);
        }

        while(!temptotalQ.isEmpty()) {
            karaokeQ.enqueue(temptotalQ.dequeue());
        }

        return totalIncome;
    }

    //to COUNT number of customer throughout the day
    public static int numOfCust(Queue karaokeQ) throws LinkedList.EmptyListException {
        int count = 0;
        Queue tempCount = new Queue();

        while(!karaokeQ.isEmpty()) {
            Karaoke KR = karaokeQ.dequeue();
            ++count;
            tempCount.enqueue(KR);
        }

        while(!tempCount.isEmpty()) {
            karaokeQ.enqueue(tempCount.dequeue());
        }

        return count;
    }

    //binary search
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

    //to find longest and shortest time session that customer has booked
    public static void longestTimeSession(Queue karaokeQ) throws LinkedList.EmptyListException {
        Queue timeQ=new Queue();
        Duration longest=Duration.ZERO;
        Karaoke longestInfo=null;
        while(!karaokeQ.isEmpty()){
            Karaoke kr=(Karaoke)karaokeQ.dequeue();
            if(kr.calcTotalHour().compareTo(longest) > 0){
                longest=kr.calcTotalHour();
                longestInfo=kr;
            }
            timeQ.enqueue(kr);
        }
        while(!timeQ.isEmpty()){
            karaokeQ.enqueue(timeQ.dequeue());
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

    //to find the shortest time session that customer has booked
    public static void shortestTimeSession(Queue karaokeQ) throws LinkedList.EmptyListException {
        Queue timeQ=new Queue();
        Duration shortest=Duration.ofHours(99).plusMinutes(99);
        Karaoke shortestInfo=null;
        while(!karaokeQ.isEmpty()){
            Karaoke kr=(Karaoke)karaokeQ.dequeue();
            if(kr.calcTotalHour().compareTo(shortest)<0){
                shortest=kr.calcTotalHour();
                shortestInfo=kr;
            }
            timeQ.enqueue(kr);
        }
        while(!timeQ.isEmpty()){
            karaokeQ.enqueue(timeQ.dequeue());
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

    //update customer's data
    public static void updateCustomer(Queue karaokeQ, int customerId) throws LinkedList.EmptyListException {
        Queue tempQueue = new Queue();
        Karaoke customerToUpdate = null;
        Scanner sc = new Scanner(System.in);

        // Search for the customer in the queue
        while (!karaokeQ.isEmpty()) {
            Karaoke currentCustomer = karaokeQ.dequeue();
            if (currentCustomer.getCustID() == customerId) {
                customerToUpdate = currentCustomer; // Customer found
            }
            tempQueue.enqueue(currentCustomer);
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

            // Enqueue the updated customer back into the queue
            karaokeQ.enqueue(customerToUpdate);
        } else {
            System.out.println("Customer with ID " + customerId + " not found.");
        }

        // Restore the original order of customers in the queue
        while (!tempQueue.isEmpty()) {
            karaokeQ.enqueue(tempQueue.dequeue());
        }
    }

    //to find available time session
    public static void availableTime(Queue karaokeQ) throws LinkedList.EmptyListException {
        boolean availableHoursPremium[] = new boolean[24];
        boolean availableHoursNormal[] = new boolean[24];
        Arrays.fill(availableHoursPremium, false); // initialize array as false
        Arrays.fill(availableHoursNormal, false); // initialize array as false

        // check available hours
        while(!karaokeQ.isEmpty()){
            Karaoke kr=(Karaoke)karaokeQ.dequeue();
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

