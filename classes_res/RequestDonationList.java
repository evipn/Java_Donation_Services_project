import java.util.ArrayList;
public class RequestDonationList
{
    static ArrayList<RequestDonation> rdEntities=new ArrayList<>();

    //getter:Epistrofh toy id tou RequestDonation
    static int get(RequestDonation rd)
    {
        return rd.entity.id;
    }

    //getter:Epistrofh posothtas tou RequestDonation
    static double getQuantity()
    {
        return RequestDonation.quantity;
    }

    //Add RequestDonation sth lista rdEntities efoson den yparxei hdh to entity enw an yparxei hdh tote ginetai metabolh ths posothtas tou
    //An den yparxei sthn entityList toy organismou tote dhmioyrgeitai eksairesh
    static void add(RequestDonation rd)
    {
        if(rdEntities.contains(rd.entity))
        {
            rd.quantity++;
        }
        else if(! rdEntities.contains(rd.entity))
        {
            rdEntities.add(rd);
        }

        else
        {
            if(! Organization.entityList.contains(rd.entity))
            {
                System.out.println("This entity does not exist!");
                throw new IllegalArgumentException();

            }
        }
    }

    //Diagrafh RequestDonation apo thn rdEntities
    static void remove(RequestDonation rd)
    {
        rdEntities.remove(rd);
    }

    //Allagh posothtas RequestDonation
    static void modify(double new_quantity)
    {
        RequestDonation.quantity=RequestDonation.quantity+new_quantity;
    }

    //Print twn stoixeiwn ths rdEntities
    static void monitor()
    {
        int counter=1;

        for (RequestDonation rdEntity : rdEntities)
        {
            System.out.println(counter + ". " + rdEntity);
            counter++;
        }
    }

    //Diagrafh twn stoixeiwn ths rdEntities
    static void reset()
        {
        rdEntities.clear();
    }

}