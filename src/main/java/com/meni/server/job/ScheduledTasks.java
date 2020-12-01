package com.meni.server.job;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.meni.server.service.AdsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {
	@Autowired
	AdsService adsService;

	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Scheduled(fixedRate = 30000)
	public void reportCurrentTime() {
		log.info("The time is now {}", dateFormat.format(new Date()));
		adsService.matchRequestedRoutesWithVolunteerRoutes();
	}
}