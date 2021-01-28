import { OnGatewayInit, OnGatewayConnection, OnGatewayDisconnect } from '@nestjs/websockets';
import { Socket, Server } from 'socket.io';
import { IMessage } from './message.entity';
export declare class MessagesGateway implements OnGatewayInit, OnGatewayConnection, OnGatewayDisconnect {
    wss: any;
    handleConnection(client: Socket, ...args: any[]): Promise<void>;
    handleDisconnect(client: Socket): Promise<void>;
    private readonly logger;
    afterInit(server: Server): void;
    handleJoinRoom(message: {
        userId: number;
        groupIds: number[];
    }, client: Socket): Promise<void>;
    typingToServer(message: {
        chatId: string;
        userId: number;
    }, client: Socket): Promise<void>;
    stopTypingToServer(message: {
        chatId: string;
        userId: number;
    }, client: Socket): Promise<void>;
    eraseMessageToserver(message: {
        chatId: string;
        userId: number;
    }, client: Socket): Promise<void>;
    standOnChatToServer(message: {
        chatId: string;
        userId: number;
    }, client: Socket): Promise<void>;
    messageCreated(createdMessage: IMessage): Promise<void>;
}
