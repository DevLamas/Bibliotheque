package com.example.bibliotheque.Object;

public class ObjectLoan {
    int Id;
    int UserId;
    int BookId;
    String BorrowingDate;
    String RenderDate;

    public ObjectLoan() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public int getBookId() {
        return BookId;
    }

    public void setBookId(int bookId) {
        BookId = bookId;
    }

    public String getBorrowingDate() {
        return BorrowingDate;
    }

    public void setBorrowingDate(String borrowingDate) {
        BorrowingDate = borrowingDate;
    }

    public String getRenderDate() {
        return RenderDate;
    }

    public void setRenderDate(String renderDate) {
        RenderDate = renderDate;
    }
}
