package challenge;

import java.util.Date;

public class Jogador {
	
	private String fullName;
	private String nationality;
	private String club;
	private float releaseClause;
	private String birthday;
	private String eurWage;
	private int age;
	
	
	public Jogador(String fullName, String nationality, String club, float releaseClause, String birthday, int age, String eurWage) {
		super();
		this.fullName = fullName;
		this.nationality = nationality;
		this.club = club;
		this.releaseClause = releaseClause;
		this.birthday = birthday;
		this.age = age;
		this.eurWage = eurWage;
	}
	


	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNacionality(String nationality) {
		this.nationality = nationality;
	}
	public String getClub() {
		return club;
	}
	public void setClub(String club) {
		this.club = club;
	}
	public float getReleaseClause() {
		return releaseClause;
	}
	public void setReleaseClause(float releaseClause) {
		this.releaseClause = releaseClause;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}


	public String getEurWage() {
		return eurWage;
	}


	public void setEurWage(String eurWage) {
		this.eurWage = eurWage;
	}
	

}
