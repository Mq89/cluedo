package action;

import java.util.LinkedList;
import java.util.List;

import players.Player;
import players.PlayerManager;

/**
 * Singleton managing the actions performed during the game. Contains the logic
 * to infer further information from the history of actions.
 * 
 * @author Markus Heinrich <mh@0x25.net>
 *
 */
public class ActionManager {

	private static ActionManager instance;

	/**
	 * List of all performed actions (turns).
	 */
	private List<Action> history;

	/**
	 * List of actions where no information could be deduced from and can be
	 * reprocessed for deduction.
	 */
	private List<Action> reprocess;

	private ActionManager() {
		history = new LinkedList<>();
		reprocess = new LinkedList<>();
	}

	/**
	 * Process an action made in a move by a player.
	 * 
	 * @param a
	 *            The action to process.
	 */
	public void processAction(Action a) {
		history.add(a);

		PlayerManager pm = PlayerManager.getInstance();

		// no answer
		if (a.getConfirm() == null) {
			pm.addHasNotToAllBut(a.getMove(), a.getSuspect());
			pm.addHasNotToAllBut(a.getMove(), a.getRoom());
			pm.addHasNotToAllBut(a.getMove(), a.getWeapon());
		} else {

			// process players between move and confirm
			int id = (a.getMove().getId() + 1) % Player.COUNT;
			while (!pm.getPlayer(id).equals(a.getConfirm())) {
				pm.getPlayer(id).addHasNotItem(a.getSuspect());
				pm.getPlayer(id).addHasNotItem(a.getRoom());
				pm.getPlayer(id).addHasNotItem(a.getWeapon());
				id++;
				id %= Player.COUNT;
			}

			if (a.getWhich() != null) {
				pm.addHasItemTo(a.getConfirm(), a.getWhichItem());
			} else {

				boolean deduced = doDeduction(a);
				if (!deduced) {
					reprocess.add(a);
				}
			}
		}

		reprocess();
	}

	private void reprocess() {
		for (Action a : reprocess) {
			boolean deduced = doDeduction(a);
			if (deduced) {
				reprocess.remove(a);
			}
		}
	}

	/**
	 * Tries to infer which card has been shown to another player by checking which
	 * cards the showing player can possibly show.
	 * 
	 * @param a
	 *            The action to perform the deduction on.
	 * @return Whether information could be learned.
	 */
	private boolean doDeduction(Action a) {

		PlayerManager pm = PlayerManager.getInstance();

		boolean deduced = false;

		if (a.getConfirm().hasNotItem(a.getSuspect()) && a.getConfirm().hasNotItem(a.getRoom())) {
			pm.addHasItemTo(a.getConfirm(), a.getWeapon());
			deduced = true;
		}
		if (a.getConfirm().hasNotItem(a.getSuspect()) && a.getConfirm().hasNotItem(a.getWeapon())) {
			pm.addHasItemTo(a.getConfirm(), a.getRoom());
			deduced = true;
		}
		if (a.getConfirm().hasNotItem(a.getWeapon()) && a.getConfirm().hasNotItem(a.getRoom())) {
			pm.addHasItemTo(a.getConfirm(), a.getSuspect());
			deduced = true;
		}

		return deduced;

	}

	public static ActionManager getInstance() {
		if (instance == null) {
			synchronized (ActionManager.class) {
				if (instance == null) {
					instance = new ActionManager();
				}
			}
		}
		return instance;
	}
}
