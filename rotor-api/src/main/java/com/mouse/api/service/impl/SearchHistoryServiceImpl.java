package com.mouse.api.service.impl;

import com.mouse.api.commons.enums.RefererEnum;
import com.mouse.api.service.SearchHistoryService;
import com.mouse.dao.entity.sys.SearchHistoryEntity;
import com.mouse.dao.repository.sys.SearchHistoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-12-21
 */
@Slf4j
@Service
public class SearchHistoryServiceImpl implements SearchHistoryService {
    @Autowired
    SearchHistoryRepository searchHistoryRepository;

    @Async
    @Override
    public void asyncSave(Integer userId, String keyword, RefererEnum refererEnum) {
        SearchHistoryEntity searchHistoryVo = new SearchHistoryEntity();
        searchHistoryVo.setKeyword(keyword);
        searchHistoryVo.setUserId(userId);
        searchHistoryVo.setFrom(refererEnum.getDesc());
        searchHistoryVo.setDeleted(false);
        searchHistoryRepository.save(searchHistoryVo);
    }
}