package com.cathaybk.hiring.controller;

import com.cathaybk.hiring.dto.ResponseDto;
import com.cathaybk.hiring.helper.CoindeskHelper;
import com.cathaybk.hiring.service.CoindeskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/coindesk")
public class CoindeskController {

    private CoindeskService coindeskService;

    public CoindeskController(CoindeskService coindeskService) {
        this.coindeskService = coindeskService;
    }

    @GetMapping("/newGetCurrentPrice")
    public ResponseEntity<ResponseDto<CoindeskHelper.NewGetCurrentPriceRes>> newGetCurrentPrice() {
        return this.coindeskService.newGetCurrentPrice();
    }

}
