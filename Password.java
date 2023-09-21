import java.util.*;

public class Password {
    private static Map<String, String> userCredentials = new HashMap<>();
    private static final List<String> securityQuestions = Arrays.asList(
            "In what city were you born?",
            "What is the name of your favorite pet?",
            "What is your mother's maiden name?",
            "What high school did you attend?",
            "What was the make of your first car?"
    );

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Create Account");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    createAccount(scanner);
                    break;
                case 2:
                    login(scanner);
                    break;
                case 3:
                    System.out.println("Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }

    private static void createAccount(Scanner scanner) {
        System.out.print("Enter a username: ");
        String username = scanner.nextLine();

        System.out.print("Create a password: ");
        String password = scanner.nextLine();


        System.out.println("Select a security question:");
        for (int i = 0; i < securityQuestions.size(); i++) {
            System.out.println((i + 1) + ". " + securityQuestions.get(i));
        }
        System.out.print("Enter the number of your choice: ");
        int securityQuestionIndex = scanner.nextInt();
        scanner.nextLine();

        if (securityQuestionIndex >= 1 && securityQuestionIndex <= securityQuestions.size()) {
            String selectedSecurityQuestion = securityQuestions.get(securityQuestionIndex - 1);

            System.out.print("Provide the answer to the security question: ");
            String securityAnswer = scanner.nextLine();

            userCredentials.put(username, password + ":" + selectedSecurityQuestion + ":" + securityAnswer);

            System.out.println("Account created successfully.");
        } else {
            System.out.println("Invalid choice.");
        }
    }


    private static void login(Scanner scanner) {
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();

        System.out.print("Enter your password: ");
        String enteredPassword = scanner.nextLine();

        String storedCredentials = userCredentials.get(username);
        if (storedCredentials != null) {
            String[] parts = storedCredentials.split(":");
            String password = parts[0];
            String securityQuestion = parts[1];
            String securityAnswer = parts[2];

            if (enteredPassword.equals(password)) {
                System.out.println("Answer the security question: " + securityQuestion);
                String enteredSecurityAnswer = scanner.nextLine();

                if (enteredSecurityAnswer.equalsIgnoreCase(securityAnswer)) {
                    System.out.println("Login successful.");
                } else {
                    System.out.println("Incorrect security answer.");
                }
            } else {
                System.out.println("Incorrect password.");
            }
        } else {
            System.out.println("Username not found.");
        }
    }
}
