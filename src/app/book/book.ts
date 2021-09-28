export class Book {
  id?: number;
  name: string;
  price: number;
  pages: number;
  code: number;
  category: {
    id: number;
    name: string;
  }
}