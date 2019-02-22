package challenge;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface QuoteRepository extends CrudRepository<Quote, Integer>{
	
	List<Quote> findByActor(String actor);
	
	List<Quote> findAll();
	



}
