package com.robust.basis.rmi.client;

import com.robust.basis.rmi.IHelloService;
import com.robust.basis.rmi.server.HelloServiceImpl;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * @Description:
 * @Author: robust
 * @CreateDate: 2019/2/14 8:19
 * @Version: 1.0
 */
public class Client {
    public static void main(String[] args) throws RemoteException, MalformedURLException, NotBoundException {
//        IHelloService service = new HelloServiceImpl();
//        System.out.println(service.say("123"));
        IHelloService service = (IHelloService) Naming.lookup("rmi://127.0.0.1/hello");
        System.out.println(service.say("zhangSan"));
    }
}
