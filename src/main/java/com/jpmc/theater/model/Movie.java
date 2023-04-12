package com.jpmc.theater.model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
public class Movie {

	private String title;
	private String description;
	private Duration runningTime;
	private double ticketPrice;
	private int specialCode;


	public Movie(String title, Duration runningTime, double ticketPrice, int specialCode) {
		this.title = title;
		this.runningTime = runningTime;
		this.ticketPrice = ticketPrice;
		this.specialCode = specialCode;
		this.description = "";
	}


	@Override
	public int hashCode() {
		return Objects.hash(title, description, runningTime, ticketPrice, specialCode);
	}
}