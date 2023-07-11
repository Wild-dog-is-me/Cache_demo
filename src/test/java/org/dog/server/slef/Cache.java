package org.dog.server.slef;

/**
 * @Author: Odin
 * @Date: 2023/7/10 22:11
 * @Description:
 */
public interface Cache {

    void put(Object key, Object value);

    void remove(Object key);

    void clear();

    Object get(Object key);

    int size();

}
