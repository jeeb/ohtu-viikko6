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
public class Sum implements Command {
    private Sovelluslogiikka sovellus;
    private JTextField tuloskentta;
    private JTextField syotekentta;
    private int previous_value = 0;

    public Sum(Sovelluslogiikka s, JTextField tulos, JTextField syote) {
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
        
        previous_value = input;
        sovellus.plus(input);
        
        result = sovellus.tulos();
        
        syotekentta.setText("");
        tuloskentta.setText("" + result);
    }
    
    @Override
    public void reverse() {
       int result = 0;

       sovellus.miinus(previous_value);
       
       result = sovellus.tulos();
        
       syotekentta.setText("");
       tuloskentta.setText("" + result);
    }
    
}
