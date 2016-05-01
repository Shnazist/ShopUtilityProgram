/*
 * To edit balance and to do other accounting type actions
 * Edited: April 28 by Spencer Secord
 *
 *two function: withdrawal and deposite
 * 
 */
package shoputilityprogram;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Spencer
 */
public class Accounting {
    
    //function for making a withdraw out of the shop balance
    public void withdrawal(double i) throws FileNotFoundException{
        FileReader reader;
        try {
            reader = new FileReader("Balance.txt");
        } catch (FileNotFoundException ex) {
            PrintWriter w = new PrintWriter("Balance.txt");
            w.println("0");
            w.close();
            reader = new FileReader("Balance.txt");
            System.out.println("Balance.txt not found. New Balance.txt file created");
        }
        Scanner s = new Scanner(reader);
        double oldBalance = s.nextDouble();
        
        //update the balance
        double balance = oldBalance - i;
        
        //delete the file so we can reWrite using the updated balance
        File file = new File("Balance.txt");
        	
    	if(file.delete()){}else{
    		System.out.println("Delete operation is failed.");
    	}
        
        //make the new text file with the new balance
        PrintWriter writer = new PrintWriter("Balance.txt");
        writer.print(balance);
        
        
        //make record of the transaction
        //get time of transaction
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        //write the information of the transaction to the text file Balance.txt
        PrintWriter recordWriter = new PrintWriter("BalanceRecord.txt");
        recordWriter.println("------------Withdrawal------------");
        recordWriter.println("Balance Before Transaction: "+oldBalance);
        recordWriter.println("Transaction Time: "+dateFormat.format(date));
        recordWriter.println("Amount Withdrawn: "+i);
        recordWriter.println("New Balance: "+balance);
        recordWriter.println("");
    }
    
    
    
    
    //function for making a deposite into the shop's balance
    public void deposite(double i) throws FileNotFoundException{
        FileReader reader;
        try {
            reader = new FileReader("Balance.txt");
        } catch (FileNotFoundException ex) {
            PrintWriter w = new PrintWriter("Balance.txt");
            w.println("0");
            w.close();
            reader = new FileReader("Balance.txt");
            System.out.println("Balance.txt not found. New Balance.txt file created");
        }
        Scanner s = new Scanner(reader);
        double oldBalance=0;
        try{oldBalance = s.nextDouble();}
        catch(NoSuchElementException e){System.out.println("balance not found in file");}
        try {
            reader.close();
        } catch (IOException ex){System.out.println("Accounting->Deposite: reader could not close");}
        
        //update the balance
        double balance = oldBalance + i;
        
        //delete the file so we can reWrite using the updated balance
        File file = new File("Balance.txt");
        
        if(file.exists()){file.delete();
        }else{System.out.println("Delete operation is failed.");}
                
        //make the new text file with the new balance
        PrintWriter writer = new PrintWriter("Balance.txt");
        writer.print(""+balance);
        writer.close();
        
        //make record of the transaction
        //get time of transaction
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        //write the information of the transaction to the text file Balance.txt
        PrintWriter recordWriter = new PrintWriter("BalanceRecord.txt");
        recordWriter.println("------------Deposit---------------");
        recordWriter.println("Balance Before Transaction: "+oldBalance);
        recordWriter.println("Transaction Time: "+dateFormat.format(date));
        recordWriter.println("Amount Deposited: "+i);
        recordWriter.println("New Balance: "+balance);
        recordWriter.println("");
        recordWriter.close();
    }
    
}