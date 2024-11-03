package old.conversation.domain.vo;

import old.shared.error.domain.Assert;
import old.user.domain.vo.UserPublicId;
import org.jilt.Builder;

import java.util.Set;

@Builder
public class CreateConversation {

    private final Set<UserPublicId> members;

    private final ConversationName name;

    public CreateConversation(Set<UserPublicId> members, ConversationName name) {
        assertMandatoryFields(members, name);
        this.members = members;
        this.name = name;
    }

    private void assertMandatoryFields(Set<UserPublicId> members, ConversationName name) {
        Assert.notNull("name", name);
        Assert.notNull("members", members);
    }

    public Set<UserPublicId> getMembers() {
        return members;
    }

    public ConversationName getName() {
        return name;
    }
}
