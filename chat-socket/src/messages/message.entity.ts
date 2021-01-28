import { Schema, Document } from 'mongoose';

export enum MessageType {
  TEXT
}

export const MessageSchema = new Schema(
    {
        id: {
            type: String,
            required: true,
        },
        groupId: {
            type: Number,
            required: true,
        },
        userId: {
            type: Number,
            required: true,
        },
        date: {
            type: Date,
            required: true,
        },
        type: {
            type: MessageType,
            required: true,
        },
        content: {
            type: String,
            required: true,
        }
    },
);

export interface IMessage extends Document {
    id: string;
    groupId: number;
    userId: number;
    date: Date;
    type: MessageType;
    content: string;
}
