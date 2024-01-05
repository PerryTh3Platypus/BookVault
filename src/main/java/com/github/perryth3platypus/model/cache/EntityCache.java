package com.github.perryth3platypus.model.cache;

import com.github.perryth3platypus.model.entities.Book;

import java.util.HashMap;
import java.util.List;

public class EntityCache {
    public static HashMap<Integer, Book> bookCache; // key = db primary key, value = entity

    public static void generateBookCache(List<Book> books){
        bookCache = new HashMap<>();
        for (Book book : books){
            bookCache.put(book.getBookId(), book);
        }
    }

}
