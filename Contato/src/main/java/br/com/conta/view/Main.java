package br.com.conta.view;

import br.com.contato.dao.ContatoCrud;
import br.com.contato.dao.Crud;
import br.com.contato.model.Contato;
import br.com.contato.model.Endereco;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Crud<Contato> c = new ContatoCrud();
        Contato contato = new Contato();
        contato.setNome("Joel");
        contato.setTelefone("65656");
        for (int i = 0; i < 100; i++) {
            Endereco end = new Endereco();
            end.setContato(contato);
            end.setDescricao("testando");
            contato.addEndereco(end);

        }
        contato = c.create(contato);
        System.out.println(contato        );
        
        List<Contato> contatos = c.read();
        System.out.println(contatos);

        for (Contato con : contatos) {
            con.setNome(con.getNome() + con.getNome());
            for(Endereco end: con.getEnderecos()){
                end.setDescricao(end.getDescricao()+" -- "+end.getDescricao());
            }
            c.update(con);
        }
        
       
        contatos = c.read();
        System.out.println(contatos);

        for (Contato con : contatos) {
            c.delete(con);
        }
    }
}
