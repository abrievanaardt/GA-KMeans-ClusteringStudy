package ac.up.cos781.clusteringstudy.algorithm.GA;

import java.util.Random;

public class TournamentSelection implements Selection
{
    private final int size;
    private final Random random;
    
    public TournamentSelection(int _size)
    {
        size = _size;
        random = new Random(System.currentTimeMillis());
    }
    
    //This function select a parent by first choosing two random parents and then returns the best one between the 2 parents
    @Override
    public Chromosome selectParent(Chromosome[] population)
    {
        int rand1 = random.nextInt(population.length);
        int rand2 = random.nextInt(population.length);
        
        if(population[rand1].getFitness() < population[rand2].getFitness())
            return population[rand1];
        
        return population[rand2];
    }
}
