package com.evanditaWiratamaPutraJBusER.controller;

import java.sql.Timestamp;
import java.util.*;
import com.evanditaWiratamaPutraJBusER.*;
import com.evanditaWiratamaPutraJBusER.dbjson.JsonAutowired;
import com.evanditaWiratamaPutraJBusER.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

/**
 * Kelas controller yang menangani operasi terkait Pembayaran pada aplikasi JBus.
 * Mengimplementasikan antarmuka {@link BasicGetController} untuk operasi dasar pengambilan data.
 *
 * @author Evandita
 */
@RestController
@RequestMapping("/payment")
public class PaymentController implements BasicGetController{
    @JsonAutowired(value = Payment.class, filepath = "C:\\Users\\Evandita\\Documents\\Tugas Kuliah\\Semester 3\\OOP 02\\Praktikum\\Pre CS\\Modul 1\\JBus\\src\\main\\java\\com\\evanditaWiratamaPutraJBusER\\json\\payment.json")
    public static JsonTable<Payment> paymentTable;
    /**
     * Mendapatkan tabel JSON terkait dengan kelas Pembayaran.
     *
     * @return Tabel JSON terkait dengan kelas Pembayaran.
     */
    public JsonTable<Payment> getJsonTable() {
        return paymentTable;
    }

    /**
     * Membuat pemesanan baru dengan parameter yang diberikan.
     *
     * @param buyerId        ID pembeli.
     * @param renterId       ID penyewa.
     * @param busId          ID bus yang dipesan.
     * @param busSeats       Daftar kursi yang dipesan.
     * @param departureDate  Waktu keberangkatan.
     * @return BaseResponse yang berisi informasi tentang keberhasilan atau kegagalan pembuatan pemesanan.
     */
    @PostMapping("/makeBooking")
    public BaseResponse<Payment> makeBooking (
            @RequestParam int buyerId,
            @RequestParam int renterId,
            @RequestParam int busId,
            @RequestParam List<String> busSeats,
            @RequestParam String departureDate
    ) {
        AccountController ac = new AccountController();
        StationController sc = new StationController();
        BusController bc = new BusController();

        Predicate<Account> a = (val) -> val.id == buyerId;
        Predicate<Bus> b = (val) -> val.id == busId;
        Predicate<Schedule> s = (val) -> val.departureSchedule.equals(Timestamp.valueOf(departureDate));

        Account buyer = Algorithm.find(ac.getJsonTable(), a);
        Bus bus = Algorithm.find(bc.getJsonTable(), b);
        if (buyer != null && bus != null) {
            Schedule depart = Algorithm.find(bus.schedules, s);
            if (buyer.balance >= bus.price.price && depart != null && depart.isSeatAvailable(busSeats)) {
                Payment pay = new Payment(buyerId, renterId, busId, busSeats, Timestamp.valueOf(departureDate));
                pay.status = Invoice.PaymentStatus.WAITING;
                Payment.makeBooking(Timestamp.valueOf(departureDate), pay.busSeats, bus);
                buyer.balance -= busSeats.size() * bus.price.price;
                paymentTable.addElement(pay);
                return new BaseResponse<>(true, "Berhasil makeBooking", pay);
            }
        }
        return new BaseResponse<>(false, "Gagal makeBooking", null);
    }

    /**
     * Menerima pembayaran untuk pemesanan dengan ID tertentu.
     *
     * @param id ID pemesanan yang akan diterima pembayarannya.
     * @return BaseResponse yang berisi informasi tentang keberhasilan atau kegagalan penerimaan pembayaran.
     */
    @PostMapping("/{id}/accept")
    public BaseResponse<Payment> accept (
            @PathVariable int id
    ) {
        Predicate<Payment> p = (val) -> val.id == id && val.status.equals(Invoice.PaymentStatus.WAITING);

        Payment tmp = Algorithm.find(paymentTable, p);
        if (tmp != null) {
            tmp.status = Invoice.PaymentStatus.SUCCESS;
            return new BaseResponse<>(true, "Berhasil accept", tmp);
        }
        return new BaseResponse<>(true, "Gagal accept", null);
    }

    /**
     * Membatalkan pemesanan dengan ID tertentu.
     *
     * @param id ID pemesanan yang akan dibatalkan.
     * @return BaseResponse yang berisi informasi tentang keberhasilan atau kegagalan pembatalan pemesanan.
     */
    @PostMapping("/{id}/cancel")
    public BaseResponse<Payment> cancel (
            @PathVariable int id
    ) {
        Predicate<Payment> p = (val) -> val.id == id && val.status.equals(Invoice.PaymentStatus.WAITING);

        Payment tmp = Algorithm.find(paymentTable, p);
        if (tmp != null) {
            BusController bc = new BusController();
            Predicate<Bus> b = (val) -> val.id == tmp.getBusId();
            Bus bus = Algorithm.find(bc.getJsonTable(), b);

            AccountController ac = new AccountController();
            Predicate<Account> a = (val) -> val.id == tmp.buyerId;
            Account buyer = Algorithm.find(ac.getJsonTable(), a);

            tmp.status = Invoice.PaymentStatus.FAILED;
            buyer.balance += tmp.busSeats.size() * bus.price.price;
            Payment.cancelBooking(tmp.departureDate, tmp.busSeats, bus);

            return new BaseResponse<>(true, "Berhasil cancel", tmp);
        }
        return new BaseResponse<>(true, "Gagal cancel", null);
    }

    /**
     * Mendapatkan daftar pembayaran berdasarkan ID pembeli.
     *
     * @param buyerId ID pembeli yang pembayarannya akan diambil.
     * @return BaseResponse yang berisi daftar pembayaran atau informasi kegagalan.
     */
    @GetMapping("/getBuyerPayment")
    public BaseResponse<List<Payment>> getBuyerPayment(@RequestParam int buyerId) {
        List<Payment> p = Algorithm.<Payment>collect(getJsonTable(), val->val.buyerId==buyerId);
        if (p != null) {
            return new BaseResponse<>(true, "Berhasil getBuyerPayment", p);
        }
        return new BaseResponse<>(false, "Gagal getBuyerPayment", null);
    }

    /**
     * Mendapatkan daftar pembayaran berdasarkan ID penyewa dan waktu keberangkatan.
     *
     * @param renterId       ID penyewa yang pembayarannya akan diambil.
     * @param departureDate  Waktu keberangkatan yang pembayarannya akan diambil.
     * @return BaseResponse yang berisi daftar pembayaran atau informasi kegagalan.
     */
    @GetMapping("/getRenterPayment")
    public BaseResponse<List<Payment>> getRenterPayment(@RequestParam int renterId, @RequestParam Timestamp departureDate) {
        List<Payment> p = Algorithm.<Payment>collect(getJsonTable(), val->val.renterId==renterId && val.status == Invoice.PaymentStatus.WAITING && val.departureDate.equals(departureDate));
        if (p != null) {
            return new BaseResponse<>(true, "Berhasil getRenterPayment", p);
        }
        return new BaseResponse<>(false, "Gagal getRenterPayment", null);
    }

}
