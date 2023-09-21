package evanditaWiratamaPutraJBusER;

public class Voucher extends Serializable
{
    public String name;
    private boolean used;
    public double minimum;
    public double cut;
    public int code;
    public Type type;
    
    public Voucher (int id, String name, int code, Type type, double minimum, double cut)
    {
        super(id);
        this.name = name;
        this.code = code;
        this.type = type;
        this.minimum = minimum;
        this.cut = cut;
        this.used = false;
    }
    
    public boolean isUsed ()
    {
        return used;
    }
    
    public boolean canApply (Price price)
    {
        if (price.price > minimum && used == false)
        {
            return true;
        }
        return false;
    }
    
    public double apply (Price price)
    {
        used = true;
        if (cut < 0)
        {
            cut = 0;
        }
        
        if (type == Type.DISCOUNT)
        {
            if (cut > 100) 
            {
                cut = 100;
            }
            return (double)(100 - cut)/100 * price.price;
        }
        else if (type == Type.REBATE)
        {
            if (cut > price.price)
            {
                return 0;
            }
            return price.price - cut;
        }
        else return 0;
    }
    
}
