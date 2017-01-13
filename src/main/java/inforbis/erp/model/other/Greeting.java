package inforbis.erp.model.other;

import javax.persistence.*;


@Entity
@Table(name="Greeting", schema= "base")
public class Greeting {

	@Id
	@GeneratedValue
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	private String text;
	
	public Greeting() { }
	
}
