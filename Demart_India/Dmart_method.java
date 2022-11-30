package Demart_India;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;


public class Dmart_method implements Dmart_interface{
    Scanner sc = new Scanner(System.in);
    String User_Name_Verify;
    String User_Name;
    String Password;
    String Password_Verify;
    int Total,Total2;
    int New_Price=0;

    @Override
    public void Account_User() {
        // TODO Auto-generated method stub
        System.out.println("WELCOME IN Dmart SUPER MARKET VIRTUAL STORE ");
        System.out.println("Register Your Self First");
        System.out.println("Enter Your Login/User Name: ");
        User_Name = sc.next();
        System.out.println("Enter Your Password: ");
        Password = sc.next();
        System.out.println("Enter Your Mobile No: ");
        Long MobNo = sc.nextLong();
        System.out.println("Credential Save Successfully");
        System.out.println("----------------------------------------------------------");

    }

    @Override
    public void Verify_User() {
        // TODO Auto-generated method stub
        System.out.println("Verify Your Login/User Name: ");
        User_Name_Verify = sc.next();
        System.out.println("Verify Your Password: ");
        Password_Verify = sc.next();
        System.out.println("----------------------------------------------------------");
    }
    
    @Override
    public void Condition_check() {
        // TODO Auto-generated method stub
        if ((User_Name.equals(User_Name_Verify)) && (Password.equals(Password_Verify))) {
            System.out.println("Your password and user name Verify Succsesfully ....Welcome in Dmart_India. !!");
            System.out.println("Have You Order Item From Dmart Super Market Y/N");
            String opt=sc.next();
            if (opt.equals("Y")) {
                Product_list();
                
            } else {
                System.out.println("Thanks For showing Intrest in Dmart Super market !! ");
                System.exit(0);
            }
        } else {
            System.out.println("Not verify Details Please Enter Details Again might You miss some Credentials:");
            Verify_User();
            Condition_check();
        }
    }


    @Override
    public void Product_list() {
        // TODO Auto-generated method stub
        System.out.println("-------------------- Welcome In Online Dmart GROSORY SECTION: --------------------");
        System.out.println("------>>>>>>>>    Enter Your Item with Price and Quantity ");
        System.out.println("Enter the Total Item Numbers: ");
        int n = sc.nextInt();
        try 
        {
            for (int i = 1; i<=n ; i++)
            {
                Class.forName("com.mysql.cj.jdbc.Driver"); // register driver 
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/batch12to2","root","Password"); 
                PreparedStatement st=con.prepareStatement("insert into Dmart values(?,?,?,?,?)");
//                Scanner sc = new Scanner(System.in);
                
                System.out.println("Enter Item Id");
                int id=sc.nextInt();
                
                System.out.println("Enter the Item Name:");
                String name = sc.next();
       
                System.out.println("Enter the Item Quantity: ");
                int Quantity =sc.nextInt();
                
                System.out.println("Enter the Item Price: Rs.");
                int Price =sc.nextInt();
                
                int total= Quantity*Price;
                
                st.setInt(1,id);
                st.setString(2,name);
                st.setInt(3,Quantity);
                st.setInt(4,Price);
                st.setInt(5,total);
                
                int k=st.executeUpdate();
                System.out.println("record inserted successfully"+k);
                System.out.println("==================================================");
                System.out.println("Details of: Item Id:"+id+" Item Name:"+name+" Item Quantity:"+Quantity+" Item Price: Rs."+Price+ " Total Price: Rs."+total);
                New_Price=New_Price+(total);
                System.out.println("Your Effective Price Now is: Rs."+New_Price);
                System.out.println("==================================================");
                
            }
            
            System.out.println("Enter CGST-in % ==");
            int cgst= sc.nextInt();
            System.out.println("Enter SGST-in % ==");
            int sgst= sc.nextInt();

            int cgst_price=(New_Price*(cgst))/100;
            System.out.println("CGST is Rs."+cgst_price);
            int sgst_price=(New_Price*(sgst))/100;
            System.out.println("SGST is Rs."+sgst_price);
            Total=New_Price+cgst_price+sgst_price;
            System.out.println("Total is Rs."+Total);
            System.out.println("==================================================");
            
            System.out.println("Have You Modify Your Order: Y/N");
            String opt=sc.next();
            
            if(opt.equals("Y")) {
                edit_Item();
            }
            
//            --------------------------------------------------------------------------
            System.out.println("Have You Remove Any Item form Your Order: Y/N");
            String opt1=sc.next();
            if(opt1.equals("Y"))
            {
                System.out.println("How many Item Have You Remove form Your Order:");
                try 
                {
                    int n1 = sc.nextInt();
                    for (int i = 1; i<=n1 ; i++)
                    {
                        cancle_Item();
                    }
                } catch (Exception e)
                {
                        // TODO: handle exception
                        System.out.println(e);
                }
            }else{
                Final_Billing();
            }
            
        } catch (Exception e)
        {
                // TODO: handle exception
                System.out.println(e);
        }
                
        }     

            
    
