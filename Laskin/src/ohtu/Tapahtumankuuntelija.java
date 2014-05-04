package ohtu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JTextField;
 
public class Tapahtumankuuntelija implements ActionListener {
    private JButton nollaa;
    private JButton undo;
    private Sovelluslogiikka sovellus;
    
    private Map<JButton, Command> commands;
    private Command last_command;
 
    public Tapahtumankuuntelija(JButton plus, JButton miinus, JButton nollaa, JButton undo, JTextField tuloskentta, JTextField syotekentta) {
        this.nollaa = nollaa;
        this.undo = undo;

        this.sovellus = new Sovelluslogiikka();
        
        commands = new HashMap<>();
        commands.put(plus, new Sum(sovellus, tuloskentta, syotekentta));
        commands.put(miinus, new Subtraction(sovellus, tuloskentta, syotekentta));
        commands.put(nollaa, new Reset(sovellus, tuloskentta, syotekentta));
        commands.put(undo, null);
        
        last_command = null;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        Command command = commands.get((JButton)ae.getSource());
        
        if (command != null) {
            command.execute();
            last_command = command;
        } else {
            last_command.reverse();
            last_command = null;
        }
        
        nollaa.setEnabled(sovellus.tulos() != 0);
        undo.setEnabled(last_command != null);
    }
 
}