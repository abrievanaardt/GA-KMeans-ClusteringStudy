package ac.up.cos781.clusteringstudy.algorithm.GA;


public interface Fitness {

    public abstract double calculateFitness(Chromosome get, double[][] dataObjs, int numClusters);

}
