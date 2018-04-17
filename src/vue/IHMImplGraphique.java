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

	private Command copier;
	private Command couper;
	private Command coller;
	private Command inserer;
	private Command selecteur;
	private Command supprimer;

	private Command rejouer;
	private Command stopEnregistreur;
	private Command startEnregistreur;

	private JTextArea textArea;
	private Buffer buffer;

	private boolean testActionListenerActive = true;

	private String caractereInsere = "";

	// List des macros
	private ListEnregistrementMacro listEnregistreurMacro;
	private JComboBox<String> listeMacros;

	public int getDebutSelection() {
		return textArea.getSelectionStart();
	}

	public int getFinSelection() {
		return textArea.getSelectionEnd();
	}

	public IHMImplGraphique() {
		buildUI();
		this.show();
	}

	private void buildUI() {

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
		boutonColler.setEnabled(false);
		
		
		JButton boutonCopier = new JButton("Copier");
		firstLine.add(boutonCopier);
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

		// Configuration de la liste des macros
		listeMacros = new JComboBox<>();
		secondLine.add(listeMacros);

		listeMacros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (testActionListenerActive) {
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
				System.out.println("key pressed" + key);
				// Mettre dans inserer ?
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

			}
		});

		boutonStopEnregistreur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boutonStartEnregistreur.setEnabled(true);
				boutonStopEnregistreur.setEnabled(false);
				stopEnregistreur.exec();
				testActionListenerActive = false;
				listeMacros.addItem("Macro " + listEnregistreurMacro.getNbMacro());
				testActionListenerActive = true;
			}
		});

		this.setVisible(true);
	}

	@Override
	public void update() {
		textArea.setText(buffer.getText());
		textArea.getCaret().setVisible(true);
	}

	public void setCursorPosition(int position) {
		textArea.setCaretPosition(position);
		textArea.getCaret().setVisible(true);
	}

	@Override
	public String getText() {
		return textArea.getText();
	}

	@Override
	public void setBuffer(Buffer buffer) {
		this.buffer = buffer;

		this.listEnregistreurMacro = buffer.getListEnregistreurMacro();

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

	public String getCaractereInsere() {
		return caractereInsere;
	}

}
