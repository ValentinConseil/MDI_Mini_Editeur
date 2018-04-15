package vue;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
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

	int compteurMacro = 0;

	boolean testActionListenerActive = true;

	String caractereInsere = "";

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

		JPanel firstLine = new JPanel();
		JPanel secondLine = new JPanel();

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		this.setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS)); 
		this.add(firstLine);
		this.add(secondLine);

		JButton boutonColler = new JButton("Coller");
		firstLine.add(boutonColler);
		JButton boutonCopier = new JButton("Copier");
		firstLine.add(boutonCopier);
		JButton boutonCouper = new JButton("Couper");
		firstLine.add(boutonCouper);

		JButton boutonStartEnregistreur = new JButton("Start enregistrement");
		secondLine.add(boutonStartEnregistreur);

		JButton boutonStopEnregistreur = new JButton("Stop enregistrement");
		boutonStopEnregistreur.setEnabled(false);
		secondLine.add(boutonStopEnregistreur);

		listeMacros = new JComboBox<>();
		secondLine.add(listeMacros);

		listeMacros.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){

						if(testActionListenerActive) {
							JComboBox combo = (JComboBox)e.getSource();
							buffer.setSelectedMacro(combo.getSelectedIndex());
							System.out.println("event macro");
							rejouer.exec();
						}
					}
				}            
				);


		textArea = new JTextArea("",1,50);
		textArea.setWrapStyleWord(true);
		this.add(textArea);
		
		textArea.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {

		

				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				int key= e.getKeyCode();
				System.out.println("key pressed"+key);

				if((((key>=65)&&(key<=90)) || key == 32 ||((key>=97)&&(key<=122))||((key>=48)&&(key<=57))))
				{
					caractereInsere = e.getKeyChar()+"";
					inserer.exec();
				}
				else if (key == 8) {//Backspace
					caractereInsere = e.getKeyChar()+"";
					inserer.exec();
				}
			}
			@Override
			public void keyPressed(KeyEvent e) {
				
			
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
				boutonStartEnregistreur.setEnabled(false);
				boutonStopEnregistreur.setEnabled(true);
				enregistreurCourant = buffer.addEnregistreur();
				startEnregistreur.exec();

			}
		});

		boutonStopEnregistreur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boutonStartEnregistreur.setEnabled(true);
				boutonStopEnregistreur.setEnabled(false);
				stopEnregistreur.exec();
				testActionListenerActive=false;
				addMacros(++compteurMacro);
				testActionListenerActive=true;
			}
		});


		textArea.addCaretListener(new CaretListener() {
			@Override
			public void caretUpdate(CaretEvent e) {
				selecteur.exec();
			}
		});

		this.setVisible(true);
	}

	@Override
	public void update() {
		int positionCurseur = textArea.getCaretPosition();
		textArea.setText(buffer.getText());
		
		if(positionCurseur > textArea.getText().length())
		{
			textArea.setCaretPosition(textArea.getText().length());
		}
		else {
			textArea.setCaretPosition(positionCurseur);
		}
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

	public String getCaractereInsere() {
		return caractereInsere;
	}

	public int getPositionCurseur() {
		return textArea.getCaretPosition();
	}

}
