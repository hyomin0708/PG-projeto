package ps2.restapidb;

import javax.persistence.*;

@Entity
public class Restaurante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String endereco;
    private String telefone;
    private boolean deliveryDisponivel;

    @ManyToOne
    @JoinColumn(name = "entregador_id")
    private Entregador entregador;

    public Restaurante() {
        // Construtor padrão vazio necessário para JPA
    }

    public Restaurante(String nome, String endereco, String telefone, boolean deliveryDisponivel) {
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.deliveryDisponivel = deliveryDisponivel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public boolean isDeliveryDisponivel() {
        return deliveryDisponivel;
    }

    public void setDeliveryDisponivel(boolean deliveryDisponivel) {
        this.deliveryDisponivel = deliveryDisponivel;
    }

    public Entregador getEntregador() {
        return entregador;
    }

    public void setEntregador(Entregador entregador) {
        this.entregador = entregador;
    }
}
