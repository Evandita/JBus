package com.evanditaWiratamaPutraJBusER;
import java.sql.Timestamp;
import java.text.*;
import java.util.*;

/**
 * Kelas {@code Payment} merupakan turunan dari kelas {@code Invoice},
 * digunakan untuk merepresentasikan pembayaran terkait penyewaan bus.
 * @author Evandita
 */
public class Payment extends Invoice
{
    /**
     * ID dari bus yang disewa.
     */
    private int busId;
    /**
     * Waktu keberangkatan bus.
     */
    public Timestamp departureDate;
    /**
     * Daftar kursi yang dipesan pada bus.
     */
    public List<String> busSeats;


    /**
     * Membangun instance {@code Payment} dengan ID pembeli, ID penyewa, ID bus, daftar kursi, dan waktu keberangkatan tertentu.
     *
     * @param buyerId       ID pembeli.
     * @param renterId      ID penyewa.
     * @param busId         ID bus yang disewa.
     * @param busSeats      Daftar kursi yang dipesan.
     * @param departureDate Waktu keberangkatan bus.
     */
    public Payment (int buyerId, int renterId, int busId, List<String> busSeats, Timestamp departureDate)
    {
        super(buyerId, renterId);
        this.busId = busId;
        this.departureDate = departureDate;
        this.busSeats = busSeats;
    }


    /**
     * Membangun instance {@code Payment} dengan objek {@code Account} pembeli, objek {@code Renter} penyewa,
     * ID bus, daftar kursi, dan waktu keberangkatan tertentu.
     *
     * @param buyer         Objek {@code Account} pembeli.
     * @param renter        Objek {@code Renter} penyewa.
     * @param busId         ID bus yang disewa.
     * @param busSeats      Daftar kursi yang dipesan.
     * @param departureDate Waktu keberangkatan bus.
     */
    public Payment (Account buyer, Renter renter, int busId, List<String> busSeats, Timestamp departureDate)
    {
        super(buyer, renter);
        this.busId = busId;
        this.departureDate = departureDate;
        this.busSeats = busSeats;
    }

    /**
     * Mengembalikan ID bus yang disewa.
     *
     * @return ID bus.
     */
    public int getBusId ()
    {
        return busId;
    }

    /**
     * Mencari jadwal yang tersedia untuk pemesanan kursi pada waktu keberangkatan tertentu dan satu kursi.
     *
     * @param departureSchedule Waktu keberangkatan.
     * @param seat              Nomor kursi yang akan dipesan.
     * @param bus               Objek bus yang akan dicek.
     * @return Jadwal yang tersedia, atau {@code null} jika tidak ditemukan.
     * @throws ParseException Jika terjadi kesalahan pada parsing waktu.
     */
    public static Schedule availableSchedule (Timestamp departureSchedule, String seat, Bus bus) throws ParseException {
        Predicate<Schedule> s = (val) -> val.departureSchedule.equals(departureSchedule) && val.isSeatAvailable(seat);
        return Algorithm.find(bus.schedules, s);
    }

    /**
     * Mencari jadwal yang tersedia untuk pemesanan kursi pada waktu keberangkatan tertentu dan beberapa kursi.
     *
     * @param departureSchedule Waktu keberangkatan.
     * @param seat              Daftar nomor kursi yang akan dipesan.
     * @param bus               Objek bus yang akan dicek.
     * @return Jadwal yang tersedia, atau {@code null} jika tidak ditemukan.
     */
    public static Schedule availableSchedule (Timestamp departureSchedule, List<String> seat, Bus bus) {
        Predicate<Schedule> s = (val) -> val.departureSchedule.equals(departureSchedule) && val.isSeatAvailable(seat);
        return Algorithm.find(bus.schedules, s);
    }

    /**
     * Melakukan pemesanan kursi pada waktu keberangkatan tertentu dan satu kursi.
     *
     * @param departureSchedule Waktu keberangkatan.
     * @param seat              Nomor kursi yang akan dipesan.
     * @param bus               Objek bus yang akan dipesan.
     * @return {@code true} jika pemesanan berhasil, {@code false} jika gagal.
     * @throws ParseException Jika terjadi kesalahan pada parsing waktu.
     */
    public static boolean makeBooking (Timestamp departureSchedule, String seat, Bus bus) throws ParseException {
        Schedule s = availableSchedule(departureSchedule, seat, bus);

        if (s != null) {

            s.bookSeat(seat);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Melakukan pemesanan kursi pada waktu keberangkatan tertentu dan beberapa kursi.
     *
     * @param departureSchedule Waktu keberangkatan.
     * @param seat              Daftar nomor kursi yang akan dipesan.
     * @param bus               Objek bus yang akan dipesan.
     * @return {@code true} jika pemesanan berhasil, {@code false} jika gagal.
     */
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
    }

    /**
     * Membatalkan pemesanan kursi pada waktu keberangkatan tertentu dan beberapa kursi.
     *
     * @param departureSchedule Waktu keberangkatan.
     * @param seat              Daftar nomor kursi yang akan dibatalkan.
     * @param bus               Objek bus yang akan dibatalkan.
     * @return {@code true} jika pembatalan berhasil, {@code false} jika gagal.
     */
    public static boolean cancelBooking (Timestamp departureSchedule, List<String> seat, Bus bus) {
        Predicate<Schedule> p = (val) -> val.departureSchedule.equals(departureSchedule) && val.isSeatNotAvailable(seat);
        Schedule s = Algorithm.find(bus.schedules, p);

        if (s != null) {
            s.unBookSeat(seat);
            return true;
        }
        return false;
    }
}