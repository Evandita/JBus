package com.evanditaWiratamaPutraJBusER.controller;

import com.evanditaWiratamaPutraJBusER.Predicate;
import com.evanditaWiratamaPutraJBusER.dbjson.JsonTable;
import com.evanditaWiratamaPutraJBusER.dbjson.Serializable;
import com.evanditaWiratamaPutraJBusER.Algorithm;
import org.springframework.web.bind.annotation.*;
import java.util.*;


/**
 * Antarmuka dasar untuk mengakses data dengan operasi dasar seperti
 * mendapatkan halaman data dan mendapatkan data berdasarkan ID.
 *
 * @param <T> Tipe data yang dapat di-serialize yang akan diakses.
 * @author Evandita
 */
@RestController
@RequestMapping("/account")
public interface BasicGetController<T extends Serializable> {
    /**
     * Mendapatkan tabel JSON terkait dengan tipe data T.
     *
     * @return Tabel JSON terkait dengan tipe data T.
     */
    public abstract JsonTable<T> getJsonTable();

    /**
     * Mendapatkan halaman data berdasarkan nomor halaman dan ukuran halaman.
     *
     * @param page Nomor halaman yang akan diakses (dimulai dari 0).
     * @param pageSize Ukuran halaman, yaitu jumlah elemen per halaman.
     * @return List berisi data pada halaman yang diminta.
     */
    @GetMapping("/page")
    public default List<T> getPage(@RequestParam(value = "page", defaultValue = "0") int page,
                                   @RequestParam (value = "size", defaultValue = "5") int pageSize) {
        return Algorithm.paginate(getJsonTable(),page,pageSize,t->true);
    }

    /**
     * Mendapatkan data berdasarkan ID.
     *
     * @param id ID dari data yang akan diambil.
     * @return Objek data yang sesuai dengan ID yang diberikan.
     */
    @GetMapping("/{id}")
    public default T getById(@PathVariable int id) {
        Predicate<T> s = (val) -> val.id == id;
        return Algorithm.find(getJsonTable(),s);
    }


}
