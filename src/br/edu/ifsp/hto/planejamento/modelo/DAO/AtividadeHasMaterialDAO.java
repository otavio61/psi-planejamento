package br.edu.ifsp.hto.planejamento.modelo.DAO;

import java.sql.*;
import java.util.*;

import br.edu.ifsp.hto.planejamento.modelo.VO.AtividadeHasMaterial;

public class AtividadeHasMaterialDAO {

    // Inserir relação
    public void inserir(AtividadeHasMaterial ahm){
        try{
            Connection conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/PlanejamentoProducao", "postegres", " ");

            String sql = "INSERT INTO atividade_has_material (material_id, atividade_id, quantidade_utilizada) VALUES (?, ?, ?)";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, ahm.getMaterialId());
            stmt.setInt(2, ahm.getAtividadeId());
            stmt.setFloat(3, ahm.getQuantidadeUtilizada());

            stmt.executeUpdate();
            stmt.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    // Listar todas as relações
    public List<AtividadeHasMaterial> listarTodos(){
        List<AtividadeHasMaterial> lista = new ArrayList<>();

        try{
            Connection conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/PlanejamentoProducao", "postegres", " ");

            String sql = "SELECT * FROM atividade_has_material";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                AtividadeHasMaterial ahm = new AtividadeHasMaterial();
                ahm.setMaterialId(rs.getInt("material_id"));
                ahm.setAtividadeId(rs.getInt("atividade_id"));
                ahm.setQuantidadeUtilizada(rs.getFloat("quantidade_utilizada"));
                lista.add(ahm);
            }

            rs.close();
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        return lista;
    }

    // Atualizar quantidade utilizada
    public void atualizar(AtividadeHasMaterial ahm){
        try{
            Connection conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/PlanejamentoProducao", "postegres", " ");
            String sql = "UPDATE atividade_has_material SET quantidade_utilizada = ? WHERE material_id = ? AND atividade_id = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setFloat(1, ahm.getQuantidadeUtilizada());
            stmt.setInt(2, ahm.getMaterialId());
            stmt.setInt(3, ahm.getAtividadeId());

            stmt.executeUpdate();
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    // Deletar relação
    public void deletar(int materialId, int atividadeId){
        try{
            Connection conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/PlanejamentoProducao", "postegres", " ");
        
            String sql = "DELETE FROM atividade_has_material WHERE material_id = ? AND atividade_id = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, materialId);
            stmt.setInt(2, atividadeId);

            stmt.executeUpdate();
            stmt.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    // Buscar relação específica
    public AtividadeHasMaterial buscarPorChave(int materialId, int atividadeId){
        AtividadeHasMaterial ahm = null;

        try{
            Connection conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/PlanejamentoProducao", "postegres", " ");

            String sql = "SELECT * FROM atividade_has_material WHERE material_id = ? AND atividade_id = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, materialId);
            stmt.setInt(2, atividadeId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                ahm = new AtividadeHasMaterial();
                ahm.setMaterialId(rs.getInt("material_id"));
                ahm.setAtividadeId(rs.getInt("atividade_id"));
                ahm.setQuantidadeUtilizada(rs.getFloat("quantidade_utilizada"));
            }

            rs.close();
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return ahm;
    }
}
