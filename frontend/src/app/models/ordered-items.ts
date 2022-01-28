export interface OrderedItem {
    id: number;
    amount: number;
    itemId: number; // meal id ili drink id
    orderId: number;
    creatorId: number; // cook id ili bartender id
    status: OrderedItemStatus;
}

enum OrderedItemStatus {
    ORDERED,
    IN_PROGRESS,
    DONE,
    DELIVERED
}