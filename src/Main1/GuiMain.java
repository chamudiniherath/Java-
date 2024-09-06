package Main1;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static Main1.westminsterSkinConsultationManager.consult;
import static Main1.westminsterSkinConsultationManager.doctorList;

class GuiMain extends GUITable implements ActionListener {
    private JButton button1,button2;

    public GuiMain(){ //main gui interface ,

        JLabel label = new JLabel();
        label.setText("Skin Consultation Manager");
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.BOTTOM);
        label.setBounds(10,2,800,700);
        label.setFont(new Font("SansSerif", Font.BOLD, 16));
        label.setBackground(Color.gray);
        label.setOpaque(true);

        JLabel label1 = new JLabel();
        label1.setBounds(0,0,1000,700);
        label1.setBackground(Color.gray);
        label1.setOpaque(true);
        button();

        this.add(label);
        this.add(label1);

        window("Westminster Skin Consultation Manager",700,500);
    }
    public void button_set(JButton button,String name , int y) {
        button.setBounds(420,y,350,40);
        button.setText(name);
        button.setFont(new Font(Font.SERIF,Font.BOLD,20));
        button.setForeground(new Color(0000));
        button.setFocusable(false);
        button.setBackground(Color.red);
        button.addActionListener(this);
        this.add(button);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==button1) {
            this.dispose();
            new CFrame();
        } else if (e.getSource()==button2) {
            this.dispose();
            new DFrame(true);

        }
    }
    @Override
    public void button() {
        button1 = new JButton();
        button_set(button1,"CONSULT A DOCTOR",160);
        button2 = new JButton();
        button_set(button2,"DOCTOR INFORMATION",260);


    }
}

abstract class GUITable extends JFrame {
    public abstract void button();
    public void window(String name,int width,int height){
        setSize(width,height);
        setTitle(name);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());
        setVisible(true);


    }

}

class DFrame extends GUITable implements ActionListener {

    private JButton back,reset,sort;

    DFrame( boolean cat){

        JLabel topic = new JLabel();
        topic.setText("Doctor Information");
        topic.setBounds(300,20,650,20);
        topic.setFont(new Font(Font.DIALOG_INPUT,Font.BOLD,20));


        JLabel colum = new JLabel();
        colum.setText("|  Name   | SurName |   Birthday    | Phone-Number | Medical Licence |Specialisation|");
        colum.setBounds(33,-20,850,300);
        colum.setFont(new Font(Font.DIALOG_INPUT,Font.BOLD,15));
        colum.setForeground(new Color(3, 51, 34, 255));


        DefaultTableModel tableModel = new DefaultTableModel(0,6);
        JTable table = new JTable(tableModel);
        table.setBounds(35,100,800,300);
        if (cat){
            for (Doctor doctor : doctorList) {
                String[] details = {doctor.getName(), doctor.getSurName(), doctor.getDateOfBirth(), doctor.getMobileNumber(), doctor.getMedicalLicNumber(), doctor.getSpecialisation()};
                tableModel.addRow(details);

            }
        }else{
            System.out.println("sort");
            int i = doctorList.size();
            String [] sort = new String[i];
            for (int j = 0; j < i;j++) {
                sort[j]=doctorList.get(j).getSurName();
            }

            Arrays.sort(sort);
            for (String s : sort) {
                for (Doctor doctor : doctorList) {
                    if (s.equals(doctor.getSurName())) {
                        String[] details = {doctor.getName(), doctor.getSurName(), String.valueOf(doctor.getDateOfBirth()),doctor.getMobileNumber(), doctor.getMedicalLicNumber(), doctor.getSpecialisation()};
                        tableModel.addRow(details);
                    }
                }
            }
        }

        this.add(topic);
        this.add(colum);
        this.add(table);
        button();
        window("All Doctors Exist",800,500);

    }


    public void button_set(JButton but,String name, int x, int y , int width , int height) {
        but.setBounds(x,y,width,height);
        but.setText(name);
        but.setFocusable(false);
        but.addActionListener(this);
        this.add(but);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==back) {
            this.dispose();
            new GuiMain();
        } else if (e.getSource()==reset) {
            this.dispose();
            new DFrame(true);

        } else if (e.getSource()==sort){
            this.dispose();
            new DFrame(false);
        }

    }
    @Override
    public void button() {
        back = new JButton();
        back.setFont(new Font("SansSerif", Font.BOLD, 12));
        button_set(back,"Back",30,20,65,30);

        reset = new JButton();
        button_set(reset,"reset",40,410,65,30);

        sort = new JButton();
        button_set(sort,"sort",750,410,80,30);

    }
}

