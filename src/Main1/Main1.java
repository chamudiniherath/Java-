package Main1;

import java.io.IOException;
import java.io.Serializable;
import java.util.Locale;
import java.util.Scanner;

import static Main1.westminsterSkinConsultationManager.doctorList;
import static java.lang.System.exit;

public class Main1 implements Serializable {
    public static Scanner scanner = new Scanner(System.in);
    public static boolean repeater;
    public static String detail;
    public static String enterData;
    public static String reEnter;
    public static String doctorName;
    public static String doctorSurName;
    public static String doctorDateOfBirth;
    public static String doctormobilenumber;
    public static String medicalLicenceNumber;
    public static String specialisation;


    public static String validatorString = "[a-zA-Z.  ]*"; //validating string
    public static String validate = "[0-9/]*";
    public static  westminsterSkinConsultationManager westminsterSkinConsultationManager = new westminsterSkinConsultationManager ();
    public static void main(String[] args) throws IOException {
        westminsterSkinConsultationManager.loadData();
        controlMenu(scanner);
        String choice=scanner.nextLine();



    }

    public static void controlMenu(Scanner scanner) throws IOException { // ask user inputs
        System.out.println();
        System.out.println("A.Add a new doctor ");
        System.out.println("B.Delete a doctor ");
        System.out.println("C.Display the list");
        System.out.println("D.Exit the program");
        System.out.println("E.call the GUI Console");
        while (!repeater) {
            System.out.println("Please enter your choice : ");
            String choice = scanner.nextLine().toLowerCase(Locale.ROOT);

            while (!repeater) {
                switch (choice) {
                    case "a" : {
                        System.out.println("----Adding a doctor----");
                        AddANewDoctor(scanner);
                        repeater = true;
                        break;
                    }

                    case "b" :{
                        System.out.println("----Delete a doctor----");
                        westminsterSkinConsultationManager.deleteDoctor();
                        repeater=true;
                        break;
                    }
                    case "c":{
                        System.out.println("----Print list of the doctors----");
                        westminsterSkinConsultationManager.displayDoctorTable();
                        repeater=true;
                        break;
                    }
                    case "d":{
                        System.out.println("----Do you want to exit the program---- ");
                        exit(0);
                    }
                    case "e":{
                        System.out.println("----Calling GUI----");
                        new GuiMain();
                        repeater=true;
                        break;
                    }
                    default : System.out.println("\nplease read the instruction and try a again \n");
                        break;
                }


            }

        }
    }

    public static void doctorInformationCollector (Scanner scanner, String wordChanger,String validator) {
        System.out.print ("Please enter "+wordChanger);
        detail = scanner.nextLine ();//

        //wrong input  catcher
        if (detail == null || !detail.matches (validator)||  detail.equals ("")) {
            System.out.println (wordChanger);
            doctorInformationCollector (scanner, reEnter,validator);
        }
        enterData = detail.toLowerCase(Locale.ROOT);
    }

    public static void AddANewDoctor(Scanner scanner) throws IOException {
        reEnter = "doctor Name: ";
        doctorInformationCollector (scanner, "doctor Name : ",validatorString);
        doctorName = enterData;

        reEnter = "doctor SurName: ";
        doctorInformationCollector (scanner, "doctor SurName : ",validatorString);
        doctorSurName = enterData;

        reEnter = "doctor data Of Birth: ";
        doctorInformationCollector (scanner, "doctor data Of Birth : ",validate);
        doctorDateOfBirth = enterData;

        reEnter = "doctor mobile Number: ";
        doctorInformationCollector (scanner, "doctor mobile Number : ",validate);
        doctormobilenumber = enterData;

        reEnter = "doctor medical Licence Number: ";
        doctorInformationCollector (scanner, "doctor medical Licence Number : ",validate);
        medicalLicenceNumber = enterData;

        reEnter = "doctor specialisation: ";
        doctorInformationCollector (scanner, "doctor specialisation : ",validatorString);
        specialisation = enterData;

        Doctor doctor = new Doctor (doctorName, doctorSurName, doctorDateOfBirth, doctormobilenumber,medicalLicenceNumber, specialisation);
        westminsterSkinConsultationManager.addDoctor(doctor);//saving method
        System.out.println ("Do you want to add another doctor ?");
        System.out.print ("if it is yes ? then press \"y\" or enter \"n\" to navigate main menu : ");
        boolean repeater = false;
        if(doctorList.size() <10){
            do {
                String choice = scanner.nextLine ().toLowerCase (Locale.ROOT);
                if (choice.equals ("y")) {
                    AddANewDoctor (scanner);
                } else if (choice.equals ("n")) {
                    westminsterSkinConsultationManager.saveData();

                    controlMenu (scanner);
                } else {
                    repeater = true;
                    System.out.print ("input invalid! ");
                }
            } while (repeater);
        }
        else
        {System.out.println("Can only add maximum 10 doctors");}

    }
}


