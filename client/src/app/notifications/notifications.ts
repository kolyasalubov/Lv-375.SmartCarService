export interface Notifications {
    id: number;
    message: string;
    suggestion: string;
    notificationTime: Date;
    type: string;
    carId: number;
    userId: number;
    skillId: number;
    isSelected: boolean;

    showCheckbox: boolean;
    showDropdown: boolean;
    showButton: boolean;
}
