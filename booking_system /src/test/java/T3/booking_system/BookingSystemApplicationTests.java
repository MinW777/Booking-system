package T3.booking_system;

import T3.booking_system.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BookingSystemApplicationTests {
	@Autowired
	private BookService bookService;

	@Test
	void contextLoads() {

	}

}
