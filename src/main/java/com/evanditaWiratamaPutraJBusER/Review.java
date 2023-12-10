package com.evanditaWiratamaPutraJBusER;
import com.evanditaWiratamaPutraJBusER.dbjson.Serializable;

/**
 * Kelas {@code Review} merepresentasikan ulasan atau review terkait dengan suatu entitas.
 * @author Evandita
 */
public class Review extends Serializable
{
    /**
     * Tanggal ulasan.
     */
    public String date;
    /**
     * Deskripsi atau isi dari ulasan.
     */
    public String desc;

    /**
     * Membangun instance {@code Review} dengan parameter yang telah ditentukan.
     *
     * @param date Tanggal ulasan.
     * @param desc Deskripsi atau isi dari ulasan.
     */
    public Review (String date, String desc)
    {
        super();
        this.date = date;
        this.desc = desc;
    }

    /**
     * Menghasilkan representasi string dari objek {@code Review},
     * termasuk informasi id, tanggal ulasan, dan deskripsi.
     *
     * @return Representasi string dari objek {@code Review}.
     */
    public String toString ()
    {
        return "\nClass: Review\n" + "id: " + id + "\ndate: " + date + "\ndesc: " + desc;
    }
}
