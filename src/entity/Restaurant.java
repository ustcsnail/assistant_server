package entity;

public class Restaurant {

	private int id;
    private String resterant_name;  //�͹�����
    private int price_avg;    //�˾��۸�
    private String district;    //�ֵ���Ȧ
    private String address;     //��ϸ��ַ
    private int level;      //���ѵȼ�  1=ƽ�� 2=����  3=�ݳ�
    private int type;  //1=�в�  2=����  3=��Ʒ  4=���
    private int mark;    //�����ǣ�����
    private String label;  //��ǩ

    public static final int TYPE_CHINESE=1;
    public static final int TYPE_WESTERN=2;
    public static final int TYPE_DESSERT=3;
    public static final int TYPE_FASTFOOD=4;

    public static final int LEVEL_LOW=1;
    public static final int LEVEL_MID=2;
    public static final int LEVEL_TOP=3;
    
    public static final int RESULT_SUCCESS =1;
    public static final int RESULT_JSONERR =2;
    public static final int RESULT_TYPEERR =3;
    public static final int RESULT_SERVERERR =4;
    public static final int RESULT_EMPTYERR =5;
    
    public String getResterant_name() {
        return resterant_name;
    }

    public void setResterant_name(String resterant_name) {
        this.resterant_name = resterant_name;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice_avg() {
        return price_avg;
    }

    public void setPrice_avg(int price_avg) {
        this.price_avg = price_avg;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
    
    
}
