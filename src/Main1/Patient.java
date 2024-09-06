package Main1;

class Patient extends Person{
    private int patientId;

    Patient(String name, String surName, String dateOfBirth, String mobileNumber,int patientId){
        super(name,surName,dateOfBirth,mobileNumber);
        this.patientId=patientId;
    }
    public void setpatientId(int patientId){
        this.patientId=patientId;
    }
    public int getpatientId(){
        return patientId;
    }
}
