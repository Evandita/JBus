package evanditaWiratamaPutraJBusER;


public class Account extends Serializable
{
    public String email;
    public String name;
    public String password;

    public Account(String name, String email, String password)
    {
        super();
        this.name = name;
        this.email = email;
        this.password = password;
    }

    
    public String toString ()
    {
        return "\nClass: Account\n" + "id: " + id + "\nname: " + name + "\nemail: " + email + "\npassword: " + password;
    }
}
