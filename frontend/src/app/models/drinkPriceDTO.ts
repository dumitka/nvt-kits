import { DrinkDTO } from "./drinkDTO";

export interface DrinkPriceDTO {
    id: number;
    drinkDTO: DrinkDTO;
    price: number;
    lastDrinkCardId: number;
}