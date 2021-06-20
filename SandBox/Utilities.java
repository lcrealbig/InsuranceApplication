package SandBox;

import java.util.Date;
import java.util.Scanner;

public class Utilities extends LoginScreenModel {
    /*this is a worksheet*/
    public static Scanner userInput = new Scanner(System.in);


    public static LoginScreenModel lsmInput() {

        LoginScreenModel lsm = new LoginScreenModel();
        System.out.println("Input your login down below please.");
        lsm.setLogin(userInput.nextLine());
        System.out.println("Input your password down below please.");
        lsm.setPassword(userInput.nextLine());

        return lsm;
    }

    public static void loginVerifier() {

        LoginScreenModel lsmInput = lsmInput();
        LoginScreenModel lsmDataBase = new LoginScreenModel("login", "password");/**/
        if (lsmInput.login.equals(lsmDataBase.login) && lsmInput.password.equals(lsmDataBase.password)) {
            System.out.println("You are logged In!");
        } else { System.out.println("Login or password is incorrect");
        loginVerifier();
        }
    }

    public static void secondScreen() {
        System.out.println("Hello User which action will you perform ?");
        System.out.println("Press '1' for policy management.");
        System.out.println("Press '2' to create a customer.");
        String userChoice = userInput.nextLine();
        if (userChoice.equals("1")){
            policyManagement();
        }
        if (userChoice.equals("2")){
            System.out.println("going next screen= customer");
        }
    }
    public static void policyManagement(){
        System.out.println("Press '1' to create a policy.");
        System.out.println("Press '2' to edit the policy.");
        System.out.println("Press '3' to delete the policy.");
        System.out.println("Press '4' to search a policy.");
        String userChoice = userInput.nextLine();
        switch (userChoice){
            case "1": PolicyUtilities.createPolicy();
            break;
            case "2": PolicyUtilities.editPolicy();
            break;
            case "3": PolicyUtilities.deletePolicy();
            break;
            case "4": PolicyUtilities.searchPolicy();
        }



    }
}
