package com.example.demo.controller;

import com.example.demo.domain.Clothes;
import com.example.demo.domain.Share;
import com.example.demo.domain.vo.ClothesWardrobeVO;
import com.example.demo.domain.vo.ShareListVO;
import com.example.demo.service.ShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/share")
public class ShareController {

    @Autowired
    ShareService shareService;

    @GetMapping("/get_all_shares")
    public ResponseEntity<ShareListVO> getAllShares() {
        List<Share> shareList = shareService.getAll();
        return new ResponseEntity<>(new ShareListVO().setShareList(shareList), HttpStatus.OK);

    }

    @GetMapping("/get_user_shares")
    public ResponseEntity<ShareListVO> getSharesByUser(@RequestParam Long userId) {
        List<Share> shareList = shareService.findByUserId(userId);
        return new ResponseEntity<>(new ShareListVO().setShareList(shareList), HttpStatus.OK);

    }

    @PostMapping("/share_suit")
    public ResponseEntity<Share> shareSuit(@RequestParam Long userId, @RequestParam Long suitId) {
        return new ResponseEntity<>(shareService.shareSuit(userId, suitId), HttpStatus.OK);
    }

    @PostMapping("/delete_share")
    public ResponseEntity<Boolean> deleteShare(@RequestParam Long shareId) {
        shareService.deleteShare(shareId);
        return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
    }


}
