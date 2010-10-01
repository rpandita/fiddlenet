/**
 * $Id$ 
 *
 * $LastChangedDate$ 
 * 
 * $LastChangedBy$
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.util.logging.Logger;
import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import algorithm.ID3;
import instance.InstanceSet;
import tree.DecisionTree;


public class Main
{
    private static Logger log = Logger.getLogger (Main.class.getName());
    String __dataFile;
    Options __opt;
    CommandLine __cl;
    InstanceSet __instanceSet;
    String [] __attributeNames;

    private Main ()
    {
        __opt = new Options(); 
        __opt.addOption("h", false, "Print help");
        __opt.addOption("f", true, "Data file");
        __opt.addOption("n", true, "Attribute names (csv)");
    }

     
    public static void main (String[] args)
    {
        new Main().run (args);
    }

    
    private void printUsage (String message, int rc)
    {
        HelpFormatter hf = new HelpFormatter();
        hf.printHelp (message, __opt);
        System.exit (rc);
    }

    
    private void run (String[] args)
    {
        try
        {
            __cl = (new BasicParser()).parse (__opt, args); 
            if ( __cl.hasOption('h')) printUsage("help", 0);
            if ( __cl.hasOption ('f') ) __dataFile = __cl.getOptionValue ('f');  else printUsage("option -f is required", 1);
            if ( __cl.hasOption ('n') ) 
            {
                __attributeNames = __cl.getOptionValue ('n').split (",");
            }
            else 
            {
                __attributeNames = new String[0];
            }
        }
        catch (ParseException ex)
        {
            printUsage (ex.getMessage(), 1);
            System.exit (1);
        }

        // Create instance set
        log.info ("Loading data from file " + __dataFile);
        try 
        {
            BufferedReader br = new BufferedReader (new FileReader (__dataFile));
            __instanceSet = new InstanceSet (br, __attributeNames);
        }
        catch (Exception ex)
        {
            log.severe ("Error loading data file " + __dataFile + ": " );
            ex.printStackTrace();
            System.exit (1);
        }

        // Create decision tree
        DecisionTree decisionTree = (new ID3()).createDecisionTree (__instanceSet);
        System.out.println (decisionTree.toString());
    }
}


