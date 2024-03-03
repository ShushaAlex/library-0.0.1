package com.example.library001;

import com.example.library001.entity.Book;
import com.example.library001.entity.Student;
import com.example.library001.repository.StudentBookRepositoryService;
import com.example.library001.repository.impl.StudentBookRepository;
import com.example.library001.service.BookService;
import com.example.library001.service.StudentService;
import com.example.library001.service.impl.BookServiceImpl;
import com.example.library001.service.impl.StudentServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

		Student student = new Student(1, "Alex");
		Book book = new Book(1, "Java", "Arnold A.");
		BookService bookService = new BookServiceImpl();
		StudentService studentService = new StudentServiceImpl();
		StudentBookRepositoryService studentBook = StudentBookRepository.GET_INSTANCE();

		System.out.println("Add student: " + studentService.save(student));
		System.out.println("Save book: " + bookService.saveBook(book));
		System.out.println("Borrow: " + bookService.borrowBook(book.getId(), student.getId()));
		System.out.println("StudentBooks list after borrow : " + studentBook.getStudentBookList());
		System.out.println("Return: " + bookService.returnBook(book.getId()));
		System.out.println("StudentBooks list after return : " + studentBook.getStudentBookList());


	}

}
