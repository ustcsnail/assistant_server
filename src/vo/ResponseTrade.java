package vo;

import java.sql.Timestamp;

public class ResponseTrade {
	private int id;
	private int trade_id;
	private String author;
	private String title ;
	private int lighttrade;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTrade_id() {
		return trade_id;
	}
	public void setTrade_id(int trade_id) {
		this.trade_id = trade_id;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getLighttrade() {
		return lighttrade;
	}
	public void setLighttrade(int lighttrade) {
		this.lighttrade = lighttrade;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	private Timestamp time;
}
