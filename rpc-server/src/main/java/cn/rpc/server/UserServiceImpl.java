package cn.rpc.server;

import cn.rpc.service.UserService;

/**
 * @author xurw
 * @description .
 * @date 2021/1/20
 */
public class UserServiceImpl implements UserService {
    /**
     * 根据id 获取名称服务
     *
     * @param id
     * @return
     */
    public String getName(Integer id) {
        switch (id){
            case 0 :
                return "张三";
            case 1 :
                return "李四";
            default:
                return "王五";
        }
    }

    /**
     * 根据name获取id
     *
     * @param name
     * @return
     */
    public Integer getId(String name) {
       if("张三".equals(name)){
           return 0;
       }else if("李四".equals(name)){
           return 1;
       }else{
           return 2;
       }
    }
}
