package com.robust.basis.rmi.server;

import com.robust.basis.rmi.IHelloService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @Description:
 * @Author: robust
 * @CreateDate: 2019/2/14 8:18
 * @Version: 1.0
 */
public class HelloServiceImpl extends UnicastRemoteObject implements IHelloService {

    public HelloServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public String say(String msg) throws RemoteException {
        return "Hello, " + msg;
    }
}
