package com.ifsp.planejamento_producao;

import com.ifsp.planejamento_producao.model.Area;
import com.ifsp.planejamento_producao.model.AreaDAO;

public class App {
    public static void main(String[] args) {
        try {
            AreaDAO areaDAO = new AreaDAO();
            areaDAO.insert(new Area(
                "para plantar feijão",
                "feijão",
                7
            ));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}