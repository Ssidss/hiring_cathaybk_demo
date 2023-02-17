package com.cathaybk.hiring.model;

import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity(name = "currency")
@Table(name = "currency")
public class Currency {

    @Id
    @Column(name = "en_code", length = 10)
    @NotNull(message = "en_code must not be null")
    @NotBlank(message = "en_code must not be empty")
    private String enCode;
    @Column(length = 20)
    private String zh;
    @Column(length = 32)
    private String symbol;
    @NotNull(message = "rate must not be empty")
    private Float rate;
    private String description;

    public String getZh() {
        return zh;
    }

    public Currency setZh(String zh) {
        this.zh = zh;
        return this;
    }

    public String getEnCode() {
        return enCode;
    }

    public Currency setEnCode(String enCode) {
        this.enCode = enCode;
        return this;
    }

    public String getSymbol() {
        return symbol;
    }

    public Currency setSymbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    public Float getRate() {
        return rate;
    }

    public Currency setRate(Float rate) {
        this.rate = rate;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Currency setDescription(String description) {
        this.description = description;
        return this;
    }
}
