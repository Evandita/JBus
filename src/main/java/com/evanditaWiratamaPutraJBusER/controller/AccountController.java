package com.evanditaWiratamaPutraJBusER.controller;

import com.evanditaWiratamaPutraJBusER.Predicate;
import com.evanditaWiratamaPutraJBusER.Renter;
import com.evanditaWiratamaPutraJBusER.Account;
import com.evanditaWiratamaPutraJBusER.Algorithm;
import com.evanditaWiratamaPutraJBusER.dbjson.JsonAutowired;
import com.evanditaWiratamaPutraJBusER.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Controller untuk menangani operasi terkait akun pengguna.
 * Menyediakan endpoint untuk registrasi pengguna, login, registrasi renter,
 * dan penambahan saldo akun.
 *
 * @author Evandita
 */
@RestController
@RequestMapping("/account")
public class AccountController implements BasicGetController<Account>
{
    public AccountController() {

    }
    @JsonAutowired(value = Account.class, filepath = "C:\\Users\\Evandita\\Documents\\Tugas Kuliah\\Semester 3\\OOP 02\\Praktikum\\Pre CS\\Modul 1\\JBus\\src\\main\\java\\com\\evanditaWiratamaPutraJBusER\\json\\account_db.json")
    public static JsonTable<Account> accountTable;

    /**
     * Mendapatkan instance JsonTable yang terkait dengan kelas Account.
     *
     * @return Instance JsonTable<Account>
     */
    public JsonTable<Account> getJsonTable() {
        return accountTable;
    }

    /**
     * Endpoint default untuk halaman akun.
     *
     * @return String yang menunjukkan halaman akun
     */
    @GetMapping
    String index() { return "account page"; }

    /**
     * Endpoint untuk registrasi pengguna.
     *
     * @param name     Nama pengguna
     * @param email    Alamat email pengguna
     * @param password Kata sandi pengguna
     * @return BaseResponse dengan status keberhasilan dan objek Account yang terdaftar
     */
    @PostMapping("/register")
    BaseResponse<Account> register
            (
                    @RequestParam String name,
                    @RequestParam String email,
                    @RequestParam String password
            )
    {

        Predicate<Account> s = (val) -> val.email.equals(email);

        String REGEX_PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[a-zA-Z0-9]{8,}$";
        String REGEX_EMAIL = "^[a-zA-Z0-9]+@[a-zA-Z_]+?\\.[a-zA-Z.]+[a-zA-Z]+$";
        Pattern patternPassword = Pattern.compile(REGEX_PASSWORD);
        Pattern patternEmail = Pattern.compile(REGEX_EMAIL);
        Matcher matcherPassword = patternPassword.matcher(password);
        Matcher matcherEmail = patternEmail.matcher(email);

        if (name.isBlank() == false && matcherPassword.find() && matcherEmail.find() && Algorithm.exists(accountTable,s) == false) {
            String passwordToHash = password;
            String generatedPassword = null;

            try
            {
                MessageDigest md = MessageDigest.getInstance("MD5");

                md.update(passwordToHash.getBytes());

                byte[] bytes = md.digest();

                StringBuilder sb = new StringBuilder();
                for (int i = 0;i < bytes.length;i++) {
                    sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
                }
                generatedPassword = sb.toString();
            }
            catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            Account tmp =  new Account(name, email, generatedPassword);
            accountTable.addElement(tmp);
            return new BaseResponse<>(true, "Berhasil register", tmp);
        }
        return new BaseResponse<>(false, "Gagal register", null);

    }

    /**
     * Endpoint untuk login pengguna.
     *
     * @param email    Alamat email pengguna
     * @param password Kata sandi pengguna
     * @return BaseResponse dengan status keberhasilan dan objek Account yang masuk
     */
    @PostMapping("/login")
    BaseResponse<Account> login (@RequestParam String email,
                                 @RequestParam String password) {
        String passwordToHash = password;
        String generatedPassword = null;

        try
        {
            MessageDigest md = MessageDigest.getInstance("MD5");

            md.update(passwordToHash.getBytes());

            byte[] bytes = md.digest();

            StringBuilder sb = new StringBuilder();
            for (int i = 0;i < bytes.length;i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            generatedPassword = sb.toString();



        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        for (Account i : accountTable) {
            if (i.password.equals(generatedPassword) && i.email.equals(email))
                return new BaseResponse<>(true, "Berhasil login", i);
        }
        return new BaseResponse<>(false, "Gagal Login", null);
    }

    /**
     * Endpoint untuk registrasi akun renter.
     *
     * @param id           ID akun pengguna
     * @param companyName  Nama perusahaan renter
     * @param address      Alamat renter
     * @param phoneNumber  Nomor telepon renter
     * @return BaseResponse dengan status keberhasilan dan objek Renter yang terdaftar
     */
    @PostMapping ("/{id}/registerRenter")
    BaseResponse<Renter> registerRenter (@PathVariable int id,
                                         @RequestParam String companyName,
                                         @RequestParam String address,
                                         @RequestParam String phoneNumber)
    {
        for (Account i : accountTable) {
            if (i.id == id && i.company == null){
                i.company = new Renter(companyName, phoneNumber, address);
                return new BaseResponse<>(true, "Berhasil register renter", i.company);
            }
        }
        return new BaseResponse<>(false, "Gagal register renter", null);
    }

    /**
     * Endpoint untuk menambahkan saldo akun pengguna.
     *
     * @param id     ID akun pengguna
     * @param amount Jumlah yang akan ditambahkan
     * @return BaseResponse dengan status keberhasilan dan objek Account yang diperbarui
     */
    @PostMapping("/{id}/topUp")
    BaseResponse<Account> topUp(@PathVariable int id, @RequestParam double amount)
    {
        for (Account i : accountTable) {
            if (i.id == id){
                if (i.topUp(amount)){
                    return new BaseResponse<>(true, "Berhasil top up", i);
                }
            }
        }
        return new BaseResponse<>(false, "Gagal top up", null);
    }

}
