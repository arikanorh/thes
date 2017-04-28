package rules;

import pacman.game.Constants;
import pacman.game.Game;

public class FindEdibleGhostMoveRule
{

	public static Constants.MOVE findEdibleGhost(Game game)
	{
		int minDistance = Integer.MAX_VALUE;
		Constants.GHOST minGhost = null;
		for (Constants.GHOST ghost : Constants.GHOST.values())
		{
			// If it is > 0 then it is visible so no more PO checks
			if (game.getGhostEdibleTime(ghost) > 0)
			{
				int distance = game.getShortestPathDistance(game.getPacmanCurrentNodeIndex(), game.getGhostCurrentNodeIndex(ghost));

				if (distance < minDistance)
				{
					minDistance = distance;
					minGhost = ghost;
				}
			}
		}
		if (minGhost != null)
		{
			return game.getNextMoveTowardsTarget(game.getPacmanCurrentNodeIndex(), game.getGhostCurrentNodeIndex(minGhost), Constants.DM.PATH);
		}
		return null;
	}
}
