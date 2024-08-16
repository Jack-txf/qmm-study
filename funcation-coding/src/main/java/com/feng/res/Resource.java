package com.feng.res;

/**
 * @author Williams_Tian
 * @CreateDate 2024/8/16
 */
public class Resource {
    private String path;
    private String name;

    public Resource() {
    }

    public Resource(String path, String name) {
        this.path = path;
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "path='" + path + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
