package com.example.alessiopinnabe.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailDto {
	
	private String from;
	private String to;
	private String text;
	private String html;
	private String subject;
	private String title;
}
