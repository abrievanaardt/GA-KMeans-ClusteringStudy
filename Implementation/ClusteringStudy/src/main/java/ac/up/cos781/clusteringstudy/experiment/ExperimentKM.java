///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package ac.up.cos781.clusteringstudy.experiment;
//
//import ac.up.cos781.clusteringstudy.data.Dataset;
//import ac.up.cos781.clusteringstudy.data.Graph;
//import java.util.Arrays;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import ac.up.cos781.clusteringstudy.data.Results;
//
//public class ExperimentKM extends Thread
//{
//
//    /**
//     *
//     * @param _numSim
//     * @param _maxEpoch
//     * @param _numK
//     */
//    public ExperimentKM(int _numSim, int _maxEpoch, int _numK)
//    {
//        name = "GA_Experiment_" + _numK;
//        path = "Experiment/";
//        NUM_SIMULATIONS = _numSim;
//        simulations = new double[NUM_SIMULATIONS];
//        epochs = new double[_maxEpoch];
//
//        //note the data sets are recorded in alphabetical order, easy to remember this way
//        datasetNames = new String[]
//        {
//            "Cancer",
//            "Diabetes",
//            "Glass",
//            "Heart",
//            "Iris"
//        };
//
//        NUM_DATASETS = datasetNames.length;
//        MAX_EPOCH = _maxEpoch;
//
//        avgTrainingErrorHistories = new double[NUM_DATASETS][_maxEpoch];
//
//        trainingErrors = new double[NUM_SIMULATIONS + 2][NUM_DATASETS];
//
//        avgErrorIndex = NUM_SIMULATIONS;
//        stdDevErrorIndex = avgErrorIndex + 1;
//
//        for (int i = 0; i < simulations.length; i++)
//        {
//            simulations[i] = i + 1;
//        }
//
//        for (int i = 0; i < epochs.length; i++)
//        {
//            epochs[i] = i + 1;
//        }
//        
//    }
//
//    @Override
//    public void run()
//    {
//
//        Logger
//                .getLogger(ExperimentGA.class.getName())
//                .log(Level.INFO, "...");
//
//        Logger
//                .getLogger(ExperimentGA.class.getName())
//                .log(Level.INFO, "Running experiment: {0}", name);
//
//        Logger
//                .getLogger(ExperimentGA.class.getName())
//                .log(Level.INFO, "Doing {0} simulation(s)", NUM_SIMULATIONS);
//
//        try
//        {
//
//            for (int i = 1; i <= NUM_SIMULATIONS; i++)
//            {
//
//                Logger
//                        .getLogger(ExperimentGA.class.getName())
//                        .log(Level.INFO, name + " - Simulation {0}", i);
//
//                runSimulation(i);
//
//            }
//
//            finalise();
//
//        }
//        catch (Exception e)
//        {
//            Logger.getLogger(ExperimentGA.class.getName()).log(Level.SEVERE, "", e);
//        }
//    }
//
//    /**
//     * Calculates the running average assuming indexing starts at 1
//     *
//     * @param oldAvg
//     * @param newValue
//     * @param currentNumber
//     * @return new average
//     */
//    protected double calculateNewAverage(double oldAvg, double newValue, int currentNumber)
//    {
//        return (oldAvg * (currentNumber - 1) + newValue) / currentNumber;
//    }
//
//    /**
//     * Calculates the sample standard deviation of the array of values
//     *
//     * @param values
//     * @param avg
//     * @return sample standard deviation
//     */
//    protected double calculateSampleStdDev(double[] values, double avg)
//    {
//        double stdDev = Arrays.stream(values).map(i -> Math.pow(i - avg, 2)).sum() / (values.length - 1);
//        return Math.sqrt(stdDev);
//    }
//
//    protected void runSimulation(int currentSimulation)
//            throws Exception
//    {
//
//        for (int i = 0; i < NUM_DATASETS; i++)
//        {
//            Dataset dataset = Dataset
//                    .fromFile(PATH_PREFIX
//                            + "/" + datasetNames[i].toLowerCase()
//                            + EXT);
//            dataset.setDatasetName(datasetNames[i]);
//
//            //initiate GA algorithm here
//            //store errors over epochs for this simulation, last epoch (index) is final error
//            //trainingErrors[currentSimulation - 1][i] = twoPhase.getTrainingError();
//            
//            double clusterQualitySSE = 2;//obtain cluster quality from algorithm
//
//            //get running average of errors over number of simulations
//            //trainingErrors[avgErrorIndex][i] = calculateNewAverage(trainingErrors[avgErrorIndex][i], twoPhase.getTrainingError(), currentSimulation);
//            
//            //calculate standard deviation only when finalising
//            
//            
//            double[] tempTrainingHist = new double[0];//obtain errors per epoch (the history) from the algorithm
//            
//            //update average error history (errors over epochs) over numer of simulations
//            for (int j = 0; j < MAX_EPOCH; j++)
//            {
//                //dataset i epoch j
//               avgTrainingErrorHistories[i][j] = calculateNewAverage(avgTrainingErrorHistories[i][j], tempTrainingHist[j], currentSimulation);               
//            }
//        }
//
//    }
//
//    protected void finalise() throws Exception
//    {
//
//        //calculate std dev for each type of measure
//        for (int i = 0; i < NUM_DATASETS; i++)
//        {
//
//            //column of errors
//            double[] errorColumn = new double[NUM_SIMULATIONS];
//
//            //extract column of trainingError
//            for (int j = 0; j < NUM_SIMULATIONS; j++)
//            {
//                errorColumn[j] = trainingErrors[j][i];
//            }
//            
//            //std dev
//            trainingErrors[stdDevErrorIndex][i] = calculateSampleStdDev(errorColumn, trainingErrors[avgErrorIndex][i]);           
//
//            
//        }
//
//        //raw result output
//        Results.writeToFile(path, name + "_TrainingErrors", trainingErrors);
//
//        //graphing the results
//        //add a plot for each of the datasets (for now)
//        graph = new Graph(path + "/Plots_" + name, "Error vs Epoch", "Epoch", "Error", "", 2);
//        for (int i = 0; i < NUM_DATASETS; i++)
//        {
//            graph.addPlot(datasetNames[i] + " E_T", epochs, avgTrainingErrorHistories[i], "line lt " + (i + 1));
//        }
//        graph.plot();
//
//    }
//
//    public String getExpName()
//    {
//        return name;
//    }
//
//    public Graph graph;
//    protected String name;
//    protected String path;
//    protected double[] simulations;
//    protected double[] epochs;
//    protected int stdDevErrorIndex;
//    protected int avgErrorIndex;
//    private final int NUM_SIMULATIONS;
//
//    private final double[][] avgTrainingErrorHistories;
//
//    private final double[][] trainingErrors; 
//
//    private final int MAX_EPOCH;
//
//    private final int NUM_DATASETS;    
//
//    private final String PATH_PREFIX = "ac/up/cos781/clusteringstudy/data/";
//    private final String EXT = ".nsds";
//
//    private final String[] datasetNames;
//}
