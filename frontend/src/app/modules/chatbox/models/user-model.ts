import { AuthRoleEnum } from "./enums/auth-role-enum";
import { ConversationModel } from "./conversation-model";

export type UserModel = {
    id: number,
    firstName: string,
    lastName: string,
    nickname: string,
    email: string,
    role: AuthRoleEnum,
    conversations: Set<ConversationModel>
}

export type UserRegistrationModel = {
    firstName: string,
    lastName: string,
    nickname: string,
    email: string,
    role: AuthRoleEnum,
}

export type UserUpdateModel = {
    firstName: string,
    lastName: string,
    nickname: string,
    email: string,
    role: AuthRoleEnum,
}