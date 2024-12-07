import { LocalDateTime } from './date.model';

export class MessageModel {
  id: number;
  content: string;
  conversationId: number;
  userId: number;
  postedAt: LocalDateTime;
  editedAt: LocalDateTime;

  constructor(
    id: number,
    content: string,
    conversationId: number,
    userId: number,
    postedAt: LocalDateTime,
    editedAt: LocalDateTime
  ) {
    this.id = id;
    this.content = content;
    this.conversationId = conversationId;
    this.userId = userId;
    this.postedAt = postedAt;
    this.editedAt = editedAt;
  }

  getId(): number {
    return this.id;
  }

  getContent(): string {
    return this.content;
  }

  getConversationId(): number {
    return this.conversationId;
  }

  getUserId(): number {
    return this.userId;
  }

  getPostedAt(): LocalDateTime {
    return this.postedAt;
  }

  getEditedAt(): LocalDateTime {
    return this.editedAt;
  }
}

export class MessageModelBuilder {
  private id: number = 0;
  private content: string = '';
  private conversationId: number = 0;
  private userId: number = 0;
  private postedAt: LocalDateTime = new LocalDateTime('');
  private editedAt: LocalDateTime = new LocalDateTime('');

  setId(id: number): MessageModelBuilder {
    this.id = id;
    return this;
  }

  setContent(content: string): MessageModelBuilder {
    this.content = content;
    return this;
  }

  setConversationId(conversationId: number): MessageModelBuilder {
    this.conversationId = conversationId;
    return this;
  }

  setUserId(userId: number): MessageModelBuilder {
    this.userId = userId;
    return this;
  }

  setPostedAt(postedAt: LocalDateTime): MessageModelBuilder {
    this.postedAt = postedAt;
    return this;
  }

  setEditedAt(editedAt: LocalDateTime): MessageModelBuilder {
    this.editedAt = editedAt;
    return this;
  }

  build(): MessageModel {
    return new MessageModel(
      this.id,
      this.content,
      this.conversationId,
      this.userId,
      this.postedAt,
      this.editedAt
    );
  }

  private validate(): void {
    if (!this.id) throw new Error('Id is required');
    if (!this.content) throw new Error('Content is required');
    if (!this.conversationId) throw new Error('Conversation ID is required');
    if (!this.userId) throw new Error('User ID is required');
    if (!this.postedAt) throw new Error('Posted date is required');
  }

  static create(): MessageModelBuilder {
    return new MessageModelBuilder();
  }
}

export class MessageRegistrationModel {
  content: string;
  conversationId: number;
  userId: number;

  constructor(content: string, conversationId: number, userId: number) {
    this.content = content;
    this.conversationId = conversationId;
    this.userId = userId;
  }

  getContent(): string {
    return this.content;
  }

  getConversationId(): number {
    return this.conversationId;
  }

  getUserId(): number {
    return this.userId;
  }
}

export class MessageRegistrationModelBuilder {
  private content!: string;
  private conversationId!: number;
  private userId!: number;

  setContent(content: string): MessageRegistrationModelBuilder {
    this.content = content;
    return this;
  }

  setConversationId(conversationId: number): MessageRegistrationModelBuilder {
    this.conversationId = conversationId;
    return this;
  }

  setUserId(userId: number): MessageRegistrationModelBuilder {
    this.userId = userId;
    return this;
  }

  build(): MessageRegistrationModel {
    this.validate();
    return new MessageRegistrationModel(
      this.content,
      this.conversationId,
      this.userId
    );
  }

  private validate(): void {
    if (!this.content) throw new Error('Content is required');
    if (!this.conversationId) throw new Error('Conversation ID is required');
    if (!this.userId) throw new Error('User ID is required');
  }

  static create(): MessageRegistrationModelBuilder {
    return new MessageRegistrationModelBuilder();
  }
}

export class MessageUpdateModel {
  private content: string;

  constructor(content: string) {
    this.content = content;
  }

  getContent(): string {
    return this.content;
  }
}

export class MessageUpdateModelBuilder {
  private content!: string;

  setContent(content: string): MessageUpdateModelBuilder {
    this.content = content;
    return this;
  }

  build(): MessageUpdateModel {
    this.validate();
    return new MessageUpdateModel(this.content);
  }

  private validate(): void {
    if (!this.content) throw new Error('Content is required');
  }

  static create(): MessageUpdateModelBuilder {
    return new MessageUpdateModelBuilder();
  }
}
