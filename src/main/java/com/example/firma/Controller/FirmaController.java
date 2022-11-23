package com.example.firma.Controller;

import com.example.firma.Entity.Firma;
import com.example.firma.Pilot.ApiResponse;
import com.example.firma.Pilot.FirmaDto;
import com.example.firma.Pilot.IshchiDto;
import com.example.firma.Repositary.FirmaRepositary;
import com.example.firma.Service.FirmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/firma")
public class FirmaController {

   @Autowired
    FirmaService firmaService;
    @PostMapping("/joylash")
    public HttpEntity<?> Joylash(@RequestBody FirmaDto firmaDto){
        ApiResponse apiResponse=firmaService.PostFirma(firmaDto);
        return ResponseEntity.status(apiResponse.isHolat()? HttpStatus.OK:HttpStatus.ALREADY_REPORTED).body(apiResponse.getXabar());

    }
    @PutMapping("/edit1/{id}")
    public HttpEntity<?> Edit(@PathVariable Integer id,@RequestBody FirmaDto firmaDto){
        ApiResponse apiResponse=firmaService.PutFirma(id,firmaDto);
        return ResponseEntity.status(apiResponse.isHolat()? HttpStatus.OK:HttpStatus.NOT_FOUND).body(apiResponse.getXabar());
    }
    @GetMapping("/oqish2/{id}")
    public HttpEntity<?> Uqish(@PathVariable Integer id){
        ApiResponse apiResponse=firmaService.GetFirma(id);
        return  ResponseEntity.status(apiResponse.isHolat()?HttpStatus.OK:HttpStatus.NOT_FOUND).body(apiResponse.getXabar());
    }
    @DeleteMapping("/delete3/{id}")
    public HttpEntity<?> Delete(@PathVariable Integer id){
        ApiResponse apiResponse=firmaService.DeleteFirma(id);
        return  ResponseEntity.status(apiResponse.isHolat()?HttpStatus.OK:HttpStatus.NOT_FOUND).body(apiResponse.getXabar());
    }

}
