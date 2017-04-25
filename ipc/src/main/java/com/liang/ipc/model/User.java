package com.liang.ipc.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.liang.ipc.aidl.Book;

import java.io.Serializable;

/**
 * Created by liliang on 2017/3/24.
 */

public class User implements Parcelable, Serializable {

    private static final long serialVersionUID = 0;

    public int userId;
    public String userName;
    public boolean isMale;

    public Book book;

    public User() {

    }

    public User(int userId, String userName, boolean isMale) {
        this.userId = userId;
        this.userName = userName;
        this.isMale = isMale;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(userId);
        out.writeString(userName);
        out.writeInt(isMale ? 1 : 0);
        out.writeParcelable(book, 0);
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {

        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    private User(Parcel in) {
        userId = in.readInt();
        userName = in.readString();
        isMale = in.readInt() == 1;
        book = in.readParcelable(Thread.currentThread().getContextClassLoader());
    }

    @Override
    public String toString() {
        return String.format("User:{userId:%s, userName:%s, isMale:%s}, with child:{%s}",
                userId, userName, isMale, book);
    }
}
