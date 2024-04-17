package ntu.com.thuchanh_recycleview;

import java.util.Calendar;
import java.util.Date;

public class Exam {
    String titleExam, descriptionExam;
    Date dateStart;

    public Exam(String titleExam, String descriptionExam, int year, int month, int day) {
        this.titleExam = titleExam;
        this.descriptionExam = descriptionExam;
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day);
        this.dateStart = calendar.getTime();
    }

    public String getTitleExam() {
        return titleExam;
    }

    public void setTitleExam(String titleExam) {
        this.titleExam = titleExam;
    }

    public String getDescriptionExam() {
        return descriptionExam;
    }

    public void setDescriptionExam(String descriptionExam) {
        this.descriptionExam = descriptionExam;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }
}
