package com.evanditaWiratamaPutraJBusER;
import com.evanditaWiratamaPutraJBusER.dbjson.Serializable;
import java.sql.Timestamp;

/**
 * Kelas {@code Invoice} merupakan representasi pembayaran terkait penyewaan bus,
 * yang merupakan turunan dari kelas {@code Serializable}.
 * @author Evandita
 */
public class Invoice extends Serializable
{
    /**
     * Waktu pembuatan invoice.
     */
    public Timestamp time;
    /**
     * ID pembeli yang terkait dengan invoice.
     */
    public int buyerId;
    /**
     * ID penyewa yang terkait dengan invoice.
     */
    public int renterId;
    /**
     * Rating bus yang terkait dengan invoice.
     */
    public BusRating rating;
    /**
     * Status pembayaran yang terkait dengan invoice.
     */
    public PaymentStatus status;

    /**
     * Enumerasi untuk rating bus pada invoice.
     */
    public enum BusRating
    {
        NONE,
        NEUTRAL,
        GOOD,
        BAD;
    }

    /**
     * Enumerasi untuk status pembayaran pada invoice.
     */
    public enum PaymentStatus
    {
        FAILED,
        WAITING,
        SUCCESS;
    }


    /**
     * Membangun instance {@code Invoice} dengan ID pembeli, ID penyewa,
     * waktu pembuatan, rating awal sebagai NONE, dan status pembayaran sebagai WAITING.
     *
     * @param buyerId  ID pembeli.
     * @param renterId ID penyewa.
     */
    protected Invoice(int buyerId, int renterId)
    {
        super();
        this.buyerId = buyerId;
        this.renterId = renterId;
        this.time = new Timestamp(System.currentTimeMillis());
        this.rating = BusRating.NONE;
        this.status = PaymentStatus.WAITING;
        
    }

    /**
     * Membangun instance {@code Invoice} dengan objek {@code Account} pembeli, objek {@code Renter} penyewa,
     * waktu pembuatan, rating awal sebagai NONE, dan status pembayaran sebagai WAITING.
     *
     * @param buyer Objek {@code Account} pembeli.
     * @param renter Objek {@code Renter} penyewa.
     */
    public Invoice (Account buyer, Renter renter)
    {
        super();
        this.buyerId = buyer.id;
        this.renterId = renter.id;
        this.time = new Timestamp(System.currentTimeMillis());
        this.rating = BusRating.NONE;
        this.status = PaymentStatus.WAITING;
    }

}
