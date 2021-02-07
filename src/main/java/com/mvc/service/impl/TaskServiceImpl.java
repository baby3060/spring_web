package com.mvc.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import org.springframework.stereotype.Service;

import com.mvc.service.TaskService;

@Service("taskService")
public class TaskServiceImpl implements TaskService {
	
	@Override
	public void taskJob() {
		Calendar cal = Calendar.getInstance();
		
		DateFormat format = new SimpleDateFormat("HH:mm:ss");
		
		System.out.println("Task Run : " + format.format(cal.getTime()));
	}
}
