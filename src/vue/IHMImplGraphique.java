package vue;

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

import commands.Coller;
import commands.Command;
import commands.Copier;
import commands.Couper;
import commands.Inserer;
import commands.Rejouer;
import commands.Selection;
import commands.StartEnregistrement;
import commands.StopEnregistrement;
import commands.Supprimer;
import controleur.Buffer;
import enregistreur.ListEnregistrementMacro;

public class IHMImplGraphique extends JFrame implements IHM {

	//Commandes
	private Command copier;
	private Command couper;
	private Command coller;
	private Command inserer;
	private Command selecteur;
	private Command supprimer;
	private Command rejouer;
	private Command stopEnregistreur;
	private Command startEnregistreur;

	//TextArea de l'éditeur
	private JTextArea textArea;

	//Buffer de l'éditer
	private Buffer buffer;

	//Flag pour bloquer le listener de la liste des macros
	private boolean activeMacro = true;

	//Dernier caractère inséré au clavier
	private String caractereInsere = "";

	public IHMImplGraphique() {
		buildUI();
		this.show();
	}

	/**
	 * Configure le buffer et les commandes de l'ihm
	 * @param Buffer buffer
	 */
	@Override
	public void setBuffer(Buffer buffer) {
		this.buffer = buffer;

		this.coller = new Coller(buffer);
		this.copier = new Copier(buffer);

		this.couper = new Couper(buffer);
		this.inserer = new Inserer(buffer);
		this.selecteur = new Selection(buffer);

		this.supprimer = new Supprimer(buffer);

		this.rejouer = new Rejouer(buffer);
		this.startEnregistreur = new StartEnregistrement(buffer);
		this.stopEnregistreur = new StopEnregistrement(buffer);

	}
	
	/**
	 * Construit l'interface de l'éditeur
	 */
	private void buildUI() {

		setTitle("Mini-Editeur");
		setSize(700, 500);

		JPanel firstLine = new JPanel();
		JPanel secondLine = new JPanel();

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		this.setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
		this.add(firstLine);
		this.add(secondLine);

		JButton boutonCopier = new JButton("Copier");
		firstLine.add(boutonCopier);
		
		JButton boutonColler = new JButton("Coller");
		firstLine.add(boutonColler);
		boutonColler.setEnabled(false);
		
		JButton boutonCouper = new JButton("Couper");
		firstLine.add(boutonCouper);

		JButton boutonUndo = new JButton("Undo");
		secondLine.add(boutonUndo);
		JButton boutonRedo = new JButton("Redo");
		secondLine.add(boutonRedo);

		JButton boutonStartEnregistreur = new JButton("Start enregistrement");
		secondLine.add(boutonStartEnregistreur);

		JButton boutonStopEnregistreur = new JButton("Stop enregistrement");
		boutonStopEnregistreur.setEnabled(false);
		secondLine.add(boutonStopEnregistreur);

		JComboBox<String> listeMacros;

		
		// Configuration de la liste des macros
		listeMacros = new JComboBox<>();
		secondLine.add(listeMacros);

		listeMacros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (activeMacro) {
					JComboBox<String> combo = (JComboBox) e.getSource();
					buffer.setSelectedMacro(combo.getSelectedIndex());
					rejouer.exec();
				}
			}
		});

		// Configuration du textArea
		textArea = new JTextArea("", 1, 50);
		textArea.getCaret().setVisible(true);
		textArea.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				int key = e.getKeyCode();
				
				// Caractères [a-zA-Z]
				if ((((key >= 65) && (key <= 90)) || key == 10 || key == 32 || ((key >= 97) && (key <= 122))
						|| ((key >= 48) && (key <= 57)))) {
					caractereInsere = e.getKeyChar() + "";
					inserer.exec();
				} else if (key == 8) {// Backspace
					caractereInsere = e.getKeyChar() + "";
					supprimer.exec();
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}
		});

		textArea.addCaretListener(new CaretListener() {
			@Override
			public void caretUpdate(CaretEvent e) {
				selecteur.exec();
				textArea.getCaret().setVisible(true);

			}
		});

		textArea.setEditable(false);

		this.add(textArea);

		// Défnitions des listeners des boutons
		boutonColler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				coller.exec();
			}
		});
		boutonCopier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boutonColler.setEnabled(true);
				copier.exec();
			}
		});
		boutonCouper.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boutonColler.setEnabled(true);
				couper.exec();
			}
		});

		boutonUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buffer.undo();
			}
		});

		boutonRedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buffer.redo();
			}
		});

		boutonStartEnregistreur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boutonStartEnregistreur.setEnabled(false);
				boutonStopEnregistreur.setEnabled(true);
				buffer.newMacro();
				startEnregistreur.exec();
				listeMacros.setEnabled(false);
			}
		});

		boutonStopEnregistreur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boutonStartEnregistreur.setEnabled(true);
				boutonStopEnregistreur.setEnabled(false);
				stopEnregistreur.exec();
				activeMacro = false;
				listeMacros.addItem("Macro " + buffer.getListEnregistreurMacro().getNbMacro());
				activeMacro = true;
				listeMacros.setEnabled(true);

			}
		});

		this.setVisible(true);
	}

	/**
	 * Met à jour l'ihm en fonction du buffer
	 */
	@Override
	public void update() {
		textArea.setText(buffer.getText());
		textArea.getCaret().setVisible(true);
	}

	
	
	//Getters et setters de l'ihm 
	
	
	public void setCursorPosition(int position) {
		textArea.setCaretPosition(position);
		textArea.getCaret().setVisible(true);
	}


	@Override
	public String getCaractereInsere() {
		return caractereInsere;
	}

	@Override
	public int getDebutSelection() {
		return textArea.getSelectionStart();
	}

	@Override
	public int getFinSelection() {
		return textArea.getSelectionEnd();
	}
}
