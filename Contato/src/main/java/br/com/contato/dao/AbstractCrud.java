package br.com.contato.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class AbstractCrud<T> implements Crud<T> {

    private static final String CREATE_DB = "CREATE TABLE IF NOT EXISTS contato (id integer primary key autoincrement, nome string, telefone string)";
    private static final String FOR_NAME = "org.sqlite.JDBC";
    private static final String URL = "jdbc:sqlite:contato.db";
    private Connection connection = null;

    /**
     * Metodo responsavel por criar uma conexão com o banco de dados. O mesmo
     * verifica se a tabela ja existe, caso não existir a mesma será criada
     *
     * @return connection
     */
    protected Connection getConnection() {
        if (this.connection == null) {
            try {
                Class.forName(FOR_NAME);//carrega classe jdbc do banco de dados
                connection = DriverManager.getConnection(URL);//cria a conexão
                Statement statement = connection.createStatement();//cria um statemente para criar o banco
                statement.executeUpdate(CREATE_DB);//criando o banco de dados
                close(connection);
                connection = DriverManager.getConnection(URL);//criando uma nova conexão
                connection.setAutoCommit(false);//desabilita o autocommit
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            } catch (SQLException ex) {
                ex.printStackTrace();
                try {
                    connection.rollback();
                } catch (SQLException ex1) {
                    Logger.getLogger(AbstractCrud.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        }
        return connection;
    }

    /**
     * Fecha a conexão
     *
     * @param connection conexão a ser fechada
     */
    protected void close(Connection connection) {
        try {
            if (connection != null && (!connection.isClosed())) {
                connection.close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Fecha a conexão
     *
     * @param rs resultSet a ser fechado com a conexão
     * @param con conexão a ser fechada
     */
    protected void close(ResultSet rs, Connection con) {

        try {
            if (rs != null && !rs.isClosed()) {
                rs.close();
            }
            close(con);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
