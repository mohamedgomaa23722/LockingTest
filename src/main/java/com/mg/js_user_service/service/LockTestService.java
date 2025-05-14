package com.mg.js_user_service.service;

import com.mg.js_user_service.entity.ReqTable;
import com.mg.js_user_service.repository.ReqRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class LockTestService {

    @Autowired
    ReqRepository reqRepository;


    @Transactional
    public List<Integer> testLock() throws InterruptedException {
        log.info("testLock Started at time: {}", System.currentTimeMillis());
        List<ReqTable> pending = reqRepository.findNext2UnlockedByStatus("pending");
        log.info("{} take those entries : {} ", Thread.currentThread().getName(), pending);
        Thread.sleep(20000);
        log.info("{} try to delete those entries : {} ", Thread.currentThread().getName(), pending);
        reqRepository.deleteAll(pending);
        return pending.stream().map(ReqTable::getId).toList();
    }
}
