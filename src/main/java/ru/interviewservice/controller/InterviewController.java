package ru.interviewservice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.interviewservice.model.Interview;
import ru.interviewservice.model.tool.Filter;
import ru.interviewservice.model.tool.Sorter;
import ru.interviewservice.repository.InterviewRepository;

import java.util.List;

@Controller
public class InterviewController {

    private final InterviewRepository repository;
    private Sorter sorter;
    private Filter filter;

    @Autowired
    public InterviewController(InterviewRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public String interviewPage(Model model) {
        sorter = new Sorter();
        sorter.setSortingType("Сортировать по...");
        filter = new Filter();

        model.addAttribute("sorter", sorter);
        model.addAttribute("filter", filter);
        model.addAttribute("items", repository.findAll());
        model.addAttribute("item", new Interview());
        return "list_interview";
    }

    @GetMapping("/tool")
    public String interviewSort(String sortingType, Filter filter,
                                Model model) {

        List<Interview> items = repository.findAll();

        if (sortingType != null) {
            sorter.setSortingType(sortingType);
        }
        if (sorter != null) items = sorter.getInterviewSort(sorter.getSortingType(), items);

        if (filter.getCriterionName() != null || filter.getCriterionDate() != null || filter.getCriterionActivity() != null) {
            this.filter.setCriterionName(filter.getCriterionName());
            this.filter.setCriterionDate(filter.getCriterionDate());
            if (filter.getCriterionActivity() != null) this.filter.setCriterionActivity(filter.getCriterionActivity());
        }

        if (this.filter != null)
            items = this.filter.getInterviewFilter(items, this.filter.getCriterionName(), this.filter.getCriterionDate(),
                this.filter.getCriterionActivity());

        model.addAttribute("sorter", sorter);
        model.addAttribute("filter", this.filter);
        model.addAttribute("items", items);
        model.addAttribute("item", new Interview());
        return "list_interview";
    }

    @PostMapping("/")
    public String newInterview(Interview item) {
        repository.save(item);
        return "redirect:/";
    }

    @PutMapping("/edit/{id}")
    public String editInterview(@PathVariable("id") Long id, Interview item) {
        Interview interview = repository.findById(id).get();

        interview.setNameInterview(item.getNameInterview());
        if (item.getActivity() != null) interview.setActivity(item.getActivity());
        interview.setStartDate(item.getStartDate());
        interview.setFinishDate(item.getFinishDate());

        repository.save(interview);
        return "redirect:/{id}/question";
    }

    @DeleteMapping("/delete")
    public String deleteInterview(@RequestParam Long id) {
        repository.deleteById(id);
        return "redirect:/";
    }
}