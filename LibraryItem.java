import java.util.ArrayList;

public class LibraryItem {
    private int itemId;
    private String title;

    public LibraryItem(int itemId, String title) {
        this.itemId = itemId;
        this.title = title;
    }
    public int getItemId() { return itemId; }
    public String getTitle() { return title; }
    public void displayDetails() {
        System.out.println(itemId + " " + title);
    }

    public static void main(String[] args) {
        Library library = new Library();
        library.addBook(new Book(101, "Java Programming", "James Gosling"));
        library.addBook(new Book(102, "Spring Boot", "Rod Johnson"));
        library.addBook(new Book(103, "Python Basics", "Guido van Rossum"));
        library.addBook(new Book(104, "C++", "Bjarne Stroustrup"));
        library.addBook(new Book(105, "DSA", "Robert Lafore"));
        library.displayAvailableBooks();
        try {
            System.out.println("\nIssue Book:\n101");
            library.issueBook(101);
        } catch (BookNotAvailableException e) {
            System.out.println("Exception: " + e.getMessage());
        }
        try {
            System.out.println("\nIssue Book:\n101");
            library.issueBook(101);
        } catch (BookNotAvailableException e) {
            System.out.println("Exception");
            System.out.println("Book Already Issued");
        }
        System.out.println("\nReturn Book:\n101");
        library.returnBook(101);
        library.displayAvailableBooks();
    }
}

class Book extends LibraryItem {
    private String authorName;
    private boolean isIssued;
    public Book(int itemId, String title, String authorName) {
        super(itemId, title);
        this.authorName = authorName;
        this.isIssued = false;
    }
    public boolean isIssued() { return isIssued; }
    public void setIssued(boolean issued) { isIssued = issued; }
    @Override
    public void displayDetails() {
        System.out.println(getItemId() + " " + getTitle());
    }
}

class BookNotAvailableException extends Exception {
    public BookNotAvailableException(String message) { super(message); }
}

class Library {
    private ArrayList<Book> books;
    public Library() { books = new ArrayList<>(); }
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
                } else {
                    throw new BookNotAvailableException("Book Already Issued");
                }
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
    }
    public void displayAvailableBooks() {
        System.out.println("Available Books:");
        for (Book book : books) {
            if (!book.isIssued()) book.displayDetails();
        }
    }
}