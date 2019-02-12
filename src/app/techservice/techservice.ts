import { TechmanagerProfile } from '../techmanager-profile/techmanager-profile';

export interface Techservice {
    name: string;
    address: string;
    workers: []; // array of Worker
    dealer: any; // Dealer type
    techManager: TechmanagerProfile;
}