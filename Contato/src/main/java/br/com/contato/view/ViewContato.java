package br.com.contato.view;

import br.com.contato.dao.ContatoCrud;
import br.com.contato.dao.Crud;
import br.com.contato.exception.ContatoException;
import br.com.contato.model.Contato;
import br.com.contato.model.tablemodel.ContatoTableModel;
import br.com.contato.view.tools.ViewMessage;
import br.com.contato.view.tools.ViewTools;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ViewContato extends javax.swing.JInternalFrame {

    private static ViewContato instance;
    private final Crud<Contato> CRUD;
    private ContatoTableModel tableModel;

    public ViewContato() {
        initComponents();
        this.CRUD = new ContatoCrud();
        this.jtTabela.setModel(getContatoTableModel());
    }
    
    private ContatoTableModel getContatoTableModel(){
        if(tableModel == null){
            try {
                tableModel = new ContatoTableModel();
            } catch (ContatoException ex) {
                ViewMessage.atencao(this, "Erro ao carregar dados do banco de dados!");
            }
        }
        return tableModel;
    }

    public static ViewContato getInstance() {
        if (instance == null) {
            instance = new ViewContato();
            ViewPrincipal.getInstance().addInternalFrame(instance);
        }
        return instance;
    }

    public static void showInstance() {
        getInstance().setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlCodigo = new javax.swing.JLabel();
        jlNome = new javax.swing.JLabel();
        jlTelefone = new javax.swing.JLabel();
        jtfCodigo = new javax.swing.JTextField();
        jtfNome = new javax.swing.JTextField();
        jtfTelefone = new javax.swing.JTextField();
        jbNovo = new javax.swing.JButton();
        jbSalvar = new javax.swing.JButton();
        jbCancelar = new javax.swing.JButton();
        jspTabela = new javax.swing.JScrollPane();
        jtTabela = new javax.swing.JTable();
        jtfFiltro = new javax.swing.JTextField();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Contatos");

        jlCodigo.setText("Código");

        jlNome.setText("Nome");

        jlTelefone.setText("Telefone");

        jtfCodigo.setEditable(false);

        jbNovo.setText("Novo");
        jbNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbNovoActionPerformed(evt);
            }
        });

        jbSalvar.setText("Salvar");
        jbSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSalvarActionPerformed(evt);
            }
        });

        jbCancelar.setText("Cancelar");

        jtTabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jspTabela.setViewportView(jtTabela);

        jtfFiltro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtfFiltroKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jbNovo, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                            .addComponent(jlCodigo)
                            .addComponent(jtfCodigo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jlNome)
                                        .addGap(230, 230, 230))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jtfNome)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jtfTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap())
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jlTelefone)
                                        .addGap(41, 41, 41))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jbSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbCancelar)
                                .addContainerGap())))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jtfFiltro, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jspTabela, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE))
                        .addGap(0, 17, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlCodigo)
                    .addComponent(jlNome)
                    .addComponent(jlTelefone))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbNovo)
                    .addComponent(jbSalvar)
                    .addComponent(jbCancelar))
                .addGap(18, 18, 18)
                .addComponent(jtfFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jspTabela, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbNovoActionPerformed
        limpaTela();
    }//GEN-LAST:event_jbNovoActionPerformed

    private void jbSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSalvarActionPerformed
        if (salvar(contatoInfo())) {
            ViewMessage.sucesso(getInstance(), "Contato salvo com sucesso!");
        } else {
            ViewMessage.atencao(getInstance(), "O sistema não conseguiu salvar o contato!");
        }
    }//GEN-LAST:event_jbSalvarActionPerformed

    private void jtfFiltroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfFiltroKeyPressed
        try {
            getContatoTableModel().setFiltro(jtfFiltro.getText());
        } catch (ContatoException ex) {
            ViewMessage.atencao(this, "O sistema não conseguiu preencher a tabela");
        }
    }//GEN-LAST:event_jtfFiltroKeyPressed

    private boolean salvar(Contato contato) {
        try {
            if (contato.getId() == null || contato.getId() == 0) {
                CRUD.create(contato);
            } else {
                CRUD.update(contato);
            }
        } catch (ContatoException ex) {
            ViewMessage.atencao(getInstance(), ex.getMessage());
            return false;
        }
        return true;
    }

    private void limpaTela() {
        ViewTools.limpaCampo(jtfCodigo, jtfNome, jtfTelefone);
    }

    private Contato contatoInfo() {
        Contato contato = new Contato();
        contato.setId(this.jtfCodigo.getText());
        contato.setNome(this.jtfNome.getText());
        contato.setTelefone(this.jtfTelefone.getText());
        return contato;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbCancelar;
    private javax.swing.JButton jbNovo;
    private javax.swing.JButton jbSalvar;
    private javax.swing.JLabel jlCodigo;
    private javax.swing.JLabel jlNome;
    private javax.swing.JLabel jlTelefone;
    private javax.swing.JScrollPane jspTabela;
    private javax.swing.JTable jtTabela;
    private javax.swing.JTextField jtfCodigo;
    private javax.swing.JTextField jtfFiltro;
    private javax.swing.JTextField jtfNome;
    private javax.swing.JTextField jtfTelefone;
    // End of variables declaration//GEN-END:variables
}
