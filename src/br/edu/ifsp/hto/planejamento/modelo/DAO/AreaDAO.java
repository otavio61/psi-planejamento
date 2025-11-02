package br.edu.ifsp.hto.planejamento.modelo.DAO;

import java.sql.*;
import java.util.*;

import br.edu.ifsp.hto.planejamento.modelo.VO.Area;

public class AreaDAO {

    // Inserir nova área
    public void inserir(Area area) {
        try {
            Connection conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/PlanejamentoProducao", "postegres", " ");

            String sql = "INSERT INTO area (associado_id, nome, area_total, area_utilizada, ph, area_m2) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, area.getAssociadoId());
            stmt.setString(2, area.getNome());
            stmt.setFloat(3, area.getAreaTotal());
            stmt.setFloat(4, area.getAreaUtilizada());
            stmt.setFloat(5, area.getPh());
            stmt.setFloat(6, area.getAreaM2());
            stmt.executeUpdate();
            stmt.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Listar todas as áreas
    public List<Area> listarTodas(){
        List<Area> lista = new ArrayList<>();

        try {
            Connection conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/PlanejamentoProducao", "postegres", " ");

            String sql = "SELECT * FROM area";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Area a = new Area();
                a.setId(rs.getInt("id"));
                a.setAssociadoId(rs.getInt("associado_id"));
                a.setNome(rs.getString("nome"));
                a.setAreaTotal(rs.getFloat("area_total"));
                a.setAreaUtilizada(rs.getFloat("area_utilizada"));
                a.setPh(rs.getFloat("ph"));
                a.setAreaM2(rs.getFloat("area_m2"));
                lista.add(a);
            }

            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    // Atualizar (editar) área existente
    public void atualizar(Area area){

        try {
            Connection conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/PlanejamentoProducao", "postegres", " ");

            String sql = "UPDATE area SET associado_id = ?, nome = ?, area_total = ?, area_utilizada = ?, ph = ?, area_m2 = ? WHERE id = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, area.getAssociadoId());
            stmt.setString(2, area.getNome());
            stmt.setFloat(3, area.getAreaTotal());
            stmt.setFloat(4, area.getAreaUtilizada());
            stmt.setFloat(5, area.getPh());
            stmt.setFloat(6, area.getAreaM2());
            stmt.setInt(7, area.getId());

            stmt.executeUpdate();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Deletar área
    public void deletar(int id) {

        try {
            Connection conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/PlanejamentoProducao", "postegres", " ");

            String sql = "DELETE FROM area WHERE id = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Buscar área por ID
    public Area buscarPorId(int id){
        Area area = null;

        try {
            Connection conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/PlanejamentoProducao", "postegres", " ");

            String sql = "SELECT * FROM area WHERE id = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            // faz a troca do banco do id pelo id do parametro
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            // cria um objeto area nulo

            // verifica se tem resultado
            if (rs.next()) {
                // se tiver, instancia o objeto area e preenche com os dados do resultado
                area = new Area();
                area.setId(rs.getInt("id"));
                area.setAssociadoId(rs.getInt("associado_id"));
                area.setNome(rs.getString("nome"));
                area.setAreaTotal(rs.getFloat("area_total"));
                area.setAreaUtilizada(rs.getFloat("area_utilizada"));
                area.setPh(rs.getFloat("ph"));
                area.setAreaM2(rs.getFloat("area_m2"));
            }

            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return area;
    }
}
