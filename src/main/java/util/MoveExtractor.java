package util;

import pacman.game.Constants.MOVE;
import pacman.game.Game;

public class MoveExtractor
{

	public static MOVE[] getPossibleMoves(Game game)
	{
		return game.getPossibleMoves(game.getPacmanCurrentNodeIndex(), game.getPacmanLastMoveMade());
	}
}
