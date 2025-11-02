package br.edu.ifsp.hto.planejamento.modelo.DAO;

import java.sql.*;
import java.util.*;

import br.edu.ifsp.hto.planejamento.modelo.VO.CanteiroHasAtividade;

public class CanteiroHasAtividadeDAO {

    // Inserir nova relação
    public void inserir(CanteiroHasAtividade cha) {
        try{
            Connection conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/PlanejamentoProducao", "postegres", " ");

            String sql = "INSERT INTO canteiro_has_atividade (canteiro_id, atividade_id, tempo_gasto_horas, data_atividade) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, cha.getCanteiroId());
            stmt.setInt(2, cha.getAtividadeId());
            stmt.setFloat(3, cha.getTempoGastoHoras());
            stmt.setDate(4, cha.getDataAtividade());

            stmt.executeUpdate();
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    // Listar todos
    public List<CanteiroHasAtividade> listarTodos() {
        List<CanteiroHasAtividade> lista = new ArrayList<>();

        try{
            Connection conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/PlanejamentoProducao", "postegres", " ");

            String sql = "SELECT * FROM canteiro_has_atividade";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
                
            while (rs.next()) {
                CanteiroHasAtividade cha = new CanteiroHasAtividade();
                cha.setCanteiroId(rs.getInt("canteiro_id"));
                cha.setAtividadeId(rs.getInt("atividade_id"));
                cha.setTempoGastoHoras(rs.getFloat("tempo_gasto_horas"));
                cha.setDataAtividade(rs.getDate("data_atividade"));
                lista.add(cha);
            }

            rs.close();
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return lista;
    }
    
    // Atualizar (caso queira editar os dados extras)
    public void atualizar(CanteiroHasAtividade cha) {
        try{
            Connection conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/PlanejamentoProducao", "postegres", " ");
            
            String sql = "UPDATE canteiro_has_atividade SET tempo_gasto_horas = ?, data_atividade = ? WHERE canteiro_id = ? AND atividade_id = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setFloat(1, cha.getTempoGastoHoras());
            stmt.setDate(2, cha.getDataAtividade());
            stmt.setInt(3, cha.getCanteiroId());
            stmt.setInt(4, cha.getAtividadeId());

            stmt.executeUpdate();
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    // Deletar uma relação
    public void deletar(int canteiroId, int atividadeId) {
        try{
            Connection conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/PlanejamentoProducao", "postegres", " ");
            
            String sql = "DELETE FROM canteiro_has_atividade WHERE canteiro_id = ? AND atividade_id = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, canteiroId);
            stmt.setInt(2, atividadeId);

            stmt.executeUpdate();
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
