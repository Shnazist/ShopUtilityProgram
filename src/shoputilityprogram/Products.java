/*
 * april 28 by spencer
 * 
 */
package shoputilityprogram;

import java.awt.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author secos7463
 */
public class Products {
    
    //name of product
    String name;
    
    //price of object set by supplier
    double supplierPrice;
    
    //price set by the store
    double unitPrice;
    
    //number of items in stock
    int stock; 
    
    //minimum number in stock before store needs to order more
    int minAmount;
    
    //the number of products ahead of it in the text file
    int ProductNumber;
    
    //2D array list to keep track of all the product for rewriting and editing
    private static ArrayList<ArrayList<String>> products = new ArrayList<ArrayList<String>>();
    
    //number of relavent numbers/values used for each product in the Products.txt file, these being name, supplier price, stock, etc
    static int valuesPerProduct = 5;
    
    //uses the name as the sole input to find an product already in the text file
    public Products(String n) throws FileNotFoundException{
        findProducts();
        name=n;
        findValuesForProduct();
    }
    
    //name, supplier price, unit price, stock, assumes minAmount is zero
    public Products(String n, double sp, double up, int s) throws IOException{
        findProducts();
        name=n;
        supplierPrice=sp;
        unitPrice=up;
        stock=s;
        minAmount=0;
        addProduct();
    }
    
    //name, supplier price, unit price, stock, minAmount
    public Products(String n, double sp, double up, int s, int m) throws IOException{
        findProducts();
        name=n;
        supplierPrice=sp;
        unitPrice=up;
        stock=s;
        minAmount=m;
        addProduct();
    }
    
    //call upon using main 
    //for example: balance = balance + apples.sell(5)
    //edits both the object and the balance that is used to call upon it
    public double sell(int a){
        stock = stock-a;
        return a*unitPrice;
    }
    
    //call upon using main 
    //for example: balance = balance - apples.buy(7)
    //edits both the object and the balance that is used to call upon it
    //returns the cost to buy the products from a supplier as a positive number
    public double buy(int a){
        stock = stock+a;
        return a*supplierPrice;
    }
    
     public void addProduct() throws IOException{
         PrintWriter reader = new PrintWriter("Products.txt");
         reader.print(name+" "+stock+" "+minAmount+" "+unitPrice+" "+supplierPrice);
    }
    
    //finds an estimate of the asset price of all the specific product in stock
    public double assetEvaluation(){
        return (stock*(unitPrice+supplierPrice)/2);
    }    
    
    //this function index's all the products and there properties in the text file 'Products.txt'
    //index's to the 2D arraylist 'products'
    private void findProducts() throws FileNotFoundException{
        FileReader reader = new FileReader("Products.txt");
        Scanner s = new Scanner(reader);
        //this while loop records all the products and there values to the 2D array list 'Products'
        while(s.hasNext()){
            ArrayList<String> x = new ArrayList<String>();
            
            //this for loop adds all the product information to the arraylist x
            for(int i = 0; i<ProductNumber; i++){
            x.add(s.next());
            }
            //add the individual product information to array Products using list x as a of middle step/proxy
            products.add(x);
        }
    }
    
    
    
    //Searches the text file for any products that share that name. Uses the values from the text file
    public void findValuesForProduct() throws FileNotFoundException{
        FileReader reader = new FileReader("Products.txt");
        Scanner s = new Scanner(reader);
        String temp;
        int i=0;
        ProductNumber=0;
        while(true){
            if(name==s.next()){
                stock=s.nextInt();
                minAmount=s.nextInt();
                unitPrice=s.nextDouble();
                supplierPrice=s.nextDouble();
                break;
            }
            else{
                //moves past current product that does not have the same name as the 
                
                //this for loop works through the redunant values of the product that does not match
                for(int k = 0; k<(valuesPerProduct-1); k++){
                    temp=s.next();
                }
                
                //updates product number
                ProductNumber=ProductNumber+1;
            }
            if(s.hasNext()==false){
                //by publishing the product it adds it as a new product
                publishProduct();
                break;
            }
        }
    }
    
    public void publishProduct() throws FileNotFoundException{
        ArrayList<String> y = new ArrayList<String>();
        y.add(name);
        y.add(""+stock);
        y.add(""+minAmount);
        y.add(""+unitPrice);
        y.add(""+supplierPrice);
        products.set(ProductNumber, y);
        
        File file = new File("Products.txt");
        	
    		if(file.delete()){
    		}else{
    			System.out.println("Delete operation is failed.");
    		}
        
        PrintWriter writer = new PrintWriter("Products.txt");
        
        for(int i=0; i<products.size(); i++){
            //temperary string and list to hold the values for the specific product before it is writen to the text file
            String temp = "";
            ArrayList<String> tempList = products.get(i);
            
            //the proccess for writing to the text file for one product
            for(int j=0; j<valuesPerProduct;j++){
                temp = temp + tempList.get(j);
            }writer.println(temp);
        }
    }
}