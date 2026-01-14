package com.npg.payroll.service;

import com.npg.payroll.entity.ExchangeRate;
import com.npg.payroll.repository.ExchangeRateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExchangeRateService {

    private final ExchangeRateRepository exchangeRateRepository;

    public ExchangeRate upload(ExchangeRate exchangeRate) throws Exception {
        ExchangeRate newExchangeRate = exchangeRateRepository.save(exchangeRate);
        return newExchangeRate;
    }

    public List<ExchangeRate> getAllExchangeRate() throws  Exception {
        List<ExchangeRate> exchangeRateList = exchangeRateRepository.findAll();
        return exchangeRateList;
    }

    public ExchangeRate getExchangRateByDate(String date) throws  Exception {
        ExchangeRate exchangeRate = exchangeRateRepository.findByDate(date);
        return exchangeRate;
    }

    public void deleteExchangRate(String date) throws Exception{
        ExchangeRate exchangeRate = exchangeRateRepository.findByDate(date);
        exchangeRateRepository.deleteById(exchangeRate.getId());
    }

}
