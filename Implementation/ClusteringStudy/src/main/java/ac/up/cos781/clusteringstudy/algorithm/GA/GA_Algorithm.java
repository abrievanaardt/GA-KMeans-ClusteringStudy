package ac.up.cos781.clusteringstudy.algorithm.GA;

import java.util.Arrays;

public class GA_Algorithm
{

    private final double[][] dataObjs;
    private final Chromosome[] population;
    private final Chromosome[] bestIndividuals;
    private final int numClusters;
    private int currentGenerationCount;
    private final GAConfiguration config;
    private final double[] fitnessVals;

    public GA_Algorithm(GAConfiguration _config, double[][] _dataObjs, int _numClusters)
    {
        config = _config;
        dataObjs = _dataObjs;
        numClusters = _numClusters;
        currentGenerationCount = 0;
        population = new Chromosome[config.getPopSize()];
        bestIndividuals = new Chromosome[config.getMaxGen()];
        fitnessVals = new double[config.getMaxGen()];
    }

    //The skeleton GA algorithm
    public void runAlgorithm()
    {
        initialisePopulation();
        calculateFitnessOfPop(population);

        while (!stoppingConditions())
        {
            Chromosome[] newPopulation = crossover();
            mutatePop(newPopulation);
            calculateFitnessOfPop(newPopulation);
            setPopulation(newPopulation);
            incrementGeneration();
            reportBestIndividual();
        }
    }

    //Creates a new Chromosome, which by default intialises randomly and also makes sure that there is an instance of each cluster.
    private void initialisePopulation()
    {
        Chromosome toAdd;

        for (int i = 0; i < config.getPopSize(); i++)
        {
            toAdd = new Chromosome(numClusters, dataObjs.length);
            population[i] = toAdd;
        }
    }

    //Returns true if one or more of the stopping conditions is met
    private boolean stoppingConditions()
    {
        return currentGenerationCount >= config.getMaxGen();
    }

    //Calculates the fitness of each chromosome, calculateFitness sets the fitness for each chrmomosome
    private void calculateFitnessOfPop(Chromosome[] pop)
    {
        Chromosome temp;
        for (int i = 0; i < config.getPopSize(); i++)
        {
            temp = pop[i];
            temp.setFitness(config.getFitness().calculateFitness(temp, dataObjs, numClusters));
        }
    }

    //Creates a new popuation by continiously selecting two parents and reproducing using these two parents - crossover will only
    //return if chromosome is valid - or if it has tried 5 times to make a better chromosome (else returns the best parent)
//    private Chromosome[] crossover()
//    {
//        Chromosome[] newPop = new Chromosome[population.length];
//        Chromosome parent1;
//        Chromosome parent2;
//        Chromosome child;
//
//        for (int i = 0; i < config.getPopSize(); i++)
//        {
//            parent1 = config.getSelection().selectParent(population);
//            parent2 = config.getSelection().selectParent(population);
//            child = config.getCrossover().crossover(parent1, parent2);
//            newPop[i] = (child);
//        }
//
//        return newPop;
//    }
    //This function just returns the old population, thus does not make use of crossover
    private Chromosome[] crossover()
    {
        Chromosome[] newPop = new Chromosome[population.length];

        for (int i = 0; i < config.getPopSize(); i++)
        {
            newPop[i] = new Chromosome(population[i]);
        }

        return newPop;
    }

    //Mutates the indiviuals - tries 5 times until a valid chromosome is found, else the chromosome is returned as is
    private void mutatePop(Chromosome[] pop)
    {
        for (int i = 0; i < config.getPopSize(); i++)
        {
            config.getMutation().mutate(pop[i], numClusters);
        }
    }

    //Keeps the elitistPerc best individuals in the current population and then copies over the best of the new population until the population
    //is full
    private void setPopulation(Chromosome[] pop)
    {
        orderAccordingToFitness(population);
        orderAccordingToFitness(pop);

        int j = 0;
        int k = (int) (config.getPopSize() * config.getElitistPerc());
        for (int i = (int) (config.getPopSize() * config.getElitistPerc()); i < config.getPopSize(); i++)
        {
            population[i] = pop[j];
            j++;
        }
    }

    //Increments the generation counter
    private void incrementGeneration()
    {
        currentGenerationCount++;
    }

    //Reports the best indiviual in this population (thus for each generation)
    private void reportBestIndividual()
    {
        orderAccordingToFitness(population);

        bestIndividuals[currentGenerationCount - 1] = population[0];
        fitnessVals[currentGenerationCount - 1] = population[0].getFitness();
        double val = population[0].getFitness();
        System.out.println(population[0].getFitness());
    }

    //Orders accroding to the fitnes of each individual (uses the compareTo function implemented in Chromosome class)
    private void orderAccordingToFitness(Chromosome[] pop)
    {
        Arrays.sort(pop);
    }
}
