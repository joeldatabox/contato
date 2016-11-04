package br.com.contato.view.tools;

import java.lang.reflect.Field;
import javax.swing.JTextField;

public class ViewTools {

    /**
     * Este metodo limpa componentes JTextFields
     *
     * @param field -> campo que será limpado
     */
    public static void limpaCampo(JTextField... fields) {
        //Verificando se o vetor não esta vazio
        if (fields != null && fields.length > 0) {
            for (int i = 0; i < fields.length; i++) {
                if (fields[i] != null) {
                    fields[i].setText("");
                }
            }
        }
    }
}
