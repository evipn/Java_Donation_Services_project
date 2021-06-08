import java.util.Scanner;

public class Menu
{
    static Scanner myObj=new Scanner(System.in);  //Dhmiourgia antikeimenou gia scan

    //Menu, elegxei ean einai eggegramenos o xrhsths: ean einai ton vazei sto antistoixo menu, ean den einai tou dinei thn epilogh na eggrafei (san donator h beneficiary)

    public static void search()
    {
        System.out.println("Please insert your number: ");
        String number = myObj.next();
        System.out.println("Please insert your name too:");
        String na = myObj.next();


        if (Admin.admin_phones.contains(number))
        {
            AdminMenu();
        }
        else if(Beneficiary.beneficiary_phones.contains(number))
        {
            Beneficiary.set_Name(na);
            Beneficiary.set_Number(number);
            BeneficiaryMenu();
        }
        else if(Donator.donator_phones.contains(number))
        {
            Donator.set_Name(na);
            Donator.set_Number(number);
            DonatorMenu();
        }
        else
        {
            System.out.println("You are not signed up!");
            System.out.println("Are you interested of joining us either as Donator or Beneficiary?");
            int choice;

            do
            {
                System.out.println("Press 1 if not, press 2 for joining as Donator, press 3 for joining as Beneficiary");
                choice=myObj.nextInt();
            }while(choice<1 || choice>3);  //amyntikos programmatismos me do-while

            if(choice==1)
            {
                System.out.println("Have a nice day!");
                System.exit(0);  //kleisimo programmatos
            }
            else if(choice==2)
            {
                System.out.println("Insert your name: ");  //eggrafh xrhsth ws donator
                String name=myObj.next();

                Donator donator=new Donator(name,number);
                donator.set_Name(name);
                donator.set_Number(number);
                Organization.insertDonator(donator);

                donator.donator_phones.add(number);
                DonatorMenu();
            }
            else
            {
                System.out.println("Insert your name: "); //eggrafh xrhsth ws beneficiary
                String name=myObj.next();

                System.out.println("Insert the members of your family: ");
                int no_Persons= myObj.nextInt();

                Beneficiary ben=new Beneficiary(name,number,no_Persons);
                ben.set_Name(name);
                ben.set_Number(number);

                Organization.insertBeneficiary(ben);
                ben.beneficiary_phones.add(number);

                BeneficiaryMenu();
            }
        }

    }

