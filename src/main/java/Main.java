import entrants.pacman.username.MyPacMan;
import examples.commGhosts.POCommGhosts;
import pacman.Executor;
import pacman.game.util.Stats;


/**
 * Created by pwillic on 06/05/2016.
 */
public class Main {

    public static void main(String[] args) {

        Executor executor = new Executor(true, true);


        final Stats[] tests = executor.runExperiment(new MyPacMan(), new POCommGhosts(50), 10000, "Test");

        for (Stats stat : tests) {
            System.out.println(stat.getDescription()+" Max :" + stat.getMax()+" Avg :"+stat.getAverage()+" Std :"+stat.getStandardDeviation());
        }
    }

}

