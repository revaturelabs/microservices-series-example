package com.revature.messaging;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.revature.models.Quiz;
import com.revature.repositories.QuizRepository;

@Service
public class MessageService {

	@Autowired
	private QuizRepository quizDao;

	@KafkaListener(topics = "quiz-flashcard")
	@Transactional
	public void processFlashCardEvent(FlashcardEvent event) {

		if (event.getOperation().equals(Operation.DELETE)) {

			for (Quiz quiz : this.quizDao.findByCard(event.getFlashcard().getId())) {

				quiz.removeCard(event.getFlashcard().getId());
				quizDao.save(quiz);

			}
		}
	}

}
