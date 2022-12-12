package com.baidu.common.entity;

public class House {
    private Long id;

    private String code;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "House{" +
                "id=" + id +
                ", code='" + code + '\'' +
                '}';
    }
}
