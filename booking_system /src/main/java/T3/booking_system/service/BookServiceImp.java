package T3.booking_system.service;

import T3.booking_system.entity.Books;
import T3.booking_system.repository.BooksDao;
import T3.booking_system.vo.BookReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.*;

@Service
public class BookServiceImp implements BookService {

    @Autowired
    private BooksDao booksDao;

    //新增書籍
    @Override
    public Books addNewBook(@Valid BookReq req) {
        Books atarashi = new Books(req.getName(), req.getIsbn(), req.getAuthor(), req.getPrice(), req.getClassBun(), req.getNumber());
        booksDao.save(atarashi);
        return atarashi;
    }

    //透過分類尋找書籍，如果沒有該類別，返回"沒有相關資料"。
    @Override
    public Set<Books> findClass(String classBun) {
        Set<Books> kensaku = new HashSet<>();
        List<Books> books = booksDao.findByClassBunContaining(classBun);

        //提出每筆資料
            for (int i = 0 ; i < books.size() ; i++) {
                String classBunrui = books.get(i).getClassBun(); //取得單一筆的分類

                for (String s: classBunrui.split(",")) {
                    String s2 = s.trim();
                    System.out.println(s2);
                    if ( classBun.equalsIgnoreCase(s2)) {
                        kensaku.add(books.get(i));
                    }
                }
            }
            return kensaku;
    }

    //====================================================

    //透過名稱尋找
    @Override
    public List<Books> findBookName(String name) {
        List<Books> books = booksDao.findByNameLike(name);
        for (Books s: books) {
            s.setNumber(null);
            s.setSaleNumber(null);
        }
        return books;
    }
//
    //透過書名尋找
    @Override
    public List<Books> findBookAuthor(String author) {
        List<Books> books = booksDao.findByAuthorLike(author);
        for (Books s: books) {

            s.setNumber(null);
            s.setSaleNumber(null);
        }
        return books;
    }
//
//    //透過ISBN尋找
    @Override
    public Books findBookISBN(String isbn) {
        Books findbn = booksDao.findAllByIsbn(isbn);
        System.out.println(findbn);

        findbn.setNumber(null);
        findbn.setSaleNumber(null);
        return findbn;
    }


    //=======================================================

    //透過書名尋找(書商)

    @Override
    public List<Books> findBookName2B(String name) {
        List<Books> books = booksDao.findByNameLike(name);
        return books;
    }
    @Override
    public List<Books> findBookAuthor2B(String author) {
        List<Books> books = booksDao.findByAuthorLike(author);
        return books;
    }
    //
//    //透過ISBN尋找
    @Override
    public Books findBookISBN2b(String isbn) {
        Books findbn1 = booksDao.findAllByIsbn(isbn);
        return findbn1;
    }

    //更新書籍
    //「  更新庫存量(進貨） 」，顯示書名、ISBN、作者、價格、庫存量
    @Override
    public Books addnumber(String isbn, int inputNum) {
        Books thebook = booksDao.findAllByIsbn(isbn);
        int kazu = thebook.getNumber() + inputNum;
        thebook.setNumber(kazu);
        booksDao.save(thebook);
        thebook.setSaleNumber(null);
        return thebook;
    }

    @Override
    public Books changePrice(String isbn, int newPrice) {
        Books thebook2 = booksDao.findAllByIsbn(isbn);
        thebook2.setPrice(newPrice);
        booksDao.save(thebook2);
        thebook2.setSaleNumber(null);
        return thebook2;
    }

    //書籍銷售
    //1. 消費者購買(可買多本)：只顯示書名、ISBN、作者、價格、「購買數量，購買總價格」
    //2. 銷售量+1，庫存量-1
    @Override
    public Books SaleOut(String isbn, int saleNum) {
        Books theBook3 = booksDao.findAllByIsbn(isbn);

        int salekazu = theBook3.getSaleNumber() + saleNum;
        theBook3.setSaleNumber(salekazu);

        int seNumber = theBook3.getNumber() - saleNum;
        theBook3.setNumber(seNumber);

        booksDao.save(theBook3);

        theBook3.setNumber(null);
        theBook3.setClassBun(null);
        theBook3.setSaleNumber(null);

        return theBook3;
    }

    //暢銷排行榜
    //暢銷書排行榜(依照銷售量前5，排序），只顯示書名、ISBN、作者、價格
    //依照銷售量排序
    @Override
    public List<Books> ranking() {
        List<Books> theBook4 = booksDao.findTop5ByOrderBySaleNumberDesc();

        return theBook4;
    }
}
