package cn.rpc.service;

/**
 * @author xurw
 * @description 定义服务对外暴露的接口
 * @date 2021/1/20
 */
public interface UserService {

    /**
     * 根据id 获取名称
     * @param id
     * @return
     */
    String getName(Integer id);

    /**
     * 根据name获取id
     * @param name
     * @return
     */
    Integer getId(String name);
}
