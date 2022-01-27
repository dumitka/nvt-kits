export interface Table {

  id: Number;
  status: String; //enum
  type: Float32Array;
  tip: Number;
  
  x: Number;
  y: Number;
  height: Number;
  width: Number;

  reserved: boolean;
  capacity: Number;
  restaurant_id: Number;
}