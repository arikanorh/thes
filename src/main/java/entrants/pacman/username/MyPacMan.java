package entrants.pacman.username;

import pacman.controllers.PacmanController;
import pacman.game.Constants;
import pacman.game.Constants.MOVE;
import pacman.game.Game;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/*
 * This is the class you need to modify for your entry. In particular, you need to
 * fill in the getMove() method. Any additional classes you write should either
 * be placed in this package or sub-packages (e.g., entrants.pacman.username).
 */
public class MyPacMan extends PacmanController
{
	private static final int MIN_DISTANCE = 20;
	private MOVE myMove = MOVE.NEUTRAL;
	private Map<Constants.GHOST, Integer> ghostsLastSeenIndex = new HashMap<>();
	private Map<Constants.GHOST, Integer> ghostsLastSeenGameTime = new HashMap<>();
	private Integer lastKnownGameTime;

	public MOVE getMove(Game game, long timeDue)
	{

		Integer currentGameTime = game.getCurrentLevel();
		int current = game.getPacmanCurrentNodeIndex();
		MOVE[] moves = game.getPossibleMoves(current, game.getPacmanLastMoveMade());

		// Strategy 1: Adjusted for PO
		for (Constants.GHOST ghost : Constants.GHOST.values())
		{
			// If can't see these will be -1 so all fine there
			if (game.getGhostEdibleTime(ghost) == 0 && game.getGhostLairTime(ghost) == 0)
			{
				int ghostLocation = game.getGhostCurrentNodeIndex(ghost);
				if (ghostLocation != -1)
				{
					ghostsLastSeenIndex.put(ghost, ghostLocation);
					ghostsLastSeenGameTime.put(ghost, currentGameTime);
					Integer distanceToGhost = game.getShortestPathDistance(current, ghostLocation);
					if (distanceToGhost < 20)
					{
						return game.getNextMoveTowardsTarget(current, ghostLocation, Constants.DM.PATH);
					}
				}
			}

		}

		if (moves.length > 0)
		{
			return moves[new Random().nextInt(moves.length)];
		}
		lastKnownGameTime = currentGameTime;
		return myMove;
	}
}