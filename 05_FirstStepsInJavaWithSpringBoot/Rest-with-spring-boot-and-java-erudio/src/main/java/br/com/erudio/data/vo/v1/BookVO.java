package br.com.erudio.data.vo.v1;

import java.io.Serializable;
//import java.util.Currency;
import java.util.Date;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id", "author", "launchDate", "price",  "title"})
public class BookVO extends RepresentationModel<BookVO> implements Serializable{

	private static final long serialVersionUID = 1L;

	@JsonProperty("id")
	private Long key;
	
	@JsonProperty("author")
	private String author;
	
	@JsonProperty("launchDate")
	private Date launchDate;
	
	@JsonProperty("price")
	private Double  price;
	
	@JsonProperty("title")
	private String title;

	public BookVO() {
	}
	
	public Long getKey() {
		return key;
	}
	public void setKey(Long key) {
		this.key = key;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Date getLaunchDate() {
		return launchDate;
	}
	public void setLaunchDate(Date launch_date) {
		this.launchDate = launch_date;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(author, key, launchDate, price, title);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookVO other = (BookVO) obj;
		return Objects.equals(author, other.author) && Objects.equals(key, other.key)
				&& Objects.equals(launchDate, other.launchDate) && Objects.equals(price, other.price)
				&& Objects.equals(title, other.title);
	}

	
}
