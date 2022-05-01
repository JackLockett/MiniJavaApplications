import java.util.Scanner;
import java.text.DecimalFormat;

public class MiniApps
{
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);     //Scanner which will be used for the entire application and passed as a parameter to the other methods
        boolean menuLoop = true;        //A boolean to determine if the menu is running or not

        do {
            try {       //Try-catch statement in case any invalid characters are entered
                System.out.println("\nP4CS Mini Applications");
                System.out.println("----------------------");
                System.out.println("Please select an option:");
                System.out.println("1) Keep Counting Game");
                System.out.println("2) Square Root Calculator");
                System.out.println("3) Check-Digit Generator");
                System.out.println("4) Check-Digit Checker");
                System.out.println("9) Quit");
                System.out.println("\nPlease enter option:");

                int value = input.nextInt();        //Scanner will take the user input as an integer

                switch (value) {        //A switch case is used to run the certain method depending on the user input and pass the scanner
                    case 1: KeepCountingGame(input); break;
                    case 2: SquareRootCalculator(input); break;
                    case 3: CheckDigitGenerator(input); break;
                    case 4: CheckDigitChecker(input); break;
                    case 9: menuLoop = false; break;        //If the user selects the quit option the application will end by changing the boolean to false, which will break the do-while loop
                    default: System.out.println("\nPlease enter a valid option.");      //If the input isn't valid, a message is printed and the menu is shown again
                }
            }
            catch (Exception error) {
                System.out.println("\nInvalid characters");
                input.nextLine();       //Clear the scanner
            }
        }
        while (menuLoop);       //Do-while loop will continue to run until the menuLoop boolean is set to false
    }

    public static void KeepCountingGame(Scanner input) {
        System.out.println("\nKeep Counting");
        System.out.println("-------------");
        System.out.println("You will be presented with 10 addition questions. After the first question, the left-hand operand is the result of the previous addition.");
        System.out.println("Press enter to start...");

        try {
            System.in.read();       //This will allow the user to press enter before the game starts using a try-catch statement
        }
        catch (Exception error) {
            System.out.println("An error has occurred: " + error);
        }

        long startTime = System.currentTimeMillis();        //This will get the current system time in milliseconds

        final int MIN_VALUE = 1; final int MAX_VALUE = 10;      //These are constant integer values between 1 and 10 that can't be changed
        final int MIN_OP_VALUE = 0; final int MAX_OP_VALUE = 1;     //These are constant integer values between 0 and 1 that can't be changed

        int previousAnswer = 0;     //Store the previous answer as an integer, so it can be used in the for loop

        for (int question = 1; question <= 10; question++) {        //For loop which will run each question 10 times

            int number1 = (int)(MIN_VALUE+(Math.random()*(MAX_VALUE-MIN_VALUE+1)));     //Get a random number as an integer, between 1 and 10
            int number2 = (int)(MIN_VALUE+(Math.random()*(MAX_VALUE-MIN_VALUE+1)));
            int randomOperator = (int)(MIN_OP_VALUE+(Math.random()*(MAX_OP_VALUE-MIN_OP_VALUE+1)));     //Get a random number as an integer, between 0 and 1

            char operator = '+'; int answer = 0; int guess = 0;     //Store the operator as a char data type also store the answer and guess as an integer

            if (question > 1)       //If the question from the for loop is greater than 1, number 1 becomes the previous operand
                number1 = previousAnswer;

            switch(randomOperator) {        //A switch case is used to choose an operator depending on the random value
                case 0: operator = '-'; answer = number1-number2; previousAnswer = answer; break;
                case 1: operator = '+'; answer = number1+number2; previousAnswer = answer; break;
            }

            System.out.println("\nQuestion " + question + ": " + number1 + " " + operator + " " + number2 + " =");

            try {       //Try-catch statement in case the guess isn't a number
                guess = input.nextInt();
            }
            catch (Exception error) {
                System.out.println("\nYou didn't enter a number");
                input.nextLine();
            }

            if (guess != answer) {      //If the question is not equal to the answer then the for loop will end and the correct answer will be displayed
                System.out.println("\nThe correct answer was " + answer);
                break;
            }
            else if (question >= 10) {      //If the question is greater than or equal to 10 then the for loop will end and the game will print a time in seconds
                long endTime = System.currentTimeMillis();
                long elapsedTime = endTime - startTime;
                long convertToSeconds = elapsedTime / 1000;

                System.out.println("\nQuestions completed in " + convertToSeconds + " seconds");
                break;
            }
        }
    }

    public static void SquareRootCalculator(Scanner input) {
        System.out.println("\nSquare Root Calculator");
        System.out.println("----------------------");

        boolean isRunning = true;

        while (isRunning) {     //Run the while loop until the isRunning boolean is false
            int number = 0; int decimal = 0;

            do {
                try {       //Try-catch statement in case the number or decimal is an invalid character
                    System.out.println("\nPlease enter a positive number:");
                    number = input.nextInt();

                    if (number < 0) {       //Check if the number is negative
                        System.out.println("\nThe number you entered isn't positive. Please try again.");
                        continue;       //Go back to the start of the do-while loop using continue statement
                    }
                    System.out.println("\nHow many decimal places do you want the solution calculated to:");
                    decimal = input.nextInt();

                    if (decimal < 1 || decimal > 7) {       //If decimal is less than 1 or decimal is greater than 7 display an error
                        System.out.println("\nThe decimal place range should be between 1-7. Please try again.");
                    }
                }
                catch (Exception error) {
                    System.out.println("\nYou didn't enter a number");
                    input.nextLine();
                }
            }
            while (decimal < 1 || decimal > 7);     //Use do-while loop if the decimal is less than 1 or greater than 7

            double squareRootAccuracy = 0.0;
            String averageFormat = "#.#";

            switch (decimal) {      //Switch case to store the accuracy and format depending on the decimal
                case 1: squareRootAccuracy = 0.1; averageFormat = "#.#"; break;
                case 2: squareRootAccuracy = 0.01; averageFormat = "#.##"; break;
                case 3: squareRootAccuracy = 0.001; averageFormat = "#.###"; break;
                case 4: squareRootAccuracy = 0.0001; averageFormat = "#.####"; break;
                case 5: squareRootAccuracy = 0.00001; averageFormat = "#.#####"; break;
                case 6: squareRootAccuracy = 0.000001; averageFormat = "#.######"; break;
                case 7: squareRootAccuracy = 0.0000001; averageFormat = "#.#######"; break;
            }

            double average = UpperLowerBounds(number, squareRootAccuracy);      //Calling the UpperLowerMethods method by passing the number and the accuracy
            DecimalFormat formatter = new DecimalFormat(averageFormat);     //Use the correct format depending on the decimal inputted

            if (decimal == 1)
                System.out.println("\nThe square root of " + number + " to " + decimal + " decimal place is " + formatter.format(average));
            else
                System.out.println("\nThe square root of " + number + " to " + decimal + " decimal places is " + formatter.format(average));

            isRunning = false;      //Break the while loop
        }
    }

    /*
            CHECK-DIGIT GENERATOR - TEST PLAN

            INPUTS              EXPECTED OUTPUTS              ACTUAL OUTPUTS              DESCRIPTION

            11111               111113                        111113                      This is working as intended
            22222               222226                        222226                      This is working as intended
            33333               333339                        333339                      This is working as intended
            99999               999997                        999997                      This is working as intended
            25687               256876                        256876                      This is working as intended
            23232               232320                        232320                      The remainder is equal to 0, therefore the check digit is set to 0
            1234                ERROR                         DISPLAYS ERROR              It will display an error and ask to input again (Less than 5 digits)
            123456              ERROR                         DISPLAYS ERROR              It will display an error and ask to input again (More than 5 digits)
            Test                ERROR                         DISPLAYS ERROR              It will display an error and ask to input again (Invalid characters)
     */

    public static void CheckDigitGenerator(Scanner input) {
        System.out.println("\nCheck-Digit Calculator");
        System.out.println("----------------------");
        System.out.println("This calculator will take a 5-digit number and generate a trailing 6th check digit");

        boolean isRunning = true;

        while (isRunning) {
            try {       //Try-catch statement in case the number is an invalid character
                System.out.println("\nPlease enter 5-digit number to generate final code: ");
                int number = input.nextInt();
                int length = String.valueOf(number).length();       //Convert the number to a string to find the length
                String inRange = String.valueOf(number);        //Use a string to check if the range of the numbers is between 1 and 9

                if (!inRange.matches("[1-9]*")) {       //Check if all the numbers entered are between 1 and 9
                    System.out.println("Digits out of range");
                }
                else if (length != 5) {     //Check if the length of the input is not equal to 5
                    System.out.println("\nNumber needs to have 5 digits");
                }
                else {
                    isRunning = false;
                    String finalNumber = SingleDigits(number);      //Calling the SingleDigits Method to split the number up into individual values using numerical methods
                    System.out.println("\nThe 6-digit final number is: " + finalNumber);
                }
            }
            catch (Exception error){
                System.out.println("\nInvalid characters");
                input.nextLine();
            }
        }
    }

    public static void CheckDigitChecker(Scanner input) {
        System.out.println("\nCheck Digit Checker");
        System.out.println("------------------------------------");
        boolean isRunning = true;

        while (isRunning) {
            try {       //Try-catch statement in case the number is an invalid character
                System.out.println("\nPlease enter 6-digit number to check: ");
                int number = input.nextInt();
                int length = String.valueOf(number).length();
                String inRange = String.valueOf(number);

                if (!inRange.matches("[0-9]*")) {       //Allow 0 in the checker in case the remainder is equal to 0
                    System.out.println("Digits out of range");
                }
                else if (length != 6) {     //Check if the length of the input is not equal to 6
                    System.out.println("\nNumber needs to have 6 digits");
                }
                else {
                    isRunning = false;
                    boolean finalNumber = IsNumberValid(number);        //Run the IsNumberValid method to return true or false
                    if (finalNumber) {
                        System.out.println("\nThe number is valid");
                    }
                    else {
                        System.out.println("\nThe number is invalid");
                    }
                }
            }
            catch (Exception error) {
                System.out.println("\nInvalid characters");
                input.nextLine();
            }
        }
    }

    //Private Methods

    //Method for Square Root Calculator
    private static double UpperLowerBounds(double number, double squareRootAccuracy) {
        double lowerBounds = 0.0; double upperBounds = number; double average; double squareAverage;

        do {
            average = (lowerBounds + upperBounds) / 2;      //Getting the average of the upper and lower bounds
            squareAverage = average * average;      //Squaring the average
            if (squareAverage > number) {       //Comparing the square of the average against the number
                upperBounds = average;      // If the square average is greater than the number update the upper bound
            } else {
                lowerBounds = average;      // Otherwise, if the square average is less than the number update the lower bound
            }
        } while (upperBounds - lowerBounds > squareRootAccuracy);       //Continue do-while loop until the upper bounds minus the lower bounds is greater than the accuracy

        return average;     //Once the do-while loop is finished it will return the average square root value
    }

    //Method for Check-Digit Generator
    private static String SingleDigits(int number) {
        int checkDigit;

        int[] singleDigits = {10000,1000,100,10};        /* Array containing the values to separate the number into individual digits
                                                            this will contain 4 numbers as it is being used for the generator */

        int d1 = number/singleDigits[0];      //Numerical methods to split the 5 digits into individual values
        number-=d1*singleDigits[0];
        int d2=number/singleDigits[1];
        number-=d2*singleDigits[1];
        int d3=number/singleDigits[2];
        number-=d3*singleDigits[2];
        int d4=number/singleDigits[3];
        int d5=number-d4*singleDigits[3];

        int total = (7 * (d1 + d3 + d5)) + (3 * (d2 + d4));     /* Add the digits in the odd number positions and multiply by 7,
                                                                   add the digits in the even number positions and multiply by 3 */
        int remainder = total%10;       //Find remainder by using modulo

        if (remainder == 0)     //If the remainder is equal to 0 then set the check digit as 0
            checkDigit = 0;
        else
            checkDigit = 10 - remainder;        //If the remainder isn't equal to 0 then subtract the remainder from 10

        return String.format("%d%d%d%d%d%d", d1,d2,d3,d4,d5,checkDigit);        //Format the string using the digits and return it
    }

    //Method for Check-Digit Checker
    private static boolean IsNumberValid(int number) {
        boolean isValid; int checkDigit;

        int[] singleDigits = {100000,10000,1000,100,10};        /* Array containing the values to separate the number into individual digits
                                                                   this will contain 5 numbers as it is being used for the checker */
        int d1 = number/singleDigits[0];
        number-=d1*singleDigits[0];
        int d2=number/singleDigits[1];
        number-=d2*singleDigits[1];
        int d3=number/singleDigits[2];
        number-=d3*singleDigits[2];
        int d4=number/singleDigits[3];
        number-=d4*singleDigits[3];
        int d5=number/singleDigits[4];
        int d6=number-d5*singleDigits[4];       //Finding the 6th number in the integer for the checker

        int total = (7 * (d1 + d3 + d5)) + (3 * (d2 + d4));
        int remainder = total%10;

        if (remainder == 0)
            checkDigit = 0;
        else
            checkDigit = 10 - remainder;

        if (checkDigit == d6)       //If the checkDigit is equal to the 6th number return true otherwise return false
            isValid = true;
        else
            isValid = false;

        return isValid;
    }
}