package com.evanditaWiratamaPutraJBusER;
import com.evanditaWiratamaPutraJBusER.dbjson.JsonDBEngine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.*;
import java.util.*;
import java.sql.Timestamp;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

/**
 * Kelas utama yang memuat metode main dan beberapa fungsi utilitas untuk menguji
 * aplikasi J Bus
 *
 * @author Evandita
 */
@SpringBootApplication
public class JBus
{
    /**
     * Metode main untuk menjalankan aplikasi.
     *
     * @param args Argumen yang diteruskan ke aplikasi.
     * @throws ParseException         Jika terjadi kesalahan parsing.
     * @throws InterruptedException   Jika terjadi kesalahan pada thread yang terinterupsi.
     */
    public static void main (String[] args)
            throws ParseException, InterruptedException {
        JsonDBEngine.Run(JBus.class);
        SpringApplication.run(JBus.class, args);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> JsonDBEngine.join()));

    }

    /**
     * Memfilter daftar bus berdasarkan kota keberangkatan.
     *
     * @param buses   Daftar bus yang akan difilter.
     * @param departure Kota keberangkatan yang menjadi kriteria filter.
     * @param page    Halaman yang diinginkan.
     * @param pageSize Jumlah item per halaman.
     * @return Daftar bus yang telah difilter.
     */
    public static List<Bus> filterByDeparture(List<Bus> buses, City departure, int page, int pageSize)
    {
        try {

            Predicate<Bus> s = (val) -> val.departure.city.equals(departure);
            List<Bus> filteredBus = Algorithm.collect(buses, s);
            return Algorithm.paginate(filteredBus,page, pageSize, t->true);
        }
        catch (Throwable t){
            t.printStackTrace();
            return null;
        }
    }

    /**
     * Memfilter daftar bus berdasarkan rentang harga.
     *
     * @param buses Daftar bus yang akan difilter.
     * @param min   Harga minimum.
     * @param max   Harga maksimum.
     * @return Daftar bus yang telah difilter.
     */
    public static List<Bus> filterByPrice(List<Bus> buses, int min, int max)
    {
        try {

            Predicate<Bus> s = (val) -> val.price.price >= min && val.price.price <= max;
            List<Bus> filteredBus = Algorithm.collect(buses, s);
            return filteredBus;
        }
        catch (Throwable t){
            t.printStackTrace();
            return null;
        }
    }

    /**
     * Memfilter daftar bus berdasarkan kota keberangkatan dan kota kedatangan.
     *
     * @param buses Daftar bus yang akan difilter.
     * @param departure Kota keberangkatan yang menjadi kriteria filter.
     * @param arrival Kota kedatangan yang menjadi kriteria filter.
     * @param page Halaman yang diinginkan.
     * @param pageSize Jumlah item per halaman.
     * @return Daftar bus yang telah difilter.
     */
    public static List<Bus> filterByDepartureAndArrival(List<Bus> buses,City departure, City arrival, int page, int pageSize)
    {
        try {

            Predicate<Bus> s = (val) -> val.departure.city.equals(departure) && val.arrival.city.equals(arrival);
            List<Bus> filteredBus = Algorithm.collect(buses, s);
            return Algorithm.paginate(filteredBus,page, pageSize, t->true);
        }
        catch (Throwable t){
            t.printStackTrace();
            return null;
        }
    }

    /**
     * Mengambil bus dari daftar berdasarkan ID.
     *
     * @param buses Daftar bus.
     * @param id ID bus yang dicari.
     * @return Objek Bus yang sesuai dengan ID.
     */
    public static Bus filterBusId(List<Bus> buses,int id)
    {
        try {

            Predicate<Bus> s = (val) -> val.id == id;
            Bus filteredBus = Algorithm.find(buses, s);
            return filteredBus;
        }
        catch (Throwable t){
            t.printStackTrace();
            return null;
        }
    }

    /**
     * Metode tes keberadaan pada array.
     *
     * @param t Array yang akan diuji.
     */
    private static void testExist(Integer[] t) {
        int valueToCheck = 67;
        boolean result3 = Algorithm.exists(t, valueToCheck);
        if (result3) {
            System.out.println(valueToCheck + " exist in the array.");
        } else {
            System.out.println(valueToCheck + " doesn't exists in the array.");
        }
    }
    /**
     * Metode tes perhitungan jumlah elemen pada array.
     *
     * @param t Array yang akan diuji.
     */
    public static void testCount(Integer[] t) {
        int valueToCount = 18;
        int result = Algorithm.count(t, valueToCount);
        System.out.println("Number " + valueToCount + " appears " + result + " times");
    }
    /**
     * Metode tes pencarian nilai dalam array.
     *
     * @param t Array yang akan diuji.
     */
    public static void testFind(Integer[] t) {
        Integer valueToFind = 69;
        Integer result2 = Algorithm.find(t, valueToFind);
        System.out.print("Finding " + valueToFind + " inside the array : ");
        if (result2 != null) {
            System.out.println("Found!" + result2);
        } else {
            System.out.println("Not Found");
        }
    }
    /**
     * Metode tes pengumpulan elemen berdasarkan kriteria.
     *
     * @param t Array yang akan diuji.
     */
    private static void testCollect(Integer[] t) {
        Predicate<Integer> below = (val) -> val <= 22;
        Predicate<Integer> above = (val) -> val > 43;

        List<Integer> integerBelow = Algorithm.collect(t, below);
        List<Integer> integerAbove = Algorithm.collect(t, above);

        System.out.println("Below 22");
        System.out.println(integerBelow);
        System.out.println("Above 43");
        System.out.println(integerAbove);
    }
    /**
     * Metode mendapatkan ID bus.
     *
     * @return ID bus.
     */
    public static int getBusId ()
    {
        return 0;
    }

    /**
     * Metode mendapatkan nama bus.
     *
     * @return Nama bus.
     */
    public static String getBusName ()
    {
        return "Bus";
    }

    /**
     * Metode mengecek apakah ada diskon.
     *
     * @return true jika ada diskon, false jika tidak.
     */
    public static boolean isDiscount ()
    {
        return true;
    }

    /**
     * Metode mendapatkan persentase diskon.
     *
     * @param beforeDiscount Harga sebelum diskon.
     * @param afterDiscount Harga setelah diskon.
     * @return Persentase diskon.
     */
    public static float getDiscountPercentage (int beforeDiscount, int afterDiscount)
    {
        if (afterDiscount >= beforeDiscount){
            return 0;
        }
        else{
            return (float)((beforeDiscount - afterDiscount))/beforeDiscount * 100;
        }
    }

    /**
     * Metode mendapatkan harga setelah diskon.
     *
     * @param price Harga awal.
     * @param discountPercentage Persentase diskon.
     * @return Harga setelah diskon.
     */
    public static int getDiscountedPrice (int price, float discountPercentage)
    {
        if (discountPercentage > 100){
            discountPercentage = 100;
        }
        return (int)((100-discountPercentage)/100 * price);
    }

    /**
     * Metode mendapatkan harga awal sebelum diskon.
     *
     * @param discountedPrice Harga setelah diskon.
     * @param discountPercentage Persentase diskon.
     * @return Harga awal sebelum diskon.
     */
    public static int getOriginalPrice (int discountedPrice, float discountPercentage)
    {
        if (discountPercentage > 100){
            discountPercentage = 100;
        }
        return (int)(discountedPrice/(100-discountPercentage) * 100);
    }

    /**
     * Metode mendapatkan persentase biaya administrasi.
     *
     * @return Persentase biaya administrasi.
     */
    public static float getAdminFeePercentage ()
    {
        return (float)(0.05);
    }

    /**
     * Metode mendapatkan biaya administrasi.
     *
     * @param price Harga tiket.
     * @return Biaya administrasi.
     */
    public static int getAdminFee (int price)
    {
        return (int)(getAdminFeePercentage() * price);
    }

    /**
     * Metode mendapatkan total harga tiket.
     *
     * @param price Harga tiket.
     * @param numberOfSeat Jumlah kursi yang dipesan.
     * @return Total harga tiket.
     */
    public static int getTotalPrice (int price,int numberOfSeat)
    {
        return (int)(price * numberOfSeat + getAdminFee(price *numberOfSeat));
    }

}