package br.com.contato.dao;

import br.com.contato.exception.ContatoException;
import java.util.List;


public interface Crud<T> {
    public T create(T obj) throws ContatoException;
    public List<T> read() throws ContatoException;
    public boolean update(T obj) throws ContatoException;
    public boolean delete(T obj)throws ContatoException;
}
