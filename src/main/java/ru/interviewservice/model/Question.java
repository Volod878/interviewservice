package ru.interviewservice.model;

import javax.persistence.*;

@Entity
@Table(name = "questions")
public class Question implements Comparable<Question> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long orderQuestion;

    @Column(nullable = false)
    private String nameQuestion;

    @ManyToOne
    @JoinColumn(name = "interview_id")
    private Interview interview;

    public Question() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderQuestion() {
        return orderQuestion;
    }

    public void setOrderQuestion(Long order) {
        this.orderQuestion = order;
    }

    public String getNameQuestion() {
        return nameQuestion;
    }

    public void setNameQuestion(String nameQuestion) {
        this.nameQuestion = nameQuestion;
    }

    public Interview getInterview() {
        return interview;
    }

    public void setInterview(Interview interview) {
        this.interview = interview;
    }

    @Override
    public int compareTo(Question question) {
        return this.getOrderQuestion().compareTo(question.getOrderQuestion());
    }
}
