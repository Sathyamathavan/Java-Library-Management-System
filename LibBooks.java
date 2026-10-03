package JAVADEMO;

import java.util.ArrayList;

public class LibBooks {

    private ArrayList<Book> books;

    public LibBooks() {
        books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book Added Successfully");
    }

    public void issueBook(int bookId) throws BookNotAvailableException {

        for (Book book : books) {

            if (book.getItemId() == bookId) {

                if (!book.isIssued()) {
                    book.setIssued(true);
                    System.out.println("Book Issued Successfully");
                    return;
                }

                throw new BookNotAvailableException("Book Already Issued");
            }
        }

        throw new BookNotAvailableException("Book Not Found");
    }

    public void returnBook(int bookId) {

        for (Book book : books) {

            if (book.getItemId() == bookId) {
                book.setIssued(false);
                System.out.println("Book Returned Successfully");
                return;
            }
        }

        System.out.println("Book Not Found");
    }

    public void displayAvailableBooks() {

        System.out.println("\nAvailable Books:");

        for (Book book : books) {

            if (!book.isIssued()) {
                book.displayDetails();
            }
        }
    }

    public static void main(String[] args) {

        LibBooks library = new LibBooks();

        library.addBook(
            new Book(101, "Java Programming", "James Gosling")
        );

        library.addBook(
            new Book(102, "Spring Boot", "Rod Johnson")
        );

        library.addBook(
            new Book(103, "Python Basics", "Guido van Rossum")
        );

        library.addBook(
            new Book(104, "C++", "Bjarne Stroustrup")
        );

        library.addBook(
            new Book(105, "DSA", "Robert Lafore")
        );

        library.displayAvailableBooks();

        try {

            System.out.println("\nIssue Book: 101");
            library.issueBook(101);

        } catch (BookNotAvailableException e) {

            System.out.println("Exception: " + e.getMessage());
        }

        try {

            System.out.println("\nIssue Book: 101");
            library.issueBook(101);

        } catch (BookNotAvailableException e) {

            System.out.println("Exception: " + e.getMessage());
        }

        System.out.println("\nReturn Book: 101");
        library.returnBook(101);

        library.displayAvailableBooks();
    }
}
