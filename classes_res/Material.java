public class Material extends Entity
{
    static double level1=2;
    static double level2=5;
    static double level3=7;
    static int category=1;
    static int counter;

    //Material Constructor
    public Material(String name,String description,double quantity)
    {
        this.name=name;
        this.description=description;
        this.quantity=quantity;
        counter++;
    }

    //getter:Epistrofh ths synolikhs posothtas twn material
    static int get_quantity()
    {
        return counter;
    }

    //Epistrofh twn levels kai oti einai materials
    String getDetails()
    {

        if(Beneficiary.noPersons==1)
        {
            return level1 + "Material";
        }
        else if(Beneficiary.noPersons>=2 && Beneficiary.noPersons<=4)
        {
            return level2 + "Material";
        }
        else
        {
            return level3 + "Material";
        }
    }

    //Epistrofh category=1
    public int getCategory()
    {
        return category;
    }
}