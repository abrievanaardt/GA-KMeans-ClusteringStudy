package ac.up.cos781.clusteringstudy.main;

import ac.up.cos781.clusteringstudy.algorithm.GA.Crossover;
import ac.up.cos781.clusteringstudy.algorithm.GA.Fitness;
import ac.up.cos781.clusteringstudy.algorithm.GA.GAConfiguration;
import ac.up.cos781.clusteringstudy.algorithm.GA.GA_Algorithm;
import ac.up.cos781.clusteringstudy.algorithm.GA.Mutation;
import ac.up.cos781.clusteringstudy.algorithm.GA.SSE;
import ac.up.cos781.clusteringstudy.algorithm.GA.Selection;
import ac.up.cos781.clusteringstudy.algorithm.GA.TournamentSelection;
import ac.up.cos781.clusteringstudy.data.Dataset;
import ac.up.cos781.clusteringstudy.data.util.IncorrectFileFormatException;
import java.io.FileNotFoundException;

public class MainMichelle
{

    public static void main(String[] args) throws FileNotFoundException, IncorrectFileFormatException
    {
       double[][] data = Dataset.fromFile("ac/up/cos781/clusteringstudy/data/movementlibras.data").getPatternInputs();
       int clusters = 3;
       
       Mutation mutation = new Mutation(0.0);
       Selection selection = new TournamentSelection(2);
       Crossover crossover = new Crossover(0.3);
       Fitness fitness = new SSE();
       GAConfiguration config = new GAConfiguration(mutation, selection, crossover, fitness, 500, 0.5, 300);
       GA_Algorithm ga = new GA_Algorithm(config, data, clusters); 
       ga.runAlgorithm();
        
    }

}
