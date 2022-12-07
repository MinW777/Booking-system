package T3.booking_system.service;

import T3.booking_system.entity.Books;
import T3.booking_system.vo.BookReq;

import java.util.List;
import java.util.Set;

public interface BookService {

    Books addNewBook(BookReq req);

    Set<Books> findClass(String classBun);

    List<Books> findBookName(String name);

    Books findBookISBN(String ISBN);

    List<Books> findBookAuthor(String author);

    List<Books> findBookName2B(String name);

    //透過書名尋找(書商)
    List<Books> findBookAuthor2B(String author);

    //透過ISBN尋找
    Books findBookISBN2b(String isbn);


    //更新書籍
    //更新庫存量(進貨），顯示書名、ISBN、作者、價格、庫存量
    Books addnumber(String isbn, int inputNum);

    Books changePrice(String isbn, int newPrive);

    //書籍銷售
    //1. 消費者購買(可買多本)：只顯示書名、ISBN、作者、價格、購買數量，購買總價格
    //2. 銷售量+1，庫存量-1
    Books SaleOut(String isbn, int saleNum);

    //排行榜前五名
    List<Books> ranking();
}