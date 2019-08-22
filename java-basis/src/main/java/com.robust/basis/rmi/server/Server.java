package com.robust.basis.rmi.server;

import com.robust.basis.rmi.IHelloService;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * @Description:
 * @Author: robust
 * @CreateDate: 2019/2/14 8:21
 * @Version: 1.0
 */
public class Server {
    public static void main(String[] args) throws RemoteException, MalformedURLException {
        IHelloService service = new HelloServiceImpl();
        LocateRegistry.createRegistry(1099);
        Naming.rebind("rmi://127.0.0.1/hello", service);
        System.out.println("服务启动成功");
    }
}
