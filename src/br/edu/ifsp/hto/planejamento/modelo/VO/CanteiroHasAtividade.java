package br.edu.ifsp.hto.planejamento.modelo.VO;

import java.sql.Date;

public class CanteiroHasAtividade {
    private int canteiroId;
    private int atividadeId;
    private float tempoGastoHoras;
    private Date dataAtividade;

    public int getCanteiroId() {
        return canteiroId;
    }

    public void setCanteiroId(int canteiroId) {
        this.canteiroId = canteiroId;
    }

    public int getAtividadeId() {
        return atividadeId;
    }

    public void setAtividadeId(int atividadeId) {
        this.atividadeId = atividadeId;
    }

    public float getTempoGastoHoras() {
        return tempoGastoHoras;
    }

    public void setTempoGastoHoras(float tempoGastoHoras) {
        this.tempoGastoHoras = tempoGastoHoras;
    }

    public Date getDataAtividade() {
        return dataAtividade;
    }

    public void setDataAtividade(Date dataAtividade) {
        this.dataAtividade = dataAtividade;
    }
}
