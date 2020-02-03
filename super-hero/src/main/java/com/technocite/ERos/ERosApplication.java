package com.technocite.ERos;

import com.technocite.ERos.utils.ErozUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ERosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ERosApplication.class, args);
        System.out.println("*******************");
		System.out.println("** Api running...");
        System.out.println("*******************");
        System.out.println("Today is the " + ErozUtils.getTimeStamp());


        for (int i = 0; i <5 ; i++) {
            System.out.println("Random days added from today : " + ErozUtils.getRandomDateAddedByDayPlusBetween(1,10));
        }
        //System.out.println(Arrays.toString(PerfomanceOfRandom.getArrayOfRandomIntWithoutSecure(10, 20)));
        //System.out.println(ErozUtils.successChance(92));

//        ErozUtils.getRandomCity();
//        ErozUtils.each5Seconds();

	}

}
