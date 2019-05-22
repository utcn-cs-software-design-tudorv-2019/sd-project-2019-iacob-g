package model;

public class Item {
	private Integer id;
	private User user;
	private Bet bet;
	private Integer value;
	private String name;
	
	public Item(Integer id, User user, Bet bet, Integer value, String name) {
		this.id = id;
		this.user = user;
		this.bet = bet;
		this.value = value;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getAvailable() {
		if (bet.getId() == 0)
			return "YES";
		return "NO";
	}
	
	public String getEventTitle() {
		return bet.getEventTitle();
	}
	
	public float getPossibleWin() {
		return value * bet.getEventOdds();
	}
	
	public boolean isPro() {
		return bet.isPro();
	}
}
