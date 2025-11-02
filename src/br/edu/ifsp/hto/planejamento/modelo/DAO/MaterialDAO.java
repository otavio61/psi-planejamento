package br.edu.ifsp.hto.planejamento.modelo.DAO;

import java.sql.*;
import java.util.*;

import br.edu.ifsp.hto.planejamento.modelo.VO.Material;

public class MaterialDAO {

    // Inserir novo material
    public void inserir(Material material) {
        try{
            Connection conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/PlanejamentoProducao", "postegres", " ");
            
            String sql = "INSERT INTO material (associado_id, nome, quantidade, unidade_medida) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, material.getAssociadoId());
            stmt.setString(2, material.getNome());
            stmt.setFloat(3, material.getQuantidade());
            stmt.setString(4, material.getUnidadeMedida());
            
            stmt.executeUpdate();
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    // Listar todos os materiais
    public List<Material> listarTodos() {
        List<Material> lista = new ArrayList<>();

        try{
            Connection conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/PlanejamentoProducao", "postegres", " ");

            String sql = "SELECT * FROM Material";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Material m = new Material();
                m.setId(rs.getInt("id"));
                m.setAssociadoId(rs.getInt("associado_id"));
                m.setNome(rs.getString("nome"));
                m.setQuantidade(rs.getFloat("quantidade"));
                m.setUnidadeMedida(rs.getString("unidade_medida"));
                lista.add(m);
            }
            
            rs.close();
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        return lista;
    }
    
    // Atualizar material existente
    public void atualizar(Material material) {
        try{
            Connection conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/PlanejamentoProducao", "postegres", " ");

            String sql = "UPDATE material SET associado_id = ?, nome = ?, quantidade = ?, unidade_medida = ? WHERE id = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, material.getAssociadoId());
            stmt.setString(2, material.getNome());
            stmt.setFloat(3, material.getQuantidade());
            stmt.setString(4, material.getUnidadeMedida());
            stmt.setInt(5, material.getId());
            
            stmt.executeUpdate();
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    // Deletar material
    public void deletar(int id) {
        try{
            Connection conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/PlanejamentoProducao", "postegres", " ");

            String sql = "DELETE FROM material WHERE id = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            
            stmt.executeUpdate();
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    // Buscar Material por ID
    public Material buscarPorId(int id) {
        Material material = null;
        try{
            Connection conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/PlanejamentoProducao", "postegres", " ");
            
            String sql = "SELECT * FROM Material WHERE id = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) { // se encontrou algum registro
                material = new Material();
                material.setId(rs.getInt("id"));
                material.setAssociadoId(rs.getInt("associado_id"));
                material.setNome(rs.getString("nome"));
                material.setQuantidade(rs.getFloat("quantidade"));
                material.setUnidadeMedida(rs.getString("unidade_medida"));
            }
            
            rs.close();
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return material; // retorna o objeto ou null se não encontrou
    }
}