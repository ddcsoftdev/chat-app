import { ConversationModel } from './conversation.model';
import { EAuthRolesModel } from './roles.model';

export class UserModel {
  private id: number;
  private firstName: string;
  private lastName: string;
  private nickname: string;
  private email: string;
  private role: EAuthRolesModel;
  private conversations: Set<ConversationModel>;

  constructor(
    id: number,
    firstName: string,
    lastName: string,
    nickname: string,
    email: string,
    role: EAuthRolesModel,
    conversations: Set<ConversationModel>
  ) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.nickname = nickname;
    this.email = email;
    this.role = role;
    this.conversations = conversations;
  }

  getId(): number {
    return this.id;
  }
  getFirstName(): string {
    return this.firstName;
  }
  getLastName(): string {
    return this.lastName;
  }
  getNickname(): string {
    return this.nickname;
  }
  getEmail(): string {
    return this.email;
  }
  getRole(): EAuthRolesModel {
    return this.role;
  }
  getConversations(): Set<ConversationModel> {
    return this.conversations;
  }
}

export class UserModelBuilder {
  private id!: number;
  private firstName!: string;
  private lastName!: string;
  private nickname!: string;
  private email!: string;
  private role!: EAuthRolesModel;
  private conversations: Set<ConversationModel> = new Set();

  setId(id: number): UserModelBuilder {
    this.id = id;
    return this;
  }

  setFirstName(firstName: string): UserModelBuilder {
    this.firstName = firstName;
    return this;
  }

  setLastName(lastName: string): UserModelBuilder {
    this.lastName = lastName;
    return this;
  }

  setNickname(nickname: string): UserModelBuilder {
    this.nickname = nickname;
    return this;
  }

  setEmail(email: string): UserModelBuilder {
    this.email = email;
    return this;
  }

  setRole(role: EAuthRolesModel): UserModelBuilder {
    this.role = role;
    return this;
  }

  setConversations(conversations: Set<ConversationModel>): UserModelBuilder {
    this.conversations = conversations;
    return this;
  }

  addConversation(conversation: ConversationModel): UserModelBuilder {
    this.conversations.add(conversation);
    return this;
  }

  build(): UserModel {
    this.validate();

    return new UserModel(
      this.id,
      this.firstName,
      this.lastName,
      this.nickname,
      this.email,
      this.role,
      this.conversations
    );
  }

  private validate(): void {
    if (!this.id) throw new Error('Id is required');
    if (!this.firstName) throw new Error('First name is required');
    if (!this.lastName) throw new Error('Last name is required');
    if (!this.nickname) throw new Error('Nickname is required');
    if (!this.email) throw new Error('Email is required');
    if (!this.role) throw new Error('Role is required');

    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(this.email)) {
      throw new Error('Invalid email format');
    }
  }

  static create(): UserModelBuilder {
    return new UserModelBuilder();
  }
}

export class UserModelNoConversation {
  private id: number;
  private firstName: string;
  private lastName: string;
  private nickname: string;
  private email: string;
  private role: EAuthRolesModel;

  constructor(
    id: number,
    firstName: string,
    lastName: string,
    nickname: string,
    email: string,
    role: EAuthRolesModel
  ) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.nickname = nickname;
    this.email = email;
    this.role = role;
  }

  getId(): number {
    return this.id;
  }
  getFirstName(): string {
    return this.firstName;
  }
  getLastName(): string {
    return this.lastName;
  }
  getNickname(): string {
    return this.nickname;
  }
  getEmail(): string {
    return this.email;
  }
  getRole(): EAuthRolesModel {
    return this.role;
  }
}

export class UserModelNoConversationBuilder {
  private id!: number;
  private firstName!: string;
  private lastName!: string;
  private nickname!: string;
  private email!: string;
  private role!: EAuthRolesModel;

  setId(id: number): UserModelNoConversationBuilder {
    this.id = id;
    return this;
  }

  setFirstName(firstName: string): UserModelNoConversationBuilder {
    this.firstName = firstName;
    return this;
  }

  setLastName(lastName: string): UserModelNoConversationBuilder {
    this.lastName = lastName;
    return this;
  }

  setNickname(nickname: string): UserModelNoConversationBuilder {
    this.nickname = nickname;
    return this;
  }

  setEmail(email: string): UserModelNoConversationBuilder {
    this.email = email;
    return this;
  }

  setRole(role: EAuthRolesModel): UserModelNoConversationBuilder {
    this.role = role;
    return this;
  }

  build(): UserModelNoConversation {
    this.validate();

    return new UserModelNoConversation(
      this.id,
      this.firstName,
      this.lastName,
      this.nickname,
      this.email,
      this.role
    );
  }

  private validate(): void {
    if (!this.id) throw new Error('Id is required');
    if (!this.firstName) throw new Error('First name is required');
    if (!this.lastName) throw new Error('Last name is required');
    if (!this.nickname) throw new Error('Nickname is required');
    if (!this.email) throw new Error('Email is required');
    if (!this.role) throw new Error('Role is required');

    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(this.email)) {
      throw new Error('Invalid email format');
    }
  }

  static create(): UserModelNoConversationBuilder {
    return new UserModelNoConversationBuilder();
  }
}

export class UserRegistrationModel {
  private firstName: string;
  private lastName: string;
  private nickname: string;
  private email: string;
  private role: EAuthRolesModel;

  constructor(
    firstName: string,
    lastName: string,
    nickname: string,
    email: string,
    role: EAuthRolesModel
  ) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.nickname = nickname;
    this.email = email;
    this.role = role;
  }

  getFirstName(): string {
    return this.firstName;
  }
  getLastName(): string {
    return this.lastName;
  }
  getNickname(): string {
    return this.nickname;
  }
  getEmail(): string {
    return this.email;
  }
  getRole(): EAuthRolesModel {
    return this.role;
  }
}

export class UserRegistrationModelBuilder {
  private firstName!: string;
  private lastName!: string;
  private nickname!: string;
  private email!: string;
  private role!: EAuthRolesModel;

  setFirstName(firstName: string): UserRegistrationModelBuilder {
    this.firstName = firstName;
    return this;
  }

  setLastName(lastName: string): UserRegistrationModelBuilder {
    this.lastName = lastName;
    return this;
  }

  setNickname(nickname: string): UserRegistrationModelBuilder {
    this.nickname = nickname;
    return this;
  }

  setEmail(email: string): UserRegistrationModelBuilder {
    this.email = email;
    return this;
  }

  setRole(role: EAuthRolesModel): UserRegistrationModelBuilder {
    this.role = role;
    return this;
  }

  build(): UserRegistrationModel {
    this.validate();

    return new UserRegistrationModel(
      this.firstName,
      this.lastName,
      this.nickname,
      this.email,
      this.role
    );
  }

  private validate(): void {
    if (!this.firstName) throw new Error('First name is required');
    if (!this.lastName) throw new Error('Last name is required');
    if (!this.nickname) throw new Error('Nickname is required');
    if (!this.email) throw new Error('Email is required');
    if (!this.role) throw new Error('Role is required');

    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(this.email)) {
      throw new Error('Invalid email format');
    }
  }

  static create(): UserRegistrationModelBuilder {
    return new UserRegistrationModelBuilder();
  }
}