class CFrame1 extends GUITable implements ActionListener { //consultation
    private JButton back;
    private  JButton Cancel;
    private  JButton ok;

    JTextField getname;
    CFrame1(){

        JLabel topic = new JLabel();
        topic.setText("CONSULTATION");
        topic.setBounds(290,20,550,20);
        topic.setFont(new Font(Font.DIALOG_INPUT,Font.BOLD,20));


        int loc = (consult.size()-1);

        JLabel details = new JLabel();
        details.setText("<html>"+
                "01.Name    : "+consult.get(loc).getName()+"<br>"+
                "02.Surname : "+consult.get(loc).getSurName()+"</html>");
        details.setBounds(39, 10, 400, 300);
        details.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 15));

        this.add(topic);
        this.add(details);
        button();
    }

    public void button_set(JButton but,String name, int x, int y , int width , int height) {
        but.setBounds(x,y,width,height);
        but.setText(name);
        but.setFocusable(false);
        but.addActionListener(this);
        this.add(but);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()== ok) {
            this.dispose();
            new GuiMain();
        }


    }
    @Override
    public void button() {

        ok = new JButton();
        button_set(ok,"OK",290,300,80,30);

    }
}
class CFrame extends GUITable implements ActionListener {


    private final JComboBox getdura;
    private String name,surname,phonenum,docconsulId,note,sttimeHou,sttimeMin,stasettime,entimeHou,dateOfBirth;

    private String filename = null;

    private int cost,patId;
    private int again = 0;

    private LocalDate cousultDate;

