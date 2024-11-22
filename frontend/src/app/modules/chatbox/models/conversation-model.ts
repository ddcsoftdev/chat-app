import { MessageModel } from "./message-model"
import { UserModel } from "./user-model"

export type ConversationModel = {
    id: number,
    users: Set<UserModel>,
    messages: Set<MessageModel>
}

export type ConversationRegistrationModel = {
    users: Set<UserModel>,
    conversations: Set<ConversationModel>
}