    //To menu tou donator
    static void DonatorMenu()
    {
        int options;

        do
        {
            System.out.println("Hello!");
            System.out.println("===============");
            System.out.println("Name: "+ Donator.get_Name());
            System.out.println("Number: "+ Donator.get_Number());
            System.out.println("Organization Name: " +Organization.name);
            System.out.println("===============");

            System.out.println("1. Add Offer");
            System.out.println("2. Show Offers");
            System.out.println("3. Commit");
            System.out.println("4. Back");
            System.out.println("5. Logout");
            System.out.println("6. Exit");

            options=myObj.nextInt();

        }while (options<1 || options>6 ); //amyntikos

        switch (options)
        {
            case 1:
                int choice;

                do  //epilogh tou donator an thelei na prosferei eite materials eite services
                {
                    System.out.println("1.Material (quantity=" + Material.get_quantity() + ")" + "\n2.Services (quantity=" +Service.get_quantity() + ")");
                    choice= myObj.nextInt();
                }while(choice<1 || choice>2);

                if(choice==1)
                {
                    Organization.listEntities(choice); //emfanish twn materials
                    String option;

                    do{
                        System.out.println("\nDo you want to donate any of these materials? y/n");
                        option = myObj.next();
                    }while(!(option.equals("y") || option.equals("n") )); //amyntikos

                    if (option.equals("y"))
                    {
                        System.out.println("Which of these materials you want to donate?Type the name of it: ");
                        String m = myObj.next(); //epilogh poiou material tha kanei donate

                        for (Entity i : Organization.entityList)
                        {
                            if (m.equals(i.getName()))
                            {
                                System.out.println("Enter the quantity of the material that you want to donate: ");
                                int quantity = myObj.nextInt();
                                System.out.println("Thank you!");

                                Material.counter = Material.counter + quantity; //h posothta tou material pou ekane offer ananewnetai
                                i.quantity=i.quantity+quantity;

                                RequestDonation rd=new RequestDonation(i,quantity); //dhmiourgia antikeimenou RequestDonation me onoma rd
                                RequestDonationList.add(rd); //prosthesh tou rd sthn lista mesw ths methodou add ths klashs RequestDonationList
                                Offers.add(rd);

                                DonatorMenu(); //epistrofh tou xrhsth sto menu
                            }
                        }
                    }
                    else
                    {
                        DonatorMenu(); //epistrofh tou xrhsth sto menu
                    }
                }

                else
                {
                    Organization.listEntities(choice);  //akrivws to idio alla gia services
                    String op;
                    do
                    {
                        System.out.println("\nDo you want to donate any of these services? y/n");
                        op = myObj.next();
                    }while(!(op.equals("y") || op.equals("n") ));
                    if(op.equals("y"))
                    {
                        System.out.println("Which of these services you want to donate?Type the name of it: ");
                        String s=myObj.next();

                        for (Entity j: Organization.entityList)
                        {
                            if(s.equals(j.getName()))
                            {
                                System.out.println("Enter the quantity of the service that you want to donate: ");
                                int quantity=myObj.nextInt();

                                Service.counter=Service.counter+quantity;
                                j.quantity=j.quantity+quantity;

                                RequestDonation rd=new RequestDonation(j,quantity);
                                RequestDonationList.add(rd);
                                Offers.add(rd);

                                DonatorMenu(); //epistrofh tou xrhsth sto menu

                            }
                        }
                    }
                    else
                    {
                        DonatorMenu(); //epistrofh tou xrhsth sto menu
                    }
                }
                break;

            case 2:

                if(!Organization.currentDonations.isEmpty())
                {
                    System.out.println("Offers: ");
                    RequestDonationList.monitor();  //printarei ta offers
                    System.out.println("Choose an offer by typing its id: ");
                    int id = myObj.nextInt();  //diavazei to offer

                    for (RequestDonation rd : RequestDonationList.rdEntities) { //emfanish tou menu gia kathe offer pou epilegei o xrhsths
                        if (id == RequestDonationList.get(rd)) {
                            System.out.println("1. Delete ");
                            System.out.println("2. Change quantity");
                            System.out.println("3. Clear");
                            System.out.println("4. Commit");

                            int opt = myObj.nextInt(); //diavazei thn epilogh tou xrhsth

                            switch (opt)
                            {
                                case 1:
                                    RequestDonationList.remove(rd); //afairesh tou offer
                                    DonatorMenu(); //epistrofh tou xrhsth sto menu

                                    break;

                                case 2:
                                    System.out.println("Insert the quantity that you want to offer: ");
                                    double new_quantity=myObj.nextDouble();
                                    RequestDonationList.modify(new_quantity); //allagh ths posothtas
                                    DonatorMenu(); //epistrofh tou xrhsth sto menu

                                    break;

                                case 3:
                                    RequestDonationList.reset(); //tis diagrafei oles
                                    DonatorMenu(); //epistrofh tou xrhsth sto menu

                                    break;

                                case 4:
                                    Offers.commit(); //apothikeuei to offer
                                    DonatorMenu(); //epistrofh tou xrhsth sto menu

                                    break;
                            }
                        }
                    }
                }
                else
                {
                  System.out.println("Sorry, currently there are no available offers."); //h periptwsh pou den yparxoun offer ekeinh thn stigmh
                  DonatorMenu();  //epistrofh tou xrhsth sto menu
                }

                break;

            case 3:
                Donator.commit(); //apothikeuei tou donator me ta offers pou exei eisagei mexri stigmhs
                DonatorMenu();  //epistrofh tou xrhsth sto menu

                break;

            case 4: //back
                String opt;

                do
                {
                    System.out.println("Are you sure you want to go back?(y/n)"); //epilogh tou xrhsth ean thelei na gyrisei pisw
                    opt = myObj.next();
                }while(!(opt.equals("y") || (opt.equals("n"))));

                if (opt.equals("y"))
                {
                    search(); //phgainei ton xrhsth sthn arxh
                }
                else
                {
                    DonatorMenu();  //epistrofh tou xrhsth sto menu
                }

                break;

            case 5: //log out

                System.out.println("Logging out...");
                System.out.println("Sign in? y/n? ");
                String op=myObj.next();

                if(op.equals("y"))
                {
                    search(); //phgainei ton xrhsth sthn arxh
                }
                else
                {
                    System.out.println("Thank you!Have a nice day!");
                    System.exit(0);
                }
                break;

            case 6: //exit
                System.out.println("Thank you!Have a nice day!");
                System.exit(0); //kleisimo programmatos
                break;
        }
    }

