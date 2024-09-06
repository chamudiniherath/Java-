package Main1;

import java.io.Serializable;

class Doctor extends Person implements Serializable {
    private String medicalLicNumber;
    private String specialisation;

    Doctor(String name, String surName, String dateOfBirth, String mobileNumber,String  medicalLicNumber,String specialisation) {
        super(name, surName, dateOfBirth, mobileNumber);
        this.medicalLicNumber=medicalLicNumber;
        this.specialisation=specialisation;
    }




    public void setMedicalLicNumber(String medicalLicNumber) {
        this.medicalLicNumber = medicalLicNumber;
    }
    public String getMedicalLicNumber() {
        return medicalLicNumber;
    }
    public void setSpecialisation(String specialization){
        this.specialisation=specialisation;
    }
    public String getSpecialisation(){
        return specialisation;
    }
}

