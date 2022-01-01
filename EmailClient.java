import java.util.function.Supplier;
import java.io.*;
import java.util.*;  
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EmailClient {
    
    protected static ArrayList<Recipient>count_list = EmailClient.ReadFromtheTextfileAndLoadToAArrayList();
    protected static int count = count_list.size();
    public static void main(String[] args) {
        int option=EmailClient.showMenu();
        switch (option) {
            case 1:
                EmailClient.getUserInputs();  //Done-Recheck
                EmailClient.showMenu();
                break;
            case 2:
                EmailClient.sendAnEmail();    //To-Do
                EmailClient.showMenu();
                break;
            case 3:
                String today = EmailClient.getDate();
                EmailClient.printTheRecipientsWhohaveBirthdays(today);  //To-Do
                EmailClient.showMenu();        
                break;
            case 4:
                EmailClient.PrintTheEmailDetailsOfGivenData(EmailClient.getDate());
                EmailClient.showMenu();
                break;
            case 5:
                System.out.println(EmailClient.ShowTheRecipientCount());
                EmailClient.showMenu();
                break;
            case 6:
                System.out.println("Thank You!"); //To-Do case 4 editing.
            default:
                break;
        }
        
    }

    private static String getDate(){
        LocalDate dateObj = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String date = dateObj.format(formatter);
        // System.out.println(date);
        return date;
    }

    public static void getUserInputs(){         //Done-Recheck
            Scanner sc = new Scanner(System.in);
            System.out.print("Wlecome!\nPlease Choose your Recipient\n\t1. Official\n\t2. Official-Friend\n\t3. Personal Friend\n\t4. Back to Menu\n");
            int choice_recipient = sc.nextInt();
            switch (choice_recipient){
                case 1:
                    count+=1;
                    EmailClient.addOfficialRecipientInput();
                    break;
                case 2:
                    count+=1;
                    EmailClient.addOfficialFriendRecipientInput();
                    break;
                case 3:
                    count+=1;
                    EmailClient.addPersonalFrientRecipientInput();
                    break;
                case 4:
                    EmailClient.showMenu();
                    break;
            }
    
    }

    private static void writeInToTextFile(String msg, String FileName){
        try {
            BufferedWriter a = new BufferedWriter(new FileWriter(FileName, true));
            a.write(msg);
            a.write('\n');
            a.close();
            System.out.println("Saved successfully!");
        } catch (IOException e) {
            System.out.println("An error occurred!");
            e.printStackTrace();
        }
    }
    
    private static void addOfficialRecipientInput(){ //To-Do
        System.out.println("Enter your input in to the following order - Official: nimal,nimal@gmail.com,ceo");
        Scanner scanner1 = new Scanner(System.in);
        String strOfficial = scanner1.nextLine();
        writeInToTextFile(strOfficial, "email_write.txt");
    }   

    private static void addOfficialFriendRecipientInput(){  //To-Do
        System.out.println("Enter your input in to the following order - Office_friend: kamal,kamal@gmail.com,clerk,2000/12/12 ");
        Scanner scanner2 = new Scanner(System.in);
        String strOfficialFriend = scanner2.nextLine();
        writeInToTextFile(strOfficialFriend, "email_write.txt");
    }  

    private static void addPersonalFrientRecipientInput(){   ////To-Do
        System.out.println("Enter your input in to the following order - Personal: sunil,<nick-name>,sunil@gmail.com,2000/10/10");
        Scanner scanner3 = new Scanner(System.in);
        String strPersonalFriend = scanner3.nextLine();
        writeInToTextFile(strPersonalFriend, "email_write.txt");
    }  

    private static int showMenu(){
        Scanner scanner = new Scanner(System.in);
            System.out.println("Enter option type: \n"
                    + "1 - Adding a new recipient\n"
                    + "2 - Sending an email\n"
                    + "3 - Printing out all the recipients who have birthdays\n"
                    + "4 - Printing out details (subject and recipient) of all the emails sent on a date specified by user input\n"
                    + "5 - Printing out the number of recipient objects in the application\n"
                    + "6 - close");
            int option = scanner.nextInt();
            return option;
    }

    private static ArrayList<Recipient> ReadFromtheTextfileAndLoadToAArrayList(){
        ArrayList<Recipient> return_read_list = new ArrayList<Recipient>();
        try {
            File myObj = new File("email_write.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] arrOfStr = data.split(":");
                String recipientType = arrOfStr[0];
                //System.out.println(recipientType);
                switch (recipientType) {
                    case "Office_friend":
                        String[] arrofDetail = arrOfStr[1].split(",");
                        OfficialFriends off = new OfficialFriends(arrofDetail[0], arrofDetail[1], arrofDetail[2], arrofDetail[3]);
                        return_read_list.add(off);
                        break;
                    
                    case "Personal":
                        String[] arrofDetail1 = arrOfStr[1].split(",");
                        PersonalFriends off1 = new PersonalFriends(arrofDetail1[0], arrofDetail1[1], arrofDetail1[2], arrofDetail1[3]);
                        return_read_list.add(off1);
                        break;

                    case "Official":
                        String[] arrofDetail2 = arrOfStr[1].split(",");
                        Official off2 = new Official(arrofDetail2[0], arrofDetail2[1], arrofDetail2[2]);
                        return_read_list.add(off2);
                        break;


                    default:
                        break;
                }    
            }
            myReader.close();   
           }catch (FileNotFoundException e) {
            System.out.println("An error occurred while Reading the emails file.");
            e.printStackTrace();
          }
        return return_read_list;
    }

    private static void sendAnEmail(){     //To-Do
    }   

    private static void  printTheRecipientsWhohaveBirthdays(String bday){   //To-Do String conflict
        ArrayList<Recipient> bdayWishList = count_list;
        for(int i=0; i<bdayWishList.size(); i++){
            //System.out.println(i);
            // (Employee) Paper1.otAry.get(i)
            try{
                //System.out.println((((wishSend) bdayWishList.get(i)).getBirthday()).equals(bday));
                //System.out.println("hasara"=="hasara");
                if((((wishSend) bdayWishList.get(i)).getBirthday()).equals(bday)){
                    EmailClient.writeInToTextFile(bdayWishList.get(i).getName()+','+bdayWishList.get(i).getEmail()+','+((wishSend)bdayWishList.get(i)).sendWish()+','+EmailClient.getDate(), "email_stored.txt");
                    System.out.println("Recipient Name: "+bdayWishList.get(i).getName()+"\n Recipient email: "+bdayWishList.get(i).getEmail()+"\n Content: "+((wishSend)bdayWishList.get(i)).sendWish());
                }
            }catch(Exception e){}
            
        }
    }

    private static void PrintTheEmailDetailsOfGivenData(String date){  //To-Do-Extreme case
        ArrayList<EmailBody> return_read_list2 = new ArrayList<EmailBody>();
        try {
            File myObj = new File("email_stored.txt");
            Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    String[] arrOfStr = data.split(",");
                    EmailBody eb = new EmailBody(arrOfStr[0], arrOfStr[1], arrOfStr[2], arrOfStr[3]);
                    return_read_list2.add(eb);
                    }

        }catch (FileNotFoundException e) {
            System.out.println("File Not Found");
            e.printStackTrace();
        }
        
        for(int i=0;i<return_read_list2.size();i++){
            System.out.println(return_read_list2.get(i).getDate()+"\t"+date);
            if ((return_read_list2.get(i).getDate()).equals(date)){
                System.out.print(return_read_list2.get(i).getRecipientName()+" "+return_read_list2.get(i).getRecipientEmail()+" "+return_read_list2.get(i).getContent());
            }
        }
    }

    private static int ShowTheRecipientCount(){
        return count;
    }
}

