package com.evanditaWiratamaPutraJBusER;
import java.sql.Timestamp;
import java.text.ParseException;

/**
 * Kelas BookingThread merupakan sebuah thread yang digunakan untuk melakukan proses pemesanan (booking) tiket bus.
 * Setiap thread ini memiliki informasi tentang bus yang akan di-booking, waktu keberangkatan, dan nama thread.
 * Thread ini akan mencoba melakukan booking tiket pada waktu yang ditentukan.
 * @author Evandita
 */
public class BookingThread extends Thread{
    /**
     * Bus yang akan di-booking.
     */
    private Bus bus;
    /**
     * Waktu keberangkatan untuk booking.
     */
    private Timestamp timestamp ;
    /**
     * Membangun objek BookingThread dengan nama thread, informasi bus, dan waktu keberangkatan yang ditentukan.
     *
     * @param name      Nama thread.
     * @param bus       Bus yang akan di-booking.
     * @param timestamp Waktu keberangkatan untuk booking.
     */
    public BookingThread(String name, Bus bus, Timestamp timestamp){
        super(name);
        this.bus = bus;
        this.timestamp = timestamp;
        this.start();
    }
    /**
     * Menjalankan thread untuk melakukan proses booking tiket bus.
     */
    public void run(){
        System.out.println(this.getName() + " ID : " + Thread.currentThread().getId() + " is running");
        synchronized (bus) {
            boolean booking = false;
            try {
                booking = Payment.makeBooking(timestamp, "ER01", bus);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            if(booking){
                System.out.println(this.getName() + " Berhasil Melakukan Booking");
            }
            else{
                System.out.println(this.getName() + " Gagal Melakukan Booking");
            }
        }
    }
}

