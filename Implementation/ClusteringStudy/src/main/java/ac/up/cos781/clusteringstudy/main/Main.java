/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ac.up.cos781.clusteringstudy.main;

import ac.up.cos781.clusteringstudy.data.util.StudyLogFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;

/**
 *
 * @author Abrie
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        int simulations = 30;
        int epochs = 300;
        
        try {
            setupLogging();
            for (int i = 2; i <= 15; i++) {
                //start experiments here
            }

        }
        catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static void setupLogging() throws IOException {
        Formatter logFormatter = new StudyLogFormatter();
        Logger.getLogger(Main.class.getName()).setLevel(Level.CONFIG);
        Logger.getLogger(ExecutorService.class.getName()).setLevel(Level.OFF);
        Logger logger = Logger.getLogger("");
        FileHandler logFileHandler = new FileHandler("log\\study.log", true);
        logFileHandler.setFormatter(logFormatter);
        logger.addHandler(logFileHandler);
        logger.setLevel(Level.ALL);
        logger.getHandlers()[0].setFormatter(logFormatter);
        logger.getHandlers()[0].setLevel(Level.ALL);//console output
        logger.getHandlers()[1].setLevel(Level.CONFIG);//normal log file
    }

}
