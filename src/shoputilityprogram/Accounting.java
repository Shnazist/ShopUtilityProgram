/*
 * To edit balance and to do other accounting type actions
 * Edited: April 28 by Spencer Secord
 *
 *two f
 * 
 */
package shoputilityprogram;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Spencer
 */
public class Accounting {
    
    //function for making a withdraw out of the shop balance
    public void withdrawal(double i) throws FileNotFoundException{
        FileReader reader = new FileReader("Balance.txt");
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
        PrintWriter recordWriter = new PrintWriter("Balance.txt");
        recordWriter.println("------------Withdrawal------------");
        recordWriter.println("Balance Before Transaction: "+oldBalance);
        recordWriter.println("Transaction Time: "+dateFormat.format(date));
        recordWriter.println("Amount Withdrawn: "+i);
        recordWriter.println("New Balance: "+balance);
        recordWriter.println("");
    }
    
    
    
    
    //function for making a deposite into the shop's balance
    public void deposite(double i) throws FileNotFoundException{
        FileReader reader = new FileReader("Balance.txt");
        Scanner s = new Scanner(reader);
        double oldBalance = s.nextDouble();
        
        //update the balance
        double balance = oldBalance + i;
        
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
        PrintWriter recordWriter = new PrintWriter("Balance.txt");
        recordWriter.println("------------Deposit---------------");
        recordWriter.println("Balance Before Transaction: "+oldBalance);
        recordWriter.println("Transaction Time: "+dateFormat.format(date));
        recordWriter.println("Amount Deposited: "+i);
        recordWriter.println("New Balance: "+balance);
        recordWriter.println("");
    }
    
}
