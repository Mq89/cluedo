package ui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import game.GameManager;
import game.players.Player;

public class PlayerPanel extends JPanel implements ActionListener, KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1158663946920147274L;

	private JTextField playerName = new JTextField(30);
	private JButton createPlayer = new JButton("Neu");
	private AbstractTableModel tableModel;

	public PlayerPanel() {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		JPanel panel = new JPanel(new FlowLayout());

		tableModel = new PlayerTableModel(GameManager.getInstance().getPm().getPlayers());

		panel.add(new JScrollPane(new JTable(tableModel)));
		add(panel);

		panel = new JPanel();
		panel.add(playerName);
		playerName.addKeyListener(this);
		panel.add(createPlayer);
		createPlayer.setEnabled(false);
		createPlayer.addActionListener(this);
		add(panel);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == createPlayer) {
			GameManager.getInstance().getPm().addPlayer(playerName.getText());
			playerName.setText(null);
			createPlayer.setEnabled(false);
			tableModel.fireTableDataChanged();
		}
	}

	private static class PlayerTableModel extends AbstractTableModel {

		/**
		 * 
		 */
		private static final long serialVersionUID = 5410546282353577254L;
		private List<Player> players;

		public PlayerTableModel(List<Player> players) {
			this.players = players;
		}

		@Override
		public int getRowCount() {
			return players.size();
		}

		@Override
		public int getColumnCount() {
			return 3;
		}

		@Override
		public String getColumnName(int index) {
			return (new String[] { "ID", "Name", "Ich" })[index];
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			Player p = players.get(rowIndex);
			switch (columnIndex) {
			case 0:
				return p.getId();
			case 1:
				return p.getName();
			case 2:
				return GameManager.getInstance().getPm().getMyself().equals(p) ? "ja" : "nein";
			default:
				return "--";
			}
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// nothing

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// nothing

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (playerName.getText() == null) {
			createPlayer.setEnabled(false);
			return;
		} else if (GameManager.getInstance().getPm().getPlayer(playerName.getText()) != null) {
			createPlayer.setEnabled(false);
			return;
		}
		createPlayer.setEnabled(true);
	}
}
