package com.example.e_additives.dao;

import com.example.e_additives.entity.EAdditive;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EAdditiveMapper implements RowMapper<EAdditive> {

    @Override
    public EAdditive mapRow(ResultSet rs, int rowNum) throws SQLException {
        EAdditive eAdditive = new EAdditive();
        eAdditive.setType(rs.getString("type"));
        eAdditive.setIndex(rs.getString("index"));
        eAdditive.setName(rs.getString("name"));
        eAdditive.setInformation(rs.getString("information"));
        return eAdditive;
    }
}
