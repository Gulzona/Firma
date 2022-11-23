package com.example.firma.Controller;

import com.example.firma.Entity.Ishchi;
import com.example.firma.Pilot.ApiResponse;
import com.example.firma.Pilot.IshchiDto;
import com.example.firma.Service.IshchiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ishchi")
public class IshchiController {

    @Autowired
    IshchiService ishchiService;
//20031706
    @PostMapping("/kiritish")
    public HttpEntity<?> IshchiMalumotlari(@RequestBody IshchiDto ishchiDto){
        ApiResponse apiResponse=ishchiService.PostIshchi(ishchiDto);
        return ResponseEntity.status(apiResponse.isHolat()? HttpStatus.OK:HttpStatus.ALREADY_REPORTED).body(apiResponse.getXabar());

    }
    @PutMapping("/edit/{id}")
    public HttpEntity<?> Edit(@PathVariable Integer id,@RequestBody IshchiDto ishchiDto){
        ApiResponse apiResponse=ishchiService.PutIshchi(id,ishchiDto);
        return ResponseEntity.status(apiResponse.isHolat()? HttpStatus.OK:HttpStatus.NOT_FOUND).body(apiResponse.getXabar());
    }
    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> Delete(@PathVariable Integer id){
        ApiResponse apiResponse=ishchiService.DeleteIshchi(id);
        return  ResponseEntity.status(apiResponse.isHolat()?HttpStatus.OK:HttpStatus.NOT_FOUND).body(apiResponse.getXabar());
    }
    @GetMapping("/oqish1/{id}")
    public HttpEntity<?> Uqish(@PathVariable Integer id){
        ApiResponse apiResponse=ishchiService.GetIshchi(id);
        return  ResponseEntity.status(apiResponse.isHolat()?HttpStatus.OK:HttpStatus.NOT_FOUND).body(apiResponse.getXabar());
    }



}
