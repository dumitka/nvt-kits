import { OrderedItem } from "./ordered-items";

export class Order {
    id: number;
    orderedMeals: OrderedItem[];
    orderedDrinks: OrderedItem[];
    notificationIds: number[];
    deskId: number;
}