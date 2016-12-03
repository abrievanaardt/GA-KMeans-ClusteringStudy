package ac.up.cos781.clusteringstudy.main;

import ac.up.cos781.clusteringstudy.data.Dataset;
import ac.up.cos781.clusteringstudy.data.util.IncorrectFileFormatException;
import java.io.FileNotFoundException;

public class MainMichelle
{

    public static void main(String[] args) throws FileNotFoundException, IncorrectFileFormatException
    {
       double[][] data = Dataset.fromFile("movementlibras.data").getPatternInputs();
       
       
        
        
    }

}
