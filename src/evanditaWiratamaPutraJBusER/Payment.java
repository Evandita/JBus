package evanditaWiratamaPutraJBusER;
import java.sql.Timestamp;
import java.text.*;
import java.util.*;

public class Payment extends Invoice
{
    private int busId;
    public Timestamp departureDate;
    public String busSeat;


    public Payment (int buyerId, int renterId, int busId, String busSeat, Timestamp departureDate)
    {
        super(buyerId, renterId);
        this.busId = busId;
        this.departureDate = departureDate;
        this.busSeat = busSeat;
    }


    public Payment (Account buyer, Renter renter, int busId, String busSeat, Timestamp departureDate)
    {
        super(buyer, renter);
        this.busId = busId;
        this.departureDate = departureDate;
        this.busSeat = busSeat;
    }
    /*
    public String toString ()
    {
        return "\nClass: Payment\n" + "id: " + id + "\nbuyerId: " + buyerId + "\nrenterId: " + renterId + "\ntime: " + time + "\nbusId: " + busId + "\ndepartureDate: " + departureDate + "\nbusSeat: " + busSeat;
    }
    */
    public int getBusId ()
    {
        return busId;
    }

    public String getDepartureInfo ()
            throws ParseException
    {
        SimpleDateFormat SDFormat = new SimpleDateFormat("'Departure Date:' MMMM dd, yyyy HH:mm:ss");
        return SDFormat.format(departureDate.getTime()) + "\nPayment Id: " + id + "\nBuyer Id: " + buyerId +  "\nRenter Id: " + renterId + "\nBus Id: " + busId + "\nBus Seat: " + busSeat;
    }

    public String getTime ()
            throws ParseException
    {
        SimpleDateFormat SDFormat = new SimpleDateFormat("'Time:' MMMM dd, yyyy HH:mm:ss");
        return SDFormat.format(time.getTime());
    }
    /*
    public static boolean isAvailable (Timestamp departureSchedule, String seat, Bus bus)
    {
        for (int i = 0;i < bus.schedules.size();i++)
        {

            if (bus.schedules.get(i).departureSchedule.equals(departureSchedule))
            {

                if (bus.schedules.get(i).isSeatAvailable(seat) == true)
                {
                    //System.out.println(bus.schedules.get(i));
                    return true;
                }
                //
                for (Map.Entry<String, Boolean> j: i.seatAvailability.entrySet())
                {
                    String key = j.getKey();
                    Boolean value = j.getValue();
                    if (key == seat)
                    {
                        return true;
                    }
                }
                //
            }
        }
        return false;
    }
    */

    public static Schedule availableSchedule (Timestamp departureSchedule, String seat, Bus bus) throws ParseException {
        Predicate<Schedule> s = (val) -> val.departureSchedule.equals(departureSchedule) && val.isSeatAvailable(seat);
        return Algorithm.find(bus.schedules, s);
    }

    public static Schedule availableSchedule (Timestamp departureSchedule, List<String> seat, Bus bus) {
        Predicate<Schedule> s = (val) -> val.departureSchedule.equals(departureSchedule) && val.isSeatAvailable(seat);
        return Algorithm.find(bus.schedules, s);
    }

    public static boolean makeBooking (Timestamp departureSchedule, String seat, Bus bus) throws ParseException {
        Schedule s = availableSchedule(departureSchedule, seat, bus);

        if (s != null) {

            s.bookSeat(seat);
            return true;
        }
        else {
            return false;
        }
        /*
        Predicate<Schedule> sc = (val) -> val.departureSchedule.equals(departureSchedule);
        Predicate<Schedule> se = (val) -> val.isSeatAvailable(seat);
        //if (isAvailable(departureSchedule, seat, bus))
        if (Algorithm.exists(bus.schedules, sc) && Algorithm.exists(bus.schedules, se))
        {
            for (int i = 0;i < bus.schedules.size();i++)
            {
                if (bus.schedules.get(i).departureSchedule.equals(departureSchedule))
                {
                    //System.out.println(bus.schedules.get(i) +"2");
                    Schedule tmp = bus.schedules.get(i);
                    tmp.bookSeat(seat);
                    bus.schedules.set(i, tmp);
                    return true;
                }
            }
        }
        return false;

         */
    }

    public static boolean makeBooking (Timestamp departureSchedule, List<String> seat, Bus bus)
    {
        Schedule s = availableSchedule(departureSchedule, seat, bus);
        if (s != null) {
            s.bookSeat(seat);
            return true;
        }
        else {
            return false;
        }

        /*
        Predicate<Schedule> sc = (val) -> val.departureSchedule.equals(departureSchedule);
        Predicate<Schedule> se = (val) -> val.isSeatAvailable(seat);
        //if (isAvailable(departureSchedule, seat, bus))
        if (Algorithm.exists(bus.schedules, sc) && Algorithm.exists(bus.schedules, se))
        {

            for (int i = 0;i < bus.schedules.size();i++)
            {
                if (bus.schedules.get(i).departureSchedule.equals(departureSchedule))
                {
                    //System.out.println(bus.schedules.get(i) +"2");
                    Schedule tmp = bus.schedules.get(i);
                    tmp.bookSeat(seat);
                    bus.schedules.set(i, tmp);
                    return true;
                }
            }
        }
        return false;
        */

    }
}