    private LocalTime consultStart,consulEnd,endsettime;
    private JButton back,Cancel,submit,pic;
    JTextField getname,getbirthday,getid,getdate,getsurname,getphone;
    JComboBox getdoc,getstarttime1,getstarttime2, getduration;
    JTextArea getnote;
    JLabel topic,colum,topic1,jname,birthday,id,time,date,jnote,addpho,addphopath;
    CFrame(){


        topic = new JLabel();
        topic.setText("ALL DOCTORS DETAILS");
        set_lable(topic,290,20,550,20,20);


        colum = new JLabel();
        colum.setText("|     Name     |    SurName    |   Mobile Number   | Medical Licence number | Specialisation |");
        colum.setForeground(new Color(10, 246, 218, 255));
        set_lable(colum,39, -70, 750, 300,15);


        DefaultTableModel tableModel = new DefaultTableModel(0,5);
        JTable table = new JTable(tableModel);
        table.setBounds(40, 100, 700, 240);

        for (Doctor doctor : doctorList) {
            String[] details = {doctor.getName(), doctor.getSurName(), doctor.getMobileNumber(), doctor.getMedicalLicNumber(), doctor.getSpecialisation()};
            tableModel.addRow(details);
        }

        topic1 = new JLabel();
        topic1.setText("DOCTOR CONSULTATION");
        set_lable(topic1,280, 360, 550, 20,20);


        jname = new JLabel();
        jname.setText("Name                 :                                                               Surname:");
        set_lable(jname,30, 400, 550, 20,15);


        getname = new JTextField();
        set_textfild(getname,230,400,150,20);

        getsurname = new JTextField();
        set_textfild(getsurname,730,400,150,20);


        birthday = new JLabel();
        birthday.setText("Birthday(yyyy-mm-dd)  :                                          Mobile Number:");
        set_lable(birthday,30, 440, 550, 20,15);


        getbirthday = new JTextField();
        set_textfild(getbirthday,230,440,150,20);

        getphone = new JTextField();
        set_textfild(getphone,730,440,150,20);

        id = new JLabel();
        id.setText("Patient ID           :                                                       Doctor Licence ID :");
        set_lable(id,30, 490, 800, 20,15);


        getid = new JTextField();
        set_textfild(getid,230,490,150,20);


        String [] doc = new String[doctorList.size()];
        for(int i = 0;doctorList.size()>i;i++){
            doc[i]=doctorList.get(i).getMedicalLicNumber();
        }
        getdoc =new JComboBox(doc);
        set_combobox(getdoc,730,490,150,20);

        time = new JLabel();
        time.setText("Consultation Start Time:                                        Consultation Duration:");
        set_lable(time,30, 520, 900, 20,15);


        String[] hours = { "HH","08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20","21","22" };// hours for consultatio
        String[] minutes = { "MM","00", "15", "30", "45" }; // minutes of the hours
        String[] duration = { "HH","01", "02", "03", "04", "05"}; // consultation duration


        getstarttime1 =new JComboBox(hours);
        set_combobox(getstarttime1,260,520,50,20);


        getstarttime2 =new JComboBox(minutes);
        set_combobox(getstarttime2,340,520,50,20);

        getdura =new JComboBox(duration);
        set_combobox(getdura,730,520,150,20);



        date = new JLabel();
        date.setText("Consultation Date(yyyy-mm-dd):");
        set_lable(date,30, 550, 550, 20,15);


        getdate = new JTextField();
        set_textfild(getdate,340,550,150,20);

        jnote = new JLabel();
        jnote.setText("     NOTES :   ");
        set_lable(jnote,30, 580, 550, 20,15);


        getnote = new JTextArea();
        getnote.setBounds(30,610,500,100);
        getnote.setFont(new Font("console",Font.BOLD,15));
        getnote.setLineWrap(true);

        addpho = new JLabel();
        addpho.setBounds(540, 630, 220, 100);
        addpho.setBackground(Color.white);
        addpho.setOpaque(true);

        addphopath = new JLabel();
        addphopath.setBounds(540, 600, 150, 20);
        addphopath.setBackground(Color.white);
        addphopath.setOpaque(true);




        this.add(table);
        this.add(getnote);
        this.add(addpho);
        this.add(addphopath);
        button();
        window("Consultation",1000,1000);
    }
    public void check_equal(){
        int time = Integer.parseInt(entimeHou);
        boolean not_equal = true;
        for (Consultation consultation : consult) {
            if (docconsulId.equals(consultation.getdrID())) {
                if (consultation.getdate().isEqual(cousultDate)) {
                    if (!consultation.getConsulStart().isBefore(consultStart) && !consultation.getConsulEnd().isAfter(consulEnd)) {
                        not_equal = false;
                        break;
                    }
                }
            }
        }
        for(Consultation consultation : consult){
            if(Objects.equals(consultation.getpatientId(), patId)){
                again++;
            }
        }
        if(again==0){
            cost=(time*15);
            System.out.println(cost);

        }else{
            cost = 25*time;
            System.out.println(cost);
        }
        if(not_equal){
            consult.add(new Consultation(name, surname, dateOfBirth, phonenum,patId,docconsulId, consultStart,consulEnd,cousultDate,note,cost));
        }else{
            int docsiz=doctorList.size(); // size of list of doctors
            String [] random = new String[docsiz];
            for(int i =0;docsiz > i ;i++){
                random[i] = doctorList.get(i).getMedicalLicNumber();
            }
            Random rand = new Random();  //create a random object
            int randomIndex = rand.nextInt(random.length);
            docconsulId = random[randomIndex];
            check_equal();
        }
    }
    public void set_combobox(JComboBox combo,int x, int y , int width , int height){
        combo.setBounds(x,y,width,height);
        combo.setBackground(new Color(0xE1DBDB));
        this.add(combo);
    }
    public void set_lable(JLabel lable ,int x, int y , int width , int height,int font){
        lable.setBounds(x,y,width,height);
        lable.setFont(new Font(Font.DIALOG_INPUT,Font.BOLD,font));
        this.add(lable);
    }

