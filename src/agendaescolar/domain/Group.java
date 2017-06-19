package agendaescolar.domain;

public class Group {

    private int classNumber; // PK
    private String className;
    private final String studentAdmin_Student_Enchiridion; // FK of Student_enchiridion

    public Group(int classNumber, String className, String StudentAdmin_Enchiridion) {
        this.classNumber = classNumber;
        this.className = className;
        this.studentAdmin_Student_Enchiridion = StudentAdmin_Enchiridion;
    }

    public int getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(int classNumber) {
        this.classNumber = classNumber;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getStudentAdmin_Student_Enchiridion() {
        return studentAdmin_Student_Enchiridion;
    }

}
