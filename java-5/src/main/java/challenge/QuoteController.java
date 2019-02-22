package challenge;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuoteController {
	
	@Autowired
	private QuoteService service;
	
	@RequestMapping(value = "/v1/quote", produces = "application/json", method = RequestMethod.GET) 
	public Quote getQuote() {
		return service.getQuote();
	}
	 
	@RequestMapping(value = "/v1/quote/{actor}", produces = "application/json", method = RequestMethod.GET) 
	public Quote getQuoteByActor(@PathVariable("actor") String actor) {
		Quote quote = service.getQuoteByActor(actor);
		return quote;
	}

}


