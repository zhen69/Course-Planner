/**
 * The PlannerManager class allows the user to enter commands to perform specific operations on the Planner
 *
 * @author Zhen Wei Liao
 *
 */
import java.util.Scanner;

public class PlannerManager {

    private static Planner planner, backupPlanner;
    private static Scanner input;
    private static boolean run;


    /**
     * Prints the given prompt and return user input.
     *
     * @param prompt
     *      String asking the user to enter something.
     *
     * @return
     *      User input.
     */
    private static String userInput(String prompt){
        System.out.print(prompt);
        return input.nextLine();
    }

    /**
     * Creates a Course based on user input.
     *
     * @return
     *      A new course based on user input.
     */
    private static Course createCourse() throws NegativeValueException {
        Course course = new Course();
        course.setName(userInput("Enter course name: "));
        course.setDepartment(userInput("Enter department: "));
        course.setCode(Integer.parseInt(userInput("Enter course code: ")));
        course.setSection((byte) Integer.parseInt(userInput("Enter course section: ")));
        course.setInstructor(userInput("Enter instructor: "));

        return course;
    }

    /**
     * Determine which actions should be performed based on the provided choice.
     *
     * @param choice
     * 		choice provided by the user (A or L)
     */
    private static void addOrLook(String choice) throws NegativeValueException, IllegalArgumentException, FullPlannerException {

        Course course = createCourse();

        if(choice.equals("A")) {
            int position = Integer.parseInt(userInput("Enter position: "));
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
     * Creates a backup of the current planner by storing a copy of it.
     */
    private static void backup(){
        backupPlanner = planner.makeCopy();
        System.out.println("Created a backup of the current planner.");
    }

    /**
     * Prints the backup planner.
     */
    private static void printBackup(){
        if (backupPlanner != null)
            System.out.println(backupPlanner);
        else
            System.out.println("No record of backup.");
    }

    /**
     * Replaces the current Planner with the backup Planner.
     */
    private static void revertBackup(){
        if (backupPlanner != null) {
            planner = backupPlanner;
            System.out.println("Planner successfully reverted to the backup copy.");
        } else
            System.out.println("No record of backup.......Planner unmodified.");
    }

    /**
     * Ends the program.
     */
    private static void terminate(){
        System.out.println("Program terminating successfully...");
        run = false;
        input.close();
    }

    /**
     * Enable user input and perform operations based on the input command.
     *
     */
    private static void commands() throws NegativeValueException, FullPlannerException {
        String choice = input.nextLine();
        switch (choice.toUpperCase().trim()) {
            case "A" -> addOrLook("A");
            case "G" -> planner.printCourse(planner.getCourse(Integer.parseInt(userInput("Enter position: "))));
            case "R" -> removeCourse();
            case "P" -> planner.printAllCourses();
            case "F" -> Planner.filter(planner, userInput("Enter department: "));
            case "L" -> addOrLook("L");
            case "S" -> System.out.println("There are " + planner.size() + " courses in the planner.");
            case "B" -> backup();
            case "PB" -> printBackup();
            case "RB" -> revertBackup();
            case "Q" -> terminate();
            default -> System.out.println("Please enter only the listed choices.");
        }
    }


    /**
     * The main method runs a menu-driven application which first creates
     * an empty Planner object. The program prompts the user for a command
     * to execute an operation. Once a command has been chosen, the program
     * may ask the user for additional information if necessary, and perform
     * the operation
     *
     */
    public static void main(String[] args) {
        planner = new Planner();
        input = new Scanner(System.in);
        run = true;

        while(run) {
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
            try {
                commands();
            } catch(NumberFormatException e){
                System.out.println("Invalid input. Please enter a integer.");
            }
            catch(IllegalArgumentException | FullPlannerException | NegativeValueException e) {
                System.out.println(e.getMessage());
            }
            System.out.println();
        }

    }
}

