package com.hujy.redisdemo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * Description
 *
 * @author hujy
 * @version 1.0
 * @date 2019-10-07 13:16
 */
@Setter
@Getter
@ToString
public class User implements Serializable {
    private Integer id;
    private String name;
    private Integer age;
}
