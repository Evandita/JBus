package com.evanditaWiratamaPutraJBusER;
import com.evanditaWiratamaPutraJBusER.dbjson.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



/**
 * Kelas yang merepresentasikan akun pengguna.
 * @author Evandita
 */
public class Account extends Serializable
{
    /**
     * Alamat email pengguna.
     */
    public String email;
    /**
     * Nama pengguna.
     */
    public String name;
    /**
     * Kata sandi pengguna.
     */
    public String password;
    /**
     * Perusahaan yang terkait dengan akun ini.
     */
    public Renter company;
    /**
     * Saldo akun.
     */
    public double balance;
    /**
     * Pola regex untuk validasi kata sandi.
     */
    public static final String REGEX_PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[a-zA-Z0-9]{8,}$";
    /**
     * Pola regex untuk validasi alamat email.
     */
    public static final String REGEX_EMAIL = "^[a-zA-Z0-9]+@[a-zA-Z_]+?\\.[a-zA-Z.]+[a-zA-Z]+$";

    /**
     * Konstruktor untuk membuat objek akun baru.
     *
     * @param name     Nama pengguna.
     * @param email    Alamat email pengguna.
     * @param password Kata sandi pengguna.
     */
    public Account(String name, String email, String password)
    {
        super();
        this.name = name;
        this.email = email;
        this.password = password;
        this.balance = 0;
        this.company = null;
    }

    /**
     * Mengembalikan representasi string dari objek akun.
     *
     * @return String yang berisi informasi akun.
     */
    public String toString ()
    {
        return "\nClass: Account\n" + "id: " + id + "\nname: " + name + "\nemail: " + email + "\npassword: " + password;
    }

    /**
     * Validasi email dan password.
     *
     * @return True jika email dan password valid, false sebaliknya.
     */
    public boolean validate ()

    {
        Pattern patternPassword = Pattern.compile(REGEX_PASSWORD);
        Pattern patternEmail = Pattern.compile(REGEX_EMAIL);
        Matcher matcherPassword = patternPassword.matcher(password);
        Matcher matcherEmail = patternEmail.matcher(email);

        if (matcherPassword.find() && matcherEmail.find())
        {
            return true;
        }
        return false;
    }

    /**
     * Melakukan top-up saldo pada akun.
     *
     * @param amount Jumlah saldo yang akan ditambahkan.
     * @return True jika top-up berhasil, false jika jumlah negatif.
     */
    public boolean topUp(double amount) {
        if (amount < 0) return false;
        balance += amount;
        return true;
    }
}
