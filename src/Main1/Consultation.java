package Main1;

import java.time.LocalDate;
import java.time.LocalTime;

class Consultation extends Patient{
    private String drID;
    private int cost;
    private LocalTime consultStart;
    private LocalTime consultEnd;
    private LocalDate date;
    private String notes;

    public Consultation(String name, String surName,String dateOfBirth,String mobileNumber,int patientId,String drID, LocalTime consulStart, LocalTime consulEnd , LocalDate date,String notes,int charges) {
        super(name, surName,dateOfBirth,mobileNumber,patientId);
        this.drID = drID;
        this.cost= cost;
        this.date =date;
        this.notes=notes;
        this.consultStart = consulStart;
        this.consultEnd = consulEnd;
    }
    public LocalTime getConsulStart()
    {return consultStart;}
    public void setConsulStart(LocalTime consulStart)
    {this.consultStart=consulStart;}

    public LocalTime getConsulEnd()
    {return consultEnd;}
    public void setConsulEnd(LocalTime consulEnd)
    {this.consultEnd=consulEnd;}

    public String getnotes()
    {return notes;}
    public void setnotes(String notes)
    {this.notes=notes;}


    public String getdrID() {
        return drID;
    }

    public void setdrID(String drID) {
        this.drID = drID;
    }

    public int getcost() {
        return cost;
    }

    public void setcharges(int charges) {
        this.cost= charges;
    }

    public LocalDate getdate() {
        return date;
    }

    public void setdateAndTime(LocalDate date) {
        this.date =date;
    }



    @Override
    public String toString() {
        return  "01.Name                : " + this.getName() + '\n' +
                "02.SurName             : " + this.getSurName() + '\n' +
                "03.Date-of-Birth       : " + this.getDateOfBirth() + '\n' +
                "04.Mobile-No           : " + this.getMobileNumber() + '\n' +
                "05.Patient-ID          : " + this.getpatientId() + '\n' +
                "06.Consultation-Date   : " + date +'\n'+
                "07.Start-Time          : " + consultStart +'\n'+
                "08.End-Time            : " + consultEnd +'\n'+
                "09.Consulted-Doctor    : " + drID +'\n'+
                "10.Cost-for-Consulation: " + cost+'\n'+
                "11.Additional-Note  : \n" + notes +'\n';
    }
}





