package com.evanditaWiratamaPutraJBusER;
import java.util.ArrayList;

public class Validate
{
    public static ArrayList filter(Price[] list,int value, boolean less)
    {
        ArrayList<Double> arrlist = new ArrayList<Double>();
        for (Price i: list)
        {
            if (less == true && i.price <= value)
            {
                arrlist.add(i.price);
            }
            else if (less == false && i.price > value)
            {
                arrlist.add(i.price);
            }
        }
        
        return arrlist;
    }
}
