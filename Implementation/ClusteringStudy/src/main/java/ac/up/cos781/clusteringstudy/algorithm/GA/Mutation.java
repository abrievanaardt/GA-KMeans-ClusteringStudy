package ac.up.cos781.clusteringstudy.algorithm.GA;

import java.util.Random;

public class Mutation {
    
    private final double rate; 
    private final Random random;
    
    public Mutation(double _rate)
    {
        rate = _rate;
        random = new Random(System.currentTimeMillis());
    }

    //For each gene a random mutation is calculated, if it is smaller than the rate, then the gene is mutated if it is not the only occurence
    public void mutate(Chromosome chrom, int clusters)
    {
        int[] genes = chrom.getGenes();
        double rand;
        
        for (int i = 0; i < genes.length; i++)
        {
            rand = random.nextDouble();
            if(rand < rate)
            { 
                if(frequency(genes, genes[i]) > 1)
                {
                    genes[i] = random.nextInt(clusters);
                }
            }
        }
        
        chrom.changeGenes(genes);
    }
    
    //Counts the number of times that val is present in genes
    private int frequency(int[] genes, int val)
    {
        int count = 0;
        for (int i = 0; i < genes.length ; i++)
        {
            if(genes[i] == val)
                count++;
        }
        
        return count;
    }

}
