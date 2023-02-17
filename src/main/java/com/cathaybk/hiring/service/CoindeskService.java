package com.cathaybk.hiring.service;

import com.cathaybk.hiring.dto.ResponseDto;
import com.cathaybk.hiring.helper.CoindeskHelper;
import com.cathaybk.hiring.helper.BaseHelper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CoindeskService {

    public CoindeskService() {

    }

    public ResponseEntity<ResponseDto<CoindeskHelper.NewGetCurrentPriceRes>> newGetCurrentPrice() {
        try {
            return BaseHelper.getSuccessResponseEntity(
                    CoindeskHelper.getNewResult(Objects.requireNonNull(CoindeskHelper.getCurrentPrice().getBody())));
        } catch (Exception e) {
            e.printStackTrace();
            return BaseHelper.getFailResponseEntity(500, "unexpected fail: " + e.getMessage());
        }
    }

}
