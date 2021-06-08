import java.util.ArrayList;

public class Beneficiary extends User
{
    static int noPersons = 1;
    static ArrayList<RequestDonationList> receivedList = new ArrayList<>();
    static ArrayList <Requests> requestsList = new ArrayList<>();
    static ArrayList<String> beneficiary_phones=new ArrayList<>();

    static String name;
    static String phone;

    //Beneficiary constructor
     public Beneficiary(String name, String phone ,int noPersons)
    {
        super(name, phone);
        this.noPersons=noPersons;
        beneficiary_phones.add(phone);
    }

    //setters-getters
    static void set_Name(String name)
    {
        Beneficiary.name=name;
    }

   static void set_Number(String phone)
    {
        Beneficiary.phone=phone;
    }

    static String get_Name()
    {
        return name;
    }

    static String get_Number()
    {
        return phone;
    }

    static int get_NoPersons()
    {
        return noPersons;
    }

    @Override //Override String toString method wste na ginei swsth ektypwsh twn beneficiary
    public String toString()
    {
        return "Name: " + this.get_Name() + " ,Number: " + this.get_Number();
    }

    //add sthn ReceivedList
    static void add_toReceivedList(RequestDonationList rdl)
    {
        receivedList.add(rdl);
    }

    //remove apo thn ReceivedList
    static void remove_toReceivedList(RequestDonationList rdl)
    {
        receivedList.remove(rdl);
    }

    //print twn stoixeiwn ths ReceivedList
    static void monitorReceivedList()
    {
        for (RequestDonationList requestDonationList : receivedList)
        {
            System.out.println(requestDonationList);
        }
    }

    //diagrafh twn stoixeiwn ths ReceivedList
    static void resetReceivedList()
    {
        receivedList.clear();
    }

    //add sthn requestsList
    static void add(Requests req)
    {
        requestsList.add(req);
    }

    //Remove request apo thn requestsList
    static void remove(Requests req)
    {
        requestsList.remove(req);
    }

    //Desmeysh tou request kai prosthiki toy meta apo elegxo sthn ReceivedList
    static void commit()
    {
        for(RequestDonation rd: RequestDonationList.rdEntities)
        {
            if((Requests.quantity<=Requests.entity.quantity && Requests.quantity>0) && (Requests.validRequestDonation()==true))
            {
                System.out.println("Your request has been accepted! Thank you!");

               RequestDonationList rdl=new RequestDonationList();

                Organization.modifyCurrent(rdl);
                RequestDonationList.remove(rd);
                add_toReceivedList(rdl);
            }
            else
            {
                System.out.println("We are sorry but either the quantity that you typed is not available or your request is not valid! ");
                throw new IllegalArgumentException();
            }
        }

    }

    //Print twn stoixeiwn ths requestsList
    static void monitor()
    {
        for (Requests requests : requestsList)
        {
            System.out.println(requests);
        }
    }

    //Diagrafh twn stoixeiwn ths requestsList
    static void reset()
    {
        requestsList.clear();
    }
}