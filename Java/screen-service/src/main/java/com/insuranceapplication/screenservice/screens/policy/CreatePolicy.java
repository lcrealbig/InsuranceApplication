package com.insuranceapplication.screenservice.screens.policy;

import com.insuranceapplication.screenservice.mainInterface.enums.DataHandlingMethod;
import com.insuranceapplication.screenservice.mainInterface.enums.RequestMethod;
import com.insuranceapplication.screenservice.mainInterface.enums.ResponseType;
import com.insuranceapplication.screenservice.mainInterface.enums.ScreenType;
import com.insuranceapplication.screenservice.methods.Requests;
import com.insuranceapplication.screenservice.models.*;
import com.insuranceapplication.screenservice.screens.general.DataScreen;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.text.SimpleDateFormat;
import java.util.*;

public class CreatePolicy extends DataScreen {
    int objectItemNo = 1;

    public CreatePolicy() {
        screenTitle = "Create Policy";
        setScreenType(ScreenType.DATA_SCREEN);
        currentMethod = DataHandlingMethod.CREATE;
    }

    @Override
    public void create() {
        Requests.setRequestMethod(RequestMethod.POST);
        Requests.setServiceName("policy-service");
        ArrayList<String> vehicleInterface = new ArrayList<String>(Arrays.asList("vehicle type", "brand", "vehicle model", "generation", "engine type", "engine"));
        TransactionModel transactionModel = new TransactionModel();
        PolicyModel policyModel = new PolicyModel();
        PolicyLineModel policyLineModel = new PolicyLineModel();
        InsuredObjectModel insuredObjectModel = new InsuredObjectModel();
        VehiclesModel vehicleModel = new VehiclesModel();
        Scanner userInput = new Scanner(System.in);

        //creating transactions model so we can use unique transaction id for all policy information
        transactionModel.setTransactionType("create");
        transactionModel.setModifiedBy("Slowik");  //for now user is hardcoded, need to refactor it so we will pass current logged user
        SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        transactionModel.setTimestamp(sdf.format(date));

        //passing transactions to db
        Requests.setEndpoint("createtransaction");
        Requests.setResponseType(ResponseType.SINGLE);
        Requests.send(transactionModel);
        System.out.println("Please wait...");
        pause(1000);

        //getting transaction id from db
        Long transactionId = null;
        Requests.setEndpoint("gettransactionid");
        JSONObject transaction = (JSONObject) Requests.send(transactionModel);
        transactionId = (Long) transaction.get("transactionId");

        //setting all policy informations
        policyModel.setTransactionId(Math.toIntExact(transactionId));
        System.out.println("Insert customer's ID: ");
        policyModel.setOwnerId(userInput.nextInt());
        System.out.println("What is type of policy?");
        policyModel.setType(userInput.next());
        System.out.println("What is status of policy?");
        policyModel.setStatus(userInput.next());
        System.out.println("Please enter start date of policy in format YYYY-MM-DD: ");
        policyModel.setStartDate(userInput.next());
        System.out.println("Please enter end date of policy in format YYYY-MM-DD: ");
        policyModel.setEndDate(userInput.next());
        System.out.println("Please enter product type: ");
        policyModel.setProductType(userInput.next());
        policyModel.setAltNo(policyModel.getType() + policyModel.getOwnerId());

        //setting correct postrequest parameteres and sending policy object to policy service
        Requests.setEndpoint("createpolicy");
        Requests.send(policyModel);
        System.out.println("Please wait...");
        pause(2000);  //had to make program stop for a while, because if user types too fast it creates issues with connecting to db

        //getting policy no from db
        Requests.setEndpoint("getpolicy");
        Requests.send(policyModel);
        JSONObject policy = (JSONObject) Requests.send(policyModel);
        Long policyNo = (Long) policy.get("policyId");

        //setting all policy line information
        policyLineModel.setPolicyNo(Math.toIntExact(policyNo));
        policyLineModel.setTransactionId(Math.toIntExact(transactionId));
        System.out.println("Please enter product line type: ");
        policyLineModel.setProductLineType(userInput.next());

        //setting correct postrequest parameteres and sending policyline object to policy service
        Requests.setEndpoint("createpolicyline");
        Requests.send(policyLineModel);

        //getting policy line no from db
        Requests.setEndpoint("getpolicyline");
        Requests.send(policyLineModel);
        JSONObject policyLine = (JSONObject) Requests.send(policyLineModel);
        Long policyLineNo = (Long) policyLine.get("lineId");

        //code below is responsible for creating insured object model and sending it to policy service
        JSONArray responseArray = null;
        while (true) {
            if (objectItemNo <= 7) {
                Requests.setEndpoint("getvehicles");
                Requests.setResponseType(ResponseType.ARRAY);
                responseArray = (JSONArray) Requests.send(vehicleModel);

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
                        vehicleModel.setVehicleType((String) responseArray.get(userInput.nextInt() - 1));
                        break;
                    case 2:
                        vehicleModel.setBrand((String) responseArray.get(userInput.nextInt() - 1));
                        break;
                    case 3:
                        vehicleModel.setVehicleModel((String) responseArray.get(userInput.nextInt() - 1));
                        break;
                    case 4:
                        vehicleModel.setGeneration((String) responseArray.get(userInput.nextInt() - 1));
                        break;
                    case 5:
                        vehicleModel.setEngineType((String) responseArray.get(userInput.nextInt() - 1));
                        break;
                    case 6:
                        vehicleModel.setEngine((String) responseArray.get(userInput.nextInt() - 1));
                        break;
                    case 7:
                        Long vehicle_id = (Long) responseArray.get(0);
                        vehicleModel.setVehicleId(vehicle_id.intValue());
                        insuredObjectModel.setN01(vehicleModel.getVehicleId());
                        insuredObjectModel.setPolicyLineNo(Math.toIntExact(policyLineNo));
                        insuredObjectModel.setTransactionId(Math.toIntExact(transactionId));
                        System.out.println("Please enter VIN number: ");
                        insuredObjectModel.setC01(userInput.next());
                        System.out.println("Please enter registration number: ");
                        insuredObjectModel.setC02(userInput.next());
                        Requests.setEndpoint("createinsuredobject");
                        Requests.setResponseType(ResponseType.SINGLE);
                        Requests.send(insuredObjectModel);
                        System.out.println("You have succesfully created object");
                        return;
                }
                objectItemNo++;
            }
        }
    }
    public static void pause(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            System.err.format("IOException: %s%n", e);
        }
    }
}
