package inforbis.erp.model.other;

import inforbis.erp.model.base.BaseEntity;

import javax.persistence.*;


@Entity
@Table(name="Greeting", schema= "base")
public class Greeting extends BaseEntity {

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	private String text;
	
	public Greeting() { }
	
}
