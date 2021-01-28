export declare enum MessageType {
    TEXT = 0
}
export interface IMessage {
    id: string;
    groupId: number;
    userId: number;
    date: Date;
    type: MessageType;
    content: string;
}
