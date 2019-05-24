package model;

public class Bet {
	private Integer id;
	private Event event;
	private boolean pro;
	
	public Bet(Integer id, Event event, boolean pro) {
		this.id = id;
		this.event = event;
		this.pro = pro;
	}
	
	public Bet() {
		this.id = 0;
	}
	
	public Bet(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public boolean isPro() {
		return pro;
	}

	public void setPro(boolean pro) {
		this.pro = pro;
	}

	public String getEventTitle() {
		return event.getTitle();
	}
	
	public float getEventOdds() {
		return event.getOdds();
	}
	
	public Integer getEventId() {
		return event.getId();
	}
}
