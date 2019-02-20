package cn.jamkong.common;

import org.springframework.beans.factory.annotation.Value;

//@PropertySource("classpath:/application.properties")
public class GlobalConfig {

    @Value("username")
    private String username;
    @Value("password")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
