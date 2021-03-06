// IBookManager.aidl
package com.liang.ipc.aidl;

// Declare any non-default types here with import statements
import com.liang.ipc.aidl.Book;
import com.liang.ipc.aidl.IOnNewBookArrivedListener;

interface IBookManager {
    List<Book> getBookList();
    void addBook(in Book book);
    void registerListener(IOnNewBookArrivedListener listener);
    void unregisterListener(IOnNewBookArrivedListener listener);
}
