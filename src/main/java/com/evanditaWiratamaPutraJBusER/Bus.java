package com.evanditaWiratamaPutraJBusER;
import com.evanditaWiratamaPutraJBusER.dbjson.Serializable;
import java.util.*;
import java.text.*;
import java.sql.Timestamp;

/**
 * Kelas Bus merepresentasikan informasi tentang sebuah bus dalam sistem transportasi.
 * Setiap objek Bus memiliki kapasitas penumpang, fasilitas, nama, harga, stasiun keberangkatan,
 * stasiun kedatangan, jenis bus, dan daftar jadwal keberangkatan.
 *
 * @author Evandita
 */
public class Bus extends Serializable
{
    /**
     * Kapasitas penumpang bus.
     */
    public int capacity;
    /**
     * Daftar fasilitas yang tersedia di dalam bus.
     */
    public List<Facility> facilities;
    /**
     * Nama bus.
     */
    public String name;
    /**
     * Informasi harga tiket bus.
     */
    public Price price;
    /**
     * Stasiun keberangkatan bus.
     */
    public Station departure;
    /**
     * Stasiun kedatangan bus.
     */
    public Station arrival;
    /**
     * Jenis bus (BusType).
     */
    public BusType busType;
    /**
     * Daftar jadwal keberangkatan bus.
     */
    public List<Schedule> schedules;
    /**
     * ID akun pemilik bus.
     */
    public int accountId;


    /**
     * Membangun objek Bus dengan ID akun, nama, fasilitas, harga, kapasitas, jenis bus, stasiun keberangkatan,
     * dan stasiun kedatangan yang ditentukan.
     *
     * @param accountId  ID akun pemilik bus.
     * @param name       Nama bus.
     * @param facilities Daftar fasilitas yang tersedia di dalam bus.
     * @param price      Informasi harga tiket bus.
     * @param capacity   Kapasitas penumpang bus.
     * @param busType    Jenis bus (BusType).
     * @param departure  Stasiun keberangkatan bus.
     * @param arrival    Stasiun kedatangan bus.
     */
    public Bus (int accountId, String name, List<Facility> facilities, Price price, int capacity, BusType busType, Station departure, Station arrival)
    {
        super();
        this.accountId = accountId;
        this.capacity = capacity;
        this.facilities = facilities;
        this.name = name;
        this.price = price;
        this.departure = departure;
        this.arrival = arrival;
        this.busType = busType;
        schedules = new ArrayList<Schedule>();
        
    }


    /**
     * Representasi string dari objek Bus.
     *
     * @return String yang merepresentasikan objek Bus.
     */
    public String toString ()
    {
        return "\nClass: Bus\n" + "id: " + id + "\ncapacity: " + capacity + "\nfacility: " + facilities + "\nname: " + name + "\nprice: " + price + "\ndeparture: " + departure + "\narrival: " + arrival + "\nbusType: " + busType;
    }

    /**
     * Menambahkan jadwal keberangkatan baru ke dalam daftar jadwal bus.
     *
     * @param departureDate Waktu keberangkatan baru.
     */
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

}
