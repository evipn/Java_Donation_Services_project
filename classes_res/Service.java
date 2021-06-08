public class Service extends Entity
{
    static int category=2;
    static int counter;

    public Service(String name,String description,double quantity)
    {
        this.name=name;
        this.description=description;
        this.quantity=quantity;
        counter++;
    }

    //Epistrofh ths synolikhs posothtas twn Service
    static int get_quantity()
    {
        return counter;
    }

    //Epistrofh oti einai Service
    String getDetails()
    {
        return "Service";
    }

    //Epistrofh category=2
    public int getCategory()
    {
        return category;
    }
}