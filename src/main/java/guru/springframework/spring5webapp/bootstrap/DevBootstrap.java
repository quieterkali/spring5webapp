package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;


@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private BookRepository bookRepository;
    private AuthorRepository authorRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(BookRepository bookRepository, AuthorRepository authorRepository, PublisherRepository publisherRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData() {

        Author hannah = new Author("Hannah", "Maflany");
        Publisher publisherHannah = new Publisher("Daouda", "Rua Maflany");
        publisherRepository.save(publisherHannah);
        Book book = new Book("les merveilleuses aventures de la petite Maflany", "03042017", publisherHannah);
        hannah.getBooks().add(book);
        book.getAuthors().add(hannah);

        authorRepository.save(hannah);
        bookRepository.save(book);

        Author teca = new Author("Teca", "Alves");
        Book livro = new Book("les merveilleuses aventures de Teca", "29121976", publisherHannah);
        teca.getBooks().add(livro);
        livro.getAuthors().add(teca);

        authorRepository.save(teca);
        bookRepository.save(livro);

    }
}
