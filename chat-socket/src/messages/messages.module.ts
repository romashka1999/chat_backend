import { Module } from '@nestjs/common';

import { MessagesController } from './messages.controller';
import { MessagesGateway } from './messages.gateway';
import { MessagesService } from './messages.service';

@Module({
    imports: [],
    providers: [MessagesService, MessagesGateway],
    controllers: [MessagesController],
})
export class MessagesModule {}
