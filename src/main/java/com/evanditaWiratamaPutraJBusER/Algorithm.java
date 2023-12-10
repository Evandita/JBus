package com.evanditaWiratamaPutraJBusER;

import java.util.*;


/**
 * Kelas Algorithm menyediakan berbagai metode utilitas untuk melakukan operasi umum pada array dan iterable.
 * Metode-metode ini mencakup paginasi, pengumpulan, pencarian, penghitungan, dan pemeriksaan keberadaan elemen.
 * @author Evandita
 */
public class Algorithm {

    private Algorithm () {

    }

    //PAGINATE
    /**
     * Memisahkan elemen-elemen dalam array berdasarkan halaman dan ukuran halaman yang ditentukan.
     *
     * @param array    Array yang akan dipaginasi.
     * @param page     Nomor halaman.
     * @param pageSize Ukuran halaman.
     * @param pred     Predikat untuk memilih elemen yang sesuai.
     * @param <T>      Tipe elemen dalam array.
     * @return Daftar elemen pada halaman yang ditentukan.
     */

    public static <T> List<T> paginate(T[] array, int page, int pageSize, Predicate<T> pred) {
        final Iterator<T> it = Arrays.stream(array).iterator();
        return paginate(it, page, pageSize, pred);
    }

    /**
     * Memisahkan elemen-elemen dalam iterable berdasarkan halaman dan ukuran halaman yang ditentukan.
     *
     * @param iterable Iterable yang akan dipaginasi.
     * @param page     Nomor halaman.
     * @param pageSize Ukuran halaman.
     * @param pred     Predikat untuk memilih elemen yang sesuai.
     * @param <T>      Tipe elemen dalam iterable.
     * @return Daftar elemen pada halaman yang ditentukan.
     */
    public static <T> List<T> paginate(Iterable<T> iterable, int page, int pageSize, Predicate<T> pred) {
        final Iterator<T> it = iterable.iterator();
        return paginate(it, page, pageSize, pred);
    }

    /**
     * Memisahkan elemen-elemen dalam iterator berdasarkan halaman dan ukuran halaman yang ditentukan.
     *
     * @param iterator Iterator yang akan dipaginasi.
     * @param page     Nomor halaman.
     * @param pageSize Ukuran halaman.
     * @param pred     Predikat untuk memilih elemen yang sesuai.
     * @param <T>      Tipe elemen dalam iterator.
     * @return Daftar elemen pada halaman yang ditentukan.
     */
    public static <T> List<T> paginate(Iterator<T> iterator, int page, int pageSize, Predicate<T> pred) {
        List <T> arrlist = new ArrayList<T>();
        int cnt = 1;
        while(iterator.hasNext()) {
            T current = iterator.next();
            if (pred.predicate(current)) {
                if (cnt > page*pageSize && cnt <= (page + 1)*pageSize ) {
                    arrlist.add(current);
                }
            }
            cnt++;
        }
        return arrlist;
    }

    //COLLECT
    /**
     * Mengumpulkan elemen-elemen dalam array yang sama dengan nilai tertentu.
     *
     * @param array Array yang akan dikumpulkan.
     * @param value Nilai untuk dikumpulkan.
     * @param <T>   Tipe elemen dalam array.
     * @return Daftar elemen yang sesuai dengan nilai tertentu.
     */
    public static <T> List<T> collect(T[] array, T value) {
        final Iterator<T> it = Arrays.stream(array).iterator();
        return collect(it, value);
    }

    /**
     * Mengumpulkan elemen-elemen dalam iterable yang sama dengan nilai tertentu.
     *
     * @param iterable Iterable yang akan dikumpulkan.
     * @param value    Nilai untuk dikumpulkan.
     * @param <T>      Tipe elemen dalam iterable.
     * @return Daftar elemen yang sesuai dengan nilai tertentu.
     */
    public static <T> List<T> collect(Iterable<T> iterable, T value) {
        final Iterator<T> it = iterable.iterator();
        return collect(it, value);
    }

    /**
     * Mengumpulkan elemen-elemen dalam iterator yang sama dengan nilai tertentu.
     *
     * @param iterator Iterator yang akan dikumpulkan.
     * @param value    Nilai untuk dikumpulkan.
     * @param <T>      Tipe elemen dalam iterator.
     * @return Daftar elemen yang sesuai dengan nilai tertentu.
     */
    public static <T> List<T> collect(Iterator<T> iterator, T value) {
        final Predicate<T> pred = value::equals;
        return collect(iterator, pred);
    }

    /**
     * Mengumpulkan elemen-elemen dalam iterator yang sama dengan nilai tertentu.
     *
     * @param pred     Predikat untuk memilih elemen yang sesuai.
     * @param <T>      Tipe elemen dalam array.
     * @return Daftar elemen yang sesuai dengan nilai tertentu.
     */
    public static <T> List<T> collect(T[] array, Predicate<T> pred) {
        final Iterator<T> it = Arrays.stream(array).iterator();
        return collect(it, pred);
    }

