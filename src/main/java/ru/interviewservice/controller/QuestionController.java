package ru.interviewservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.interviewservice.model.Interview;
import ru.interviewservice.model.Question;
import ru.interviewservice.repository.InterviewRepository;
import ru.interviewservice.repository.QuestionRepository;

import java.util.Collections;
import java.util.List;

@Controller
public class QuestionController {

    private final QuestionRepository repository;
    private final InterviewRepository interviewRepository;

    @Autowired
    public QuestionController(QuestionRepository repository, InterviewRepository interviewRepository) {
        this.repository = repository;
        this.interviewRepository = interviewRepository;
    }

    @GetMapping("/{id}/question")
    public String questionPage(@PathVariable("id") Long id, Model model) {
        Interview item = interviewRepository.findById(id).get();
        List<Question> questions = repository.findByInterviewNameInterview(item.getNameInterview());
        Collections.sort(questions);

        model.addAttribute("item", item);
        model.addAttribute("questions", questions);
        model.addAttribute("question", new Question());
        return "list_question";
    }

    @PostMapping("/{id}/question")
    public String newQuestion(@PathVariable("id") Long id, @RequestParam String nameQuestion,
                              @RequestParam Long orderQuestion) {

        Interview item = interviewRepository.findById(id).get();

        Question question = new Question();
        question.setInterview(item);
        question.setNameQuestion(nameQuestion);
        question.setOrderQuestion(orderQuestion);

        repository.save(question);
        return "redirect:/{id}/question";
    }

    @PutMapping("/{id}/question")
    public String editQuestion(@RequestParam Long idQuestion, @RequestParam String nameQuestion,
                               @RequestParam Long orderQuestion) {

        Question question = repository.findById(idQuestion).get();
        question.setNameQuestion(nameQuestion);
        question.setOrderQuestion(orderQuestion);

        repository.save(question);
        return "redirect:/{id}/question";
    }

    @DeleteMapping("/{id}/question")
    public String deleteQuestion(@RequestParam Long idQuestion) {
        repository.deleteById(idQuestion);
        return "redirect:/{id}/question";
    }
}