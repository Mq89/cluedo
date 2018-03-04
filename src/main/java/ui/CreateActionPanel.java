package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ca.odell.glazedlists.swing.DefaultEventComboBoxModel;
import game.GameManager;
import game.action.Action;
import game.items.Item;
import game.items.Room;
import game.items.Suspect;
import game.items.Weapon;
import game.players.Player;

public class CreateActionPanel extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5075311407738575255L;
	private JComboBox<Player> move;
	private JComboBox<Suspect> suspect;
	private JComboBox<Weapon> weapon;
	private JComboBox<Room> room;
	private JComboBox<Player> confirm;
	private JComboBox<Item.Type> which;

	private JButton submit;

	@SuppressWarnings("unchecked")
	public CreateActionPanel() {
		setBorder(new EmptyBorder(30, 10, 30, 10));
		move = new JComboBox<>(new DefaultEventComboBoxModel<>(GameManager.getInstance().getPm().getPlayers()));
		move.addActionListener(this);
		suspect = new JComboBox<Suspect>(GameManager.getInstance().getIm().getSuspectsVector());
		weapon = new JComboBox<Weapon>(GameManager.getInstance().getIm().getWeaponsVector());
		room = new JComboBox<Room>(GameManager.getInstance().getIm().getRoomsVector());

		confirm = new JComboBox<>();
		fillConfirm();
		confirm.addActionListener(this);

		Vector<Item.Type> types = new Vector<>();
		types.add(null);
		types.add(Item.Type.SUSPECT);
		types.add(Item.Type.WEAPON);
		types.add(Item.Type.ROOM);
		which = new JComboBox<>(types);

		submit = new JButton("+");
		submit.addActionListener(this);
		

		move.setSelectedIndex(0);

		add(move);
		add(suspect);
		add(weapon);
		add(room);
		add(confirm);
		add(which);
		add(submit);
	}

	/**
	 * Fill confirm combobox with null and possibly confirming players (leave out
	 * active player).
	 */
	private void fillConfirm() {
		Vector<Player> list = new Vector<>();
		list.add(null);
		list.addAll(GameManager.getInstance().getPm().getPlayers());
		list.remove(move.getSelectedIndex() + 1);
		confirm.removeAllItems();
		for (Player p : list) {
			confirm.addItem(p);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == submit) {
			Action a = new Action((Player) move.getSelectedItem(), (Suspect) suspect.getSelectedItem(),
					(Room) room.getSelectedItem(), (Weapon) weapon.getSelectedItem(),
					(Player) confirm.getSelectedItem(), (Item.Type) which.getSelectedItem());
			GameManager.getInstance().getAm().processAction(a);
			move.setSelectedIndex((move.getSelectedIndex() + 1) % move.getItemCount());
			which.setSelectedIndex(0);
		} else if (e.getSource() == move) {
			fillConfirm();
		} else if (e.getSource() == move || e.getSource() == confirm) {
			Player myself = GameManager.getInstance().getPm().getMyself();
			which.setEnabled(confirm.getSelectedItem() != null && move.getSelectedItem().equals(myself) || myself.equals(confirm.getSelectedItem()));
		}

	}
}
