package com.ruoyi.system.domain.vo;

import java.util.Objects;

public class UserVo {
    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserVo)) return false;
        UserVo userVo = (UserVo) o;
        return Objects.equals(getName(), userVo.getName()) &&
                Objects.equals(getAge(), userVo.getAge());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAge());
    }

    @Override
    public String toString() {
        return "UserVo{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public UserVo(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
