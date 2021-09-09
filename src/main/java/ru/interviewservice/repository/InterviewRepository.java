package ru.interviewservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.interviewservice.model.Interview;

@Repository
public interface InterviewRepository extends JpaRepository<Interview, Long> {
}
