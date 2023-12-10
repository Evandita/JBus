package com.evanditaWiratamaPutraJBusER;
import com.evanditaWiratamaPutraJBusER.dbjson.Serializable;
/**
 * Kelas {@code Station} merepresentasikan stasiun dalam sistem, termasuk informasi seperti nama stasiun,
 * kota tempat stasiun berada, dan alamat stasiun.
 * @author Evandita
 */
public class Station extends Serializable
{

    /**
     * Objek City yang mewakili kota tempat stasiun berada.
     */
    public City city;
    /**
     * Nama dari stasiun.
     */
    public String stationName;
    /**
     * Alamat dari stasiun.
     */
    public String address;

    /**
     * Membangun instance {@code Station} dengan parameter yang telah ditentukan.
     *
     * @param stationName Nama dari stasiun.
     * @param city        Objek City yang mewakili kota tempat stasiun berada.
     * @param address     Alamat dari stasiun.
     */
    public Station(String stationName, City city, String address)
    {
        super();
        this.stationName = stationName;
        this.city = city;
        this.address = address;
    }


    /**
     * Menghasilkan representasi string dari objek {@code Station}, termasuk informasi id, nama stasiun, kota, dan alamat.
     *
     * @return Representasi string dari objek {@code Station}.
     */
    public String toString()
    {
        return "\nClass: Station\n" + "id: " + id + "\nstationName: " + stationName + "\ncity: " + city + "\naddress: " + address;

    }
}
