package rules;

import pacman.game.Constants.MOVE;
import pacman.game.Game;

import java.util.Random;

public class RandomMoveRule
{

	public static MOVE getMove(Game game)
	{
		MOVE[] moves = game.getPossibleMoves(game.getPacmanCurrentNodeIndex(), game.getPacmanLastMoveMade());

		if (moves.length > 0)
		{
			return moves[new Random().nextInt(moves.length)];
		}
		return null;
	}
}
