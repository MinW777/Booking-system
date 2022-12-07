package T3.booking_system.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookReq {
    @JsonProperty("isbn")
    @NotBlank
    @Size(min=14)
    private String isbn;

    @JsonProperty("name")
    @NotBlank
    private String name;

    @JsonProperty("author")
    @NotBlank
    private String author;

    @JsonProperty("price")
    @Min(10)
    private int price;

    @JsonProperty("number")
    @NotNull
    private int number;

    @JsonProperty("sale_number")
    @NotNull
    private int saleNumber;

    @JsonProperty("classBun")
    @NotBlank
    private String classBun;

    @JsonProperty("saleKazu")
    private Integer saleKazu;

    public BookReq() {
    }

    //=========

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getSaleNumber() {
        return saleNumber;
    }

    public void setSaleNumber(int saleNumber) {
        this.saleNumber = saleNumber;
    }

    public String getClassBun() {
        return classBun;
    }

    public void setClassBun(String classBun) {
        this.classBun = classBun;
    }

    public Integer getSaleKazu() {
        return saleKazu;
    }

    public void setSaleKazu(Integer saleKazu) {
        this.saleKazu = saleKazu;
    }
}
