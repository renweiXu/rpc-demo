package cn.rpc.server;

import cn.rpc.service.UserService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author xurw
 * @description .
 * @date 2021/1/20
 */
public class Server {

    public static void main(String[] args) throws Exception {
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
        String clazzName = ois.readUTF();
        String methodName = ois.readUTF();
        Class [] paramsTypes = (Class [])ois.readObject();
        Object[] params = (Object[])ois.readObject();
        UserService userService = new UserServiceImpl();
        Method method = userService.getClass().getMethod(methodName, paramsTypes);
        Object result = method.invoke(userService, params);
        // 将查询结果 写入到流中
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(result);
    }
}
