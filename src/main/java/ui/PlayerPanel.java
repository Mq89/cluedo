package ui;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class PlayerPanel extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1158663946920147274L;

	private JTextField playerName = new JTextField(30);
	private JButton createPlayer = new JButton("Neu");
	private AbstractTableModel tableModel;

	public PlayerPanel() {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		JPanel panel = new JPanel(new CenteredLayoutManager());

		tableModel = new PlayerTableModel(GameManager.getInstance().getPm().getPlayers());

		panel.add(new JScrollPane(new JTable(tableModel)));
		add(panel);

		panel = new JPanel();
		panel.add(playerName);
		panel.add(createPlayer);
		createPlayer.addActionListener(this);
		add(panel);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == createPlayer) {
			GameManager.getInstance().getPm().addPlayer(playerName.getText());
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

	private static class CenteredLayoutManager implements LayoutManager {

		@Override
		public void addLayoutComponent(String name, Component comp) {
			// nothing

		}

		@Override
		public void removeLayoutComponent(Component comp) {
			// nothing

		}

		@Override
		public Dimension preferredLayoutSize(Container parent) {
			return calculateSize(parent);
		}

		@Override
		public Dimension minimumLayoutSize(Container parent) {
			return calculateSize(parent);
		}

		private Dimension calculateSize(Container parent) {
			Insets insets = parent.getInsets();
			int width = 0;
			int height = 0;
			for (Component c : parent.getComponents()) {
				width += c.getPreferredSize().width;
				height = Math.max(height, c.getPreferredSize().height);
			}

			return new Dimension(insets.left + width + insets.right, insets.top + height + insets.bottom);
		}

		@Override
		public void layoutContainer(Container parent) {
			Dimension size = calculateSize(parent);
			int freeWidth = parent.getWidth() - size.width;
			Insets insets = parent.getInsets();
			int x = freeWidth/2;
			for (Component c : parent.getComponents()) {
				c.setBounds(x, insets.top, c.getPreferredSize().width, parent.getHeight());
				System.out.println(parent.getHeight());
				x += c.getPreferredSize().width;
			}
		}

	}

}
