import { Module } from '@nestjs/common';
import { MongooseModule } from '@nestjs/mongoose';

import { MessagesModule } from './messages/messages.module';

@Module({
    imports: [
        MongooseModule.forRoot(
            'mongodb+srv://chat-user:Chatuser123@cluster0.arfwf.mongodb.net/<dbname>?retryWrites=true&w=majority',
            {
                useNewUrlParser: true,
                useUnifiedTopology: true,
            },
        ),
        MessagesModule,
    ],
})
export class AppModule {}
