package br.edu.ifsp.hto.planejamento.modelo.DAO;

import java.sql.*;
import java.util.*;

import br.edu.ifsp.hto.planejamento.modelo.VO.Plano;

public class PlanoDAO {

    // Inserir novo plano
    public void inserir(Plano plano) {
        try{
            Connection conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/PlanejamentoProducao", "postegres", " ");
            
            String sql = "INSERT INTO plano (especie_id, talhao_area_id, talhao_id, nome_plano, descricao, data_inicio, data_fim, observacoes, area_cultivo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, plano.getEspecieId());
            stmt.setInt(2, plano.getTalhaoAreaId());
            stmt.setInt(3, plano.getTalhaoId());
            stmt.setString(4, plano.getNomePlano());
            stmt.setString(5, plano.getDescricao());
            stmt.setDate(6, plano.getDataInicio());
            stmt.setDate(7, plano.getDataFim());
            stmt.setString(8, plano.getObservacoes());
            stmt.setFloat(9, plano.getAreaCultivo());
            
            stmt.executeUpdate();
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }   
    }

    // Listar todos os planos
    public List<Plano> listarTodos() {
        List<Plano> lista = new ArrayList<>();

        try{
            Connection conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/PlanejamentoProducao", "postegres", " ");

            String sql = "SELECT * FROM plano";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Plano p = new Plano();
                p.setId(rs.getInt("id"));
                p.setEspecieId(rs.getInt("especie_id"));
                p.setTalhaoAreaId(rs.getInt("talhao_area_id"));
                p.setTalhaoId(rs.getInt("talhao_id"));
                p.setNomePlano(rs.getString("nome_plano"));
                p.setDescricao(rs.getString("descricao"));
                p.setDataInicio(rs.getDate("data_inicio"));
                p.setDataFim(rs.getDate("data_fim"));
                p.setObservacoes(rs.getString("observacoes"));
                p.setAreaCultivo(rs.getFloat("area_cultivo"));
                lista.add(p);
            }
            
            rs.close();
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        } 

        return lista;
    }

    // Atualizar plano existente
    public void atualizar(Plano plano) {
        try{
            Connection conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/PlanejamentoProducao", "postegres", " ");

            String sql = "UPDATE Plano SET especie_id = ?, talhao_area_id = ?, talhao_id = ?, nome_plano = ?, descricao = ?, data_inicio = ?, data_fim = ?, observacoes = ?, area_cultivo = ? WHERE id = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, plano.getEspecieId());
            stmt.setInt(2, plano.getTalhaoAreaId());
            stmt.setInt(3, plano.getTalhaoId());
            stmt.setString(4, plano.getNomePlano());
            stmt.setString(5, plano.getDescricao());
            stmt.setDate(6, plano.getDataInicio());
            stmt.setDate(7, plano.getDataFim());
            stmt.setString(8, plano.getObservacoes());
            stmt.setFloat(9, plano.getAreaCultivo());
            stmt.setInt(10, plano.getId());
            
            stmt.executeUpdate();
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    // Deletar plano
    public void deletar(int id) {
        try{
            Connection conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/PlanejamentoProducao", "postegres", " ");

            String sql = "DELETE FROM plano WHERE id = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);

            stmt.executeUpdate();
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    // Buscar por ID
    public Plano buscarPorId(int id) {
        Plano plano = null;
        
        try{
            Connection conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/PlanejamentoProducao", "postegres", " ");
            
            String sql = "SELECT * FROM plano WHERE id = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                plano = new Plano();
                plano.setId(rs.getInt("id"));
                plano.setTalhaoId(rs.getInt("especie_id"));
                plano.setTalhaoId(rs.getInt("talhao_area_id"));
                plano.setTalhaoId(rs.getInt("talhao_id"));
                plano.setNomePlano(rs.getString("nome_plano"));
                plano.setDescricao(rs.getString("descricao"));
                plano.setDataInicio(rs.getDate("data_inicio"));
                plano.setDataFim(rs.getDate("data_fim"));
                plano.setObservacoes(rs.getString("observacoes"));
                plano.setAreaCultivo(rs.getFloat("area_cultivo"));
            }
            
            rs.close();
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return plano;
    }
}
