package ohtu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JTextField;
 
public class Tapahtumankuuntelija implements ActionListener {
    private JButton plus;
    private JButton miinus;
    private JButton nollaa;
    private JButton undo;
    private JTextField tuloskentta;
    private JTextField syotekentta;
    private Sovelluslogiikka sovellus;
    
    private Map<JButton, Command> commands;
    private Command last_command;
 
    public Tapahtumankuuntelija(JButton plus, JButton miinus, JButton nollaa, JButton undo, JTextField tuloskentta, JTextField syotekentta) {
        this.plus = plus;
        this.miinus = miinus;
        this.nollaa = nollaa;
        this.undo = undo;
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
        this.sovellus = new Sovelluslogiikka();
        
        commands = new HashMap<>();
        commands.put(plus, new Sum(sovellus, tuloskentta, syotekentta));
        commands.put(miinus, new Subtraction(sovellus, tuloskentta, syotekentta));
        commands.put(nollaa, new Reset(sovellus, tuloskentta, syotekentta));
        
        last_command = null;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        Command command = commands.get(ae.getSource());
        
        if (command != null) {
            command.execute();
            last_command = command;
        } else {
            last_command.execute();
            last_command = null;
        }
        
        nollaa.setEnabled(sovellus.tulos() != 0);
        undo.setEnabled(last_command != null);
    }
 
}