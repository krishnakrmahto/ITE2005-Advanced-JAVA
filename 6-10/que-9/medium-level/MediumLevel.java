import java.util.Scanner;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.io.*;
import java.text.SimpleDateFormat;

class Donor implements Serializable {
  private String name;
  private int age;
  private String address;
  private int contactNumber;
  private String bloodGroup;
  private Date dateOfLastDonation;

  public Donor() {
    name = "";
    age = 0;
    address = "";
    contactNumber = 0;
    bloodGroup = "";
    dateOfLastDonation = new Date();
  }

  public void createDonor(String name, int age, String address, int contactNumber, String bloodGroup, Date dateOfLastDonation) {
    this.name = name;
    this.age = age;
    this. address = address;
    this.contactNumber = contactNumber;
    this.bloodGroup = bloodGroup;
    this.dateOfLastDonation = dateOfLastDonation;
  }

  public void displayDonorDetails() {
    System.out.println("Name: " + name);
    System.out.println("Age: " + age);
    System.out.println("Address: " + address);
    System.out.println("Contact: " + contactNumber);
    System.out.println("Blood group: " + bloodGroup);
    System.out.println("Date of Last Donation: " + dateOfLastDonation);
  }

  public String getNameOfDonor() {
    return this.name;
  }

  public String getBloodGroup() {
    return this.bloodGroup;
  }

  public Date getDateOfLastDonation() {
    return this.dateOfLastDonation;
  }

  public void updateDateofLastDonation(Date dateOfLastDonation) {
    this.dateOfLastDonation = dateOfLastDonation;
  }
}

class FileHandlingForDonorList {

  public void writer(List<Donor> donorList) throws Exception {

    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("./donors"));

    oos.writeObject(donorList);
    oos.flush();
    oos.close();
  }

  public List<Donor> reader() throws Exception {

    ObjectInputStream ois = new ObjectInputStream(new FileInputStream("./donors"));

    List<Donor> donorList = new ArrayList<Donor>();
    // Donor donor;

    donorList = (List<Donor>) ois.readObject();

    ois.close();

    return donorList;
  }
}

public class MediumLevel {
  public static void main(String args[]) throws Exception {

    String name;
    int age;
    String address;
    int contactNumber;
    String bloodGroup;
    Date dateOfLastDonation;

    // Donor donor = new Donor();

    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    BufferedReader keyboardBufferedReader = new BufferedReader(new InputStreamReader(System.in));
    int n;

    Scanner keyboardScanner = new Scanner(System.in);

    System.out.println("Enter the number of regular donors in Vellore: ");
    n = keyboardScanner.nextInt();

    List<Donor> donorList = new ArrayList<Donor>();

    for(int i = 0; i < n; i++) {
      Donor donor = new Donor();
      System.out.println("-Donor " + i + "-");
      System.out.print("Name: ");
      name = keyboardBufferedReader.readLine();
      System.out.print("Age: ");
      age = keyboardScanner.nextInt();
      System.out.print("Address: ");
      address = keyboardBufferedReader.readLine();
      System.out.print("Contact number: ");
      contactNumber = keyboardScanner.nextInt();
      System.out.print("Blood group: ");
      bloodGroup = keyboardBufferedReader.readLine();
      System.out.print("Date (dd-MM-yyyy): ");
      dateOfLastDonation = sdf.parse(keyboardBufferedReader.readLine());

      donor.createDonor(name, age, address, contactNumber, bloodGroup, dateOfLastDonation);
      donorList.add(donor);
      System.out.println();
    }


    FileHandlingForDonorList fileHandler = new FileHandlingForDonorList();
    fileHandler.writer(donorList);

    List<Donor> donorListReadFromFile = fileHandler.reader();

    System.out.println();
    long numberOfMilliSecondsInOneMonth = 30L*86400000L;
    long currentDayEpochMilliSeconds = new Date().getTime();
    long donorMilliSecondsTimeOfLastDonation;
    // System.out.println("ms in a month " + numberOfMilliSecondsInOneMonth);
    System.out.println("-Donors who can donate- ");
    for(int i = 0; i < donorListReadFromFile.size(); i++) {

      if(donorListReadFromFile.get(i).getBloodGroup().equals("A+")) {
        // System.out.println((new Date().getTime() - donorListReadFromFile.get(i).getDateOfLastDonation().getTime())/numberOfMilliSecondsInOneMonth);
        donorMilliSecondsTimeOfLastDonation = donorListReadFromFile.get(i).getDateOfLastDonation().getTime();
        if((currentDayEpochMilliSeconds - donorMilliSecondsTimeOfLastDonation)/numberOfMilliSecondsInOneMonth >= 6) {
          donorListReadFromFile.get(i).displayDonorDetails();
        }
      }
      System.out.println();
    }

    System.out.println("------Donate (for registered donors)-------");

    // Donor donor = new Donor();
    System.out.println("-New Donor-");
    System.out.print("Name: ");
    name = keyboardBufferedReader.readLine();
    // System.out.print("Age: ");
    // age = keyboardScanner.nextInt();
    // System.out.print("Address: ");
    // address = keyboardBufferedReader.readLine();
    // System.out.print("Contact number: ");
    // contactNumber = keyboardScanner.nextInt();
    // System.out.print("Blood group: ");
    // bloodGroup = keyboardBufferedReader.readLine();
    // System.out.print("Date (dd-MM-yyyy): ");
    // dateOfLastDonation = sdf.parse(keyboardBufferedReader.readLine());

    // donor.createDonor(name, age, address, contactNumber, bloodGroup, dateOfLastDonation);

    int indexOfDonorInList;
    for(Donor donor: donorListReadFromFile) {
      if(donor.getNameOfDonor().equals(name)) {
        indexOfDonorInList = donorListReadFromFile.indexOf(donor);
        Donor temp = donorListReadFromFile.get(indexOfDonorInList);
        temp.updateDateofLastDonation(new Date());
        donorListReadFromFile.set(indexOfDonorInList, temp);
      }
    }
    // fileHandler = new FileHandlingForDonorList();
    // updating the list of donors as the date of donation for one donor has been modified
    fileHandler.writer(donorListReadFromFile);
    System.out.println("File updated.");
    // reading the updated file
    donorListReadFromFile = fileHandler.reader();

    System.out.println();
    System.out.println("-Updated contents of the file-");
    for(int i = 0; i < donorListReadFromFile.size(); i++) {
      donorListReadFromFile.get(i).displayDonorDetails();
      System.out.println(); // for new line
    }



    // donorList.add(donor);

    // for(int i = 0; i < donorListReadFromFile.size(); i++) {
    //
    //   if(donorListReadFromFile.get(i).getBloodGroup().equals("A+")) {
    //     if((new Date().getTime() - donorListReadFromFile.get(i).getDateOfLastDonation().getTime())/(86400000*30) >= 6)
    //       donorListReadFromFile.get(i).displayDonorDetails();
    //   }
    //   System.out.println();
    // }
  }
}
