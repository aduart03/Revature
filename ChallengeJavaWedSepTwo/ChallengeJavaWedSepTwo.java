import java.util.*;

public class ChallengeJavaWedSepTwo {

    public static void main(String[] args){

        // Challenge - Calculate Test Scores
        System.out.println("---------------------------");
        System.out.println("\nChallenge - Calculate 5 Test Scores.\n");
        int[] testScores = {100,90,60,75,20};
        int tsLength = testScores.length;
        int sum = 0;
        int avg = 0;
        int highest = 0;
        int lowest = testScores[0];
        char grade;

        // Sum
        for (int num : testScores){
            sum += num;
        }

        System.out.println("Total: " + sum);

        // Average
        for (int num: testScores){
            avg += num;
        }
        System.out.println("Average " + (avg/tsLength) );

        // Highest
        for (int num : testScores){
            highest = Math.max(highest, num);
        }
        System.out.println("Highest: " + highest);

        // Lowest
        for (int num : testScores){
            lowest = Math.min(lowest, num);
        }
        System.out.println("Lowest: " + lowest + "\n");

        System.out.println("---------------------------\n");
        System.out.println("Assign a Letter Grade.\n");

        for(int i = 0; i < tsLength; i++){

            if(testScores[i] >= 90){
                grade = 'A';
                System.out.println(testScores[i] + "-" + grade);
            }else if (testScores[i] >= 75){
                grade = 'C';
                System.out.println(testScores[i] + "-" + grade);
            }else if (testScores[i] >= 60){
                grade = 'D';
                System.out.println(testScores[i] + "-" + grade);
            }else{
                grade = 'F';
                System.out.println(testScores[i] + "-" + grade);
            }

        }

        System.out.println();


    }

}
