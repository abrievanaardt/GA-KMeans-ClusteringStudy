package ac.up.cos781.clusteringstudy.algorithm.GA;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class Chromosome implements Comparable<Chromosome>
{

    private int[] genes;
    private double fitness;

    //TODO: test if that hash map makes anything slower
    public Chromosome(int numOfClusters, int numOfDataObjects)
    {
        Random rand = new Random();
        Boolean valid = false;
        genes = new int[numOfDataObjects];

        while (!valid)
        {
            valid = true;
            for (int i = 0; i < numOfDataObjects; i++)
            {
                genes[i] = (rand.nextInt(numOfClusters));
            }

            Set<Integer> unique = new HashSet(Arrays.stream(genes).boxed().collect(Collectors.toList()));

            if (unique.size() < numOfClusters)
            {
                valid = false;
            }
        }
    }

    public Chromosome(Chromosome toCopy)
    {
        genes = new int[toCopy.getGenes().length];

        for (int i = 0; i < toCopy.getGenes().length; i++)
        {
            genes[i] = (toCopy.getGenes()[i]);
        }
    }

    public int getGene(int index)
    {
        return genes[index];
    }

    public int[] getGenes()
    {
        return genes;
    }

    public void setGene(int index, int val)
    {
        genes[index] = val;
    }

    public void setFitness(double _fitness)
    {
        fitness = _fitness;
    }

    public double getFitness()
    {
        return fitness;
    }

    //New mutated gene array is passed
    void changeGenes(int[] newGenes)
    {
        for (int i = 0; i < genes.length; i++)
        {
            genes[i] = newGenes[i];
        }
    }

    //Function used to comapre this Chromosome and another, used to sort
    @Override
    public int compareTo(Chromosome chrom)
    {
        double comFitness = chrom.getFitness();

        //ascending order
        if (this.fitness < comFitness)
        {
            return -1;
        }
        else if (this.fitness > comFitness)
        {
            return 1;
        }

        return 0;

    }
}