class Recipient{
    protected String name, email;
    public Recipient(String name, String email){
        this.name=name;
        this.email=email;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}

class Official extends Recipient{
    protected String designation;
    public Official(String name, String email, String designation){
        super(name, email);
        this.designation=designation;
    }
}

interface wishSend{
    public String sendWish();
    public String getBirthday();
}

class OfficialFriends extends Official implements wishSend{
    protected String bday;
    public OfficialFriends(String name, String email,String designation,String bday){
        super(name, email, designation);
        this.bday=bday;
    }

    public String sendWish(){
        //System.out.println("Hugs and love on your birthday. Hasara");
        return "Hugs and love on your birthday. Hasara";
        //TO-DO
    }

    public String getBirthday(){
        return this.bday;
    }
}

class PersonalFriends extends Recipient implements wishSend{
    protected String bday, nickname;
    public PersonalFriends(String name, String nickname, String email, String bday){
        super(name, email);
        this.bday=bday;
        this.nickname=nickname;
    }

    public String sendWish(){
        //System.out.println("Wish you a Happy Birthday. Hasara");
        return "Wish you a Happy Birthday. Hasara";
        //TO-DO
    }

    public String getBirthday(){
        return this.bday;
    }


}

class EmailBody{
    protected String recipientName, recipientEmail, recipientcontent, sentdate;

