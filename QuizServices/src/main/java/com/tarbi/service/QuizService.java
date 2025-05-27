package com.tarbi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tarbi.dao.QuizDao;
import com.tarbi.feign.QuizInterface;
import com.tarbi.model.QuestionWrapper;
import com.tarbi.model.Quiz;
import com.tarbi.model.Response;

@Service
public class QuizService {

	@Autowired
	QuizDao quizDao;
	
	@Autowired
	QuizInterface quizInterface;
	
	public ResponseEntity<String> createQuiz(String category,int numQ,String title) {
		
	//	REST Template
	// 	to access from one microservice to another microservice
	//  Feign Client-declarative---->shortcut
	//  Service Discovery---Eureka Server from Netflix Eureka
		
		List<Integer> questions = quizInterface.getQuestionsForQuiz(category,numQ).getBody();
		 
		Quiz quiz= new Quiz();
		
		quiz.setTitle(title);
		quiz.setQuestionIds(questions) ;
		quizDao.save(quiz);
		
		return new ResponseEntity<>("Success",HttpStatus.CREATED);
	}
	
	
	

	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
	
		Quiz quiz=quizDao.findById(id).get();
		List<Integer> questionIds=quiz.getQuestionIds();
		ResponseEntity<List<QuestionWrapper>> questionwrappers= quizInterface.getQuestionsFromId(questionIds);
		
		return questionwrappers;
	}

	
	public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
		
	ResponseEntity<Integer> score=quizInterface.getScore(responses);
	
	return score;
	
	}

	

}
