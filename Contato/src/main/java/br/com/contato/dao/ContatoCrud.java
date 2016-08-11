/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.contato.dao;

import br.com.contato.model.Contato;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Joel Rodrigues
 */
public class ContatoCrud extends AbstractCrud<Contato> {

    private static final String INSERT = "INSERT INTO contato(nome, telefone) values(?, ?)";
    private static final String UPDATE = "UPDATE contato SET nome = ?, telefone = ? WHERE id = ?";
    private static final String SELECT = "SELECT * FROM contato";
    private static final String DELETE = "DELETE FROM contato WHERE id = ?";

    @Override
    public Contato create(Contato obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Contato> read() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Contato obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Contato obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
