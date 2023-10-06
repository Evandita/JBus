package evanditaWiratamaPutraJBusER;


public class Renter extends Serializable
{
    public String address;
    public String companyName;
    public int phoneNumber;
    
    public Renter (String companyName)
    {
        super();
        this.companyName = companyName;
        this.phoneNumber = 0;
        this.address = "";
    }
    
    public Renter (String companyName, String address)
    {
        super();
        this.companyName = companyName;
        this.phoneNumber = 0;
        this.address = address;
    }
    
    public Renter (String companyName, int phoneNumber)
    {
        super();
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
        this.address = "";
    }
    
    public Renter (String companyName, int phoneNumber, String address)
    {
        super();
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
    
}
