package com.finalproject.possystem.table.controller;

import com.finalproject.possystem.table.DiningService;
import com.finalproject.possystem.table.entity.Dining;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DiningController {
    @Autowired
    private DiningService diningService;

    @GetMapping("/dining")
    public List<Dining> getDining(){
        System.out.println(diningService.getTable());
        return diningService.getTable();
    }

    @PostMapping("/editDining")
    public Dining newTable(Dining dining){
        return diningService.newTable(dining);
    }
}
