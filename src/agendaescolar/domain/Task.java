package agendaescolar.domain;

import agendaescolar.service.Time;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Task {

    private final int id; // PK
    private String groupSection;
    private String description;
    private String notes;
    private LocalDate postDate;
    private LocalDate deliveryDate;
    private final int SchoolSubject_id; // FK of SchoolSubject_id

    public Task(int id, String groupSection, String description, String notes, String deliveryDate, int SchoolSubject_id) throws DateTimeParseException {
        this.id = id;
        this.groupSection = groupSection;
        this.description = description;
        this.notes = notes;
        this.postDate = LocalDate.now(ZoneId.of("UTC-3"));
        this.setDeliveryDate(deliveryDate);
        this.SchoolSubject_id = SchoolSubject_id;
    }

    public int getId() {
        return id;
    }

    public String getGroupSection() {
        return groupSection;
    }

    public void setGroupSection(String groupSection) {
        this.groupSection = groupSection;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getPostDate() {
        try {
            return Time.formatToBrazilianPattern(postDate);
        } catch (DateTimeException e) {
            Logger.getLogger(Task.class.getName()).log(Level.INFO, null, e);
            return null;
        }
    }

    public final void setPostDate(String postDate) {
        try {
            this.postDate = Time.formatToLocalDatePattern(postDate);
        } catch (DateTimeParseException e) {
            throw e;
        }
    }

    public String getDeliveryDate() {
        try {
            return Time.formatToBrazilianPattern(deliveryDate);
        } catch (DateTimeException e) {
            Logger.getLogger(Task.class.getName()).log(Level.INFO, null, e);
            return null;
        }
    }

    public final void setDeliveryDate(String deliveryDate) {
        try {
            this.deliveryDate = Time.formatToLocalDatePattern(deliveryDate);
        } catch (DateTimeParseException e) {
            throw e;
        }
    }

    public int getSchoolSubject_id() {
        return SchoolSubject_id;
    }

}
