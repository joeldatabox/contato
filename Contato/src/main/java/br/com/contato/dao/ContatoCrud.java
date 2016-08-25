package br.com.contato.dao;

import br.com.contato.model.Contato;
import br.com.contato.model.Endereco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Joel Rodrigues
 */
public class ContatoCrud extends AbstractCrud<Contato> {

    private static final String INSERT = "INSERT INTO contato(nome, telefone) values(?, ?)";
    private static final String INSERT_ENDERECO = "INSERT INTO endereco(id_contato, descricao) values(?, ?)";
    private static final String UPDATE = "UPDATE contato SET nome = ?, telefone = ? WHERE id = ?";
    private static final String UPDATE_ENDERECO = "UPDATE endereco SET descricao = ? WHERE id = ? AND id_contato = ?";
    private static final String SELECT = "SELECT c.id AS c_id, c.nome AS c_nome, c.telefone AS c_telefone, e.id AS e_id, e.descricao  AS e_descricao FROM contato AS c LEFT JOIN endereco AS e ON(e.id_contato = c.id) ORDER BY c.id";
    private static final String DELETE = "DELETE FROM contato WHERE id = ?";
    private static final String DELETE_ENDERECO = "DELETE FROM endereco WHERE id_contato = ?";

    @Override
    public Contato create(Contato obj) {
        Connection con = null;

        try {
            con = getConnection();
            //Neste ponto estamos criando o objeto statement, porem alem de passarmos o Script de inserção por parametro,
            //passando também uma constante com o valor '1' para que possamos pegar o ID gerado pelo banco
            PreparedStatement stmt = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, obj.getNome());//setando o parametro de nome
            stmt.setString(2, obj.getTelefone());//setando o parametro de telefone
            stmt.execute();//executando nossa inserção
            //Pegando ID gerado pelo banco de dados
            ResultSet rs = stmt.getGeneratedKeys();//metodo irá retornar um resultset a ser percorrido
            if (rs.next()) {
                obj.setId(rs.getInt(1));//devemos pegar o registro que esteja na posição inicial;
            }
            if (obj.getEnderecos() != null) {
                for (Endereco end : obj.getEnderecos()) {
                    PreparedStatement endPs = con.prepareStatement(INSERT_ENDERECO, Statement.RETURN_GENERATED_KEYS);
                    endPs.setInt(1, obj.getId());
                    endPs.setString(2, end.getDescricao());
                    endPs.execute();
                    ResultSet rsEnd = endPs.getGeneratedKeys();
                    if (rsEnd.next()) {
                        end.setId(rsEnd.getInt(1));
                    }
                }
            }
            con.commit();//confirmando nossa alteração
        } catch (SQLException ex1) {
            ex1.printStackTrace();// pilha de erros
            try {
                con.rollback();//desfazendo o que foi feito na base de dados caso der algum problema na execução do insert
            } catch (SQLException ex2) {
                ex2.printStackTrace();//pilha de erros
            }
        }
        return obj;//obj ja persistido
    }

    @Override
    public List<Contato> read() {
        Connection con = null;
        ResultSet rs = null; //Objeto chave valor com resultado vindos de um banco de dados relacional 
        List<Contato> contatos = new ArrayList();// lista de contatos que iremos retornar
        try {
            con = getConnection();// pegamos uma nova conexao
            PreparedStatement stmt = con.prepareStatement(SELECT);//criando o objeto preparestatement
            rs = stmt.executeQuery();//Executando nossa requisição para o banco de dados
            //os registros recebidos precisa ser recebidos um a um percorrendo o resultset
            //o metodos next é responsavel por avançar o indice dos registros a ser lido. No final da pilha ele retorna um false e sai do bloco while
            Contato contato = new Contato();// criando um novo contato para ser adicionado na lista
            while (rs.next()) {    
                if (contato.getId() == null || !contato.getId().equals(rs.getInt("c_id"))) {
                    contato = new Contato();
                    contato.setId(rs.getInt("c_id"));//lendo o id do registro
                    contato.setNome(rs.getString("c_nome"));// lendo o registro da coluna nome
                    contato.setTelefone(rs.getString("c_telefone"));//lendo registro da colunao telefone
                    contatos.add(contato);//adicionando o contato na lista para ser retornado 
                }
                Endereco end = new Endereco();
                end.setContato(contato);
                end.setDescricao(rs.getString("e_descricao"));
                end.setId(rs.getInt("e_id"));
                contato.addEndereco(end);
            }
        } catch (Exception ex) {
            ex.printStackTrace();//imprimindo a pilha de erros
            try {
                con.rollback();// desfazendo qualquer alteração que realizada no banco
            } catch (SQLException ex1) {
                ex1.printStackTrace();//imprimindo a pilha de erros
            }
        } finally {
            close(rs, con);// fechando a conexão e o resultset
        }
        return contatos;//retornando os contatos
    }

    @Override
    public boolean update(Contato obj) {
        Connection con = null;
        try {
            con = getConnection();
            PreparedStatement stmt = con.prepareStatement(UPDATE);//criando o objeto preparestatement
            stmt.setString(1, obj.getNome());//setando o parametro nome
            stmt.setString(2, obj.getTelefone());//setando o parametro telefone
            stmt.setInt(3, obj.getId());//setando o id
            stmt.executeUpdate();//executando o update necessario 
             if (obj.getEnderecos() != null) {
                for (Endereco end : obj.getEnderecos()) {
                    PreparedStatement endPs = con.prepareStatement(UPDATE_ENDERECO);
                    endPs.setString(1, end.getDescricao());
                    endPs.setInt(2, end.getId());
                    endPs.setInt(3, obj.getId());
                    endPs.executeUpdate();
                }
            }
            con.commit();//confirmando nossa movimentação de registros executada
            return true;// devemos confirmar como true quem chemou nosso metodo
        } catch (Exception ex) {
            ex.printStackTrace();//pilha de erros
            try {
                con.rollback();// desfazendo qualquer problema que pode vim ter acontecido 
                return false;//se chegamos a este ponto é sinal que deu algum problema, sendo assim devemos retornar o resultado como false
            } catch (SQLException ex1) {
                ex1.printStackTrace();// imprime pilha de erros
                return false;//retorna false
            }
        } finally {
            close(con);//fecha a conexão
        }
    }

    @Override
    public boolean delete(Contato obj) {
        Connection con = null;
        try {
            con = getConnection();
            PreparedStatement psEnd =con.prepareStatement(DELETE_ENDERECO);
            psEnd.setInt(1, obj.getId());
            psEnd.executeUpdate();
            PreparedStatement stmt = con.prepareStatement(DELETE);//criando o objeto preparestatement
            stmt.setInt(1, obj.getId());//setando o parametro relacionado ao id
            stmt.executeUpdate();//executando a alteração
            con.commit();//confirmando as alterações
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();//pilha de erros
            try {
                con.rollback();//cancelando as alterações
                return false;
            } catch (SQLException ex1) {
                ex1.printStackTrace();//pilha de erros
                return false;//status
            }
        } finally {
            close(con);//fechado a conexão
        }
    }
}
