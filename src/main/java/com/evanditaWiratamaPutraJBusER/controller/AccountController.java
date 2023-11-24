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


@RestController
@RequestMapping("/account")
public class AccountController implements BasicGetController<Account>
{
    public AccountController() {

    }
    @JsonAutowired(value = Account.class, filepath = "C:\\Users\\Evandita\\Documents\\Tugas Kuliah\\Semester 3\\OOP 02\\Praktikum\\Pre CS\\Modul 1\\JBus\\src\\main\\java\\com\\evanditaWiratamaPutraJBusER\\json\\account_db.json")
    public static JsonTable<Account> accountTable;
    public JsonTable<Account> getJsonTable() {
        return accountTable;
    }

    @GetMapping
    String index() { return "account page"; }

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
    /*
    @GetMapping("/{id}")
    String getById(@PathVariable int id) { return "account id " + id + " not found!"; }

     */
}
