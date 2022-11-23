package com.example.firma.Controller;

import com.example.firma.Pilot.ApiResponse;
import com.example.firma.Pilot.BolimDto;
import com.example.firma.Pilot.IshchiDto;
import com.example.firma.Service.BolimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bolim")
public class BolimController {

    @Autowired
    BolimService bolimService;

    @PostMapping("/malumotJoylash")
    public HttpEntity<?> MalumotJoylash(@RequestBody BolimDto bolimDto){
        ApiResponse apiResponse=bolimService.PostBolim(bolimDto);
        return ResponseEntity.status(apiResponse.isHolat()? HttpStatus.OK:HttpStatus.ALREADY_REPORTED).body(apiResponse.getXabar());
    }
    @PutMapping("/edit2/{id}")
    public HttpEntity<?> Edit(@PathVariable Integer id,@RequestBody BolimDto bolimDto){
        ApiResponse apiResponse=bolimService.PutBolim(id,bolimDto);
        return ResponseEntity.status(apiResponse.isHolat()? HttpStatus.OK:HttpStatus.NOT_FOUND).body(apiResponse.getXabar());
    }
    @GetMapping("/oqish/{id}")
    public HttpEntity<?> Oqish(@PathVariable Integer id){
        ApiResponse apiResponse=bolimService.GetBolim(id);
        return ResponseEntity.status(apiResponse.isHolat()? HttpStatus.OK:HttpStatus.NOT_FOUND).body(apiResponse.getXabar());
    }
    @DeleteMapping("/delete1/{id}")
    public HttpEntity<?> Delete1(@PathVariable Integer id){
        ApiResponse apiResponse=bolimService.DeleteBolim(id);
        return ResponseEntity.status(apiResponse.isHolat()? HttpStatus.OK:HttpStatus.NOT_FOUND).body(apiResponse.getXabar());
    }

}
