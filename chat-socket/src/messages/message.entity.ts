
export enum MessageType {
    TEXT,
}

export interface IMessage {
    id: string;
    groupId: number;
    userId: number;
    date: Date;
    type: MessageType;
    content: string;
}
