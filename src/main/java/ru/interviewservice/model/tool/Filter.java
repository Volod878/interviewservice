package ru.interviewservice.model.tool;

import org.springframework.format.annotation.DateTimeFormat;
import ru.interviewservice.model.Interview;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Filter {

    private String criterionName;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime criterionDate;

    private String criterionActivity;

    public String getCriterionName() {
        return criterionName;
    }

    public void setCriterionName(String criterionName) {
        this.criterionName = criterionName;
    }

    public LocalDateTime getCriterionDate() {
        return criterionDate;
    }

    public void setCriterionDate(LocalDateTime criterionDate) {
        this.criterionDate = criterionDate;
    }

    public String getCriterionActivity() {
        return criterionActivity;
    }

    public void setCriterionActivity(String criterionActivity) {
        this.criterionActivity = criterionActivity;
    }

    public List<Interview> getInterviewFilter(List<Interview> interview, String criterionName, LocalDateTime criterionDate,
                                              String criterionActivity) {

        List<Interview> result = interview;

        if (criterionName != null) result = filterByName(criterionName, result);
        if (criterionDate != null) result = filterByDate(criterionDate, result);
        if (criterionActivity != null) result = filterByActivity(criterionActivity, result);

        return result;
    }

    private List<Interview> filterByName(String criterionName, List<Interview> interview) {
        List<Interview> result = new ArrayList<>();

        for (Interview item : interview) {
            Interview test = null;

            String s = item.getNameInterview();
            if (s.toLowerCase().contains(criterionName.toLowerCase())) test = item;

            if (test != null) result.add(test);
        }

        return result;
    }

    private List<Interview> filterByDate(LocalDateTime criterionDate, List<Interview> interview) {
        List<Interview> result = new ArrayList<>();

        for (Interview item : interview) {
            Interview test = null;

            if (criterionDate.isAfter(item.getStartDate()) && criterionDate.isBefore(item.getFinishDate()))
                test = item;

            if (test != null) result.add(test);
        }

        return result;
    }

    private List<Interview> filterByActivity(String criterionActivity, List<Interview> interview) {
        if (criterionActivity.equals("Все")) return interview;
        List<Interview> result = new ArrayList<>();

        for (Interview item : interview) {
            Interview test = null;

            if (criterionActivity.equals(item.getActivity())) test = item;

            if (test != null) result.add(test);
        }

        return result;
    }
}
