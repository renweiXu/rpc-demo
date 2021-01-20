package cn.rpc.client;

import cn.rpc.service.UserService;

/**
 * @author xurw
 * @description .
 * @date 2021/1/20
 */
public class Client {

    public static void main(String[] args) {
        UserService service = (UserService)ClientSub.getSub(UserService.class);
        String name = service.getName(1);
        System.out.println(service.getId("李四"));
        System.out.println(name);
    }
}