    /**
     * Mengumpulkan elemen-elemen dalam iterable yang memenuhi predikat tertentu.
     *
     * @param iterable Iterable yang akan dikumpulkan.
     * @param pred     Predikat untuk memilih elemen yang sesuai.
     * @param <T>      Tipe elemen dalam iterable.
     * @return Daftar elemen yang sesuai dengan predikat tertentu.
     */
    public static <T> List<T> collect(Iterable<T> iterable, Predicate<T> pred) {
        final Iterator<T> it = iterable.iterator();
        return collect(it, pred);
    }

    /**
     * Mengumpulkan elemen-elemen dalam iterator yang memenuhi predikat tertentu.
     *
     * @param iterator Iterator yang akan dikumpulkan.
     * @param pred     Predikat untuk memilih elemen yang sesuai.
     * @param <T>      Tipe elemen dalam iterator.
     * @return Daftar elemen yang sesuai dengan predikat tertentu.
     */
    public static <T> List<T> collect(Iterator<T> iterator, Predicate<T> pred) {
        List <T> arrlist = new ArrayList<T>();
        while(iterator.hasNext()) {
            T current = iterator.next();
            if (pred.predicate(current)) {
                arrlist.add(current);
            }
        }
        return arrlist;
    }

    //FIND
    /**
     * Mencari elemen tertentu dalam array.
     *
     * @param array Array yang akan dicari.
     * @param value Nilai yang akan dicari.
     * @param <T>   Tipe elemen dalam array.
     * @return Elemen yang sesuai dengan nilai tertentu.
     */
    public static <T> T find(T[] array, T value) {
        final Iterator<T> it = Arrays.stream(array).iterator();
        return find(it, value);
    }

    /**
     * Mencari elemen tertentu dalam iterable.
     *
     * @param iterable Iterable yang akan dicari.
     * @param value    Nilai yang akan dicari.
     * @param <T>      Tipe elemen dalam iterable.
     * @return Elemen yang sesuai dengan nilai tertentu.
     */
    public static <T> T find(Iterable<T> iterable, T value) {
        final Iterator<T> it = iterable.iterator();
        return find(it, value);
    }

    /**
     * Mencari elemen tertentu dalam iterator.
     *
     * @param iterator Iterator yang akan dicari.
     * @param value    Nilai yang akan dicari.
     * @param <T>      Tipe elemen dalam iterator.
     * @return Elemen yang sesuai dengan nilai tertentu.
     */
    public static <T> T find(Iterator<T> iterator, T value) {
        final Predicate<T> pred = value::equals;
        return find(iterator, pred);
    }

    /**
     * Mencari elemen yang memenuhi predikat tertentu dalam array.
     *
     * @param array Array yang akan dicari.
     * @param pred  Predikat untuk memilih elemen yang sesuai.
     * @param <T>   Tipe elemen dalam array.
     * @return Elemen yang sesuai dengan predikat tertentu.
     */
    public static <T> T find(T[] array, Predicate<T> pred) {
        final Iterator<T> it = Arrays.stream(array).iterator();
        return find(it, pred);
    }

    /**
     * Mencari elemen yang memenuhi predikat tertentu dalam iterable.
     *
     * @param iterable Iterable yang akan dicari.
     * @param pred     Predikat untuk memilih elemen yang sesuai.
     * @param <T>      Tipe elemen dalam iterable.
     * @return Elemen yang sesuai dengan predikat tertentu.
     */
    public static <T> T find(Iterable<T> iterable, Predicate<T> pred) {
        final Iterator<T> it = iterable.iterator();
        return find(it, pred);
    }

    /**
     * Mencari elemen yang memenuhi predikat tertentu dalam iterator.
     *
     * @param iterator Iterator yang akan dicari.
     * @param pred     Predikat untuk memilih elemen yang sesuai.
     * @param <T>      Tipe elemen dalam iterator.
     * @return Elemen yang sesuai dengan predikat tertentu.
     */
    public static <T> T find(Iterator<T> iterator, Predicate<T> pred) {
        while(iterator.hasNext()) {
            T current = iterator.next();
            if (pred.predicate(current)) {
                return current;
            }
        }
        return null;
    }
    //COUNT
    /**
     * Menghitung jumlah elemen tertentu dalam array.
     *
     * @param array Array yang akan dihitung.
     * @param value Nilai yang akan dihitung.
     * @param <T>   Tipe elemen dalam array.
     * @return Jumlah elemen yang sesuai dengan nilai tertentu.
     */
    public static <T> int count(T[] array, T value) {
        final Iterator<T> it = Arrays.stream(array).iterator();
        return count(it, value);
    }

    /**
     * Menghitung jumlah elemen tertentu dalam iterable.
     *
     * @param iterable Iterable yang akan dihitung.
     * @param value    Nilai yang akan dihitung.
     * @param <T>      Tipe elemen dalam iterable.
     * @return Jumlah elemen yang sesuai dengan nilai tertentu.
     */
    public static <T> int count(Iterable<T> iterable, T value) {
        final Iterator<T> it = iterable.iterator();
        return count(it, value);
    }

