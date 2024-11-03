package old.message.domain.vo;

import old.message.domain.aggregate.Message;
import old.user.domain.vo.UserPublicId;

import java.util.List;

public record MessageNotificationData(Message message, List<UserPublicId> userToNotify) {
}
