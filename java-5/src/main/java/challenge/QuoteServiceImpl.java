package challenge;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuoteServiceImpl implements QuoteService {

	@Autowired
	private QuoteRepository repository;

	@Override
	public Quote getQuote() {
		
		List<Quote> quotes = new ArrayList<>();
		Random random = new Random();
		quotes = repository.findAll();
		
		int index = random.nextInt(quotes.size());
	    return quotes.get(index);
		
		
	}

	@Override
	public Quote getQuoteByActor(String actor) {
		List<Quote> quotes = new ArrayList<>();
		Random random = new Random();
		quotes = repository.findByActor(actor);
		
		
		int index = random.nextInt(quotes.size());
	    return quotes.get(index);
	}

}
