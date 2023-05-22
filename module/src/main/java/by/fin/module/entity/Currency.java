package by.fin.module.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@JsonIgnoreProperties(ignoreUnknown=true)
public class Currency {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	Long id;
	@JsonProperty("Cur_ID")
	private int curID;
	@JsonProperty("Date")
	private LocalDate date;
	@JsonProperty("Cur_Abbreviation")
	private String curAbbreviation;
	@JsonProperty("Cur_Scale")
	private int curScale;
	@JsonProperty("Cur_Name")
	private String curName;
	@JsonProperty("Cur_OfficialRate")
	private double curOfficialRate;
	
	public Currency() {
		
	}

	public Currency(Long id, int curID, LocalDate date, String curAbbreviation, int curScale, String curName,
			double curOfficialRate) {
		this.id = id;
		this.curID = curID;
		this.date = date;
		this.curAbbreviation = curAbbreviation;
		this.curScale = curScale;
		this.curName = curName;
		this.curOfficialRate = curOfficialRate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getCurID() {
		return curID;
	}

	public void setCurID(int curID) {
		this.curID = curID;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getCurAbbreviation() {
		return curAbbreviation;
	}

	public void setCurAbbreviation(String curAbbreviation) {
		this.curAbbreviation = curAbbreviation;
	}

	public int getCurScale() {
		return curScale;
	}

	public void setCurScale(int curScale) {
		this.curScale = curScale;
	}

	public String getCurName() {
		return curName;
	}

	public void setCurName(String curName) {
		this.curName = curName;
	}

	public double getCurOfficialRate() {
		return curOfficialRate;
	}

	public void setCurOfficialRate(double curOfficialRate) {
		this.curOfficialRate = curOfficialRate;
	}

	// поскольку двух курсов у одной валюты за одну дату быть не может, достаточно этих полей
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + curID;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Currency other = (Currency) obj;
		if (curID != other.curID)
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Currency [id=" + id + ", curID=" + curID + ", date=" + date + ", curAbbreviation=" + curAbbreviation
				+ ", curScale=" + curScale + ", curName=" + curName + ", curOfficialRate=" + curOfficialRate + "]";
	}

}
