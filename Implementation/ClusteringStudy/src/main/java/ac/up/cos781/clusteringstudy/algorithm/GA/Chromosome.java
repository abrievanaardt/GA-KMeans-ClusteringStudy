package ac.up.cos781.clusteringstudy.algorithm.GA;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class Chromosome
{

    private int[] genes;
    private Double fitness;

    //TODO: test if that hash map makes anything slower
    public Chromosome(int numOfClusters, int numOfDataObjects)
    {
        Random rand = new Random();
        Boolean valid = false;

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

    public Chromosome(ArrayList<Integer> toCopy)
    {
        genes = new int[toCopy.size()];

        for (int i = 0; i < toCopy.size(); i++)
        {
            genes[i] = (toCopy.get(i));
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

    public void setFitness(Double _fitness)
    {
        fitness = _fitness;
    }

    public Double getFitness()
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
    public double compareTo(Chromosome compareFruit)
    {
        double comFitness = compareFruit.getFitness();

        //ascending order
        return this.fitness - comFitness;

    }
}
