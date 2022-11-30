package Demart_India;

import java.util.Scanner;

public class Dmart_Main extends Dmart_method {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Dmart_method oc= new Dmart_method();
        oc.Account_User();
        oc.Verify_User();
        oc.Condition_check();
        
        
        Scanner sc = new Scanner(System.in);
        System.out.println("For new Order select following option: Y/N");
        String opt=sc.next();
        if(opt.equals("Y")) {
            oc.Account_User();
        }else {
            System.out.println("==========================================");
            System.out.println("Thank You using Dmart Virtual Store..!!!!");
            System.out.println("===========================================");
            System.exit(0);
        }
    }

}
