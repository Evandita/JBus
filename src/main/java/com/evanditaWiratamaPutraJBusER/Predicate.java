package com.evanditaWiratamaPutraJBusER;

/**
 * Antarmuka {@code Predicate} menyediakan metode untuk menentukan kebenaran suatu kondisi terhadap suatu objek.
 *
 * @param <T> Jenis objek yang akan dinilai.
 * @author Evandita
 */
public interface Predicate<T> {
    /**
     * Menilai kebenaran suatu kondisi terhadap objek yang diberikan.
     *
     * @param t Objek yang akan dinilai.
     * @return {@code true} jika kondisi terpenuhi, {@code false} jika tidak.
     */
    public boolean predicate (T t);
}
