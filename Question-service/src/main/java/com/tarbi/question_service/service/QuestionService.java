package com.tarbi.question_service.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tarbi.question_service.dao.QuestionDao;
import com.tarbi.question_service.model.Question;
import com.tarbi.question_service.model.QuestionWrapper;
import com.tarbi.question_service.model.Response;

@Service
public class QuestionService {

	
	@Autowired
	QuestionDao questionDao;
	
	
	public ResponseEntity<List<Question>> getAllQuestions() {
	
		try {
			return new ResponseEntity<>(questionDao.findAll(),HttpStatus.OK);
			}
		catch(Exception e){
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
		}

	
	public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
		
		
		try {
			return new ResponseEntity<>(questionDao.findByCategory(category),HttpStatus.OK);
			}
		catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<String> addQuestion(Question question) {
		try {
			questionDao.save(question);
			return new ResponseEntity<>("Success",HttpStatus.CREATED);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("Error",HttpStatus.BAD_REQUEST);
	}


	public ResponseEntity<List<Integer>> getQuestionsForQuiz(String categoryName, Integer numQuestions) {
		try {
			List<Integer> questions=questionDao.findRandomQuestionsByCategory(categoryName,numQuestions);
			return new ResponseEntity<>(questions,HttpStatus.OK);
			
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
		
	}


	public ResponseEntity<Integer> getScore(List<Response> responses) {
		
		int right=0;
		for (Response response:responses) {
			Question question =questionDao.findById(response.getId()).get();
			if(response.getResponse().equals(question.getRightAnswer())) {
				right++;
			}
			}
		
		
		return new ResponseEntity<>(right,HttpStatus.OK);
	}


	public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionIds) {
		try {
			List<Question> questions=questionDao.findAll();
			
			// converting questions into question wrapper from stream api or a conveyer belt for reference
			// DTO
			// Data transformation 
			// Java Stream API
			// declarative way to process data from a source like a list in this example
			List<QuestionWrapper>  questionWrappers=questions.stream()
					.map(question->new QuestionWrapper(
							question.getId(),
							question.getQuestionTitle(),
							question.getOption1(),
		                    question.getOption2(),
		                    question.getOption3(),
		                    question.getOption4()
		                   ))
					.collect(Collectors.toList());
			
			return new ResponseEntity<>(questionWrappers,HttpStatus.OK);
			
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
		
	}

	
	
	
	

}
