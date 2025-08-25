package com.ifsp.planejamento_producao.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AreaDAO {
    public Area get(int id) throws SQLException, Exception {
        Connection con = SQL.connect();
        PreparedStatement stt = con.prepareStatement(
            "SELECT * FROM Area"
        );
        ResultSet res = stt.executeQuery();

        if (!res.next()) {
            throw new Exception("Area de id " + id + " não existe");
        }

        return new Area(
            res.getInt("id"),
            res.getString("nome"),
            res.getString("tipo_cultura"),
            res.getFloat("ph")
        );
    }
}
