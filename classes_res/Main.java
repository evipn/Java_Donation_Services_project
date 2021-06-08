public class Main
{
    public static void main(String[] args)
    {
        //Dhmiourgia antikeimenwn (1 admin, 1 organization me onoma org, 2 beneficiaries, 1 donator, 3 materials, 3 services)

        Admin admin=new Admin("Evi" ,"666");
        admin.set_Name("Evi");
        admin.set_Number("666");

        Organization org=new Organization("AE",admin);
        org.setAdmin(admin);

        Beneficiary ben1=new Beneficiary("John" ,"679" ,3);
        ben1.set_Name("John");
        ben1.set_Number("679");
        org.insertBeneficiary(ben1);

        Beneficiary ben2=new Beneficiary("Pantelhs" ,"001" ,2);
        ben2.set_Name("Pantelhs");
        ben2.set_Number("001");
        org.insertBeneficiary(ben2);

        Donator d1=new Donator("Maria" ,"002");
        d1.set_Name("Maria");
        d1.set_Number("002");
        org.insertDonator(d1);

        Entity rice=new Material("rice","500g" ,5);
        org.addEntity(rice);
        Entity milk=new Material("milk","1L",5);
        org.addEntity(milk);
        Entity sugar=new Material("sugar","500g",5);
        org.addEntity(sugar);

        Entity ms=new Service("Medical Support","Supplies for health",10);
        org.addEntity(ms);
        Entity ns=new Service("Nursery Support","Support for your kids",10);
        org.addEntity(ns);
        Entity bs=new Service("Babysitting","Care of your kids!",10);
        org.addEntity(bs);


        //Metavash tou xrhsth sto arxiko menu

        Menu.search();
    }
}