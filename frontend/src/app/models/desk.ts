export class Desk {
    id: number;
	deskNumber: number;
    deskStatus: DeskStatus;
}

enum DeskStatus {
    NOT_ORDERED,
	ORDERED,
	DELIVERED_DRINKS,
	DELIVERED_MEALS,
	DELIVERED
}