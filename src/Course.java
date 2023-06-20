/**
 * The Course class contains basic course information such as the name,
 * the department, the code, the section, and the instructor of the course.
 * The Course class implements the Cloneable interface for the purpose of overriding 
 * the clone() method.
 *
 * @author Zhen Wei Liao
 */

public class Course{
    private String name;
    private String department;
    private int code;
    private byte section;
    private String instructor;

    /**
     * Constructor used to create a Course object with no information in it.
     */
    public Course(){
    }

    /**
     * Constructor used to create a new Course object with specific attributes.
     *
     *  @param name
     *  	The name of the course.
     *
     *  @param department
     *  	The department of the course.
     *
     *  @param code
     *  	The code of the course.
     *
     *  @param section
     *  	The section of the course.
     *
     *  @param instructor
     *  	THe instructor that's teaching the course.
     */
    public Course(String name, String department, int code, byte section, String instructor){
        this.name = name;
        this.department = department;
        this.code = code;
        this.section = section;
        this.instructor = instructor;
    }

    /**
     * Accessor. Returns the name of the course.
     *
     * @return
     * 		The name of the course.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Accessor. Returns the department of the course.
     *
     * @return
     * 		The department of the course.
     */
    public String getDepartment() {
        return this.department;
    }

    /**
     * Accessor. Returns the code of the course.
     *
     * @return
     * 		The code of the course.
     */
    public int getCode() {
        return this.code;
    }

    /**
     * Accessor. Returns the section of the course.
     *
     * @return
     * 		The section of the course.
     */
    public byte getSection() {
        return this.section;
    }

    /**
     * Accessor. Returns the instructor of the course.
     *
     * @return
     * 		The instructor of the course.
     */
    public String getInstructor() {
        return this.instructor;
    }

    /**
     * Modifier. Modifies the name of the course.
     *
     * @param name
     * 		The name of the course.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Modifier. Modifies the department of the course.
     *
     * @param department
     * 		The department of the course.
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * Modifier. Modifies the code of the course.
     *
     * @param code
     * 		The code of the course.
     *
     * @throws NegativeValueException
     * 		when the code is set to a negative number.
     */
    public void setCode(int code) throws NegativeValueException {
        if(code < 0)
            throw new NegativeValueException("Invalid Input. Code can't be negative.");

        this.code = code;
    }

    /**
     * Modifier. Modifies the section of the course.
     *
     * @param section
     * 		The section of the course.
     *
     * @throws NegativeValueException
     * 		when the section is set to a negative number.
     */
    public void setSection(byte section) throws NegativeValueException{
        if(section < 0)
            throw new NegativeValueException("Invalid Section.");

        this.section = section;
    }

    /**
     * Modifier. Modifies the instructor of the course.
     *
     * @param instructor
     * 		The instructor of the course.
     */
    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    /**
     * Determine if two Course objects contains the same information.
     *
     * @param obj
     * 		The object that is being used to compare with the current Course object.
     *
     * @return
     * 		The boolean value or truth value after comparing all the information.
     * 		If obj is not a Course object, the method would always return false.
     *
     * */
    @Override
    public boolean equals(Object obj) {
        if(obj == this)return true;
        if(!(obj instanceof Course))return false;

        Course objCourse = (Course)obj;

        boolean compareName = objCourse.name.equals(this.name), compareDepartment = objCourse.department.equals(this.department),
                compareCode = objCourse.code == this.code, compareSection = objCourse.section == this.section,
                compareInstructor = objCourse.instructor.equals(this.instructor);

        return compareName && compareDepartment && compareCode && compareSection && compareInstructor;

    }

    /**
     * Returns the String representation of the Course object in the format of (department code.section).
     *
     * @return
     * 		The String representation of this Course object.
     *
     * */
    @Override
    public String toString() {
        return department + " " + code + (section < 10 ? ".0" : ".") + section;
    }
}
