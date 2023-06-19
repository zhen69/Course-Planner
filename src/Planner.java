import java.util.Arrays;

/**
 * The Planner class stores an ordered list of Course objects that allows students
 * to insert or remove a Course at any position within the range of the list.
 * The Planner class also contains functions or methods that students can use on the
 * ordered list. Moreover, the Planner class could only record a maximum of 50 courses.
 *
 * @author Zhen Wei Liao
 */
public class Planner{
    public static final int MAX_COURSES = 50;
    private Course[] courses = new Course[MAX_COURSES];
    private int numOfCourses = 0;

    /**
     * Constructor create a Planner object with no Course in it.
     *
     */
    public Planner() {
    }

    /**
     * Constructor create a Planner object using the given attributes.
     *
     * @param courses
     *      Array of Course objects.
     *
     * @param numOfCourses
     *      Number of courses in the array.
     *
     * @throws IllegalArgumentException
     *      when number of courses exceeds MAX_COURSES.
     */
    public Planner(Course[] courses, int numOfCourses) throws IllegalArgumentException{
        if(courses.length > MAX_COURSES)
            throw new IllegalArgumentException("Error: Exceeds Max Courses.");
        this.courses = courses;
        this.numOfCourses = numOfCourses;
    }

    /**
     * Accessor. Returns the array of Course objects.
     *
     * @return
     * 		The array of Course objects.
     */
    public Course[] getCourses() {
        return this.courses;
    }

    /**
     * Modifier. Modifies the array of Course objects.
     *
     * @param courses
     * 		The array of Course objects.
     */
    public void setCourses(Course[] courses) {
        this.courses = courses;
    }

    /**
     * Accessor. Returns the number of Courses in the current list.
     * <p>
     * Preconditions:
     * 		The current Planner has been instantiated.
     *
     * @return
     * 		The number of Courses in the current Planner.
     */
    public int size() {
        return numOfCourses;
    }

    /**
     * Modifier. Modifies the number of Courses in the current list.
     *
     * @param numOfCourses
     * 		The number of Courses in the Planner.
     */
    public void setSize(int numOfCourses) {
        this.numOfCourses = numOfCourses;
    }

    /**
     * Adds a new Course to the list.
     *
     * @param newCourse
     * 		The new Course object being added to the Planner.
     *
     * @param position
     * 		The position of the new Course on the list.
     * <p>
     * Preconditions:
     * 		The Course object has been instantiated.
     * 		Position is within the range 1 <= position <= items_currently_in_list + 1.
     * 		The number of Course Objects in the Planner is less than MAX_COURSES.
     * <p>
     * Post conditions:
     * 		After adding the new Course to the list on its given position,
     * 		all the courses that are originally greater than or equal to the
     * 		given position are moved back one position.
     *
     * @throws IllegalArgumentException
     * 		when enter a position that is not within the valid range
     *
     * @throws FullPlannerException
     * 		when the number of Courses in the Planner has reach MAX_COURSES
     *
     */
    public void addCourse(Course newCourse, int position) throws IllegalArgumentException, FullPlannerException{

        if(position <= 0 || position > numOfCourses + 1)
            throw new IllegalArgumentException("Invalid input. Position is not within the "
                    + "valid range [1, " + numOfCourses + "]");

        if(numOfCourses == MAX_COURSES)
            throw new FullPlannerException("Planner is full. No more room for additional course.");

        if(numOfCourses == 0) {
            courses[0] = newCourse;
            numOfCourses = 1;
            return;
        }

        Course[] newCourses = new Course[MAX_COURSES];
        /*
        Stores all the Courses that are less than the given position into the new array.
         */
        System.arraycopy(courses, 0, newCourses, 0, position - 1);
        /*
        Stores all the Courses that are originally greater than or equal to the given position
        into the new array their original position increase by 1.
         */
        if (size() - (position - 1) >= 0)
            System.arraycopy(courses, position - 1, newCourses, position, size() - (position - 1));

        newCourses[position-1] = newCourse;
        courses = newCourses;
        numOfCourses++;
    }

