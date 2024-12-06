import { UserModel } from "./user.model";

export class AuthLoginResquest {
    email: string;
    password: string;

    constructor(
        email: string,
        password: string){
        this.email = email;
        this.password = password;
    }
}

export class AuthLoginResponse {
    token: string;
    chatUserDTO: UserModel;

    constructor(
        token: string,
        chatUserDTO: UserModel){
        this.token = token;
        this.chatUserDTO = chatUserDTO;
    }
}