public class Requests extends RequestDonationList
{
    static Entity entity;
    static double quantity;

    //Requests Constructor
    public Requests(Entity entity,double quantity)
    {
        this.entity=entity;
        this.quantity=quantity;
    }

    //Efoson ginoun oi elegxoi ginetai add tou request sthn RequestsList alliws ektypwnetai katallhlo mhnuma kai dhmiourgeitai eksairesh
    static void add(Requests req)
    {

        if((req.quantity<=req.entity.quantity && req.quantity>0) && (req.validRequestDonation()==true))
        {
            Beneficiary.add(req);
        }
        else
        {
            System.out.println("We are sorry but either the quantity that you typed is not available or your request is not valid! ");
            throw new IllegalArgumentException();
        }
    }

    //Efoson ginoun oi elegxoi ginetai allagh ths posothtas tou request alliws ektypwnetai katallhlo mhnuma kai dhmiourgeitai eksairesh
     static void modify(double new_quantity)
    {
        if((Requests.quantity<=Requests.entity.quantity && Requests.quantity>0) && (Requests.validRequestDonation()==true))
        {
            quantity=quantity+new_quantity;
        }
        else
        {
            System.out.println("We are sorry but either the quantity that you typed is not available or your request is not valid! ");
            throw new IllegalArgumentException();
        }
    }

    //Epistrefei true an to request tou beneficiary einai egkyro enw epitrefei false an isxuei kapoios apo tous elegxous
    //Dhladh an den dikaieitai thn posothta pou zhtaei
    static boolean validRequestDonation()
    {
        boolean b=true;
        RequestDonationList rdl=new RequestDonationList();

        if(Beneficiary.get_NoPersons()==1)
        {
            if(Requests.quantity+rdl.getQuantity() >= Material.level1)
            {
                b=false;
            }
        }
        else if(Beneficiary.noPersons>=2 && Beneficiary.noPersons<=4)
        {
            if(Requests.quantity+rdl.getQuantity() >= Material.level2)
            {
                b=false;
            }
        }
        else if(Beneficiary.noPersons>=5)
        {
            if (Requests.quantity + rdl.getQuantity() >= Material.level3)
            {
                b=false;
            }
        }
        return b;
    }

    //Ylopoihhss ths commit sthn Beneficiary
    void commit()
    {
        Beneficiary.commit();
    }

    //Diagrafh request apo thn requestsList
    void remove(Requests req)
    {
        Beneficiary.requestsList.remove(req);
    }

    //Diagrafh twn stoixeiwn ths requestsList
    static void reset()
    {
        Beneficiary.reset();
    }

} 