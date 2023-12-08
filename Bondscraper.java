import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class Bondscraper {

     public static void main(String args[]) throws IOException{
     
          //Construct URL object and Scanner object for wiki
          URL Wikipage = new URL("https://oldschool.runescape.wiki/w/Old_school_bond");          
          Scanner sc = new Scanner(Wikipage.openStream());         
          //Extracting gp price of osrs bond from wiki
          String gpPrice = "";
          while(gpPrice.equals("")){
               String next = sc.next();
               System.out.println(next);
               if (next.contains("data-val-each=")){
                    gpPrice = next.substring(15,22);
                    //System.out.println(gpPrice);
               }
          }
           
           
           
           String[] currencyNames = {"British", "US Dollar", "Canadian Dollar", "Euro", "Australian Dollar", "Swiss Franc",
                                        "Mexican Peso", "Brazilian Real", "Polish Z", "Swedish Krona", "Japanese Yen", "Singapore Dollar",
                                        "Malaysian Ringgit", "Norwegian Krone", "New Zealand Dollar", "Danish Krone", "South African Rand",
                                        "Colombian Peso", "Indian Rupee"};
           
           int[] prices = new int[19];
           
           
                  
          //Extracts real life bond price in British Pounds from wiki        
          String GBPPrice = "";
          
          
          int arrayIndex = 0;
          while(arrayIndex <= 19){
               String price = "";
               String currency = currencyNames[arrayIndex];
               while(price.equals("")){
                    String next = sc.next();
                    System.out.print(next + "");
                    if (next.contains(currency)){
                         for(int i = 0; i <= 7; i++){
                              sc.next();
                         }
                         price = sc.next();
                         price = price.substring(6);
                         currencyNames[arrayIndex] = currency;
                         arrayIndex++;
                    }
               }
          }
          
          //Extracts real life bond price in USD from wiki      
          String USDPrice = "";
          while(USDPrice.equals("")){
               String next = sc.next();
               if (next.contains("US")){
                    for(int i = 0; i <= 7; i++){
                         sc.next();
                    }
                    USDPrice = sc.next();
                    USDPrice = USDPrice.substring(5);
                    //System.out.println(USDPrice);
               }
          }
          
          
          
          
          
          
          
          
          
          
          
          
          
          
          //Construct URL object and Scanner object for rates
          URL rates = new URL("https://www.x-rates.com/table/?from=USD&amount=1");          
          Scanner sc2 = new Scanner(rates.openStream());
          
          //Extracts gbp to usd conversion rate from rate website
          String gbpToUSD = "";
          while(gbpToUSD.equals("")){
               String next = sc2.next();
               //System.out.println(next);             
               if (next.contains("to=GBP")){
                    gbpToUSD = next;
                    gbpToUSD = gbpToUSD.substring(58,64);
                    //System.out.println(gbpToUSD);      
               }
          }


          //Sphaggetti Formatting
          double UsdPriceNum = Double.parseDouble(USDPrice);
          double GbpPriceNum = Double.parseDouble(GBPPrice);
          double gbpToUSDNum = Double.parseDouble(gbpToUSD);         
          float gpPriceNum = Float.parseFloat(gpPrice) / 1000000;       
          float gpGained = gpPriceNum * 10;                   
          GbpPriceNum = GbpPriceNum * gbpToUSDNum;         
          double cheapestPriceNum = 0.0;
          
          
          //Price Comparison
          if (UsdPriceNum >= GbpPriceNum){
               cheapestPriceNum = GbpPriceNum;
               System.out.println("Cheapest Currency: Great British Pounds");
          } else {
               cheapestPriceNum = GbpPriceNum;
               System.out.println("Cheapest Currency: US Dollars");
          }
          
          
          //Sphaggeti formatting
          String cheapestPrice = "" + cheapestPriceNum;
          cheapestPrice = cheapestPrice.substring(0,5);          
          System.out.println("Price: $" + cheapestPrice);         
          double dollarsPerMilNum = cheapestPriceNum / (gpPriceNum*10);         
          String dollarsPerMil = "" + dollarsPerMilNum;         
          dollarsPerMil = dollarsPerMil.substring(0,4);         
          System.out.println("$ / million: " + dollarsPerMil);                           
          String gpGainedFinal = "" + gpGained;
          gpGainedFinal = gpGainedFinal.substring(0,2) + "M";         
          System.out.println("gp gained from 10 bond purchase: " + gpGainedFinal);   
     }    
}