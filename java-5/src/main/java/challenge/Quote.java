package challenge;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="scripts")
public class Quote {
	
	@Id
	@Column(name = "id")
	private Integer id;
	
	@Column(name="detail")
    private String quote;
	
    private String actor;
	

	public Quote(Integer id, String quote, String actor) {
		super();
		this.id = id;
		this.quote = quote;
		this.actor = actor;
	}

	public Quote() {
		super();
	}
	
	public Integer getId() {
		return id;
	}


	public String getActor() {
		return actor;
	}

	public String getQuote() {
		return quote;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public void setQuote(String quote) {
		this.quote = quote;
	}


	public void setActor(String actor) {
		this.actor = actor;
	}
	
	


}
