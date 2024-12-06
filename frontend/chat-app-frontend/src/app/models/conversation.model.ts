import { MessageModel } from './message.model';
import { UserModelNoConversation } from './user.model';

export class ConversationModel {
  id: number;
  users: Set<UserModelNoConversation>;
  messages: Set<MessageModel>;
  isActive:boolean = true;//TODO: implement this in backend

  constructor(
    id: number,
    users: Set<UserModelNoConversation>,
    messages: Set<MessageModel>
  ) {
    this.id = id;
    this.users = users;
    this.messages = messages;
  }

  getId(): number {
    return this.id;
  }
  getUsers(): Set<UserModelNoConversation> {
    return this.users;
  }
  getMessages(): Set<MessageModel> {
    return this.messages;
  }
}

export class ConversationModelBuilder {
  private id!: number;
  private users: Set<UserModelNoConversation> = new Set();
  private messages: Set<MessageModel> = new Set();

  setId(id: number): ConversationModelBuilder {
    this.id = id;
    return this;
  }

  setUsers(users: Set<UserModelNoConversation>): ConversationModelBuilder {
    this.users = users;
    return this;
  }

  setMessages(messages: Set<MessageModel>): ConversationModelBuilder {
    this.messages = messages;
    return this;
  }

  build(): ConversationModel {
    this.validate();

    return new ConversationModel(this.id, this.users, this.messages);
  }

  private validate(): void {
    if (!this.id) throw new Error('Id is required');
    if (!this.users) throw new Error('Users is required');
    if (!this.messages) throw new Error('Messges is required');
  }

  static create(): ConversationModelBuilder {
    return new ConversationModelBuilder();
  }
}

export class ConversationRegistrationModel {
  private users: Set<UserModelNoConversation>;
  private messages: Set<MessageModel>;

  constructor(
    users: Set<UserModelNoConversation>,
    messages: Set<MessageModel>
  ) {
    this.users = users;
    this.messages = messages;
  }

  getUsers(): Set<UserModelNoConversation> {
    return this.users;
  }
  getMessages(): Set<MessageModel> {
    return this.messages;
  }
}

export class ConversationRegistrationModelBuilder {
  private users: Set<UserModelNoConversation> = new Set();
  private messages: Set<MessageModel> = new Set();

  setUsers(
    users: Set<UserModelNoConversation>
  ): ConversationRegistrationModelBuilder {
    this.users = users;
    return this;
  }

  setMessages(
    messages: Set<MessageModel>
  ): ConversationRegistrationModelBuilder {
    this.messages = messages;
    return this;
  }

  build(): ConversationRegistrationModel {
    this.validate();

    return new ConversationRegistrationModel(this.users, this.messages);
  }

  private validate(): void {
    if (!this.users) throw new Error('Users is required');
    if (!this.messages) throw new Error('Messges is required');
  }

  static create(): ConversationModelBuilder {
    return new ConversationModelBuilder();
  }
}
