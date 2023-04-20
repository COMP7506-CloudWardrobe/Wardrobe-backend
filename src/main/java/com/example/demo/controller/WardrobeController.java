package com.example.demo.controller;

import com.example.demo.common.Common;
import com.example.demo.config.StorageConfig;
import com.example.demo.domain.Clothes;
import com.example.demo.domain.Suit;
import com.example.demo.domain.vo.ClothesWardrobeVO;
import com.example.demo.service.ClothesService;
import com.example.demo.service.ImageService;
import com.example.demo.service.SuitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/wardrobe")
public class WardrobeController {

    @Autowired
    StorageConfig storageConfig;

    @Autowired
    ClothesService clothesService;

    @Autowired
    ImageService imageService;

    @Autowired
    SuitService suitService;


    @GetMapping("/get_all_clothes")
    public ResponseEntity<ClothesWardrobeVO> getAllClothes(@RequestParam int id) {
        List<List<Clothes>> typeClothesList = clothesService.getClothes(id);
        ClothesWardrobeVO clothesVO = new ClothesWardrobeVO()
                .setTops(typeClothesList.get(0))
                .setBottoms(typeClothesList.get((1)))
                .setOne_pieces(typeClothesList.get(2))
                .setShoes(typeClothesList.get(3))
                .setAccessories(typeClothesList.get(4));
        return new ResponseEntity<>(clothesVO, HttpStatus.OK);

    }

    @GetMapping("/get_all_suits")
    public ResponseEntity<List<Suit>> getAllSuits(@RequestParam int id) {
        return new ResponseEntity<>(suitService.getSuits(id), HttpStatus.OK);
    }

    @PostMapping("/upload_clothes")
    @Transactional
    public ResponseEntity<Boolean> uploadClothes(@RequestParam MultipartFile image,
                                                 @RequestParam Long userId,
                                                 @RequestParam Integer type) throws IOException {
        Long clothesId = clothesService.uploadClothes(new Clothes()
                .setType(type)
                .setUserId(userId));

        Path path = Paths.get(storageConfig.getBasePath())
                .resolve(Common.USER + userId)
                .resolve(Common.CLOTHES_DIR)
//                .resolve(Common.CLOTHES_TYPES.get(type))/
                .resolve(clothesId + Common.JPG);

        if (imageService.saveImage(path, image)) return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
        else return new ResponseEntity<>(Boolean.FALSE, HttpStatus.OK);
    }

    @PostMapping("/upload_suit")
    public ResponseEntity<Boolean> uploadSuit(@RequestParam MultipartFile image,
                                              @RequestParam Long userId,
                                              @RequestParam(required = false) Long topId,
                                              @RequestParam(required = false) Long bottomId,
                                              @RequestParam(required = false) Long onePieceId,
                                              @RequestParam(required = false) Long shoesId,
                                              @RequestParam(required = false) Long accessoryId) throws IOException {

        Long suitId = suitService.uploadSuit(new Suit()
                .setUserId(userId)
                .setTopId(topId)
                .setBottomId(bottomId)
                .setOnePieceId(onePieceId)
                .setShoesId(shoesId)
                .setAccessoryId(accessoryId));
        Path path = Paths.get(storageConfig.getBasePath())
                .resolve(Common.USER + userId)
                .resolve(Common.SUIT_DIR)
                .resolve(suitId + Common.JPG);
        if (imageService.saveImage(path, image)) return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
        else return new ResponseEntity<>(Boolean.FALSE, HttpStatus.OK);
    }

    @GetMapping("/delete_suit")
    public ResponseEntity<Boolean> deleteSuit(@RequestParam Long suitId, @RequestParam Long userId) {
        return new ResponseEntity<>(suitService.deleteSuit(suitId, userId) > 0, HttpStatus.OK);
    }

    @GetMapping("/delete_clothes")
    public ResponseEntity<Boolean> deleteClothes(@RequestParam Long clothesId, @RequestParam Long userId) {
        return new ResponseEntity<>(clothesService.deleteClothes(clothesId, userId) > 0, HttpStatus.OK);
    }

}