    static void BeneficiaryMenu()
    {
        int options;

        do
        {
            System.out.println("Hello!");
            System.out.println("===============");
            System.out.println("Name: "+ Beneficiary.get_Name());
            System.out.println("Number: "+ Beneficiary.get_Number());
            System.out.println("Organization Name: " +Organization.name);
            System.out.println("===============");

            System.out.println("1. Add Request");
            System.out.println("2. Show Request");
            System.out.println("3. Commit");
            System.out.println("4. Back");
            System.out.println("5. Logout");
            System.out.println("6. Exit");

            options=myObj.nextInt();

        }while (options<1 || options>6 );

        switch (options)
        {
            case 1: //epilogh tou beneficiary an thelei na lavei eite materials eite services
                int choice;

                do
                {
                    System.out.println("1.Material (quantity=" + Material.get_quantity() + ")" + "\n2.Services (quantity=" +Service.get_quantity() + ")");
                    choice= myObj.nextInt();
                }while(choice<1 || choice>2);

                if(choice==1)
                {
                    Organization.listEntities(choice);
                    String option;

                    do{
                        System.out.println("\nDo you want to benefit from any of these materials? y/n");
                        option = myObj.next();
                    }while(!(option.equals("y") || option.equals("n") ));

                    if (option.equals("y"))
                    {
                            System.out.println("Which of these materials you want to benefit from? Type the name of it: ");
                            String m= myObj.next(); //epilogh poiou material thelei na lavei

                        for (Entity i : Organization.entityList)
                        {
                            if (m.equals(i.getName()))
                            {
                                System.out.println("Enter the quantity of the material that you want to benefit from: ");
                                int quantity = myObj.nextInt(); //epilogh ths posothtas tou material pou thelei na lavei

                                Material.counter = Material.counter + quantity; //ananewsh ths posothtas tou material pou lamvanei

                                Requests req=new Requests(i,quantity);  //dhmiourgia antikeimenou Requests me onoma req
                                RequestDonation rd=new RequestDonation(i,quantity); //dhmiourgia antikeimenou RequestDonation me onoma rd

                                RequestDonationList.add(rd); //prosthesi autou sthn lista
                                Requests.add(req); //prosthesi autou sta requests

                                BeneficiaryMenu();
                            }
                        }
                    }
                    else
                    {
                        BeneficiaryMenu();
                    }
                }

                else //akrivws to idio alla gia services
                {
                    Organization.listEntities(choice);
                    String op;
                    do{
                        System.out.println("\nDo you want to benefit from any of these services? y/n");
                        op = myObj.next();
                    }while(!(op.equals("y") || op.equals("n") ));
                    if(op.equals("y"))
                    {
                        System.out.println("Which of these services you want to benefit from? Type the name of it: ");
                        String s=myObj.next();

                        for (Entity j: Organization.entityList)
                        {
                            if(s.equals(j.getName()))
                            {
                                System.out.println("Enter the quantity of the service that you want to benefit from: ");
                                int quantity=myObj.nextInt();

                                Service.counter=Service.counter+quantity;

                                Requests req=new Requests(j,quantity);
                                RequestDonation rd=new RequestDonation(j,quantity);

                                RequestDonationList.add(rd);
                                Requests.add(req);

                                BeneficiaryMenu();

                            }
                        }
                    }
                    else
                    {
                        BeneficiaryMenu();
                    }
                }

                break;

            case 2:
                if(!Beneficiary.receivedList.isEmpty())
                {
                    System.out.println("Requests: /n");
                    Beneficiary.monitor(); //emfanizei ola ta requests
                    System.out.println("Choose your request.");
                    String r=myObj.next(); //epilogh poiou thelei

                    for(Requests req : Beneficiary.requestsList)
                    {
                        if(r.equals(req.entity.getName()))
                        {
                            System.out.println("1. Delete ");
                            System.out.println("2. Change quantity");
                            System.out.println("3. Clear");
                            System.out.println("4. Commit");
                            int opt = myObj.nextInt(); //diavazei thn epilogh tou xrhsth

                            switch (opt)
                            {

                                case 1:
                                    req.remove(req); //afairei to req apo ta requests

                                    break;

                                case 2:
                                    System.out.println("Insert the quantity that you want: ");
                                    double quantity= myObj.nextDouble(); //diavazei thn posothta pou thelei
                                    Requests.modify(quantity); //allagh ths posothtas

                                    break;

                                case 3:
                                    Requests.reset(); //diagrafei ta requests

                                    break;

                                case 4:
                                    Beneficiary.commit(); //apothikeuei to requests
                                    BeneficiaryMenu();

                                    break;
                            }
                        }
                    }
                }
                else
                {
                    System.out.println("Sorry, currently there are no available requests."); //h periptwsh pou den yparxoun offer ekeinh thn stigmh
                    BeneficiaryMenu(); //epistrofh tou xrhsth sto menu
                }


                break;

            case 3:
                Beneficiary.commit();//apothikeuei tou beneficiary, ta requests pou exei eisagei mexri stigmhs
                BeneficiaryMenu(); //epistrofh tou xrhsth sto menu

                break;

            case 4: //back akrivws idio me to panw apla se petaei sto antistoixo menu
                String opt;
                do
                {
                    System.out.println("Are you sure you want to go back?(y/n)");
                    opt = myObj.next();
                }while(!(opt.equals("y") || (opt.equals("n"))));

                if (opt.equals("y"))
                {
                    search();
                }
                else
                {
                    BeneficiaryMenu();
                }

                break;

            case 5: //log out akrivws idio me to panw apla se petaei sto antistoixo menu

                System.out.println("Logging out...");
                System.out.println("Sign in? y/n? ");
                String op=myObj.next();

                if(op.equals("y"))
                {
                    search();
                }
                else
                {
                    System.out.println("Thank you!Have a nice day!");
                    System.exit(0);
                }

                break;

            case 6: //exit akrivws idio me to panw apla se petaei sto antistoixo menu
                System.out.println("Thank you!Have a nice day!");
                System.exit(0);

                break;

        }

    }

