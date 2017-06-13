package vo;

import java.sql.Timestamp;

public class Trade {
	private int id;
	private String author;
	private String title ;
	private int lighttrade;
	private int reponsetrade;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public int getReponsetrade() {
		return reponsetrade;
	}
	public void setReponsetrade(int reponsetrade) {
		this.reponsetrade = reponsetrade;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public int getBbs_id() {
		return bbs_id;
	}
	public void setBbs_id(int bbs_id) {
		this.bbs_id = bbs_id;
	}
	private Timestamp time;
	private String topic;
	private int bbs_id;
}
