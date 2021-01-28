import { Body, Controller, Get, Post } from "@nestjs/common";
import { MessagesService } from './messages.service';
import { IMessage } from './message.entity';

@Controller('/messages')
export class MessagesController {
    constructor(private readonly messagesService: MessagesService) {}

    @Post()
    public async sendMessageToUser(@Body() message: IMessage) {
        return await this.messagesService.createMessage(message);
    }
}
