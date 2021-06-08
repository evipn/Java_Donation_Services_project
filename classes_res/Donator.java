import java.util.ArrayList;

public class Donator extends User
{
    static ArrayList<Offers> offersList = new ArrayList<>();
    static ArrayList<String> donator_phones=new ArrayList<>();

    static String name;
    static String number;

    //Donator constructor
    public Donator(String name, String number)
    {
        super(name,number);
        donator_phones.add(number);
    }

    //setters-getters
    static void set_Name(String name)
    {
        Donator.name=name;
    }

    static void set_Number(String number)
    {
        Donator.number=number;
    }

    static String get_Name()
    {
        return name;    
    }

    static String get_Number()
    {
        return number;
    }

    @Override //Override String toString method wste na ginei swsth ektypwsh twn donator
    public String toString()
    {
        return "Name: " +this.get_Name() + " ,Number: " +this.get_Number();
    }

    //Add offer sthn offersList
    static void add(Offers offer)
    {
        offersList.add(offer);
    }

    //Remove offer apo thn offersList
    static void remove(Offers offer)
    {
        offersList.remove(offer);
    }

    //Print twn stoixeiwn ths offersList
    static void monitor()
    {
        for (Offers offers : offersList)
        {
            System.out.println(offers);
        }
    }

    //Desmeysh tou offer kai add sthn currentDonations mesw ths add ths Organization
    static void commit()
    {
        System.out.println("Your donation has been accepted! Thank you!");
        RequestDonationList rdl=new RequestDonationList();
        Organization.add(rdl);
        RequestDonationList.reset();
    }

    //Diagrafh twn stoixeiwn ths offersList
    static void reset()
    {
        offersList.clear();
    }
}