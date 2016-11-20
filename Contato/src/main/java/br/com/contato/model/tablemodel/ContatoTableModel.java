package br.com.contato.model.tablemodel;

import br.com.contato.dao.ContatoCrud;
import br.com.contato.dao.Crud;
import br.com.contato.exception.ContatoException;
import br.com.contato.model.Contato;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ContatoTableModel extends AbstractTableModel {

    private static final int CODIGO = 0;
    private static final int NOME = 1;
    private static final int TELEFONE = 2;
    private static final String[] COLUNS = new String[]{"Código", "Nome", "Telefone"};
    private final Crud<Contato> crud;
    private List<Contato> contatos;

    public ContatoTableModel() throws ContatoException {
        this.contatos = new ArrayList<>();
        this.crud = new ContatoCrud();
        this.contatos = crud.read();
    }

    /**
     * Retorna o numero de Linhas contido na tabela
     */
    @Override
    public int getRowCount() {
        return contatos.size();
    }

    /**
     * Retorna o numero de Colunas contido na tabela, utilizando o array com os
     * nomes das colunas
     */
    @Override
    public int getColumnCount() {
        return COLUNS.length;
    }

    /**
     * Pega o nome da coluna contida no index passado por parametro
     *
     * @param columnIndex -> Indice da coluna desejada
     * @return
     */
    @Override
    public String getColumnName(int columnIndex) {
        return COLUNS[columnIndex];
    }

    /**
     * A implementação deste método vai depender do critério utilizado para
     * definir quais células são editáveis ou não. Neste primeiro momento vamos
     * deixar o retorno sempre como false, assim nenhuma célula será editável.
     *
     * @param rowIndex
     * @param columnIndex
     * @return boolean
     */
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    /**
     * A implementação deste método possui uma estrutura parecida com a do
     * getValueAt, porém devemos setar o valor do campo ao invés de retorná-lo.
     * Além disso, precisamos dizer para a JTable que a célula em questão foi
     * alterada, caso contrário o novo valor não será exibido na tela.
     *
     * @param aValue
     * @param rowIndex
     * @param columnIndex
     */
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Contato item = contatos.get(rowIndex);
        switch (columnIndex) {
            case CODIGO:
                item.setId((Integer) aValue);
                break;
            case NOME:
                item.setNome((String) aValue);
                break;
            case TELEFONE:
                item.setNome((String) aValue);
                break;
            default:
                // nunca pode ocorrer pois a coluna so possui dois indices
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    /**
     * Este método deve verificar o índice recebido por parâmetro e retornar o
     * tipo de classe correspondente à coluna. Para a nossa tabela, tanto o nome
     * quanto o endereço são tratados como Strings.
     */
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case CODIGO:
                return Integer.class;
            case NOME:
                return String.class;
            case TELEFONE:
                return String.class;
            default:
                // nunca pode ocorrer pois a coluna so possui dois indices
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    /**
     * Aqui temos que retornar o conteúdo da célula especificada. Primeiro
     * obtemos o contato referente a linha e em seguida verificamos o índice da
     * coluna para recuperar o campo correspondente.
     *
     * @param rowIndex
     * @param columnIndex
     * @return
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Contato contato = contatos.get(rowIndex);
        switch (columnIndex) {
            case CODIGO:
                return contato.getId();
            case NOME:
                return contato.getNome();
            case TELEFONE:
                return contato.getTelefone();
            default:
                // nunca pode ocorrer pois a coluna so possui dois indices
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }
    
    public void setFiltro(String filtro) throws ContatoException{
        contatos = crud.read(filtro);
        fireTableDataChanged();
    }
}
