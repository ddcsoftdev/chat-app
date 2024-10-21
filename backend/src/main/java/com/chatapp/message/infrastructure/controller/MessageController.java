package com.chatapp.message.infrastructure.controller;

import com.chatapp.message.infrastructure.dto.MessageDto;
import com.chatapp.message.application.service.MessageApplicationService;
import com.chatapp.message.domain.aggregate.Message;
import com.chatapp.message.domain.vo.MessageNew;
import com.chatapp.shared.service.State;
import com.chatapp.shared.service.StatusNotification;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private final MessageApplicationService messageApplicationService;

    private ObjectMapper objectMapper = new ObjectMapper();

    public MessageController(MessageApplicationService messageApplicationService) {
        this.messageApplicationService = messageApplicationService;
    }

    @PostMapping(value = "/send", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<MessageDto> send(@RequestPart(value = "file", required = false)
                                            MultipartFile file,
                                           @RequestPart("dto") String messageRaw) throws IOException {
        MessageDto restMessage = objectMapper.readValue(messageRaw, MessageDto.class);
        if(restMessage.hasMedia()) {
            restMessage.setMediaAttachment(file.getBytes(), file.getContentType());
        }

        MessageNew messageSendNew = MessageDto.toDomain(restMessage);

        State<Message, String> sendState = messageApplicationService.send(messageSendNew);
        if(sendState.getStatus().equals(StatusNotification.OK)) {
            return ResponseEntity.ok(MessageDto.from(sendState.getValue()));
        } else {
            ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, sendState.getError());
            return ResponseEntity.of(problemDetail).build();
        }
    }
}
