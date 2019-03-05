package com.goodstuff.mall.db.service;

import com.goodstuff.mall.db.dao.LitemallSystemMapper;
import com.goodstuff.mall.db.domain.LitemallSystem;
import com.goodstuff.mall.db.domain.LitemallSystemExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LitemallSystemConfigService {
    @Resource
    private LitemallSystemMapper systemMapper;

    public List<LitemallSystem> queryAll() {
        LitemallSystemExample example = new LitemallSystemExample();
        example.or();
        return systemMapper.selectByExample(example);
    }
}
