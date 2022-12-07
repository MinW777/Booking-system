package T3.booking_system.repository;

import T3.booking_system.entity.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BooksDao extends JpaRepository<Books, String> {

    public List<Books> findByClassBunContaining(String classBun);

    public List<Books> findByNameLike(String name);

    public List<Books> findByAuthorLike(String author);

    public Books findAllByIsbn(String isbn);

    public List<Books> findTop5ByOrderBySaleNumberDesc();
}
