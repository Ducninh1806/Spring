package com.codegym.service.Impl;

import com.codegym.model.Province;
import com.codegym.repository.ProvinveRepository;
import com.codegym.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;

public class ProvinceServiceImpl implements ProvinceService {
    @Autowired
    private ProvinveRepository provinveRepository;

    @Override
    public Iterable<Province> findAll() {
        return provinveRepository.findAll();
    }

    @Override
    public Province findById(Long id) {
        return provinveRepository.findOne(id);
    }

    @Override
    public void save(Province province) {
        provinveRepository.save(province);
    }

    @Override
    public void remove(Long id) {
        provinveRepository.delete(id);
    }
}
