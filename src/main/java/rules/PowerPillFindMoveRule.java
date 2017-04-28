package rules;

import pacman.game.Constants;
import pacman.game.Game;

import java.util.ArrayList;

public class PowerPillFindMoveRule
{
	static ArrayList<Integer> totalTargets = new ArrayList<Integer>();
	private static Integer currentLevel = 0;

	public static Constants.MOVE getMove(Game game)
	{
		if (currentLevel != game.getCurrentLevel())
		{
			totalTargets.clear();
			currentLevel = game.getCurrentLevel();
			System.out.println("LEVEL CHANGED CLEARring cache");
		}

		int[] pills = game.getPillIndices();
		int[] powerPills = game.getPowerPillIndices();
		int current = game.getPacmanCurrentNodeIndex();

		ArrayList<Integer> targets = new ArrayList<Integer>();



		for (int i = 0; i < powerPills.length; i++)
		{            //check with power pills are available
			Boolean pillStillAvailable = game.isPillStillAvailable(pills[i]);
			if (pillStillAvailable == null)
			{
				continue;
			}

			try
			{
				if (game.isPowerPillStillAvailable(pills[i]))
				{
					targets.add(powerPills[i]);
				}
			}
			catch (Exception e)
			{ //TODO:Check  Why exception here
			}
		}
		if (!targets.isEmpty())
		{
			for (Integer target : targets)
			{
				if (!totalTargets.contains(target))
				{
					totalTargets.add(target);
				}
			}
		}

		if (!targets.isEmpty())
		{
			for (Integer target : targets)
			{
				if (!totalTargets.contains(target))
				{
					totalTargets.add(target);
				}
			}

			int[] targetsArray = new int[targets.size()];        //convert from ArrayList to array

			for (int i = 0; i < targetsArray.length; i++)
			{
				targetsArray[i] = targets.get(i);
			}
			//return the next direction once the closest target has been identified
			Constants.MOVE nextMoveTowardsTarget = game.getNextMoveTowardsTarget(current, game.getClosestNodeIndexFromNodeIndex(current, targetsArray, Constants.DM.PATH), Constants.DM.PATH);
			return nextMoveTowardsTarget;
		}

		int[] targetsArray = new int[totalTargets.size()];        //convert from ArrayList to array

		for (int i = 0; i < targetsArray.length; i++)
		{
			targetsArray[i] = totalTargets.get(i);
		}
		//return the next direction once the closest target has been identified
		Constants.MOVE nextMoveTowardsTarget = game.getNextMoveTowardsTarget(current, game.getClosestNodeIndexFromNodeIndex(current, targetsArray, Constants.DM.PATH), Constants.DM.PATH);
		return nextMoveTowardsTarget;

	}
}