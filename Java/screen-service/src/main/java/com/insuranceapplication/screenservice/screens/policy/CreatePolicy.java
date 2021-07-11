package com.insuranceapplication.screenservice.screens.policy;

import com.insuranceapplication.screenservice.mainInterface.enums.DataHandlingMethod;
import com.insuranceapplication.screenservice.mainInterface.enums.ResponseType;
import com.insuranceapplication.screenservice.mainInterface.enums.ScreenType;
import com.insuranceapplication.screenservice.methods.PostRequest;
import com.insuranceapplication.screenservice.models.InsuredObjectModel;
import com.insuranceapplication.screenservice.models.PolicyLineModel;
import com.insuranceapplication.screenservice.models.PolicyModel;
import com.insuranceapplication.screenservice.models.VehiclesModel;
import com.insuranceapplication.screenservice.screens.general.DataScreen;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.Scanner;

public class CreatePolicy extends DataScreen {
    int objectItemNo = 1;

    public CreatePolicy() {
        screenTitle = "Create Policy";
        setScreenType(ScreenType.DATA_SCREEN);
        currentMethod = DataHandlingMethod.CREATE;
    }

    @Override
    public void create() {
        PostRequest.setServiceName("policy-service");
        ArrayList<String> vehicleInterface = new ArrayList<String>(Arrays.asList("vehicle type", "brand", "vehicle model", "generation", "engine type", "engine"));
        PolicyModel policy = new PolicyModel();
        PolicyLineModel policyLine = new PolicyLineModel();
        InsuredObjectModel insuredObjectModel = new InsuredObjectModel();
        VehiclesModel vehicleModel = new VehiclesModel();
        Scanner userInput = new Scanner(System.in);

        //setting all policy informations
        System.out.println("Insert customer's ID: ");
        policy.setOwner_id(userInput.nextInt());
        System.out.println("What is type of policy?");
        policy.setType(userInput.next());
        System.out.println("What is status of policy?");
        policy.setStatus(userInput.next());
        System.out.println("Please enter start date of policy in format YYYY-MM-DD: ");
        policy.setStart_date(userInput.next());
        System.out.println("Please enter end date of policy in format YYYY-MM-DD: ");
        policy.setEnd_date(userInput.next());
        System.out.println("Please enter product type: ");
        policy.setProduct_type(userInput.next());
        policy.setAlt_no(policy.getType() + policy.getOwner_id());

        //setting correct postrequest parameteres and sending policy object to policy service
        PostRequest.setEndpoint("createpolicy");
        PostRequest.setResponseType(ResponseType.SINGLE);
        PostRequest.send(policy);

        //setting all policy line information
        System.out.println("Please enter policy ID: ");
        policyLine.setPolicy_no(userInput.nextInt());
        System.out.println("Please enter product line type: ");
        policyLine.setProduct_line_type(userInput.next());

        //setting correct postrequest parameteres and sending policyline object to policy service
        PostRequest.setEndpoint("createpolicyline");
        PostRequest.setResponseType(ResponseType.SINGLE);
        PostRequest.send(policyLine);

          /*       WORKING ON TAKING correct policy no from db            */
//        PostRequest.setEndpoint("getpolicyid");
//        PostRequest.setResponseType(ResponseType.SINGLE);
//        policy_no = PostRequest.send(policy);
//        System.out.println(policy_no);
//        System.out.println("policy no: " + policy_no.toString());
//        policyLine.setPolicy_no(policy_no);
//        JSONObject policy_id = (JSONObject) PostRequest.send(policy);
//        System.out.println("policy no: " + policy_id);
//        policyLine.setPolicy_no(policy_id);
//        System.out.println("Please enter policy line type: ");
//        String policyLineType = userInput.next();
//        policyLine.setProduct_line_type(policyLineType);
//        policyLine.setPolicy_no(33); //find how to use correct policy no from db

        JSONArray responseArray = null;
        while (true) {
            if (objectItemNo <= 7) {
                PostRequest.setEndpoint("getvehicles");
                PostRequest.setResponseType(ResponseType.ARRAY);
                responseArray = (JSONArray) PostRequest.send(vehicleModel);

                if (objectItemNo < 7) {
                    System.out.println("Please enter " + vehicleInterface.get(objectItemNo - 1) + ":");
                }

                for (int i = 0; i < responseArray.size(); i++) {
                    if (objectItemNo < 7) {
                        System.out.println(i + 1 + ". " + responseArray.get(i));
                    } else {
                        System.out.println("Vehicle id from database: " + responseArray.get(i));
                    }
                }
                switch (objectItemNo) {
                    case 1:
                        vehicleModel.setVehicle_type((String) responseArray.get(userInput.nextInt() - 1));
                        break;
                    case 2:
                        vehicleModel.setBrand((String) responseArray.get(userInput.nextInt() - 1));
                        break;
                    case 3:
                        vehicleModel.setVehicle_model((String) responseArray.get(userInput.nextInt() - 1));
                        break;
                    case 4:
                        vehicleModel.setGeneration((String) responseArray.get(userInput.nextInt() - 1));
                        break;
                    case 5:
                        vehicleModel.setEngine_type((String) responseArray.get(userInput.nextInt() - 1));
                        break;
                    case 6:
                        vehicleModel.setEngine((String) responseArray.get(userInput.nextInt() - 1));
                        break;
                    case 7:
                        Long vehicle_id = (Long) responseArray.get(0);
                        vehicleModel.setVehicle_id(vehicle_id.intValue());
                        insuredObjectModel.setN01(vehicleModel.getVehicle_id());
                        System.out.println("Please enter VIN number: ");
                        insuredObjectModel.setC01(userInput.next());
                        System.out.println("Please enter registration number: ");
                        insuredObjectModel.setC02(userInput.next());
                        PostRequest.setEndpoint("createinsuredobject");
                        PostRequest.setResponseType(ResponseType.SINGLE);
                        PostRequest.send(insuredObjectModel);
                        System.out.println("You have succesfully created object");
                        return;
                }
                objectItemNo++;
            }
        }
    }
}
