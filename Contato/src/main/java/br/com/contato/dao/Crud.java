package br.com.contato.dao;

import java.util.List;


public interface Crud<T> {
    public T create(T obj);
    public List<T> read();
    public boolean update(T obj);
    public boolean delete(T obj);
}
