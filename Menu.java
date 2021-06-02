import java.util.*;
public class Menu
{
    private String phone;
    private String name;
    public Menu(){}

    public void start(Organization o1, Menu m1)
    {
        System.out.println("Welcome to our organization! ");
        System.out.println("Please enter your phone number: "); //exception για το αν το τηλέφωνο είναι τηλέφωνο και όχι text
        Scanner scanner = new Scanner(System.in);
        phone = scanner.nextLine();
        String answer;
        Beneficiary t1 = o1.getBeneficiary(phone);
        Donator s1 = o1.getDonator(phone);

        if (phone.equals(o1.getAdmin().getphone()))
        {
            System.out.println("Welcome Admin! \n" 
                + "Username: " + o1.getAdmin().getName() + "\n"
                + "Phone number: " + o1.getAdmin().getPhone() + "\n"
                + "You belong in the Organization " + o1.getOrgName());
            //μέθοδο για συνέχεια
        }

        else if (t1 != null)
        {
            System.out.println("Welcome Beneficiary! \n" 
                + "Username: " + t1.getName() + "\n"
                + "Phone number: " + t1.getPhone() + "\n"
                + "Number Of Family Members: " + t1.getnoPersons() + "\n"
                + "You belong in the Organization " + o1.getOrgName());
                beneficiaryMenu(t1, o1, m1);
        }
        
        else if (s1 != null)
        {
            System.out.println("Welcome Donator! \n" 
                + "Username: " + s1.getName() + "\n"
                + "Phone number: " + s1.getPhone() + "\n"
                + "You belong in the Organization " + o1.getOrgName());
                m1.donatorMenu(s1, o1, m1);
            //μέθοδο για συνέχεια
        }
        else 
        {
            System.out.println ("This user is not yet registered in our system.");
            System.out.println ("Please enter yes to continue with your sign up, or no to exit the program.");
            answer = scanner.nextLine();
            if (answer.equals("yes"))
            {
                m1.signUp(o1, m1);
            }
            else if (answer.equals("no"))
            {
                System.exit(0);
                scanner.close();
            }
        }
        scanner.close();
    }

    public void signUp(Organization o1, Menu m1)
    {
        String select;
        int person = 1;
        int id = 1;
        int id2 = 2;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please Input your Name: ");
        name = scanner.nextLine();
        System.out.println("Please Input your Phone Number: ");
        phone = scanner.nextLine();
        System.out.println("Type 1 If you want to create a Donator Account or Type 2 If you want to create a Beneficiary Account");
        select = scanner.nextLine();
        if(select.equals("1"))
        {
            Donator d = new Donator((id++), name, phone);
            o1.insertDonator(d);
            System.out.println("Welcome Donator! \n" 
                + "Username: " + name + "\n"
                + "Phone number: " + phone + "\n"
                + "You belong in the Organization " + o1.getOrgName());
                m1.donatorMenu(d, o1, m1);
        }
        else if(select.equals("2"))
        {
            System.out.println("Please enter the Number of Members of Your Family: ");
            person = scanner.nextInt();
            Beneficiary b = new Beneficiary((id2++), name, phone, person);
            o1.insertBeneficiary(b);
            System.out.println("Welcome Beneficiary! \n"
                + "Username: " + name + "\n"
                + "Phone number: " + phone + "\n"
                + "Number Of Family Members: " + person + "\n"
                + "You belong in the Organization " + o1.getOrgName());
                beneficiaryMenu(b, o1, m1);
        }
        else{
            //Εδώ θέλουμε ένα exception κατά την γνώμη μου που θα κάνει exit το πρόγραμμα
        }
        scanner.close();
    }

