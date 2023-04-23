package com.example.demo.controller;

import com.example.demo.common.Common;
import com.example.demo.config.StorageConfig;
import com.example.demo.domain.Clothes;
import com.example.demo.domain.Suit;
import com.example.demo.domain.vo.ClothesWardrobeVO;
import com.example.demo.domain.vo.SuitWardrobeVO;
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
                .setOutwears(typeClothesList.get(2))
                .setShoes(typeClothesList.get(3))
                .setAccessories(typeClothesList.get(4));
        return new ResponseEntity<>(clothesVO, HttpStatus.OK);

    }

    @GetMapping("/get_all_suits")
    public ResponseEntity<SuitWardrobeVO> getAllSuits(@RequestParam int id) {
        List<Suit> suitList = suitService.getSuits(id);
        return new ResponseEntity<>(new SuitWardrobeVO().setSuitList(suitList), HttpStatus.OK);
    }

    @PostMapping("/upload_clothes")
    @Transactional
    public ResponseEntity<Clothes> uploadClothes(@RequestParam MultipartFile image,
                                                 @RequestParam Long userId,
                                                 @RequestParam Integer type) throws IOException {
        Clothes newClothes = clothesService.uploadClothes(new Clothes()
                .setType(type)
                .setUserId(userId));

        Path path = Paths.get(storageConfig.getBasePath())
                .resolve(Common.USER + userId)
                .resolve(Common.CLOTHES_DIR)
                .resolve(newClothes.getClothesId() + Common.JPG);

        if (imageService.saveImage(path, image)) return new ResponseEntity<>(newClothes, HttpStatus.OK);
        else return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/upload_suit")
    public ResponseEntity<Suit> uploadSuit(@RequestParam MultipartFile image,
                                           @RequestParam Long userId,
                                           @RequestParam(required = false) Long topId,
                                           @RequestParam(required = false) Long bottomId,
                                           @RequestParam(required = false) Long outwearId,
                                           @RequestParam(required = false) Long shoesId,
                                           @RequestParam(required = false) Long accessoryId1,
                                           @RequestParam(required = false) Long accessoryId2) throws IOException {

        Suit newSuit = suitService.uploadSuit(new Suit()
                .setUserId(userId)
                .setTopId(topId)
                .setBottomId(bottomId)
                .setOutwearId(outwearId)
                .setShoesId(shoesId)
                .setAccessoryId1(accessoryId1)
                .setAccessoryId2(accessoryId2));
        Path path = Paths.get(storageConfig.getBasePath())
                .resolve(Common.USER + userId)
                .resolve(Common.SUIT_DIR)
                .resolve(newSuit.getSuitId() + Common.JPG);
        if (imageService.saveImage(path, image)) return new ResponseEntity<>(newSuit, HttpStatus.OK);
        else return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/delete_suit")
    public ResponseEntity<Boolean> deleteSuit(@RequestParam Long suitId, @RequestParam Long userId) throws IOException {
        Path path = Paths.get(storageConfig.getBasePath())
                .resolve(Common.USER + userId)
                .resolve(Common.SUIT_DIR)
                .resolve(suitId + Common.JPG);
        if (suitService.deleteSuit(suitId, userId) > 0)
            if (imageService.deleteImage(path))
                return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
        return new ResponseEntity<>(Boolean.FALSE, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/delete_clothes")
    public ResponseEntity<Boolean> deleteClothes(@RequestParam Long clothesId, @RequestParam Long userId) throws IOException {
        Path path = Paths.get(storageConfig.getBasePath())
                .resolve(Common.USER + userId)
                .resolve(Common.CLOTHES_DIR)
                .resolve(clothesId + Common.JPG);

        if (clothesService.deleteClothes(clothesId, userId) > 0)
            if (imageService.deleteImage(path))
                return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
        return new ResponseEntity<>(Boolean.FALSE, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
