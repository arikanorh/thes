package entrants.pacman.username;

import pacman.controllers.PacmanController;
import pacman.game.Constants;
import pacman.game.Constants.MOVE;
import pacman.game.Game;
import rules.*;

/*
	REAL IMPLEMENTATION
 */
public class PacmanControllerImpl extends PacmanController
{
	private static final int MIN_DISTANCE = 20;

	private Integer avoidTemporary = 0;

	Integer lastKnownGhostLocation = null;

	public MOVE getMove(Game game, long timeDue)
	{
		MOVE move = null;

		// Rule 1: Avoid ghost
		Integer currentKnownGhostLocation = AvoidGhostMoveRule.getAvoidGhostMove(game);
		if (currentKnownGhostLocation != null)
		{
			lastKnownGhostLocation = currentKnownGhostLocation;
			avoidTemporary = 0;

			move = game.getNextMoveAwayFromTarget(game.getPacmanCurrentNodeIndex(), currentKnownGhostLocation, Constants.DM.PATH);
			System.out.println("AVOID GHOST " + move.name());
		}
		else if (lastKnownGhostLocation != null)
		{
			avoidTemporary++;
			if (avoidTemporary < 20)
			{
				move = game.getNextMoveAwayFromTarget(game.getPacmanCurrentNodeIndex(), lastKnownGhostLocation, Constants.DM.PATH);
				System.out.println("TEMP AVOID :"+game.isJunction(game.getPacmanCurrentNodeIndex()));
			}
			else
			{
				avoidTemporary = 0;
				lastKnownGhostLocation=null;
			}
		}
		if (move != null)
		{
			return move;
		}
		// Rule 2: Find nearest edible ghost and go after them
		move = FindEdibleGhostMoveRule.findEdibleGhost(game);
		if (move != null)
		{
			System.out.println("EDIBLE GHOST :" + move.name());
			return move;
		}

		//Rule 3: Move to pill
		move = NormalPillFindMoveRule.getMove(game);
		if (move != null)
		{
			System.out.println("TO PILL " + move.name());
			return move;
		}

		// Rule 5: Random move
		move = RandomMoveRule.getMove(game);
		if (move != null)
		{
			System.out.println("RANDOM :" + move.name());
			return move;
		}

		return MOVE.NEUTRAL;
	}

}