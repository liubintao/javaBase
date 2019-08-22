package com.robust.basis.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @Description:
 * @Author: robust
 * @CreateDate: 2019/2/14 8:16
 * @Version: 1.0
 */
public interface IHelloService extends Remote {
    String say(String msg) throws RemoteException;
}
