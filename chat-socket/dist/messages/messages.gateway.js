"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
var __param = (this && this.__param) || function (paramIndex, decorator) {
    return function (target, key) { decorator(target, key, paramIndex); }
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.MessagesGateway = void 0;
const websockets_1 = require("@nestjs/websockets");
const common_1 = require("@nestjs/common");
let MessagesGateway = class MessagesGateway {
    constructor() {
        this.logger = new common_1.Logger('ChatsGateWay');
    }
    async handleConnection(client, ...args) {
        this.logger.log(`ChatsGateway cient connected: ${client.id}`);
        client.emit('joinRoom', {});
    }
    async handleDisconnect(client) {
        this.logger.log(`cient disconnected: ${client.id}`);
        console.log(this.wss.adapter.nsp.adapter.rooms);
    }
    afterInit(server) {
        this.logger.log('initialized');
    }
    async handleJoinRoom(message, client) {
        const { userId, groupIds } = message;
        groupIds.forEach((groupId) => {
            client.join(`${groupId}groups`);
        });
    }
    async typingToServer(message, client) {
        if (client.in(`${message.chatId}groups`)) {
            this.wss
                .to(`${message.chatId}groups`)
                .emit('typingToClient', message);
        }
    }
    async stopTypingToServer(message, client) {
        if (client.in(`${message.chatId}groups`)) {
            this.wss
                .to(`${message.chatId}groups`)
                .emit('stopTypingToClient', message);
        }
    }
    async eraseMessageToserver(message, client) {
        if (client.in(`${message.chatId}groups`)) {
            this.wss
                .to(`${message.chatId}groups`)
                .emit('eraseMessageToClient', message);
        }
    }
    async standOnChatToServer(message, client) {
        if (client.in(`${message.chatId}groups`)) {
            this.wss
                .to(`${message.chatId}groups`)
                .emit('standOnChatToClient', message);
        }
    }
    async messageCreated(createdMessage) {
        this.wss
            .to(`${createdMessage.groupId}groups`)
            .emit('messageCreated', createdMessage);
    }
};
__decorate([
    websockets_1.WebSocketServer(),
    __metadata("design:type", Object)
], MessagesGateway.prototype, "wss", void 0);
__decorate([
    websockets_1.SubscribeMessage('joinRoom'),
    __param(0, websockets_1.MessageBody()),
    __param(1, websockets_1.ConnectedSocket()),
    __metadata("design:type", Function),
    __metadata("design:paramtypes", [Object, Object]),
    __metadata("design:returntype", Promise)
], MessagesGateway.prototype, "handleJoinRoom", null);
__decorate([
    websockets_1.SubscribeMessage('typingToServer'),
    __param(0, websockets_1.MessageBody()),
    __param(1, websockets_1.ConnectedSocket()),
    __metadata("design:type", Function),
    __metadata("design:paramtypes", [Object, Object]),
    __metadata("design:returntype", Promise)
], MessagesGateway.prototype, "typingToServer", null);
__decorate([
    websockets_1.SubscribeMessage('stopTypingToServer'),
    __param(0, websockets_1.MessageBody()),
    __param(1, websockets_1.ConnectedSocket()),
    __metadata("design:type", Function),
    __metadata("design:paramtypes", [Object, Object]),
    __metadata("design:returntype", Promise)
], MessagesGateway.prototype, "stopTypingToServer", null);
__decorate([
    websockets_1.SubscribeMessage('eraseMessageToserver'),
    __param(0, websockets_1.MessageBody()),
    __param(1, websockets_1.ConnectedSocket()),
    __metadata("design:type", Function),
    __metadata("design:paramtypes", [Object, Object]),
    __metadata("design:returntype", Promise)
], MessagesGateway.prototype, "eraseMessageToserver", null);
__decorate([
    websockets_1.SubscribeMessage('standOnChatToServer'),
    __param(0, websockets_1.MessageBody()),
    __param(1, websockets_1.ConnectedSocket()),
    __metadata("design:type", Function),
    __metadata("design:paramtypes", [Object, Object]),
    __metadata("design:returntype", Promise)
], MessagesGateway.prototype, "standOnChatToServer", null);
MessagesGateway = __decorate([
    websockets_1.WebSocketGateway(3001, { namespace: '/messages' })
], MessagesGateway);
exports.MessagesGateway = MessagesGateway;
//# sourceMappingURL=messages.gateway.js.map