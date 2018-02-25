package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JTabbedPane;

import game.GameManager;

public class CluedoAssistant {

	private JFrame frame;
	private JMenuBar topMenuBar = new JMenuBar();
	private JTabbedPane tabbedPane = new JTabbedPane();

	public CluedoAssistant() {
		this.frame = new JFrame("Cluedo Assistant");
		this.frame.setJMenuBar(topMenuBar);
		this.frame.add(tabbedPane);
		tabbedPane.addTab("Spielerliste", new PlayerPanel());
		tabbedPane.addTab("Meine Karten", new MyCardsPanel());
		tabbedPane.addTab("Spielzüge", new ActionsPanel());
		tabbedPane.addTab("Übersicht", new GameOverviewPanel());

		this.frame.pack();
		this.frame.setLocationRelativeTo(null);
		this.frame.setVisible(true);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameManager.getInstance().init();
					GameManager.getInstance().addFakePlayers();
					new CluedoAssistant();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