    /**
     * Adds a new Course to the end of the list by calling the other
     * addCourse() method.
     *
     * @param newCourse
     * 		The Course being added to the list
     *
     * @throws IllegalArgumentException
     * 		when the position is not within the valid range
     *
     * @throws FullPlannerException
     * 		when the number of Courses in the Planner has reach MAX_COURSES
     *
     * */
    public void addCourse(Course newCourse) throws IllegalArgumentException, FullPlannerException{
        addCourse(newCourse, numOfCourses + 1);
    }

    /**
     * Removes a Course to the list.
     *
     * @param position
     * 		The position in the list where the Course will be removed from.
     * <p>
     * Preconditions:
     * 		The Planner object has been instantiated.
     * 		Position is within the range 1 <= position <= items_currently_in_list.
     * <p>
     * Post conditions:
     * 		After removing the Course at the given position,
     * 		all the courses that are originally greater than or equal to the
     * 		given position are moved backward one position.
     *
     * @throws IllegalArgumentException
     * 		when enter a position that is not within the valid range
     *
     */
    public void removeCourse(int position) throws IllegalArgumentException {
        if(numOfCourses == 0)
            throw new IllegalArgumentException("Error: Planner is empty.");

        if(position <= 0 || position > numOfCourses)
            throw new IllegalArgumentException("Invalid: Position is not within the "
                    + "valid range [1, " + numOfCourses + "]");

        if(numOfCourses == 1){
            courses[0] = null;
            numOfCourses = 0;
            return;
        }

        Course[] newCourses = new Course[MAX_COURSES];

        /*
        Stores all the Courses that are less than the given position into the new array
         */
        System.arraycopy(courses, 0, newCourses, 0, position - 1);

        /*
        Stores all the Courses that are originally greater than or equal to the given position
        into the new array with their original position decrease by 1.
        */
        if (size() - (position - 1) >= 0)
            System.arraycopy(courses, position - 1 + 1, newCourses, position - 1, size() - (position - 1));

        courses = newCourses;

        numOfCourses--;
    }

    /**
     * Returns a specific Course in the list.
     *
     * @param position
     * 		The position of the Course in the list that the user want to access.
     * <p>
     * Preconditions:
     * 		The Planner object has been instantiated.
     * 		Position is within the range 1 <= position <= items_currently_in_list.
     *
     * @throws IllegalArgumentException
     * 		when enter a position that is not within the valid range.
     *
     * @return
     * 		The Course at the specified position in Planner.
     *
     */
    public Course getCourse(int position) throws IllegalArgumentException{
        if(numOfCourses == 0)
            throw new IllegalArgumentException("Error: Planner is empty.");

        if(position <= 0 || position > numOfCourses + 1)
            throw new IllegalArgumentException("Invalid: Position is not within the "
                    + "valid range [1, " + numOfCourses + "]");

        return courses[position - 1];
    }

    /**
     * Returns string representation of the table headers;
     *
     * @return
     * 		Header of the table.
     */
    public static String header() {
        return String.format("%-5s%-25s%-12s%-6s%-9s%-30s", "No.",
                "Course Name", "Department","Code", "Section", "Instructor") + "\n"
                + "-".repeat(82) + "\n";
    }

    /**
     * Returns string representation of the table rows that contains information of a specific Course.
     *
     * @return
     *      Rows of the table.
     */
    public static String row(Course course, int position) {
        return String.format("%-5d%-25s%-12s%-11d%-4s%-30s", position,
                course.getName(),
                course.getDepartment(),
                course.getCode(),
                ((course.getSection() < 10) ? "0" : "") + course.getSection(),
                course.getInstructor()) + "\n";
    }

