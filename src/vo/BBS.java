package vo;

import java.sql.Timestamp;
public class BBS {
	private int id;
	private String author;
	private String title ;
	private int lightBBS;
	private int reponseBBS;
	private Timestamp time;
	private String topic;
	private int bbs_id;
	public int getBbs_id() {
		return bbs_id;
	}
	public void setBbs_id(int bbs_id) {
		this.bbs_id = bbs_id;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	private String s_class="生活";
	
	public int getId(){
		return this.id;
	}
	public void setID(int id){
		this.id = id;
	}
	public String getS_class() {
		return s_class;
	}
	public void setS_class(String s_class) {
		this.s_class = s_class;
	}
	public String getAuthor(){
		return this.author;
	}
	public void setAuthor(String author){
		this.author = author;
	}
	public String getTitle(){
		return this.title;
	}
	public void setTitle(String title){
		this.title = title;
	}
	public Timestamp getTime(){
		return this.time;
	}
	public void setTime(Timestamp time){
		this.time = time;
	}
	public int getLightBBS(){
		return this.lightBBS;
	}
	public void setLightBBS(int lightBBS){
		this.lightBBS = lightBBS;
	}
	public int getReponseBBS(){
		return reponseBBS;
	}
	public void setReponseBBS(int reponseBBS){
		this.reponseBBS = reponseBBS;
	}
}
