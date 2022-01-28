export interface Table {

  id: number;
  status: String; //enum
  tip: number;
  
  x: number;
  y: number;
  height: number;
  width: number;

  tableNum: number;
  reserved: boolean;
}