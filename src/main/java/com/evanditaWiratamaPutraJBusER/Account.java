package com.evanditaWiratamaPutraJBusER;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Account extends Serializable
{
    public String email;
    public String name;
    public String password;
    public static final String REGEX_PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";
    public static final String REGEX_EMAIL = "^[A-Za-z0-9]+[@]{1}[A-Za-z]+[.]+[A-Za-z.]*[A-Za-z]+$";

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
}
