package com.ead.authuser.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import java.util.UUID;
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

  @JsonView(UserView.RegistationPost.class)
  private String username;

  @JsonView(UserView.RegistationPost.class)
  private String email;

  @JsonView({ UserView.RegistationPost.class, UserView.PasswordPut.class })
  private String password;

  @JsonView(UserView.PasswordPut.class)
  private String oldPassword;

  @JsonView({ UserView.RegistationPost.class, UserView.UserPut.class })
  private String fullName;

  @JsonView({ UserView.RegistationPost.class, UserView.UserPut.class })
  private String phoneNumber;

  @JsonView({ UserView.RegistationPost.class, UserView.UserPut.class })
  private String cpf;

  @JsonView(UserView.ImagePut.class)
  private String imageUrl;
}
