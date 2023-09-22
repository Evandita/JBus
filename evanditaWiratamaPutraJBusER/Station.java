package evanditaWiratamaPutraJBusER;

public class Station extends Serializable
{

    public City city;
    public String stationName;


    public Station(int id, String stationName, City city)
    {
        super(id);
        this.stationName = stationName;
        this.city = city;
    }


    public String print()
    {
        return "id: " + id + "\nstationName: " + stationName + "\ncity: " + city;

    }
}
