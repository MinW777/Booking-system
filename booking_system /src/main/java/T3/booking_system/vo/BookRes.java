package T3.booking_system.vo;

import T3.booking_system.entity.Books;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookRes {
    List<Books> books = new ArrayList<>();

    private Integer kauKazu;

    private Integer totalCost;

    public BookRes() {
    }

    //============


    public List<Books> getBooks() {
        return books;
    }

    public void setBooks(List<Books> books) {
        this.books = books;
    }

    public Integer getKauKazu() {
        return kauKazu;
    }

    public void setKauKazu(Integer kauKazu) {
        this.kauKazu = kauKazu;
    }

    public Integer getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Integer totalCost) {
        this.totalCost = totalCost;
    }
}
