/*
 * april 18 by spencer
 * 
 */
package shoputilityprogram;

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
    
    public Products(String n, double sp, double up, int s){
        name=n;
        supplierPrice=sp;
        unitPrice=up;
        stock=s;
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
    
    //finds an estimate of the asset price of all the specific product in stock
    public double assetEvaluation(){
        return (stock*(unitPrice+supplierPrice)/2);
    }
    
    
}
