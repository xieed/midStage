package com.example.nacosclient;

/**
 * @className: TestData
 * @Description: TODO
 * @author: xieed
 * @date: 2019/12/16 18:34
 * @version: v1.0
 */
public class TestData {
    private String name;
    private String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "TestData{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
