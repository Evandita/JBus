package evanditaWiratamaPutraJBusER;
import java.util.Calendar;
import java.text.*;

public class Payment extends Invoice
{
    private int busId;
    public Calendar departureDate;
    public String busSeat;
    
    
    public Payment (int id, int buyerId, int renterId, int busId, String busSeat)
    {
        super(id, buyerId, renterId);
        this.busId = busId;
        this.departureDate = Calendar.getInstance();
        departureDate.add(Calendar.DAY_OF_MONTH, 2);
        this.busSeat = busSeat;
    }
    
    
    public Payment (int id, Account buyer, Renter renter, int busId, String busSeat)
    {
        super(id, buyer, renter);
        this.busId = busId;
        this.departureDate = Calendar.getInstance();
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
}

