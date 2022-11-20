package library;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class BookConfig {
    @Bean
    CommandLineRunner commandLineRunner(BookRepo repo) {
        return args -> {
            Book pictureOfDorianGray = new Book("The Picture Of Dorian Gray", "Oscar Wilde", "Penguin");
            Book frankensteinModernPrometheus = new Book("Frankenstein: Or, the Modern Prometheus", "Mary Shelley", "Penguin");
            repo.saveAll(List.of(pictureOfDorianGray, frankensteinModernPrometheus));
        };
    }
}
