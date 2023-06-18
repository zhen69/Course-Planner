/**
 * The PlannerManager class allows user to enter commands to perform specific operations on the Planner
 *
 * @author Zhen Wei Liao
 * 		Date: 9/6/22
 *
 */
import java.util.Scanner; //Imports the Scanner class

public class PlannerManager {

    private static Planner planner, backupPlanner;
    private static Scanner input;

    /**
     * Checks if the user enter an integer or not.
     *
     * @param input
     * 		User input.
     *
     * @throws IllegalArgumentException
     * 		when the user input is not a number
     *
     * @return
     * 		User input but as a double.
     * */
    public static int isInteger(String input) throws IllegalArgumentException {
        if(!(input != null && input.matches("-?\\d+")))
            throw new IllegalArgumentException("Invalid input. Please only enter a number.\n");
        else
            return Integer.parseInt(input);
    }

    /**
     * Prints the given prompt and return user input.
     *
     * @param prompt
     *      String asking the user to enter something.
     *
     * @return
     *      User input.
     */
    public static String userInput(String prompt){
        System.out.print(prompt);
        return input.nextLine();
    }

    /**
     * Determine which actions should be performed based on the provided choice.
     *
     * @param choice
     * 		choice provided by the user (A or L)
     */
    private static void addOrLook(String choice) throws NegativeValueException, IllegalArgumentException, FullPlannerException {

        Course course = new Course();

        course.setName(userInput("Enter course name: "));

        course.setDepartment(userInput("Enter department: "));

        course.setCode(isInteger(userInput("Enter course code: ")));

        course.setSection((byte) isInteger(userInput("Enter course section: ")));

        course.setInstructor(userInput("Enter instructor: "));

        if(choice.equals("A")) {
            int position = isInteger(userInput("Enter position: "));

            planner.addCourse(course, position);
            System.out.println(planner.getCourse(position) + " successfully added to planner.");
        }
        else {
            if(planner.exists(course))
                System.out.println(course + " is found in the planner at position " + (planner.index(course) + 1));
            else
                System.out.println("Course not found.");
        }

    }

    /**
     * Enable user to remove a course from the planner.
     */
    private static void removeCourse() throws IllegalArgumentException {
        System.out.print("Enter position: ");
        int position = Integer.parseInt(input.nextLine());

        Course removedCourse = planner.getCourse(position);
        planner.removeCourse(position);
        System.out.println(removedCourse + " has been successfully removed from the planner.");
    }


    /**
     * The main method runs a menu-driven application which first creates
     * an empty Planner object. The program prompts the user for a command
     * to execute an operation. Once a command has been chosen, the program
     * may ask the user for additional information if necessary, and performs
     * the operation
     *
     */
    public static void main(String[] args) {
        planner = new Planner();
        input = new Scanner(System.in);
        boolean quit = false;

        while(!quit) {
            System.out.println("""
                    (A) Add Course
                    (G) Get Course
                    (R) Remove Course
                    (P) Print Courses in Planner
                    (F) Filter by Department Code
                    (L) Look For Course
                    (S) Size
                    (B) Backup
                    (PB) Print Courses in Backup
                    (RB) Revert to Backup
                    (Q) Quit
                    """);
            System.out.print("Enter a selection: ");
            String choice = input.nextLine();
            try {
                switch (choice.toUpperCase().trim()) {
                    case "A" -> addOrLook("A");
                    case "G" -> planner.printCourse(planner.getCourse(isInteger(userInput("Enter position: "))));
                    case "R" -> removeCourse();
                    case "P" -> planner.printAllCourses();
                    case "F" -> Planner.filter(planner, userInput("Enter department: "));
                    case "L" -> addOrLook("L");
                    case "S" -> System.out.println("There are " + planner.size() + " courses in the planner.");
                    case "B" -> {
                        backupPlanner = planner.makeCopy();
                        System.out.println("Created a backup of the current planner.");
                    }
                    case "PB" -> {
                        if (backupPlanner != null)
                            System.out.println(backupPlanner);
                        else
                            System.out.println("No record of backup.");
                    }
                    case "RB" -> {
                        if (backupPlanner != null) {
                            planner = backupPlanner;
                            System.out.println("Planner successfully reverted to the backup copy.");
                        } else
                            System.out.println("No record of backup.......Planner unmodified.");
                    }
                    case "Q" -> {
                        System.out.println("Program terminating successfully...");
                        quit = true;
                        input.close();
                    }
                    default -> System.out.println("Please enter only the listed choices.");
                }
            } catch(IllegalArgumentException | FullPlannerException | NegativeValueException e) {
                System.out.println(e);
            }
            System.out.println();
        }

    }
}

