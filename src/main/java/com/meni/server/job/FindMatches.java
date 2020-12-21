package com.meni.server.job;

import com.meni.server.service.AdsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

@Component
public class FindMatches {
	@Autowired
	AdsService adsService;

	@Scheduled(fixedRate = 30000)
	public void scanForMatches() {
		adsService.matchRequestedRoutesWithVolunteerRoutes();
	}
}