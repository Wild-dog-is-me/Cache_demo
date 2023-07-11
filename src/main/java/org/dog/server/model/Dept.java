package org.dog.server.model;

import lombok.Data;

/**
 * @Author: Odin
 * @Date: 2023/7/10 23:16
 * @Description:
 */
@Data
public class Dept {
    private Long id;
    /**
     * 1M
     */
    private byte[] bytes = new byte[1024 * 1024];
    public Dept(Long id){
        this.id = id;
    }

    /**
     * jvm要回收你这个对象了，会回调这个方法，你可以在这个方法里面完成资源的清理
     * 或者完成自救
     * @throws Throwable
     */
    @Override
    protected void finalize() throws Throwable {
        System.out.println(id + "将要被回收,赶紧想法自救吧....");
    }
}
