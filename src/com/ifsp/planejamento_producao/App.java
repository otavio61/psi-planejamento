package com.ifsp.planejamento_producao;

import com.ifsp.planejamento_producao.model.Area;
import com.ifsp.planejamento_producao.model.AreaDAO;

public class App {
    public static void main(String[] args) {
        try {
            AreaDAO dao = new AreaDAO();
            Area a = dao.get(1);
            System.err.println(a.getNome());


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}