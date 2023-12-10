package com.evanditaWiratamaPutraJBusER;
import com.evanditaWiratamaPutraJBusER.dbjson.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Kelas {@code Renter} merepresentasikan penyewa atau perusahaan penyewa dalam sistem.
 * @author Evandita
 */
public class Renter extends Serializable
{
    /**
     * Alamat dari penyewa.
     */
    public String address;
    /**
     * Nama perusahaan penyewa.
     */
    public String companyName;
    /**
     * Nomor telepon dari penyewa.
     */
    public String phoneNumber;
    /**
     * Pola regex untuk memvalidasi nomor telepon.
     */
    private final String REGEX_PHONE = "^[0-9]{9,12}$";
    /**
     * Pola regex untuk memvalidasi nama perusahaan.
     */
    private final String REGEX_NAME = "^[A-Z][A-Za-z0-9_]{4,20}$";


    /**
     * Membangun instance {@code Renter} dengan nama perusahaan.
     *
     * @param companyName Nama perusahaan penyewa.
     */
    public Renter (String companyName)
    {
        super();
        this.companyName = companyName;
        this.phoneNumber = "0";
        this.address = "";
    }

    /**
     * Membangun instance {@code Renter} dengan nama perusahaan dan nomor telepon.
     *
     * @param companyName Nama perusahaan penyewa.
     * @param phoneNumber Nomor telepon penyewa.
     */
    public Renter (String companyName, String phoneNumber)
    {
        super();
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
        this.address = "";
    }

    /**
     * Membangun instance {@code Renter} dengan nama perusahaan, nomor telepon, dan alamat.
     *
     * @param companyName Nama perusahaan penyewa.
     * @param phoneNumber Nomor telepon penyewa.
     * @param address     Alamat penyewa.
     */
    public Renter (String companyName, String phoneNumber, String address)
    {
        super();
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    /**
     * Memvalidasi nomor telepon dan nama perusahaan penyewa.
     *
     * @return {@code true} jika valid, {@code false} jika tidak valid.
     */
    public boolean validate ()

    {
        Pattern patternPhone = Pattern.compile(REGEX_PHONE);
        Pattern patternName = Pattern.compile(REGEX_NAME);
        Matcher matcherPhone = patternPhone.matcher(phoneNumber);
        Matcher matcherName = patternName.matcher(companyName);

        if (matcherPhone.find() && matcherName.find())
        {
            return true;
        }
        return false;


    }

}

