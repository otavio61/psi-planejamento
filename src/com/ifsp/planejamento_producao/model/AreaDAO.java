package com.ifsp.planejamento_producao.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AreaDAO {
    public Area get(int id) throws SQLException, Exception {
        Connection con = null;
        PreparedStatement stt = null;
        ResultSet res = null;
        
        try {
            con = SQL.connect();
            stt = con.prepareStatement(
                "SELECT * FROM Area"
            );
            res = stt.executeQuery();

            if (!res.next()) {
                throw new Exception("Area de id " + id + " não existe");
            }

            return new Area(
                res.getInt("id"),
                res.getString("nome"),
                res.getString("tipo_cultura"),
                res.getFloat("ph")
            );

        } catch (Exception e) {
            e.printStackTrace();
            throw e;

        } finally {
            if (con != null) con.close();
            if (stt != null) stt.close();
            if (res != null) res.close();
        }
    }

    public void insert(Area a) throws SQLException, Exception {
        Connection con = null; 
        PreparedStatement stt = null;

        try {
            con = SQL.connect();
            stt = con.prepareStatement(
                "INSERT INTO Area (nome, tipo_cultura, ph)" +
                "VALUES (?, ?, ?)"
            );

            stt.setString(1, a.getNome());
            stt.setString(2, a.getTipoCultura());
            stt.setFloat(3, a.getPh());

            stt.execute();

        } catch (Exception e) {
            e.printStackTrace();
            throw e;

        } finally {
            if (con != null) con.close();
            if (stt != null) stt.close();
        }
    }
}
