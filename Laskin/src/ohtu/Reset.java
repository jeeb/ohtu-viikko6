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
public class Reset implements Command {
    private Sovelluslogiikka sovellus;
    private JTextField tuloskentta;
    private JTextField syotekentta;
    private int previous_value = 0;
    
    public Reset(Sovelluslogiikka s, JTextField tulos, JTextField syote) {
        sovellus = s;
        tuloskentta = tulos;
        syotekentta = syote;
    }
    
    @Override
    public void execute() {
        int result = 0;
        
        previous_value = sovellus.tulos();
        
        sovellus.nollaa();
        
        result = sovellus.tulos();
        
        syotekentta.setText("");
        tuloskentta.setText("" + result);
    }
    
    @Override
    public void reverse() {
        int result = 0;
        
        sovellus.plus(previous_value);
        
        result = sovellus.tulos();
        
        syotekentta.setText("");
        tuloskentta.setText("" + result);
    }
}
