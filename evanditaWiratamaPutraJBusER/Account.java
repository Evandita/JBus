package evanditaWiratamaPutraJBusER;


public class Account extends Serializable implements FileParser
{
    public String email;
    public String name;
    public String password;
    
    public Object write ()
    {
        return 0;
    }
    public boolean read (String content)
    {
        return true;
    }
        
    public Account(int id, String name, String email, String password)
    {
        super(id);
        this.name = name;
        this.email = email;
        this.password = password;
    }

    
    public String toString ()
    {
        return "\nClass: Account\n" + "id: " + id + "\nname: " + name + "\nemail: " + email + "\npassword: " + password;
    }
}
