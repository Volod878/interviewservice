package ru.interviewservice.model.tool;

import ru.interviewservice.model.Interview;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Sorter implements Comparator<Interview> {

    private String sortingType;

    public List<Interview> getInterviewSort(String sortingType, List<Interview> interview) {
        if (sortingType == null) return interview;
        Comparator<Interview> sorterByDate = new Sorter();
        switch (sortingType) {
            case ("Имя (по возрастанию)"):
                Collections.sort(interview);
                break;
            case ("Имя (по убыванию)"):
                Collections.sort(interview);
                Collections.reverse(interview);
                break;
            case ("Дата начала (по возрастанию)"):
                interview.sort(sorterByDate);
                break;
            case ("Дата начала (по убыванию)"):
                interview.sort(sorterByDate);
                Collections.reverse(interview);
                break;
        }
        return interview;
    }

    public String getSortingType() {
        return sortingType;
    }

    public void setSortingType(String sortingType) {
        this.sortingType = sortingType;
    }

    @Override
    public int compare(Interview item, Interview item2) {
        return item.getStartDate().compareTo(item2.getStartDate());
    }
}
