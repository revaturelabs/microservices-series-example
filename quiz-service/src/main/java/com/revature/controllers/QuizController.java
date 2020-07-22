package com.revature.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.clients.FlashcardClient;
import com.revature.models.Flashcard;
import com.revature.models.Quiz;
import com.revature.repositories.QuizRepository;

@RestController
public class QuizController {

	@Autowired
	private QuizRepository quizDao;
	
	@Autowired
	private FlashcardClient flashcardClient;
	
	@GetMapping("/port")
	public String retrievePort() {
		String port = this.flashcardClient.retrievePort();
		
		return port;
	}
	
	@GetMapping
	public ResponseEntity<List<Quiz>> findAll() {
		List<Quiz> all = quizDao.findAll();
		
		if(all.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(all);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Quiz> findById(@PathVariable("id") int id) {
		Optional<Quiz> optional = quizDao.findById(id);
		
		if(optional.isPresent()) {
			return ResponseEntity.ok(optional.get());
		}
		
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping
	public ResponseEntity<Quiz> insert(@RequestBody Quiz quiz) {
		int id = quiz.getId();
		
		if(id != 0) {
			return ResponseEntity.badRequest().build();
		}
		
		quizDao.save(quiz);
		return ResponseEntity.status(201).body(quiz);
	}
	
	@GetMapping("/cards")
	public ResponseEntity<List<Flashcard>> getCards() {
		List<Flashcard> all = this.flashcardClient.findAll(); // Could be time consuming
		
		if(all.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(all);
	}
}
