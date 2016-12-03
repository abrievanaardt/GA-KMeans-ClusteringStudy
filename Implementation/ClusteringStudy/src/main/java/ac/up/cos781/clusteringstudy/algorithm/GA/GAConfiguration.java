package ac.up.cos781.clusteringstudy.algorithm.GA;


public class GAConfiguration {
    
    private final Mutation mutation;
    private final Selection selection;
    private final Crossover crossover;
    private final Fitness fitness;
    private final int maxGen;
    private final double elitist;
    private final int popSize;
    
    public GAConfiguration(Mutation _mut, Selection _sel, Crossover _cross, Fitness _fit, int _maxGen, double _elitist, int _popSize)
    {
        mutation = _mut;
        selection = _sel;
        crossover = _cross;
        fitness = _fit;
        maxGen= _maxGen;
        elitist = _elitist;
        popSize = _popSize;
    }
           

    public Mutation getMutation()
    {
        return mutation;
    }

    public Selection getSelection()
    {
        return selection;
    }

    public Crossover getCrossover()
    {
        return crossover;
    }

    public Fitness getFitness()
    {
        return fitness;
    }

    public int getMaxGen()
    {
        return maxGen;
    }

    public double getElitistPerc()
    {
        return elitist;
    }
    
    public int getPopSize()
    {
        return popSize;
    }

}
