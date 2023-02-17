package com.cathaybk.hiring.service;

import com.cathaybk.hiring.dto.ResponseDto;
import com.cathaybk.hiring.model.Currency;
import com.cathaybk.hiring.repository.CurrencyRepository;
import com.cathaybk.hiring.helper.BaseHelper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class CurrencyService {

    private CurrencyRepository currencyRepository;

    public CurrencyService(CurrencyRepository currencyRepository){
        this.currencyRepository = currencyRepository;
    }

    public ResponseEntity<ResponseDto<Currency>> create(Currency request) {
        try {
            Optional<Currency> currencyChk = this.currencyRepository.findById(request.getEnCode());
            if (currencyChk.isPresent()) {
                return BaseHelper.getFailResponseEntity(400, "en code is exists en code: " + request.getEnCode());
            }
            request = this.currencyRepository.save(request);
            return BaseHelper.getSuccessResponseEntity(request);

        } catch (Exception e) {
            e.printStackTrace();
            return BaseHelper.getFailResponseEntity(500, "unexpected fail: " + e.getMessage());
        }
    }

    public ResponseEntity<ResponseDto<Currency>> update(Currency request) {
        ResponseEntity<ResponseDto<Currency>> res;
        try {
            Optional<Currency> currencyChk = this.currencyRepository.findById(request.getEnCode());
            if (!currencyChk.isPresent()) {
                throw new EntityNotFoundException(String.format("currency id not found: %s", request.getEnCode()));
            }
            this.currencyRepository.save(request);
            res = BaseHelper.getSuccessResponseEntity();
        } catch (EntityNotFoundException entityNotFoundException) {
            res = BaseHelper.getFailResponseEntity(404, entityNotFoundException.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            res = BaseHelper.getFailResponseEntity(400, "unexpected fail: " + e.getMessage());
        }
        return res;
    }

    public ResponseEntity<ResponseDto<Object>> delete(String id) {
        ResponseEntity<ResponseDto<Object>> res;
        try {
            Optional<Currency> currencyChk = this.currencyRepository.findById(id);
            if (!currencyChk.isPresent()) {
                throw new EntityNotFoundException(String.format("currency code not found: %s", id));
            }
            this.currencyRepository.deleteById(id);
            res = BaseHelper.getSuccessResponseEntity();
        } catch (EntityNotFoundException entityNotFoundException) {
            res = BaseHelper.getFailResponseEntity(400, entityNotFoundException.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            res = BaseHelper.getFailResponseEntity(400, "unexpected fail: " + e.getMessage());
        }
        return res;
    }

    public ResponseEntity<ResponseDto<List<Currency>>> findAll() {
        return BaseHelper.getSuccessResponseEntity(currencyRepository.findAll());
    }

    public ResponseEntity<ResponseDto<Currency>> findById(String id) {
        ResponseEntity<ResponseDto<Currency>> res;
        Optional<Currency> currencyChk = currencyRepository.findById(id);
        if (!currencyChk.isPresent()) {
            res = BaseHelper.getFailResponseEntity(404, "id not found: " + id);
        } else {
            res = BaseHelper.getSuccessResponseEntity(currencyChk.get());
        }
        return res;
    }

}
