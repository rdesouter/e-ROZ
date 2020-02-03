package com.technocite.ERos.utils;

import com.technocite.ERos.dto.MissionDto;
import com.technocite.ERos.model.Hero;
import com.technocite.ERos.model.Mission;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
@Configuration
@EnableScheduling
public class ErozUtils {

    private static double XP;
    private static double ratio = 1.25;

    public static LocalDateTime getLocalDateNow(){
        return LocalDateTime.now();
    }

    public static Timestamp getTimeStamp(){
        Timestamp timestamp = Timestamp.valueOf(getLocalDateNow());
        //LocalDate localDate = LocalDate.now();
        return timestamp;
    }

    public static int getRandomNumber(int min, int max) {
        return (int) (Math.random() * ((max-min) + 1)) + min;
    }

    public static double getRandomNumberDouble(int min, int max) {
        return (Math.random() * ((max-min) + 1)) + min;
    }

    public static Timestamp getRandomDateAddedByDayPlusBetween(int min, int max){
        return Timestamp.valueOf(getLocalDateNow().plusDays(getRandomNumber(min,max)));
    }

    public static Timestamp getRandomDateAddedByMinutesPlusBetween(int min, int max){
        return Timestamp.valueOf(getLocalDateNow().plusMinutes(getRandomNumber(min,max)));
    }

    public static double getExperience(double difficulty){
        return ((difficulty * 100) * ratio);
    }

    public static boolean successChance(double percentage){
        Random r = new Random();
        double randomNumber = r.nextDouble();
        if( randomNumber <= percentage){
            System.out.println("mission SUCCEED ! random number was: " + randomNumber );
            return true;
        }
        System.out.println("mission FAILED ! random number was: " + randomNumber );
        return false;
    }
    public static Mission createRandomMission(){
        String[] title = {
                "Sauvez Willy", "Il faut sauvez le soldat Ryan", "Apollo", "Aliens",
                "Star Wars Episode", "Mission Impossible", "Harry Potter", "Avengers",
                "James Bond", "Armageddon","Suicide Squad", "Les Croisades",
                "Hunger Games","Matrix","Indiana Jones", "Inception" };
        String[] number = {"I","II","III","IV","V"};

        int difficulty = getRandomNumber(30,100);
        int safePeople = difficulty/10;

        StringBuilder randomTitleAndNumber = new StringBuilder();
        randomTitleAndNumber.append(title[getRandomNumber(0,title.length-1)]);
        randomTitleAndNumber.append(": " + number[getRandomNumber(0,number.length-1)]);
        //System.out.println("Random title with first number = " + randomTitleAndNumber);

        String [] cities = {
                "Bruxelles","Louvain","Wavre","Namur","Liège",
                "Mons","Charleroi","Gent","Hasselt","Arlon",
                "Dinant","Verviers","Anvers","Brugges","Ostende",
                "Blankenberge","Kortrijk","La Louvière","Couvin","Ciney"};
        String randomCity = cities[getRandomNumber(0,cities.length-1)];
        //System.out.println("Random city: " + randomCity);


        double awards = difficulty * 150;
        List<Hero> heroes = new ArrayList<>();

        return new Mission(
                randomTitleAndNumber.toString(),
                difficulty,
                safePeople,
                randomCity,
                awards,
                getTimeStamp(),
                getRandomDateAddedByMinutesPlusBetween(2,120),
                getExperience(difficulty),
                heroes,
                false,
                false,
                false,
                false);
    }

    //FOR TESTING
    public static void getRandomCity(){
        String[] title = {
                "Sauvez Willy", "Il faut sauvez le soldat Ryan", "Apollo", "Aliens",
                "Star Wars Episode", "Mission Impossible", "Harry Potter", "Avengers",
                "James Bond", "Armageddon","Suicide Squad", "Les Croisades",
                "Hunger Games","Matrix","Indiana Jones", "Inception" };
        String[] number = {"I","II","III","IV","V"};

        int difficulty = getRandomNumber(30,100);
        int safePeople = difficulty/10;

        StringBuilder randomTitleAndNumber = new StringBuilder();
        randomTitleAndNumber.append(title[getRandomNumber(0,title.length-1)]);
        randomTitleAndNumber.append(": " + number[getRandomNumber(0,number.length-1)]);
        //System.out.println("Random title with first number = " + randomTitleAndNumber);

        String [] cities = {
                "Bruxelles","Louvain","Wavre","Namur","Liège",
                "Mons","Charleroi","Gent","Hasselt","Arlon",
                "Dinant","Verviers","Anvers","Brugges","Ostende",
                "Blankenberge","Kortrijk","La Louvière","Couvin","Ciney"};
        String randomCity = cities[getRandomNumber(0,cities.length-1)];
        //System.out.println("Random city: " + randomCity);


        double awards = difficulty * 150;
        List<Hero> heroes = new ArrayList<>();

        Mission randomMission = new Mission(
                randomTitleAndNumber.toString(),
                difficulty,
                safePeople,
                randomCity,
                awards,
                getTimeStamp(),
                getRandomDateAddedByMinutesPlusBetween(2,120),
                getExperience(difficulty),
                heroes,
                false,
                false,
                false,
                false);
        System.out.println("*******************");
        System.out.println("Random mission:");
        System.out.println(randomMission.toString());

    }

//    @Scheduled(fixedDelay = 5000)
//    public static void each5Seconds() {
//        System.out.println(
//                "Fixed delay task to get Date Nom - " + getLocalDateNow());
//    }

//    @Scheduled(fixedDelay = 5000)
//    public static void createRandomMissionEach5Seconds(){
//        createRandomMission();
//    }

}
