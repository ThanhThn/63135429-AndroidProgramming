package ntu.lhqthanh.usingsqllite;

public class Books {
    int booksID, page;
    String bookName, description;
    float price;

    public Books(int booksID, int page, String bookName, String description, float price) {
        this.booksID = booksID;
        this.page = page;
        this.bookName = bookName;
        this.description = description;
        this.price = price;
    }

    public int getBooksID() {
        return booksID;
    }

    public int getPage() {
        return page;
    }

    public String getBookName() {
        return bookName;
    }

    public String getDescription() {
        return description;
    }

    public float getPrice() {
        return price;
    }
}
