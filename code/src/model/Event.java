package model;

public class Event {
	private Integer id;
	private String title;
	private float odds;
	// odds of opposite event are 1 / odds. No need to have 2 variables.
	
	public Event(Integer id, String title, float odds) {
		this.id = id;
		this.title = title;
		this.odds = odds;
	}
	
	public Event() {
		this.id = 0;
	}
	
	public Event(String title) {
		this.title = title;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public float getOdds() {
		return odds;
	}

	public void setOdds(float odds) {
		this.odds = odds;
	}
}
