import java.util.*;

public class REPL_APP {

    public static void main(String[] args){
        // Ethan Duarte

        // Gather data here
        Scanner sc = new Scanner(System.in);
       
        int result;
        int randomNumber;

        // Print statements welcome
        System.out.println("Welcome to my REPL App!\n");

        while( sc.hasNext() ){
            System.out.print(" >");
            String input = sc.next();

            // Available Commands
            if (input.equals("quit")){
                System.out.println("Goodbye!");
                break;
            }else if(input.equals("add")){
                System.out.println("Type your First Number:");
                int firstNumber = sc.nextInt();
                System.out.println("Type your Second Number:");
                int secondNumber = sc.nextInt();
                result = firstNumber + secondNumber;
                System.out.println("First Number: " + firstNumber);
                System.out.println("Second Number: " + secondNumber);
                System.out.println("Result: " + result);

            }else if (input.equals("subtract")){
                System.out.println("Type your First Number:");
                int firstNumber = sc.nextInt();
                System.out.println("Type your Second Number:");
                int secondNumber = sc.nextInt();
                result = firstNumber - secondNumber;
                System.out.println("Result: " + result);

            }else if (input.equals("multiply")){
                System.out.println("Type your First Number:");
                int firstNumber = sc.nextInt();
                System.out.println("Type your Second Number:");
                int secondNumber = sc.nextInt();
                result = firstNumber * secondNumber;
                System.out.println("Result: " + result);

            }else if (input.equals("divide")){
                System.out.println("Type your First Number:");
                int firstNumber = sc.nextInt();
                System.out.println("Type your Second Number:");
                int secondNumber = sc.nextInt();
                if (secondNumber == 0) {
                    System.out.println("Cannot divide by zero.");
                } else {
                    double quotient = (double) firstNumber / secondNumber;
                    System.out.println("Result: " + quotient);
                }

            }else if (input.equals("random")){
                int min = 1;
                int max = 100;
                randomNumber = (int)( Math.random() * (max - min + 1) ) + min;
                System.out.println("Minimum " + min);
                System.out.println("Maximum " + max);
                System.out.println("Random number: " + randomNumber);


            }else if (input.equals("reverse")){
                System.out.println("Enter Text: " );
                sc.nextLine();
                String inputText = sc.nextLine();
                String reversed = new StringBuilder(inputText).reverse().toString();
                System.out.println("Result: " + reversed );

            }else if (input.equals("2") ||
                      input.equals("password")){
                // Password Validator
                System.out.println("\nWelcome to Password Validator");
                System.out.println("Please Create a password");
                System.out.println("Password must be :");
                System.out.println("- At Least 8 characters");
                System.out.println("- Contain at least one uppercase letter");
                System.out.println("- Contain at least one lowercase letter");
                System.out.println("- Contain at least one number");
                


                sc.nextLine();
                String passwordInput = sc.nextLine();

                boolean hasUppercase = false;
                boolean hasLowercase = false;
                boolean hasNumber = false;


                for (int i =0; i < passwordInput.length(); i++){
                    char ch = passwordInput.charAt(i);

                    if (Character.isUpperCase(ch)){
                        hasUppercase = true;

                    }else if (Character.isLowerCase(ch)){
                        hasLowercase = true;

                    }else if (Character.isDigit(ch)){
                        hasNumber = true;
                    }

                }

                if (passwordInput.length() >= 8 && hasUppercase && hasLowercase && hasNumber) {
                    System.out.println("Output: Password accepted!");
                } else {
                    System.out.println("Output: Password rejected:");
                    if (passwordInput.length() < 8) {
                        System.out.println(" - Must be at least 8 characters");
                    }
                    if (!hasUppercase) {
                        System.out.println(" - Must contain an uppercase letter");
                    }
                    if (!hasLowercase) {
                        System.out.println(" - Must contain a lowercase letter");
                    }
                    if (!hasNumber) {
                        System.out.println(" - Must contain a number");
                    }
                }
            }else if (input.equals("3") ||
                      input.equals("analyze")){

                
                System.out.println("\nWelcome to Word Analyzer");
                System.out.println("Enter a Word: ");
                sc.nextLine();
                String word = sc.nextLine();
                int numOfCharacters = 0;
                int countVowels = 0;
                int consonants = 0;
                int digits = 0;
                int spaces = 0;

                if (!word.isEmpty()){
                    word = word.toLowerCase();
                }

                for (int i = 0; i < word.length(); i++){
                    // Use the Character Class to compare characters to differnt types
                    char ch = word.charAt(i);

                    numOfCharacters++;

                    if( ch == 'a' ||
                        ch == 'e' ||
                        ch == 'i' ||
                        ch == 'o' ||
                        ch == 'u'){
                        countVowels++;
                    }

                    if( ch == 'b' ||
                        ch == 'c' ||
                        ch == 'd' ||
                        ch == 'f' ||
                        ch == 'g' ||
                        ch == 'h' ||
                        ch == 'j' ||
                        ch == 'k' ||
                        ch == 'l' ||
                        ch == 'm' ||
                        ch == 'n' ||
                        ch == 'p' ||
                        ch == 'q' ||
                        ch == 'r' ||
                        ch == 's' ||
                        ch == 't' ||
                        ch == 'v' ||
                        ch == 'w' ||
                        ch == 'x' ||
                        ch == 'y' ||
                        ch == 'z'){
                            consonants ++;
                    }

                    if (Character.isDigit(ch)){
                        digits++;
                    }

                    if (Character.isWhitespace(ch)){
                        spaces++;
                    }

                }

                System.out.println("Characters: " + numOfCharacters);
                System.out.println("Vowels: " + countVowels);
                System.out.println("Consonants: " + consonants);
                System.out.println("Digits: " + digits);
                System.out.println("Spaces: " + spaces);

            }else if ( input.equals("help") ){
                System.out.println("Available commands:");
                System.out.println("  add");
                System.out.println("  subtract");
                System.out.println("  multiply");
                System.out.println("  divide");
                System.out.println("  random");
                System.out.println("  reverse");
                System.out.println("  password");
                System.out.println("  analyze");
                System.out.println("  quit\n");
            }
        }

        sc.close();        

    }
}
