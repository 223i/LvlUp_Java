import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class AviaBooking {
    public static void main(String[] args) {
        String[] firstNamesDictionary = new String[] { "Jack", "John", "Samuel", "Tomas", "Daniel", "Harry","Michael"};
        String[] lastName = new String[] { "Luis", "Carrol", "Dwait", "Qwerty", "Smith", "Poul" };
        String[] gender = new String[] { "w", "m" };
        String[] citizenship = new String[] { "Canada", "France", "Japain", "China", "Russia", "Germany", "USA", "Italy"};
        String[] city = new String[] { "St.Petersburg", "Toronto", "Paris", "Rome", "Frankfurt", "Barcelona" };
        String[] nameOfAirCompany = new String[] { "AirBaltic", "Lufthansa", "Aeroflot", "KLM", "AlItalia", "FinAir", "UtAir", "BelAvia" };
        String[] datumOfFlight = new String[] { "2019.10.14", "2019.11.04", "2019.09.05", "2019.10.16", "2019.12.14", "2019.08.03", "2019.10.28"};
        String[] time = new String[] { "17.00", "12.00", "00.05", "03.45", "16.39", "19.25", "21.40"};
        String[] planeType = new String[] { "A330", "Boeing747", "A320", "A310", "ИЛ-96М"};

        ArrayList<AirCompany> allAirCompanies = createDataAirCompany(nameOfAirCompany);   // создаем список компаний
        ArrayList<AirCompany> allAirCompaniesWithFlights = createAllFlightsInAllCompanies(allAirCompanies,datumOfFlight, time,
                city,city,planeType);

        // Code to check the TestData
//        for (AirCompany airComp : allAirCompaniesWithFlights){
//            System.out.println(airComp.getNameOfAirCompany());
//            for (Flight everyFlight : airComp.getAllFlights()){
//                System.out.println(everyFlight.getDatum() + " " + everyFlight.getDepartureTime() +" "+ everyFlight.getFrom()+ " "
//                        + everyFlight.getTo()+ " "+ everyFlight.getPlaneType()+ " " + everyFlight.getBoardNumber());
//            }
//        }

        Passenger newPassenger = createOnePassengerWithRandomData(firstNamesDictionary,lastName,gender,citizenship);
        createBooking(newPassenger, allAirCompaniesWithFlights);

    }

    public static void createBooking(Passenger passenger, ArrayList<AirCompany> allAirCompanies){

        String from = "Toronto";  //желаемый пункт отправления
        String to = "Rome"; // желаемый пункт прибытия

        for (AirCompany airCompany : allAirCompanies){
            for(Flight everyFlight : airCompany.getAllFlights()){
                if (everyFlight.getFrom().equals(from) && everyFlight.getTo().equals(to)){
                    Booking newBooking = new Booking(passenger,everyFlight, airCompany);
                    passenger.appendCreatedBooking(newBooking);
                    System.out.println(passenger.getFirstName() + " " + passenger.getSecondName() + " " + everyFlight.getDatum() + " " + everyFlight.getDepartureTime() +" "+ everyFlight.getFrom()+ " "
                        + everyFlight.getTo()+ " "+ everyFlight.getPlaneType()+ " " + everyFlight.getBoardNumber() + " " + airCompany.getNameOfAirCompany());
                }
            }
        }

    }

    public static Passenger createOnePassengerWithRandomData(String[] firstNames,String[] lastName, String[] gender,
                                                             String[] citizenship){
        Random random = new Random();
        String firstNameOfPassenger = firstNames[random.nextInt(firstNames.length)];
        String lastNameOfPassenger = lastName[random.nextInt(lastName.length)];
        String genderOfPassenger = gender[random.nextInt(gender.length)];
        int ageOfPassenger = random.nextInt(100);
        String citizenshipOfPassenger = citizenship[random.nextInt(citizenship.length)];
        Passenger newPassenger = new Passenger(firstNameOfPassenger,lastNameOfPassenger,genderOfPassenger, ageOfPassenger,
                citizenshipOfPassenger);

        return newPassenger;

    }


    public static ArrayList<AirCompany> createAllFlightsInAllCompanies(ArrayList<AirCompany> allAirCompanies, String[] datumOfFlight,
                                                                       String[] time, String[] from, String[] to,
                                                                       String[] planeType){

        for (int i = 0; i< allAirCompanies.size(); i++){
            Random random = new Random();
            AirCompany airCompany = allAirCompanies.get(i);
            String airCompanyName = allAirCompanies.get(i).getNameOfAirCompany();

            for (int eachflight = 0; eachflight< 10; eachflight++) {
                String date = datumOfFlight[random.nextInt(datumOfFlight.length)];
                String timeOfFlight = time[random.nextInt(time.length)];
                String from_ = from[random.nextInt(from.length)];
                String destination = to[random.nextInt(to.length)];
                String typeOfPlane = planeType[random.nextInt(planeType.length)];
                int boardNumber = random.nextInt(15);

                Flight newFlight = new Flight(airCompanyName, date, timeOfFlight, from_, destination,typeOfPlane, boardNumber);
                airCompany.appendNewFlight(newFlight);
            }
        }
        return allAirCompanies;
    }

    public static ArrayList<AirCompany> createDataAirCompany(String[] nameOfAirCompany){
        Random random = new Random();
        ArrayList<AirCompany> allAirCompanies = new ArrayList<>(20);
        for (int i = 0; i < 20; i++) {
            String name = nameOfAirCompany[random.nextInt(nameOfAirCompany.length)];
            AirCompany newAirCompany = new AirCompany(name);
            allAirCompanies.add(newAirCompany);
        }
        return allAirCompanies;
    }
}

