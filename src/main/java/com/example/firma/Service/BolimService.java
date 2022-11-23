package com.example.firma.Service;

import com.example.firma.Entity.Bolim;
import com.example.firma.Entity.Firma;
import com.example.firma.Pilot.ApiResponse;
import com.example.firma.Pilot.BolimDto;
import com.example.firma.Repositary.BolimRepositary;
import com.example.firma.Repositary.FirmaRepositary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BolimService {

    @Autowired
    BolimRepositary bolimRepositary;

    @Autowired
    FirmaRepositary firmaRepositary;
    public ApiResponse PostBolim(BolimDto bolimDto) {
        Optional<Firma> byId = firmaRepositary.findById(bolimDto.getFirmaId());
        if(!byId.isPresent()) return new ApiResponse("Firma topilmadi",false);
        boolean b = bolimRepositary.existsByBolimNomiAndFirmaId(bolimDto.getBolimNomi(), bolimDto.getFirmaId());
        if(b) return new ApiResponse("Bunday bolim mavjud",false);
        Bolim bolim=new Bolim();
        bolim.setBolimNomi(bolimDto.getBolimNomi());
        bolim.setFirma(firmaRepositary.findById(bolimDto.getFirmaId()).get());
        bolimRepositary.save(bolim);
        return new ApiResponse("Malumotlar bazga joylandi!",true);

    }

    public ApiResponse PutBolim(Integer id, BolimDto bolimDto) {
        Optional<Bolim> byId = bolimRepositary.findById(id);
        if (!byId.isPresent()) return new ApiResponse("Bunday bolim mavjud emas!",false);
        Bolim bolim=byId.get();
        bolim.setBolimNomi(bolimDto.getBolimNomi());
        bolim.setFirma(firmaRepositary.findById(bolimDto.getFirmaId()).get());
        bolimRepositary.save(bolim);
        return new ApiResponse("Malumotlar tahrirlandi",true);
    }

    public ApiResponse GetBolim(Integer id) {
        Optional<Bolim> byId = bolimRepositary.findById(id);
        if(!byId.isPresent()) return new ApiResponse("Bunday id yoq",false);
        String xabar=byId.get().toString();
        return new ApiResponse(xabar,true);

    }


    public ApiResponse DeleteBolim(Integer id) {
        Optional<Bolim> byId = bolimRepositary.findById(id);
        if(!byId.isPresent()) return new ApiResponse("Bunday bo'lim mavjud emas",false);
        bolimRepositary.deleteById(id);
        return new ApiResponse("Ma'lumot o'chirildi",true);
    }
}
