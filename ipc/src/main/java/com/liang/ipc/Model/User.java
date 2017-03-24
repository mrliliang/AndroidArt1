package com.liang.ipc.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by liliang on 2017/3/24.
 */

public class User implements Parcelable, Serializable {
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {

        @Override
        public User createFromParcel(Parcel source) {
            return null;
        }

        @Override
        public User[] newArray(int size) {
            return new User[0];
        }
    };
}
