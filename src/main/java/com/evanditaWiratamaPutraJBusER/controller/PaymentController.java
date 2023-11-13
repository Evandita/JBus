package com.evanditaWiratamaPutraJBusER.controller;

import java.sql.Timestamp;
import java.util.*;
import com.evanditaWiratamaPutraJBusER.*;
import com.evanditaWiratamaPutraJBusER.dbjson.JsonAutowired;
import com.evanditaWiratamaPutraJBusER.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController implements BasicGetController{
    @JsonAutowired(value = Payment.class, filepath = "C:\\Users\\Evandita\\Documents\\Tugas Kuliah\\Semester 3\\OOP 02\\Praktikum\\Pre CS\\Modul 1\\JBus\\src\\main\\java\\com\\evanditaWiratamaPutraJBusER\\json\\payment.json")
    public static JsonTable<Payment> paymentTable;
    public JsonTable<Payment> getJsonTable() {
        return paymentTable;
    }

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
                paymentTable.addElement(pay);
                return new BaseResponse<>(true, "Berhasil makeBooking", pay);
            }
        }
        return new BaseResponse<>(false, "Gagal makeBooking", null);
    }

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

    @PostMapping("/{id}/cancel")
    public BaseResponse<Payment> cancel (
            @PathVariable int id
    ) {
        Predicate<Payment> p = (val) -> val.id == id && val.status.equals(Invoice.PaymentStatus.WAITING);

        Payment tmp = Algorithm.find(paymentTable, p);
        if (tmp != null) {
            tmp.status = Invoice.PaymentStatus.FAILED;
            return new BaseResponse<>(true, "Berhasil cancel", tmp);
        }
        return new BaseResponse<>(true, "Gagal cancel", null);
    }
}
