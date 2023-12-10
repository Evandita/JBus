package com.evanditaWiratamaPutraJBusER.controller;

/**
 * Kelas generik untuk membungkus respons API.
 *
 * @param <T> Tipe data muatan yang akan dibungkus oleh respons.
 * @author Evandita
 */
public class BaseResponse<T> {
    /**
     * Menunjukkan keberhasilan atau kegagalan operasi.
     */
    public boolean success;
    /**
     * Pesan yang memberikan informasi tambahan tentang respons.
     */
    public String message;
    /**
     * Data muatan yang dibungkus oleh respons.
     */
    public T payload;

    /**
     * Konstruktor untuk membuat objek BaseResponse.
     *
     * @param success Menunjukkan keberhasilan atau kegagalan operasi.
     * @param message Pesan yang memberikan informasi tambahan tentang respons.
     * @param payload Data muatan yang dibungkus oleh respons.
     */
    public BaseResponse(boolean success, String message, T payload) {
        this.success = success;
        this.message = message;
        this.payload = payload;
    }
}
