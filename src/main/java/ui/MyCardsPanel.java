package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import game.GameManager;
import game.items.Item;

public class MyCardsPanel extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4321053139527704905L;

	private JButton addButton = new JButton("Hinzufügen");
	
	private JTable suspectTable;
	private ItemTableModel suspectTableModel;

	private JTable weaponTable;
	private ItemTableModel weaponTableModel;

	private JTable roomTable;
	private ItemTableModel roomTableModel;

	public MyCardsPanel() {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		// tables
		JPanel panel = new JPanel();
		panel.add(new JScrollPane(suspectTable = new JTable(suspectTableModel = new ItemTableModel("Verdächtige",
				GameManager.getInstance().getIm().getSuspectsList()))));
		panel.add(new JScrollPane(weaponTable = new JTable(
				weaponTableModel = new ItemTableModel("Waffen", GameManager.getInstance().getIm().getWeaponsList()))));
		panel.add(new JScrollPane(roomTable = new JTable(
				roomTableModel = new ItemTableModel("Räume", GameManager.getInstance().getIm().getRoomsList()))));
		add(panel);

		// addbutton
		panel = new JPanel();
		addButton.addActionListener(this);
		panel.add(addButton);
		add(panel);

	}

	private static class ItemTableModel extends AbstractTableModel {

		/**
		 * 
		 */
		private static final long serialVersionUID = -1401359068847036539L;
		private List<? extends Item> items;
		private String name;

		public ItemTableModel(String name, List<? extends Item> items) {
			this.name = name;
			this.items = items;
			items.sort(null);
		}

		@Override
		public int getRowCount() {
			return items.size();
		}

		@Override
		public int getColumnCount() {
			return 2;
		}

		public Item getItem(int index) {
			return items.get(index);
		}

		@Override
		public String getColumnName(int index) {
			switch (index) {
			case 0:
				return name;
			case 1:
				return "x";
			default:
				return "---";
			}
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			Item i = items.get(rowIndex);
			switch (columnIndex) {
			case 0:
				return i.getName();
			case 1:
				return GameManager.getInstance().getPm().getMyself().hasItem(i) ? "ja" : "nein";
			default:
				return "---";
			}
		}

	}

	private void addSelectedItems() {
		for (int i : suspectTable.getSelectedRows()) {
			GameManager.getInstance().getPm().addHasItemTo(GameManager.getInstance().getPm().getMyself(),
					suspectTableModel.getItem(i));
			;
		}
		suspectTableModel.fireTableDataChanged();

		for (int i : weaponTable.getSelectedRows()) {
			GameManager.getInstance().getPm().addHasItemTo(GameManager.getInstance().getPm().getMyself(),
					weaponTableModel.getItem(i));
			;
		}
		weaponTableModel.fireTableDataChanged();

		for (int i : roomTable.getSelectedRows()) {
			GameManager.getInstance().getPm().addHasItemTo(GameManager.getInstance().getPm().getMyself(),
					roomTableModel.getItem(i));
			;
		}
		roomTableModel.fireTableDataChanged();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addButton) {
			addSelectedItems();
		}

	}
}
