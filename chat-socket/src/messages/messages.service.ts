import { Injectable } from '@nestjs/common';
import { MessagesGateway } from './messages.gateway';
import { IMessage } from './message.entity';

@Injectable()
export class MessagesService {
    constructor(private readonly messagesGateway: MessagesGateway) {}

    async createMessage(message: IMessage) {
        await this.messagesGateway.messageCreated(message);
    }
}
