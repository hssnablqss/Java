import java.io.*;
import java.util.*;
public class MainApp {
    public static void main(String args[]) throws Exception {
        try {
            // declare an array of 40 for SilverMember and GoldMember.
            SilverMember[] SV = new SilverMember[40];
            GoldMember[] GD = new GoldMember[40];

            // Open input files to read data
            FileReader fr = new FileReader("input.txt");
            BufferedReader br = new BufferedReader(fr);

            // print out output in output file
            FileWriter fw = new FileWriter("result.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            String input = null;
            input = br.readLine();
            // initialization
            String name, icNo, gender, category, type, entry, total;
            int custSV = 0, custGD = 0, minSV = 0, minGD = 0, indexSV = 0, indexGD = 0;
            double highestSV = 0, highestGD = 0, totalIncomeSV = 0.0, totalIncomeGD = 0.0;

            // Store
            while (input != null && input.length() > 0) {
                StringTokenizer st = new StringTokenizer(input, ",");
                name = st.nextToken();
                icNo = st.nextToken();
                gender = st.nextToken();
                category = st.nextToken();
                type = st.nextToken();
                entry = st.nextToken();
                int entryInt = Integer.parseInt(entry);

                // Store
                if (category.contains("silver member")) {
                    // store silver members data in an array
                    SV[custSV] = new SilverMember(name, icNo, gender, category, type, entryInt);
                    // display each customer (for silver membership)detailed information
                    System.out.println("\n==>> SILVER MEMBER: " + SV[custSV].toString());

                    for (int i = 0; i < custSV; i++) {
                        if (SV[i].calcSilverCharges() < SV[minSV].calcSilverCharges()) {
                            minSV = i;
                        }

                        if (SV[i].calcSilverCharges() > highestSV) {
                            highestSV = SV[i].calcSilverCharges();
                            indexSV = i;
                        }

                        totalIncomeSV += SV[i].calcSilverCharges();
                    }
                    custSV++;
                    System.out.println("Current total member for Silver Membership: " + custSV);
                } else if (category.contains("gold member")) {
                    // store gold members data in an array
                    GD[custGD] = new GoldMember(name, icNo, gender, category, type, entryInt);
                    // display each customer(for gold membership) detailed information
                    System.out.println("\n==>> GOLD MEMBER: " + GD[custGD].toString());

                    for (int i = 0; i < custGD; i++) {
                        if (GD[i].calcGoldCharges() < GD[minGD].calcGoldCharges()) {
                            minGD = i;
                        }

                        if (GD[i].calcGoldCharges() > highestGD) {
                            highestGD = GD[i].calcGoldCharges();
                            indexGD = i;
                        }

                        totalIncomeGD += GD[i].calcGoldCharges();
                    }
                    custGD++;
                    System.out.println("Current total Member for Gold Membership: " + custGD);
                }

                input = br.readLine();
            }

            // to calculate the company income for the year 2023
            double sum = totalIncomeSV + totalIncomeGD;

            // print result in output file (result.txt)
            pw.println("\n\n----------------------------------------------------");
            pw.println("-->Total member for Silver Membership : " + custSV);
            pw.println("-->Total member for Gold Membership : " + custGD);
            pw.println("********************************************************");
            pw.println("\nThe lowest charge for Silver Member: " + SV[minSV].toString());
            pw.println("\nThe lowest charge for Gold Member: " + GD[minGD].toString());
            pw.println("********************************************************");
            pw.println("\nThe highest charge for Silver Member: " + SV[indexSV].toString());
            pw.println("\nThe highest charge for Gold Member: " + GD[indexGD].toString());
            pw.println("========================================================");
            pw.println("        ----Total income for Silver Membership----");
            pw.println("        ----         = RM " + totalIncomeSV + "       ----");
            pw.println("________________________________________________________");
            pw.println("________________________________________________________");
            pw.println("        ----Total income for Gold Membership----");
            pw.println("        ----         = RM " + totalIncomeGD + "       ----");
            pw.println("*********************************************************");
            pw.println("total company income for 2023 : RM " + sum);
            pw.println("________________________________________________________-");

            br.close();
            pw.close();
            fw.close();

            // infinite loop for MAIN MENU (searching)
            for (;;) {
                Scanner sc = new Scanner(System.in);
                System.out.println("\n____________________________ MAIN MENU _______________________________");
                System.out.println("\nselect one : ");
                System.out.println("1. search ic from input file ");
                System.out.println("2. display total member for each membership");
                System.out.println("3. display highest and lowest charges for each membership");
                System.out.println("4. display total income for each membership");
                System.out.println("5. update customer's data");
                System.out.println("0. end program");
                System.out.println("your choice :");
                String choice = sc.nextLine();
               if(choice.contains("1")){
                    System.out.println("select one:");
                    System.out.println("1.SILVER MEMBERS");
                    System.out.println("2.GOLD MEMBERS");
                    System.out.println("your selection:");
                    String select=sc.nextLine();
                    boolean found=false;
                    int i=0;
                    if(select.contains("1")){
                        System.out.println("enter ic number :");
                        String search=sc.nextLine();
                        while(found == false && i<custSV){
                            if(SV[i].getIc().equalsIgnoreCase(search)){
                                found=true;
                                break;
                            }
                            else{
                                i++;
                            }
                        }
                        if(found == true){
                            System.out.println("\n====================================");
                            System.out.println("      "+ SV[i].getName()+" is a SILVER MEMBER");
                            System.out.println("====================================");
                            System.out.println(SV[i].toString());
                        }
                        else{
                            System.out.println("\n-NO RECORD FOUND- ");  
                        }
                    }
                    else if(select.contains("2")){
                        System.out.println("enter ic number :");
                        String search=sc.nextLine();
                        while(found == false && i<custGD){
                            if(GD[i].getIc().equalsIgnoreCase(search)){
                                found=true; 
                                break;
                            }
                            else{
                                i++;
                            }
                        }
                        if(found == true){
                            System.out.println("\n====================================");
                            System.out.println("      "+ GD[i].getName()+" is a GOLD MEMBER");
                            System.out.println("====================================");
                            System.out.println(GD[i].toString());
                        }
                        else{
                            System.out.println("\n-NO RECORD FOUND-");  
                        }
                    }

                }else if(choice.contains("2")){
                    System.out.println("\n___________TOTAL MEMBER FOR EACH MEMBERSHIP___________");
                    System.out.println(">>>>>Total member for Silver Membership : " + custSV);
                    System.out.println(">>>>>Total member for Gold Membership : " + custGD);
                    System.out.println("________________________________________________________");
                }else if(choice.contains("3")){
                    System.out.println("=======================================================");
                    System.out.println("\nThe lowest charge for Silver Member: " + SV[minSV].toString());
                    System.out.println("\nThe lowest charge for Gold Member: " + GD[minGD].toString());
                    System.out.println("***********************************************");
                    System.out.println("\nThe highest charge for Silver Member: " + SV[indexSV].toString());
                    System.out.println("\nThe highest charge for Gold Member: " + GD[indexGD].toString());
                    System.out.println("=======================================================");
                }else if(choice.contains("4")){

                    System.out.println("__________$ TOTAL INCOME FOR EACH MEMBERSHIP $ ___________");
                    System.out.println("        ----Total income for Silver Membership----");
                    System.out.println("        ----         = RM "+totalIncomeSV+  "       ----");
                    System.out.println("________________________________________________________");
                    System.out.println("        ----Total income for Gold Membership----");
                    System.out.println("        ----         = RM "+totalIncomeGD+  "       ----");
                    System.out.println("*********************************************************");
                    System.out.println("total company income for the year : RM "+sum);
                    System.out.println("________________________________________________________-");                

                }
 
                else if (choice.contains("5")) {
                    updateInputFile();
                } else if (choice.contains("0")) {
                    System.out.println("you have exited the program");
                    System.exit(0);
                }
            }

        } catch (Exception ioe) {
            System.out.println("\nproblem: " + ioe.getMessage());
        } finally {
            System.out.println("end of program");
        }

    }

    //update customer's data
    private static void updateInputFile() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the IC number of the member to update: ");
            String icNumber = scanner.nextLine();

            File inputFile = new File("input.txt");
            File tempFile = new File("temp.txt");

            FileReader fr = new FileReader(inputFile);
            BufferedReader br = new BufferedReader(fr);

            FileWriter fw = new FileWriter(tempFile);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            String line;
            boolean found = false;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String currentIcNumber = data[1];

                // Check if the current line contains the IC number to be updated
                if (currentIcNumber.equals(icNumber)) {
                    found = true;
                    System.out.println("Updating member details for IC number: " + icNumber);

                    // Prompt the user to enter the updated details
                    System.out.print("Enter updated name: ");
                    String updatedName = scanner.nextLine();
                    System.out.print("Enter updated IC number: ");
                    String updatedIcNumber = scanner.nextLine();
                    System.out.print("Enter updated gender: ");
                    String updatedGender = scanner.nextLine();
                    System.out.print("Enter updated category: ");
                    String updatedCategory = scanner.nextLine();
                    System.out.print("Enter updated type: ");
                    String updatedType = scanner.nextLine();
                    System.out.print("Enter updated entry: ");
                    int updatedEntry = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character

                    // Update the line with the new content
                    line = updatedName + "," + updatedIcNumber + "," + updatedGender + ","
                            + updatedCategory + "," + updatedType + "," + updatedEntry;
                }

                // Write the line to the temporary file
                pw.println(line);
            }

            br.close();
            pw.close();

            // Delete the original input file and rename the temporary file to the original name
            inputFile.delete();
            tempFile.renameTo(inputFile);

            if (found) {
                System.out.println("Member details updated successfully.");
            } else {
                System.out.println("No member found with IC number: " + icNumber);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while updating the input file.");
        }
    }
}
