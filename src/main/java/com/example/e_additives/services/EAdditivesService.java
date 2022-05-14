package com.example.e_additives.services;

import com.example.e_additives.dao.EAdditivesDAO;
import com.example.e_additives.entity.EAdditive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class EAdditivesService {

    @Autowired
    private EAdditivesDAO eAdditivesDAO;

    public List<EAdditive> findAll(){
        return eAdditivesDAO.findAll();
    }

    public List<EAdditive> findSelectedAdditives(String[] indexes){
        List<EAdditive> selectedAdditives = new ArrayList<>();
        for (String index:indexes) {
            selectedAdditives.add(eAdditivesDAO.findByIndex(index));
        }
        return selectedAdditives;
    }

}
