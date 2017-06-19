package agendaescolar.domain;

public class SchoolBoy {

    private final String Student_enchiridion; // FK of Student_enchiridion
    private final int Group_classNumber; // FK of Group_classNumber

    public SchoolBoy(String Student_enchiridion, int Group_classNumber) {
        this.Student_enchiridion = Student_enchiridion;
        this.Group_classNumber = Group_classNumber;
    }

    public String getStudent_enchiridion() {
        return Student_enchiridion;
    }

    public int getGroup_classNumber() {
        return Group_classNumber;
    }

}
