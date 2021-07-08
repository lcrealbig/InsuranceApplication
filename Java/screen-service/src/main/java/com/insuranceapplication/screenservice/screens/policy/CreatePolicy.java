package com.insuranceapplication.screenservice.screens.policy;

import com.insuranceapplication.screenservice.mainInterface.enums.DataHandlingMethod;
import com.insuranceapplication.screenservice.mainInterface.enums.ScreenType;
import com.insuranceapplication.screenservice.methods.PostRequest;
import com.insuranceapplication.screenservice.models.PolicyLineModel;
import com.insuranceapplication.screenservice.models.PolicyModel;
import com.insuranceapplication.screenservice.screens.general.DataScreen;
import java.util.Scanner;

public class CreatePolicy extends DataScreen {


    public CreatePolicy() {
        screenTitle = "Create Policy";
        setScreenType(ScreenType.DATA_SCREEN);
        currentMethod = DataHandlingMethod.CREATE;
    }


    @Override
    public void create() {
        super.create();
        PolicyModel policy = new PolicyModel();
        PolicyLineModel policyLine = new PolicyLineModel();

        Scanner userInput = new Scanner(System.in);

        System.out.println("Insert customer's ID: ");
        int customerID = userInput.nextInt();
        policy.setOwner_id(customerID);

        System.out.println("What is type of policy?");
        String type = userInput.next();
        policy.setType(type);

        System.out.println("What is status of policy?");
        String status = userInput.next();
        policy.setStatus(status);

        System.out.println("Please enter start date of policy in format YYYY-MM-DD: ");
        String startDate = userInput.next();
        policy.setStart_date(startDate);

        System.out.println("Please enter end date of policy in format YYYY-MM-DD: ");
        String endDate = userInput.next();
        policy.setEnd_date(endDate);

        System.out.println("Please enter product type: ");
        String productType = userInput.next();
        policy.setProduct_type(productType);

        policy.setAlt_no(type + customerID);
        PostRequest.setServiceName("policy-service");
        PostRequest.setEndpoint("createpolicy");
        PostRequest.send(policy);


//        System.out.println("Please enter policy line type: ");
//        String policyLineType = userInput.next();
//        policyLine.setProduct_line_type(policyLineType);
//        policyLine.setPolicy_no(33); //find how to use correct policy no from db



//        GetRequest.getRequest("getvehicles");
    }
}
