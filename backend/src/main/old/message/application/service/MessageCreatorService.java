package old.message.application.service;

import old.conversation.application.service.ConversationReaderService;
import old.conversation.domain.aggregate.Conversation;
import old.message.domain.aggregate.Message;
import com.chatapp.message.domain.aggregate.MessageBuilder;
import old.message.domain.vo.MessageNew;
import old.message.domain.repository.MessageRepository;
import old.message.domain.vo.MessagePublicId;
import old.message.domain.vo.MessageSendState;
import old.message.domain.vo.MessageSentTime;
import old.shared.service.State;
import old.user.domain.aggregate.User;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

public class MessageCreatorService {

    private final MessageRepository messageRepository;
    private final MessageChangeNotifier messageChangeNotifier;
    private final ConversationReaderService conversationReader;

    public MessageCreatorService(MessageRepository messageRepository, MessageChangeNotifier messageChangeNotifier,
                                 ConversationReaderService conversationReader) {
        this.messageRepository = messageRepository;
        this.messageChangeNotifier = messageChangeNotifier;
        this.conversationReader = conversationReader;
    }


    public State<Message, String> create(MessageNew messageSendNew, User sender) {
            Message newMessage = MessageBuilder.message()
                .content(messageSendNew.messageContent())
                .publicId(new MessagePublicId(UUID.randomUUID()))
                .sendState(MessageSendState.RECEIVED)
                .sentTime(new MessageSentTime(Instant.now()))
                .conversationId(messageSendNew.conversationPublicId())
                .sender(sender.getUserPublicId())
                .build();

        State<Message, String> creationState;
        Optional<Conversation> conversationToLink = conversationReader.getOneByPublicId(messageSendNew.conversationPublicId());
        if (conversationToLink.isPresent()) {
            Message messageSaved = messageRepository.save(newMessage, sender, conversationToLink.get());
            messageChangeNotifier.send(newMessage, conversationToLink.get().getMembers().stream()
                    .map(User::getUserPublicId).toList());
            creationState = State.<Message, String>builder().forSuccess(messageSaved);
        } else {
            creationState = State.<Message, String>builder().forError(
                    String.format("Unable to find the conversation to link the message with the " +
                            "following publicId %s", messageSendNew.conversationPublicId().value())
            );
        }
        return creationState;
    }

}
