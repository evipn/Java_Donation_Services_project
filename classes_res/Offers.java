public class Offers extends RequestDonationList
{
    //Ylopoihsh olwn twn synarthsewn mesw ths klashs Donator

    static void add(Offers offer)
    {
        Donator.add(offer);
    }

    static void remove(Offers offer)
    {
        Donator.remove(offer);;
    }

    static void monitor()
    {
        Donator.monitor();
    }

    static void commit()
    {
        Donator.commit();
    }

    static void reset()
    {
        Donator.reset();
    }
}
