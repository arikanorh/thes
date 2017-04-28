package interfaces;

import pacman.game.Constants;
import pacman.game.Game;

public interface  MoveRule
{

	 Constants.MOVE getMove(Game game);

}
