import entrants.pacman.username.PacmanControllerImpl;
import examples.commGhosts.POCommGhosts;
import pacman.Executor;
import pacman.game.util.Stats;


/**
 * Created by pwillic on 06/05/2016.
 */
public class Main {

    public static void main(String[] args) {



        runGameForReal();


//        runSimulation( );
    }

    private static void runGameForReal()
    {
        Executor executor = new Executor(true, true);

        executor.runGameTimed(new PacmanControllerImpl(), new POCommGhosts(50), true);

    }

    private static void runSimulation( )
    {
        Executor executor = new Executor(true, true);

        final Stats[] tests = executor.runExperiment(new PacmanControllerImpl(), new POCommGhosts(50), 10000, "Test");

        for (Stats stat : tests) {
            System.out.println(stat.getDescription()+" Max :" + stat.getMax()+" Avg :"+stat.getAverage()+" Std :"+stat.getStandardDeviation());
        }
    }

}

