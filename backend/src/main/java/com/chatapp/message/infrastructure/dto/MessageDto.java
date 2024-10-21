package com.chatapp.message.infrastructure.dto;

import com.chatapp.conversation.domain.vo.ConversationPublicId;
import com.chatapp.message.infrastructure.dto.MessageDtoBuilder;
import com.chatapp.message.domain.aggregate.Message;
import com.chatapp.message.domain.vo.MessageMediaContent;
import com.chatapp.message.domain.vo.MessageSendState;
import com.chatapp.message.domain.vo.MessageType;
import com.chatapp.message.domain.vo.MessageNew;
import com.chatapp.message.domain.vo.MessageNewBuilder;
import com.chatapp.message.domain.vo.*;
import org.jilt.Builder;

import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Builder
public class MessageDto {

    private String textContent;
    private Instant sendDate;
    private MessageSendState state;
    private UUID publicId;
    private UUID conversationId;
    private MessageType type;
    private byte[] mediaContent;
    private String mimeType;
    private UUID senderId;

    public MessageDto() {
    }

    public MessageDto(String textContent, Instant sendDate, MessageSendState state, UUID publicId, UUID conversationId,
                      MessageType type, byte[] mediaContent, String mimeType, UUID senderId) {
        this.textContent = textContent;
        this.sendDate = sendDate;
        this.state = state;
        this.publicId = publicId;
        this.conversationId = conversationId;
        this.type = type;
        this.mediaContent = mediaContent;
        this.mimeType = mimeType;
        this.senderId = senderId;
    }

    public static MessageDto from(Message message) {
        MessageDtoBuilder messageDtoBuilder = MessageDtoBuilder.messageDto()
                .textContent(message.getContent().text())
                .sendDate(message.getSentTime().date())
                .state(message.getSendState())
                .publicId(message.getPublicId().value())
                .conversationId(message.getConversationId().value())
                .type(message.getContent().type())
                .senderId(message.getSender().value());

        if (message.getContent().type() != MessageType.TEXT) {
            messageDtoBuilder.mediaContent(message.getContent().media().file())
                    .mimeType(message.getContent().media().mimetype());
        }

        return messageDtoBuilder.build();
    }

    public static List<MessageDto> from(Set<Message> messages) {
        return messages.stream().map(MessageDto::from).toList();
    }

    public static MessageNew toDomain(MessageDto restMessage) {
        MessageContentBuilder messageContent = MessageContentBuilder.messageContent()
                .type(restMessage.type)
                .text(restMessage.textContent);

        if (!restMessage.type.equals(MessageType.TEXT)) {
            messageContent.media(new MessageMediaContent(restMessage.mediaContent,
                    restMessage.mimeType));
        }
        return MessageNewBuilder.messageNew()
                .messageContent(messageContent.build())
                .conversationPublicId(new ConversationPublicId(restMessage.conversationId))
                .build();
    }

    public boolean hasMedia() {
        return !type.equals(MessageType.TEXT);
    }

    public void setMediaAttachment(byte[] file, String contentType) {
        this.mediaContent = file;
        this.mimeType = contentType;
    }

    public String getTextContent() {
        return textContent;
    }

    public Instant getSendDate() {
        return sendDate;
    }

    public MessageSendState getState() {
        return state;
    }

    public UUID getPublicId() {
        return publicId;
    }

    public UUID getConversationId() {
        return conversationId;
    }

    public MessageType getType() {
        return type;
    }

    public byte[] getMediaContent() {
        return mediaContent;
    }

    public String getMimeType() {
        return mimeType;
    }

    public UUID getSenderId() {
        return senderId;
    }
}
