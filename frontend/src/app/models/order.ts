import { OrderedItem } from "./ordered-item";

export class Order {
    id: number;
    orderedMeals: OrderedItem[];
    orderedDrinks: OrderedItem[];
    notificationIds: number[];
    deskId: number;
}