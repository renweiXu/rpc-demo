package cn.rpc.client;

import cn.rpc.util.RpcRequest;

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

    static Object getSub(final Class clazz) throws ClassNotFoundException {
        InvocationHandler invocationHandler = new InvocationHandler() {

            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // 与服务端建立连接
                Socket socket = new Socket("127.0.0.1", 5555);
                OutputStream ous = socket.getOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(ous);
                // 将要调用的服务的 类名、方法名、参数列表等编码后 发给提供者
                RpcRequest request = new RpcRequest();
                request.setClazzName(clazz.getName());
                request.setMethodName(method.getName());
                // 存在方法重载的可能 所以需要把方法参数类型传过
                request.setParameterTypes(method.getParameterTypes());
                request.setParams(args);
                oos.writeObject(request);
                oos.flush();
                //接收服务端返回的的结果
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                return ois.readObject();
            }
        };
        // 返回动态代理的对象
        return Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, invocationHandler);
    }
}
