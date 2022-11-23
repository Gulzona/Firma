package com.example.firma.Service;

import com.example.firma.Entity.Firma;
import com.example.firma.Entity.Ishchi;
import com.example.firma.Entity.Manzil;
import com.example.firma.Pilot.ApiResponse;
import com.example.firma.Pilot.FirmaDto;
import com.example.firma.Repositary.FirmaRepositary;
import com.example.firma.Repositary.ManzilRepositary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FirmaService {

    @Autowired
    FirmaRepositary firmaRepositary;

    @Autowired
    ManzilRepositary manzilRepositary;

    public ApiResponse PostFirma(FirmaDto firmaDto){

        boolean b = firmaRepositary.existsByFirmaNomi(firmaDto.getFirmaNomi());
        if(b) return  new ApiResponse("Bunday firma allaqachon mavjud",false);
        boolean b1 = manzilRepositary.existsByUyRaqami(firmaDto.getUyRaqami());
        if(b1) return new ApiResponse("Bunday manzilli firma mavjud",false);
        Firma firma=new Firma();
        firma.setFirmaNomi(firmaDto.getFirmaNomi());
        Manzil manzil=new Manzil();
        manzil.setViloyat(firmaDto.getViloyat());
        manzil.setTuman(firmaDto.getTuman());
        manzil.setKocha(firmaDto.getKocha());
        manzil.setUyRaqami(firmaDto.getUyRaqami());
        manzilRepositary.save(manzil);
        firma.setManzil(manzil);
        firmaRepositary.save(firma);
        return new ApiResponse("Malumotlar bazaga joylandi!",true);



    }

    public ApiResponse PutFirma(Integer id, FirmaDto firmaDto) {
        Optional<Firma> byId = firmaRepositary.findById(id);
        if(!byId.isPresent()) return new ApiResponse("Bunday firma mavjud emas!",false);
        Firma firma=byId.get();
        firma.setFirmaNomi(firmaDto.getFirmaNomi());
        Optional<Manzil> byId1 = manzilRepositary.findById(id);
        Manzil manzil=byId1.get();
        manzil.setViloyat(firmaDto.getViloyat());
        manzil.setTuman(firmaDto.getTuman());
        manzil.setKocha(firmaDto.getKocha());
        manzil.setUyRaqami(firmaDto.getUyRaqami());
        manzilRepositary.save(manzil);
        firma.setManzil(manzil);
        firmaRepositary.save(firma);
        return new ApiResponse("Ma'lumotlar tahrirlandi!",true);
    }


    public ApiResponse GetFirma(Integer id) {
        Optional<Firma> byId = firmaRepositary.findById(id);
        if(!byId.isPresent()) return new ApiResponse("Bunday id yoq",false);
        String xabar=byId.get().toString();
        return new ApiResponse(xabar,true);
    }

    public ApiResponse DeleteFirma(Integer id) {
        Optional<Firma> byId = firmaRepositary.findById(id);
        if(!byId.isPresent()) return new ApiResponse("Bunday ishchi topilmadi",false);
        firmaRepositary.deleteById(id);
        Manzil manzil=byId.get().getManzil();
        manzilRepositary.deleteById(manzil.getId());
        return new ApiResponse("Ma'lumotlar o'chirildi!",true);
    }
}
