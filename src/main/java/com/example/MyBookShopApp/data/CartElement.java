package com.example.MyBookShopApp.data;

public class CartElement {
    private String Author;
    private String title;
    private Integer price;
    private Integer priceOld;
    private Integer sale;

    public Integer getSale() {
        return sale;
    }

    public void setSale(Integer sale) {
        this.sale = sale;
    }

    public CartElement() {
    }

    @Override
    public String toString() {
        return "CartElement{" +
                "Author='" + Author + '\'' +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", priceOld=" + priceOld +
                '}';
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getPriceOld() {
        return priceOld;
    }

    public void setPriceOld(Integer priceOld) {
        this.priceOld = priceOld;
    }
}

