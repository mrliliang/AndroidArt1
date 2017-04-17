// IOnNewBookArrivedListener.aidl
package com.liang.ipc.aidl;

import com.liang.ipc.aidl.Book;

interface IOnNewBookArrivedListener {

    void onNewBookArrived(in Book newBook);
}
