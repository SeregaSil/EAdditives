package com.example.e_additives.dao;

import com.example.e_additives.entity.EAdditive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EAdditivesDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EAdditivesDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<EAdditive> findAllAdditives(){
        String sql = "SELECT * FROM Eadditives";
        return jdbcTemplate.query(sql, new EAdditiveMapper());
    }

    public EAdditive findAdditiveByIndex(String index){
        String sql = "SELECT * FROM Eadditives WHERE index = ?";
        return jdbcTemplate.queryForObject(sql, new EAdditiveMapper(), index);
    }
}
