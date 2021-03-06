package com.epam.streamdp.two;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Product implements Entity {
    private long productId;
    private String productName;
    private String stringUniversalProductCode;
    private String manufacturer;
    private double price;
    private long monthOfTheExpirationDate;
    private long amount;

    public Product() {
    }

    public Product(long productId, String productName, String stringUniversalProductCode, String manufacturer, double price, long monthOfTheExpirationDate, long amount) {
        this.productId = productId;
        this.productName = productName;
        this.stringUniversalProductCode = stringUniversalProductCode;
        this.manufacturer = manufacturer;
        this.price = price;
        this.monthOfTheExpirationDate = monthOfTheExpirationDate;
        this.amount = amount;
    }

    @Override
    public String toString() {
        String monthOfTheExpirationDateString = monthOfTheExpirationDate == Long.MAX_VALUE ? "Неограничен" : monthOfTheExpirationDate + "мес.";
        return "Products [" +
                "Id = " + productId + "," + '\t' +
                "Name=" + productName + "," + '\t' +
                "UPC=" + stringUniversalProductCode + "," + '\t' +
                "Manufacturer=" + manufacturer + "," + '\t' +
                "Price=" + price + "," + '\t' +
                "Expiration date=" + monthOfTheExpirationDateString + "," + '\t' +
                "Amount=" + amount +
                ']';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (productId != product.productId) return false;
        if (Double.compare(product.price, price) != 0) return false;
        if (monthOfTheExpirationDate != product.monthOfTheExpirationDate) return false;
        if (amount != product.amount) return false;
        if (!Objects.equals(productName, product.productName)) return false;
        if (!Objects.equals(stringUniversalProductCode, product.stringUniversalProductCode)) return false;
        return Objects.equals(manufacturer, product.manufacturer);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (productId ^ (productId >>> 32));
        result = 31 * result + (productName != null ? productName.hashCode() : 0);
        result = 31 * result + (stringUniversalProductCode != null ? stringUniversalProductCode.hashCode() : 0);
        result = 31 * result + (manufacturer != null ? manufacturer.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (int) (monthOfTheExpirationDate ^ (monthOfTheExpirationDate >>> 32));
        result = 31 * result + (int) (amount ^ (amount >>> 32));
        return result;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getStringUniversalProductCode() {
        return stringUniversalProductCode;
    }

    public void setStringUniversalProductCode(String stringUniversalProductCode) {
        this.stringUniversalProductCode = stringUniversalProductCode;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getMonthOfTheExpirationDate() {
        return monthOfTheExpirationDate;
    }

    public void setMonthOfTheExpirationDate(long monthOfTheExpirationDate) {
        this.monthOfTheExpirationDate = monthOfTheExpirationDate;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public List<Product> findProductsByName(List<Product> list, String name) {
        return list
                .stream()
                .filter(product -> product.getProductName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    public List<Product> findProductsByPriceLessThanThis(List<Product> list, double price) {
        return list
                .stream()
                .filter(product -> product.getPrice() <= price)
                .collect(Collectors.toList());
    }

    public List<Product> findProductsByStorageTimeMoreThanThis(List<Product> list, long storageTime) {
        return list
                .stream()
                .filter(product -> product.getMonthOfTheExpirationDate() > storageTime)
                .collect(Collectors.toList());
    }
}
