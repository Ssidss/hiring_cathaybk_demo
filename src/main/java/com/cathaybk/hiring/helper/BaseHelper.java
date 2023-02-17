package com.cathaybk.hiring.helper;

import com.cathaybk.hiring.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BaseHelper {

    public static <T> ResponseEntity<ResponseDto<T>> getFailResponseEntity(Integer code, String msg) {
        return getResponseEntity(0, code, msg, null);
    }

    public static <T> ResponseEntity<ResponseDto<T>> getSuccessResponseEntity(T body) {
        return getResponseEntity(1, 200, "", body);
    }
    public static <T> ResponseEntity<ResponseDto<T>> getSuccessResponseEntity() {
        return getResponseEntity(1, 200, "", null);
    }
    public static <T> ResponseEntity<ResponseDto<T>> getResponseEntity(Integer ok, Integer code, String msg, T body) {
        return new ResponseEntity<>(new ResponseDto<T>()
                .setOk(ok)
                .setCode(code)
                .setMsg(msg)
                .setData(body), HttpStatus.valueOf(code));
    }


}
