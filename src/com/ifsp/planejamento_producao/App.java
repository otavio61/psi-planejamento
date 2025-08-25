package com.ifsp.planejamento_producao;

import com.ifsp.planejamento_producao.controller.AreaController;
import com.ifsp.planejamento_producao.model.Area;

public class App {
    public static void main(String[] args) {
        try {
            AreaController controller = new AreaController();
            controller.insert(new Area(
                "para plantar feijão",
                "feijão",
                7
            ));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}