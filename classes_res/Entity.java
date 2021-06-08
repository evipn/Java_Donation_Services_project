abstract class Entity
{
    String name;
    String description;
    static int id;
    double quantity;

    //getter:Epistrofh twn plhroforiwn tou entity
    String getEntityInfo()
    {
        return "Name: " + name + " ,description: " + description + " ,id: " + id++;
    }

    //getter:Epistrofh toy onomatos tou Entity
    String getName()
    {
        return name;
    }

    //Dhmiourgia abstract method pou ylopoeitai apo tis klaseis Service kai Material
    abstract String getDetails();

    @Override
    public String toString()
    {
        return getEntityInfo();
    }

    abstract int getCategory();//getter:Dhmiourgia abstract method pou ylopoeitai apo tis klaseis Service kai Material me skopo thn epistrofh tou category
}