package cn.rpc.server;

import cn.rpc.service.UserService;
import cn.rpc.util.RpcRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xurw
 * @description 服务端存根
 * @date 2021/1/20
 */
public class ServerSub {

    private static Map<String,Object> register = new HashMap<String, Object>();

    static {
        // 服务启动的时候 将所有的服务注册到注册中心
        register.put(UserService.class.getName(),new UserServiceImpl());
    }

    public static void main(String[] args) throws Exception {
        System.out.println("服务端已启动...");
        ServerSocket server = new ServerSocket(5555);
        while (true){
            // 持续接收数据
            Socket client = server.accept();
            process(client);
            client.close();
        }
    }

    public static void process(Socket socket) throws Exception {
        // 获取输入参数
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        RpcRequest request = (RpcRequest)ois.readObject();
        UserService userService = (UserServiceImpl)register.get(request.getClazzName());
        Method method = userService.getClass().getMethod(request.getMethodName(), request.getParameterTypes());
        Object result = method.invoke(userService, request.getParams());
        // 将查询结果 写入到流中
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(result);
    }
}
