import java.util.*;

public class ChallengeJavaWedSepTwo {

    public static void main(String[] args){

        // Challenge - Calculate Test Scores
        System.out.println("---------------------------");
        System.out.println("\nChallenge - Calculate 5 Test Scores.\n");
        int[] testScores = {100,90,60,75,20};
        int tsLength = testScores.length;
        int sum = 0;
        double avg =0.0;
        int highest = 0;
        int lowest = testScores[0];
        char grade;

        // Sum
        for (int num : testScores){
            sum += num;
        }

        System.out.println("Total: " + sum);

        // Average
        avg = (double)sum/tsLength;
        System.out.println("Average " + avg );

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

        System.out.println("Your values were:\n");

        for(int i = 0; i < tsLength; i++){

            if(testScores[i] >= 90){
                grade = 'A';
                System.out.println(testScores[i] + "-" + grade);
            }else if (testScores[i] >= 80){
                grade = 'B';
                System.out.println(testScores[i] + "-" + grade);
            }else if (testScores[i] >= 70){
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
        System.out.println("---------------------------\n");
        System.out.println("Challenge - REPL.\n");

        Scanner sc = new Scanner(System.in);
        int accountBalance = 0;

        System.out.println("Welcome to REPL Bank! What option will you choose:\n");
        System.out.println("c or 1 - Check Balance");
        System.out.println("d or 2 - Deposit");
        System.out.println("w or 3 - Withdraw");
        System.out.println("q or 4 - To exit account\n");

        while (  sc.hasNext() ){            
            String input = sc.next().toLowerCase();
            if (input.equals("4") ||
                input.equals("q") ||
                input.equals("e") || 
                input.equals("quit") ||
                input.equals("exit")
            ){
                break;
            }

            if (input.equals("c") ||
                input.equals("1")){
                System.out.println("Checking Account Balance...");
                System.out.println("Account Balance: " + accountBalance);
            }else if (input.equals("d") ||
                        input.equals("2")){
                
                // Store Amount
                System.out.println("Add Deposit amount: ");
                int dAmount =  sc.nextInt();

                // Check for negative values
                if (dAmount < 0){
                    System.out.println("Cant use negative numbers.");
                }else{
                    accountBalance = accountBalance + dAmount;
                    System.out.println("New Account Balance: " + accountBalance);
                }
            }else if (input.equals("w") ||
                      input.equals("3")){
                System.out.println("How much do you want to withdraw?");
                int wAmount =  sc.nextInt();
                // check for negative values OR if amount is more than account
                if (wAmount > accountBalance ||
                    wAmount < 0
                ){
                    System.out.println("Withdraw amount exceeds account balance or withdraw amount is a negative number. Try again.");
                }else{
                    accountBalance = accountBalance - wAmount;
                    System.out.println("New Account Balance: " + accountBalance);
                }
                
            }

        }

        sc.close();

    }

}
