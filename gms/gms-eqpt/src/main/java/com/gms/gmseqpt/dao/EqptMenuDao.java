package com.gms.gmseqpt.dao;

import com.gms.commons.domain.EqptMenu;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EqptMenuDao {
    public List<EqptMenu> getEqptMenus();
}
