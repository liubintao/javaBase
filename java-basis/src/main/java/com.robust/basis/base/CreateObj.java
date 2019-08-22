package com.robust.basis.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.*;

/**
 * Created by neil on 2017/4/28.
 * 创建对象的几种方式
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateObj implements Cloneable, Serializable{

    private static final long serialVersionUID = 3712945471842625795L;
    private String name;
    private Integer age;

    public static void main(String[] args) throws Exception {
        //new
        CreateObj obj = new CreateObj("张三", 12);
        System.out.println(obj);

        //clone
        CreateObj cloneObj = (CreateObj) obj.clone();
        System.out.println(cloneObj);

        //Class.forName and newInstance
        CreateObj forNameCls = (CreateObj) Class.forName("org.btliu.base.CreateObj").newInstance();
        System.out.println(forNameCls);

        //Class.class.newInstance
        CreateObj cls = CreateObj.class.newInstance();
        System.out.println(cls);

        //Constructor
        CreateObj cls2 = (CreateObj) CreateObj.class.getConstructors()[0].newInstance();
        System.out.println(cls2);
        CreateObj cls3 = (CreateObj) CreateObj.class.getConstructors()[1].newInstance("李四", 3);
        System.out.println(cls3);
        CreateObj cls4 = CreateObj.class.getDeclaredConstructor(String.class, Integer.class)
                .newInstance("王五", 4);
        System.out.println(cls4);

        //反序列化
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("obj.dat"));
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("obj.dat"))) {
            oos.writeObject(cls4);

            CreateObj cls5 = (CreateObj) ois.readObject();
            System.out.println(cls5);
        }
    }
}
