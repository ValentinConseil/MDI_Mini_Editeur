package vue;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import commands.*;
import controleur.Buffer;
import enregistreur.Enregistreur;

public class IHMImplGraphique extends JFrame  implements IHM { 

	private Command copier;
	private Command couper;
	private Command coller;
	private Command inserer;
	private Command selecteur;
	
	private Command rejouer;
	private Command stopEnregistreur;
	private Command startEnregistreur;
	
	private JTextArea textArea;
	private Buffer buffer;
	private JComboBox listeMacros;

	private Enregistreur enregistreurCourant;
	
	public void setCommandCopier(Command copier) {
		this.copier = copier;
	}

	public void setCommandCouper(Command couper) {
		this.couper = couper;
	}

	public void setCommandColler(Command coller) {
		this.coller = coller;
	}

	public void setCommandInserer(Command inserer) {
		this.inserer = inserer;
	}

	public void setCommandSelecteur(Command selecteur) {
		this.selecteur = selecteur;
	}
	
	public void setCommandRejouer(Command rejouer) {
		this.rejouer=rejouer;
	}

	public void setCommandStartEnregistrement(Command enregistrement) {
		this.startEnregistreur=enregistrement;
	}

	public void setCommandStopEnregistrement(Command stopenregistrement) {
		this.stopEnregistreur=stopenregistrement;
	}


	public int getDebutSelection(){
		return textArea.getSelectionStart();
	}

	public int getFinSelection(){
		return textArea.getSelectionEnd();
	}
	public int getInsertion(){
		return 0;
	}

	public IHMImplGraphique(){

		setTitle("Mini-Editeur");
		setSize(700, 500);

		JPanel upperPanel = new JPanel();
		JPanel lowerPanel = new JPanel();


		this.setLayout(new BorderLayout()); 
		this.add(BorderLayout.NORTH,upperPanel);
		this.add(BorderLayout.CENTER,lowerPanel);


		JButton boutonColler = new JButton("Coller");
		upperPanel.add(boutonColler);
		JButton boutonCopier = new JButton("Copier");
		upperPanel.add(boutonCopier);
		JButton boutonCouper = new JButton("Couper");
		upperPanel.add(boutonCouper);

		JButton boutonStartEnregistreur = new JButton("Start enregistrement");
		upperPanel.add(boutonStartEnregistreur);
		
		JButton boutonStopEnregistreur = new JButton("Stop enregistrement");
		upperPanel.add(boutonStopEnregistreur);
		
		listeMacros = new JComboBox<>();
		upperPanel.add(listeMacros);
		
		listeMacros.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        JComboBox combo = (JComboBox)e.getSource();
                        enregistreurCourant = buffer.getEnregistreur(combo.getSelectedIndex());
                        rejouer.exec();
                    }
                }            
        );
		
		
		textArea = new JTextArea("",50,50);

		lowerPanel.add(textArea);


		textArea.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				warn();
			}
			public void removeUpdate(DocumentEvent e) {
				warn();
			}
			public void insertUpdate(DocumentEvent e) {
				warn();
			}

			public void warn() {
				inserer.exec();
			}
		});

		boutonColler.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				coller.exec();
			} 
		});
		boutonCopier.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				copier.exec();
			} 
		});
		boutonCouper.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				couper.exec();
			} 
		});
		
		boutonStartEnregistreur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                enregistreurCourant = buffer.addEnregistreur();
				startEnregistreur.exec();
			}
		});
		
		boutonStopEnregistreur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {    
				stopEnregistreur.exec();
			}
		});


		textArea.addCaretListener(new CaretListener() {
			@Override
			public void caretUpdate(CaretEvent e) {
				int length = textArea.getSelectionEnd() - textArea.getSelectionStart();                
				selecteur.exec();
			}
		});

		this.setVisible(true);
	}

	@Override
	public void update() {
		textArea.setText(buffer.getText());		
	}

	@Override
	public String getText() {
		return textArea.getText();
	}

	@Override
	public void setBuffer(Buffer buffer) {
		this.buffer=buffer;
	}

	@Override
	public void addMacros(int id) {
		listeMacros.addItem("Macro "+id);
	}

	@Override
	public Enregistreur getEnregistreur() {
		return this.enregistreurCourant;
	}

}
