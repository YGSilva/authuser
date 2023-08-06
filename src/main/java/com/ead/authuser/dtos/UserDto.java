package com.ead.authuser.dtos;

import com.ead.authuser.validations.UsernameConstraint;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import java.util.UUID;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import lombok.Data;

@Data
// NAO MOSTRA AS VARIAVEIS NULAS NA VISUALIZACAO DO JSON
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {

  public interface UserView {
    public static interface RegistationPost {
    }

    public static interface UserPut {
    }

    public static interface PasswordPut {
    }

    public static interface ImagePut {
    }
  }

  private UUID userId;

  @NotBlank(groups = UserView.RegistationPost.class)
  @Size(min = 4, max = 60, groups = UserView.RegistationPost.class)
  @UsernameConstraint(groups = UserView.RegistationPost.class)
  @JsonView(UserView.RegistationPost.class)
  private String username;

  @NotBlank(groups = UserView.RegistationPost.class)
  @Email(groups = UserView.RegistationPost.class)
  @JsonView(UserView.RegistationPost.class)
  private String email;

  @NotBlank(groups = { UserView.RegistationPost.class, UserView.PasswordPut.class })
  @Size(min = 4, max = 60, groups = { UserView.RegistationPost.class, UserView.PasswordPut.class })
  @JsonView({ UserView.RegistationPost.class, UserView.PasswordPut.class })
  private String password;

  @NotBlank(groups = UserView.PasswordPut.class)
  @JsonView(UserView.PasswordPut.class)
  private String oldPassword;

  @NotBlank(groups = { UserView.RegistationPost.class, UserView.UserPut.class })
  @Size(min = 4, max = 60)
  @JsonView({ UserView.RegistationPost.class, UserView.UserPut.class })
  private String fullName;

  @NotBlank(groups = { UserView.RegistationPost.class, UserView.UserPut.class })
  @JsonView({ UserView.RegistationPost.class, UserView.UserPut.class })
  private String phoneNumber;

  @NotBlank(groups = { UserView.RegistationPost.class, UserView.UserPut.class })
  @CPF
  @JsonView({ UserView.RegistationPost.class, UserView.UserPut.class })
  private String cpf;

  @NotBlank(groups = UserView.ImagePut.class)
  @JsonView(UserView.ImagePut.class)
  private String imageUrl;
}
