package library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepo bookRepo;

    @Autowired
    public BookService(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    public List<Book> getBooks() {
        return bookRepo.findAll();
    }

    public void addNewBook(Book book) {
        Optional<Book> bookOptional = bookRepo.findBookByTitle(book.getTitle());
        if (bookOptional.isPresent()) {
            throw new IllegalStateException("Book already present");
        }
        bookRepo.save(book);
    }

    public void deleteBook(Long bookId) {
        boolean exists = bookRepo.existsById(bookId);
        if (!exists) throw new IllegalStateException("Book with ID " + bookId + " does not exist");
        bookRepo.deleteById(bookId);
    }

    @Transactional
    public void updateBook(Long bookId, String title, String author, String publisher) {
        Book book = bookRepo.findById(bookId).orElseThrow(() -> new IllegalStateException("Book with ID " + bookId + " does not exist"));

        if (title != null && title.length() > 0 && !Objects.equals(book.getTitle(), title)) {
            book.setTitle(title);
        }

        if (author != null && author.length() > 0 && !Objects.equals(book.getAuthor(), author)) {
            book.setAuthor(author);
        }

        if (publisher != null && publisher.length() > 0 && !Objects.equals(book.getPublisher(), publisher)) {
            book.setPublisher(publisher);
        }

    }
}
