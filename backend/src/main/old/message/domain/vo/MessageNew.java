package old.message.domain.vo;

import old.conversation.domain.vo.ConversationPublicId;
import org.jilt.Builder;

@Builder
public record MessageNew(MessageContent messageContent,
                         ConversationPublicId conversationPublicId) {
}
