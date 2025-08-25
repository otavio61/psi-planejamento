package com.ifsp.planejamento_producao.controller;

import com.ifsp.planejamento_producao.model.Area;
import com.ifsp.planejamento_producao.model.AreaDAO;

public class AreaController {
    private AreaDAO areaDAO = new AreaDAO();

    public Area get(int id) throws Exception {
        return areaDAO.get(id);
    }

    public void insert(Area a) throws Exception {
        areaDAO.insert(a);
    }
}
