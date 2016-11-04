/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.contato.view.tools;

import java.awt.Component;
import javax.swing.JOptionPane;

public class ViewMessage {

    public static void sucesso(String mensagem) {
        sucesso(null, mensagem);
    }

    public static void sucesso(Component pai, String mensagem) {
        JOptionPane.showMessageDialog(pai, mensagem, "Mensagem do sistema!", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void atencao(String mensagem) {
        atencao(null, mensagem);
    }

    public static void atencao(Component pai, String mensagem) {
        JOptionPane.showMessageDialog(pai, mensagem, "Mensagem do sistema!", JOptionPane.WARNING_MESSAGE);
    }
}
