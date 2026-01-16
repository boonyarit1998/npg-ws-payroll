package com.npg.payroll.controller;

import com.npg.payroll.dto.UploadExchangeRateRequest;
import com.npg.payroll.entity.ExchangeRate;
import com.npg.payroll.service.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/exchange-rates")
@RequiredArgsConstructor
public class ExchangeRateController {
    private final ExchangeRateService exchangeRateService;

    @PostMapping()
    public ResponseEntity<List<ExchangeRate>> uploadExchangeRate(@RequestBody UploadExchangeRateRequest request) throws Exception{
        List<ExchangeRate> newExchangRate = exchangeRateService.uploadExchangeRate(request.getFilename(),request.getBase64Data());
        return ResponseEntity.ok().body(newExchangRate);
    }

    @GetMapping()
    public List<ExchangeRate> getAllExchangeRate() throws Exception{
        List<ExchangeRate> exchangeRateList = exchangeRateService.getAllExchangeRate();
        return exchangeRateList;
    }

    @GetMapping("/{date}")
    public List<ExchangeRate> getExchangeRateByDate(@PathVariable String date) throws Exception{
        List<ExchangeRate> exchangeRate = exchangeRateService.getExchangRateByDate(date);
        return  exchangeRate;
    }

    @DeleteMapping("/{date}")
    public void deleteExchangeRate(@PathVariable String date) throws Exception{
        exchangeRateService.deleteExchangRate(date);
    }

}
