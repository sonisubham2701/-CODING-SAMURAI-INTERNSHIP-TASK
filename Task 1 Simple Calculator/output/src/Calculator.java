import java.util.Scanner;

class Calculator{
    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        int userInput;
        double firstNumber = 0, secondNumber = 0, result;
        String operator = "", exceptionMessage;
        boolean exceptionCheck;

        System.out.println("""

             ==========================================================================================================================================
             |    Welcome to the Simple Calculator                                                                                                    |
             |    Basic calculator in Java that can perform arithmetic operations like addition, subtraction, multiplication, division, and modulus.  |
             ==========================================================================================================================================    
            """);

        do{

            System.out.print("""

                1. Addition
                2. Subtraction
                3. Multiplication
                4. Division
                5. Modulus
                6. Exit

                Choose Your Operator 
            --------------------------
                (eg. like you can type 1 for the addition operation and likewise) 
            """);

            System.out.print("\nWaiting for your input : ");

            userInput = scanner.nextInt();

            if(userInput > 0 && userInput <= 5){

                if(userInput == 1){
                    operator = "Addition";
                }else if(userInput == 2){
                    operator = "Subtraction";
                }else if(userInput == 3){
                    operator = "Multiplication";
                }else if(userInput == 4){
                    operator = "Division";
                }else if(userInput == 5){
                    operator = "Modulus";
                }else{
                    operator = "Wrong Operator";
                }
                
                System.out.println("\nYou have choosen the " + userInput + ". " + operator + "\n");
                System.out.println("""
                    Now Enter Numbers to perfrom operation
                --------------------------------------------

                Waiting for your input 
                """);

                System.out.print("Enter first number : ");
                firstNumber = scanner.nextDouble();

                System.out.print("Enter second number : ");
                secondNumber = scanner.nextDouble();
            }

            exceptionCheck = false;
            exceptionMessage = "";
            result = 0;

            switch(userInput){
                case 1:
                    result = firstNumber + secondNumber;
                    break;

                case 2:
                    result = firstNumber - secondNumber;
                    break;
                
                case 3:  
                    result = firstNumber * secondNumber;
                    break;

                case 4:
                    if(secondNumber != 0){
                        result = firstNumber / secondNumber;
                    }else{
                        exceptionCheck = true;
                        exceptionMessage = "Division by zero is not allowed.";
                    }
                    break;

                case 5:
                    result = firstNumber % secondNumber;
                    break;

                case 6:
                    System.out.println("\n=====================================");
                    System.out.println("Thank You For Using the Calculator.");
                    System.out.println("May we continue to make your life easier!");
                    System.out.println("=====================================");
                    
                    System.exit(0);

                default:
                    System.out.println("\n================================");
                    System.out.println("Invalid input try again...");  
                    System.out.println("================================");  
            }

            if(userInput > 0 && userInput <= 5){

                if (firstNumber % 1 == 0 && secondNumber % 1 == 0 && result % 1 == 0) {
                    System.out.println("\n===================================================================");
                    System.out.println(operator + " of " + (int) firstNumber + " and " + (int) secondNumber + " : " + (int) result);
                    if(exceptionCheck){
                        System.out.println("Error: " + exceptionMessage);
                    }
                    System.out.println("===================================================================");
                } else {
                    System.out.println("\n===================================================================");
                    System.out.println(operator + " of " + firstNumber + " and " + secondNumber + " : " + result);
                    if(exceptionCheck){
                        System.out.println("Error: " + exceptionMessage);
                    }
                    System.out.println("===================================================================");
                }
            }

        }while (userInput != 6);

        scanner.close();
    }
}