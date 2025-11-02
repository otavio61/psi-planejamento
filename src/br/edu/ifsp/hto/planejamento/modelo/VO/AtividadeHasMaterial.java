package br.edu.ifsp.hto.planejamento.modelo.VO;

public class AtividadeHasMaterial {
    private int materialId;
    private int atividadeId;
    private float quantidadeUtilizada;

    public int getMaterialId() {
        return materialId;
    }

    public void setMaterialId(int materialId) {
        this.materialId = materialId;
    }

    public int getAtividadeId() {
        return atividadeId;
    }

    public void setAtividadeId(int atividadeId) {
        this.atividadeId = atividadeId;
    }

    public float getQuantidadeUtilizada() {
        return quantidadeUtilizada;
    }

    public void setQuantidadeUtilizada(float quantidadeUtilizada) {
        this.quantidadeUtilizada = quantidadeUtilizada;
    }
}
