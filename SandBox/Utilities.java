package SandBox;

import java.util.Date;
import java.util.Scanner;

public class Utilities {
    /*this is a worksheet*/
    public static String ShowCurrentdate() {
        Date date = java.util.Calendar.getInstance().getTime();
        String date2String = date.toString();
        return date2String;
    }

    public static LoginScreenModel lsmAttempt() {
        LoginScreenModel lsm = new LoginScreenModel();
        Scanner userInput = new Scanner(System.in);

        System.out.println("Input your login down below please.");
        lsm.setLogin(userInput.nextLine());
        System.out.println("Input your password down below please.");
        lsm.setPassword(userInput.nextLine());

        return lsm;
    }

    public static void loginVerifier() {
        LoginScreenModel lsmInput =lsmAttempt();
        LoginScreenModel lsmDataBase = new LoginScreenModel();/**/
        if (lsmInput!=lsmDataBase){
            System.out.println("Login or password is incorrect");
        }



    }

}
