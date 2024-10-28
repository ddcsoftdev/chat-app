package com.chatapp.user.domain.aggregate;

import com.chatapp.user.domain.vo.UserEmail;
import com.chatapp.user.domain.vo.UserFirstName;
import com.chatapp.user.domain.vo.UserImageUrl;
import com.chatapp.user.domain.vo.UserLastName;
import com.chatapp.user.domain.vo.UserPassword;
import com.chatapp.user.domain.vo.UserPublicId;
import java.lang.Long;
import java.time.Instant;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("Jilt-1.5")
public class UserBuilder {
  private UserLastName lastName;

  private UserFirstName firstname;

  private UserEmail email;

  private UserPassword password;

  private UserPublicId userPublicId;

  private UserImageUrl imageUrl;

  private Instant lastModifiedDate;

  private Instant createdDate;

  private Instant lastSeen;

  private Set<Authority> authorities;

  private Long dbId;

  public static UserBuilder user() {
    return new UserBuilder();
  }

  public UserBuilder lastName(UserLastName lastName) {
    this.lastName = lastName;
    return this;
  }

  public UserBuilder firstname(UserFirstName firstname) {
    this.firstname = firstname;
    return this;
  }

  public UserBuilder email(UserEmail email) {
    this.email = email;
    return this;
  }

  public UserBuilder password(UserPassword password) {
    this.password = password;
    return this;
  }

  public UserBuilder userPublicId(UserPublicId userPublicId) {
    this.userPublicId = userPublicId;
    return this;
  }

  public UserBuilder imageUrl(UserImageUrl imageUrl) {
    this.imageUrl = imageUrl;
    return this;
  }

  public UserBuilder lastModifiedDate(Instant lastModifiedDate) {
    this.lastModifiedDate = lastModifiedDate;
    return this;
  }

  public UserBuilder createdDate(Instant createdDate) {
    this.createdDate = createdDate;
    return this;
  }

  public UserBuilder lastSeen(Instant lastSeen) {
    this.lastSeen = lastSeen;
    return this;
  }

  public UserBuilder authorities(Set<Authority> authorities) {
    this.authorities = authorities;
    return this;
  }

  public UserBuilder dbId(Long dbId) {
    this.dbId = dbId;
    return this;
  }

  public User build() {
    return new User(lastName, firstname, email, password, userPublicId, imageUrl, lastModifiedDate, createdDate, lastSeen, authorities, dbId);
  }
}