    /**
     * Menghitung jumlah elemen yang memenuhi predikat tertentu dalam iterator.
     *
     * @param iterator Iterator yang akan dihitung.
     * @param value    Nilai yang akan dihitung.
     * @param <T>      Tipe elemen dalam iterator.
     * @return Jumlah elemen yang sesuai dengan nilai tertentu.
     */
    public static <T> int count(Iterator<T> iterator, T value) {
        final Predicate<T> pred = value::equals;
        return count(iterator, pred);
    }

    /**
     * Menghitung jumlah elemen yang memenuhi predikat tertentu dalam array.
     *
     * @param array Array yang akan dihitung.
     * @param pred  Predikat untuk memilih elemen yang sesuai.
     * @param <T>   Tipe elemen dalam array.
     * @return Jumlah elemen yang sesuai dengan predikat tertentu.
     */
    public static <T>int count(T[] array, Predicate<T> pred) {
        final Iterator<T> it = Arrays.stream(array).iterator();
        return count(it, pred);
    }

    /**
     * Menghitung jumlah elemen yang memenuhi predikat tertentu dalam iterable.
     *
     * @param iterable Iterable yang akan dihitung.
     * @param pred     Predikat untuk memilih elemen yang sesuai.
     * @param <T>      Tipe elemen dalam iterable.
     * @return Jumlah elemen yang sesuai dengan predikat tertentu.
     */
    public static <T> int count(Iterable<T> iterable, Predicate<T> pred) {
        final Iterator<T> it = iterable.iterator();
        return count(it, pred);
    }

    /**
     * Menghitung jumlah elemen yang memenuhi predikat tertentu dalam iterator.
     *
     * @param iterator Iterator yang akan dihitung.
     * @param pred     Predikat untuk memilih elemen yang sesuai.
     * @param <T>      Tipe elemen dalam iterator.
     * @return Jumlah elemen yang sesuai dengan predikat tertentu.
     */
    public static <T> int count(Iterator<T> iterator, Predicate<T> pred) {
        int cnt = 0;
        while(iterator.hasNext()) {
            T current = iterator.next();
            if (pred.predicate(current)) {
                cnt++;
            }
        }
        return cnt;
    }

    //EXISTS

    /**
     * Memeriksa apakah elemen tertentu ada dalam array.
     *
     * @param array Array yang akan diperiksa.
     * @param value Nilai yang akan diperiksa.
     * @param <T>   Tipe elemen dalam array.
     * @return True jika elemen ditemukan, false sebaliknya.
     */
    public static <T> boolean exists(T[] array, T value) {
        final Iterator<T> it = Arrays.stream(array).iterator();
        return exists(it, value);
    }

    /**
     * Memeriksa apakah elemen tertentu ada dalam iterable.
     *
     * @param iterable Iterable yang akan diperiksa.
     * @param value    Nilai yang akan diperiksa.
     * @param <T>      Tipe elemen dalam iterable.
     * @return True jika elemen ditemukan, false sebaliknya.
     */
    public static <T> boolean exists(Iterable<T> iterable, T value) {
        final Iterator<T> it = iterable.iterator();
        return exists(it, value);
    }

    /**
     * Memeriksa apakah elemen tertentu ada dalam iterator.
     *
     * @param iterator Iterator yang akan diperiksa.
     * @param value    Nilai yang akan diperiksa.
     * @param <T>      Tipe elemen dalam iterator.
     * @return True jika elemen ditemukan, false sebaliknya.
     */
    public static <T> boolean exists(Iterator<T> iterator, T value) {
        final Predicate<T> pred = value::equals;
        return exists(iterator, pred);
    }

    /**
     * Memeriksa apakah elemen tertentu ada dalam iterator.
     *
     * @param array    Array yang akan diperiksa.
     * @param pred     Predikat untuk memilih elemen yang sesuai.
     * @param <T>      Tipe elemen dalam Array.
     * @return True jika elemen yang memenuhi predikat ditemukan, false sebaliknya.
     */
    public static <T> boolean exists(T[] array, Predicate<T> pred) {
        final Iterator<T> it = Arrays.stream(array).iterator();
        return exists(it, pred);
    }


    /**
     * Memeriksa apakah elemen yang memenuhi predikat tertentu ada dalam iterable.
     *
     * @param iterable Iterable yang akan diperiksa.
     * @param pred     Predikat untuk memilih elemen yang sesuai.
     * @param <T>      Tipe elemen dalam iterable.
     * @return True jika elemen yang memenuhi predikat ditemukan, false sebaliknya.
     */
    public static <T> boolean exists(Iterable<T> iterable, Predicate<T> pred) {
        final Iterator<T> it = iterable.iterator();
        return exists(it, pred);
    }

    /**
     * Memeriksa apakah elemen yang memenuhi predikat tertentu ada dalam iterator.
     *
     * @param iterator Iterator yang akan diperiksa.
     * @param pred     Predikat untuk memilih elemen yang sesuai.
     * @param <T>      Tipe elemen dalam iterator.
     * @return True jika elemen yang memenuhi predikat ditemukan, false sebaliknya.
     */
    public static <T> boolean exists(Iterator<T> iterator, Predicate<T> pred) {
        while (iterator.hasNext()) {

            T current = iterator.next();
            if (pred.predicate(current))
                return true;
        }
        return false;
    }
}