    static void AdminMenu()
    {
        int options;

        do
        {
            System.out.println("Hello!");
            System.out.println("===============");
            System.out.println("Name: "+ Admin.get_Name());
            System.out.println("Number: "+ Admin.get_Number());
            System.out.println("Organization Name: " +Organization.name);
            System.out.println("===============");

            System.out.println("1. View");
            System.out.println("2. Monitor Organization");
            System.out.println("3. Back");
            System.out.println("4. Logout");
            System.out.println("5. Exit");

            options=myObj.nextInt();

        }while (options<1 || options>5 );

        switch (options)
        {
            case 1:
                int choice;

                do {
                    System.out.println("1.Material (quantity=" + Material.get_quantity() + ")" + "\n2.Services (quantity=" + Service.get_quantity() + ")"); //ektypwsh twn quantities
                    choice = myObj.nextInt(); //diavazei poio apo ta dyo thelei na dei o admin
                } while (choice < 1 || choice > 2); //amyntikos

                if (choice == 1)
                {
                    Organization.listEntities(choice); //typwnei ta stoixeia ths epiloghs tou admin
                    AdminMenu();

                }
                else
                {
                    Organization.listEntities(choice); //typwnei ta stoixeia ths epiloghs tou admin
                    AdminMenu();
                }

                break;

            case 2:
                int option;

                do //emfanish twn stoixeiwn tou org h epilogh diagrafhs twn beneficiaries
                {
                    System.out.println("1. List Beneficiaries");
                    System.out.println("2. List Donators");
                    System.out.println("3. Reset Beneficiaries Lists");

                    option=myObj.nextInt();
                }while(option<1 || option>3); //amyntikos

                switch (option)
                {
                    case 1:

                        if(!Organization.beneficiaryList.isEmpty())
                        {
                            String b;
                            System.out.println("List of Beneficiaries: ");
                            Organization.listBeneficiaries(); //lista twn beneficiaries

                            do
                            {
                                System.out.println("Type the name of the beneficiary you want: ");
                                b = myObj.next(); //diavazei to onoma pou thelei o xrhsths
                            }while(!b.equals(Beneficiary.get_Name())); //amyntikos

                            for(Beneficiary ben:  Organization.beneficiaryList) //epiloges gia ton sygkekrimeno beneficiary pou thelei o xrhsths
                            {
                                if(b.equals(ben.get_Name()))
                                {
                                    int op1;

                                    System.out.println("1. View beneficiary's received list.");
                                    System.out.println("2. Clear beneficiary's received list.");
                                    System.out.println("3. Remove beneficiary");

                                    op1=myObj.nextInt(); //diavazei poio thelei o xrhsths

                                    switch(op1)
                                    {
                                        case 1:
                                            Beneficiary.monitorReceivedList(); //emfanish ths listas tou
                                            AdminMenu();

                                            break;

                                        case 2:
                                            Beneficiary.resetReceivedList(); //diagrafh ths listas tou
                                            AdminMenu();

                                            break;

                                        case 3:
                                            Organization.removeBeneficiary(ben); //diagrafh tou ben
                                            AdminMenu();

                                            break;
                                    }
                                }
                            }
                        }
                        else
                        {
                            System.out.println("There are no Beneficiaries!"); //mhnyma se periptwsh pou den yparxei
                            AdminMenu();
                        }
                        break;

                    case 2:

                        if(!Organization.donatorList.isEmpty())
                        {
                            String d;
                            System.out.println("List of Donators: "); //typwnei tous donators
                            Organization.listDonators();

                            do
                            {
                                System.out.println("Type the name of the donator you want: ");
                                d = myObj.next(); //epilogh tou donator pou theloume
                            }while(!d.equals(Donator.get_Name())); //amyntikos

                            for(Donator don:  Organization.donatorList)
                            {
                                if(d.equals(don.get_Name()))
                                {
                                    int op1;

                                    System.out.println("1. View donator's offers list.");
                                    System.out.println("2. Remove donator");

                                    op1=myObj.nextInt(); //diavazei thn epilogh pou thelei o xrhsths

                                    switch(op1)
                                    {
                                        case 1:
                                            Donator.monitor(); //ektypwnei to offerslists
                                            AdminMenu();

                                            break;

                                        case 2:
                                            Organization.removeDonator(don); //afairei ton donator
                                            AdminMenu();

                                            break;
                                    }
                                }
                            }
                        }
                        else
                        {
                            System.out.println("There are no donators!"); //h periptwsh pou den yparxoun donators
                            AdminMenu(); //se petaei sto admin menu
                        }

                        break;

                    case 3:
                        for(Beneficiary ben:  Organization.beneficiaryList)
                        {
                            ben.resetReceivedList(); //diagrafh tou receivedlist
                            AdminMenu(); //se petaei sto admin menu
                        }

                        break;

                }
                break;

            case 3: //back akrivws idio me to panw apla se petaei sto antistoixo menu
                String opt;
                do
                {
                    System.out.println("Are you sure you want to go back?(y/n)");
                    opt = myObj.next();
                }while(!(opt.equals("y") || (opt.equals("n"))));

                if (opt.equals("y"))
                {
                    search();
                }
                else
                {
                    AdminMenu();
                }

                break;

            case 4: //log out akrivws idio me to panw apla se petaei sto antistoixo menu

                System.out.println("Logging out...");
                System.out.println("Sign in? y/n? ");
                String op=myObj.next();

                if(op.equals("y"))
                {
                    search();
                }
                else
                {
                    System.out.println("Thank you!Have a nice day!");
                    System.exit(0);
                }

                break;

            case 5: //exit akrivws idio me to panw apla se petaei sto antistoixo menu
                System.out.println("Thank you!Have a nice day!");
                System.exit(0);

                break;

        }
    }
}
