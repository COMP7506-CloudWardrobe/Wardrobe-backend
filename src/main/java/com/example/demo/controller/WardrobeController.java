package com.example.demo.controller;

import com.example.demo.domain.Clothes;
import com.example.demo.domain.Suit;
import com.example.demo.domain.vo.ClothesVO;
import com.example.demo.service.ClothesService;
import com.example.demo.service.SuitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/wardrobe")
public class WardrobeController {

    @Autowired
    ClothesService clothesService;

    @Autowired
    SuitService suitService;


    @PostMapping("/clothes")
    public ResponseEntity<ClothesVO> getAllClothes(@RequestParam int id) {
        List<List<Clothes>> typeClothesList = clothesService.getClothes(id);
        ClothesVO clothesVO = new ClothesVO()
                .setTops(typeClothesList.get(0))
                .setBottoms(typeClothesList.get((1)))
                .setOne_pieces(typeClothesList.get(2))
                .setShoes(typeClothesList.get(3))
                .setAccessories(typeClothesList.get(4));
        return new ResponseEntity<>(clothesVO, HttpStatus.OK);

    }

    @PostMapping("/suits")
    public ResponseEntity<List<Suit>> getAllSuits(@RequestParam int id) {
        return new ResponseEntity<>(suitService.getSuits(id), HttpStatus.OK);
    }

}
