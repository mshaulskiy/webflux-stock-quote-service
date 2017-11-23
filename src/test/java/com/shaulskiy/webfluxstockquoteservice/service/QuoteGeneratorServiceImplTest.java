package com.shaulskiy.webfluxstockquoteservice.service;

import com.shaulskiy.webfluxstockquoteservice.model.Quote;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;
import java.util.function.Consumer;

import static org.junit.Assert.*;

public class QuoteGeneratorServiceImplTest {

    private QuoteGeneratorServiceImpl quoteGeneratorService = new QuoteGeneratorServiceImpl();

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void fetchQuoteStream() throws Exception {
        //get quoteFlux of quotes
        Flux<Quote> quoteFlux = quoteGeneratorService.fetchQuoteStream(Duration.ofMillis(1L));
        // 22000
        quoteFlux.take(20)
                .subscribe(System.out::println);

//        Thread.sleep(1000);
    }

    @Test
    public void fetchQuoteStreamCountDown() throws Exception {

        //get quoteFlux of quotes
        Flux<Quote> quoteFlux = quoteGeneratorService.fetchQuoteStream(Duration.ofMillis(100L));

        //subscriber lambda
        Consumer<Quote> println = System.out::println;

        //error handler
        Consumer<Throwable> errorHandler = e -> System.out.println("Some Error Occurred");

        //set Countdown latch to 1
        CountDownLatch countDownLatch = new CountDownLatch(1);

        //runnable called upon complete, count down latch
        Runnable allDone = () -> countDownLatch.countDown();

        quoteFlux.take(30)
                .subscribe(println, errorHandler, allDone);

        countDownLatch.await();
    }

}