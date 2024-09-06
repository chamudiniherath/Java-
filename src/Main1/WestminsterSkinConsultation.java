package Main1;

import java.io.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

class  westminsterSkinConsultationManager  implements skinConsultationManager {
    public static ArrayList<Doctor> doctorList = new ArrayList<> (); // store doctor
    public static ArrayList <Consultation> consult = new ArrayList <>();

    public static void sort(ArrayList<Doctor> list) {

        list.sort((o1, o2)
         -> o1.getSurName().compareTo(
                o2.getSurName()));  // compare the surname and sort it
    }


    private static final File file = new File ("Doctor.txt");
    public static westminsterSkinConsultationManager westminsterSkinConsultationManager=new westminsterSkinConsultationManager();

    public void addDoctor (Doctor doctor) {
        doctorList.add (doctor);
        System.out.println ("Added a doctor " + doctorList.size ());
    }

    public void isEmpty() throws IOException {
        if (doctorList.isEmpty ()) {
            System.out.println ("Not yet registered a doctor");
            System.out.print ("Do you want to add a new Doctor ? Then enter \"Y\" or enter \"N\" for navigate to main menu : " );
            if (navigate (Main1.scanner).equalsIgnoreCase("Y")) {
                Main1.AddANewDoctor (Main1.scanner);
            } else {
                Main1.controlMenu (Main1.scanner);
            }
        }
    }

    public String navigate (Scanner scanner) {
        boolean repeater=false;
        String choice;
        do {
            choice = scanner.nextLine ().toLowerCase (Locale.ROOT);
            if (choice.equals ("y")) {
                return "y";
            } else if (choice.equals ("n")) {
                return "n";
            } else {
                repeater = true;
                System.out.print ("input invalid ! ");
            }
        } while (repeater);
        return choice;
    }

    public void saveData () throws FileNotFoundException, IOException  {
        FileOutputStream fileOutputStream = new FileOutputStream (file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream (fileOutputStream);

        try{
            objectOutputStream.writeObject (doctorList);
        }

        catch (IOException e) {
            e.printStackTrace ();
        }
        finally{
            objectOutputStream.close();
            fileOutputStream.close();
        }

    }


    public void deleteDoctor () throws IOException {
        isEmpty (); // call to method empty
        String alignToLeft = "|%-30s  %-21s %n";
        System.out.println (" Delete the doctor");
        System.out.println (" DOCTOR'S LIST");
        System.out.format ("-------------------------------------------------%n");
        System.out.format ("                        Licence Number %n");
        System.out.format ("-------------------------------------------------%n");
        doctorList.forEach (doctor -> System.out.format (alignToLeft, doctor.getName () , doctor.getMedicalLicNumber()));
        boolean x = false;
        System.out.print ("\nEnter the doctor's licence number to delete the doctor from the list : ");
        do {
            String number = Main1.scanner.nextLine ().toLowerCase(Locale.ROOT);
            for (Doctor doctor : doctorList) {
                if (number.equals (doctor.getMedicalLicNumber ())) {
                    doctorList.remove (doctor);
                    x = true;
                    break;
                }
            }
            if (!x) {
                System.out.println ("Entered doctor is not registered ");
                System.out.print ("Try again with a existing doctor : ");

            }
        } while (!x);
        saveData ();//saving the deletion
        System.out.println ("\nDeleted the doctor\n");
        System.out.print ("Do you want to delete another  doctor? \nThen press \"Y\" or press \"N\" to navigate main menu : ");

        // this is navigator guide to run through program

        if (navigate (Main1.scanner).equalsIgnoreCase ("Y")) {
            deleteDoctor ();
        } else {
            Main1.controlMenu (Main1.scanner);
        }
    }

    public void displayDoctorTable () throws IOException {
        isEmpty ();
        sort(doctorList);
        String alignToLeft = "| %-21s | %-21s |%-16s |%-13s |%-14s |%-14s|%n";
        System.out.format("       Surname                     name             Liscence Number  dataOfBirth   mobileNumber    Specialization   %n");



        //doctorStat.sort (Collections.reverseOrder ());//sorting with positionChanger variables placed as descending oder
        doctorList.forEach ((Doctor doctor) -> {
            System.out.format (alignToLeft, ((Doctor) doctor).getSurName (),((Doctor) doctor).getName (),((Doctor) doctor).getMedicalLicNumber(),
                    ((Doctor) doctor).getDateOfBirth(), ((Doctor) doctor).getMobileNumber(),((Doctor) doctor).getSpecialisation() );
        });//



        System.out.print ("\nDo you want to navigate main menu ? \nThen press \"y\" or press \"n\" to exit the program : ");
        if (navigate (Main1.scanner).equalsIgnoreCase ("y")) {
            Main1.controlMenu (Main1.scanner);
        } else {
            System.out.println (" GOOD BYE !");
            System.exit (1);
        }
    }


    public static void loadData () {
        try {
            FileInputStream fileInputStream = new FileInputStream (file);
            ObjectInputStream objectInputStream = new ObjectInputStream (fileInputStream);
            doctorList = (ArrayList<Doctor>) objectInputStream.readObject ();
            fileInputStream.close ();
            objectInputStream.close ();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace ();
        }
    }
}





