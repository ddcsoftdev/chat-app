package old.user.infrastructure.entity;

import old.conversation.infrastructure.entity.ConversationEntity;
import com.chatapp.user.domain.aggregate.UserBuilder;
import com.chatapp.user.domain.vo.*;
import old.shared.jpa.AbstractAuditingEntity;
import old.user.domain.aggregate.User;
import com.chatapp.user.infrastructure.entity.UserOldEntityBuilder;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;
import org.jilt.Builder;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "chat_user")
@Builder
public class UserOldEntity extends AbstractAuditingEntity<Long> {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userSequenceGenerator")
    @SequenceGenerator(name = "userSequenceGenerator", sequenceName = "user_sequence", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "email")
    private String email;

    @Column(name = "image_url")
    private String imageUrl;

    @UuidGenerator
    @Column(name = "public_id", updatable = false)
    private UUID publicId;

    @Column(name = "last_seen")
    private Instant lastSeen = Instant.now();

    @ManyToMany(cascade = CascadeType.DETACH)
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "name")}
    )
    private Set<AuthorityEntity> authorities = new HashSet<>();

    @ManyToMany(cascade = CascadeType.DETACH)
    @JoinTable(
            name = "user_conversation",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "conversation_id", referencedColumnName = "id")}
    )
    private Set<ConversationEntity> conversations = new HashSet<>();

    public UserOldEntity(Long id, String lastName, String firstName, String email, String imageUrl,
                         UUID publicId, Instant lastSeen, Set<AuthorityEntity> authorities,
                         Set<ConversationEntity> conversations) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.imageUrl = imageUrl;
        this.publicId = publicId;
        this.lastSeen = lastSeen;
        this.authorities = authorities;
        this.conversations = conversations;
    }

    public UserOldEntity() {
    }

    public void updateFromUser(User user) {
        this.email = user.getEmail().value();
        this.lastName = user.getLastName().value();
        this.firstName = user.getFirstname().value();
        this.imageUrl = user.getImageUrl().value();
    }

    public static UserOldEntity from(User user) {
        UserOldEntityBuilder userEntityBuilder = UserOldEntityBuilder.userOldEntity();

        if (user.getImageUrl() != null) {
            userEntityBuilder.imageUrl(user.getImageUrl().value());
        }

        if (user.getUserPublicId() != null) {
            userEntityBuilder.publicId(user.getUserPublicId().value());
        }

        return userEntityBuilder
                .authorities(AuthorityEntity.from(user.getAuthorities()))
                .email(user.getEmail().value())
                .firstName(user.getFirstname().value())
                .lastName(user.getLastName().value())
                .id(user.getDbId())
                .lastSeen(user.getLastSeen())
                .build();
    }

    public static User toDomain(UserOldEntity userOldEntity) {
        UserBuilder userBuilder = UserBuilder.user();

        if (userOldEntity.getImageUrl() != null) {
            userBuilder.imageUrl(new UserImageUrl(userOldEntity.getImageUrl()));
        }

        return userBuilder
                .email(new UserEmail(userOldEntity.getEmail()))
                .lastName(new UserLastName(userOldEntity.getLastName()))
                .firstname(new UserFirstName(userOldEntity.getFirstName()))
                .authorities(AuthorityEntity.toDomain(userOldEntity.getAuthorities()))
                .userPublicId(new UserPublicId(userOldEntity.getPublicId()))
                .lastModifiedDate(userOldEntity.getLastModifiedDate())
                .createdDate(userOldEntity.getCreatedDate())
                .dbId(userOldEntity.getId())
                .lastSeen(userOldEntity.getLastSeen())
                .build();
    }

    public static Set<UserOldEntity> from(List<User> users) {
        return users.stream().map(UserOldEntity::from).collect(Collectors.toSet());
    }

    public static Set<User> toDomain(Set<UserOldEntity> user) {
        return user.stream().map(UserOldEntity::toDomain).collect(Collectors.toSet());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public UUID getPublicId() {
        return publicId;
    }

    public void setPublicId(UUID publicId) {
        this.publicId = publicId;
    }

    public Instant getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(Instant lastSeen) {
        this.lastSeen = lastSeen;
    }

    public Set<AuthorityEntity> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<AuthorityEntity> authorities) {
        this.authorities = authorities;
    }

    public Set<ConversationEntity> getConversations() {
        return conversations;
    }

    public void setConversations(Set<ConversationEntity> conversations) {
        this.conversations = conversations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserOldEntity that = (UserOldEntity) o;
        return Objects.equals(lastName, that.lastName) && Objects.equals(firstName, that.firstName) && Objects.equals(email, that.email) && Objects.equals(imageUrl, that.imageUrl) && Objects.equals(publicId, that.publicId) && Objects.equals(lastSeen, that.lastSeen);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastName, firstName, email, imageUrl, publicId, lastSeen);
    }
}
