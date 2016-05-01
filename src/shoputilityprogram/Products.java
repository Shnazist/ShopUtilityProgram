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
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    //to make a new product, enter name, supplier price, unit price, stock, minAmount(optional)
    public Products(String n) throws FileNotFoundException{
        name=n;
        findProducts();
        findValuesForProduct();
    }
    
    //used when you want to add a previously undocumented product
    public void setNewValues(String n, int s, int m, double up, double sp) throws IOException{
        name=n;
        stock=s;
        supplierPrice=sp;
        unitPrice=up;
        minAmount=m;
        publish();
    }
    
    //updates the supplier price
    public void setSupplierPrice(double price) throws FileNotFoundException{
        supplierPrice=price;
        publish();
    }
    
    //updates the unit price
    public void setUnitPrice(double price) throws FileNotFoundException{
        unitPrice=price;
        publish();
    }
    
    //corrects the amount of product recorded in stock
    //notice that this function is much different than the "buy" or "sell" functions as it is 
    //not intended to be used for purchases/transactions from the supplier or customers,
    //it is made to correct the stock amount after an in store product count
    public void correctStockRecord(int s) throws FileNotFoundException{
        stock=s;
        publish();
    }
    
    //used to sell an amount of the product
    //takes the amount of stock sold as an arguement
    public void sell(int a) throws FileNotFoundException{
        stock = stock-a;
        double saleRevenue = a*unitPrice;
        //update balance
        Accounting localAccounting = new Accounting();
        localAccounting.deposite(saleRevenue);
        //update product
        publish();
    }
    
    //used to buy more of the product
    //takes the amount of product bought as an arguement
    public void buy(int a) throws FileNotFoundException{
        stock = stock+a;
        double costOfPurchase = a*supplierPrice;
        //update balance
        Accounting localAccounting = new Accounting();
        localAccounting.withdrawal(costOfPurchase);
        //update product
        publish();
    }
    
    
    
    //this function index's all the products and there properties in the text file 'Products.txt'
    //index's to the 2D arraylist 'products'
    private void findProducts() throws FileNotFoundException{
        FileReader reader;
        try {
            reader = new FileReader("Products.txt");
        } catch (FileNotFoundException ex) {
            PrintWriter w = new PrintWriter("Products.txt");
            w.println("Name||Stock||Minimum_Amount||Unit_Price||Supplier_Price");
            w.close();
            reader = new FileReader("Products.txt");
            System.out.println("Product.txt not found. New Product.txt file created");
        }
        Scanner s = new Scanner(reader);
        String temp;
        
        //skip the headers
        try{temp = s.next();}catch(NoSuchElementException e){}
        
        //keep track of the product number
        int tempProductNumber = 0;
        
        //this while loop records all the products and there values to the 2D array list 'Products'
        while(s.hasNext()){
            ArrayList<String> x = new ArrayList<String>();
            
            //this for loop adds all the product information to the arraylist x
            for(int i = 0; i<valuesPerProduct; i++){
                try{
                    x.add(s.next());
                }
                catch(NoSuchElementException e){
                    if(tempProductNumber<products.size()){
                        products.set(tempProductNumber, x);
                    }else{
                        products.add(x);
                    }
                    System.out.println("Too little values for product: "+x.get(0));
                }
            }
            
            //add the individual product information to array Products using list x as a of middle step/proxy
            if(tempProductNumber<products.size()){
                products.set(tempProductNumber, x);
            }else{
                products.add(x);
            }  
            
            //increase counter for next product
            tempProductNumber++;
            
        }
    }
    
    //Searches the text file for any products that share that name. Uses the values from the text file
    /*if product being searched does not exist then it calls the publish function, because
    the product number will be one more than the total number of products excluding the topic product, this means
    that publish will make the product in a non-ocupied space on the array list products  */ 
    private void findValuesForProduct() throws FileNotFoundException{
        ProductNumber=0;
        while(ProductNumber<products.size()) {
            ArrayList<String> tempList = products.get(ProductNumber);
            if(tempList.get(0).equals(name)){
                stock=Integer.parseInt(tempList.get(1));
                minAmount=Integer.parseInt(tempList.get(2));
                unitPrice=Double.parseDouble(tempList.get(3));
                supplierPrice=Double.parseDouble(tempList.get(4));
                break;
            }else{
                System.out.println("'"+tempList.get(0)+"' is not '"+name+"'");
                //updates product number
                ProductNumber=ProductNumber+1;
            }
        }
        
        /*this if statement checks to see if we completed the previous for loop,
        if so then it calls the function "publish" which(by using an product number
        that is not logged yet) will add the current product to the text file "Products.txt"
        */
        publish();
    }
    
    
    
    //rewrites the text file "Products.txt" with all the changes made
    private void publish() throws FileNotFoundException{
        ArrayList<String> y = new ArrayList<String>();
        y.add(name);
        y.add(""+stock);
        y.add(""+minAmount);
        y.add(""+unitPrice);
        y.add(""+supplierPrice);
        if(ProductNumber<products.size()){
                products.set(ProductNumber, y);
            }else{
                products.add(y);
                System.out.println("new product added: "+name);
            }
        File file = new File("Products.txt");

        if(file.exists()){file.delete();
        }else{System.out.println("Delete operation is failed.");}
        
        PrintWriter writer = new PrintWriter("Products.txt");
        writer.println("Name||Stock||Minimum_Amount||Unit_Price||Supplier_Price");
        
        for(int i=0; i<products.size(); i++){
            //temperary string and list to hold the values for the specific product before it is writen to the text file
            String temp = "";
            ArrayList<String> tempList = products.get(i);
            
            //the proccess for writing to the text file for one product
            for(int j=0; j<tempList.size();j++){
                temp = ""+temp + tempList.get(j)+"     ";
            }writer.println(temp);
        }
        writer.close();
    }
    
    
    //used for debugging purposess
    public void printProduct(){
        System.out.println("Name: "+name);
        System.out.println("Stock: "+stock);
        System.out.println("Product Number: "+ProductNumber);
        System.out.println();
    }
    
    //used for debugging purposess
    public void printAllProducts(){
        for (int i=0; i<products.size(); i++) {
            System.out.println(products.get(i));
        }
    }
    
    /*
    //This is a backup(less efficient) version of findValuesForProduct
    private void findValuesForProductOldFassionWay() throws FileNotFoundException{
        
        FileReader reader = new FileReader("Products.txt");
        Scanner s = new Scanner(reader);
        String temp;
        
        //to skip the headers
        temp=s.next();
        
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
                //because it cannot find the product in the text file, it will make a new product for it
                publish();
                break;
            }
        }
    }
    */
}