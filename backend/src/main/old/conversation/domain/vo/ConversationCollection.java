package old.conversation.domain.vo;

import old.conversation.domain.aggregate.Conversation;
import old.shared.error.domain.Assert;

import java.util.List;

public record ConversationCollection(List<Conversation> conversations) {

    public ConversationCollection {
        Assert.field("conversations", conversations).notNull().noNullElement();
    }
}
