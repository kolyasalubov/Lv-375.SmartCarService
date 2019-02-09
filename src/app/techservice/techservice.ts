import { TechmanagerProfile } from '../techmanager-profile/techmanager-profile';

export interface Techservice {
    workers: []; // array of Worker
    dealer: any; // Dealer type
    techManager: TechmanagerProfile;
}