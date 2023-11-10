package com.evanditaWiratamaPutraJBusER.controller;

import com.evanditaWiratamaPutraJBusER.Predicate;
import com.evanditaWiratamaPutraJBusER.dbjson.JsonTable;
import com.evanditaWiratamaPutraJBusER.dbjson.Serializable;
import com.evanditaWiratamaPutraJBusER.Algorithm;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/account")
public interface BasicGetController<T extends Serializable> {
    public abstract JsonTable<T> getJsonTable();


    @GetMapping("/page")
    public default List<T> getPage(@RequestParam(value = "page", defaultValue = "0") int page,
                                   @RequestParam (value = "size", defaultValue = "5") int pageSize) {
        return Algorithm.paginate(getJsonTable(),page,pageSize,t->true);
    }

    @GetMapping("/{id}")
    public default T getById(int id) {
        Predicate<T> s = (val) -> val.id == id;
        return Algorithm.find(getJsonTable(),s);
    }


}
