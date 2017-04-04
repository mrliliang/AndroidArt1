// IBookManager.aidl
package com.liang.ipc;

// Declare any non-default types here with import statements
import com.liang.ipc.aidl.Book;

interface IBookManager {
    List<Book> getBookList();
    void addBook(in Book book);
}
