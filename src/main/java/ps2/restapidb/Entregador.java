package ps2.restapidb;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Entregador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cod_entregador; // Changed from cod_entragador to cod_entregador

    private String nome;
    private String telefone;
    private int pontuacao;
    private boolean situacaoEntrega;

    public Long getCod_entregador() { // Changed from getCod() to getCod_entregador()
        return cod_entregador;
    }

    public void setCod_entregador(Long cod_entregador) { // Changed from setcod() to setCod_entregador()
        this.cod_entregador = cod_entregador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public boolean isSituacaoEntrega() { // Changed from getSituacaoEntrega() to isSituacaoEntrega()
        return situacaoEntrega;
    }

    public void setSituacaoEntrega(boolean situacaoEntrega) {
        this.situacaoEntrega = situacaoEntrega;
    }
}
