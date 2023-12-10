package com.evanditaWiratamaPutraJBusER;

/**
 * Kelas {@code Rating} digunakan untuk menghitung dan menyimpan rating serta statistik terkait.
 * @author Evandita
 */
public class Rating
{
    /**
     * Jumlah total rating yang telah dimasukkan.
     */
    private long count;
    /**
     * Jumlah total dari semua rating yang telah dimasukkan.
     */
    private long total;

    /**
     * Membangun instance {@code Rating} dengan nilai awal total dan count sama dengan 0.
     */
    public Rating()
    {
        this.total = 0;
        this.count = 0;
    }

    /**
     * Memasukkan rating baru ke dalam perhitungan.
     *
     * @param rating Rating yang akan dimasukkan.
     */
    public void insert (int rating)
    {
        total += rating;
        count += 1;
    }

    /**
     * Menghitung dan mengembalikan rata-rata dari semua rating yang telah dimasukkan.
     *
     * @return Rata-rata dari semua rating, atau 0 jika belum ada rating.
     */
    public double getAverage ()
    {
        if (count == 0)
        {
            return 0;
        }
        return ((double)total)/count;
    }

    /**
     * Mengembalikan jumlah total rating yang telah dimasukkan.
     *
     * @return Jumlah total rating.
     */
    public long getCount ()
    {
        return count;
    }

    /**
     * Mengembalikan jumlah total dari semua rating yang telah dimasukkan.
     *
     * @return Jumlah total rating.
     */
    public long getTotal ()
    {
        return total;
    }

    /**
     * Menghasilkan representasi string dari objek {@code Rating},
     * termasuk informasi jumlah total rating dan jumlah total.
     *
     * @return Representasi string dari objek {@code Rating}.
     */
    public String toString ()
    {
        return "\nClass: Rating\n" + "count: " + count + "\ntotal: " + total;
    }

}
