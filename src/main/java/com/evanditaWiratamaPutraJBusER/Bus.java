package com.evanditaWiratamaPutraJBusER;
import com.evanditaWiratamaPutraJBusER.dbjson.Serializable;
import java.util.*;
import java.text.*;
import java.sql.Timestamp;

public class Bus extends Serializable
{
    public int capacity;
    public Facility facility;
    public String name;
    public Price price;
    public Station departure;
    public Station arrival;
    public BusType busType;
    public City city;
    public List<Schedule> schedules;
    

    
    public Bus (String name, Facility facility, Price price, int capacity, BusType busType, City city, Station departure, Station arrival)
    {
        super();
        this.capacity = capacity;
        this.facility = facility;
        this.name = name;
        this.price = price;
        this.departure = departure;
        this.arrival = arrival;
        this.busType = busType;
        this.city = city;
        schedules = new ArrayList<Schedule>();
        
    }

    
    public String toString ()
    {
        return "\nClass: Bus\n" + "id: " + id + "\ncapacity: " + capacity + "\nfacility: " + facility + "\nname: " + name + "\nprice: " + price + "\ndeparture: " + departure + "\narrival: " + arrival + "\nbusType: " + busType + "\ncity: " + city;
    }
    
    public void addSchedule (Timestamp departureDate)
    {
        try{
            Predicate<Schedule> s = (val) -> val.departureSchedule.equals(departureDate);
            if (Algorithm.exists(this.schedules, s)){
                throw new RuntimeException("Hari Keberangkatan sudah ada");
            }
            else {
                schedules.add(new Schedule(departureDate, capacity));
            }
        }
        catch (RuntimeException e) {
            System.err.println(e.getMessage());
        }
    }
    
    /*
    public void printSchedule (Schedule schedule)
        throws ParseException
    {
        SimpleDateFormat SDFormat = new SimpleDateFormat("'Tanggal Keberangkatan:' MMMM dd, yyyy HH:mm:ss");
        System.out.println(SDFormat.format(schedule.departureSchedule.getTime()));
        System.out.println("Tanggal keberangkatan dana ketersediaan kursinya: ");
        int cnt = 0;
        for (Map.Entry<String, Boolean> seat: schedule.seatAvailability.entrySet())
        {
            if (cnt == 4)
            {
                cnt = 0;
                System.out.print("\n");
            }
            String key = seat.getKey();
            Boolean value = seat.getValue();
            System.out.print(key + ":" + value + "\t");
            cnt++;
        }
        System.out.print("\n");
    }
    */

}
