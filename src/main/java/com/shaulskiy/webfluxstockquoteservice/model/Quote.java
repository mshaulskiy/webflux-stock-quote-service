package com.shaulskiy.webfluxstockquoteservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.Instant;

@Data
@NoArgsConstructor
public class Quote {
    private static final MathContext MATH_CONTEXT = new MathContext(2);

    private String ticker;
    private BigDecimal price;
    private Instant instant;

    public Quote(String ticker, BigDecimal price) {
        this.ticker = ticker;
        this.price = price;
    }

    public Quote(String ticker, double price) {
        this.ticker = ticker;
        this.price = new BigDecimal(price, MATH_CONTEXT);
    }

    public void setInstant(Instant instant) {
        this.instant = instant;
    }

    public Instant getInstant() {
        return instant;
    }

    public String getTicker() {
        return ticker;
    }

    public BigDecimal getPrice() {
        return price;
    }

}
