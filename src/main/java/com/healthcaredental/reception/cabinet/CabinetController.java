package com.healthcaredental.reception.cabinet;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cabinet")
public class CabinetController {

    @Autowired
    private CabinetService cabinetService;

    @GetMapping("")
    public List<Cabinet> getAllCabinet() {


        return cabinetService.getAllCabinet();
    }

    @GetMapping("/{id}")
    public Cabinet getCabinet(@PathVariable("id") String id) {

        return cabinetService.getCabinet(id);
    }

    @PostMapping("")
    public void addCabinet(@RequestBody Cabinet cabinet) {



        cabinetService.addCabinet(cabinet);
    }

    @PutMapping("/{id}")
    public void updateCabinet(@RequestBody Cabinet cabinet) {


        cabinetService.updateCabinet(cabinet);

    }

    @DeleteMapping("/{id}")
    public void deleteCabinet(@PathVariable String id){
        cabinetService.deleteCabinet(id);
    }
}
