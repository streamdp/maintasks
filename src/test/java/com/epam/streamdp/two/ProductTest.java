package com.epam.streamdp.two;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class ProductTest {
    private static List<Product> productList = Arrays.asList(
            new Product(1, "Coffee", "4607085440118", "ООО \"Нестле Кубань\"", 20.5, 12, 100),
            new Product(2, "Coffee", "5855427977539", "ООО \"Якобс Рус\"", 22.5, 24, 99),
            new Product(3, "Coffee", "4607066400391", " ООО \"Гранд - НН\"", 1.5, 6, 77),
            new Product(4, "Coffee", "4607033631742 ", "ООО \"Леаланис\"", 5.5, 12, 88),
            new Product(5, "Лампа", "8727900021103", "PLP S.A.", 10.5, Long.MAX_VALUE, 40),
            new Product(6, "Лампа", "8727900021301 ", "PLP S.A.", 6.5, Long.MAX_VALUE, 33),
            new Product(7, "Лампа", "8711500363954 ", "Philips Lighting Poland S.A.", 1.5, Long.MAX_VALUE, 42),
            new Product(8, "Клей", "4600611840079", "ООО \"Краски Текс\"", 13.3, 12, 11),
            new Product(9, "Клей", "4601541001585", "Герметик-Трейд", 15.6, 18, 22),
            new Product(10, "Валик", "8727900021301 ", "Matrize GmbH", 8.5, Long.MAX_VALUE, 56),
            new Product(11, "Валик", "4044996020891 ", "Matrize GmbH", 11.5, Long.MAX_VALUE, 78),
            new Product(12, "Кисть", "4044996019925", "Matrize GmbH", 3.3, Long.MAX_VALUE, 98),
            new Product(13, "Кисть", "4034229000561", "Kraftool I/E GmbH", 5.6, Long.MAX_VALUE, 32)
    );

    @Test
    public void testFindProductsByName() {
        List<Product> productListByName = new Product().findProductsByName(productList, "Coffee");
        assertEquals(productListByName.get(0).getProductName().equals("Coffee"), productListByName.size() == 4);
    }

    @Test
    public void testFindProductsByPrice() {
        List<Product> productListByName = new Product().findProductsByPriceLessThanThis(productList, 4);
        assertEquals(productListByName.get(0).getPrice() == 1.5, productListByName.size() == 3);
    }

    @Test
    public void testFindProductsByStorageTime() {
        List<Product> productListByName = new Product().findProductsByStorageTimeMoreThanThis(productList, 36);
        assertEquals(productListByName.get(0).getMonthOfTheExpirationDate() == Long.MAX_VALUE, productListByName.size() == 7);
    }
}