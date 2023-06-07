package com.ruoyi.system.domain.vo;

import java.util.List;

public class Directory {
    // 目录标签
    private String label;

    // 子目录
    private List<Directory> children;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<Directory> getChildren() {
        return children;
    }

    public Directory(String label, List<Directory> children) {
        this.label = label;
        this.children = children;
    }

    public void setChildren(List<Directory> children) {
        this.children = children;
    }

    public Directory() {
    }

    @Override
    public String toString() {
        return "Directory{" +
                "label='" + label + '\'' +
                ", children=" + children +
                '}';
    }
}
