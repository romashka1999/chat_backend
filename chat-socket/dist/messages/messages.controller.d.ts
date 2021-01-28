import { MessagesService } from './messages.service';
import { IMessage } from './message.entity';
export declare class MessagesController {
    private readonly messagesService;
    constructor(messagesService: MessagesService);
    sendMessageToUser(message: IMessage): Promise<void>;
}
