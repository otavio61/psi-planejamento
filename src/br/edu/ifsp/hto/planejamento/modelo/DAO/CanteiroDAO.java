package br.edu.ifsp.hto.planejamento.modelo.DAO;

import java.sql.*;
import java.util.*;

import br.edu.ifsp.hto.planejamento.modelo.VO.Canteiro;

public class CanteiroDAO {

    // 🔹 Inserir um novo Canteiro
    public void inserir(Canteiro c) throws SQLException {
        try{
            Connection conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/PlanejamentoProducao", "postegres", " ");
            
            String sql = "INSERT INTO canteiro (plano_especie_id, plano_id, nome, area_canteriro_m2, observacoes, kg_gerados) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, c.getPlanoEspecieId());
            stmt.setInt(2, c.getPlanoId());
            stmt.setString(3, c.getNome());
            stmt.setFloat(4, c.getAreaCanteiroM2());
            stmt.setString(5, c.getObservacoes());
            stmt.setFloat(6, c.getKgGerados());

            stmt.executeUpdate();
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    // 🔹 Listar todos os Canteiros
    public List<Canteiro> listarTodos() throws SQLException {
        List<Canteiro> lista = new ArrayList<>();
        
        try{
            Connection conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/PlanejamentoProducao", "postegres", " ");
            
            String sql = "SELECT * FROM canteiro";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Canteiro c = new Canteiro();
                c.setId(rs.getInt("id"));
                c.setPlanoEspecieId(rs.getInt("plano_especie_id"));
                c.setPlanoId(rs.getInt("plano_id"));
                c.setNome(rs.getString("nome"));
                c.setAreaCanteiroM2(rs.getFloat("area_canteriro_m2"));
                c.setObservacoes(rs.getString("observacoes"));
                c.setKgGerados(rs.getFloat("kg_gerados"));
                lista.add(c);
            }
            
            rs.close();
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        return lista;
    }

    // 🔹 Buscar Canteiro por ID
    public Canteiro buscarPorId(int id) throws SQLException {
        Canteiro c = null;

        try{
            Connection conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/PlanejamentoProducao", "postegres", " ");

            String sql = "SELECT * FROM canteiro WHERE id = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                c = new Canteiro();
                c.setId(rs.getInt("id"));
                c.setPlanoEspecieId(rs.getInt("plano_especie_id"));
                c.setPlanoId(rs.getInt("plano_id"));
                c.setNome(rs.getString("nome"));
                c.setAreaCanteiroM2(rs.getFloat("area_canteriro_m2"));
                c.setObservacoes(rs.getString("observacoes"));
                c.setKgGerados(rs.getFloat("kg_gerados"));
            }
            
            rs.close();
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        return c;
    }

    // 🔹 Atualizar Canteiro
    public void atualizar(Canteiro c) throws SQLException {
        try{
            Connection conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/PlanejamentoProducao", "postegres", " ");
            
            String sql = "UPDATE canteiro SET plano_especie_id = ?, plano_id = ?, nome = ?, area_canteriro_m2 = ?, observacoes = ?, kg_gerados = ? WHERE id = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, c.getPlanoEspecieId());
            stmt.setInt(2, c.getPlanoId());
            stmt.setString(3, c.getNome());
            stmt.setFloat(4, c.getAreaCanteiroM2());
            stmt.setString(5, c.getObservacoes());
            stmt.setFloat(6, c.getKgGerados());
            stmt.setInt(7, c.getId());

            stmt.executeUpdate();
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    // 🔹 Deletar Canteiro
    public void deletar(int id) throws SQLException {
        try{
            Connection conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/PlanejamentoProducao", "postegres", " ");
            
            String sql = "DELETE FROM canteiro WHERE id = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
