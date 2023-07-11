package org.dog.server.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Odin
 * @Date: 2023/7/9 23:21
 * @Description:
 */

@Data
@Builder
public class User implements Serializable {

    private Long id;

    private String name;
}
