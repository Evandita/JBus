package com.evanditaWiratamaPutraJBusER.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import com.evanditaWiratamaPutraJBusER.*;
import com.evanditaWiratamaPutraJBusER.dbjson.JsonAutowired;
import com.evanditaWiratamaPutraJBusER.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Kelas controller yang menangani operasi terkait Bus pada aplikasi JBus.
 * Mengimplementasikan antarmuka {@link BasicGetController} untuk operasi dasar pengambilan data.
 *
 * @author Evandita
 */
@RestController
@RequestMapping("/bus")
public class BusController implements BasicGetController<Bus> {
    @JsonAutowired(value = Bus.class, filepath = "C:\\Users\\Evandita\\Documents\\Tugas Kuliah\\Semester 3\\OOP 02\\Praktikum\\Pre CS\\Modul 1\\JBus\\src\\main\\java\\com\\evanditaWiratamaPutraJBusER\\json\\bus.json")
    public static JsonTable<Bus> busTable;

    /**
     * Mendapatkan tabel JSON terkait dengan kelas Bus.
     *
     * @return Tabel JSON terkait dengan kelas Bus.
     */
    public JsonTable<Bus> getJsonTable() {
        return busTable;
    }

    /**
     * Membuat bus baru dengan parameter yang diberikan.
     *
     * @param accountId         ID akun yang memiliki bus.
     * @param name              Nama bus.
     * @param facilities        Daftar fasilitas bus.
     * @param price             Harga tiket bus.
     * @param capacity          Kapasitas bus.
     * @param busType           Tipe bus.
     * @param stationDepartureId ID stasiun keberangkatan.
     * @param stationArrivalId   ID stasiun kedatangan.
     * @return BaseResponse yang berisi informasi tentang keberhasilan atau kegagalan pembuatan bus.
     */
    @PostMapping("/create")
    public BaseResponse<Bus> create
            (
                    @RequestParam int accountId,
                    @RequestParam String name,
                    @RequestParam List<Facility> facilities,
                    @RequestParam int price,
                    @RequestParam int capacity,
                    @RequestParam BusType busType,
                    @RequestParam int stationDepartureId,
                    @RequestParam int stationArrivalId
                    )
    {
        AccountController ac = new AccountController();
        StationController sc = new StationController();
        Predicate<Account> a = (val) -> val.id == accountId;
        Predicate<Station> sd = (val) -> val.id == stationDepartureId;
        Predicate<Station> sa = (val) -> val.id == stationArrivalId;

        Account tmp = Algorithm.find(ac.getJsonTable(), a);
        Station depart = Algorithm.find(sc.getJsonTable(), sd);
        Station arriv = Algorithm.find(sc.getJsonTable(), sa);
        if (tmp != null &&  tmp.company != null && depart != null && arriv != null) {
            Price p = new Price((double)price);
            Bus newBus =  new Bus(accountId, name, facilities, p, capacity, busType, depart, arriv);
            busTable.addElement(newBus);
            return new BaseResponse<>(true, "Berhasil create", newBus);
        }
        return new BaseResponse<>(false, "Gagal create", null);
    }

    /**
     * Menambahkan jadwal keberangkatan untuk bus yang ada.
     *
     * @param busId ID bus yang akan ditambahkan jadwalnya.
     * @param time  Waktu keberangkatan dalam format timestamp.
     * @return BaseResponse yang berisi informasi tentang keberhasilan atau kegagalan penambahan jadwal.
     */
    @PostMapping("/addSchedule")
    public BaseResponse<Bus> addSchedule(
            @RequestParam int busId,
            @RequestParam String time
    ){
        Predicate<Bus> s = (val) -> val.id == busId;
        Bus tmp = Algorithm.find(busTable,s);
        if (tmp != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy HH:mm:ss");
            Timestamp departureDate = Timestamp.valueOf(time);
            tmp.addSchedule(departureDate);
            return new BaseResponse<>(true, "Berhasil addSchedule", tmp);
            /*
            try {
                Date date = dateFormat.parse(time);
                Timestamp departureDate = new Timestamp(date.getTime());
                tmp.addSchedule(departureDate);
                return new BaseResponse<>(true, "Berhasil addSchedule", tmp);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            */

        }
        return new BaseResponse<>(false, "Gagal addSchedule", null);
    }

    /**
     * Mendapatkan daftar bus berdasarkan ID akun.
     *
     * @param accountId ID akun yang memiliki bus.
     * @return BaseResponse yang berisi daftar bus atau informasi kegagalan.
     */
    @GetMapping("/getMyBus")
    public BaseResponse<List<Bus>> getMyBus(@RequestParam int accountId) {
        List<Bus> bus = Algorithm.<Bus>collect(getJsonTable(), b->b.accountId==accountId);
        if (bus != null) {
            return new BaseResponse<>(true, "Berhasil getMyBus", bus);
        }
        return new BaseResponse<>(false, "Gagal getMyBus", null);
    }

    /**
     * Mendapatkan semua data bus.
     *
     * @return BaseResponse yang berisi daftar semua bus atau informasi kegagalan.
     */
    @GetMapping("/getAll")
    public BaseResponse<List<Bus>> getAllBus() {return new BaseResponse<>(true, "Bus added successfully", getJsonTable());}
}
