package com.nfjokes.model;

import com.nfjokes.utils.Tools;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterModel extends User {
    private MultipartFile multipartImage;
    private String rePassword;
    private Boolean terms;

    public User getUser(){
        User user = new User();
        user.setEmail(this.getEmail());
        user.setName(this.getName());
        user.setPassword(this.rePassword);
        user.setImage(Tools.setImage(this.multipartImage));
        return user;
    }
}
