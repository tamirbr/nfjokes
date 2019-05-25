package com.nfjokes.api;

import com.nfjokes.lang.ConstantsEn;
import com.nfjokes.model.User;
import com.nfjokes.model.UserRole;
import com.nfjokes.model.RegisterModel;
import com.nfjokes.service.UserService;
import com.nfjokes.utils.Tools;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.List;

@RestController
@RequestMapping("/api/users/")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("all")
    public List<User> all(){
        return userService.findAll();
    }

    @GetMapping("id/{id}")
    public User id(@PathVariable Long id){
        return userService.findById(id);
    }

    @GetMapping("email/{email}")
    public User email(@PathVariable String email){
        return userService.findByEmail(email);
    }

    @PostMapping("register")
    public User save(@RequestBody @Valid RegisterModel userViewModel, BindingResult result){

        System.out.println(userViewModel.getName());
        validatePasswordAndTermsOfUse(userViewModel, result);
        // Check for validation errors
        if(result.hasErrors()) {
            throw new ValidationException();
        }

        User user = registerAction(userViewModel.getUser(),userViewModel.getMultipartImage());

        return user;
    }

    private User registerAction(User newUser,MultipartFile multipartFile) {
        //TODO: remove user set enabled and auto login to confirm registered email
        try{
            newUser.setNonLocked(true);
            newUser.setEnabled(true);
            newUser.setRole(UserRole.USER);
            newUser.setImage(Tools.setImage(multipartFile));
            userService.save(newUser);
            return newUser;
        }catch (Exception e) {
            throw new HibernateException(e);
        }
    }

    private void validatePasswordAndTermsOfUse(RegisterModel registerModel, BindingResult result) {
        if(registerModel.getRePassword().toString().equals(registerModel.getPassword().toString()) == false){
            result.addError(new FieldError("newUser", "password", registerModel.getPassword(), false,
                    new String[1], null, ConstantsEn.PASSWORD_CONFIRMATION));
        } else if(registerModel.getRePassword().length() > ConstantsEn.PASSWORD_MAX ||
                registerModel.getRePassword().length() < ConstantsEn.PASSWORD_MIN) {
            result.addError(new FieldError("newUser", "password", registerModel.getPassword(), false,
                    new String[1], null, ConstantsEn.PASSWORD_LENGTH));
        } else if(registerModel.getTerms() == false){
            result.addError(new FieldError("newUser", "password", registerModel.getPassword(), false,
                    new String[1], null, ConstantsEn.TERMS_AGREE));
        }
    }
}
