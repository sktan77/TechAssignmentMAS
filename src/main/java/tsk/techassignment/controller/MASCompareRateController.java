package tsk.techassignment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import tsk.techassignment.service.ProcessorFactory;
import tsk.techassignment.service.processor.RecordProcessor;


@Controller
public class MASCompareRateController {
	public static final String SUMMARYAVERAGEMODEL = "SummaryAverageModel";
	public static final String SUMMARYTRENDMODEL = "SummaryTrendModel";
	public static final String DETAILMODEL = "DetailModel";
	public static final String EARLIESTRECORD = "EarliestModel";
	public static final String LATESTRECORD = "LatestModel";
	public static final String FROMMONTH = "FromMonth";
	public static final String TOMONTH = "ToMonth";
	public static final String ERRORMESSAGE = "errorMessage";
	
	@RequestMapping(path = { "/" }, method = RequestMethod.GET)
	public String sayHello(Model model) {
		// model.addAttribute("errorMessage", "Contains Error");
		
		return "mas";
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String retrieveMasRates(Model model, @RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate,
			@RequestParam("masJsonResponse") String masJsonResponse) {		
		model.addAttribute(FROMMONTH, fromDate);
		model.addAttribute(TOMONTH, toDate);
		
		final RecordProcessor recordProcessor = ProcessorFactory.createProcessor(masJsonResponse, model);
		recordProcessor.process();
				
	    return "mas";
	}	    
}
