package evanditaWiratamaPutraJBusER;

public class Station extends Serializable
{

    public City city;
    public String stationName;
    public String address;

    public Station(int id, String stationName, City city, String address)
    {
        super(id);
        this.stationName = stationName;
        this.city = city;
        this.address = address;
    }


    public String toString()
    {
        return "\nClass: Station\n" + "id: " + id + "\nstationName: " + stationName + "\ncity: " + city + "\naddress: " + address;

    }
}
