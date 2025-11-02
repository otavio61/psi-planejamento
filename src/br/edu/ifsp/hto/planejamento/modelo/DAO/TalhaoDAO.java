package br.edu.ifsp.hto.planejamento.modelo.DAO;

import java.sql.*;
import java.util.*;

import br.edu.ifsp.hto.planejamento.modelo.VO.Talhao;

public class TalhaoDAO {

    // Inserir novo Talhão
    public void inserir(Talhao talhao)  {
        try{
            Connection conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/PlanejamentoProducao", "postegres", " ");

            String sql = "INSERT INTO talhao (area_id, nome, area_talhao, observacoes, status_2) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, talhao.getAreaId());
            stmt.setString(2, talhao.getNome());
            stmt.setFloat(3, talhao.getAreaTalhao());
            stmt.setString(4, talhao.getObservacoes());
            stmt.setString(5, talhao.getStatus2());
            
            stmt.executeUpdate();
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    // Listar todos os Talhões
    public List<Talhao> listarTodos()  {
        List<Talhao> lista = new ArrayList<>();

        try{
            Connection conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/PlanejamentoProducao", "postegres", " ");

            String sql = "SELECT * FROM talhao";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Talhao t = new Talhao();
                t.setId(rs.getInt("id"));
                t.setAreaId(rs.getInt("area_id"));
                t.setNome(rs.getString("nome"));
                t.setAreaTalhao(rs.getFloat("area_talhao"));
                t.setObservacoes(rs.getString("observacoes"));
                t.setStatus2(rs.getString("status_2"));
                lista.add(t);
            }
            
            rs.close();
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        return lista;
    }
        
    // Atualizar Talhão existente
    public void atualizar(Talhao talhao)  {
        try{
            Connection conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/PlanejamentoProducao", "postegres", " ");

            String sql = "UPDATE talhao SET area_id = ?, nome = ?, area_talhao = ?, observacoes = ?, status_2 = ? WHERE id = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, talhao.getAreaId());
            stmt.setString(2, talhao.getNome());
            stmt.setFloat(3, talhao.getAreaTalhao());
            stmt.setString(4, talhao.getObservacoes());
            stmt.setString(5, talhao.getStatus2());
            stmt.setInt(6, talhao.getId());
            
            stmt.executeUpdate();
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        } 
    }

    // Buscar Talhão por ID
    public Talhao buscarPorId(int id)  {
        Talhao talhao = null;
        
        try{
            Connection conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/PlanejamentoProducao", "postegres", " ");

            String sql = "SELECT * FROM talhao WHERE id = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) { // se encontrou algum registro
                talhao = new Talhao();
                talhao.setId(rs.getInt("id"));
                talhao.setAreaId(rs.getInt("area_id"));
                talhao.setNome(rs.getString("nome"));
                talhao.setAreaTalhao(rs.getFloat("area_talhao"));
                talhao.setObservacoes(rs.getString("observacoes"));
                talhao.setStatus2(rs.getString("status_2"));
            }
            
            rs.close();
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        return talhao; // retorna o objeto ou null se não encontrou
    }

    // Deletar Talhão pelo ID
    public void deletar(int id)  {
        try{
            Connection conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/PlanejamentoProducao", "postegres", " ");
            
            String sql = "DELETE FROM talhao WHERE id = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id); // define o ID do talhão a ser deletado
            
            stmt.executeUpdate(); // executa o DELETE
            stmt.close(); // fecha o statement
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}