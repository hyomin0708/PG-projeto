package ps2.restapidb;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Restaurante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String endereco;
    private String telefone;
    private String tipoCozinha;
    private boolean deliveryDisponivel;

    @ManyToMany
    @JoinTable(name = "restaurante_entregador",
            joinColumns = @JoinColumn(name = "restaurante_id"),
            inverseJoinColumns = @JoinColumn(name = "entregador_id"))
    private Set<Entregador> entregadores = new HashSet<>();

    public Restaurante() {
        // Construtor padrão vazio necessário para JPA
    }

    public Restaurante(String nome, String endereco, String telefone, String tipoCozinha, boolean deliveryDisponivel) {
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.tipoCozinha = tipoCozinha;
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

    public String getTipoCozinha() {
        return tipoCozinha;
    }

    public void setTipoCozinha(String tipoCozinha) {
        this.tipoCozinha = tipoCozinha;
    }

    public boolean isDeliveryDisponivel() {
        return deliveryDisponivel;
    }

    public void setDeliveryDisponivel(boolean deliveryDisponivel) {
        this.deliveryDisponivel = deliveryDisponivel;
    }

    public Set<Entregador> getEntregadores() {
        return entregadores;
    }

    public void setEntregadores(Set<Entregador> entregadores) {
        this.entregadores = entregadores;
    }

    public void addEntregador(Entregador entregador) {
        this.entregadores.add(entregador);
        entregador.getRestaurantes().add(this);
    }

    public void removeEntregador(Entregador entregador) {
        this.entregadores.remove(entregador);
        entregador.getRestaurantes().remove(this);
    }

}
