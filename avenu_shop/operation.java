package avenu_shop;
import java.util.*;

public class operation implements inter_Avenu {
    Scanner sc = new Scanner(System.in);
    String User_Name_Verify;
    String User_Name;
    String Password;
    String Password_Verify;
    int Total;

    @Override
    public void Account_User() {
        // TODO Auto-generated method stub
        System.out.println("WELCOME IN AVENU SUPER MARKET VIRTUAL STORE ");
        System.out.println("Register Your Self First");
        System.out.println("Enter Your Login/User Name: ");
        User_Name = sc.next();
        System.out.println("Enter Your Password: ");
        Password = sc.next();
        System.out.println("Enter Your Mobile No: ");
        int MobNo = sc.nextInt();
        System.out.println("Credential Save Successfully");

    }

    @Override
    public void Verify_User() {
        // TODO Auto-generated method stub
        System.out.println("Verify Your Login/User Name: ");
        User_Name_Verify = sc.next();
        System.out.println("Verify Your Password: ");
        Password_Verify = sc.next();
    }
    
    @Override
    public void Condition_check() {
        // TODO Auto-generated method stub
        if ((User_Name.equals(User_Name_Verify)) && (Password.equals(Password_Verify))) {
            System.out.println("Have You Order Item From Avenu Super Market Y/N");
            String opt=sc.next();
            if (opt.equals("Y")) {
                Product_list();
                Final_Billing();
            } else {
                System.out.println("Thanks For showing Intrest in Avenu Super market !! ");
                System.exit(0);
            }
        } else {
            System.out.println("Not verify Please Enter Details Again You miss some Credentials:");
            Verify_User();
        }
    }


    @Override
    public void Product_list() {
        // TODO Auto-generated method stub
        System.out.println("Welcome In Online GROSORY SECTION:");
        System.out.println("Enter Your Item with Price and Quantity ");
        System.out.println("Enter the Total Item Numbers: ");
        int n = sc.nextInt();
        int New_Price=0;
        for (int i = 1; i<=n ; i++) {
            System.out.println("Enter the Item Name:");
            String item = sc.next();
            System.out.println("Enter the Item Quantity: ");
            int Quantity =sc.nextInt();
            System.out.println("Enter the Item Price: Rs.");
            int Price =sc.nextInt();
            System.out.println("Details is: Item Name:"+item+" Item Quantity:"+Quantity+" Item Price: Rs."+Price);
            New_Price=New_Price+(Price*Quantity);
            System.out.println("Your Effective Price Now is: Rs."+New_Price);
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
    }


    @Override
    public void Final_Billing() {
        // TODO Auto-generated method stub
        System.out.println("Final Billing Amount is Rs."+Total);
    }



    
}
