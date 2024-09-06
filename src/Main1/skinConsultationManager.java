package Main1;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

interface skinConsultationManager {
    public  static void sort(){}
    public void addDoctor(Doctor doctor);
    public void isEmpty()throws IOException;
    public String navigate (Scanner scanner);
    public void saveData () throws FileNotFoundException, IOException;
    public void deleteDoctor () throws IOException;
    public void displayDoctorTable () throws IOException;
    public static void loadData (){}
}

