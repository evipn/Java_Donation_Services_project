import java.util.ArrayList;

public class Admin extends User{
    boolean isAdmin=true;
    static ArrayList<String> admin_phones=new ArrayList<>();

    static String name;
    static String phone;

    //Admin constructor
    public Admin(String name, String phone)
    {
        super(name, phone);
        admin_phones.add(phone); //add phone sthn admin_phones
    }

    //setter-getters

    void set_Name(String name)
    {
        this.name=name;
    }

    void set_Number(String phone)
    {
        this.phone=phone;
    }

    static String get_Name()
    {
        return name;
    }

    static String get_Number()
    {
        return phone;
    }
}
