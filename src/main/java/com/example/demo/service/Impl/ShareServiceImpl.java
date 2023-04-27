package com.example.demo.service.Impl;

import com.example.demo.domain.Share;
import com.example.demo.mapper.ShareMapper;
import com.example.demo.service.ShareService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ShareServiceImpl implements ShareService {

    @Resource
    ShareMapper shareMapper;

    @Override
    public List<Share> getAll() {
        return shareMapper.getAll();
    }

    @Override
    public List<Share> findByUserId(Long userId) {
        return shareMapper.findByUserId(userId);
    }

    @Override
    public Share shareSuit(Long userId, Long suitId) {
        Share newShare = new Share().setUserId(userId).setSuitId(suitId);
        shareMapper.shareSuit(newShare);
        Long id = newShare.getShareId();
        return shareMapper.findByShareId(id);
    }

    @Override
    public void deleteShare(Long shareId) {
        shareMapper.deleteShare(shareId);
    }
}
