package com.evanditaWiratamaPutraJBusER.controller;

import com.evanditaWiratamaPutraJBusER.Renter;
import com.evanditaWiratamaPutraJBusER.Account;
import com.evanditaWiratamaPutraJBusER.Algorithm;
import com.evanditaWiratamaPutraJBusER.dbjson.JsonAutowired;
import com.evanditaWiratamaPutraJBusER.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;




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
        Account tmp =  new Account(name, email, password);
        if (name.isBlank() == false && tmp.validate() && Algorithm.exists(accountTable,tmp)) {
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
            if (i.password.equals(generatedPassword))
                return new BaseResponse<>(true, "Berhasil login", i);
        }
        return new BaseResponse<>(false, "Gagal register", null);
    }

    @PostMapping ("/{id}/registerRenter")
    BaseResponse<Renter> registerRenter (@PathVariable int id,
                                         @RequestParam String companyName,
                                         @RequestParam String address,
                                         @RequestParam String phoneNumber)
    {
        Account renter = new Account(companyName, address, phoneNumber)
        if (Algorithm.exists(accountTable, renter)) {
            return new BaseResponse<>(true, "Berhasil login", i);
        }
    }
    /*
    @GetMapping("/{id}")
    String getById(@PathVariable int id) { return "account id " + id + " not found!"; }

     */
}
