package T3.booking_system.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "book_data")
public class Books {

    @Id
    @Column(name = "isbn")
    private String isbn;

    @Column(name = "name")
    private String name;

    @Column(name = "author")
    private String author;

    @Column(name = "price")
    private Integer price;

    @Column(name = "number")
    private Integer number;

    @Column(name = "sale_number")
    private Integer saleNumber = 0;

    @Column(name = "class_bun")
    private String classBun;


    public Books() {

    }

    public Books(String isbn, String name, String author, Integer price, String classBun, int number) {
        this.isbn = isbn;
        this.name = name;
        this.author = author;
        this.price = price;
        this.classBun = classBun;
        this.number = number;
    }

    public Books(String name, String Isbn, String author, String price) {
    }

    //====== g & t


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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getSaleNumber() {
        return saleNumber;
    }

    public void setSaleNumber(Integer saleNumber) {
        this.saleNumber = saleNumber;
    }

    public String getClassBun() {
        return classBun;
    }

    public void setClassBun(String classBun) {
        this.classBun = classBun;
    }
}
