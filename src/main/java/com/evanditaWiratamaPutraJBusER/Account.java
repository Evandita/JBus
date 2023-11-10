package com.evanditaWiratamaPutraJBusER;
import com.evanditaWiratamaPutraJBusER.dbjson.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Account extends Serializable
{
    public String email;
    public String name;
    public String password;

    public Renter company;

    public double balance;
    public static final String REGEX_PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[a-zA-Z0-9]{8,}$";
    public static final String REGEX_EMAIL = "^[a-zA-Z0-9]+@[a-zA-Z_]+?\\.[a-zA-Z.]+[a-zA-Z]+$";

    public Account(String name, String email, String password)
    {
        super();
        this.name = name;
        this.email = email;
        this.password = password;
        this.balance = 0;
        this.company = null;
    }

    
    public String toString ()
    {
        return "\nClass: Account\n" + "id: " + id + "\nname: " + name + "\nemail: " + email + "\npassword: " + password;
    }

    public boolean validate ()

    {
        Pattern patternPassword = Pattern.compile(REGEX_PASSWORD);
        Pattern patternEmail = Pattern.compile(REGEX_EMAIL);
        Matcher matcherPassword = patternPassword.matcher(password);
        Matcher matcherEmail = patternEmail.matcher(email);

        if (matcherPassword.find() && matcherEmail.find())
        {
            return true;
        }
        return false;
    }

    public boolean topUp(double amount) {
        if (amount < 0) return false;
        balance += amount;
        return true;
    }
}
