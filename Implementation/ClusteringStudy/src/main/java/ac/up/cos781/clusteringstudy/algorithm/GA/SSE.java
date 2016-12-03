package ac.up.cos781.clusteringstudy.algorithm.GA;

public class SSE implements Fitness
{

    @Override
    public double calculateFitness(Chromosome chrom, double[][] dataObjs, int numClusters)
    {
        int[] genes = chrom.getGenes();
        double[][] means = new double[numClusters][genes.length];
        int[] counts = new int[numClusters];
        int val;

        for (int i = 0; i < genes.length; i++)
        {
            val = genes[i];
            counts[val] = counts[val] + 1;

            for (int j = 0; j < dataObjs[i].length; j++)
            {
                means[val][j] += dataObjs[i][j];
            }
        }
        
        double totalClusterSum = 0;
        double pattersInClusterSum;
        
        for (int i = 0; i < genes.length; i++)
        {
            totalClusterSum += distance(dataObjs[i], means[genes[i]], counts[genes[i]]);
        }
        
        return totalClusterSum;
    }
    
    private double distance(double[] data, double[] means, int count)
    {
        double dist = 0;
        double sum = 0;
        
        for (int i = 0; i < data.length; i++)
        {
            sum += Math.pow(data[i] - means[i]/count, 2);
        }
        
        return Math.sqrt(sum);
    }
}
