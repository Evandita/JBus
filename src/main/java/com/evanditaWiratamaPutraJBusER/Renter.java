package com.evanditaWiratamaPutraJBusER;
import com.evanditaWiratamaPutraJBusER.dbjson.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Renter extends Serializable
{
    public String address;
    public String companyName;
    public String phoneNumber;
    private final String REGEX_PHONE = "^[0-9]{9,12}$";
    private final String REGEX_NAME = "^[A-Z][A-Za-z0-9_]{4,20}$";

    
    public Renter (String companyName)
    {
        super();
        this.companyName = companyName;
        this.phoneNumber = "0";
        this.address = "";
    }
    
    public Renter (String companyName, String phoneNumber)
    {
        super();
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
        this.address = "";
    }
    
    public Renter (String companyName, String phoneNumber, String address)
    {
        super();
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public boolean validate ()

    {
        Pattern patternPhone = Pattern.compile(REGEX_PHONE);
        Pattern patternName = Pattern.compile(REGEX_NAME);
        Matcher matcherPhone = patternPhone.matcher(phoneNumber);
        Matcher matcherName = patternName.matcher(companyName);

        if (matcherPhone.find() && matcherName.find())
        {
            return true;
        }
        return false;


    }

}
