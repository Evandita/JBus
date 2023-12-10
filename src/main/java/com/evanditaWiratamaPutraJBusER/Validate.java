package com.evanditaWiratamaPutraJBusER;
import java.util.ArrayList;

/**
 * Kelas {@code Validate} menyediakan metode untuk menyaring array harga berdasarkan kriteria tertentu.
 * @author Evandita
 */
public class Validate
{
    /**
     * Memfilter array objek Price berdasarkan nilai dan jenis perbandingan tertentu.
     *
     * @param list  Array objek Price yang akan difilter.
     * @param value Nilai batas yang digunakan untuk menyaring.
     * @param less  Sebuah bendera boolean yang menunjukkan jenis perbandingan:
     *              {@code true} untuk kurang dari atau sama dengan nilai batas,
     *              {@code false} untuk lebih besar dari nilai batas.
     * @return ArrayList berisi harga-harga yang memenuhi kriteria penyaringan.
     */
    public static ArrayList filter(Price[] list,int value, boolean less)
    {
        ArrayList<Double> arrlist = new ArrayList<Double>();
        for (Price i: list)
        {
            if (less == true && i.price <= value)
            {
                arrlist.add(i.price);
            }
            else if (less == false && i.price > value)
            {
                arrlist.add(i.price);
            }
        }
        
        return arrlist;
    }
}
