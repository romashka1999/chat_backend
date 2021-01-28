import { MessagesGateway } from './messages.gateway';
import { IMessage } from './message.entity';
export declare class MessagesService {
    private readonly messagesGateway;
    constructor(messagesGateway: MessagesGateway);
    createMessage(message: IMessage): Promise<void>;
}
