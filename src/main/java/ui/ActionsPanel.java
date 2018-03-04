package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Comparator;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import ca.odell.glazedlists.SortedList;
import ca.odell.glazedlists.gui.TableFormat;
import ca.odell.glazedlists.swing.DefaultEventTableModel;
import game.GameManager;
import game.action.Action;
import game.items.Item;

public class ActionsPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2944058780733873550L;

	public ActionsPanel() {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		JPanel panel = new JPanel(new BorderLayout());
		JTable table;
		panel.add(new JScrollPane(
				table = new JTable(new DefaultEventTableModel<Action>(new SortedList<Action>(GameManager.getInstance().getAm().getHistory(), new Comparator<Action>() {

					@Override
					public int compare(Action o1, Action o2) {
						return Integer.compare(o2.getId(), o1.getId());
					}
				}),
						new ActionTableFormat()))));
		table.getColumnModel().getColumn(0).setPreferredWidth(40);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		table.getColumnModel().getColumn(2).setPreferredWidth(200);
		table.getColumnModel().getColumn(3).setPreferredWidth(200);
		table.getColumnModel().getColumn(4).setPreferredWidth(200);
		table.getColumnModel().getColumn(5).setPreferredWidth(200);
		table.getColumnModel().getColumn(6).setPreferredWidth(150);
		Dimension preferredSize = table.getPreferredSize();
		preferredSize.height = 500;
		table.setPreferredScrollableViewportSize(preferredSize);
		add(panel);
		add(new CreateActionPanel());

	}

	private class ActionTableFormat implements TableFormat<Action> {
		@Override
		public int getColumnCount() {
			return 7;
		}

		@Override
		public String getColumnName(int columnIndex) {
			switch (columnIndex) {
			case 0:
				return "Zug";
			case 1:
				return "Spieler";
			case 2:
				return Item.Type.SUSPECT.toString();
			case 3:
				return Item.Type.WEAPON.toString();
			case 4:
				return Item.Type.ROOM.toString();
			case 5:
				return "Antwortender";
			case 6:
				return "Antwort";
			default:
				return "---";
			}
		}

		@Override
		public Object getColumnValue(Action a, int columnIndex) {
			switch (columnIndex) {
			case 0:
				return a.getId();
			case 1:
				return a.getMove();
			case 2:
				return a.getSuspect();
			case 3:
				return a.getWeapon();
			case 4:
				return a.getRoom();
			case 5:
				return a.getConfirm();
			case 6:
				return a.getWhich();
			default:
				return "---";
			}
		}

	}
}
