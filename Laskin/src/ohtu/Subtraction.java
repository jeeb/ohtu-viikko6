/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ohtu;

import javax.swing.JTextField;

/**
 *
 * @author jeeb
 */
public class Subtraction implements Command {
    private Sovelluslogiikka sovellus;
    private JTextField tuloskentta;
    private JTextField syotekentta;
    
    public Subtraction(Sovelluslogiikka s, JTextField tulos, JTextField syote) {
        sovellus = s;
        tuloskentta = tulos;
        syotekentta = syote;
    }
    
    @Override
    public void execute() {
        int input = 0;
        int result = 0;

        try {
            input = Integer.parseInt(syotekentta.getText());
        } catch (Exception e) {
        }
        
        sovellus.miinus(input);
        
        result = sovellus.tulos();
        
        syotekentta.setText("");
        tuloskentta.setText("" + result);
    }
    
    @Override
    public void reverse() {
        
    }
}
