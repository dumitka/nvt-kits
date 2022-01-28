import { DrinkPriceDTO } from "./drinkPriceDTO";

export interface DrinkCardDTO {
    id: number;
    dateOfValidation: any;
    drinkPriceDTOs: DrinkPriceDTO[];
    restaurantId: number;
}