    public void set_textfild(JTextField textfild,int x, int y , int width , int height){//150,20
        textfild.setBounds(x,y,width,height);
        textfild.setFont(new Font("console",Font.BOLD,14));
        this.add(textfild);
    }
    public void button_set(JButton but,String name, int x, int y , int width , int height) {
        but.setBounds(x,y,width,height);
        but.setText(name);

        but.setFocusable(false);
        but.addActionListener(this);
        this.add(but);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource()== submit) {
            try {
                String regex = "^[A-Za-z]\\w{2,29}$";
                Pattern p = Pattern.compile(regex);
                name = getname.getText().trim();
                surname = getsurname.getText().trim();
                Matcher f = p.matcher(name);
                Matcher s = p.matcher(surname);
                if (f.matches() && s.matches()) {
                    try {
                        dateOfBirth = getbirthday.getText().trim();
                        phonenum = getphone.getText().trim();

                        if (10 == phonenum.length()) {
                            try {
                                Integer.parseInt(phonenum);
                                try {
                                    patId = Integer.parseInt(getid.getText());
                                    docconsulId = (String) getdoc.getSelectedItem();
                                    sttimeHou = (String) getstarttime1.getSelectedItem();
                                    sttimeMin = (String) getstarttime2.getSelectedItem();
                                    stasettime = sttimeHou + ":" + sttimeMin + ":00";
                                    try {
                                        consultStart = LocalTime.parse(stasettime);
                                        entimeHou = (String) getduration.getSelectedItem();

                                        consulEnd = consultStart.plusHours(Long.parseLong(entimeHou));
                                        System.out.println(consulEnd);

                                        try {
                                            cousultDate = LocalDate.parse(getdate.getText().trim());
                                            if (cousultDate.isAfter(LocalDate.now()) && cousultDate.isBefore(LocalDate.now().plusYears(1))) {
                                                note = getnote.getText();
                                                check_equal();
                                                System.out.println(consult.get(0));
                                                try {
                                                    FileInputStream file = new FileInputStream(addphopath.getText());
                                                    byte data[] = new byte[file.available()];
                                                    file.read(data);
                                                    int i = 0;

                                                    for (byte b : data) {  //Encrypt the data
                                                        data[i] = (byte) (b ^ patId);
                                                        i++;
                                                    }
                                                    FileOutputStream fos = new FileOutputStream(patId + name + again + ".jpg");

                                                    fos.write(data);
                                                    fos.close();
                                                    file.close();
                                                    System.out.println("Encryption");
                                                    this.dispose();
                                                    new GuiMain();


                                                } catch (Exception ignored) {
                                                    this.dispose();
                                                   new CFrame1();
                                                }
                                            } else {   // validation
                                                JOptionPane.showMessageDialog(null, " Entered Consultation Date Again", "Error", JOptionPane.ERROR);
                                            }

                                        } catch (Exception ignored) {
                                            JOptionPane.showMessageDialog(null, "Enter date again!", "Error", JOptionPane.ERROR);
                                        }
                                    } catch (Exception ignored) {
                                        JOptionPane.showMessageDialog(null, " Entered Time and Duration Again", "Error", JOptionPane.ERROR);
                                    }
                                } catch (Exception ignored) {
                                    System.out.println("ffff");
                                    JOptionPane.showMessageDialog(null, "Enter  Phone Number Again!", "Error", JOptionPane.ERROR);
                                }
                            }catch (Exception ignored){
                                JOptionPane.showMessageDialog(null, "Check the Patient ID!", "Error", JOptionPane.ERROR);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Check the Phone Number!", "Error", JOptionPane.ERROR);
                        }
                    }catch (Exception ignored){
                        JOptionPane.showMessageDialog(null, "Check the Birthday!", "Error", JOptionPane.ERROR);
                    }
                } else{
                    JOptionPane.showMessageDialog(null, "Check the  First Name And Surname!", "Error", JOptionPane.ERROR);
                }
            }catch (Exception ignored){
                JOptionPane.showMessageDialog(null,"Getting Wrong","Error", JOptionPane.ERROR);
            }
            this.dispose();
            new CFrame1();

        }else if (e.getSource() == pic) {
            try {
                JFileChooser chooser = new JFileChooser();
                chooser.showOpenDialog(null);
                File f = chooser.getSelectedFile();
                addpho.setIcon(new ImageIcon(f.toString()));
                filename = f.getAbsolutePath();
                addphopath.setText(filename);
            } catch (Exception ignored) {
            }
        } else {
            this.dispose();
            new GuiMain();
        }

    }

    @Override
    public void button () {
        back = new JButton();
        back.setFont(new Font("SansSerif", Font.BOLD, 12));
        button_set(back, "Back", 30, 20, 65, 30);

        pic = new JButton();
        button_set(pic, "Pic", 700, 600, 60, 20);

        submit = new JButton();
        button_set(submit, "Submit", 550, 760, 80, 30);

        Cancel = new JButton();
        button_set(Cancel, "Cancel", 660, 760, 80, 30);

    }
}



