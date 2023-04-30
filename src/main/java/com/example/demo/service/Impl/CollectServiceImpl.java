package com.example.demo.service.Impl;

import com.example.demo.domain.Share;
import com.example.demo.mapper.CollectMapper;
import com.example.demo.mapper.ShareMapper;
import com.example.demo.service.CollectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CollectServiceImpl implements CollectService {
    @Resource
    CollectMapper collectMapper;

    @Resource
    ShareMapper shareMapper;

    @Override
    public List<Share> findByUserId(Long userId) {
        List<Share> res=collectMapper.findByUserId(userId);
        System.out.println(res);
        return collectMapper.findByUserId(userId);
    }

    @Override
    public void collect(Long userId, Long shareId) {
        collectMapper.collect(shareId, userId);
        shareMapper.increaseHeat(shareId);
    }

    @Override
    public void delete(Long userId, Long shareId) {
        collectMapper.delete(shareId, userId);
        shareMapper.decreaseHeat(shareId);
    }
}
