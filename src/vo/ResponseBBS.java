package vo;

	import java.sql.Timestamp;

public class ResponseBBS {
		private int id;
		public int getBBS_id() {
			return BBS_id;
		}
		public int getBBS_Id(){
			return BBS_id;
		}
		public void setBBS_id(int bBS_id) {
			BBS_id = bBS_id;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		private int BBS_id;
		private String author;
		private String title ;
		private int lightBBS;
		private Timestamp time;
		
		
		
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
			return lightBBS;
		}
		public void setLightBBS(int lightBBS){
			this.lightBBS = lightBBS;
		}
	

}
