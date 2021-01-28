import {
    WebSocketGateway,
    OnGatewayInit,
    OnGatewayConnection,
    OnGatewayDisconnect,
    WebSocketServer,
    ConnectedSocket,
    MessageBody,
    SubscribeMessage,
} from '@nestjs/websockets';
import { Logger } from '@nestjs/common';
import { Socket, Server } from 'socket.io';
import { IMessage } from './message.entity';

@WebSocketGateway(3001, { namespace: '/messages' })
export class MessagesGateway
    implements OnGatewayInit, OnGatewayConnection, OnGatewayDisconnect {
    @WebSocketServer() wss: any;


    async handleConnection(client: Socket, ...args: any[]) {
        this.logger.log(`ChatsGateway cient connected: ${client.id}`);
        client.emit('joinRoom', {});
    }

    async handleDisconnect(client: Socket) {
        this.logger.log(`cient disconnected: ${client.id}`);
        console.log(this.wss.adapter.nsp.adapter.rooms);
    }

    private readonly logger: Logger = new Logger('ChatsGateWay');

    afterInit(server: Server) {
        this.logger.log('initialized');
    }

    @SubscribeMessage('joinRoom')
    async handleJoinRoom(
        @MessageBody() message: { userId: number; groupIds: number[] },
        @ConnectedSocket() client: Socket,
    ) {
        const { userId, groupIds } = message;
        groupIds.forEach((groupId) => {
            client.join(`${groupId}groups`);
        });
    }

    @SubscribeMessage('typingToServer')
    async typingToServer(
        @MessageBody() message: { chatId: string; userId: number },
        @ConnectedSocket() client: Socket,
    ): Promise<void> {
        if (client.in(`${message.chatId}groups`)) {
            this.wss
                .to(`${message.chatId}groups`)
                .emit('typingToClient', message);
        }
    }

    @SubscribeMessage('stopTypingToServer')
    async stopTypingToServer(
        @MessageBody() message: { chatId: string; userId: number },
        @ConnectedSocket() client: Socket,
    ): Promise<void> {
        if (client.in(`${message.chatId}groups`)) {
            this.wss
                .to(`${message.chatId}groups`)
                .emit('stopTypingToClient', message);
        }
    }

    @SubscribeMessage('eraseMessageToserver')
    async eraseMessageToserver(
        @MessageBody() message: { chatId: string; userId: number },
        @ConnectedSocket() client: Socket,
    ): Promise<void> {
        if (client.in(`${message.chatId}groups`)) {
            this.wss
                .to(`${message.chatId}groups`)
                .emit('eraseMessageToClient', message);
        }
    }

    @SubscribeMessage('standOnChatToServer')
    async standOnChatToServer(
        @MessageBody() message: { chatId: string; userId: number },
        @ConnectedSocket() client: Socket,
    ): Promise<void> {
        if (client.in(`${message.chatId}groups`)) {
            this.wss
                .to(`${message.chatId}groups`)
                .emit('standOnChatToClient', message);
        }
    }

    public async messageCreated(createdMessage: IMessage) {
        this.wss
            .to(`${createdMessage.groupId}groups`)
            .emit('messageCreated', createdMessage);
    }
}
