package com.liang.ipc.binderpool;

import android.os.RemoteException;

/**
 * Created by liliang on 2017/4/28.
 */

public class ComputeImpl extends ICompute.Stub {
    @Override
    public int add(int a, int b) throws RemoteException {
        return a + b;
    }
}
