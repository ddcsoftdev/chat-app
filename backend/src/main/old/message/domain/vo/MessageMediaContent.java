package old.message.domain.vo;

import org.jilt.Builder;

public record MessageMediaContent(byte[] file,
                                  String mimetype) {
}
