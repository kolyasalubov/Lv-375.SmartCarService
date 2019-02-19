import { Skill } from './skill/skill';

export interface Worker {
    id: number;
    userName: string;
    fullName: string;
    password: string;
    email: string;
    numberPhone: string;
    skill: Skill;
}
