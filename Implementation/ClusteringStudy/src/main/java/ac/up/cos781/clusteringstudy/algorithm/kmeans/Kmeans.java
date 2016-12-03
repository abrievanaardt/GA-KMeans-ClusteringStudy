package ac.up.cos781.clusteringstudy.algorithm.kmeans;

import ac.up.cos781.clusteringstudy.data.Dataset;
import ac.up.cos781.clusteringstudy.data.util.IncorrectFileFormatException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Abrie van Aardt
 */
public class Kmeans {

    public Kmeans(double[][] _x, int _k, int _numEvals) {
        x = _x;//todo: note no deep copy
        K = _k;

        I = x[0].length;//all inputs have same dimensions, use first
        N = x.length;//number of patterns

        m = new double[K][I];

        c = new ArrayList<>();

        //we know k in advance so initialise outer list
        for (int k = 0; k < K; k++) {
            c.add(new ArrayList<>());
        }

        rand = new Random();
        numEvals = _numEvals;
    }

    public void fit() {
        initialise();
        for (int e = 0; e < numEvals; e++) {//stopping conditions go here
            c = new ArrayList<>();

            //we know k in advance so initialise outer list
            for (int k = 0; k < K; k++) {
                c.add(new ArrayList<>());
            }

            //assign patterns to closest means
            for (int n = 0; n < N; n++) {
                int closestMeanIndex = findClosestMean(x[n]);
                c.get(closestMeanIndex).add(n);
            }

            //update means based on patterns associated with it
            for (int k = 0; k < K; k++) {
                m[k] = calculateClusterMean(c.get(k));
            }
        }
    }

    public List<List<Integer>> getClusters() {
        return c;
    }

    /**
     * For now takes random samples from the data set as initial centroids
     */
    private void initialise() {
        HashMap<Integer, Integer> selectedPatterns = new HashMap<>();

        //all centroids
        for (int k = 0; k < K; k++) {
            //sample a pattern uniformly
            int n = rand.nextInt(N);
            while (!selectedPatterns.containsValue(n)) {
                selectedPatterns.put(k, n);
                m[k] = x[n];
                n = rand.nextInt(N);
            }
        }
    }

    /**
     * Get mean closest to pattern v
     *
     * @param v
     * @return
     */
    private int findClosestMean(double[] v) {
        double minDistance = Double.MAX_VALUE;
        int minIndex = 0;

        for (int k = 0; k < 10; k++) {
            double dist = vectorDistance(v, m[k]);
            if (dist < minDistance) {
                minDistance = dist;
                minIndex = k;
            }
        }

        return minIndex;
    }

    private double[] calculateClusterMean(List<Integer> cluster) {
        double[] mean = new double[I];

        for (int p = 1; p <= cluster.size(); p++) {
            for (int i = 0; i < I; i++) {
                mean[i] = calculateNewAverage(mean[i], x[cluster.get(p - 1)][i], p);
            }
        }

        return mean;
    }

    /**
     * Calculates the running average assuming indexing starts at 1
     *
     * @param oldAvg
     * @param newValue
     * @param currentNumber
     * @return new average
     */
    protected double calculateNewAverage(double oldAvg, double newValue, int currentNumber) {
        return (oldAvg * (currentNumber - 1) + newValue) / currentNumber;
    }

    /**
     * Euclidean distance between two vectors
     *
     * @param v1
     * @param v2
     * @return
     */
    private double vectorDistance(double[] v1, double[] v2) {
        double distance = 0;
        for (int i = 0; i < v1.length; i++) {
            distance += Math.pow(v1[i] - v2[i], 2);
        }
        return Math.sqrt(distance);
    }

    public double SSE() {
        double sum = 0;
        for (int k = 0; k < K; k++) {
            for (int p = 0; p < c.get(k).size(); p++) {
                sum += Math.pow(vectorDistance(m[k], x[c.get(k).get(p)]),2);
            }
        }
        return sum;
    }

    private final double[][] x;//patterns
    private final int K;//num centroids
    private final double[][] m;//centroid locations
    private List<List<Integer>> c;//clusters with associated patterns

    private final int I;//input dimensions
    private final int N;//number of patterns in the dataset

    private final int numEvals;

    private final Random rand;

    /**
     * Testing for this algorithm
     *
     * @param args
     */
    public static void main(String[] args) throws FileNotFoundException, IncorrectFileFormatException {
        double[][] patternInputs = Dataset.fromFile("ac/up/cos781/clusteringstudy/data/movementlibras.data").getPatternInputs();
        int[] patternTargets = Dataset.fromFile("ac/up/cos781/clusteringstudy/data/movementlibras.data").getPatternTargets();

        

        for (int i = 0; i < 30; i++) {
            Kmeans kmeans = new Kmeans(patternInputs, 15, 20000);
            kmeans.fit();
            System.out.println(kmeans.SSE());
        }
        

//        List<List<Integer>> clusters = kmeans.getClusters();

//        for (int i = 0; i < clusters.size(); i++) {
//            System.out.println("NEW CLUSTER\r\n");
//            for (int p = 0; p < clusters.get(i).size(); p++) {
//                System.out.println(patternTargets[clusters.get(i).get(p)]);
//            }
//            System.out.println("===============");
//        }

//        System.out.println("ERRORERRORERRORERRORERROR");
        
        

    }

}

}
