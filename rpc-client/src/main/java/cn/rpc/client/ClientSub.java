package cn.rpc.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;

/**
 * @author xurw
 * @description 客户端存根
 * @date 2021/1/20
 */
public class ClientSub {

    static Object getSub(final Class clazz){
        InvocationHandler invocationHandler = new InvocationHandler() {

            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // 与服务端建立连接
                Socket socket = new Socket("127.0.0.1", 5555);
                OutputStream ous = socket.getOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(ous);
                // 服务类型
                oos.writeUTF(clazz.getName());
                // 方法名
                oos.writeUTF(method.getName());
                // 参数类型
                oos.writeObject(method.getParameterTypes());
                // 方法参数
                oos.writeObject(args);
                //接收服务端返回的的结果
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Object o = ois.readObject();

                return o;
            }
        };
        Object o = Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, invocationHandler);
        return o;
    }
}
