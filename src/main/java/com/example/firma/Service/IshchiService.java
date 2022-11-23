package com.example.firma.Service;

import com.example.firma.Entity.Bolim;
import com.example.firma.Entity.Ishchi;
import com.example.firma.Entity.Manzil;
import com.example.firma.Pilot.ApiResponse;
import com.example.firma.Pilot.IshchiDto;
import com.example.firma.Repositary.BolimRepositary;
import com.example.firma.Repositary.IshchiRepositary;
import com.example.firma.Repositary.ManzilRepositary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

@Service
public class IshchiService {
     @Autowired
    IshchiRepositary ishchiRepositary;
     @Autowired
    BolimRepositary bolimRepositary;
     @Autowired
    ManzilRepositary manzilRepositary;


    public ApiResponse PostIshchi(IshchiDto ishchiDto) {
        boolean b = ishchiRepositary.existsByEmail(ishchiDto.getEmail());
        if (b) return new ApiResponse("Bunday ishchi allaqachon mavjud",false);
        Optional<Bolim> byId = bolimRepositary.findById(ishchiDto.getBolimId());
        if(!byId.isPresent()) return new ApiResponse("Bunday bolim topilmadi",false);
        Ishchi ishchi=new Ishchi();
        ishchi.setIsm(ishchiDto.getIsm());
        ishchi.setFamilya(ishchiDto.getFamilya());
        ishchi.setTelRaqam(ishchiDto.getTelRaqam());
        ishchi.setEmail(ishchiDto.getEmail());
        ishchi.setBolim(bolimRepositary.findById(ishchiDto.getBolimId()).get());
        Manzil manzil=new Manzil();
        manzil.setViloyat(ishchiDto.getViloyat());
        manzil.setTuman(ishchiDto.getTuman());
        manzil.setKocha(ishchiDto.getKocha());
        manzil.setUyRaqami(ishchiDto.getUyRaqami());
        manzilRepositary.save(manzil);
        ishchi.setManzil(manzil);
        ishchiRepositary.save(ishchi);
        return new ApiResponse("Malumotlar bazaga joylandi!",true);
    }

    public ApiResponse PutIshchi(Integer id, IshchiDto ishchiDto) {
        Optional<Ishchi> byId = ishchiRepositary.findById(id);
        if(!byId.isPresent()) return new ApiResponse("Bunday ishchi topilmadi",false);
        Ishchi ishchi=byId.get();
        ishchi.setIsm(ishchiDto.getIsm());
        ishchi.setFamilya(ishchiDto.getFamilya());
        ishchi.setTelRaqam(ishchiDto.getTelRaqam());
        ishchi.setEmail(ishchiDto.getEmail());
        ishchi.setBolim(bolimRepositary.findById(ishchiDto.getBolimId()).get());
        Optional<Manzil> byId1 = manzilRepositary.findById(id);
        Manzil manzil=byId1.get();
        manzil.setViloyat(ishchiDto.getViloyat());
        manzil.setTuman(ishchiDto.getTuman());
        manzil.setKocha(ishchiDto.getKocha());
        manzil.setUyRaqami(ishchiDto.getUyRaqami());
        manzilRepositary.save(manzil);
        ishchi.setManzil(manzil);
        ishchiRepositary.save(ishchi);
        return  new ApiResponse("Malumotlar tahrirlandi!",true);
    }


    public ApiResponse DeleteIshchi(Integer id) {
        Optional<Ishchi> byId = ishchiRepositary.findById(id);
        if(!byId.isPresent()) return new ApiResponse("Bunday ishchi topilmadi",false);
        ishchiRepositary.deleteById(id);
        Manzil manzil=byId.get().getManzil();
        manzilRepositary.deleteById(manzil.getId());
        return new ApiResponse("Ma'lumotlar o'chirildi!",true);

    }

    public ApiResponse GetIshchi(Integer id) {
        Optional<Ishchi> byId = ishchiRepositary.findById(id);
        if(!byId.isPresent()) return new ApiResponse("Bunday id yoq",false);
        String xabar=byId.get().toString();
        return new ApiResponse(xabar,true);
    }
}