    public EmailBody(String recipientName, String recipientEmail, String content, String date){
        this.recipientName=recipientName;
        this.recipientEmail=recipientEmail;
        this.recipientcontent=content;
        this.sentdate=date;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public String getRecipientEmail() {
        return recipientEmail;
    }

    public String getContent() {
        return recipientcontent;
    }

    public String getDate() {
        return sentdate;
    }
}









































// private ArrayList<wishSend> ReadFromtheTextfileAndLoadToAArrayList(){
    //     ArrayList<wishSend> return_read_list = new ArrayList<wishSend>();
    //     try {
    //         File myObj = new File("email_write.txt");
    //         Scanner myReader = new Scanner(myObj);
    //         while (myReader.hasNextLine()) {
    //             String data = myReader.nextLine();
    //             String[] arrOfStr = data.split(":");
    //             String recipientType = arrOfStr[0];
    //             System.out.println(recipientType);
    //             switch (recipientType) {
    //                 case "Office_friend":
    //                     String[] arrofDetail = arrOfStr[1].split(",");
    //                     OfficialFriends off = new OfficialFriends(arrofDetail[0], arrofDetail[1], arrofDetail[2], arrofDetail[3]);
    //                     return_read_list.add(off);
    //                     break;
                    
    //                 case "Personal":
    //                     String[] arrofDetail1 = arrOfStr[1].split(",");
    //                     PersonalFriends off1 = new PersonalFriends(arrofDetail1[0], arrofDetail1[1], arrofDetail1[2], arrofDetail1[3]);
    //                     return_read_list.add(off1);
    //                     break;

    //                 default:
    //                     break;
    //             }    
    //         }
    //         myReader.close();   
    //        }catch (FileNotFoundException e) {
    //         System.out.println("An error occurred while Reading the emails file.");
    //         e.printStackTrace();
    //       }
    //     return return_read_list;
    // }


      // private void printTheRecipientsWhohaveBirthdays(String bday){   //To-Do
    //     EmailClient ec3 = new EmailClient();
    //     ArrayList<wishSend> bdayWishList = ec3.ReadFromtheTextfileAndLoadToAArrayList();
    //     for(int i=0; i<bdayWishList.size(); i++){
    //         if(bdayWishList.get(i).getBirthday()==bday){
    //             Recipient r1 = bdayWishList.get(i);
    //             ec3.writeInToTextFile(r1.getName()+','+r1.getEmail()+','+bdayWishList.get(i).sendWish()+','+ec3.getDate(), "email_stored.txt");
    //             System.out.println("Recipient Name: "+r1.getName()+"\n Recipient email: "+r1.getEmail()+"\n Content: "+bdayWishList.get(i).sendWish());
    //         }
    //     }
    // }