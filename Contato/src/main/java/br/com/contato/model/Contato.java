package br.com.contato.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Joel Rodrigues
 */
public class Contato implements Serializable {

    private Integer id;
    private String nome;
    private String telefone;
    private List<Endereco> enderecos;

    public Contato() {
        this.enderecos = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public void setId(String id){
       if(id != null && !id.isEmpty()){
           this.id = Integer.valueOf(id);
       }
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
    
    public List<Endereco> getEnderecos(){
        return this.enderecos;
    }
    
    public void addEndereco(Endereco endereco){
        this.enderecos.add(endereco);
    }
    
    public void addEnderecos(List<Endereco> enderecos){
        this.enderecos.addAll(enderecos);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Contato other = (Contato) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Contato{" + "id=" + id + ", nome=" + nome + ", telefone=" + telefone + ", enderecos=" + enderecos + '}';
    }

}
