import { ConversationModel } from "./conversation-model";
import { UserModel } from "./user-model";

export type MessageModel = {
    id: number;
    content: string;
    conversation: ConversationModel;
    user: UserModel;
    postedAt: string;
    editedAt: string;
  };

  export type MessageRegistrationModel = {
    content: string;
    conversationId: number;
    userId: number;
  };

  export type MessageUpdateModel = {
    content: string;
  };