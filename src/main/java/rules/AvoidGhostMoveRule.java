package rules;

import pacman.game.Constants;
import pacman.game.Game;

public class AvoidGhostMoveRule
{

	private static final int MIN_DISTANCE = 20;

	public static Integer getAvoidGhostMove(Game game)
	{
		Integer minGhostLocation =null;
		for (Constants.GHOST ghost : Constants.GHOST.values())
		{

			// If can't see these will be -1 so all fine there
			if (game.getGhostEdibleTime(ghost) == 0 && game.getGhostLairTime(ghost) == 0)
			{
				int ghostLocation = game.getGhostCurrentNodeIndex(ghost);
				if (ghostLocation != -1)
				{
					int shortestPathDistance = game.getShortestPathDistance(game.getPacmanCurrentNodeIndex(), ghostLocation);
					if (shortestPathDistance < MIN_DISTANCE)
					{
						if (minGhostLocation==null || shortestPathDistance < minGhostLocation)
						{
							minGhostLocation = ghostLocation;
						}
					}
				}
			}
		}
		return minGhostLocation;
	}
}
