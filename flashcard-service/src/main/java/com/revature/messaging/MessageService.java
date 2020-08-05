package com.revature.messaging;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
	
	private static Set<Integer> eventCache = new HashSet<>();
	
	@Autowired
	private KafkaTemplate<String, FlashcardEvent> kt;
	
	public void triggerEvent(FlashcardEvent event) {
		eventCache.add(event.hashCode());
		
		kt.send("quiz-flashcard", event);
		
	}

}
