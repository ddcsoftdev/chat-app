package com.chatapp.messaging.domain.message.vo;

import org.jilt.Builder;

public record MessageMediaContent(byte[] file,
                                  String mimetype) {
}