    @Override
    public void Final_Billing() {
        // TODO Auto-generated method stub
        System.out.println("====================================");
        System.out.println("------- Final Billing Section -------");
        System.out.println("====================================");
        
        
        System.out.println("====================================");
        System.out.println("------- Your Order Details  -------");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // register driver 
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/batch12to2","root","Password"); // establish connection with database
            Statement st=con.createStatement();  // for Excecute qury 
            ResultSet rs=st.executeQuery("select*from Dmart");  
            System.out.println("Id  Product  Quantity  Price  Total");
            while(rs.next()){
                System.out.println(rs.getInt(1)+"   "+rs.getString(2)+"     "+rs.getInt(3)+"         "+rs.getInt(4)+"    "+rs.getInt(5));
            } 
            con.close();
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
        }
        
        
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // register driver 
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/batch12to2","root","Password"); // establish connection with database
            Statement st=con.createStatement();  // for Excecute qury 
            ResultSet rs=st.executeQuery("select*from Final_Total");  
            
            while(rs.next()){
                System.out.println("New Price without GST will be Rs."+rs.getInt(1));
                
                int a=rs.getInt(1);
                System.out.println("Enter CGST-in % ==");
                int cgst= sc.nextInt();
                System.out.println("Enter SGST-in % ==");
                int sgst= sc.nextInt();
                int cgst_price=(a*(cgst))/100;
                System.out.println("CGST is Rs."+cgst_price);
                int sgst_price=(a*(sgst))/100;
                System.out.println("SGST is Rs."+sgst_price);
                int Total2=a+cgst_price+sgst_price; 
               
                System.out.println("====================================");
                System.out.println("Final Billing Amount is Rs."+Total2);
                System.out.println("====================================");
                System.out.println("Order Place Successfully .....Further Update recive on register mobile no.");
                System.out.println(" Thanks for Shoping in Dmart Store..!!");
                
            }
            
            con.close();
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
            
        }
        
        
        
        
    }

    @Override
    public void edit_Item() {
        // TODO Auto-generated method stub
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // register driver 
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/batch12to2","root","Password"); // establish connection with database
            PreparedStatement st=con.prepareStatement("update Dmart set Product_Name=?, Product_Quantity=?,Product_Price=?,Total_price=? where Product_Id=?"); 
            Scanner sc = new Scanner(System.in);
            
//            referance Id to change data
            System.out.println("Enter Item Id to be Edited:");
            int id=sc.nextInt();
            
            System.out.println("Enter the Item Name:");
            String name = sc.next();
   
            System.out.println("Enter the Item Quantity: ");
            int Quantity =sc.nextInt();
            
            System.out.println("Enter the Item Price: Rs.");
            int Price =sc.nextInt();
            
            int Total1= Quantity*Price;
            
            
            st.setString(1,name);
            st.setInt(2,Quantity);
            st.setInt(3,Price);
            st.setInt(4,Total1);
            st.setInt(5,id);
            
            
            int k=st.executeUpdate();
            System.out.println("record Updated  successfully"+k);           
            
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
        } 
    }

    @Override
    public void cancle_Item() {
        // TODO Auto-generated method stub
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // register driver 
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/batch12to2","root","Password"); // establish connection with database
            PreparedStatement st=con.prepareStatement("DELETE FROM Dmart WHERE Product_Id=?"); 
            Scanner sc = new Scanner(System.in);
            
            
            System.out.println("Enter Product_Id to delete item: (one at time)");
            int id=sc.nextInt();
            
            st.setInt(1,id);
            
            int k=st.executeUpdate();
            System.out.println("item deleted successfully"+k);
            
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
        }
        Final_Billing();
    }
}
