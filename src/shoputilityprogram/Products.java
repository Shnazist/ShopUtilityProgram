/*
 * april 28 by spencer
 * 
 */
package shoputilityprogram;

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
    
    //uses the name to find an product already in the text file
    public Products(String n) throws FileNotFoundException{
        findProducts();
        name=n;
        findValuesForProduct();
    }
    
    //name, supplier price, unit price, stock
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
    
    //2D array list to keep track of all the product for rewriting and editing
    private static ArrayList<ArrayList<String>> products = new ArrayList<ArrayList<String>>();
    
    
    //this function index's all the products and there properties in the text file 'Products.txt'
    //index's to the 2D arraylist 'products'
    private void findProducts() throws FileNotFoundException{
        FileReader reader = new FileReader("Products.txt");
        Scanner s = new Scanner(reader);
        //this while loop records all the products and there values to the 2D array list 'Products'
        while(s.hasNext()){
            ArrayList<String> x = new ArrayList<String>();
            
            //name
            x.add(s.next());
            
            //amount
            x.add(s.next());
            
            //minimum amount
            x.add(s.next());
            
            //unit price
            x.add(s.next());
            
            //supplier price
            x.add(s.next());
            
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
        while(s.hasNext()){
            if(name==s.next()){
                stock=s.nextInt();
                minAmount=s.nextInt();
                unitPrice=s.nextDouble();
                supplierPrice=s.nextDouble();
                break;
            }
            else{
                //amount:
                temp=s.next();
                //minimum amount:
                temp=s.next();
                //unit price:
                temp=s.next();
                //supplier price:
                temp=s.next();
                ProductNumber=ProductNumber+1;
            }
        }
    }
    
    public void publishProduct(){
        ArrayList<String> y = new ArrayList<String>();
        y.add(name);
        y.add(""+stock);
        y.add(""+minAmount);
        y.add(""+unitPrice);
        y.add(""+supplierPrice);
        products.set(ProductNumber, y);
        
        File file = new File("Products.txt");
        	
    		if(file.delete()){
    			System.out.println(file.getName() + " is deleted!");
    		}else{
    			System.out.println("Delete operation is failed.");
    		}
        
        FileReader reader = new FileReader("Products.txt");
        Scanner s = new Scanner(reader);
                
        for(int i=0; i<products.size(); i++){
            
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton3.setText("Finish");

        jList1.setModel(nameOfProduct);
        jScrollPane1.setViewportView(jList1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(364, 364, 364)
                .addComponent(jButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(353, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(189, 189, 189))
        );
        layout.setVerticalGroup(