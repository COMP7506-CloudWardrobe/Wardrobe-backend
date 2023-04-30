package com.example.demo.controller;

import com.example.demo.domain.Share;
import com.example.demo.domain.vo.ShareListVO;
import com.example.demo.service.CollectService;
import com.example.demo.service.ShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/collect")
public class CollectController {

    @Autowired
    CollectService collectService;

    @GetMapping("/get_collections")
    public ResponseEntity<ShareListVO> getCollections(@RequestParam Long userId) {
        System.out.println(userId);
        List<Share> shareList = collectService.findByUserId(userId);
        shareList = shareList == null ? new ArrayList<>() : shareList;
        return new ResponseEntity<>(new ShareListVO().setShareList(shareList), HttpStatus.OK);

    }

    @PostMapping("/collect")
    public ResponseEntity<Boolean> collect(@RequestParam Long userId, @RequestParam Long shareId) {
        collectService.collect(userId, shareId);
        return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity<Boolean> deleteShare(@RequestParam Long userId, @RequestParam Long shareId) {
        collectService.delete(userId, shareId);
        return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
    }


}
