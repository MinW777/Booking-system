package T3.booking_system.controller;

import T3.booking_system.entity.Books;
import T3.booking_system.vo.BookReq;
import T3.booking_system.vo.BookRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import T3.booking_system.service.BookService;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
//安安安安
@RestController
@Validated
public class BooksController {

    //注入serivce
    @Autowired
    @Qualifier("bookServiceImp")
    private BookService bookService;

    //=============================================
    //新增
    //(O)驗證參數.
    //(O)自定義狀態碼
    //(O)Exception處理 switch寫法
    //()Intercepter test
    @PostMapping("/updatenew")
    public ResponseEntity<Books> upadateNew(@RequestBody @Valid BookReq req) {
            Books resBook = bookService.addNewBook(req);
            //stwitch----
            if ( req.getName().isEmpty() ) {
                throw new RuntimeException("上傳失敗");
            } else if ( req.getIsbn().isEmpty() ) {
                throw new RuntimeException("請輸入有效ISBN格式");
            } else if ( req.getAuthor().isEmpty() ) {
                throw new RuntimeException("請輸入作者名稱");
            } else if ( req.getNumber() < 0 ) {
                throw new RuntimeException("請輸入有效庫存數量");
            } else if ( !StringUtils.hasText(req.getClassBun()) ) {
                throw new RuntimeException("請輸入書籍分類");
            } else {
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(resBook);
            }
    }


    //=============================================

    //找分類
    //(O)驗證參數
    //(O)自定義狀態碼
    //(O)Exception處理
    //()Intercepter test
    @GetMapping(value = "/books/{classbun}")
    public ResponseEntity<Set<Books>> findClass(@PathVariable @Validated String classbun) {
            if (bookService.findClass(classbun).isEmpty()) {
                throw new RuntimeException("搜尋失敗");
            } else {
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(bookService.findClass(classbun));
            }
    }

    //=============================================

    //書名、作者。找書 ， 只顯示書名、作者、價格、isbn
    //(O)驗證參數
    //(O)自定義狀態碼
    //(O)Exception處理
    //()Intercepter test
    @PostMapping (value = "/books/find")
    public ResponseEntity<BookRes> findBooktName(@RequestBody @Valid BookReq req) {
            BookRes res = new BookRes();
            if ( StringUtils.hasText(req.getName()) ) {
                List<Books> list = bookService.findBookName(req.getName());
                res.setBooks(list);
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(res);
            } else if (StringUtils.hasText(req.getAuthor())){
                List<Books> list2 = bookService.findBookAuthor(req.getAuthor());
                res.setBooks(list2);
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(res);
            } else {
                throw new RuntimeException("搜尋條件失敗");
            }
    }
    @PostMapping(value = "/books/isbn")
    public ResponseEntity<Books> findBookBango(@RequestBody @Valid BookReq req) {
        Books list = bookService.findBookISBN(req.getIsbn());
        if (list == null ) {
            throw new RuntimeException("搜尋失敗，請嘗試其他搜尋方式獲確認格式有無正確");
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(list);
    }

    //=============================================
    //書商搜尋
    //安安
    //(O)驗證參數
    //(O)自定義狀態碼
    //(X)Exception處理
    //()Intercepter test

    @PostMapping (value = "/books/sales/find")
    public ResponseEntity<BookRes> findBooktName2B(@RequestBody @Valid BookReq req) {
        BookRes res = new BookRes();
        if ( StringUtils.hasText(req.getName()) ) {
            List<Books> list = bookService.findBookName2B(req.getName());
            res.setBooks(list);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(res);
        } else {
            List<Books> list2 = bookService.findBookAuthor2B(req.getAuthor());
            res.setBooks(list2);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(res);
        }
    }


    //    書商ISBN找書
    @PostMapping(value = "/books/sales/isbn")
    public ResponseEntity<Books> findBookISBN(@RequestBody @Valid BookReq req) {
        Books list = bookService.findBookISBN2b(req.getIsbn());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(list);
    }

    //=============================================
    //更新書籍資料

    //更新進貨量
    //(O)驗證參數
    //(O)自定義狀態碼
    //(X)Exception處理
    //()Intercepter test
    @PostMapping(value = "/books/update/revenue")
    public ResponseEntity<Books> upadateBook(@RequestBody @Valid BookReq req) {
        Books list = new Books();
        if (StringUtils.hasText(req.getIsbn()) && (req.getNumber() > 0)) {
            list = bookService.addnumber(req.getIsbn(), req.getNumber());
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(list);
        } else {
            throw new RuntimeException("請輸入正確ISBN碼或是有效的進貨數量");
        }
    }

    //更新價格
    //(O)驗證參數
    //(O)自定義狀態碼
    //(O)Exception處理
    //()Intercepter test
    @PostMapping(value = "/books/update/price")
    public ResponseEntity<Books> upadateBookPrice(@RequestBody @Valid BookReq req) {
        Books list = new Books();
        if (StringUtils.hasText(req.getIsbn()) && req.getNumber() > 0) {
            list = bookService.changePrice(req.getIsbn(), req.getPrice());
            if (list != null) {
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(list);
            } else {
                throw new RuntimeException("書籍價格更新失敗");
            }
        } else {
            throw new RuntimeException("請輸入有效的ISBN碼及有效價格");
        }

    }


    //=============================================
    //書籍銷售
    //(O)驗證參數
    //(O)自定義狀態碼
    //(O)Exception處理
    //()Intercepter test
    @PostMapping(value = "/books/saleout")
    public ResponseEntity<BookRes> saleOut(@RequestBody @Valid BookReq req) {
        BookRes beel = new BookRes();
        if (StringUtils.hasText(req.getIsbn())) {
            //轉型
            Books list = bookService.SaleOut(req.getIsbn(), req.getSaleKazu());
            List<Books> some1Buy = Arrays.asList(list);
            System.out.println(some1Buy);
            //「購買數量，購買總價格」
            beel.setBooks(some1Buy);
            beel.setKauKazu(req.getSaleKazu());
            beel.setTotalCost(beel.getKauKazu() * list.getPrice());
        }

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(beel);
    }


    //=============================================
    //暢銷書排行
    //(X)驗證參數
    //(O)自定義狀態碼
    //(X)Exception處理
    //()Intercepter test
    @GetMapping("/books/top5")
    public ResponseEntity<BookRes> theTop5() {
        BookRes res = new BookRes();
        res.setBooks(bookService.ranking());

        if (res.getBooks().isEmpty()) {
            throw new RuntimeException("查無排行榜");
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(res);
    }

}