    public void donatorMenu(Donator d, Organization o, Menu m1){
        int select = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please Select One Of The Following Options: ");
        System.out.println("1) Add Offer \n" + 
        "2) Show Offers \n" + 
        "3) Commit \n" +
        "4) Back \n" +
        "5) Logout \n" +
        "6) Exit \n");
        select = scanner.nextInt();
        while (select < 1 || select > 6){
            System.out.println("Please Select A Valid Option: ");
            select = scanner.nextInt();
        }
        switch (select){
            case 1:
            addOffers(d, o);
                break;

            case 2:
            showOffers(d, o);
                break;
            case 3:

            d.getOffersList().commit(o);
            System.out.println("Your Changes Were Succesfully Saved!");

            break;

            case 4:

            break;

            case 5:
                System.out.println("Logging out... Thank You For Using Our Service! ");
                start(o, m1);
                break;
            case 6:
                System.exit(0);
                break;
            default :

        }
        m1.donatorMenu(d, o, m1);
        scanner.close();
    }
    public void beneficiaryMenu(Beneficiary b, Organization o, Menu m1){
        int select = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please Select One Of The Following Options: ");
        System.out.println("1) Add Request \n" + 
        "2) Show Requests \n" + 
        "3) Commit \n" +
        "4) Back \n" +
        "5) Logout \n" +
        "6) Exit \n");
        select = scanner.nextInt();
        while (select < 1 || select > 6){
            System.out.println("Please Select A Valid Option: ");
            select = scanner.nextInt();
        }
        switch (select){
            case 1:
                addRequest(b, o);
                break;

            case 2:
            
                break;
            case 3:
            
            //o.currentDonations.getRdEntities().add(req);
            System.out.println("Your Changes Were Succesfully Saved!");
            break;

            case 4:

            break;

            case 5:
                System.out.println("Logging out... Thank You For Using Our Service! ");
                start(o, m1);
                break;
            case 6:
                System.exit(0);
                break;
            default :

        }
        m1.beneficiaryMenu(b, o, m1);
        scanner.close();
    }
    public void adminMenu (Organization o, Menu m1){
        int select = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please Select One Of The Following Options: ");
        System.out.println("1) View \n" + 
        "2) Monitor Organization \n" + 
        "3) Back \n" +
        "4) Logout \n" +
        "5) Exit \n");
        select = scanner.nextInt();
        while (select < 1 || select > 6){
            System.out.println("Please Select A Valid Option: ");
            select = scanner.nextInt();
        }
        switch (select){
            case 1:
            viewEntities(o);
            break;

            case 2:
            monitorOrg(o);
            break;

            case 3:

            break;

            case 4:
                System.out.println("Logging out... Thank You For Using Our Service! ");
                start(o, m1);
                break;
            case 5:
                System.exit(0);
                break;
            default :

        }
        scanner.close();
    }
    public void addOffers(Donator d, Organization o){
        int id = 0;
        Scanner scanner1 = new Scanner(System.in);
        Scanner scanner2 = new Scanner(System.in);
        int select1 = 0;
        String select2;
        double amount = 0;
        double hours = 0;
        System.out.println("Please Select One Of The Following Options: ");
        System.out.println("1) Materials 2) Services ");
        select1 = scanner1.nextInt();
        while (select1 < 1 || select1 > 2){
            System.out.println("Please Select A Valid Option: ");
            select1 = scanner1.nextInt();
        }
        if(select1 == 1){
            o.listMaterials();
            System.out.println("Input the ID of the Material You Want to Contribute: ");
            id = scanner1.nextInt();
            System.out.println("Input the Amount You Want to Contribute: ");
            amount = scanner1.nextDouble();
            System.out.println("Confirm the Donations (yes/no): ");
            select2 = scanner2.nextLine();
            if (select2.equals("yes")){
                System.out.println("The Donation was succeed.");
                RequestDonation req = new RequestDonation(o.getEntityById(id), amount);
                d.getOffersList().add(req);
                
            }else if(select2.equals("no")) System.out.println("The Donation was cancelled.");
        }
        if(select1 == 2){
            o.listServices();
            System.out.println("Input the ID of the Service You Want to Contribute: ");
            id = scanner1.nextInt();
            System.out.println("Input the Amount You Want to Contribute: ");
            hours = scanner1.nextDouble();
            System.out.println("Confirm the Donation (yes/no): ");
            select2 = scanner2.nextLine();
            if (select2.equals("yes")){
                System.out.println("The Donation was succeed.");
                RequestDonation req = new RequestDonation(o.getEntityById(id), hours);
                d.getOffersList().add(req);
            }else System.out.println("The Donation was cancelled.");
        }
    }
    public void addRequest(Beneficiary b, Organization o){
        int id = 0;
        Scanner scanner1 = new Scanner(System.in);
        Scanner scanner2 = new Scanner(System.in);
        int select1 = 0;
        String select2;
        double amount = 0;
        double hours = 0;
        System.out.println("Please Select One Of The Following Options: ");
        System.out.println("1) Materials 2) Services ");
        select1 = scanner1.nextInt();
        while (select1 < 1 || select1 > 2){
            System.out.println("Please Select A Valid Option: ");
            select1 = scanner1.nextInt();
        }
        if(select1 == 1){
            o.listMaterials();
            System.out.println("Input the ID of the Material You Want to Request: ");
            id = scanner1.nextInt();
            System.out.println("Input the Amount You Want to Request: ");
            amount = scanner1.nextDouble();

            System.out.println("Confirm the Request (yes/no): ");
            select2 = scanner2.nextLine();
            if (select2.equals("yes")){
                System.out.println("The Request was succeed.");
                RequestDonation req = new RequestDonation(o.getEntityById(id), amount);
                b.getRequestsList().add(o, req, b);
            }else if(select2.equals("no")) System.out.println("The Request was cancelled.");
        }
        if(select1 == 2){
            o.listServices();
            System.out.println("Input the ID of the Service You Want to Contribute: ");
            id = scanner1.nextInt();
            System.out.println("Input the Amount You Want to Contribute: ");
            hours = scanner1.nextDouble();
            System.out.println("Confirm the Request (yes/no): ");
            select2 = scanner2.nextLine();
            if (select2.equals("yes")){
                System.out.println("The Request was succeed.");
                RequestDonation req = new RequestDonation(o.getEntityById(id), hours);
                b.getRequestsList().add(o, req, b);
            }else System.out.println("The Request was cancelled.");
        }
    }
    public void showOffers(Donator d, Organization o){
                Scanner scanner = new Scanner(System.in);
                Scanner scanner1 = new Scanner(System.in);
                int select3 = 0;
                String select4;
                String select5;
                double q = 0;
                d.listOffers();
                if (!d.listOffers()){
                    return;
                }
                System.out.println("Please Select One Of The Options: \n" +
                "a) Edit or Delete a Donation \n" +
                "b) Delete All Your Donations \n" +
                "c) Commit ");
                select4 = scanner.nextLine();
                if (select4.equals("a")){
                    System.out.println("Please Select One Of The Donations by its Number: ");
                    select3 = scanner1.nextInt();
                    System.out.println("Please Press d for Delete or e For Edit: ");
                    select5 = scanner1.nextLine();
                    if (select5.equals("d"))
                    {
                        d.getOffersList().remove(select3);
                    }
                    else {
                        System.out.println("Please Enter the New Quantity You Wish to Donate: ");
                        q = scanner1.nextDouble();
                        d.getOffersList().modify(d.getOffersList().get(select3), q);
                    }
                }
                else if (select4.equals("b")){
                    d.getOffersList().reset();
                }
                else if (select4.equals("c")){
                    d.getOffersList().commit(o);
                }
    }
    public void viewEntities(Organization o){
        int select1 = 0;
        Scanner scanner1 = new Scanner(System.in);
        System.out.println("Please Select One Of The Following Options: ");
        System.out.println("1) Materials 2) Services ");
        select1 = scanner1.nextInt();
        while (select1 < 1 || select1 > 2){
            System.out.println("Please Select A Valid Option: ");
            select1 = scanner1.nextInt();
        }
        if(select1 == 1){
            o.listMaterials();
        }
        if(select1 == 2){
            o.listServices();
        }
    }
    public void monitorOrg(Organization o){
        int select = 0;
        int id = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please Select One Of The Following Options: ");
        System.out.println(
        "1) List Beneficiaries \n" + 
        "2) List Donators \n" + 
        "3) Reset Beneficiaries Lists \n");
        select = scanner.nextInt();
        while (select < 1 || select > 3){
            System.out.println("Please Select A Valid Option: ");
            select = scanner.nextInt();
        }
        switch (select){
        case 1:
            o.listBeneficiaries();
            System.out.println("Please Select One Beneficiary from the list above: ");
            id = scanner.nextInt();
            listBen(o, o.getBeneficiaryById(id));
            break;

        case 2:
            o.listDonators();
            System.out.println("Please Select One Donator from the list above: ");
            id = scanner.nextInt();
            listDon(o, o.getDonatorById(id));
            break;

        case 3:
        for(Beneficiary b : o.beneficiaryList){
            b.getReceivedList().reset();
        }
        System.out.print("All the Received Lists of All the Beneficiaries Are Cleared!");
            break;

        default:
    }
}
    public void listBen(Organization o, Beneficiary b){
        int select = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please Select One Of The Following Options: ");
        System.out.println(
        "1) View ReceivedList \n" + 
        "2) Clear ReceivedList  \n" + 
        "3) Remove Beneficiary \n");
        select = scanner.nextInt();
        while (select < 1 || select > 3){
            System.out.println("Please Select A Valid Option: ");
            select = scanner.nextInt();
        }
        switch (select){
        case 1:
            for(int i=0; i<b.getReceivedList().getRdEntities().size(); i++)
            {
                System.out.println(String.format("ID: %d Name: %s Quantity %d" 
                , b.getReceivedList().getRdEntities().get(i).getID()
                , b.getReceivedList().getRdEntities().get(i).getEntity().getName() 
                , b.getReceivedList().getRdEntities().get(i).getQuantity()));
            }
            break;

        case 2:
            b.getReceivedList().reset();
            System.out.println(String.format("The Received List of the Beneficiary %s has been emptied ", b.getName()));
            break;

        case 3:
            o.removeBeneficiary(b);
            System.out.println(String.format("The Beneficiary with Name %s has been removed from the Organization ", b.getName()));
            break;

        default:
        }
    }
    public void listDon(Organization o, Donator d){
        int select = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please Select One Of The Following Options: ");
        System.out.println(
        "1) View getOffersList \n" + 
        "2) Remove Donator \n");
        select = scanner.nextInt();
        while (select < 1 || select > 3){
            System.out.println("Please Select A Valid Option: ");
            select = scanner.nextInt();
        }
        if(select == 1 ){
            for(int i=0; i<d.getOffersList().getRdEntities().size(); i++)
            {
                System.out.println(String.format("ID: %d Name: %s Quantity %d" 
                , d.getOffersList().getRdEntities().get(i).getID()
                , d.getOffersList().getRdEntities().get(i).getEntity().getName() 
                , d.getOffersList().getRdEntities().get(i).getQuantity()));
            }
        }else if (select == 2){

            o.removeDonator(d);
            System.out.println(String.format("The Donator with Name %s has been removed from the Organization ", d.getName()));
        }
    }
}
