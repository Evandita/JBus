package com.evanditaWiratamaPutraJBusER;


/**
 * Kelas {@code Price} merepresentasikan harga suatu produk atau layanan,
 * termasuk informasi harga dasar dan potongan harga (rebate) jika ada.
 * @author Evandita
 */
public class Price
{

    /**
     * Potongan harga (rebate) yang diberikan pada produk atau layanan.
     */
    public double rebate;
    /**
     * Harga dasar dari produk atau layanan.
     */
    public double price;

    /**
     * Membangun instance {@code Price} dengan harga dasar dan rebate awal.
     *
     * @param price Harga dasar dari produk atau layanan.
     */
    public Price (double price)
    {
        this.price = price;
        this.rebate = 0;
    }

    /**
     * Membangun instance {@code Price} dengan harga dasar dan potongan harga (rebate) tertentu.
     *
     * @param price  Harga dasar dari produk atau layanan.
     * @param rebate Potongan harga yang diberikan pada produk atau layanan.
     */
    public Price (double price, double rebate)
    {
        this.price = price;
        this.rebate = rebate;
    }

    /**
     * Menghasilkan representasi string dari objek {@code Price},
     * termasuk informasi harga dasar dan potongan harga (rebate).
     *
     * @return Representasi string dari objek {@code Price}.
     */
    public String toString ()
    {
        return "\nClass: Price\n" + "price: " + price + "\nrebate: " + rebate;
    }
}
