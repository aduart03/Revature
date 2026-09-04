package Revature.DateTimeApiChallenge;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class DateTimeApi {
    public static void main(String[] args) {

        LocalDateTime currDateAndTime = LocalDateTime.now();
        DateTimeFormatter dateAndTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDateTime = currDateAndTime.format(dateAndTimeFormatter);

        Scanner sc = new Scanner(System.in);

        // 1. Write a program that prints:
        //      Today's date
        //      The current year
        //      The current month
        //      The current day of the month

        System.out.println("\nDate: " + formattedDateTime);
        System.out.println("Year: " + currDateAndTime.getYear());
        System.out.println("Month: " + currDateAndTime.getMonth());
        System.out.println("Day: " + currDateAndTime.getDayOfMonth() + "\n");

        // 2. Calculate your age
        System.out.print("Enter your birth date: ");
        String input = sc.nextLine();
        LocalDate birthDate = LocalDate.parse(input);
        LocalDate today = currDateAndTime.toLocalDate();

        long age = ChronoUnit.YEARS.between(birthDate, today);
        System.out.println("You are " + age + " years old.");

        // 3. Days until your birthday
        LocalDate nextBirthday = birthDate.withYear(today.getYear());

        if (nextBirthday.isBefore(today) || nextBirthday.isEqual(today)) {
            nextBirthday = nextBirthday.plusYears(1);
        }

        long daysUntilBirthday = ChronoUnit.DAYS.between(today, nextBirthday);
        System.out.println("Days until your next birthday: " + daysUntilBirthday);

        sc.close();
    }
}