class Passenger{
    private String firstName;
    private String secondName;
    private String gender;
    private int age;
    private String citizenship;
    ArrayList<Booking> createdBooking = new ArrayList<>();

    Passenger(String firstName, String secondName, String gender, int age, String citizenship){
        if (firstName== null || secondName == null || gender == null || age <= 0 || citizenship == null ){
            throw new IllegalArgumentException("Name schould be specified and age must be > 0");
        }

        this.firstName = firstName;
        this.secondName = secondName;
        this.gender = gender;
        this.age = age;
        this.citizenship = citizenship;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void appendCreatedBooking(Booking newBooking) {
        createdBooking.add(newBooking);
    }

    public ArrayList<Booking>  getCreatedBooking(){
        return createdBooking;
    }
}

class Flight {
    private String datum;              //2019.10.14 - формат
    private String departureTime;
    private String from;
    private String to;
    private String planeType;
    private int boardNumber;
    private String nameOfAirCompany;

    public Flight(String nameOfAirCompany, String datum,String departureTime, String from, String to,
                  String planeType,int boardNumber){
        this.nameOfAirCompany = nameOfAirCompany;
        this.datum = datum;
        this.departureTime = departureTime;
        this.from = from;
        this.to = to;
        this.planeType = planeType;
        this.boardNumber = boardNumber;
    }

    public String getDatum() {
        return datum;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getPlaneType() {
        return planeType;
    }

    public int getBoardNumber() {
        return boardNumber;
    }

    public String getNameOfAirCompany() {
        return nameOfAirCompany;
    }
}

class AirCompany {
    private String nameOfAirCompany;
    private ArrayList<Flight> allFlights = new ArrayList<>();

    AirCompany(String nameOfCompany) {
        nameOfAirCompany = nameOfCompany;
    }

    public String getNameOfAirCompany() {
        return nameOfAirCompany;
    }


    public void appendNewFlight(Flight newFlight) {
        allFlights.add(newFlight);
    }

    public ArrayList<Flight> getAllFlights() {
        return new ArrayList<Flight>(allFlights);
    }
}


class Booking{
    private Passenger passenger;
    private Flight flight;
    private AirCompany company;

    Booking (Passenger passenger, Flight flight, AirCompany company){
        this.passenger = passenger;
        this.flight = flight;
        this.company = company;
    }

}
