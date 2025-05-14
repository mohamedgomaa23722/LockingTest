package com.mg.js_user_service.controller;

import com.mg.js_user_service.entity.ReqTable;
import com.mg.js_user_service.repository.ReqRepository;
import com.mg.js_user_service.service.LockTestService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

@RestController
@RequiredArgsConstructor
public class LockingController {

    private final LockTestService lockTestService;
    private final ReqRepository reqRepository;
    private Set<Integer> ids = new HashSet<>();

    @PostMapping
    @RequestMapping("/add/{count}")
    public void add(@PathVariable int count) {
        for (int i = 0; i < count; i++) {
            reqRepository.save(new ReqTable("username"+i, "pending"));
        }
    }
    @PostMapping
    @RequestMapping("/testLock/{threadCount}")
    public void post(@PathVariable int threadCount) throws InterruptedException {

        final CountDownLatch startLatch = new CountDownLatch(1); // Single release
        final CountDownLatch readyLatch = new CountDownLatch(threadCount); // All threads ready

        List<Thread> threads = getThreads(readyLatch, startLatch, threadCount);
//        // Wait for all threads to complete (optional)
//        for (Thread thread : threads) {
//            thread.join();
//        }
        System.out.println("\n All ids for : " +ids.stream().sorted());
        System.out.println("is lock working fine : " + (ids.size() == 60));
        try {
            // Wait until all threads are ready
            readyLatch.await();

            // Release all threads at once
            startLatch.countDown();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }

    private List<Thread> getThreads(CountDownLatch readyLatch, CountDownLatch startLatch, int threadCount) {
        Runnable runnable = () -> {
            try {
                readyLatch.countDown();

                startLatch.await();

                List<Integer> integers = lockTestService.testLock();
                ids.addAll(integers);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        };

        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < threadCount; i++) {
            Thread thread = new Thread(runnable);
            threads.add(thread);
            thread.start();
        }
        return threads;
    }
}
