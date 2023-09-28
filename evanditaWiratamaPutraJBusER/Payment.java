package evanditaWiratamaPutraJBusER;

public class Payment extends Invoice
{
    private int busId;
    public String departureDate;
    public String busSeat;
    
    
    public Payment (int id, int buyerId, int renterId, String time, int busId, String departureDate, String busSeat)
    {
        super(id, buyerId, renterId);
        this.busId = busId;
        this.departureDate = departureDate;
        this.busSeat = busSeat;
    }
    
    
    public Payment (int id, Account buyer, Renter renter, String time, int busId, String departureDate, String busSeat)
    {
        super(id, buyer, renter);
        this.busId = busId;
        this.departureDate = departureDate;
        this.busSeat = busSeat;
    }
    
    public String toString ()
    {
        return "\nClass: Payment\n" + "id: " + id + "\nbuyerId: " + buyerId + "\nrenterId: " + renterId + "\ntime: " + time + "\nbusId: " + busId + "\ndepartureDate: " + departureDate + "\nbusSeat: " + busSeat;
    }
    
    public int getBusId ()
    {
        return busId;
    }
    
}

