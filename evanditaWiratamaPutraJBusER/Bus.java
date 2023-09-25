package evanditaWiratamaPutraJBusER;


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

    public Bus (int id, String name, Facility facility, Price price, int capacity, BusType busType, City city, Station departure, Station arrival)
    {
        super(id);
        this.capacity = capacity;
        this.facility = facility;
        this.name = name;
        this.price = price;
        this.departure = departure;
        this.arrival = arrival;
        this.busType = busType;
        this.city = city;
    }
    
    public String toString ()
    {
        return "\nClass: Bus\n" + "id: " + id + "\ncapacity: " + capacity + "\nfacility: " + facility + "\nname: " + name + "\nprice: " + price + "\ndeparture: " + departure + "\narrival: " + arrival + "\nbusType: " + busType + "\ncity: " + city;
    }

}
