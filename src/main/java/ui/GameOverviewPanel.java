package ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import game.GameManager;
import game.items.Item;
import game.players.Player;

public class GameOverviewPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 149860209006037585L;

	private JButton fire = new JButton("Fire");
	private GameOverviewEventTableModel tableModel;

	public GameOverviewPanel() {
		setLayout(new BorderLayout());
		JPanel panel = new JPanel();
		JTable table;
		JScrollPane scrollPane = new JScrollPane(table = new JTable(tableModel = new GameOverviewEventTableModel(
				GameManager.getInstance().getPm().getPlayers(), GameManager.getInstance().getIm().getItems())));
		table.getColumnModel().getColumn(0).setPreferredWidth(200);
		table.setPreferredScrollableViewportSize(table.getPreferredSize());
		panel.add(scrollPane);
		add(panel);
		panel = new JPanel();
		panel.add(fire);

		add(panel, BorderLayout.PAGE_END);

		fire.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tableModel.fireTableDataChanged();
			}
		});

	}

	private class GameOverviewEventTableModel extends AbstractTableModel {

		/**
		 * 
		 */
		private static final long serialVersionUID = 3605981564987556311L;
		private List<Player> players;
		private List<Item> items;

		public GameOverviewEventTableModel(List<Player> players, List<Item> items) {
			this.players = players;
			this.items = items;

		}

		@Override
		public int getRowCount() {
			return items.size();
		}

		@Override
		public int getColumnCount() {
			return players.size() + 1;
		}

		@Override
		public String getColumnName(int columnIndex) {
			if (columnIndex == 0) {
				return "Karte";
			} else {
				return players.get(columnIndex - 1).getName();
			}
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			if (columnIndex == 0) {
				return items.get(rowIndex);
			} else {
				return players.get(columnIndex - 1).getItemState(items.get(rowIndex));
			}
		}

	}
}
