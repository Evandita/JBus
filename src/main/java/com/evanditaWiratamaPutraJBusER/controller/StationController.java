package com.evanditaWiratamaPutraJBusER.controller;

import com.evanditaWiratamaPutraJBusER.Bus;
import com.evanditaWiratamaPutraJBusER.City;
import com.evanditaWiratamaPutraJBusER.Station;
import com.evanditaWiratamaPutraJBusER.dbjson.JsonAutowired;
import com.evanditaWiratamaPutraJBusER.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Kelas controller yang menangani operasi terkait Stasiun pada aplikasi JBus.
 * Mengimplementasikan antarmuka {@link BasicGetController} untuk operasi dasar pengambilan data.
 *
 * @author Evandita
 */
@RestController
@RequestMapping("/station")
public class StationController implements BasicGetController<Station> {
    @JsonAutowired(value = Station.class, filepath = "C:\\Users\\Evandita\\Documents\\Tugas Kuliah\\Semester 3\\OOP 02\\Praktikum\\Pre CS\\Modul 1\\JBus\\src\\main\\java\\com\\evanditaWiratamaPutraJBusER\\json\\station.json")
    public static JsonTable<Station> stationTable;
    /**
     * Mendapatkan tabel JSON terkait dengan kelas Stasiun.
     *
     * @return Tabel JSON terkait dengan kelas Stasiun.
     */
    @Override
    public JsonTable<Station> getJsonTable() {
        return stationTable;
    }

    //Add new Station
    /**
     * Membuat stasiun baru dengan parameter yang diberikan.
     *
     * @param stationName Nama stasiun.
     * @param city        Nama kota yang valid dari enumerasi {@link City}.
     * @param address     Alamat stasiun.
     * @return BaseResponse yang berisi informasi tentang keberhasilan atau kegagalan pembuatan stasiun.
     */
    @PostMapping("/create")
    public BaseResponse<Station> createStation(
            @RequestParam String stationName,
            @RequestParam String city,
            @RequestParam String address
    ) {
        try {
            // Validate parameters
            if (stationName.isBlank() || city.isBlank() || address.isBlank()) {
                return new BaseResponse<>(false, "Parameter values cannot be blank or null", null);
            }

            // Validate city as a valid enum value
            City cityEnum = City.valueOf(city.toUpperCase());

            // Create a new station using the provided details
            Station newStation = new Station(stationName, cityEnum, address);

            // Add the new station to the stationTable
            stationTable.add(newStation);

            //Success response message
            return new BaseResponse<>(true, "Station added successfully", newStation);
        } catch (IllegalArgumentException e) {
            // Handle invalid enum value
            return new BaseResponse<>(false, "Invalid city value", null);
        } catch (Exception e) {
            // Handle other unexpected errors
            return new BaseResponse<>(false, "An error occurred while adding the station", null);
        }
    }
    /**
     * Mendapatkan daftar semua stasiun.
     *
     * @return BaseResponse yang berisi daftar stasiun atau informasi kegagalan.
     */
    @GetMapping("/getAll")
    public BaseResponse<List<Station>> getAllStation() {return new BaseResponse<>(true, "Station added successfully", getJsonTable());}

}
