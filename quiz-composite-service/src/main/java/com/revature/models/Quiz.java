package com.revature.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode @ToString
public class Quiz {

	private int id;
	
	private double grade;
	private String name;
	
	
	private List<Integer> cards;
	
	public boolean addCard(Integer id) {
		if (!cards.contains(id)) {
			return cards.add(id);
		}
		return false;
	}
	
	public boolean removeCard(Integer id) {
		if (cards.contains(id)) {
			return cards.remove(id);
		}
		return false;
	}
}
