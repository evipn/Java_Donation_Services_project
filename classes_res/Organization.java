import java.util.ArrayList;

public class Organization
{
    static String name;
    Admin admin;
    static ArrayList<Entity> entityList=new ArrayList<>();
    static ArrayList<Donator> donatorList=new ArrayList<>();
    static ArrayList<Beneficiary> beneficiaryList=new ArrayList<>();
    static ArrayList<RequestDonationList> currentDonations=new ArrayList<>();

    //Admin Constructor
    public Organization(String name,Admin admin)
    {
        this.name=name;
        this.admin=admin;
    }

    //setters-getters
    void setAdmin(Admin admin)
    {
        this.admin=admin;
    }

    Admin getAdmin()
    {
        return admin;
    }

    //add entity sth lista entityList
    void addEntity(Entity entity)
    {
        if(entityList.contains(entity))
        {
            System.out.println("This entity already exists!");
            throw new IllegalArgumentException();
        }
        else
        {
            entityList.add(entity);
        }
    }

    //Diagrafh entity apo th lista entityList
    void removeEntity(Entity entity)
    {
        entityList.remove(entity);
    }

    //Prosthiki donator sth lista donatorList efoson den yparxei hdh
    static void insertDonator(Donator donator)
    {
        if(donatorList.contains(donator))
        {
            System.out.println("Donator already exists!");
            throw new IllegalArgumentException();
        }
        else
        {
            donatorList.add(donator);
        }
    }

    //Diagrafh donator apo th lista donatoList
    static void removeDonator(Donator donator)
    {
        donatorList.remove(donator);
    }

    //Prothiki sth lista beneficiaryList efoson den yparxei hdh
    static void insertBeneficiary(Beneficiary beneficiary)
    {
        if(beneficiaryList.contains(beneficiary))
        {
            System.out.println("Beneficiary already exists!");
            throw new IllegalArgumentException();
        }
        else
        {
            beneficiaryList.add(beneficiary);
        }
    }

    //Diagrafh beneficiary apo th lista beneficiaryList
    static void removeBeneficiary(Beneficiary beneficiary)
    {
        beneficiaryList.remove(beneficiary);
    }

    //Ektypwsh Material h Service analoga me th timh tou category
    static void listEntities(int category)
    {
        int counter=1;

        if(category==1)
        {
            for(Entity i: entityList)
            {
                if (i.getCategory()== 1)
                {
                    System.out.println(counter + ". "+ i.getEntityInfo());
                    counter++;
                }
            }
        }
        else if (category==2)
        {
            for(Entity i: entityList)
            {
                if(i.getCategory()==2)
                {
                    System.out.println(counter + ". " + i.getEntityInfo());
                    counter++;
                }
            }
        }
    }

    //Print twn beneficiary ths beneficiaryList
    static void listBeneficiaries()
    {
        int counter=1;
        for (Beneficiary beneficiary : beneficiaryList)
        {
            System.out.println(counter + ". " + beneficiary);
            counter++;
        }
    }

    //Print twn Donator ths donatorList
    static void listDonators()
    {
        int counter=1;
        for (Donator donator : donatorList)
        {
            System.out.println(counter + ". " + donator);
            counter++;
        }
    }

    //Add sth currentDonations
    static void add(RequestDonationList rdl)
    {
        currentDonations.add(rdl);
    }

    //Diagrafh twn stoixeiwn ths currentDonations
    static void reset()
    {
        currentDonations.clear();
    }

    //Allagh ths posothtas ths currentDonations
    static void modifyCurrent(RequestDonationList rdl)
    {
        RequestDonation.quantity=RequestDonation.quantity+rdl.getQuantity();
    }

}
