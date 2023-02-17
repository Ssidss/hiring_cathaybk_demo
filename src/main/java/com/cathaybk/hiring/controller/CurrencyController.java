package com.cathaybk.hiring.controller;

import com.cathaybk.hiring.dto.ResponseDto;
import com.cathaybk.hiring.model.Currency;
import com.cathaybk.hiring.service.CurrencyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/currency")
public class CurrencyController {

    private CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @PostMapping("")
    public ResponseEntity<ResponseDto<Currency>> create(@Valid @RequestBody Currency currency) {
        return this.currencyService.create(currency);
    }

    @PutMapping("")
    public ResponseEntity<ResponseDto<Currency>> update(@Valid @RequestBody Currency request) {
        return this.currencyService.update(request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto<Object>> delete(@PathVariable String id) {
        return this.currencyService.delete(id);
    }

    @GetMapping("")
    public ResponseEntity<ResponseDto<List<Currency>>> findAll() {
        return this.currencyService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<Currency>> findById(@PathVariable String id) {
        return this.currencyService.findById(id);
    }

}
