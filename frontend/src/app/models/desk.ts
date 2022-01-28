export class Desk {
    id: number = 0;
    deskStatus: DeskStatus;
}

enum DeskStatus {
    NOT_ORDERED,
	ORDERED,
	DELIVERED_DRINKS,
	DELIVERED_MEALS,
	DELIVERED
}