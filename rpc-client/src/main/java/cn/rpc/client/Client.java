package cn.rpc.client;

import cn.rpc.service.UserService;

/**
 * @author xurw
 * @description  服务调用方
 * @date 2021/1/20
 */
public class Client {


    public static void main(String[] args) throws ClassNotFoundException {
        // 这一步 例如dubbo 会从注册中心获取提供者的信息  然后消费者 只需通过注解 注入即可
        UserService service = (UserService)ClientSub.getSub(UserService.class);
        String name = service.getName(1);
        System.out.println(name);
    }
}