    /**
     * Prints a table of Courses with the same department.
     *
     * @param planner
     * 		The list of Courses to search in.
     *
     * @param department
     * 		The department code of a Course to look for.
     * <p>
     * Preconditions:
     * 		The Planner object has been instantiated.
     * <p>
     * Post conditions:
     * 		Displays a neatly formatted table of each course filtered from the Planner.
     * 		Keep the preference numbers the same.
     *
     */
    public static void filter(Planner planner, String department) {

        StringBuilder table = new StringBuilder(header());

        for(int i = 1; i < MAX_COURSES + 1; i++) {
            Course currCourse = planner.courses[i - 1];

            if(currCourse == null) break;

            if(currCourse.getDepartment().equals(department)) {
                table.append(row(currCourse, i));
            }
        }

        System.out.println(table);
    }

    /**
     * Checks if a Course is in the Planner.
     *
     * @param course
     * 		The Course the user is looking for.
     * <p>
     * Preconditions:
     * 		This Planner and Course has both been instantiated.
     *
     * @return
     * 		True if the Course is in the Planner, false otherwise.
     *
     */
    public boolean exists(Course course) {
        return Arrays.asList(courses).contains(course);
    }

    /**
     * Returns the index of a Course in the array.
     *
     * @param course
     * 		The Course the user wants the index for.
     *
     * @return
     * 		The index of the desired Course if the Course exist, -1 otherwise.
     *
     */
    public int index(Course course) { return Arrays.asList(courses).indexOf(course); }


    /**
     * Returns a copy of the Planner object. Subsequent changes to the copy will not affect
     * the original and vice versa.
     *
     * @return clonePlanner
     * 		The copy of the current Planner object
     * */
    public Planner makeCopy() {
        Planner clonePlanner = new Planner();

        Course[] cloneCourses = new Course[MAX_COURSES];

        if (numOfCourses >= 0) System.arraycopy(courses, 0, cloneCourses, 0, numOfCourses);

        clonePlanner.setCourses(cloneCourses);
        clonePlanner.setSize(numOfCourses);
        return clonePlanner;
    }

    /**
     * Prints a neatly formatted table of each item in the list with its position number by calling
     * the toString() method.
     * <p>
     * Preconditions:
     * 		This Planner has been instantiated.
     * <p>
     * Post conditions:
     * 		Displays a neatly formatted table of each course from the Planner.
     *
     */
    public void printAllCourses() {
        System.out.println(this);
    }

    /**
     * Prints a neatly formatted table of a course in the list with its position number
     * <p>
     * Preconditions:
     * 		This Planner has been instantiated.
     * <p>
     * Post conditions:
     * 		Displays a neatly formatted table of a course from the Planner.
     *
     */
    public void printCourse(Course course) {
        int position = index(course) + 1;
        String table = header() + row(course, position);

        System.out.println(table);
    }

    /**
     * Returns the String representation of the Planner object, which is a neatly formatted
     * table of each Course in the Planner on its own line with its position number.
     *
     * @return clonePlanner
     * 		The String representation of this Planner object.
     *
     * */
    public String toString() {
        StringBuilder table = new StringBuilder(header());

        for(int i = 0; i < numOfCourses; i++)
            if(courses[i] != null) {
                table.append(row(courses[i], i + 1));
            }
        return table.toString();
    }


    /**
     * Determine if two Planner objects are equal to each other.
     *
     * @param obj
     * 		The object that is being used to compare with the current Planner object.
     *
     * @return
     * 		False if two Planner objects are not the same size.
     * 		True if all the courses in the list for both Planner objects are same, false otherwise.
     * 		This method would always return false if obj is not a Planner object.
     *
     * */
    public boolean equals(Object obj) {
        if(obj == this) return true;
        if(!(obj instanceof Planner)) return false;

        Planner planObj = (Planner) obj;

        if(planObj.courses.length != MAX_COURSES || planObj.numOfCourses != this.numOfCourses) return false;

        return Arrays.equals(this.courses, ((Planner) obj).courses);

    }
}

