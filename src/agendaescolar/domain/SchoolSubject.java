package agendaescolar.domain;

public class SchoolSubject {

    private final int id; // PK
    private String name;
    private final int Group_classNumber; // FK of Group_classNumber

    public SchoolSubject(int id, String name, int groupClassNumber) {
        this.id = id;
        this.name = name;
        this.Group_classNumber = groupClassNumber;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGroup_classNumber() {
        return Group_classNumber;
    }

}
